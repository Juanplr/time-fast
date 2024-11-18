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
public class HistorialDeBaja {
    private Integer idHistorialBaja;
    private Integer idUnidad;
    private String motivo;

    public HistorialDeBaja() {
    }

    public HistorialDeBaja(Integer idHistorialBaja, Integer idUnidad, String motivo) {
        this.idHistorialBaja = idHistorialBaja;
        this.idUnidad = idUnidad;
        this.motivo = motivo;
    }

    public void setIdHistorialBaja(Integer idHistorialBaja) {
        this.idHistorialBaja = idHistorialBaja;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdHistorialBaja() {
        return idHistorialBaja;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public String getMotivo() {
        return motivo;
    }
    
    
}
