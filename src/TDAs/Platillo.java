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
public class Platillo {
    private String id_platillo, nombre, descripcion, ingredientes, categoria, temperatura, tipo, servido;

    public Platillo(String id_platillo, String nombre, String descripcion, String ingredientes, String categoria, String temperatura, String tipo, String servido) {
        this.id_platillo = id_platillo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.temperatura = temperatura;
        this.tipo = tipo;
        this.servido = servido;
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

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getServido() {
        return servido;
    }

    public void setServido(String servido) {
        this.servido = servido;
    }
    
}
