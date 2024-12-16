/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;


import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import pojo.HistorialDeEnvio;
import pojo.Mensaje;
import mybatis.MyBatisUtil;

/**
 *
 * @author Daniel García Jácome
 */
public class ImpHistorialEnvio {
    
    public static List<HistorialDeEnvio> obtenerTodos() {
        List<HistorialDeEnvio> lista = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                lista = conexionBD.selectList("historialDeEnvio.getAll");
            } catch (Exception e) {
                System.err.println("Error al recuperar historial de envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("Por el momento no se puede consultar la información");
        }

        return lista;
    }
    
    
    public static List<HistorialDeEnvio> obtenerHistorialPorNoGuia(String noGuia) {
    List<HistorialDeEnvio> lista = null;
    SqlSession conexionBD = MyBatisUtil.obtenerConexion();

    if (conexionBD != null) {
        try {
            lista = conexionBD.selectList("historialDeEnvio.getHistorialDeEnvioPorNoGuia", noGuia);
            System.out.println("Registros obtenidos: " + (lista != null ? lista.size() : "null"));
        } catch (Exception e) {
            System.err.println("Error al obtener historial de envío por noGuia: " + e.getMessage());
        } finally {
            conexionBD.close();
        }
    }
    return lista;
}


    public static Mensaje registrar(HistorialDeEnvio historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("historialDeEnvio.insert", historial);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de envío registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el historial de envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar historial de envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }
        
        return mensaje;
    }

    public static Mensaje editar(HistorialDeEnvio historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("historialDeEnvio.update", historial);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de envío editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el historial de envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar historial de envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }

        return mensaje;
    }

    public static Mensaje eliminar(int idHistorialDeEnvio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("historialDeEnvio.delete", idHistorialDeEnvio);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de envío eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el historial de envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar historial de envío: " + e.getMessage());
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