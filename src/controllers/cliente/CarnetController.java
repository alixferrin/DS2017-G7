/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import TDAs.roles.Cliente;
import TDAs.strategy.Carnet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class CarnetController implements Initializable {
    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnPagar;
    @FXML
    private ComboBox cmbHorarios;
    @FXML
    private PasswordField txtContrasenia;

    private Cliente cliente;
    private double cantPagar;
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public void setCantPagar(double cantidad){
        this.cantPagar = cantidad;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbHorarios.getItems().add("11:00 - 11:30");
        cmbHorarios.getItems().add("11:30 - 12:00");
        cmbHorarios.getItems().add("12:00 - 12:30");
        cmbHorarios.getItems().add("12:30 - 13:00");
        cmbHorarios.getItems().add("13:00 - 13:30");
        cmbHorarios.getItems().add("13:30 - 14:00");
        cmbHorarios.getItems().add("14:00 - 14:30");
        cmbHorarios.getItems().add("14:30 - 15:00");
    }    

    @FXML
    private void pagar(ActionEvent event) {
        if (cliente.getUserName().equals(txtUsuario.getText()) && cliente.getPassword().equals(txtContrasenia.getText()))
            cliente.pagar(new Carnet(), cantPagar);
        else
            System.out.println("Usuario o contraseña incorrectos");
    }
    
    
}
