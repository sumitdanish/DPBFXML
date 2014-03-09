/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author SUMMITM
 */
public class DPBFXML extends Application {
    
    static public final String DEFAULT_STYLESHEET = "skin/undecorator.css";
    static public final String DEFAULT_STYLESHEET_UTILITY = "skin/undecoratorUtilityStage.css";
    final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    @Override
    public void start(Stage stage) throws Exception {
        
        Scene scene = new Scene(new WebViewRegion(primaryScreenBounds),600,400);
        stage.setTitle("Hello World!");
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        //stage.setMinWidth(primaryScreenBounds.getWidth()); 
        //stage.setMaxHeight(primaryScreenBounds.getHeight()); 
        File f = StoringWebHistory.getStoringWebHistory().getCreateHistoryDirectory();
        //f.mkdir();
        stage.setScene(scene);
       // stage.getScene().getStylesheets().add(DEFAULT_STYLESHEET);
       stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
