/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author SUMMITM
 */
@XmlRootElement
public class WebHistoryBeans {
    
    private String url;
    private String title;
    private String date;
    public WebHistoryBeans()
    {
        
    }

    public String getUrl() {
        return url;
    }
    
       @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

       @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

       @XmlElement
    public void setDate(String date) {
        this.date = date;
    }  
}
