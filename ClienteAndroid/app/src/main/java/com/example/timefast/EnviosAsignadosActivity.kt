package com.example.timefast

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timefast.adapter.EnviosAdapter
import com.example.timefast.databinding.ActivityEnviosAsignadosBinding
import com.example.timefast.databinding.ActivityLoginBinding
import com.example.timefast.poko.Colaborador
import com.example.timefast.poko.Envio
import com.example.timefast.poko.LoginColaborador
import com.example.timefast.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class EnviosAsignadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnviosAsignadosBinding
    private lateinit var colaborador: Colaborador
    private lateinit var enviosAdapter: EnviosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envios_asignados)
        binding = ActivityEnviosAsignadosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerColaborador()
        setupRecyclerView()
        obtenerEnvios()
    }

    override fun onStart() {
        super.onStart()
        binding.irPerfil.setOnClickListener {
            irPantallaActualizarPerfil()
        }
        obtenerEnvios()
    }


    fun setupRecyclerView() {
        enviosAdapter = EnviosAdapter(emptyList()) { envioSeleccionado ->
            irADetalleEnvio(envioSeleccionado) // Acción al hacer clic en un envío
        }
        binding.recyclerViewEnvios.apply {
            layoutManager = LinearLayoutManager(this@EnviosAsignadosActivity)
            adapter = enviosAdapter
        }
    }


    private fun obtenerEnvios() {
        Ion.with(this@EnviosAsignadosActivity)
            .load("GET", "${Constantes().url_ws}/envio/obtener-envios-conductor/${colaborador.idColaborador}")
            .setHeader("Accept", "application/json")
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    try {
                        val decodedResult = result?.toByteArray(Charsets.ISO_8859_1)?.toString(Charsets.UTF_8) ?: ""
                        Log.e("EnviosError", decodedResult)
                        val gson = Gson()
                        val envios = gson.fromJson(result, Array<Envio>::class.java).toList()
                        actualizarRecyclerView(envios)
                    } catch (ex: Exception) {
                        Log.e("EnviosError", "Error al procesar el JSON: ${ex.message}")
                        Toast.makeText(this, "Error al procesar los datos", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("EnviosError", "Error de conexión: ${e.message}")
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
    }



    private fun actualizarRecyclerView(envios: List<Envio>) {
        if (envios.isEmpty()){
            Toast.makeText(this, "No tienes Envios", Toast.LENGTH_LONG).show()
        }else{
            enviosAdapter = EnviosAdapter(envios) { envioSeleccionado ->
                irADetalleEnvio(envioSeleccionado)
            }
            binding.recyclerViewEnvios.adapter = enviosAdapter
        }

    }


    private fun irADetalleEnvio(envio: Envio) {
        val intent = Intent(this, EnviosDetallesActivity::class.java)
        val json = Gson().toJson(envio) // Serializar el objeto Envio a JSON
        val jsonColaborador = serializarInformacion(colaborador)
        intent.putExtra("envio", json)
        intent.putExtra("colaborador", jsonColaborador)
        startActivity(intent)
    }



    fun irPantallaActualizarPerfil(){
        val intent = Intent(this@EnviosAsignadosActivity,ActualizarPerfilActivity::class.java)
        val json = serializarInformacion(colaborador)
        intent.putExtra("colaborador", json);
        startActivity(intent)
    }


    fun serializarInformacion(colaborador: Colaborador): String {
        val gson = Gson()
        val colaboradorJson = gson.toJson(colaborador)
        return colaboradorJson
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