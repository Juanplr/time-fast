/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;
import dominio.ImpEnvio;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.Mensaje;
/**
 *
 * @author Daniel García Jácome
 */
@Path("envio")
public class WSEnvio {
    
   @Path("obtener-envios")
@GET
@Produces(MediaType.APPLICATION_JSON)
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


    @Path("agregar-envio")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEnvio(Envio envio) {
        if (envio == null) {
            throw new BadRequestException("Los datos del envío son inválidos o están vacíos.");
        }
        return ImpEnvio.registrarEnvio(envio);
    }

    @Path("editar-envio")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEnvio(Envio envio) {
        if (envio == null) {
            throw new BadRequestException("Los datos del envío son inválidos o están vacíos.");
        }
        return ImpEnvio.editarEnvio(envio);
    }

    @Path("eliminar-envio/{idEnvio}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEnvio(@PathParam("idEnvio") int idEnvio) {
        if (idEnvio <= 0) {
            throw new BadRequestException("El idEnvio debe ser mayor que 0.");
        }
        return ImpEnvio.eliminarEnvio(idEnvio);
    }
}