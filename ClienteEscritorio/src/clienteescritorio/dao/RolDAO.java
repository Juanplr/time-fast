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
import pojo.RespuestaHTTP;
import pojo.Rol;

/**
 *
 * @author juanl
 */
public class RolDAO {
    public static List<Rol> optenerRoles(){
        List<Rol> roles = null;
        String url = Constantes.URL+"rol/obtener-roles";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                //enviar una lista.
                Type tipoLista = new TypeToken<List<Rol>>(){}.getType();
                roles = gson.fromJson(respuesta.getContenido(), tipoLista);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roles;
    }
}
