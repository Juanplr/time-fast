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
import java.util.List;
import pojo.Mensaje;
import pojo.Paquete;
import pojo.RespuestaHTTP;

/**
 *
 * @author juanl
 */
public class PaqueteDAO {

    public static List<Paquete> obtenerPaquetes(){
        List<Paquete> paquetes = null;
        String url = Constantes.URL + "paquetes/obtener-paquetes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaPaquete = new TypeToken<List<Paquete>>() {}.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoListaPaquete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return paquetes;
    }

    public static List<Paquete> obtenerPaquetesPorEnvio(int idEnvio){
        List<Paquete> paquetes = null;
        String url = Constantes.URL + "paquetes/obtener-paquetes-envio/" + idEnvio;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaPaquete = new TypeToken<List<Paquete>>() {}.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoListaPaquete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return paquetes;
    }
    
    public static List<Paquete> obtenerPaquetesPorNoGuia(String noGuia){
        List<Paquete> paquetes = null;
        String url = Constantes.URL + "paquetes/obtener-paquetes-noguia/" + noGuia;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaPaquete = new TypeToken<List<Paquete>>() {}.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoListaPaquete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return paquetes;
    }

    public static Mensaje registrarPaquete(Paquete paquete){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "paquetes/agregar-paquete";
        Gson gson = new Gson();

        try {
            String parametros = gson.toJson(paquete);
            RespuestaHTTP respuesta = ConexionWS.peticionPOSTJSON(url, parametros);

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

    public static Mensaje editarPaquete(Paquete paquete){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "paquetes/editar-paquete";
        Gson gson = new Gson();

        try {
            String parametros = gson.toJson(paquete);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJSON(url, parametros);

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

    public static Mensaje eliminarPaquete(int idPaquete){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "paquetes/eliminar-paquete/" + idPaquete;
        Gson gson = new Gson();

        try {
            RespuestaHTTP respuesta = ConexionWS.peticionDELETE(url, "");

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
}


