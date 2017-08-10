/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

/**
 *
 * @author HOME
 */
public class Restaurante {
    private String idRestaurante, nombre, direccion, telefono, owner, asistente;

    public Restaurante(String idRestaurante, String nombre, String direccion, String telefono, String owner, String asistente) {
        this.idRestaurante = idRestaurante;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.owner = owner;
        this.asistente = asistente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAsistente() {
        return asistente;
    }

    public void setAsistente(String asistente) {
        this.asistente = asistente;
    }
    
}
