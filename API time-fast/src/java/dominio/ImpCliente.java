package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Mensaje;


public class ImpCliente {
    
     public static List<Cliente> getClientes(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Cliente> respuesta = null;
        if(conexionBD !=null){
            try {
                respuesta = conexionBD.selectList("cliente.obtenerClientes");
            } catch (Exception e) {
                
            }
        }else{
            
        }   
        
        return respuesta;
    }

    public static List<Cliente> obtenerClientesPorNombre(String nombre) {
        List<Cliente> clientes = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("nombre", nombre);
                clientes = conexionBD.selectList("cliente.obtenerClientesPorNombre", parametros);
                if(clientes != null){
                   
                }else{
                 
                }
            } catch (Exception e) {
              
            }
        }else{
            
        }
        
        return clientes;
    }
    
    public static Cliente obtenerClientePorCorreo(String correo) {
        Cliente cliente = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("correo", correo);
                cliente = conexionBD.selectOne("cliente.obtenerClientesPorCorreo", parametros);
                if(cliente != null){
                   
                }else{
                 
                }
            } catch (Exception e) {
              
            }
        }else{
            
        }
        
        return cliente;
    }

    public static Cliente obtenerClientePorNumeroTelefonico(String telefono) {
        Cliente cliente = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("telefono", telefono);
                cliente = conexionBD.selectOne("cliente.obtenerClientesPorTelefono", parametros);
                if(cliente != null){
                   
                }else{
                 
                }
            } catch (Exception e) {
              
            }
        }else{
            
        }
        
        return cliente;
    }
    
    public static Mensaje agregarCliente(Cliente cliente) {
        Mensaje msj = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();

        if (conexion != null) {
            try {
                int filasAfectadas = conexion.insert("cliente.agregarCliente", cliente);
                conexion.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Cliente agregado correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo agregar el cliente");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexion.close();
            }
        } else {
            msj.setError(true);
            msj.setMensaje("No se pudo establecer conexión con la base de datos.");
        }

        return msj;
    }

    public static Mensaje editarCliente(Cliente cliente) {
       Mensaje msj = new Mensaje();
       SqlSession conexion = MyBatisUtil.obtenerConexion();

       if (conexion != null) {
            try {
                int filasAfectadas = conexion.update("cliente.editarCliente", cliente);
                conexion.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Cliente Editado correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo editar el cliente");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexion.close();
            }
        } else {
            msj.setError(true);
            msj.setMensaje("No se pudo establecer conexión con la base de datos.");
        }

        return msj;
    }

    public static Mensaje eliminarCliente(Integer idCliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                HashMap<String, Integer> parametros = new LinkedHashMap<>();
                parametros.put("idCliente", idCliente);
                int filasAfectadas = conexionBD.delete("cliente.eliminarCliente", parametros);
                conexionBD.commit(); // Confirma los cambios en la base de datos
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Colaborador eliminado exitosamente.");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el colaborador para eliminar.");
                }
            } catch (Exception e) {
                conexionBD.rollback(); // Reversión en caso de error
                respuesta.setError(true);
                respuesta.setMensaje("Error al eliminar el colaborador: " + e.getMessage());
            } finally {
                conexionBD.close(); // Cierra la conexión
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No se puede conectar a la base de datos en este momento.");
        }
        return respuesta;
    }

}
