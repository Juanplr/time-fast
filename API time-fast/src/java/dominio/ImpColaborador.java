/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

/**
 *
 * @author Daniel García Jácome
 */
public class ImpColaborador {
    public static List<Colaborador> obtenerColaboradores() {

        List<Colaborador> listaColaboradores = new ArrayList<>();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaColaboradores = conexionBD.selectList("colaborador.getColaboradores");
            } catch (Exception e) {
                
                System.err.println("Error al recuperar los colaboradores: " + e.getMessage());
            } finally {
                
                conexionBD.close();
            }
        } else {
            System.err.println("Por el momento no se puede consultar la información");
        }

        return listaColaboradores;
    }
    
    public static List<Colaborador> obtenerColaboradoresPorNombre(String nombre) {
    List<Colaborador> listaColaboradores = new ArrayList<>();
    SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

    if (conexionBD != null) {
        try {
            listaColaboradores = conexionBD.selectList("colaborador.getColaboradoresPorNombre", nombre);
        } catch (Exception e) {
            System.err.println("Error al recuperar los colaboradores por nombre: " + e.getMessage());
        } finally {
            conexionBD.close();
        }
    } else {
        System.err.println("Por el momento no se puede consultar la información");
    }

    return listaColaboradores;
}    
    
    
    
    public static List<Colaborador> obtenerColaboradoresPorRol(int idRol) {
    List<Colaborador> listaColaboradores = new ArrayList<>();
    SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

    if (conexionBD != null) {
        try {
            listaColaboradores = conexionBD.selectList("colaborador.getColaboradoresPorRol", idRol);
        } catch (Exception e) {
            System.err.println("Error al recuperar los colaboradores por rol: " + e.getMessage());
        } finally {
            conexionBD.close();
        }
    } else {
        System.err.println("Por el momento no se puede consultar la información");
    }

    return listaColaboradores;
    }
   
    
public static Colaborador obtenerColaboradorPorNoPersonal(String noPersonal) {
    Colaborador colaborador = null;
    SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

    if (conexionBD != null) {
        try {
            System.out.println("Consultando colaborador con noPersonal: " + noPersonal);
            colaborador = conexionBD.selectOne("colaborador.getColaboradorPorNoPersonal", noPersonal);
            System.out.println("Resultado obtenido: " + colaborador);
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexionBD.close();
        }
    } else {
        System.err.println("No se pudo establecer conexión con la base de datos.");
    }

    return colaborador;
}


    
    
    public static Mensaje registrarColaborador(Colaborador colaborador) {

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("colaborador.registrar", colaborador);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Colaborador(a) registrado con exito");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar al colaborador(as)");
                }
            } catch (Exception e) {
                mensaje.setMensaje(e.getMessage());
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no esta disponible");
        }
        return mensaje;
    }

    
    
     public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("colaborador.editar", colaborador);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Colaborador(a) editado con éxito");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el colaborador(a)");
                }
            } catch (Exception e) {
                e.printStackTrace();  
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar el colaborador: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no está disponible");
        }

        return mensaje;
    }

    public static Mensaje eliminarColaborador(int idColaborador) {
    Mensaje mensaje = new Mensaje();
    SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

    if (conexionBD != null) {
        try {
            int resultado = conexionBD.delete("colaborador.eliminar", idColaborador);
            conexionBD.commit();
            if (resultado > 0) {
                mensaje.setError(false);
                mensaje.setMensaje("Colaborador eliminado con éxito");
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No se pudo eliminar el colaborador");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            mensaje.setError(true);
            mensaje.setMensaje("Error al eliminar el colaborador: " + e.getMessage());
        } finally {
            if (conexionBD != null) {
                conexionBD.close();
            }
        }
    } else {
        mensaje.setError(true);
        mensaje.setMensaje("Por el momento el servicio no está disponible");
    }

    return mensaje;
}
}