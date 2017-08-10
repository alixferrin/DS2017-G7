/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs.roles;

import TDAs.strategy.FormaDePago;

/**
 *
 * @author HOME
 */
public class Cliente extends Usuario {

    public Cliente(String cedula, String nombre, String apellido, String userName, String password, String level) {
        super(cedula, nombre, apellido, userName, password, level);
    }
    
    public void pagar(FormaDePago forma){
        forma.pagar();
    }
    
}
