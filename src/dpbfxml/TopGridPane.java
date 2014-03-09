/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author SUMMITM
 */
public class TopGridPane {
    
    private GridPane gridPane;

    public TopGridPane() {
        
        gridPane = new GridPane();
        Button b = new Button("KJHKJ");
        Button b1 = new Button("KJHKJ");
        gridPane.setConstraints(b, 1,1);
        gridPane.setConstraints(b1, 2,1);
        gridPane.getChildren().addAll(b,b1);
        
    }

    
    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }
    
}
