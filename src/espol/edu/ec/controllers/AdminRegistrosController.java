package espol.edu.ec.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by MiguelPS on 24-Jun-18.
 */
public class AdminRegistrosController {

    Stage adminRegStage;

    @FXML
    void add(){

    }

    @FXML
    void edit(){

    }

    @FXML
    void remove(){

    }

    @FXML
    void cancelar(){
        adminRegStage.close();
    }

    public Stage getAdminRegStage() {
        return adminRegStage;
    }

    public void setAdminRegStage(Stage adminRegStage) {
        this.adminRegStage = adminRegStage;
    }
}
