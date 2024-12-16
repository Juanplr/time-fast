package clienteescritorio.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;
import pojo.Cliente;
import pojo.Mensaje;
import pojo.RespuestaHTTP;

public class ClienteDAO {
    public static List<Cliente> obtenerClientes(){
        List<Cliente> clientes = null;
        String url = Constantes.URL+"cliente/obtener-clientes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Cliente>>(){}.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }
    public static List<Cliente> obtenerClientePorNombre(String nombre){
        List<Cliente> clientes = null;
        String url = Constantes.URL+"cliente/obtener-clientes-nombre/"+nombre;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Cliente>>(){}.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }
    public static List<Cliente> obtenerClientePorNumeroDeTelefono(String telefono){
        List<Cliente> clientes = null;
        String url = Constantes.URL+"cliente/obtener-cliente-numero-telefono/"+telefono;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Cliente>>(){}.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }
     public static List<Cliente> obtenerClientePorCorreo(String correo){
        List<Cliente> clientes = null;
        String url = Constantes.URL+"cliente/obtener-cliente-correo/"+correo;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Cliente>>(){}.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }
    public static Mensaje agregarCliente(Cliente cliente){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL+"cliente/agregar-cliente";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(cliente);
            RespuestaHTTP respuesta = ConexionWS.peticionPOSTJSON(url, parametros);
            if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        }
        return mensaje;
    }
    public static Mensaje editarCliente(Cliente cliente){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL+"cliente/editar-cliente";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(cliente);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJSON(url, parametros);
            if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        } 
        return mensaje;
    }
    public static Mensaje eliminarCliente(Integer idCliente){
        Mensaje mensaje = new Mensaje();
        String url =Constantes.URL+"cliente/eliminar-cliente";
        Gson gson = new Gson();
        try {
            String parametros = String.format("idCliente=%s", idCliente);
            RespuestaHTTP respuesta = ConexionWS.peticionDELETE(url, parametros);
            if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        }
        return mensaje;
    }
}
