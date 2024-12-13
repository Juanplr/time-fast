package pojo;

/**
 *
 * @author juanl
 */
public class Mensaje {
    private boolean error;
    private String mensaje;

    public Mensaje(boolean error, String mensaje, Object body) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
    public Mensaje() {
    }

    public String getMensaje() {
        return mensaje;
    }


    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isError() {
        return error;
    }
}
