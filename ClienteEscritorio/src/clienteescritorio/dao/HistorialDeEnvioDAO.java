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
import pojo.HistorialDeEnvio;
import pojo.Mensaje;
import pojo.RespuestaHTTP;

/**
 *
 * @author juanl
 */
public class HistorialDeEnvioDAO {

    public static List<HistorialDeEnvio> obtenerTodos() {
        List<HistorialDeEnvio> historialEnvio = null;
        String url = Constantes.URL + "historial-envio/obtener-todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaHistorial = new TypeToken<List<HistorialDeEnvio>>() {}.getType();
                historialEnvio = gson.fromJson(respuesta.getContenido(), tipoListaHistorial);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return historialEnvio;
    }

    public static List<HistorialDeEnvio> obtenerHistorialPorNoGuia(String noGuia) {
        List<HistorialDeEnvio> historialEnvio = null;
        String url = Constantes.URL + "historial-envio/obtener-historial/" + noGuia;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaHistorial = new TypeToken<List<HistorialDeEnvio>>() {}.getType();
                historialEnvio = gson.fromJson(respuesta.getContenido(), tipoListaHistorial);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return historialEnvio;
    }

    public static Mensaje registrarHistorialEnvio(HistorialDeEnvio historial) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "historial-envio/agregar";
        Gson gson = new Gson();

        try {
            String parametros = gson.toJson(historial);
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

    public static Mensaje editarHistorialEnvio(HistorialDeEnvio historial) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "historial-envio/editar";
        Gson gson = new Gson();

        try {
            String parametros = gson.toJson(historial);
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

    public static Mensaje eliminarHistorialEnvio(int idHistorialDeEnvio) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL + "historial-envio/eliminar/" + idHistorialDeEnvio;
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

