/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Conexion.procedure = Conexion.connection.prepareCall("{call listCategorias()}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next()){
                cmbCategorias.getItems().add(Conexion.result.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }    

    @FXML
    private void mostrarInformacion(ActionEvent event) throws SQLException {
        lblNombre.setText("");
        lblRestaurante.setText("");
        lblCateogoria.setText("");
        txtDescripcion.clear();
        lstRestaurante.getItems().clear();
        txtIngredientes.clear();
        String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
        Conexion.procedure = Conexion.connection.prepareCall("{call mostrarPlatillo('" + nombrePla + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        while (Conexion.result.next()){
            
        }
        
    }

    @FXML
    private void cargarListView(ActionEvent event) throws SQLException{
        lstPlatillos.getItems().clear();
        String categoria = (String) cmbCategorias.getValue();
        Conexion.procedure = Conexion.connection.prepareCall("{call mostrarPlatillos('" + categoria + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        while (Conexion.result.next()){
            lstPlatillos.getItems().add(Conexion.result.getString(1));
        }
    }
    
}
