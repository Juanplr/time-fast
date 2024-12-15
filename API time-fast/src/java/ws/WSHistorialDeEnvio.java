/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;
import dominio.ImpHistorialEnvio;
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
import pojo.HistorialDeEnvio;
import pojo.Mensaje;
/**
 *
 * @author Daniel García Jácome
 */
@Path("historial-envio")
public class WSHistorialDeEnvio {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialDeEnvio> obtenerHistorialEnvio() {
        List<HistorialDeEnvio> lista = new ArrayList<>();
        lista = ImpHistorialEnvio.obtenerTodos();
        
        return lista;
    }
    
    
     @Path("obtener-historial/{noGuia}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public List<HistorialDeEnvio> obtenerHistorialPorNoGuia(@PathParam("noGuia") String noGuia) {
    System.out.println("Número de guía recibido: " + noGuia);

    if (noGuia != null && !noGuia.isEmpty()) {
        List<HistorialDeEnvio> lista = ImpHistorialEnvio.obtenerHistorialPorNoGuia(noGuia);
       

        if (lista == null || lista.isEmpty()) {
            throw new NotFoundException("No se encontraron registros para el número de guía: " + noGuia);
        }
        return lista;
    }
    throw new BadRequestException("El número de guía proporcionado es inválido.");
}
    
    @Path("agregar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarHistorialEnvio(HistorialDeEnvio historial) {
        if (historial == null) {
            throw new BadRequestException("Los datos del historial de envío son inválidos o están vacíos.");
        }
        return ImpHistorialEnvio.registrar(historial);
    }

    @Path("editar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarHistorialEnvio(HistorialDeEnvio historial) {
        if (historial == null || historial.getIdHistorialDeEnvio() <= 0) {
            throw new BadRequestException("Los datos del historial de envío son inválidos o el ID es incorrecto.");
        }
        return ImpHistorialEnvio.editar(historial);
    }

    @Path("eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarHistorialEnvio(@PathParam("id") int idHistorialDeEnvio) {
        if (idHistorialDeEnvio <= 0) {
            throw new BadRequestException("El idHistorialDeEnvio debe ser mayor que 0.");
        }
        return ImpHistorialEnvio.eliminar(idHistorialDeEnvio);
    }
}