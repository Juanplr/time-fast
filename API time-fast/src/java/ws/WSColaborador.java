package ws;

import com.google.gson.Gson;
import dominio.ImpColaborador;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import pojo.Colaborador;
import pojo.Mensaje;

/**
 * Web Service para la gestión de colaboradores
 * Autor: Daniel García Jácome
 */
@Path("colaboradores")
public class WSColaborador {

    @Context
    private UriInfo context;

    public WSColaborador() {
    }

    @Path("obtener-colaboradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerColaboradores() {
        List<Colaborador> listaColaboradores = ImpColaborador.obtenerColaboradores();
        Gson gson = new Gson();
        if (listaColaboradores != null && !listaColaboradores.isEmpty()) {
            // Convertir la lista a JSON antes de enviarla en la respuesta
            String jsonResponse = gson.toJson(listaColaboradores);
            return Response.ok(jsonResponse).build();
        } else {
            throw new NotFoundException("No se encontraron colaboradores.");
        }
    }

    @Path("obtener-colaboradores-nombre/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerColaboradoresPorNombre(@PathParam("nombre") String nombre) {
        try {
            List<Colaborador> colaboradores = ImpColaborador.obtenerColaboradoresPorNombre(nombre);
            Gson gson = new Gson();
            if (colaboradores != null && !colaboradores.isEmpty()) {
                // Convertir la lista de colaboradores a JSON
                String jsonResponse = gson.toJson(colaboradores);
                return Response.ok(jsonResponse).build();
            } else {
                throw new NotFoundException("No se encontraron colaboradores con el nombre: " + nombre);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Path("obtener-colaboradores-rol/{idRol}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerColaboradoresPorRol(@PathParam("idRol") int idRol) {
        try {
            List<Colaborador> colaboradores = ImpColaborador.obtenerColaboradoresPorRol(idRol);
            Gson gson = new Gson();
            if (colaboradores != null && !colaboradores.isEmpty()) {
                // Convertir la lista de colaboradores a JSON
                String jsonResponse = gson.toJson(colaboradores);
                return Response.ok(jsonResponse).build();
            } else {
                throw new NotFoundException("No se encontraron colaboradores con el rol ID: " + idRol);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Error al procesar la solicitud: " + e.getMessage());
        }
    }

@Path("obtener-colaboradores-noPersonal/{noPersonal}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response obtenerColaboradorPorNoPersonal(@PathParam("noPersonal") String noPersonal) {
    if (noPersonal != null && !noPersonal.isEmpty()) {
        try {
            System.out.println("Recibido noPersonal: " + noPersonal); 
            List<Colaborador> colaborador = ImpColaborador.obtenerColaboradorPorNoPersonal(noPersonal);
            if (colaborador != null) {
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(colaborador);
                System.out.println("Colaborador encontrado: " + jsonResponse); 
                return Response.ok(jsonResponse).build();
            } else {
                throw new NotFoundException("No se encontró el colaborador con número personal: " + noPersonal);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
            e.printStackTrace();
            throw new BadRequestException("Error al procesar la solicitud: " + e.getMessage());
        }
    } else {
        throw new BadRequestException("El número personal proporcionado es inválido.");
    }
}



    @Path("agregar-colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarColaborador(String jsonColaborador) {
        try {
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
            Mensaje mensaje = ImpColaborador.registrarColaborador(colaborador);
            if (!mensaje.isError()) {
                String jsonResponse = gson.toJson(mensaje);
                return Response.status(Response.Status.CREATED).entity(jsonResponse).build();
            } else {
                String jsonResponse = gson.toJson(mensaje);
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonResponse).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Path("editar-colaborador")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarColaborador(String jsonColaborador) {
        try {
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
            Mensaje mensaje = ImpColaborador.editarColaborador(colaborador);
            if (!mensaje.isError()) {
                String jsonResponse = gson.toJson(mensaje);
                return Response.ok(jsonResponse).build();
            } else {
                String jsonResponse = gson.toJson(mensaje);
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonResponse).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Path("eliminar-colaborador/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarColaborador(@PathParam("idColaborador") int idColaborador) {
        try {
            Mensaje mensaje = ImpColaborador.eliminarColaborador(idColaborador);
            Gson gson = new Gson();
            if (!mensaje.isError()) {
                String jsonResponse = gson.toJson(mensaje);
                return Response.ok(jsonResponse).build();
            } else {
                String jsonResponse = gson.toJson(mensaje);
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonResponse).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Error al intentar eliminar el colaborador: " + e.getMessage());
        }
    }
}
