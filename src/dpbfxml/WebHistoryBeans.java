/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.io.Serializable;
import javafx.collections.ObservableList;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;

/**
 *
 * @author SUMMITM
 */
public final class WebHistoryBeans implements Serializable{
    private ObservableList<WebHistory.Entry> urlList;
    private transient WebEngine webEngine;
    private String url;
    
    public WebHistoryBeans()
    {
       // this.webEngine = we;
        
    }
    
    public ObservableList<WebHistory.Entry> getUrlList() {
        return urlList;
    }

    public void setUrlList(ObservableList<WebHistory.Entry> urlList) {
        
        this.urlList = urlList;
    }
    
    
    @Override
    public String toString()
    {
        return this.getUrl();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
  
    
    
    
}
