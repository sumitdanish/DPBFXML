/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 *
 * @author SUMMITM
 */
public class TopPanelComponentInWebView {
    
    
    private static TopPanelComponentInWebView topPanelComponentInWebView;
    private BorderPane pane = null;
    
    private TopPanelComponentInWebView() throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopComponent.fxml"));
        fxmlLoader.setController(this);
        pane = (BorderPane) fxmlLoader.load();
    }

    public static synchronized  TopPanelComponentInWebView getTopPanelComponentInWebView() throws IOException {
        if(topPanelComponentInWebView == null)
        {
            topPanelComponentInWebView = new TopPanelComponentInWebView();
        }
        return topPanelComponentInWebView;
    }

    public BorderPane getRoot() {
        //pane.getChildren().add(new Button("lklk"));
        return pane;
    }
    
    
    
    
   
    
}
