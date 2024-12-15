package clienteescritorio.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;
import pojo.Mensaje;
import pojo.RespuestaHTTP;
import pojo.TipoUnidad;
import pojo.Unidad;

/**
 *
 * @author juanl
 */
public class UnidadDAO {
    
    public static List<Unidad> obtenerUnidades(){
        List<Unidad> unidades = null;
        String url = Constantes.URL+"unidad/obtener-unidades";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Unidad>>(){}.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static List<Unidad> obtenerUnidadesDisponibles(){
        List<Unidad> unidades = null;
        String url = Constantes.URL+"unidad/obtener-unidades-disponibles";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Unidad>>(){}.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    public static List<Unidad> obtenerUnidadesPorVin(String vin){
        List<Unidad> unidades = null;
        String url = Constantes.URL+"unidad/obtener-unidad-por-vin/"+vin;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Unidad>>(){}.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static List<Unidad> obtenerUnidadesPorMarca(String marca){
        List<Unidad> unidades = null;
        String url = Constantes.URL+"unidad/obtener-unidades-por-marca/"+marca;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Unidad>>(){}.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static List<Unidad> obtenerUnidadesPorNii(String nii){
        List<Unidad> unidades = null;
        String url = Constantes.URL+"unidad/obtener-unidades-por-nii/"+nii;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Unidad>>(){}.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static Mensaje agregarUnidad(Unidad unidad){
        Mensaje mensaje = null;
        String url = Constantes.URL+"unidad/agregar-unidad";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(unidad);
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
    
    public static Mensaje EditarUnidad(Unidad unidad){
        Mensaje mensaje = null;
        String url = Constantes.URL+"unidad/editar-unidad";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(unidad);
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
    
    public static Mensaje eliminarUnidad(Integer idUnidad){
        Mensaje mensaje = null;
        String url = Constantes.URL+"unidad/eliminar-unidad/"+idUnidad;
        Gson gson = new Gson();
        try {
            RespuestaHTTP respuesta = ConexionWS.peticionDELETE(url, "");
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
    public static List<TipoUnidad> obtenerTiposUnidades(){
        List<TipoUnidad> unidades = null;
        String url = Constantes.URL+"unidad/obtener-tipos-unidades";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<TipoUnidad>>(){}.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
}
