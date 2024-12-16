package clienteescritorio.dao;

/**
 *
 * @author juanl
 */

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;
import pojo.ConductoresAsignados;
import pojo.Mensaje;
import pojo.RespuestaHTTP;

public class ConductoresAsignadosDAO {


    public static List<ConductoresAsignados> obtenerTodos() {
        List<ConductoresAsignados> lista = null;
        String url = Constantes.URL + "conductores-asignados/obtener-todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoLista = new TypeToken<List<ConductoresAsignados>>() {}.getType();
                lista = gson.fromJson(respuesta.getContenido(), tipoLista);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }


    public static Mensaje registrar(ConductoresAsignados conductor) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL + "conductores-asignados/agregar";
        Gson gson = new Gson();

        try {
            String parametros = gson.toJson(conductor);
            RespuestaHTTP respuesta = ConexionWS.peticionPOSTJSON(url, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        }

        return mensaje;
    }

 
    public static Mensaje editar(ConductoresAsignados conductor) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL + "conductores-asignados/editar";
        Gson gson = new Gson();

        try {
            String parametros = gson.toJson(conductor);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJSON(url, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje.setError(true);
                mensaje.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setMensaje(e.getMessage());
        }

        return mensaje;
    }

  
    public static Mensaje eliminar(int idConductoresAsignados) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL + "conductores-asignados/eliminar/" + idConductoresAsignados;
        Gson gson = new Gson();

        try {
            RespuestaHTTP respuesta = ConexionWS.peticionDELETE(url, "");
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
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
