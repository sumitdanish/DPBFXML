/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import com.aquafx_project.AquaFx;
import com.aquafx_project.controls.skin.styles.ButtonType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
/**
 *
 * @author SUMMITM
 */
public class AddNewToolBar{
    private final Button goButton;
    private final Button backButton;
    private final Button forwardButton;
    private final Button refreshButton;
    private final ComboBox urlTextfield;
    private final ToolBar toolBar;
    private TabPane tabPane;
    private final ProgressIndicator progressIndicator;
    private final WebHistory webHistory;
    private WebViewRegion webViewRegion;
    private final NewWebHistory newWebHistory ;
//    private String buttonStyle =    " -fx-background-color: \n" +
//                                    "    #b0c4de,\n" +
//                                    "    linear-gradient(#b0c4de 0%, #b0c4de 20%, #b0c4de 100%),\n" +
//                                    "    linear-gradient(#FFFFFF, #FFFFFF),\n" +
//                                    "    radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
//                                    "    -fx-text-fill: white;\n" +
//                                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
//                                    "    -fx-font-family: \"Arial\";\n" +
//                                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
//                                    "    -fx-font-size: 12px;\n";
    public AddNewToolBar(Rectangle2D primaryScreenBounds,WebView addNewWebView,WebViewRegion webViewRegion) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        newWebHistory = new NewWebHistory(addNewWebView);
        webHistory = newWebHistory.getWebHistory();
        this.webViewRegion = webViewRegion;
        progressIndicator = new ProgressIndicator(0);
        progressIndicator.setVisible(false); 
        progressIndicator.setPrefWidth(23);
        progressIndicator.setPrefHeight(28);
        goButton = new Button("Go");
        AquaFx.createButtonStyler().setType(ButtonType.ROUND_RECT).style(goButton); 
        forwardButton = new Button(">");
        AquaFx.createButtonStyler().setType(ButtonType.RIGHT_PILL).style(forwardButton); 
        backButton = new Button("<");
        AquaFx.createButtonStyler().setType(ButtonType.LEFT_PILL).style(backButton); 
        refreshButton = new Button("R");
        urlTextfield = new ComboBox();
        urlTextfield.setPrefWidth(getWebViewRegion().getPrefWidth()-(goButton.getWidth())); 
        urlTextfield.setEditable(true);
        urlTextfield.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds t, Bounds t1) {
                urlTextfield.prefWidthProperty().bind(getWebViewRegion().widthProperty().subtract((goButton.getWidth()+200))); 
            }
        });
        //urlTextfield.valueProperty().addListener(getComboboxEventHandler(addNewWebView));
        //addWebHistoryInTextField(urlTextfield);
        webHistory.getEntries().addListener(newWebHistory.getListChangeListener(addNewWebView, urlTextfield)); 
        toolBar = new ToolBar(backButton,forwardButton,refreshButton,urlTextfield,goButton,progressIndicator);
        goButton.setOnAction(getEvent(addNewWebView)); 
    }

    private EventHandler getEvent(WebView addNewWebView)
    {
        
        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                WebViewRegion weR = getWebViewRegion();
                BorderPane borderPane = (BorderPane)getWebViewRegion().lookup("#topBorderPanel");
               // GridPane vBoxBrdPane = (GridPane)getWebViewRegion().lookup("#topGridPane"); 
                borderPane.setVisible(false);
                //borderPane = null;
                //vBoxBrdPane.setVisible(false);
                WebEngine we = addNewWebView.getEngine();
                we.load(urlTextfield.getSelectionModel().getSelectedItem().toString()); 
                we.locationProperty().addListener(getChnagleListener(we));
            }
        };
        return eventHandler;        
    }
    
    public void addWebHistoryInTextField(ComboBox urlTextFiled) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList<WebHistoryBeans> webHistoryList = StoringWebHistory.getStoringWebHistory().readHistoryFromFile();
       if(webHistoryList!=null)
       {
            for(WebHistoryBeans web : webHistoryList)
            {
                System.out.println(" > > > ....."+web);
            }
       }
    }
    
    
    private ChangeListener getChnagleListener(WebEngine we)
    {
        ChangeListener changeListner = new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
               /// System.out.println(" > HI > "+we.getHistory().getEntries());
                 if(!(t.toString().equals(t1.toString())))
                 {
                     int offset = urlTextfield.getSelectionModel().getSelectedIndex()-webHistory.getCurrentIndex(); 
                     if(offset <=0)
                     {
                         try {
                             StoringWebHistory.getStoringWebHistory().writeHistoryInFile(t1.toString());
                             urlTextfield.setValue(t1.toString());
                         } catch (IOException ex) {
                             Logger.getLogger(AddNewToolBar.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                     
                 }
            }
        };
        return changeListner;
    }
    
    
    
 
    
    
    private ChangeListener getComboboxEventHandler(WebView addNewWebView)
    {
        ChangeListener comboBoxEventHandler = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String t, String t1) {

              
                
                BorderPane borderPane = (BorderPane)getWebViewRegion().lookup("#topBorderPanel");
                borderPane.setVisible(false);
                WebEngine we = addNewWebView.getEngine();
                we.load(t1); 
                we.locationProperty().addListener(getChnagleListener(we));
            }
        };
        return comboBoxEventHandler;
                
    }
    public ToolBar getToolBar() {
        return toolBar;
    }
    
    public void installWebViewer(WebView wv)
    {
        
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public WebViewRegion getWebViewRegion() {
        return webViewRegion;
    }

    public void setWebViewRegion(WebViewRegion webViewRegion) {
        this.webViewRegion = webViewRegion;
    }    
}
