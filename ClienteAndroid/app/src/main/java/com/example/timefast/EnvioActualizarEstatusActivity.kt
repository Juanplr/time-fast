package com.example.timefast

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.adapter.EstadoAdapter
import com.example.timefast.databinding.ActivityEnvioActualizarEstatusBinding
import com.example.timefast.poko.EstadoEnvio
import com.example.timefast.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import org.json.JSONObject

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
                //enviarCambios(idEstatus, comentario)
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




}