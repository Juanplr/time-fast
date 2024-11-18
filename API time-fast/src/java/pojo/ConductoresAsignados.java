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
public class ConductoresAsignados {
    private Integer idConductoresAsignados;
    private Integer idColaborador;
    private Integer idUnidad;

    public ConductoresAsignados() {
    }

    public ConductoresAsignados(Integer idConductoresAsignados, Integer idColaborador, Integer idUnidad) {
        this.idConductoresAsignados = idConductoresAsignados;
        this.idColaborador = idColaborador;
        this.idUnidad = idUnidad;
    }

    public void setIdConductoresAsignados(Integer idConductoresAsignados) {
        this.idConductoresAsignados = idConductoresAsignados;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Integer getIdConductoresAsignados() {
        return idConductoresAsignados;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }
    
    
    
}
