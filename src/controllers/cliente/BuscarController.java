/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import TDAs.Conexion;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class BuscarController implements Initializable {
    @FXML
    private TextField txtBusqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private ListView lstPlatillos;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private ImageView imgImagen;
    @FXML
    private Label lblNombre;
    @FXML
    private Button btnMostrarInfo;
    @FXML
    private Label lblCategoria;
    @FXML
    private ListView lstRestaurante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscar(ActionEvent event) throws SQLException {
        this.limpiar();
        lstPlatillos.getItems().clear();
        String busqueda = txtBusqueda.getText().toUpperCase();
        Conexion.procedure = Conexion.connection.prepareCall("{call buscarPlatillo('" + busqueda + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        if (!Conexion.result.next()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Resultados de la búsqueda");
            alert.setHeaderText(null);
            alert.setContentText("No se encontraron platillos con los datos ingresados");
            alert.showAndWait();
        }else{
            Conexion.result.beforeFirst();
            while (Conexion.result.next())
                lstPlatillos.getItems().add(Conexion.result.getString(1));
        }
    }

    @FXML
    private void showMostrarInfo(ActionEvent event) {
        try{
            String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
            this.limpiar();
            Conexion.procedure = Conexion.connection.prepareCall("{call getRest('" + nombrePla + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next()){
                lstRestaurante.getItems().add(Conexion.result.getString(1));
            }
            Conexion.procedure = Conexion.connection.prepareCall("{call getInfoPla('" + nombrePla + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            Conexion.result.next();
            lblNombre.setText(Conexion.result.getString(2));
            lblCategoria.setText(Conexion.result.getString(4));
            txtDescripcion.setText(Conexion.result.getString(3));
            txtIngredientes.setText(Conexion.result.getString(6));
            Image imagen = new Image(new FileInputStream("imgs\\" + Conexion.result.getString(5)));
            imgImagen.setImage(imagen);
        }catch (SQLException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selección de platillos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un platillo de la lista.");
            alert.showAndWait();
        }catch (FileNotFoundException ef){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mostrar Información - Imagen");
            alert.setHeaderText(null);
            alert.setContentText("No se puede mostrar imagen del platillo. La imagen no se encuentra en el sistema");
            alert.showAndWait();
        }
    }
    
    private void limpiar(){
        lblNombre.setText("");
        lblCategoria.setText("");
        txtDescripcion.clear();
        lstRestaurante.getItems().clear();
        txtIngredientes.clear();
        imgImagen.setImage(null);
    }
}
