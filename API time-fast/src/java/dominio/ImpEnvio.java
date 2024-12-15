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
import pojo.Envio;
import pojo.Mensaje;
/**
 *
 * @author Daniel García Jácome
 */
public class ImpEnvio {
    
public static List<Envio> obtenerEnvios() {
    List<Envio> listaEnvios = new ArrayList<>();
    SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

    if (conexionBD != null) {
        try {
            listaEnvios = conexionBD.selectList("envio.getObtenerEnvios");
        } catch (Exception e) {
            System.err.println("Error al recuperar los envíos: " + e.getMessage());
        } finally {
            conexionBD.close();
        }
    } else {
        System.err.println("Por el momento no se puede consultar la información");
    }

    return listaEnvios;
}

    public static List<Envio> obtenerEnviosPorNoGuia(String noGuia) {
        List<Envio> listaEnvios = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaEnvios = conexionBD.selectList("envio.getObtenerEnviosPorNoGuia", noGuia);
            } catch (Exception e) {
                System.err.println("Error al recuperar los envíos: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("Por el momento no se puede consultar la información");
        }

        return listaEnvios;
    }

    
 
    public static Mensaje registrarEnvio(Envio envio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("envio.registrar", envio);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Envío registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }
        
        return mensaje;
    }

    public static Mensaje editarEnvio(Envio envio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("envio.editar", envio);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Envío editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }

        return mensaje;
    }

    public static Mensaje eliminarEnvio(int idEnvio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("envio.eliminar", idEnvio);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Envío eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar envío: " + e.getMessage());
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