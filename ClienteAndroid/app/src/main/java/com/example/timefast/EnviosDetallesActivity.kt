package com.example.timefast

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.databinding.ActivityEnviosDetallesBinding
import com.example.timefast.poko.Colaborador
import com.example.timefast.poko.Envio
import com.google.gson.Gson

class EnviosDetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnviosDetallesBinding
    private lateinit var envio: Envio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_envios_detalles)
        binding = ActivityEnviosDetallesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerEnvio()
        mostrarInformacionEnvio()
    }


    override fun onStart() {
        super.onStart()
        binding.btnActualizarEstatus.setOnClickListener {
            irPantallaActualizarEnvio()
        }
    }

    private fun mostrarInformacionEnvio() {
        binding.guiaValor.text = envio.noGuia
        binding.origenValor.text = envio.origen
        binding.destinoValor.text = envio.destino
        //binding.paquetesValor.text = envio.
        binding.estatusValor.text = envio.estadoDeEnvio
        binding.clienteValor.text = envio.cliente
    }


    fun irPantallaActualizarEnvio(){
        val intent = Intent(this@EnviosDetallesActivity,EnvioActualizarEstatusActivity::class.java)
        val json = serializarInformacion(envio)
        intent.putExtra("envio", json);
        startActivity(intent)
    }


    fun serializarInformacion(envio: Envio): String {
        val gson = Gson()
        val colaboradorJson = gson.toJson(envio)
        return colaboradorJson
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