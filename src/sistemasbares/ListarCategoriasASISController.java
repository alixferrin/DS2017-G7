/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class ListarCategoriasASISController implements Initializable {
    @FXML
    private ListView lstPlatillos;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCategoria;
    @FXML
    private ImageView imgImagen;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCargarIMG;
    @FXML
    private ComboBox cmbCategorias;
    @FXML
    private Button btnMostrarInfo;
    @FXML
    private ListView lstRestaurante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtDescripcion.setWrapText(true);
        try{
            Conexion.procedure = Conexion.connection.prepareCall("{call listCatASIS('" + Conexion.asisRest + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next()){
                cmbCategorias.getItems().add(Conexion.result.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }    

    @FXML
    private void mostrarInfo(ActionEvent event) {
        try{
            String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
            btnModificar.setDisable(false);
            this.limpiar();
            this.deshabilitar();
            Conexion.procedure = Conexion.connection.prepareCall("{call getRestASIS('" + Conexion.asisRest + "','" + nombrePla + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next()){
                lstRestaurante.getItems().add(Conexion.result.getString(1));
            }
            Conexion.procedure = Conexion.connection.prepareCall("{call getInfoPla('" + nombrePla + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            Conexion.result.next();
            txtNombre.setText(Conexion.result.getString(1));
            txtCategoria.setText(Conexion.result.getString(3));
            txtDescripcion.setText(Conexion.result.getString(2));
            txtIngredientes.setText(Conexion.result.getString(5));
            Image imagen = new Image(new FileInputStream("imgs\\" + Conexion.result.getString(4)));
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
    private void cargarListView(ActionEvent event) throws SQLException {
        btnMostrarInfo.setDisable(false);
        lstPlatillos.getItems().clear();
        String categoria = (String) cmbCategorias.getValue();
        Conexion.procedure = Conexion.connection.prepareCall("{call mostrarPlatilloASIS('" + Conexion.asisRest + "','" + categoria + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        while (Conexion.result.next()){
            lstPlatillos.getItems().add(Conexion.result.getString(1));
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        txtNombre.setEditable(true);
        txtCategoria.setEditable(true);
        txtIngredientes.setEditable(true);
        txtDescripcion.setEditable(true);
        btnGuardar.setDisable(false);
        btnLimpiar.setDisable(false);
        btnCargarIMG.setDisable(false);
    }
    
    @FXML
    private void limpiar(){
        txtNombre.setText("");
        txtCategoria.setText("");
        txtDescripcion.clear();
        txtIngredientes.clear();
        lstRestaurante.getItems().clear();
        imgImagen.setImage(null);
    }
    
    private void deshabilitar(){
        txtNombre.setEditable(false);
        txtCategoria.setEditable(false);
        txtIngredientes.setEditable(false);
        txtDescripcion.setEditable(false);
        btnLimpiar.setDisable(true);
        btnGuardar.setDisable(true);
        btnCargarIMG.setDisable(true);
    }
}
