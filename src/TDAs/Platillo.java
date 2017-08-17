/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import TDAs.decorator.Precio;

/**
 *
 * @author HOME
 */
public class Platillo implements Precio{
    private String id_platillo, nombre, descripcion, categoria, ingredientes, imagen;
    private double precio;

    public Platillo(String id_platillo, String nombre, String descripcion, String categoria, String imagen, String ingredientes, String precio) {
        this.id_platillo = id_platillo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = Double.parseDouble(precio);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }
    
}
