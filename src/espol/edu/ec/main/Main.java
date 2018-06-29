package espol.edu.ec.main;

import java.io.IOException;

import espol.edu.ec.pane.PaneScreenTurnos;
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
