package espol.edu.ec.main;

import java.io.IOException;

import espol.edu.ec.pane.PaneScreenTurnos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage turnsWindow;
    Parent rootOptions;
    private static final PaneScreenTurnos SCREEN_TURNOS = new PaneScreenTurnos();;


    @Override
    public void start(Stage optionsWindow) throws Exception{

        rootOptions = FXMLLoader.load(getClass().getResource("../views/OptionsWindow.fxml"));


        optionsWindow.setTitle("Sistema de Migraciones.");
        optionsWindow.setScene(new Scene(rootOptions));
        optionsWindow.show();

        initRootTurnShower();

    }
    public static void main(String[] args) {
        launch(args);
    }

    private void initRootTurnShower() throws IOException {
        turnsWindow = new Stage();
        turnsWindow.setScene(new Scene(SCREEN_TURNOS.getRoot()));
        turnsWindow.setFullScreen(true); 
        turnsWindow.show();

    }

    public static PaneScreenTurnos getRootTurnShower() {
        return SCREEN_TURNOS;
    }
}
