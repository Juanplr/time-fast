/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.ConductoresAsignados;
import pojo.Mensaje;

/**
 *
 * @author Daniel García Jácome
 */
public class ImpConductoresAsignados {
    
    public static List<ConductoresAsignados> obtenerTodos() {
        List<ConductoresAsignados> lista = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                lista = conexionBD.selectList("conductoresAsignados.getAll");
            } catch (Exception e) {
                System.err.println("Error al recuperar conductores asignados: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("Por el momento no se puede consultar la información");
        }

        return lista;
    }

    public static Mensaje registrar(ConductoresAsignados conductor) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("conductoresAsignados.insert", conductor);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Conductor asignado registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el conductor asignado");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar conductor asignado: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }
        
        return mensaje;
    }

    public static Mensaje editar(ConductoresAsignados conductor) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("conductoresAsignados.update", conductor);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Conductor asignado editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el conductor asignado");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar conductor asignado: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }

        return mensaje;
    }

    public static Mensaje eliminar(int idConductoresAsignados) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("conductoresAsignados.delete", idConductoresAsignados);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Conductor asignado eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el conductor asignado");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar conductor asignado: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }

        return mensaje;
    }
}