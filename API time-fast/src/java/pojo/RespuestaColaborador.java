package pojo;

import java.util.List;


public class RespuestaColaborador {
    private boolean error;
    private String mensaje;
    private List<Colaborador> colaboradores;

    public RespuestaColaborador() {
    }
    

    public RespuestaColaborador(boolean error, String mensaje, List<Colaborador> colaboradores) {
        this.error = error;
        this.mensaje = mensaje;
        this.colaboradores = colaboradores;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public boolean isError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }
    
}
