/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

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
import sistemasbares.Main;


public class ClienteController implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuBuscar;
    @FXML
    private Menu menuLogOut;
    @FXML
    private StackPane loaderPane;
    @FXML
    private Menu menuClategorias;
    @FXML
    private MenuItem menuListCategoria;
    @FXML
    private Menu menuPedido;
    
    @FXML
    private void showListarCategoriasCLIE(ActionEvent event) throws IOException{
        loaderPane.getChildren().clear();
        Node n = FXMLLoader.load(getClass().getResource("/FXML/ListarCategoriasCLIE.fxml"));
        loaderPane.getChildren().add(n);
        StackPane.setAlignment(n,Pos.CENTER);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showBuscar(ActionEvent event) throws IOException {
        loaderPane.getChildren().clear();
        Node n = FXMLLoader.load(getClass().getResource("/FXML/Buscar.fxml"));
        loaderPane.getChildren().add(n);
        StackPane.setAlignment(n,Pos.CENTER);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        try{
            Main.logout();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void showComprar(ActionEvent event) throws IOException {
        loaderPane.getChildren().clear();
        Node n = FXMLLoader.load(getClass().getResource("/FXML/Ordenar.fxml"));
        loaderPane.getChildren().add(n);
        StackPane.setAlignment(n, Pos.CENTER);
        
    }
    
}
