package com.example.timefast

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.databinding.ActivityEnviosAsignadosBinding
import com.example.timefast.databinding.ActivityLoginBinding
import com.example.timefast.poko.Colaborador
import com.example.timefast.poko.LoginColaborador
import com.google.gson.Gson

class EnviosAsignadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnviosAsignadosBinding
    private lateinit var colaborador: Colaborador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_envios_asignados)
        binding = ActivityEnviosAsignadosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Inicializa colaborador desde el Intent
        val colaboradorJson = intent.getStringExtra("colaborador")
        if (colaboradorJson != null) {
            val gson = Gson()
            colaborador = gson.fromJson(colaboradorJson, Colaborador::class.java)
        } else {
            // Manejar el caso donde colaborador no se pasó en el Intent
            throw IllegalStateException("Colaborador no inicializado. Asegúrate de pasar el colaborador desde la actividad anterior.")
        }
    }

    override fun onStart() {
        super.onStart()
        binding.irPerfil.setOnClickListener {
            irPantallaActualizarPerfil()
        }
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

}