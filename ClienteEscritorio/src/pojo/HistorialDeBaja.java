/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author juanl
 */
public class HistorialDeBaja {
     private Integer idHistorialDeBaja;
    private Integer idUnidad;
    private String motivo;

    public HistorialDeBaja() {
    }

    public HistorialDeBaja(Integer idHistorialDeBaja, Integer idUnidad, String motivo) {
        this.idHistorialDeBaja = idHistorialDeBaja;
        this.idUnidad = idUnidad;
        this.motivo = motivo;
    }

    public Integer getIdHistorialDeBaja() {
        return idHistorialDeBaja;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setIdHistorialDeBaja(Integer idHistorialDeBaja) {
        this.idHistorialDeBaja = idHistorialDeBaja;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
