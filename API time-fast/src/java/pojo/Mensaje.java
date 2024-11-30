package pojo;

public class Mensaje {
    private boolean error;
    private String mensaje;
    private Object body;

    public Mensaje(boolean error, String mensaje, Object body) {
        this.error = error;
        this.mensaje = mensaje;
        this.body = body;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
    public Mensaje() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public Object getBody() {
        return body;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public boolean isError() {
        return error;
    }
}
