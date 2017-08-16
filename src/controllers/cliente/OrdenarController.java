/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import TDAs.Conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class OrdenarController implements Initializable {
    @FXML
    private ListView<?> lstRestaurante;
    @FXML
    private Button btnMostrarInfo;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private Label lblCateogoria;
    @FXML
    private Label lblNombre;
    @FXML
    private ImageView imgImagen;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private ComboBox cmbCategorias;
    @FXML
    private ComboBox cmbRestaurante;
    @FXML
    private ListView lstPlatillos;
    @FXML
    private CheckBox cboxJugo;
    @FXML
    private CheckBox cboxPostre;
    @FXML
    private Button btnOrdenar;
    @FXML
    private Button btnOrdenar1;

    Conexion conexion = Conexion.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            conexion.setProcedure("{call listarRestaurantes()}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado()){
                cmbRestaurante.getItems().add(conexion.getResultFila(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }    

    @FXML
    private void mostrarInformacion(ActionEvent event) {
    }

    @FXML
    private void cargarListView(ActionEvent event) {
        lstPlatillos.getItems().clear();
        String categoria = (String) cmbCategorias.getValue();
        try{
            conexion.setProcedure("{call listPlatillosCategoria('" + categoria + "')}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado())
                lstPlatillos.getItems().add(conexion.getResultFila(1));
        }catch (SQLException sql){
            sql.printStackTrace();
        }

    }
    
    @FXML
    private void showTarjeta(ActionEvent event){
        Stage stage = new Stage();
        try{
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/TarjetaCredito.fxml"))));
            stage.setTitle("Pago con tarjeta de credito");
            stage.centerOnScreen();
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void showCarnet(ActionEvent event){
        Stage stage = new Stage();
        try{
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/Carnet.fxml"))));
            stage.setTitle("Pago con carnet");
            stage.centerOnScreen();
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
