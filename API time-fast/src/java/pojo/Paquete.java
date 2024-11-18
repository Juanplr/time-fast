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
public class Paquete {
    private Integer idPaquete;
    private Integer idEnvio;
    private String descripcion;
    private float peso;
    private float alto;
    private float ancho;
    private float profundidad;

    public Paquete(int idPaquete, int idEnvio, String descripcion, float peso, float alto, float ancho, float profundidad) {
        this.idPaquete = idPaquete;
        this.idEnvio = idEnvio;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
    }

    public Paquete() {
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public void setProfundidad(float profundidad) {
        this.profundidad = profundidad;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public float getAlto() {
        return alto;
    }

    public float getAncho() {
        return ancho;
    }

    public float getProfundidad() {
        return profundidad;
    }
    
    
}
