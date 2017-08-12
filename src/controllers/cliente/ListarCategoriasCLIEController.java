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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import TDAs.Conexion;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class ListarCategoriasCLIEController implements Initializable {
    @FXML
    private ComboBox cmbCategorias;
    @FXML
    private ListView lstPlatillos;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private ImageView imgImagen;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblRestaurante;
    @FXML
    private Label lblCateogoria;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private Button btnMostrarInfo;
    @FXML
    private ListView lstRestaurante;
    
    Conexion conexion = Conexion.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            conexion.setProcedure("{call listCategorias()}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado()){
                cmbCategorias.getItems().add(conexion.getResultFila(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }    

    @FXML
    private void mostrarInformacion(ActionEvent event){
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
            lblCateogoria.setText(conexion.getResultFila(4));
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

    @FXML
    private void cargarListView(ActionEvent event) throws SQLException{
        lstPlatillos.getItems().clear();
        String categoria = (String) cmbCategorias.getValue();
        conexion.setProcedure("{call mostrarPlatillos('" + categoria + "')}");
        conexion.ejecutarQuery();
        while (conexion.iterarResultado()){
            lstPlatillos.getItems().add(conexion.getResultFila(1));
        }
    }
    
    private void limpiar(){
        lblNombre.setText("");
        lblCateogoria.setText("");
        txtDescripcion.clear();
        lstRestaurante.getItems().clear();
        txtIngredientes.clear();
        imgImagen.setImage(null);
    }
    
}
