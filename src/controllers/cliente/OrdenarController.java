/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import TDAs.Conexion;
import TDAs.Platillo;
import TDAs.decorator.Jugo;
import TDAs.decorator.Postre;
import TDAs.decorator.Precio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
    private Button btnMostrarInfo;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPrecio;
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
    private Button btnCarnet;
    @FXML
    private Button btnTarjeta;

    Conexion conexion = Conexion.getInstance();
    Platillo platillo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbCategorias.getItems().add("EJECUTIVO");
        cmbCategorias.getItems().add("ESTUDIANTIL");
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
    public void enableCategorias(ActionEvent event){
        cmbCategorias.setDisable(false);
    }

    @FXML
    private void cargarInformacion(ActionEvent event) {
        try{
            String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
            this.limpiar();
            conexion.setProcedure("{call getInfoPla('" + nombrePla + "')}");
            conexion.ejecutarQuery();
            conexion.iterarResultado();
            platillo = new Platillo(conexion.getResultFila(1), conexion.getResultFila(2), conexion.getResultFila(3), conexion.getResultFila(4), conexion.getResultFila(5), conexion.getResultFila(6), conexion.getResultFila(7));
            mostrarInformacion(platillo);
        }catch (SQLException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selección de platillos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un platillo de la lista.");
            alert.showAndWait();
        }
    }
    
    private void mostrarInformacion(Platillo p){
        try{
            lblNombre.setText(p.getNombre());
            txtDescripcion.setText(p.getDescripcion());
            txtIngredientes.setText(p.getIngredientes());
            Image imagen = new Image(new FileInputStream("imgs\\" + p.getImagen()));
            imgImagen.setImage(imagen);
            lblPrecio.setText(p.getPrecio() + "");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private void actualizarPrecio(Precio p){
        lblPrecio.setText(p.getPrecio() + "");
    }
    
    @FXML
    private void validarCheckBox(){
        if (cboxPostre.isSelected() && cboxJugo.isSelected())
            actualizarPrecio(new Postre(new Jugo(platillo)));
        else if(cboxPostre.isSelected() && !cboxJugo.isSelected())
            actualizarPrecio(new Postre((platillo)));
        else if(!cboxPostre.isSelected() && cboxJugo.isSelected())
            actualizarPrecio(new Jugo(platillo));
        else
            actualizarPrecio(platillo);
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
        this.habilitar();
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
    
    private void habilitar(){
        lstPlatillos.setDisable(false);
        btnMostrarInfo.setDisable(false);
        txtIngredientes.setDisable(false);
        txtDescripcion.setDisable(false);
        cboxJugo.setDisable(false);
        cboxPostre.setDisable(false);
        btnCarnet.setDisable(false);
        btnTarjeta.setDisable(false);
    }
    
    private void limpiar(){
        lblNombre.setText("");
        txtDescripcion.clear();
        txtIngredientes.clear();
        imgImagen.setImage(null);
    }
}
