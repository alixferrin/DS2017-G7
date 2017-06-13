/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasbares;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HOME
 */
public class Main extends Application {
    private static Parent root;
    private static Scene scene;
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        
        root = FXMLLoader.load(getClass().getResource("Loggin.fxml"));
        
        scene = new Scene(root);
        
        Main.stage.setScene(scene);
        Main.stage.setTitle("Ingreso");
        Main.stage.setResizable(false);
        Main.stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void logout() throws Exception{
        root = FXMLLoader.load(Main.class.getResource("Loggin.fxml"));
        
        scene = new Scene(root);
        
        Main.stage.setScene(scene);
        Main.stage.setTitle("Ingreso");
        Main.stage.centerOnScreen();
        Main.stage.show();
    }
}
