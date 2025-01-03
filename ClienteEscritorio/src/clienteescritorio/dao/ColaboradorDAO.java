/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.List;
import pojo.Colaborador;
import pojo.Mensaje;
import pojo.RespuestaHTTP;

/**
 *
 * @author juanl
 */
public class ColaboradorDAO {
    public static List<Colaborador> obtenerColaboradores(){
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL+"colaborador/obtener-colaboradores";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                //enviar una lista.
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static List<Colaborador> obtenerColaboradoresNombre(String nombre){
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL+"colaborador/obtener-colaboradores-nombre/"+nombre;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                //enviar una lista.
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static List<Colaborador> obtenerColaboradoresNoPersonal(String noPersonal){
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL+"colaborador/obtener-colaboradores-noPersonal/"+ noPersonal;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                //enviar una lista.
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static List<Colaborador> obtenerColaboradoresRol(Integer rol){
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL+"colaborador/obtener-colaboradores-rol/"+rol;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                //enviar una lista.
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static Mensaje registrarColaborador(Colaborador colaborador){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "colaborador/agregar-colaborador";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(colaborador);
            RespuestaHTTP respuesta = ConexionWS.peticionPOSTJSON(url, parametros);
            if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }
        
        return msj;
    }
    public static Mensaje editarColaborador(Colaborador colaborador){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "colaborador/editar-colaborador";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(colaborador);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJSON(url, parametros);
            if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }
        
        return msj;
    }
    public static Mensaje eliminarColaborador(Integer idColaborador){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "colaborador/eliminar-colaborador/"+idColaborador;
        Gson gson = new Gson();
        try {
            RespuestaHTTP respuesta = ConexionWS.peticionDELETE(url,"");
            if(respuesta.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }
        
        return msj;
    }
    public static Mensaje subirFoto(Integer idColaborador, byte[] foto) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "colaborador/subir-foto/" + idColaborador;
        Gson gson = new Gson();
        try {
            // Realizar la petición PUT directamente con los bytes
            RespuestaHTTP respuesta = ConexionWS.peticionPUTBINARIO(url, foto);

            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }

        return msj;
    }
    public static byte[] obtenerFoto(Integer idColaborador) {
        byte[] foto = null;
        Colaborador colaborador = null;
        String url = Constantes.URL + "colaborador/obtener-foto/" + idColaborador;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        Gson gson = new Gson();
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            try {
                colaborador = gson.fromJson(respuesta.getContenido(), Colaborador.class);
                String fotografia = colaborador.getFotografia();
                if (fotografia != null) {
                    fotografia = fotografia.replace("\n", "").replace("\r", "").trim();
                    foto = Base64.getDecoder().decode(fotografia);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foto;
    }
    
    public static List<Colaborador> obtenerConductores(){
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL+"colaborador/obtener-conductores";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                //enviar una lista.
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    public static List<Colaborador> obtenerConductoresSinAsignar(){
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL+"colaborador/obtener-conductores-sin-asignar";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                //enviar una lista.
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
}
