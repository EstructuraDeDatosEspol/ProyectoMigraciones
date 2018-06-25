package espol.edu.ec.main;

import java.io.IOException;

import espol.edu.ec.controllers.AddTurnWindow_Controller;
import espol.edu.ec.controllers.OptionsWindow_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage turnsWindow;
    Parent rootTurnShower;
    Parent rootOptions;


    @Override
    public void start(Stage optionsWindow) throws Exception{

        rootOptions = FXMLLoader.load(getClass().getResource("../views/OptionsWindow.fxml"));

        OptionsWindow_Controller controller=new OptionsWindow_Controller();

        optionsWindow.setTitle("Sistema de Migraciones.");
        optionsWindow.setScene(new Scene(rootOptions));
        optionsWindow.show();

        initRootTurnShower();

    }
    public static void main(String[] args) {
        launch(args);
    }

    private void initRootTurnShower() throws IOException {

        rootTurnShower= FXMLLoader.load(getClass().getResource("../views/TurnShower.fxml"));

        AddTurnWindow_Controller controller=new AddTurnWindow_Controller();
        controller.setRootTurnShower(rootTurnShower);

        turnsWindow = new Stage();
        turnsWindow.setTitle("Mostrador de Turnos.");
        turnsWindow.setScene(new Scene(rootTurnShower));
        turnsWindow.show();

    }

    public Parent getRootTurnShower() {
        return rootTurnShower;
    }
}
