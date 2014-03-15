/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author SUMMITM
 */
public final class StoringWebHistory{
    private static String tempDirFOrWebHistory;
    private static File createHistoryDirectory;
    private static String tempWebHistoryDirectoryPath;
    private static File fileProperties;
    private static File searchDir;
    private static volatile StoringWebHistory storingWebHistory;
    private static boolean flag = true;
    private static final FileOutputStream fout =null;
    private static final  ObjectOutputStream oout = null;
    ArrayList<WebHistoryBeans> webHistoryList = new ArrayList<>();
    private File historyFile = null;
    private ArrayList<WebHistoryBeans> webHistoryBeansList = new ArrayList<>();
    private StoringWebHistory() throws IOException
    {
        tempDirFOrWebHistory = System.getProperty("java.io.tmpdir");
        searchDir = new File(tempDirFOrWebHistory);
        for(File f : searchDir.listFiles())
        {
                if((f.getName().equals("DPWebHIstory")))
                {
                    flag=false;
                    break;
                }
        }
        if(flag)
        {
            createHistoryDirectory = new File(tempDirFOrWebHistory, "DPWebHIstory");
            createHistoryDirectory.mkdir();
            
            
        }
        
        
        //System.out.println("Calling..."+createHistoryDirectory.mkdir());
       // fileProperties=File.createTempFile("url","dp", createHistoryDirectory);
       
    }
    public void writeHistoryInFile(WebHistory wh) throws FileNotFoundException, IOException
    {
        historyFile = new File(tempDirFOrWebHistory+File.separator+"DPWebHIstory"+File.separator+"urlHistory.xml");
        
        ObservableList<Entry> ol = wh.getEntries();
        WebHistoryBeans webHistoryBeans = new WebHistoryBeans();
        ol.stream().forEach(new Consumer<Entry>() {
            public void accept(Entry e) {
                webHistoryBeans.setUrl(e.getUrl());
                webHistoryBeans.setTitle(e.getTitle());
                webHistoryBeans.setDate(String.valueOf((Object)e.getLastVisitedDate()));
                webHistoryBeansList.add(webHistoryBeans);
            }
        });
        writeHistoryInFile(webHistoryBeansList,historyFile);
    }
    private void writeHistoryInFile(ArrayList<WebHistoryBeans> webHistoryBeanse,File historyFile)
    {
        marshling(historyFile, webHistoryBeanse);
    }
    public ArrayList<WebHistoryBeans> readHistoryFromFile() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        
        
        if(!historyFile.exists())
        {
            return null;
        }
        else
        {
            Object obj = null;
            FileInputStream fin = new FileInputStream(historyFile); 
            ObjectInputStream oin = new ObjectInputStream(fin);
            while(((obj=oin.readObject())!=null) && (oin.available()!=0))
            {
                if(obj instanceof WebHistoryBeans)
                {
                    WebHistoryBeans webHis = (WebHistoryBeans)obj;
                    webHistoryList.add(webHis);
                }
                
            }
            oin.close();
            
            //WebHistoryBeans entryList = (WebHistoryBeans)oin.readObject();
            return webHistoryList;
        }
    }
    
    private void marshling(File f,ArrayList<WebHistoryBeans> webHistoryBeanse)
    {
        try
        {
            BufferedWriter writer = null;
            writer = new BufferedWriter(new FileWriter(f));
            JAXBContext aXBContext = JAXBContext.newInstance(WebHistoryDetailsList.class);
            Marshaller marshaller = aXBContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new WebHistoryDetailsList(webHistoryBeanse),writer); 
            writer.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public String createWebHistoryDirectory()
    {
        return null;
    }

    public String getTempDirFOrWebHistory() {
        return tempDirFOrWebHistory;
    }

    public void setTempDirFOrWebHistory(String tempDirFOrWebHistory) {
        this.tempDirFOrWebHistory = tempDirFOrWebHistory;
    }

    public File getCreateHistoryDirectory() {
        return createHistoryDirectory;
    }

    public void setCreateHistoryDirectory(File createHistoryDirectory) {
        StoringWebHistory.createHistoryDirectory = createHistoryDirectory;
    }

    public String getTempWebHistoryDirectoryPath() {
        return tempWebHistoryDirectoryPath;
    }

    public void setTempWebHistoryDirectoryPath(String tempWebHistoryDirectoryPath) {
        StoringWebHistory.tempWebHistoryDirectoryPath = tempWebHistoryDirectoryPath;
    }

    public File getFileProperties() {
        return fileProperties;
    }

    public void setFileProperties(File fileProperties) {
        StoringWebHistory.fileProperties = fileProperties;
    }

    public static synchronized StoringWebHistory getStoringWebHistory() throws IOException {
        if(storingWebHistory == null)
        {
           storingWebHistory = new StoringWebHistory();
        }
        return storingWebHistory;
    }

    
    
    
    
}
