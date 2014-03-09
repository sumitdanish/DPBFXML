/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author SUMMITM
 */
public class TopBorderPaneToolBar {
    
    
    private ToolBar topBorderPaneToolBar;
    private Scene scene;//
    public TopBorderPaneToolBar(BorderPane borderPane)
    {
        Region region = new Region();
        //HBox hBox = new HBox();
        HBox.setHgrow(region, Priority.ALWAYS);
        SplitMenuButton splitMenuButton = new SplitMenuButton();
        splitMenuButton.setId("splitMenuButton");
        topBorderPaneToolBar = new ToolBar(); 
        topBorderPaneToolBar.applyCss();
        topBorderPaneToolBar.getStylesheets().add("JFXCSS.css");
        topBorderPaneToolBar.setId("tool-bar"); 
        //topBorderPaneToolBar.setStyle("tool-bar"); 
        //topBorderPaneToolBar.setStyle("-fx-background-color:#FFFFFF;");
        topBorderPaneToolBar.getItems().addAll(region,splitMenuButton);
    } 

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    
    
    public ToolBar getTopBorderPaneToolBar() {
        return topBorderPaneToolBar;
    }

    public void setTopBorderPaneToolBar(ToolBar topBorderPaneToolBar) {
        this.topBorderPaneToolBar = topBorderPaneToolBar;
    }
    
    
}
