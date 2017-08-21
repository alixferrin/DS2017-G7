/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs.roles;

import TDAs.strategy.FormaDePago;
import javafx.scene.control.Alert;

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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pedido");
            alert.setHeaderText("Se ha registrado su pedido");
            alert.setContentText("El numero de su pedido es: ?");
            alert.showAndWait();
        }else
            System.out.println("No tiene fondos suficientes;");
    }

    public String getId_carnet() {
        return id_carnet;
    }

    public double getSaldo() {
        return saldo;
    }
    
}
