package pojo;


public class Unidad {
    
    private Integer idUnidad;
    private String marca;
    private String modleo;
    private String anio;
    private String vin;
    private String nii;
    private Integer idTipoUnidad;
    private Integer idEstadoUnidad;
    private String tipoUnidad;
    private String estadoUnidad;

    public Unidad() {
    }

    public Unidad(Integer idUnidad, String marca, String modleo, String anio, String vin, String nii, Integer idTipoUnidad, Integer idEstadoUnidad, String tipoUnidad, String estadoUnidad) {
        this.idUnidad = idUnidad;
        this.marca = marca;
        this.modleo = modleo;
        this.anio = anio;
        this.vin = vin;
        this.nii = nii;
        this.idTipoUnidad = idTipoUnidad;
        this.idEstadoUnidad = idEstadoUnidad;
        this.tipoUnidad = tipoUnidad;
        this.estadoUnidad = estadoUnidad;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public String getMarca() {
        return marca;
    }

    public String getModleo() {
        return modleo;
    }

    public String getAnio() {
        return anio;
    }

    public String getVin() {
        return vin;
    }

    public String getNii() {
        return nii;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public Integer getIdEstadoUnidad() {
        return idEstadoUnidad;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public String getEstadoUnidad() {
        return estadoUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModleo(String modleo) {
        this.modleo = modleo;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setNii(String nii) {
        this.nii = nii;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public void setIdEstadoUnidad(Integer idEstadoUnidad) {
        this.idEstadoUnidad = idEstadoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public void setEstadoUnidad(String estadoUnidad) {
        this.estadoUnidad = estadoUnidad;
    }
    
    
}