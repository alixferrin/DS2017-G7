/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import TDAs.Conexion;

/**
 *
 * @author HOME
 */
public class LogginController implements Initializable {
    
    @FXML
    private Label lblNada;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnIngresar;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblContrasenia;
    
    private void showMenu(ActionEvent event, String fxmlDocument) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try{
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlDocument))));
            stage.setTitle(fxmlDocument.substring(0, fxmlDocument.lastIndexOf(".")));
            stage.centerOnScreen();
        }catch(IOException e){ 
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loggin(ActionEvent event){
        String usr = txtUsuario.getText();
        String pwrd = txtPassword.getText();
        String level = "";
        try{
            Conexion.procedure = Conexion.connection.prepareCall("{Call login('" + usr + "','" + pwrd + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            if (!Conexion.result.next())
                lblNada.setText("Usuario o contraseña incorrecta");
            else{
                level = Conexion.result.getString(1);
                System.out.println(level);
                if (level.equals("1"))
                    this.showMenu(event, "Cliente.fxml");
                else{
                    Conexion.asisRest = Conexion.result.getString(2);
                    this.showMenu(event, "Asistente.fxml");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String user = "root";
        String pwrd = "saverio.1995";
        try{
            //Esta clase sirve para generar la conexion en SQL
            Conexion.connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemabares", user, pwrd);
        }catch(SQLException ex){
            Logger.getLogger(LogginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
