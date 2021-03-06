package espol.edu.ec.main;

import espol.edu.ec.moduloTurnos.PaneModulo1;
import java.io.IOException;

import espol.edu.ec.moduloTurnos.PaneScreenTurnos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    Stage turnsWindow;
    Parent rootOptions;
    private static final PaneScreenTurnos SCREEN_TURNOS = new PaneScreenTurnos();;


    @Override
    public void start(Stage optionsWindow) throws Exception{

        rootOptions = FXMLLoader.load(getClass().getResource("OptionsWindow.fxml"));

        optionsWindow.setTitle("Sistema de Migraciones.");
        optionsWindow.setScene(new Scene(rootOptions));
        initRootTurnShower();
        optionsWindow.show();
        optionsWindow.setOnCloseRequest(e -> turnsWindow.close()); 

    }
    
    @Override
    public void stop () throws Exception {
        PaneModulo1.updateFiles();
        SCREEN_TURNOS.endRun();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    private void initRootTurnShower() throws IOException {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER); 
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(SCREEN_TURNOS.getRoot());
        turnsWindow = new Stage();
        turnsWindow.setScene(new Scene(pane)); 
        turnsWindow.setFullScreen(false); 
        turnsWindow.show();
    }

    public static PaneScreenTurnos getRootTurnShower() {
        return SCREEN_TURNOS;
    }
}
