/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class TarjetaCreditoController implements Initializable {
    @FXML
    private TextField txtTarjeta;
    @FXML
    private TextField txtCVC;
    @FXML
    private ComboBox<Integer> cmbMes;
    @FXML
    private ComboBox<Integer> cmbAnios;
    @FXML
    private Button btnPagar;
    @FXML
    private ComboBox cmbHorarios;

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
        
        cmbMes.getItems().add(1);
        cmbMes.getItems().add(2);
        cmbMes.getItems().add(3);
        cmbMes.getItems().add(4);
        cmbMes.getItems().add(5);
        cmbMes.getItems().add(6);
        cmbMes.getItems().add(7);
        cmbMes.getItems().add(8);
        cmbMes.getItems().add(9);
        cmbMes.getItems().add(10);
        cmbMes.getItems().add(11);
        cmbMes.getItems().add(12);
        
        cmbAnios.getItems().add(2017);
        cmbAnios.getItems().add(2018);
        cmbAnios.getItems().add(2019);
        cmbAnios.getItems().add(2020);
        cmbAnios.getItems().add(2021);
        cmbAnios.getItems().add(2022);
        cmbAnios.getItems().add(2023);
        cmbAnios.getItems().add(2024);
        cmbAnios.getItems().add(2025);
    }    
    
    @FXML
    private void pagar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra por tarjeta");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        if (txtTarjeta.getText().length() != 12 && txtTarjeta.getText().matches("\\d*")){
            alert.setHeaderText("Ingreso de tarjeta no válido");
            alert.setContentText("Ingrese un número de tarjeta válido");
        }else if (txtCVC.getText().length() != 3 && txtTarjeta.getText().matches("\\d*")){
            alert.setHeaderText("Ingreso de tarjeta no válido");
            alert.setContentText("Ingreso de CVC no válido");
        }else if (cmbAnios.getValue() < year || (cmbAnios.getValue() == year && cmbMes.getValue() < mes)){
            alert.setHeaderText("Fecha de expiracion");
            alert.setContentText("Su tarjeta ha expirado");
        }else{
            alert.setHeaderText("Ingreso de tarjeta válido");
            alert.setContentText("Su orden ha sido cargada a su tarjeta de crédito");
        }
        alert.showAndWait();
    }
    
}
