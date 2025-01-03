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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_envio_actualizar_estatus)
        binding = ActivityEnvioActualizarEstatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cargarEstatusDesdeWeb()
    }


    override fun onStart() {
        super.onStart()
        binding.btnGuardarCambios.setOnClickListener{
            val estadoSeleccionado = binding.spinnerEstatus.selectedItem as EstadoEnvio
            val nombreEstatus = estadoSeleccionado.nombre
            val idEstatus = estadoSeleccionado.idEstadoDeEnvio
            val comentario = binding.edittextComentario.text.toString()

            if (validarComentario(nombreEstatus, comentario)) {
                if(editarEnvio(null)){
                    enviarCambios(idEstatus, comentario)
                }
            }
        }
    }


    private fun cargarEstatusDesdeWeb() {
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



     fun llenarSpinnerEstatus(estados: List<EstadoEnvio>) {
        val adapter = EstadoAdapter(this, estados)
        binding.spinnerEstatus.adapter = adapter
    }


    private fun validarComentario(estatus: String, comentario: String): Boolean {
        return if ((estatus == "Detenido" || estatus == "Cancelado") && comentario.isBlank()) {
            Toast.makeText(
                this,
                "El comentario es obligatorio para el estatus '$estatus'",
                Toast.LENGTH_SHORT
            ).show()
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





}