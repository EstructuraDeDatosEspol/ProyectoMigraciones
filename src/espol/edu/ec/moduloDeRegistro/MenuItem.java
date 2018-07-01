package espol.edu.ec.moduloDeRegistro;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//Declaracion de la clase
/**
 * Clase MenuBox que hereda de VBox
 * @author Araujo Steven
 * @author Kenny Camba
 * @author Miguel Patiño
 * @version 1
 */

public class MenuItem extends StackPane {
    //Declaracion de atributos

    /*
     * variable privada final: nodo figura
    */
    private final Rectangle rectangulo;
    /*
     * variable privada final: almacenara el texto del menu 
    */
    private final Text texto;
    /*
     * variable privada final: dara formato al texto del menu 
    */
    private final LinearGradient gradiente;
    
    //Declaracion de metodos 
    
    /**
     * Metodo MenuItem: permite asignar un nombre a la opcion del menu principal 
     * @param name  Almanecenara el nombre correspondiente a la opcion del menu principal 
     * 
     */
    public MenuItem(String name){
        gradiente = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] { 
            new Stop(0, Color.WHITE),
            new Stop(0.1, Color.BLACK),
            new Stop(0.9, Color.BLACK),
            new Stop(1, Color.WHITE)
        });
        
        rectangulo = new Rectangle(200,30);
        rectangulo.setOpacity(0.9);

        texto = new Text(name);
        texto.setFill(Color.WHITE);
        texto.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,20));

        setAlignment(Pos.CENTER);
        eventoMouse();
        super.getChildren().addAll(rectangulo, texto);
    }		
    
    /**
     * Metodo eventoMouse: define el diseño al dar clic a los botones del
     * menu principal 
     * 
     */
    private void eventoMouse(){
        setOnMouseEntered(e -> {
                rectangulo.setFill(gradiente);
                texto.setFill(Color.WHITE);
        });
			
        setOnMouseExited(e -> {
                rectangulo.setFill(Color.BLACK);
                texto.setFill(Color.WHITE);
        });
        
        setOnMousePressed(e -> rectangulo.setFill(Color.GREY));
    }
}
