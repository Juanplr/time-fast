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
public class EstadoDeEnvio {
   private Integer idEstadoDeEnvio;
   private String nombre;

    public EstadoDeEnvio() {
    }

    public EstadoDeEnvio(Integer idEstadoDeEnvio, String nombre) {
        this.idEstadoDeEnvio = idEstadoDeEnvio;
        this.nombre = nombre;
    }

    public Integer getIdEstadoDeEnvio() {
        return idEstadoDeEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdEstadoDeEnvio(Integer idEstadoDeEnvio) {
        this.idEstadoDeEnvio = idEstadoDeEnvio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
