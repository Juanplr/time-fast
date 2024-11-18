package mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author juanl
 */
public class MyBatisUtil {
    private static final String RESOURCE ="mybatis/MyBatisConfig.xml";
    private static final String ENVIROMENT ="development";
    
    public static SqlSession obtenerConexion(){
        SqlSession conexionBD = null;
        try {
                Reader reader = Resources.getResourceAsReader(RESOURCE);
                SqlSessionFactory abrirSesion = new SqlSessionFactoryBuilder().build(reader);
                conexionBD = abrirSesion.openSession();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        return conexionBD;
    }
}
