package espol.edu.ec.controllers;

import espol.edu.ec.main.Main;
import espol.edu.ec.registro.ModuloRegistro;
import espol.edu.ec.tda.ModuloTurno;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OptionsWindow_Controller {

    private Parent rootAddTurn,rootAdminReg;
    private Stage addTurnStage, adminRegStage;

    @FXML
    void handle_addTurn() throws IOException {
        rootAddTurn= FXMLLoader.load(getClass().getResource("../views/AddTurnWindow.fxml"));
        addTurnStage = new Stage();
        addTurnStage.setScene(new Scene(new ModuloTurno(Main.getRootTurnShower()).getPane()));
        addTurnStage.show();
    }


    @FXML
    void handle_AdminReg() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/AdminRegistrosWindow.fxml"));
        rootAdminReg = loader.load();
        
        adminRegStage = new Stage();
        new ModuloRegistro().pantalla(adminRegStage);
        //adminRegStage.setScene(new Scene(rootAdminReg));
        adminRegStage.show();

        AdminRegistrosController controller = loader.getController();
        controller.setAdminRegStage(adminRegStage);
    }

    @FXML
    void handle_showProv(){

    }

    public Parent getRootAddTurn() {
        return rootAddTurn;
    }

    public Stage getAddTurnStage() {
        return addTurnStage;
    }

    public Stage getAdminRegStage() {
        return adminRegStage;
    }

    public void setRootAddTurn(Parent rootAddTurn) {
        this.rootAddTurn = rootAddTurn;
    }
}
