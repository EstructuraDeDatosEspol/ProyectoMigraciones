package espol.edu.ec.moduloDeRegistro;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensajesAlerta {
    
    public void cuadrosIncompletos(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error! ");
        alert.setHeaderText("Campos incompletos");
        alert.setContentText("Tienen que estar todos los campos llenos!");

        alert.showAndWait();
    }
    
    public void cedulaEnteroError(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Tipo de dato erroneo");
        alert.setContentText("En el campo debe ir solo números!");

        alert.showAndWait();
    }
    
    public void guardadoExitosamente(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Guardado");
        alert.setHeaderText(null);
        alert.setContentText("Se Guardo correctamente!");

        alert.showAndWait();
    }
    
    public void sobreEscritoExitosamente(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("SobreEscrito");
        alert.setHeaderText(null);
        alert.setContentText("Se modificó correctamente!");

        alert.showAndWait();
    }
    
    public void eliminadoExitosamente(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Eliminado");
        alert.setHeaderText(null);
        alert.setContentText("Se eliminó correctamente!");

        alert.showAndWait();
    }
}
