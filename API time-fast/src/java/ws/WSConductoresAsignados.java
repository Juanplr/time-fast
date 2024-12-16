/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;
import dominio.ImpConductoresAsignados;
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
import pojo.ConductoresAsignados;
import pojo.Mensaje;

/**
 *
 * @author Daniel García Jácome
 */
@Path("conductores-asignados")
public class WSConductoresAsignados {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConductoresAsignados> obtenerConductoresAsignados() {
        List<ConductoresAsignados> lista = new ArrayList<>();
        lista = ImpConductoresAsignados.obtenerTodos();
        
        

        return lista;
    }
    
    @Path("agregar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarConductorAsignado(ConductoresAsignados conductor) {
        if (conductor == null) {
            throw new BadRequestException("Los datos del conductor asignado son inválidos o están vacíos.");
        }
        return ImpConductoresAsignados.registrar(conductor);
    }

    @Path("editar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarConductorAsignado(ConductoresAsignados conductor) {
        if (conductor == null || conductor.getIdConductoresAsignados() <= 0) {
            throw new BadRequestException("Los datos del conductor asignado son inválidos o el ID es incorrecto.");
        }
        return ImpConductoresAsignados.editar(conductor);
    }

    @Path("eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarConductorAsignado(@PathParam("id") int idConductoresAsignados) {
        if (idConductoresAsignados <= 0) {
            throw new BadRequestException("El idConductoresAsignados debe ser mayor que 0.");
        }
        return ImpConductoresAsignados.eliminar(idConductoresAsignados);
    }
}
