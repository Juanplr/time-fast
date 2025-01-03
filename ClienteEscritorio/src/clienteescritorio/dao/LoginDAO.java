package clienteescritorio.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import pojo.Colaborador;
import pojo.LoginRespuesta;
import pojo.RespuestaHTTP;

/**
 *
 * @author juanl
 */
public class LoginDAO {
    public static LoginRespuesta iniciarSesion(String noPersonal, String password){
        LoginRespuesta respuesta = new LoginRespuesta();
        Colaborador colaborador = null;
        String url = Constantes.URL+"login/iniciar-sesion";
        String parametros = String.format("noPersonal=%s&contrasena=%s", noPersonal,password);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOST(url, parametros);
        if(respuestaWS.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            colaborador = gson.fromJson(respuestaWS.getContenido(), Colaborador.class);
            respuesta.setError(false);
            respuesta.setMensaje("Colaborador Encontrado");
            respuesta.setColaborador(colaborador);
        }else if(respuestaWS.getCodigoRespuesta()== HttpURLConnection.HTTP_NOT_FOUND){
            respuesta.setError(true);
            respuesta.setMensaje("Error en el servidor Favor de intentarlo más tarde");
        }else{
           respuesta.setError(true);
           respuesta.setMensaje("Numero Personal y/o Contraseña incorrecta");   
        }
        return respuesta;
    }
}
