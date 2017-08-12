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
    
    Conexion conexion = Conexion.getInstance();
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
        conexion.setProcedure("{call buscarPlatillo('" + busqueda + "')}");
        conexion.ejecutarQuery();
        if (!conexion.iterarResultado()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Resultados de la búsqueda");
            alert.setHeaderText(null);
            alert.setContentText("No se encontraron platillos con los datos ingresados");
            alert.showAndWait();
        }else{
            conexion.getResultSet().beforeFirst();
            while (conexion.iterarResultado())
                lstPlatillos.getItems().add(conexion.getResultFila(1));
        }
    }

    @FXML
    private void showMostrarInfo(ActionEvent event) {
        try{
            String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
            this.limpiar();
            conexion.setProcedure("{call getRest('" + nombrePla + "')}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado()){
                lstRestaurante.getItems().add(conexion.getResultFila(1));
            }
            conexion.setProcedure("{call getInfoPla('" + nombrePla + "')}");
            conexion.ejecutarQuery();
            conexion.iterarResultado();
            lblNombre.setText(conexion.getResultFila(2));
            lblCategoria.setText(conexion.getResultFila(4));
            txtDescripcion.setText(conexion.getResultFila(3));
            txtIngredientes.setText(conexion.getResultFila(6));
            Image imagen = new Image(new FileInputStream("imgs\\" + conexion.getResultFila(5)));
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
