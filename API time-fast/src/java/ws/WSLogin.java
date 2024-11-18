package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


/**
 * REST Web Service
 *
 * @author juanl
 */
@Path("login")
public class WSLogin {

    @Context
    private UriInfo context;

    public WSLogin() {
    }
    
    @Path("probar-conexion")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean probarConexion(){
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        
        return (conexion!=null);
    }
}

