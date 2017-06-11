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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
    private void mostrarInformacion(ActionEvent event) throws SQLException, FileNotFoundException {
        this.limpiar();
        String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
        Conexion.procedure = Conexion.connection.prepareCall("{call getInfoPla('" + nombrePla + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        Conexion.result.next();
        lblNombre.setText(Conexion.result.getString(1));
        lblCateogoria.setText(Conexion.result.getString(3));
        txtDescripcion.setText(Conexion.result.getString(2));
        txtIngredientes.setText(Conexion.result.getString(5));
        Image imagen = new Image(new FileInputStream("imgs\\" + Conexion.result.getString(4)));
        imgImagen.setImage(imagen);
        Conexion.procedure = Conexion.connection.prepareCall("{call getRest('" + nombrePla + "')}");
        Conexion.result = Conexion.procedure.executeQuery();
        while (Conexion.result.next()){
            lstRestaurante.getItems().add(Conexion.result.getString(1));
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
    
    private void limpiar(){
        lblNombre.setText("");
        lblCateogoria.setText("");
        txtDescripcion.clear();
        lstRestaurante.getItems().clear();
        txtIngredientes.clear();
        imgImagen.setImage(null);
    }
    
}
