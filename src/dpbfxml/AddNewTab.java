/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.io.IOException;
import java.net.MalformedURLException;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 *
 * @author SUMMITM
 */
public class AddNewTab{
     private Rectangle2D primaryScreenBounds;
    public AddNewTab()
    {
        
    }
    
    public Tab createAndSelectNewTab(final TabPane tabPane, final String title,final VBox vBox,Rectangle2D primaryScreenBounds,WebView addWebView,WebViewRegion webViewRegion) throws MalformedURLException, IOException, ClassNotFoundException
    {
         Tab tab = new Tab(title);    
         tab.setClosable(true);       
         tab.setContent(getTabContent(vBox, primaryScreenBounds, addWebView, webViewRegion,tabPane));   
         final ObservableList<Tab> tabs = tabPane.getTabs();
         tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));     
         tabs.add(tabs.size() - 1, tab);
         tabPane.getSelectionModel().select(tab);
         return tab;
    }
    private Node getTabContent(VBox vBox,Rectangle2D primaryScreenBounds,WebView addWebView,WebViewRegion webViewRegion,final TabPane tabPane) throws IOException, IOException, IOException, ClassNotFoundException
    {
        this.primaryScreenBounds = primaryScreenBounds;
        BorderPane topBorderPane = new TopPanel(tabPane).getBorderPane();
        StackPane st = new StackPane();
        st.setId("topPanel"); 
        topBorderPane.setId("topBorderPanel"); 
        ToolBar topToolBar = new AddNewToolBar(getPrimaryScreenBounds(),addWebView,webViewRegion).getToolBar();
        topBorderPane.setStyle("-fx-arc-width:5;-fx-background-radius:10;-fx-arc-height:5;-fx-background-color: cornsilk; -fx-border-width:5; -fx-border-color: linear-gradient(to bottom, chocolate, derive(chocolate, 5%));");
        topBorderPane.setEffect(new DropShadow());
        st.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
           @Override
            public void changed(ObservableValue<? extends Bounds> arg0, Bounds arg1, Bounds arg2) {
                st.prefHeightProperty().bind(tabPane.heightProperty().subtract(topToolBar.getPrefHeight()));
            } 
        });
        st.getChildren().add(addWebView);
        st.getChildren().add(topBorderPane);
        VBox tabVbox = new VBox(); 
        tabVbox.setPrefWidth(vBox.getWidth());
        tabVbox.getChildren().add(topToolBar);
        tabVbox.getChildren().add(st);
       // tabVbox.getChildren().add(st);
        return tabVbox;
    }

    public Rectangle2D getPrimaryScreenBounds() {
        return primaryScreenBounds;
    }

    public void setPrimaryScreenBounds(Rectangle2D primaryScreenBounds) {
        this.primaryScreenBounds = primaryScreenBounds;
    }
    
}
