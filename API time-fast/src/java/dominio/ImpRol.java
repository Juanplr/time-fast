/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import pojo.Mensaje;
import org.apache.ibatis.session.SqlSession;
import pojo.Rol;

/**
 *
 * @author reyes
 */
public class ImpRol {
    
    public static List<Rol> obtenerRoles(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Rol> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("rol.obtenerRoles");
            }catch(Exception e){
                
            }
        }
        return respuesta;
    }
    
}
