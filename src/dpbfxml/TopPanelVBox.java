/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import javafx.scene.layout.VBox;

/**
 *
 * @author SUMMITM
 */
public class TopPanelVBox {
    
    private VBox vBox;

    private TopGridPane topGridPane;
    public TopPanelVBox()
    {
        vBox = new VBox();
        topGridPane = new TopGridPane();
        vBox.getChildren().add(topGridPane.getGridPane());
    }
    
    
    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public TopGridPane getTopGridPane() {
        return topGridPane;
    }

    public void setTopGridPane(TopGridPane topGridPane) {
        this.topGridPane = topGridPane;
    }
    
    
}
