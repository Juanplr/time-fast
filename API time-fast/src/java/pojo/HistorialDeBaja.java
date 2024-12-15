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
    private Integer idHistorialDeBaja;
    private Integer idUnidad;
    private String motivo;

    public HistorialDeBaja() {
    }

    public HistorialDeBaja(Integer idHistorialBaja, Integer idUnidad, String motivo) {
        this.idHistorialDeBaja = idHistorialBaja;
        this.idUnidad = idUnidad;
        this.motivo = motivo;
    }

    public void setIdHistorialBaja(Integer idHistorialBaja) {
        this.idHistorialDeBaja = idHistorialBaja;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

   

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public String getMotivo() {
        return motivo;
    }
    
    public Integer getIdHistorialDeBaja() {
    return idHistorialDeBaja;
}

public void setIdHistorialDeBaja(Integer idHistorialDeBaja) {
    this.idHistorialDeBaja = idHistorialDeBaja;
}
    
}
