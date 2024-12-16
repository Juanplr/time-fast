/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import Utilidades.Utilidades;
import com.google.gson.Gson;
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
import javax.ws.rs.core.UriInfo;
import pojo.EstadoUnidad;
import pojo.Mensaje;
import pojo.TipoUnidad;
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
         if(!vin.isEmpty()&& Utilidades.validarInyecciónSQL(vin)){
                      return ImpUnidad.obtenerUnidadPorVin(vin);
         }
         throw new BadRequestException();
     }
     
    // Obtener unidades por marca
    @Path("obtener-unidades-por-marca/{marca}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> getUnidadesPorMarca(@PathParam("marca") String marca) {
        if(!marca.isEmpty() && Utilidades.validarInyecciónSQL(marca)){
            return ImpUnidad.obtenerUnidadesPorMarca(marca);
        }
        throw new BadRequestException();
    }

    
    // Obtener unidades por NII
    @Path("obtener-unidades-por-nii/{nii}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> getUnidadesPorNii(@PathParam("nii") String nii) {
        if(!nii.isEmpty()&&Utilidades.validarInyecciónSQL(nii)){
            return ImpUnidad.obtenerUnidadesPorNii(nii);
        }
        throw new BadRequestException();
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
            System.out.println(jsonUnidad);
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
    public Mensaje eliminarUnidad(@PathParam("idUnidad") Integer idUnidad) {
        if(idUnidad != null&& idUnidad > 0){
            return ImpUnidad.eliminarUnidad(idUnidad);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-tipos-unidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUnidad> getTiposUnidades(){
         return ImpUnidad.obtenerTiposDeUnidad();
    }
    
    @Path("obtener-estados-unidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstadoUnidad> getEstadosUnidades(){
         return ImpUnidad.obtenerEstadosDeUnidad();
    }
    
}
