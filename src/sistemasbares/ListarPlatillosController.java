/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Stream;
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
    private void guardarFoto(ActionEvent event){
    
        //instanciando ventana emergente
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG"); 
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        
        File file = fileChooser.showOpenDialog(null);
        
        String name = file.getName();
        System.out.println(name);
        String rutaImagen = file.getAbsolutePath();
        System.out.print(rutaImagen);
        
        this.datosImagen[0] = name;
        this.datosImagen[1] = rutaImagen;
        
        this.foto = file;
        
    }
    
    @FXML
    private void modificarPlatillo(ActionEvent event){
         
        try{
            
            /*Conexion.procedure = Conexion.connection.prepareCall("{Call modificarPlatillo('" + this.id_plato + "','" + this.txtNombre.getText()
                                                                    + "','" + this.txtDescripcion.getText() + "','" + this.txtCategoria.getText() 
                                                                + "','" + this.datosImagen[0] + "','" + this.txtIngredientes.getText() +"')}");
            Conexion.result = Conexion.procedure.executeQuery();*/
            Path FROM = Paths.get(this.foto.getAbsolutePath());
            Path TO = Paths.get("imgs\\" + this.foto.getName());
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(FROM, TO, options);
            
            System.out.println("COPIADO");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    } 
}
