package com.example.timefast

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.databinding.ActivityLoginBinding
import com.example.timefast.poko.LoginColaborador
import com.example.timefast.util.Constantes
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.koushikdutta.ion.Ion

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    override fun onStart() {
        super.onStart()
        binding.btnEntrar.setOnClickListener {
            val numeroPersonal = binding.personalNumero.text.toString()
            val contrasena = binding.contrasena.text.toString()
            if(validarCampos(numeroPersonal, contrasena)){
                verificarCredenciales(numeroPersonal, contrasena)
            }
        }
    }

    fun validarCampos(numeroPersonal:String, contrasena:String):Boolean {
        var camposValidos = true
        if(numeroPersonal.isEmpty()){
            camposValidos = false
            binding.personalNumero.error = "Numero personal obligatorio"
        }
        if(contrasena.isEmpty()){
            camposValidos = false
            binding.contrasena.error = "Ingrese su contraseña"
        }
        return camposValidos
    }

    fun verificarCredenciales(noPersonal: String, contrasena: String ){
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)
        Ion.with(this@LoginActivity)
            .load("POST","${Constantes().url_ws}/login/iniciar-sesion")
            .setHeader("Content-Type","application/x-www-form-urlencoded")
            .setBodyParameter("noPersonal",noPersonal)
            .setBodyParameter("contrasena",contrasena)
            .asString()
            .setCallback{e, result ->
                if(e == null){
                    Log.d("LoginActivity","Respuesta del servidor: $result" )
                    if(result != null && result.startsWith("{")){
                        try{
                            Log.d("LoginActivity","Entro al metodo para realiazr la serializacion")
                            serializarInformacion(result)
                        }catch (ex:JsonSyntaxException){
                            Log.e("LoginActivity", "Error de deserialización: ${ex.message}")
                            Toast.makeText(this@LoginActivity, "Error al procesar la respuesta del servidor", Toast.LENGTH_LONG).show()
                        }
                    }else {
                        Log.d("LoginActivity", "No entro al metodo para realiazr la serializacion")
                        Toast.makeText(this@LoginActivity, "Respuesta inesperada del servidor", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    public fun serializarInformacion(json: String){
        try{
            val gson = Gson()
            val respuestaLoginColaborador = gson.fromJson(json, LoginColaborador::class.java)
            Log.d("LoginActivity", "Cliente deserializado: ${respuestaLoginColaborador.Colaborador}")
            if(respuestaLoginColaborador.Colaborador!=null){
                val clienteJSON = gson.toJson(respuestaLoginColaborador.Colaborador);
                Log.d("LoginActivity",clienteJSON)
                irPantallaPrincipal(clienteJSON);
            }
        }catch(ex: JsonSyntaxException){
            Log.e("LoginActivity", "Error de deserialización: ${ex.message}")
            Toast.makeText(this@LoginActivity, "Error al procesar la respuesta del servidor", Toast.LENGTH_LONG).show()
        }

    }

    fun irPantallaPrincipal(colaborador:String){
        val intent = Intent(this@LoginActivity,EnviosAsignadosActivity::class.java)
        intent.putExtra("colaborador", colaborador);
        Log.e("Datos de colaborador es : ", colaborador)
        startActivity(intent)
        finish()
    }



}