package com.example.timefast

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.adapter.EstadoAdapter
import com.example.timefast.databinding.ActivityEnvioActualizarEstatusBinding
import com.example.timefast.poko.Envio
import com.example.timefast.poko.EstadoEnvio
import com.example.timefast.poko.HistorialDeEnvio
import com.example.timefast.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date

class EnvioActualizarEstatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnvioActualizarEstatusBinding
    private lateinit var listaEstados: List<EstadoEnvio>
    private lateinit var envio: Envio


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envio_actualizar_estatus)
        binding = ActivityEnvioActualizarEstatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerEnvio()
        cargarEstatusDesdeWeb()
        configurarBotonGuardar()
    }


        Ion.with(this)
            .load("GET", "${Constantes().url_ws}/envio/obtener-estados-envios")
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    try {
                        val gson = Gson()
                        listaEstados = gson.fromJson(result, Array<EstadoEnvio>::class.java).toList()
                        llenarSpinnerEstatus(listaEstados)
                    } catch (ex: Exception) {
                        Toast.makeText(this, "Error al procesar los datos", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun llenarSpinnerEstatus(estados: List<EstadoEnvio>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados.map { it.nombre })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEstatus.adapter = adapter

        // Almacenar los estados completos (opcional si los necesitas luego)
        listaEstados = estados
    }

    private fun enviarActualizacionEstatus(idEstatus: Int, comentario: String) {
        val json = JSONObject()
        json.put("idEnvio", envio.idEnvio) // Desde el objeto envio
        json.put("idEstadoDeEnvio", idEstatus) // Desde el Spinner
        json.put("idColaborador", envio.idColaborador) // Desde el objeto envio
        json.put("noGuia", envio.noGuia) // Desde el objeto envio
        json.put("motivo", comentario) // Comentario ingresado
        json.put("tiempoDeCambio", obtenerFechaActual()) // Fecha actual

        Log.e("EnvioActualizarEstatusActivity", "JSON enviado: $json")

        Ion.with(this)
            .load("PUT", "${Constantes().url_ws}/historial-envio/editar")
            .setHeader("Content-Type", "application/json")
            .setStringBody(json.toString())
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    Toast.makeText(this, "Estatus actualizado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al actualizar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun configurarBotonGuardar() {
        binding.btnGuardarCambios.setOnClickListener {
            val posicionSeleccionada = binding.spinnerEstatus.selectedItemPosition
            val estadoSeleccionado = listaEstados[posicionSeleccionada] // Estado completo
            val idEstatus = estadoSeleccionado.idEstadoDeEnvio
            val nombreEstatus = estadoSeleccionado.nombre
            val comentario = binding.edittextComentario.text.toString()

            if (validarComentario(nombreEstatus, comentario)) {
                enviarActualizacionEstatus(idEstatus, comentario)
            }
        }
    }



    private fun validarComentario(nombreEstatus: String, comentario: String): Boolean {
        return if ((nombreEstatus == "Detenido" || nombreEstatus == "Cancelado") && comentario.isBlank()) {
            Toast.makeText(this, "El comentario es obligatorio para el estatus '$nombreEstatus'", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun enviarCambios(idEstatus: Int, comentario: String) {
        val fechaActual = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val historial = HistorialDeEnvio(
            idHistorialDeEnvio = null,
            idEstadoDeEnvio = idEstatus,
            idColaborador = -1, //EL ID del conductor que esta cambiando los datos del envio
            colaborador = null,
            noGuia = "AQUI Va los datos del no guia",
            motivo = if (comentario.isEmpty()) "S/M" else comentario,
            tiempoDeCambio = fechaActual
        )

        val gson = Gson()
        val parametros = gson.toJson(historial)

        Ion.with(this@EnvioActualizarEstatusActivity)
            .load("POST", "${Constantes().url_ws}/historial-envio/agregar")
            .setHeader("Content-Type", "application/json")
            .setStringBody(parametros)
            .asString()
            .setCallback { e, result ->
                if (e == null) {

                    Log.d("HistorialEnvio", "Respuesta: $result")
                } else {
                    Log.e("HistorialEnvio", "Error al enviar historial", e)
                }
            }
    }
    private fun editarEnvio(envio: Envio?):Boolean{
        var validacion = false
        val gson = Gson()
        val parametros = gson.toJson(envio)
        Ion.with(this@EnvioActualizarEstatusActivity)
            .load("PUT","${Constantes().url_ws}/envio/editar-envio")
            .setHeader("Content-Type","application/json")
            .setStringBody(parametros)
            .asString()
            .setCallback { e, result ->
                if(e == null){
                    validacion = true

                }else{
                    validacion = false
                }
            }
        return validacion
    }



    private fun obtenerFechaActual(): String {
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatoFecha.format(Date())
    }

    fun obtenerEnvio(){
        val envioJson = intent.getStringExtra("envio")
        if (envioJson != null) {
            val gson = Gson()
            envio = gson.fromJson(envioJson, Envio::class.java)
        } else {
            // Manejar el caso donde colaborador no se pasó en el Intent
            throw IllegalStateException("Envio no inicializado. Asegúrate de pasar el colaborador desde la actividad anterior.")
        }
    }

}

