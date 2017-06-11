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
public class ListarPlatillosController implements Initializable {
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
            Conexion.procedure = Conexion.connection.prepareCall("{call listPlatillos('" + Conexion.asisRest + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next()){
                lstPlatillos.getItems().add(Conexion.result.getString(1));
            }
        }catch (SQLException e){
            
        }
    }    

    @FXML
    private void mostrarInfo(ActionEvent event) throws SQLException, FileNotFoundException {
        btnModificar.setDisable(false);
        this.limpiar();
        String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
        Conexion.procedure = Conexion.connection.prepareCall("{call getInfoPla('" + nombrePla + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        Conexion.result.next();
        txtNombre.setText(Conexion.result.getString(1));
        txtCategoria.setText(Conexion.result.getString(3));
        txtDescripcion.setText(Conexion.result.getString(2));
        txtIngredientes.setText(Conexion.result.getString(5));
        Image imagen = new Image(new FileInputStream(Conexion.result.getString(4)));
        imgImagen.setImage(imagen);
        Conexion.procedure = Conexion.connection.prepareCall("{call getRestASIS('" + Conexion.asisRest + "','" + nombrePla + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        while (Conexion.result.next()){
            lstRestaurante.getItems().add(Conexion.result.getString(1));
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
}
