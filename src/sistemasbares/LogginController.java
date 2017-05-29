/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author HOME
 */
public class LogginController implements Initializable {
    
    @FXML
    private Label lblNada;
    
    @FXML
    private void showMenu(ActionEvent event) {
        lblNada.setText("No hay nada programado todavía :(");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try{
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Cliente.fxml"))));
            stage.centerOnScreen();
        }catch(IOException e){ 
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
