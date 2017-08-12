/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import TDAs.Conexion;

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
    
    private String[] datosImagen = {"",""};
    private String id_plato = "";
    File foto;
    Conexion conexion = Conexion.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtDescripcion.setWrapText(true);
        try{
            conexion.setProcedure("{call listPlatillos('" + Conexion.asisRest + "')}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado()){
                lstPlatillos.getItems().add(conexion.getResultFila(1));
            }
        }catch (SQLException e){
            
        }
    }    

    @FXML
    private void mostrarInfo(ActionEvent event) throws SQLException, FileNotFoundException {
        try{
            String nombrePla = (String) lstPlatillos.getSelectionModel().getSelectedItem();
            btnModificar.setDisable(false);
            this.limpiar();
            this.deshabilitar();
            conexion.setProcedure("{call getRestASIS('" + Conexion.asisRest + "','" + nombrePla + "')}");
            conexion.ejecutarQuery();
            while (conexion.iterarResultado()){
                lstRestaurante.getItems().add(conexion.getResultFila(1));
            }
            conexion.setProcedure("{call getInfoPla('" + nombrePla + "')}");
            conexion.ejecutarQuery();
            conexion.iterarResultado();
            this.id_plato = conexion.getResultFila(1);
            txtNombre.setText(conexion.getResultFila(2));
            txtCategoria.setText(conexion.getResultFila(4));
            txtDescripcion.setText(conexion.getResultFila(3));
            txtIngredientes.setText(conexion.getResultFila(6));
            String img = conexion.getResultFila(5);
            this.datosImagen[0] = img;
            Image imagen = new Image(new FileInputStream("imgs\\" + img));
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
    
    @FXML
    private void cargarFoto(ActionEvent event){
    
        //instanciando ventana emergente
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
    
    @FXML
    private void modificarPlatillo(ActionEvent event){
        try{
            conexion.setProcedure("{call modificarPlatillo('" + this.id_plato + "','" + this.txtNombre.getText().toUpperCase() + "','" + this.txtDescripcion.getText().toUpperCase() + "','" + this.txtCategoria.getText().toUpperCase() + "','" + this.datosImagen[0] + "','" + this.txtIngredientes.getText().toUpperCase() +"')}");
            conexion.getProcedure().execute();
            
            if (this.foto != null){
                Path FROM = Paths.get(this.foto.getAbsolutePath());
                Path TO = Paths.get("imgs\\" + this.foto.getName());
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(FROM, TO, options);
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modificar datos del platillo");
            alert.setHeaderText(null);
            alert.setContentText("Los datos han sido guardados exitosamente.");
            alert.showAndWait();
            
            this.limpiar();
            btnModificar.setDisable(true);
            this.deshabilitar();
            
        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
