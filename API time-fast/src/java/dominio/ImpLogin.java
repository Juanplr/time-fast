package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

public class ImpLogin {
    
    public static Mensaje validarSesionColaborador(String noPersonal, String password){
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD= MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try{
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("password", password);
                Colaborador colaborador = conexionBD.selectOne("login.iniciarSesion", parametros);
                if(colaborador!=null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del colaborador" + colaborador.getNombre());
                    respuesta.setBody(colaborador);
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Numero Personal o password incorrectos");
                }
                
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje("Problemas con el servidor.");
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Por el momento no se puede consultar la información.");
        }
        return respuesta;
    }
    
    
}
