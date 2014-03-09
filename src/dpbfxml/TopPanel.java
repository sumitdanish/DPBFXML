/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author SUMMITM
 */
public final class TopPanel {

    private BorderPane borderPane;
    private GridPane gridPane;
    private TopPanelVBox topPanelVBox;
    private ToolBar topToolBar;
    public TopPanel(TabPane tabPane) {
        borderPane = new BorderPane();
        topPanelVBox = new TopPanelVBox();
        topToolBar = new TopBorderPaneToolBar(borderPane).getTopBorderPaneToolBar();
        borderPane.setTop(getTopToolBar());
        borderPane.setCenter(getTopPanelVBox().getvBox()); 
        
    }

    public ToolBar getTopToolBar() {
        return topToolBar;
    }

    public void setTopToolBar(ToolBar topToolBar) {
        this.topToolBar = topToolBar;
    }

    
    
    
    public TopPanelVBox getTopPanelVBox() {
        return topPanelVBox;
    }

    public void setTopPanelVBox(TopPanelVBox topPanelVBox) {
        this.topPanelVBox = topPanelVBox;
    }

    
    public BorderPane getBorderPane() {
        return borderPane;
    }

   
    
    
    
    
    
}
