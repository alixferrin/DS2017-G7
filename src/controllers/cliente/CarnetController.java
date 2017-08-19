/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import java.net.URL;
import java.util.ResourceBundle;
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
    private PasswordField txtContraseña;

    /**
     * Initializes the controller class.
     */
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
    
}
