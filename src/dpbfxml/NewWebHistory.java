/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;

/**
 *
 * @author SUMMITM
 */

public class NewWebHistory {
    private final WebHistory webHistory;
    private final ObservableList<WebHistory.Entry> webObservableList;

    
    
    public NewWebHistory(WebView webView)
    {
        webHistory = webView.getEngine().getHistory();
        webObservableList = webHistory.getEntries();
    }

    public WebHistory getWebHistory() {
        return webHistory;
    }

   
    
    public ObservableList getWebObservableList() {
        return webObservableList;
    }

    
    public ListChangeListener getListChangeListener(WebView webView,ComboBox urlTextfield)
    {
        ListChangeListener listChangeListener;
        listChangeListener = new ListChangeListener<WebHistory.Entry>() {
            
            @Override
            public void onChanged(Change<? extends Entry> c) 
            {
                c.next();
                for (Entry e : c.getRemoved())
                {
                    urlTextfield.getItems().remove(e.getUrl());
                }
                for (Entry e : c.getAddedSubList())
                {
                    urlTextfield.getItems().add(e.getUrl());
                }
            }
        };
        return listChangeListener;
    }
}
