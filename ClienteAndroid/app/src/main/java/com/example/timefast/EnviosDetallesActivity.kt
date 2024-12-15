package com.example.timefast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.databinding.ActivityEnviosDetallesBinding

class EnviosDetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnviosDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_envios_detalles)
        binding = ActivityEnviosDetallesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}