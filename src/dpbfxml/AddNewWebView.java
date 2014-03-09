/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author SUMMITM
 */
public class AddNewWebView{
    private static WebView webView;
    private static WebEngine webEngine;
    private static String webUrl;
    private static AddNewWebView addNewWebView;
    private AddNewWebView() {
        
    }

    public static AddNewWebView getAddNewWebView() {
        if(addNewWebView == null)
        {
            addNewWebView = new AddNewWebView();
        }
        return addNewWebView;
    }

    public static void setAddNewWebView(AddNewWebView addNewWebView) {
        AddNewWebView.addNewWebView = addNewWebView;
    }

    
    
    public WebEngine getWebEngine() {
        return webEngine;
    }

    public void setWebEngine(WebEngine webEngine) {
        this.webEngine = webEngine;
    }

    
    public WebView getWebView() {
        
        webView = new WebView();
        webEngine = webView.getEngine();
        return webView; 
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    
}
