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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class ClienteController implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuCliente;
    @FXML
    private MenuItem menuCategoria;
    @FXML
    private Menu menuPlatos;
    @FXML
    private MenuItem menuMostrarPlatos;
    @FXML
    private MenuItem menuMostrarPlatillos;
    @FXML
    private Menu menuBuscar;
    @FXML
    private Menu menuLogOut;
    @FXML
    private StackPane loaderPane;
    
    @FXML
    private void showListarCategoriasCLIE(ActionEvent event) throws IOException{
        loaderPane.getChildren().clear();
        Node n = FXMLLoader.load(getClass().getResource("ListarCategoriasCLIE.fxml"));
        loaderPane.getChildren().add(n);
        StackPane.setAlignment(n,Pos.CENTER);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
