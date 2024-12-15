/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Daniel García Jácome
 */
public class HistorialDeEnvio {
    private Integer idHistorialDeEnvio;
    private Integer idPaquete;
    private Integer idEnvio;
    private Integer idColaborador;
    private String motivo;
    private String tiempoDeCambio;

    public HistorialDeEnvio() {
    }

    public HistorialDeEnvio(Integer idHistorialDeEnvio, Integer idPaquete, Integer idEnvio, Integer idColaborador, String motivo, String tiempoDeCambio) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
        this.idPaquete = idPaquete;
        this.idEnvio = idEnvio;
        this.idColaborador = idColaborador;
        this.motivo = motivo;
        this.tiempoDeCambio = tiempoDeCambio;
    }

    public void setIdHistorialDeEnvio(Integer idHistorialDeEnvio) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setTiempoDeCambio(String tiempoDeCambio) {
        this.tiempoDeCambio = tiempoDeCambio;
    }

    public Integer getIdHistorialDeEnvio() {
        return idHistorialDeEnvio;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getTiempoDeCambio() {
        return tiempoDeCambio;
    }
    
    
}
