package espol.edu.ec.moduloDeRegistro;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

//Declaracion de la clase
/**
 * Clase MenuBox que hereda de VBox
 * @author Araujo Steven
 * @author Kenny Camba
 * @author Miguel Patiño
 * @version 1
 */

public class MenuBox extends VBox {
    //Declaracion de atributos
    
     /*
     * variable privada final: Array que contiene las opciones del menu
    */ 
    private final MenuItem[] menu;
    
    /**
     * Constructor del MenuBox
     * @param items conjunto de MenuItem
     */
    public MenuBox(MenuItem... items){
        super.getChildren().add(separador());
        
        menu = items;
        for(MenuItem item: items){
            super.getChildren().addAll(item, separador());
        }
    }
    
    //Declaracion de metodos 
    
    /**
     * Metodo separador: permite separar las opciones en el menu presentado
     */
    
    private Line separador() {
        Line sep = new Line();
        sep.setEndX(210);
        sep.setStroke(Color.BEIGE);
        return sep;
    }
    
    /**
     * Metodo MenuItem: devuelve el menu del MenuBox
     * @return menu
     */
    public MenuItem[] getMenu(){
        return menu;
    }
}
