package espol.edu.ec.controllers;

import espol.edu.ec.pane.PaneModulo1;
import espol.edu.ec.registro.ModuloRegistro;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OptionsWindow_Controller {

    private Parent rootProcesamiento;
    private Stage addTurnStage, adminRegStage, stageProcesamiento;

    @FXML
    void handle_addTurn() throws IOException {

        addTurnStage = new Stage();
        addTurnStage.setResizable(false);
        addTurnStage.initModality(Modality.APPLICATION_MODAL);
        addTurnStage.initStyle(StageStyle.UTILITY); 
        addTurnStage.setScene(new Scene(new PaneModulo1().getRoot()));
        addTurnStage.show();
    }


    @FXML
    void handle_AdminReg() throws IOException {
        
        adminRegStage = new Stage();
        new ModuloRegistro().pantalla(adminRegStage);
        adminRegStage.show();
    }

    @FXML
    void handle_showModuloProcesamiento()throws Exception{
        stageProcesamiento=new Stage();
        stageProcesamiento.setTitle("Contador de Registros");

        rootProcesamiento = FXMLLoader.load(getClass().getResource("../moduloProcesamiento/ModuloProcesamiento.fxml"));
        stageProcesamiento.setScene(new Scene(rootProcesamiento));

        stageProcesamiento.show();
    }
}
