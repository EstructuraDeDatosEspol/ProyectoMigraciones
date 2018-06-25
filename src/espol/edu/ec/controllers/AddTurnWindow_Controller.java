package espol.edu.ec.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Created by MiguelPS on 23-Jun-18.
 */
public class AddTurnWindow_Controller {

    Parent rootTurnShower;
    Parent rootAddTurn;

    @FXML
    void addTurn(){

    }

    public void setRootTurnShower(Parent rootTurnShower) {
        this.rootTurnShower = rootTurnShower;
    }

    public void setRootAddTurn(Parent rootAddTurn) {
        this.rootAddTurn = rootAddTurn;
    }

    public Parent getRootTurnShower() {
        return rootTurnShower;
    }

    public Parent getRootAddTurn() {
        return rootAddTurn;
    }


}
