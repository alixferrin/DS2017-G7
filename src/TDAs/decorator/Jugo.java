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
public class Jugo extends Decorator{
   
    public Jugo(Precio precio){
        super(precio);
    }
    public double getPrecio() {
        return super.getPrecio() + 0.50;
    }
}
    
    
