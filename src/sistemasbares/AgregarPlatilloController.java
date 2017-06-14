/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class AgregarPlatilloController implements Initializable {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private ComboBox cmbServido;
    @FXML
    private ComboBox cmbTipo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private ImageView imgImagen;
    @FXML
    private Button cargarImagen;
    @FXML
    private TextField txtTemp;
    @FXML
    private ComboBox cmbRestaurante;
    
    private String[] datosImagen = {"",""};
    File foto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Conexion.procedure = Conexion.connection.prepareCall("{call getTipos()}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next())
                cmbTipo.getItems().add(Conexion.result.getString(1));
            Conexion.procedure = Conexion.connection.prepareCall("{call getServido()}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next())
                cmbServido.getItems().add(Conexion.result.getString(1));
            Conexion.procedure = Conexion.connection.prepareCall("{call getRestDeASIS('" + Conexion.asisRest + "')}");
            Conexion.result = Conexion.procedure.executeQuery();
            while (Conexion.result.next())
                cmbRestaurante.getItems().add(Conexion.result.getString(1));
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }    

    @FXML
    private void guardarPlatillo(ActionEvent event) {
        if (!this.datosImagen[0].equals("") || this.foto != null){
            try{
                String IDRest = "";
                String IDPlat = "";
                Conexion.procedure = Conexion.connection.prepareCall("{call nuevoPlatillo('" + txtNombre.getText().toUpperCase() + "','" + txtDescripcion.getText().toUpperCase() + "','" + txtIngredientes.getText().toUpperCase() + "','" + txtCategoria.getText().toUpperCase() + "','" + txtTemp.getText() + "','" + datosImagen[0] + "','" + (String)cmbTipo.getValue() + "','" + (String)cmbServido.getValue() + "')}");
                Conexion.procedure.execute();
                Conexion.procedure = Conexion.connection.prepareCall("{call getIDRest('" + (String)cmbRestaurante.getValue() + "')}");
                Conexion.result = Conexion.procedure.executeQuery();
                Conexion.result.next();
                IDRest = Conexion.result.getString(1);
                Conexion.procedure = Conexion.connection.prepareCall("{call getPlatID('" + txtNombre.getText() + "')}");
                Conexion.result = Conexion.procedure.executeQuery();
                Conexion.result.next();
                IDPlat = Conexion.result.getString(1);
                Conexion.procedure = Conexion.connection.prepareCall("{call insertMenu('" + IDPlat + "','" + IDRest + "')}");
                Conexion.procedure.execute();
                Path FROM = Paths.get(this.foto.getAbsolutePath());
                Path TO = Paths.get("imgs\\" + this.foto.getName());
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(FROM, TO, options);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Agregar nuevo platillo");
                alert.setHeaderText(null);
                alert.setContentText("Los datos han sido guardados exitosamente.");
                alert.showAndWait();
                this.limpiar();
            }catch (SQLException sql){
                sql.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Agregar nuevo platillo");
            alert.setHeaderText(null);
            alert.setContentText("Todos los campos deben de ser ingresados, incluyendo la imagen.");
            alert.showAndWait();
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        this.limpiar();
    }

    @FXML
    private void cargarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG"); 
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        
        File file = fileChooser.showOpenDialog(null);
        
        String name = file.getName();
        String rutaImagen = file.getAbsolutePath();
       
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
            Image img = SwingFXUtils.toFXImage(bufferedImage, null);
            imgImagen.setImage(img);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        
        this.datosImagen[0] = name;
        this.datosImagen[1] = rutaImagen;
        this.foto = file;
    }
    
    private void limpiar(){
        txtNombre.setText("");
        txtCategoria.setText("");
        txtDescripcion.setText("");
        txtIngredientes.setText("");
        imgImagen.setImage(null);
        txtTemp.setText("");
    }
    
}
