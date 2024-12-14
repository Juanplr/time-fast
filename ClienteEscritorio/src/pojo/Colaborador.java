/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

public class Colaborador {
    private Integer idColaborador;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private String correo;
    private String noPersonal;
    private String contrasena;
    private Integer idRol;
    private String rol;
    private String fotografia;
    private byte[] foto;
    private String numeroDeLicencia;

    // Constructor sin argumentos
    public Colaborador() {
    }

    public Colaborador(Integer idColaborador, String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String correo, String noPersonal, String contrasena, Integer idRol, String rol, String fotografia, byte[] foto, String numeroDeLicencia) {
        this.idColaborador = idColaborador;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.correo = correo;
        this.noPersonal = noPersonal;
        this.contrasena = contrasena;
        this.idRol = idRol;
        this.rol = rol;
        this.fotografia = fotografia;
        this.foto = foto;
        this.numeroDeLicencia = numeroDeLicencia;
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

    public String getContrasena() {
        return contrasena;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public String getRol() {
        return rol;
    }

    public String getFotografia() {
        return fotografia;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getNumeroDeLicencia() {
        return numeroDeLicencia;
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

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setNumeroDeLicencia(String numeroDeLicencia) {
        this.numeroDeLicencia = numeroDeLicencia;
    }
    


}