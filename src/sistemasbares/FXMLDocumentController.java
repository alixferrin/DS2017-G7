/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author HOME
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblNada;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        lblNada.setText("No hay nada programado todavía :(");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
