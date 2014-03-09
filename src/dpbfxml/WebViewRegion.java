/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author SUMMITM
 */
public final class WebViewRegion extends Region
{
    private final TabPane tabPane;
    private final Tab tab1;
    private final Tab clickNewTab;
    private final ToolBar bottomToolBar; 
    private final VBox vBox;
    StackPane staPane = new StackPane();
    private final MenuBar globalMenuBar;
    private Rectangle2D primaryScreenBounds;
    private Stage stage;
    public WebViewRegion(Rectangle2D primaryScreenBounds) 
    {
        this.primaryScreenBounds = primaryScreenBounds;
        getStylesheets().add("JFXCSS.css");
        applyCss();
        globalMenuBar = new MenuBar();
        globalMenuBar.getMenus().add(new Menu("File"));
        vBox = new VBox();
        tabPane = new TabPane();
        tab1 = new Tab("New Tab");
        clickNewTab = new Tab("+");
        bottomToolBar = new ToolBar();
        bottomToolBar.setEffect(new Reflection());
        bottomToolBar.setPrefWidth(primaryScreenBounds.getWidth());
        bottomToolBar.setPrefHeight(40); 
        addNewTab();
        bottomToolBar.getStyleClass().add("decoration-tool");
        tabPane.setPrefHeight(primaryScreenBounds.getHeight()-90);
        tabPane.setPrefWidth(primaryScreenBounds.getWidth());
        layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {

            @Override 
            public void changed(ObservableValue<? extends Bounds> ov, Bounds t, Bounds t1) {
                bottomToolBar.setPrefHeight(40); 
                vBox.setPrefSize(ov.getValue().getWidth(), ov.getValue().getHeight());
                
            }
        });
        staPane.getChildren().add(bottomToolBar);
        tabPane.getTabs().addAll(tab1,clickNewTab);
        vBox.getChildren().addAll(globalMenuBar,tabPane,staPane);
        getChildren().add(vBox);
        
    }
    
    public void addNewTab()
    {
        AddNewTab addNewTab = new AddNewTab();
        tabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldSelectedTab, Tab newSelectedTab) -> 
             {
                 if (newSelectedTab == clickNewTab)
                 {
                     try
                     {
                         AddNewWebView addNewWebView = AddNewWebView.getAddNewWebView();
                         WebView wv = addNewWebView.getWebView();
                         
                         addNewTab.createAndSelectNewTab(tabPane, "New Tab " + (tabPane.getTabs().size()),vBox,getPrimaryScreenBounds(),wv,this);
                     } 
                     catch (MalformedURLException ex) 
                     {
                         Logger.getLogger(WebViewRegion.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(WebViewRegion.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (ClassNotFoundException ex) {
                         Logger.getLogger(WebViewRegion.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 
             });
    }
    
    public Rectangle2D getPrimaryScreenBounds() {
        return primaryScreenBounds;
    }

    public void setPrimaryScreenBounds(Rectangle2D primaryScreenBounds) {
        this.primaryScreenBounds = primaryScreenBounds;
    }
    
    
    
}
