package com.example.timefast

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timefast.databinding.ActivityActualizarPerfilBinding
import com.example.timefast.databinding.ActivityEnviosAsignadosBinding
import com.example.timefast.poko.Colaborador
import com.example.timefast.poko.Mensaje
import com.example.timefast.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import java.io.ByteArrayOutputStream
import java.io.InputStream

class ActualizarPerfilActivity : AppCompatActivity() {

    private lateinit var binding : ActivityActualizarPerfilBinding
    private lateinit var colaborador: Colaborador
    private var fotoPerfilBytes : ByteArray ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_perfil)
        binding = ActivityActualizarPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerColaborador()
        cargarDatosColaborador()
    }


    override fun onStart() {
        super.onStart()
        obtenerFotoColaborador(colaborador.idColaborador)
        binding.cambiarFoto.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            selecionarFotoImagenPerfil.launch(intent)
        }
        binding.cerrarSesion.setOnClickListener {
            cerrarSesion()
        }
        binding.guardarCambios.setOnClickListener {
            if(validarCampos()){
                val colaborador = cargarDatos()
                enviarDatosActualizados(colaborador)
            }else{
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun obtenerColaborador(){
        val jsonColaborador =intent.getStringExtra("colaborador")
        if(jsonColaborador != null){
            val gson = Gson()
            colaborador = gson.fromJson(jsonColaborador, Colaborador::class.java)
        }
    }

    fun cargarDatosColaborador(){
        binding.nombre.setText(colaborador.nombre)
        binding.apellidoPaterno.setText(colaborador.apellidoPaterno)
        binding.apellidoMaterno.setText(colaborador.apellidoMaterno)
        binding.curp.setText(colaborador.curp)
        binding.email.setText(colaborador.correo)
        binding.contrasena.setText(colaborador.contrasena)
        binding.numeroLicencia.setText(colaborador.numeroDeLicencia)
    }

    fun validarCampos(): Boolean {
        // Verifica si algún campo está vacío
        val nombre = binding.nombre.text.toString()
        val apellidoPaterno = binding.apellidoPaterno.text.toString()
        val apellidoMaterno = binding.apellidoMaterno.text.toString()
        val curp = binding.curp.text.toString()
        val email = binding.email.text.toString()
        val numeroLicencia = binding.numeroLicencia.text.toString()
        val contrasena = binding.contrasena.text.toString()

        // Mostrar en el log los valores de los campos (opcional)
        Log.d("Campos", "Nombre: $nombre, Apellido Paterno: $apellidoPaterno, Apellido Materno: $apellidoMaterno, CURP: $curp, Email: $email, Licencia: $numeroLicencia, Contraseña: $contrasena")

        // Devuelve true si todos los campos están llenos
        return nombre.isNotEmpty() &&
                apellidoPaterno.isNotEmpty() &&
                apellidoMaterno.isNotEmpty() &&
                curp.isNotEmpty() &&
                email.isNotEmpty() &&
                numeroLicencia.isNotEmpty() &&
                contrasena.isNotEmpty()
    }



    fun enviarDatosActualizados(colaborador: Colaborador){
        val gson = Gson()
        val parametros = gson.toJson(colaborador)
        Ion.with(this@ActualizarPerfilActivity)
            .load("PUT","${Constantes().url_ws}/colaborador/editar-colaborador")
            .setHeader("Content-Type","application/json")
            .setStringBody(parametros)
            .asString()
            .setCallback { e, result ->
                if(e == null){
                    Log.d("Colaborador actualizado", result)
                    //Toast.makeText(this@ActualizarPerfilActivity, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                    respuestaEdicion(result)

                }else{

                }
            }

    }


    fun respuestaEdicion(resultado:String){
        try{
            val gson = Gson()
            val mensaje = gson.fromJson(resultado, Mensaje::class.java)
            Toast.makeText(this@ActualizarPerfilActivity, "Informacion actualizada con éxito", Toast.LENGTH_SHORT).show()
            //Toast.makeText(this@ActualizarPerfilActivity,mensaje.mensaje, Toast.LENGTH_SHORT).show()
            Log.e("ERROR", mensaje.mensaje)
            if(!mensaje.error){
                finish()
            }
        }catch (ex : Exception){
            Toast.makeText(this@ActualizarPerfilActivity, ex.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun cargarDatos():Colaborador{
        return Colaborador(
            nombre = binding.nombre.text.toString(),
            apellidoPaterno = binding.apellidoPaterno.text.toString(),
            apellidoMaterno = binding.apellidoMaterno.text.toString(),
            curp = binding.curp.text.toString(),
            correo = binding.email.text.toString(),
            contrasena = binding.contrasena.text.toString(),
            numeroDeLicencia = binding.numeroLicencia.text.toString(),
            fotografia = colaborador.fotografia,
            idColaborador = colaborador.idColaborador,
            noPersonal = colaborador.noPersonal,
            idRol = colaborador.idRol,
            rol = colaborador.rol
        )
    }


    fun obtenerFotoColaborador(idColaborador : Int) {
        Ion.with(this@ActualizarPerfilActivity)
            .load("GET", "${Constantes().url_ws}/colaborador/obtener-foto/${idColaborador}")
            .asString()
            .setCallback() {
                    e, result ->
                if (e == null) {
                    cargarfotoCliente(result)
                } else {
                    Toast.makeText(this@ActualizarPerfilActivity, "Error" + e.message, Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun cargarfotoCliente(json :String) {
        if(json.isNotEmpty()){
            val gson = Gson()
            val colaboradorFoto = gson.fromJson(json, Colaborador::class.java)
            if(colaboradorFoto.fotografia != null){
                try{
                    val imgBytes = Base64.decode(colaboradorFoto.fotografia, Base64.DEFAULT)
                    val imgBitMap = BitmapFactory.decodeByteArray(imgBytes,0,imgBytes.size)
                    binding.foto.setImageBitmap(imgBitMap)
                }catch (e : Exception){
                    Toast.makeText(this@ActualizarPerfilActivity, "Error imagen " + e.message, Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@ActualizarPerfilActivity, "No hay foto disponible", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val selecionarFotoImagenPerfil = this.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result : ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val data = result.data
            val imgURI = data?.data
            if(imgURI != null){
                fotoPerfilBytes = uriToByteArray(imgURI)
                if(fotoPerfilBytes != null){
                    subirFotoPerfil(colaborador.idColaborador)
                }
            }
        }
    }

    private fun uriToByteArray(uri: Uri): ByteArray? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            if (inputStream == null) {
                throw IllegalArgumentException("Input stream could not be opened for the given URI.")
            }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            byteArrayOutputStream.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun subirFotoPerfil(idColaborador: Int){
        Ion.with(this@ActualizarPerfilActivity)
            .load("PUT", "${Constantes().url_ws}/colaborador/subir-foto/${idColaborador}")
            .setByteArrayBody(fotoPerfilBytes)
            .asString()
            .setCallback() {
                    e, result ->
                if(e==null) {
                    val gson = Gson()
                    val msj = gson.fromJson(result, Mensaje::class.java)
                    Toast.makeText(this@ActualizarPerfilActivity,msj.mensaje, Toast.LENGTH_SHORT).show()
                    if(!msj.error){
                        obtenerFotoColaborador(idColaborador)
                    }
                }else{
                    Toast.makeText(this@ActualizarPerfilActivity, "Error: "+e.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun cerrarSesion() {
        // Limpia las preferencias de sesión
        val sharedPreferences = getSharedPreferences("Sesion", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Borra todos los datos almacenados
        editor.apply()

        // Redirige al LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        // Muestra un mensaje de cierre de sesión
        Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()

        // Finaliza la actividad actual
        finish()
    }



}