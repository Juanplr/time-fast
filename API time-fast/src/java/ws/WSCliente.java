package ws;

import Utilidades.Utilidades;
import com.google.gson.Gson;
import dominio.ImpCliente;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Cliente;
import pojo.Mensaje;


@Path("cliente")
public class WSCliente {
    
    @Path("obtener-clientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes(){
        
        return ImpCliente.getClientes();
    }
    
    @Path("obtener-clientes-nombre/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getCLientesPorNombre(@PathParam("nombre") String nombre){
         if(!nombre.isEmpty() && Utilidades.validarInyecciónSQL(nombre)){
            return ImpCliente.obtenerClientesPorNombre(nombre);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-cliente-correo/{correo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getCLientePorCorreo(@PathParam("correo") String correo){
         if(!correo.isEmpty() && Utilidades.validarInyecciónSQL(correo)){
            return ImpCliente.obtenerClientePorCorreo(correo);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-cliente-numero-telefono/{telefono}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getCLientePorNumeroTelefono(@PathParam("telefono") String telefono){
         if(!telefono.isEmpty() && Utilidades.validarInyecciónSQL(telefono)){
            return ImpCliente.obtenerClientePorNumeroTelefonico(telefono);
        }
        throw new BadRequestException();
    }
    
    @Path("agregar-cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarCliente(String jsonCliente) {
        if (!jsonCliente.isEmpty()) {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
            if(!validarCliente(cliente)){
                return ImpCliente.agregarCliente(cliente);
            }else{
                throw new BadRequestException("Datos del cliente invaidos");
            }
        } else {
            throw new BadRequestException("Parámetro inválido: el cliente no puede estar vacio.");
        }
    }
    
    @Path("editar-cliente")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(String jsonCliente) {
        if (!jsonCliente.isEmpty()) {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
            if(!validarCliente(cliente)){
                return ImpCliente.editarCliente(cliente);
            }else{
               throw new BadRequestException("Datos del Cliente equivocados");
            }
        } else {
            throw new BadRequestException("Parámetro inválido: el cliente no puede estar vacio.");
        }
    }
    
    @Path("eliminar-cliente")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@FormParam("idCliente") Integer idCliente){
        if(idCliente != null&& idCliente > 0){
            return ImpCliente.eliminarCliente(idCliente);
        }
        throw new BadRequestException();
    }
    
    private boolean validarCliente(Cliente cliente) {
        boolean error = false;
        // Validar que el nombre no tenga números
        if (!cliente.getNombre().matches("^[a-zA-Z\\s]+$")){
            error = true;
        }

        // Validar que el apellido paterno no tenga números
        if (!cliente.getApellidoPaterno().matches("^[a-zA-Z\\s]+$")) {
            error = true;
        }

        // Validar que el apellido materno no tenga números
        if (!cliente.getApellidoMaterno().matches("^[a-zA-Z\\s]+$")) {
            error = true;
        }

        // Validar que el número de teléfono tenga exactamente 10 dígitos
        if (!cliente.getTelefono().matches("^\\d{10}$")) {
           error = true;
        }

        // Validar que el correo tenga un formato válido
        if (!cliente.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            error = true;
        }

        return error;
    }
}