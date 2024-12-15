/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.ImpHistorialDeBaja;
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
import pojo.HistorialDeBaja;
import pojo.Mensaje;

/**
 *
 * @author Daniel García Jácome
 */
@Path("historial-baja")
public class WSHistorialDeBaja {

    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialDeBaja> obtenerHistorialDeBaja() {
        List<HistorialDeBaja> lista = new ArrayList<>();
        lista = ImpHistorialDeBaja.obtenerTodos();

        return lista;
    }
    
    @Path("obtener-por-idunidad/{idUnidad}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialDeBaja> obtenerHistorialPorIdUnidad(@PathParam("idUnidad") int idUnidad) {
        if (idUnidad > 0) {
            List<HistorialDeBaja> lista = ImpHistorialDeBaja.obtenerPorIdUnidad(idUnidad);
            if (!lista.isEmpty()) {
                return lista;
            } else {
                throw new NotFoundException("No se encontraron registros para la idUnidad proporcionada: " + idUnidad);
            }
        } else {
            throw new BadRequestException("El parámetro idUnidad debe ser mayor que 0.");
        }
    }

    @Path("agregar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarHistorialDeBaja(HistorialDeBaja historial) {
        if (historial == null) {
            throw new BadRequestException("Los datos del historial de baja son inválidos o están vacíos.");
        }
        return ImpHistorialDeBaja.registrar(historial);
    }

@Path("editar")  
@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Mensaje editarHistorialDeBaja(HistorialDeBaja historial) {
    if (historial == null || historial.getIdHistorialDeBaja() <= 0) {
        throw new BadRequestException("Los datos del historial de baja son inválidos o el ID es incorrecto.");
    }
    
    // Asegurarte de que todas las dependencias están inicializadas
    Mensaje mensaje = ImpHistorialDeBaja.editar(historial);
    
    if (mensaje == null) {
        throw new RuntimeException("Error inesperado al editar el historial de baja.");
    }
    
    return mensaje;
}

    @Path("eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarHistorialDeBaja(@PathParam("id") int idHistorialDeBaja) {
        if (idHistorialDeBaja <= 0) {
            throw new BadRequestException("El idHistorialDeBaja debe ser mayor que 0.");
        }
        return ImpHistorialDeBaja.eliminar(idHistorialDeBaja);
    }
}