/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs.decorator;

/**
 *
 * @author HOME
 */
public class Decorator implements Precio{
    
    private Precio precio;
    
    public Decorator(Precio precio){
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio.getPrecio();
    }
    
}
