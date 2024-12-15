package pojo;

/**
 *
 * @author juanl
 */
public class EstadoUnidad {
    private Integer idEstadoUnidad;
    private String nombre;

    public EstadoUnidad() {
    }

    public EstadoUnidad(Integer idEstadoUnidad, String nombre) {
        this.idEstadoUnidad = idEstadoUnidad;
        this.nombre = nombre;
    }

    public Integer getIdEstadoUnidad() {
        return idEstadoUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdEstadoUnidad(Integer idEstadoUnidad) {
        this.idEstadoUnidad = idEstadoUnidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }  
}
