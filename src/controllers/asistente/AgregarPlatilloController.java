/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.asistente;

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
import TDAs.Conexion;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class AgregarPlatilloController implements Initializable {
    @FXML
    private TextField txtNombre;
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
    @FXML
    private ComboBox cmbCategorias;
    
    private String[] datosImagen = {"",""};
    File foto;
    Conexion conexion = Conexion.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            conexion.setProcedure("{call getTipos()}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado())
                cmbTipo.getItems().add(conexion.getResultFila(1));
            conexion.setProcedure("{call getServido()}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado())
                cmbServido.getItems().add(conexion.getResultFila(1));
            conexion.setProcedure("{call getRestDeASIS('" + Conexion.asisRest + "')}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado())
                cmbRestaurante.getItems().add(conexion.getResultFila(1));
            conexion.setProcedure("{call listCatASIS('" + Conexion.asisRest + "')}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado())
                cmbCategorias.getItems().add(conexion.getResultFila(1));
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }    

    @FXML
    private void guardarPlatillo(ActionEvent event) {
        double precio;
        if (!this.datosImagen[0].equals("") || this.foto != null){
            String categoria = (String) cmbCategorias.getValue();
            if (categoria.equals("EJECUTIVO"))
                precio = 3.0;
            else
                precio = 2.50;
            try{
                String IDRest = "";
                String IDPlat = "";
                conexion.setProcedure("{call nuevoPlatillo('" + txtNombre.getText().toUpperCase() + "','" + txtDescripcion.getText().toUpperCase() + "','" + 
                        txtIngredientes.getText().toUpperCase() + "','" + categoria + "','" + txtTemp.getText() + "','" + datosImagen[0] + "','" + 
                        (String)cmbTipo.getValue() + "','" + (String)cmbServido.getValue() + "'," + precio + ")}");
                conexion.ejecutarQuery();
                conexion.setProcedure("{call getIDRest('" + (String)cmbRestaurante.getValue() + "')}");
                conexion.ejecutarQuery();
                conexion.iterarResultado();
                IDRest = conexion.getResultFila(1);
                conexion.setProcedure("{call getPlatID('" + txtNombre.getText() + "')}");
                conexion.ejecutarQuery();
                conexion.iterarResultado();
                IDPlat = conexion.getResultFila(1);
                conexion.setProcedure("{call insertMenu('" + IDPlat + "','" + IDRest + "')}");
                conexion.iterarResultado();
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
        txtDescripcion.setText("");
        txtIngredientes.setText("");
        imgImagen.setImage(null);
        txtTemp.setText("");
    }
    
}
