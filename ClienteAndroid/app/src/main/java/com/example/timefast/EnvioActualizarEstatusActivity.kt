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
import com.example.timefast.poko.Colaborador
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
    private lateinit var colaborador: Colaborador
    private lateinit var envio: Envio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envio_actualizar_estatus)
        binding = ActivityEnvioActualizarEstatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerColaborador()
        obtenerEnvio()
        cargarEstatusDesdeWeb()

    }


    override fun onStart() {
        super.onStart()
        binding.btnGuardarCambios.setOnClickListener{
            val estadoSeleccionado = binding.spinnerEstatus.selectedItem as EstadoEnvio
            val nombreEstatus = estadoSeleccionado.nombre
            val idEstatus = estadoSeleccionado.idEstadoDeEnvio
            val comentario = binding.edittextComentario.text.toString()

            /*
            if (validarComentario(nombreEstatus, comentario)) {

                if(editarEnvio(envio)){
                    enviarCambios(idEstatus, comentario)
                    Toast.makeText(this, "Cambios guardados con éxito", Toast.LENGTH_SHORT).show()
                    finish() // Cierra la actividad actual y regresa a la anterior
                }else{
                    Toast.makeText(this, "Error al editar el envío", Toast.LENGTH_SHORT).show()
                }

            }*/


            if (validarComentario(nombreEstatus, comentario)) {
                // Enviar primero los cambios (historial de envío)
                enviarCambios(idEstatus, comentario) { success ->
                    if (success) {
                        // Si el envío del historial fue exitoso, actualizar el estado del envío
                        editarEnvio(envio) { envioActualizado ->
                            if (envioActualizado) {
                                Toast.makeText(this, "Cambios guardados con éxito", Toast.LENGTH_SHORT).show()
                                finish() // Cierra la actividad actual y regresa a la anterior
                            } else {
                                Toast.makeText(this, "Error al actualizar el envío", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Error al enviar historial", Toast.LENGTH_SHORT).show()
                    }
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

    /*
    private fun enviarCambios(idEstatus: Int, comentario: String) {
        val fechaActual = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val historial = HistorialDeEnvio(
            idHistorialDeEnvio = 1,
            idEstadoDeEnvio = idEstatus,
            idColaborador = colaborador.idColaborador, //EL ID del conductor que esta cambiando los datos del envio
            colaborador = colaborador.nombre,
            noGuia = envio.noGuia,
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
    } */

    private fun enviarCambios(idEstatus: Int, comentario: String, callback: (Boolean) -> Unit) {
        val fechaActual = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val historial = HistorialDeEnvio(
            idHistorialDeEnvio = 1,
            idEstadoDeEnvio = idEstatus,
            idColaborador = colaborador.idColaborador,
            colaborador = colaborador.nombre,
            noGuia = envio.noGuia,
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
                    callback(true) // Indicar que el envío fue exitoso
                } else {
                    Log.e("HistorialEnvio", "Error al enviar historial", e)
                    callback(false) // Indicar que hubo un error
                }
            }
    }


    /*
    private fun editarEnvio(envio: Envio):Boolean{
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
    } */

    private fun editarEnvio(envio: Envio, callback: (Boolean) -> Unit) {
        val gson = Gson()
        val parametros = gson.toJson(envio)
        Ion.with(this@EnvioActualizarEstatusActivity)
            .load("PUT", "${Constantes().url_ws}/envio/editar-envio")
            .setHeader("Content-Type", "application/json")
            .setStringBody(parametros)
            .asString()
            .setCallback { e, _ ->
                if (e == null) {
                    callback(true) // Si no hubo error, indicamos que la actualización fue exitosa
                } else {
                    callback(false) // Si hubo error, indicamos que no se pudo actualizar el envío
                }
            }
    }



    fun obtenerColaborador(){
        val colaboradorJson = intent.getStringExtra("colaborador")
        if (colaboradorJson != null) {
            val gson = Gson()
            colaborador = gson.fromJson(colaboradorJson, Colaborador::class.java)
        } else {
            // Manejar el caso donde colaborador no se pasó en el Intent
            throw IllegalStateException("Colaborador no inicializado. Asegúrate de pasar el colaborador desde la actividad anterior.")
        }
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