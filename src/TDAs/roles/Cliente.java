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
    private String id_carnet;
    private double saldo;

    public Cliente(String cedula, String nombre, String apellido, String userName, String password, String level, String id_carnet, double saldo) {
        super(cedula, nombre, apellido, userName, password, level);
        this.id_carnet = id_carnet;
        this.saldo = saldo;
    }
    
    public void pagar(FormaDePago forma, double cantidad){
        double saldoTotal = this.saldo - cantidad;
        if (saldoTotal > 0){
            forma.pagar(this.id_carnet, saldoTotal);
        }else
            System.out.println("No tiene fondos suficientes;");
    }
    
}
