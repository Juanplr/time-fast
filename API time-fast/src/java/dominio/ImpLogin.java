package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;


public class ImpLogin {
    
    public static Colaborador validarSesionColaborador(String noPersonal, String password){
        Colaborador respuesta = null;
        SqlSession conexionBD= MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try{
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contrasena", password);
                Colaborador colaborador = conexionBD.selectOne("login.iniciarSesion", parametros);
                if(colaborador!=null){
                    respuesta = colaborador;
                }else{
                }
            }catch(Exception e){
            }
        }else{
        }
        return respuesta;
    }
    
    
}
