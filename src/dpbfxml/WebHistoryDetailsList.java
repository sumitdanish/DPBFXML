/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author SUMMITM
 */
@XmlRootElement
public class WebHistoryDetailsList {
    private ArrayList<WebHistoryBeans> historyList = new ArrayList<>();
    public WebHistoryDetailsList(ArrayList<WebHistoryBeans> historyList)
    {
        this.historyList = historyList;
    }
    
    public WebHistoryDetailsList()
    {
        
    }

    public ArrayList<WebHistoryBeans> getHistoryList() {
        return historyList;
    }
    
    @XmlElement
    public void setHistoryList(ArrayList<WebHistoryBeans> historyList) {
        this.historyList = historyList;
    }
    
    
}
