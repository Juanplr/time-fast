/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.ImpPaquete;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import pojo.Paquete;
import pojo.Mensaje;
/**
 *
 * @author Daniel García Jácome
 */
@Path("paquetes")
public class WSPaquete {

    @Path("obtener-paquetes-envio/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetesPorEnvio(@PathParam("idEnvio") int idEnvio) {
        if (idEnvio <= 0) {
            throw new BadRequestException("El idEnvio debe ser mayor que 0.");
        }
        List<Paquete> paquetes = ImpPaquete.obtenerPaquetesPorEnvio(idEnvio);
        if (paquetes != null && !paquetes.isEmpty()) {
            return paquetes;
        } else {
            throw new NotFoundException("No se encontraron paquetes para el envío con idEnvio: " + idEnvio);
        }
    }
    
    @Path("obtener-paquetes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetes() {
       return ImpPaquete.obtenerPaquetes();
    }

    @Path("agregar-paquete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new BadRequestException("Los datos del paquete son inválidos o están vacíos.");
        }
        return ImpPaquete.registrarPaquete(paquete);
    }

    @Path("editar-paquete")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new BadRequestException("Los datos del paquete son inválidos o están vacíos.");
        }
        return ImpPaquete.editarPaquete(paquete);
    }

    @Path("eliminar-paquete/{idPaquete}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("idPaquete") int idPaquete) {
        if (idPaquete <= 0) {
            throw new BadRequestException("El idPaquete debe ser mayor que 0.");
        }
        return ImpPaquete.eliminarPaquete(idPaquete);
    }
}
