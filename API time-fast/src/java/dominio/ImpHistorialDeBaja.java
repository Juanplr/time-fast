/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;


import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import pojo.HistorialDeBaja;
import pojo.Mensaje;
import mybatis.MyBatisUtil;

/**
 *
 * @author Daniel García Jácome
 */
public class ImpHistorialDeBaja {

    public static List<HistorialDeBaja> obtenerTodos() {
        List<HistorialDeBaja> lista = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                lista = conexionBD.selectList("historialDeBaja.getObtenerHistorialDeBaja");
            } catch (Exception e) {
                System.err.println("Error al recuperar historial de baja: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("Por el momento no se puede consultar la información");
        }

        return lista;
    }
    
    public static List<HistorialDeBaja> obtenerPorIdUnidad(int idUnidad) {
    List<HistorialDeBaja> lista = new ArrayList<>();
    SqlSession conexionBD = MyBatisUtil.obtenerConexion();

    if (conexionBD != null) {
        try {
            lista = conexionBD.selectList("historialDeBaja.getHistorialDeBajaPorIdUnidad", idUnidad);
        } catch (Exception e) {
            System.err.println("Error al recuperar historial de baja por idUnidad: " + e.getMessage());
        } finally {
            conexionBD.close();
        }
    } else {
        System.err.println("No se pudo establecer conexión con la base de datos.");
    }

    return lista;
}


    public static Mensaje registrar(HistorialDeBaja historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("historialDeBaja.insert", historial);
                conexionBD.commit();

                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de baja registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el historial de baja");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar historial de baja: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }

        return mensaje;
    }

    public static Mensaje editar(HistorialDeBaja historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("historialDeBaja.update", historial);
                conexionBD.commit();

                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de baja editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el historial de baja");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar historial de baja: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }

        return mensaje;
    }

    public static Mensaje eliminar(int idHistorialDeBaja) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("historialDeBaja.delete", idHistorialDeBaja);
                conexionBD.commit();

                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de baja eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el historial de baja");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar historial de baja: " + e.getMessage());
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