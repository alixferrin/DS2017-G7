/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class ClienteController implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuCliente;
    @FXML
    private MenuItem menuCategoria;
    @FXML
    private Menu menuPlatos;
    @FXML
    private MenuItem menuMostrarPlatos;
    @FXML
    private MenuItem menuMostrarPlatillos;
    @FXML
    private Menu menuBuscar;
    @FXML
    private Menu menuLogOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
