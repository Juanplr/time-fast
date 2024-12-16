/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;


import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Paquete;
/**
 *
 * @author Daniel García Jácome
 */
public class ImpPaquete {
    
    public static List<Paquete> obtenerPaquetesPorEnvio(int idEnvio) {
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Paquete> paquetes = null;
        
        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.getPaquetesPorEnvio", idEnvio);
            } catch (Exception e) {
                System.err.println("Error al obtener paquetes por envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("No se pudo establecer conexión con la base de datos");
        }
        
        return paquetes;
    }
    
    public static List<Paquete> obtenerPaquetes() {
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Paquete> paquetes = null;
        
        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.getPaquetes");
            } catch (Exception e) {
                System.err.println("Error al obtener paquetes por envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("No se pudo establecer conexión con la base de datos");
        }
        
        return paquetes;
    }
    
    
    public static Mensaje registrarPaquete(Paquete paquete) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("paquete.registrar", paquete);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Paquete registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el paquete");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar paquete: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }
        
        return mensaje;
    }

    public static Mensaje editarPaquete(Paquete paquete) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("paquete.editar", paquete);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Paquete editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el paquete");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar paquete: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }

        return mensaje;
    }

    public static Mensaje eliminarPaquete(int idPaquete) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("paquete.eliminar", idPaquete);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Paquete eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el paquete");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar paquete: " + e.getMessage());
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