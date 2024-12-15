package ws;

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
import javax.ws.rs.core.MediaType;
import pojo.Colaborador;
import pojo.Mensaje;

/**
 * Web Service para la gestión de colaboradores
 * Autor: Daniel García Jácome
 */
@Path("colaborador")
public class WSColaborador {

    @Path("obtener-colaboradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores() {
        List<Colaborador> listaColaboradores = ImpColaborador.obtenerColaboradores();
        if (listaColaboradores != null && !listaColaboradores.isEmpty()) {
            return listaColaboradores;
        } else {
            throw new NotFoundException("No se encontraron colaboradores.");
        }
    }

    @Path("obtener-colaboradores-nombre/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradoresPorNombre(@PathParam("nombre") String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BadRequestException("El nombre proporcionado es inválido.");
        }
        try {
            List<Colaborador> colaboradores = ImpColaborador.obtenerColaboradoresPorNombre(nombre);
            if (colaboradores != null && !colaboradores.isEmpty()) {
                return colaboradores;
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
    public List<Colaborador> obtenerColaboradoresPorRol(@PathParam("idRol") int idRol) {
        if (idRol <= 0) {
            throw new BadRequestException("El idRol debe ser mayor que 0.");
        }
        try {
            List<Colaborador> colaboradores = ImpColaborador.obtenerColaboradoresPorRol(idRol);
            if (colaboradores != null && !colaboradores.isEmpty()) {
                return colaboradores;
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
    public List<Colaborador> obtenerColaboradorPorNoPersonal(@PathParam("noPersonal") String noPersonal) {
        if (noPersonal == null || noPersonal.trim().isEmpty()) {
            throw new BadRequestException("El número personal proporcionado es inválido.");
        }
        try {
            List<Colaborador> colaborador = ImpColaborador.obtenerColaboradorPorNoPersonal(noPersonal);
            if (colaborador != null) {
                return colaborador;
            } else {
                throw new NotFoundException("No se encontró el colaborador con número personal: " + noPersonal);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Path("agregar-colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarColaborador(Colaborador colaborador) {
        if (colaborador == null) {
            throw new BadRequestException();
        }
        try {
            return ImpColaborador.registrarColaborador(colaborador);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Path("editar-colaborador")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(Colaborador colaborador) {
        if (colaborador == null) {
            throw new BadRequestException();
        }
        try {
            Mensaje mensaje = ImpColaborador.editarColaborador(colaborador);
            return mensaje;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @Path("eliminar-colaborador/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idColaborador") int idColaborador) {
        if (idColaborador <= 0) {
            throw new BadRequestException();
        }
        try {
            Mensaje mensaje = ImpColaborador.eliminarColaborador(idColaborador);
            return mensaje;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    @Path("subir-foto/{idColaborador}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("idColaborador") Integer idColaborador,
            byte[] foto){
        if(foto.length > 0 && idColaborador !=null && idColaborador >0){
            return ImpColaborador.guardarFoto(idColaborador, foto);
        }else{
            throw new BadRequestException();
        }  
    }
    
    @Path("obtener-foto/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerFoto(@PathParam("idColaborador") Integer idColaborador){
        if(idColaborador !=null && idColaborador >0){
            return ImpColaborador.obtenerFoto(idColaborador);
        }else{
            throw new BadRequestException();
        }  
    }
}