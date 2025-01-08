package com.example.timefast

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.timefast.databinding.ActivityEnviosDetallesBinding
import com.example.timefast.poko.Colaborador
import com.example.timefast.poko.Envio
import com.example.timefast.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import org.json.JSONArray

class EnviosDetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnviosDetallesBinding
    private lateinit var envio: Envio
    private lateinit var colaborador: Colaborador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envios_detalles)
        binding = ActivityEnviosDetallesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerEnvio()
        obtenerColaborador()
    }


    override fun onStart() {
        super.onStart()
        binding.btnActualizarEstatus.setOnClickListener {
            irPantallaActualizarEnvio()
        }
        obtenerEnvio()
        obtenerColaborador()
        mostrarInformacionEnvio()
    }

    fun obtenerPaquetes(callback: (String) -> Unit) {
        Ion.with(this@EnviosDetallesActivity)
            .load("GET","${Constantes().url_ws}/paquetes/obtener-paquetes-envio/${envio.idEnvio}")
            .asString()
        .setCallback { e, result ->
            if(e == null){
                Log.d("EnviosDetallesActivity","Respuesta del servidor: $result" )
                callback(result) // Devuelve la respuesta al callback
            } else{
                Log.e("EnviosDetallesActivity", "Error: ${e.message}")
                callback("Error al obtener paquetes")
            }
        }
    }

    private fun mostrarInformacionEnvio() {
        binding.guiaValor.text = envio.noGuia
        binding.origenValor.text = envio.origen
        binding.destinoValor.text = envio.destino
        binding.estatusValor.text = envio.estadoDeEnvio
        binding.clienteValor.text = envio.cliente

        obtenerPaquetes { paquetesJson ->
            // Procesar la respuesta JSON y mostrarla
            try {
                val paquetesArray = JSONArray(paquetesJson)
                val stringBuilder = StringBuilder()

                for (i in 0 until paquetesArray.length()) {
                    val paquete = paquetesArray.getJSONObject(i)
                    stringBuilder.append("Descripción: ${paquete.getString("descripcion")}\n")
                    stringBuilder.append("Dimensiones: ${paquete.getString("dimensiones")}\n")
                    stringBuilder.append("Peso: ${paquete.getDouble("peso")} kg\n")
                    stringBuilder.append("Alto: ${paquete.getString("alto")} cm\n")
                    stringBuilder.append("Ancho: ${paquete.getString("ancho")} cm\n")
                }

                runOnUiThread {
                    binding.paquetesValor.text = stringBuilder.toString()
                }
            } catch (e: Exception) {
                Log.e("EnviosDetallesActivity", "Error al procesar el JSON: ${e.message}")
                runOnUiThread {
                    binding.paquetesValor.text = "No hay paquetes"
                }
            }
        }

    }


    fun irPantallaActualizarEnvio(){
        val intent = Intent(this@EnviosDetallesActivity,EnvioActualizarEstatusActivity::class.java)
        val json = serializarInformacion(envio)
        val jsonColaborador = serializarColaborador(colaborador)
        intent.putExtra("envio", json);
        intent.putExtra("colaborador", jsonColaborador);
        startActivity(intent)
    }


    fun serializarInformacion(envio: Envio): String {
        val gson = Gson()
        val json = gson.toJson(envio)
        return json
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

    fun serializarColaborador(colaborador: Colaborador): String {
        val gson = Gson()
        val json = gson.toJson(colaborador)
        return json
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

}