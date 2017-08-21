/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import TDAs.Conexion;
import TDAs.roles.Asistente;
import TDAs.roles.Cliente;
import TDAs.roles.Usuario;
import controllers.asistente.AsistenteController;
import controllers.cliente.ClienteController;
import javafx.scene.layout.AnchorPane;

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
    
    Conexion conexion = Conexion.getInstance();
    
    private void showMenu(ActionEvent event, String fxmlDocument, Usuario user) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try{
            /*
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/"+fxmlDocument))));
            stage.setTitle(fxmlDocument.substring(0, fxmlDocument.lastIndexOf(".")));
            stage.centerOnScreen();
            */
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/" + fxmlDocument));
            AnchorPane userView = (AnchorPane) loader.load();
            if (user.getLevel().equals("1")){
                ClienteController controler = loader.getController();
                controler.setCliente((Cliente)user);
            }else if(user.getLevel().equals("2")){
                AsistenteController controler = loader.getController();
                controler.setAsistente((Asistente)user);
            }
            
            stage.setScene(new Scene(userView));
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
            conexion.setProcedure("{Call login('" + usr + "','" + pwrd + "')}") ;
            conexion.ejecutarQuery();
            if (!conexion.iterarResultado())
                lblNada.setText("Usuario o contraseña incorrecta");
            else{
                level = conexion.getResultFila(6);
                System.out.println(level);
                if (level.equals("1")){
                    Cliente cliente = new Cliente(conexion.getResultFila(1), conexion.getResultFila(2), conexion.getResultFila(3), conexion.getResultFila(4), conexion.getResultFila(5), level, conexion.getResultFila(7), Integer.parseInt(conexion.getResultFila(8)));
                    this.showMenu(event, "Cliente.fxml", cliente);
                }else{
                    Asistente asistente = new Asistente(conexion.getResultFila(1), conexion.getResultFila(2), conexion.getResultFila(3), conexion.getResultFila(4), conexion.getResultFila(5), level);
                    Conexion.asisRest = conexion.getResultFila(1);
                    this.showMenu(event, "Asistente.fxml", asistente);
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
            conexion.setConnexion(user, pwrd);
        }catch(SQLException ex){
            Logger.getLogger(LogginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
