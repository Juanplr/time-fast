/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpCliente;
import dominio.ImpRol;
import dominio.ImpUnidad;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import pojo.Cliente;
import pojo.Mensaje;
import pojo.Rol;
import pojo.Unidad;

/**
 *
 * @author reyes
 */

@Path("unidad")

public class WSUnidad {
    
     @Context
    private UriInfo context;
     
     @Path("obtener-unidades")
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     public List<Unidad> getUnidades(){
         return ImpUnidad.obtenerUnidades();
     }
    
     @Path("obtener-unidades-disponibles")
     @GET
     @Produces(MediaType.APPLICATION_JSON)       
     public List<Unidad> getUnidadesDisponibles(){
         return ImpUnidad.obtenerUnidadesDisponibles();
     }
     
      // Obtener unidad por VIN
     @Path("obtener-unidad-por-vin/{vin}")
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     public List<Unidad> getUnidadPorVin(@PathParam("vin") String vin){
         return ImpUnidad.obtenerUnidadPorVin(vin);
     }
     
    // Obtener unidades por marca
    @Path("obtener-unidades-por-marca/{marca}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Unidad getUnidadesPorMarca(@PathParam("marca") String marca) {
        return ImpUnidad.obtenerUnidadesPorMarca(marca);
    }

    
    // Obtener unidades por NII
    @Path("obtener-unidades-por-nii/{nii}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> getUnidadesPorNii(@PathParam("nii") String nii) {
       return ImpUnidad.obtenerUnidadesPorNii(nii);
    }

    
    // Agregar una unidad
    @Path("agregar-unidad")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarUnidad(String jsonUnidad) {
        if (!jsonUnidad.isEmpty()) {
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(jsonUnidad, Unidad.class);
            return ImpUnidad.agregarUnidad(unidad);
        } else {
            throw new BadRequestException("Parámetro inválido: la unidad no puede estar vacio.");
        }
    }

    
    // Editar una unidad
    @Path("editar-unidad")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUnidad(String jsonUnidad) {
        if(!jsonUnidad.isEmpty()){
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(jsonUnidad, Unidad.class);
            return ImpUnidad.editarUnidad(unidad);
        }else{
            throw new BadRequestException("Parametro invalido: la unidad no puede estar vacia");
        }
    }

    
    // Eliminar una unidad
    @Path("eliminar-unidad/{idUnidad}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarUnidad(@PathParam("idUnidad") int idUnidad) {
        return ImpUnidad.eliminarUnidad(idUnidad);
    }
     
    
}
