package pojo;


public class Colaborador {
    
    private Integer idColaborador;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private String correo;
    private String noPersonal;
    private Integer idRol;
    private String rol;
    private String numeroDeLicencia;
    private String fotografia;
    private String contrasena;

    public Colaborador() {
    }

    public Colaborador(Integer idColaborador, String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String correo, String noPersonal, Integer idRol, String rol, String numeroDeLicencia, String fotografia, String contrasena) {
        this.idColaborador = idColaborador;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.correo = correo;
        this.noPersonal = noPersonal;
        this.idRol = idRol;
        this.rol = rol;
        this.numeroDeLicencia = numeroDeLicencia;
        this.fotografia = fotografia;
        this.contrasena = contrasena;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCurp() {
        return curp;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNoPersonal() {
        return noPersonal;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public String getRol() {
        return rol;
    }

    public String getNumeroDeLicencia() {
        return numeroDeLicencia;
    }

    public String getFotografia() {
        return fotografia;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setNumeroDeLicencia(String numeroDeLicencia) {
        this.numeroDeLicencia = numeroDeLicencia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
