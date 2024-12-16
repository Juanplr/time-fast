package com.example.timefast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.databinding.ActivityEnvioActualizarEstatusBinding

class EnvioActualizarEstatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnvioActualizarEstatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_envio_actualizar_estatus)
        binding = ActivityEnvioActualizarEstatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


}