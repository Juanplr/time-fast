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
import pojo.HistorialDeBaja;
import pojo.Mensaje;
import pojo.RespuestaHTTP;

/**
 *
 * @author juanl
 */
public class HistorialDeBajaDAO {

    public static List<HistorialDeBaja> obtenerHistorialesDeBaja() {
        List<HistorialDeBaja> historiales = null;
        String url = Constantes.URL + "historial-baja/obtener-todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoLista = new TypeToken<List<HistorialDeBaja>>() {}.getType();
                historiales = gson.fromJson(respuesta.getContenido(), tipoLista);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return historiales;
    }

    public static HistorialDeBaja obtenerHistorialPorIdUnidad(int idUnidad) {
        HistorialDeBaja historial = null;
        String url = Constantes.URL + "historial-baja/obtener-por-idunidad/" + idUnidad;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                historial = gson.fromJson(respuesta.getContenido(), HistorialDeBaja.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return historial;
    }

    public static Mensaje agregarHistorialDeBaja(HistorialDeBaja historial) {
        Mensaje mensaje = null;
        String url = Constantes.URL + "historial-baja/agregar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(historial);
            RespuestaHTTP respuesta = ConexionWS.peticionPOSTJSON(url, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje = new Mensaje();
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje = new Mensaje();
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje editarHistorialDeBaja(HistorialDeBaja historial) {
        Mensaje mensaje = null;
        String url = Constantes.URL + "historial-baja/editar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(historial);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJSON(url, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje = new Mensaje();
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje = new Mensaje();
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje eliminarHistorialDeBaja(int idHistorialDeBaja) {
        Mensaje mensaje = null;
        String url = Constantes.URL + "historial-baja/eliminar/" + idHistorialDeBaja;
        Gson gson = new Gson();
        try {
            RespuestaHTTP respuesta = ConexionWS.peticionDELETE(url, "");
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje = new Mensaje();
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje = new Mensaje();
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        }

        return mensaje;
    }
}
