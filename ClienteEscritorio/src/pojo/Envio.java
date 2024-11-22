/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author reyes
 */
public class Envio {
 
    private Integer idEnvio;
    private Integer idCliente;
    private String origenCalle;
    private String origenNunero;
    private String origenColonia;
    private String origenCodigoPostal;
    private String origenCiudad;
    private String origenEstado;
    private String noGuia;
    private float costoEnvio;
    private Integer idEstadoDeEnvio;
    private Integer idColaborador;

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getOrigenCalle() {
        return origenCalle;
    }

    public void setOrigenCalle(String origenCalle) {
        this.origenCalle = origenCalle;
    }

    public String getOrigenNunero() {
        return origenNunero;
    }

    public void setOrigenNunero(String origenNunero) {
        this.origenNunero = origenNunero;
    }

    public String getOrigenColonia() {
        return origenColonia;
    }

    public void setOrigenColonia(String origenColonia) {
        this.origenColonia = origenColonia;
    }

    public String getOrigenCodigoPostal() {
        return origenCodigoPostal;
    }

    public void setOrigenCodigoPostal(String origenCodigoPostal) {
        this.origenCodigoPostal = origenCodigoPostal;
    }

    public String getOrigenCiudad() {
        return origenCiudad;
    }

    public void setOrigenCiudad(String origenCiudad) {
        this.origenCiudad = origenCiudad;
    }

    public String getOrigenEstado() {
        return origenEstado;
    }

    public void setOrigenEstado(String origenEstado) {
        this.origenEstado = origenEstado;
    }

    public String getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(String noGuia) {
        this.noGuia = noGuia;
    }

    public float getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(float costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Integer getIdEstadoDeEnvio() {
        return idEstadoDeEnvio;
    }

    public void setIdEstadoDeEnvio(Integer idEstadoDeEnvio) {
        this.idEstadoDeEnvio = idEstadoDeEnvio;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Envio(Integer idEnvio, Integer idCliente, String origenCalle, String origenNunero, String origenColonia, String origenCodigoPostal, String origenCiudad, String origenEstado, String noGuia, float costoEnvio, Integer idEstadoDeEnvio, Integer idColaborador) {
        this.idEnvio = idEnvio;
        this.idCliente = idCliente;
        this.origenCalle = origenCalle;
        this.origenNunero = origenNunero;
        this.origenColonia = origenColonia;
        this.origenCodigoPostal = origenCodigoPostal;
        this.origenCiudad = origenCiudad;
        this.origenEstado = origenEstado;
        this.noGuia = noGuia;
        this.costoEnvio = costoEnvio;
        this.idEstadoDeEnvio = idEstadoDeEnvio;
        this.idColaborador = idColaborador;
    }
    
    
           
    
}
