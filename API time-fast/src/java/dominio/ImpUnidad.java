/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EstadoUnidad;
import pojo.Mensaje;
import pojo.Rol;
import pojo.TipoUnidad;
import pojo.Unidad;

/**
 *
 * @author reyes
 */


public class ImpUnidad {
    
    public static List<Unidad> obtenerUnidades(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerUnidades");
            }catch(Exception e){
                
            }
        }
        return respuesta;
    }
    
    public static List<Unidad> obtenerUnidadesDisponibles(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List <Unidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesDisponibles");
            }catch(Exception e){
            
            }
        }
        return respuesta;
    }
    
    // Obtener unidades por VIN
    public static List<Unidad> obtenerUnidadPorVin(String vin) {
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if (conexionDB != null) {
            try {
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesPorVin", vin);
            } catch (Exception e) {

            } 
        }
        return respuesta;
    }

    // Obtener unidades por marca
    public static List<Unidad> obtenerUnidadesPorMarca(String marca) {
        List<Unidad> unidad = null;
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        if (conexionDB != null) {
            try {
                HashMap<String,String> parametros = new LinkedHashMap<>();
                parametros.put("marca", marca);
                unidad = conexionDB.selectList("unidad.obtenerUnidadesPorMarca",parametros);
                if(unidad!=null){
                    
                }
            } catch (Exception e) {
                
            } 
        }
        return unidad;
    }

    
    public static List<Unidad> obtenerUnidadesPorNii(String nii) {
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if (conexionDB != null) {
            try {
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesPorNii", nii);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return respuesta;
    }
    
    // Agregar una unidad
    public static Mensaje agregarUnidad(Unidad unidad) {
       Mensaje mensaje = new Mensaje();
       SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        if(conexionDB!=null){
           try{
               int filasAFectas = conexionDB.insert("unidad.agregarUnidad", unidad);
               conexionDB.commit();
               if(filasAFectas>0){
                   mensaje.setError(false);
                   mensaje.setMensaje("Unidad agregada correctamente");
               } else{
                   mensaje.setError(true);
                   mensaje.setMensaje("No se pudo agregar la unidad");
               }
           }catch(Exception e){
               mensaje.setError(true);
               mensaje.setMensaje(e.getMessage());
            } finally {
                conexionDB.close();
            }
        }else{
           mensaje.setError(true);
           mensaje.setMensaje("No se pudo establecer conexión con la base de datos.");
       }
        return mensaje;
    }
    
    public static Mensaje editarUnidad(Unidad unidad){
        Mensaje mensaje = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        
        if(conexionDB!=null){
            try{
                int filasAfectadas = conexionDB.update("unidad.editarUnidad", unidad);
                conexionDB.commit();
                if(filasAfectadas>0){
                    mensaje.setError(false);
                    mensaje.setMensaje("Unidad editada correctamente");
                }else{
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar la unidad");
                }    
            }catch(Exception e){
                mensaje.setError(true);
                mensaje.setMensaje(e.getMessage());
            }finally{
                conexionDB.close();
            }
        }else{
            mensaje.setError(true);
            mensaje.setMensaje("NO se pudo establecer una conexion, intente mas tarde");
        }
        return mensaje;
    }
    
    
    public static Mensaje eliminarUnidad(Integer idUnidad){
        Mensaje mensaje = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        
        if(conexionDB!=null){
            try{
                int filasAfectadas = conexionDB.delete("unidad.eliminarUnidad", idUnidad);
                conexionDB.commit();
                if(filasAfectadas>0){
                    mensaje.setError(false);
                    mensaje.setMensaje("Unidad eliminada satidfactoriamente");
                }else{
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar la unidad");
                }
            }catch(Exception e){
                mensaje.setError(true);
                mensaje.setMensaje(e.getMessage());
            }finally{
                conexionDB.close();
            }
        }else{
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer una conexion, intente mas tarde");
        }
        
        return mensaje;
    }
    
    public static List<TipoUnidad> obtenerTiposDeUnidad(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<TipoUnidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerTiposDeUnidad");
            }catch(Exception e){
                
            }
        }
        return respuesta;
    }

    public static List<EstadoUnidad> obtenerEstadosDeUnidad() {
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<EstadoUnidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerEstadosDeUnidad");
            }catch(Exception e){
                
            }
        }
        return respuesta;
    }
    
    public static List<Unidad> obtenerUnidadesSinAsignar(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesSinAsignar");
            }catch(Exception e){
                
            }
        }
        return respuesta;
    }
    
    
}
