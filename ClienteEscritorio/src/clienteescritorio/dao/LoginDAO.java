package clienteescritorio.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import pojo.Colaborador;
import pojo.RespuestaHTTP;

/**
 *
 * @author juanl
 */
public class LoginDAO {
    public static Colaborador iniciarSesion(String noPersonal, String password){
        Colaborador respuestaLogin = null;
        String url = Constantes.URL+"login/iniciar-sesion";
        String parametros = String.format("noPersonal=%s&contrasena=%s", noPersonal,password);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOST(url, parametros);
        if(respuestaWS.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            System.out.println("JSON: "  + respuestaWS.getContenido());
            respuestaLogin = gson.fromJson(respuestaWS.getContenido(), Colaborador.class);
        }else{
        }
        return respuestaLogin;
    }
}
