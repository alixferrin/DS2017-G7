/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

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
    private ListView<?> lstCategoria;
    @FXML
    private ComboBox<?> cmbRestaurante;
    @FXML
    private ListView<?> lstPlatillos;
    @FXML
    private CheckBox cboxJugo;
    @FXML
    private CheckBox cboxPostre;
    @FXML
    private Button btnOrdenar;
    @FXML
    private Button btnOrdenar1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrarInformacion(ActionEvent event) {
    }

    @FXML
    private void cargarListView(ActionEvent event) {
    }
    
}
