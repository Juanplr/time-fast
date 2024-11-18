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
public class TipoUnidad {
    private Integer idTipoUnidad;
    private String nombre;

    public TipoUnidad() {
    }

    public TipoUnidad(Integer idTipoUnidad, String nombre) {
        this.idTipoUnidad = idTipoUnidad;
        this.nombre = nombre;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
