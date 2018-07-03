/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.moduloTurnos;

import espol.edu.ec.tda.Const;
import espol.edu.ec.tda.Turno;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Kenny Camba
 */
public class TurnoPane extends HBox {
    
    private Turno turno;
    private double height;
    private double widht;
    
    public TurnoPane(Turno turno, double widht, double height) {
        this.turno = turno;
        this.widht = widht;
        this.height = height;
        content();
    }
    
    public TurnoPane(Turno turno) {
        this(turno, 200, 75);
    }
    
    private void content() {
        Text t = new Text(turno.getNumero());
        Text p = new Text(turno.getPuesto().getPuesto());
        t.setStyle(Const.FONT_BOLD);
        p.setStyle(Const.FONT_BOLD); 
        t.setFill(Color.WHITE);
        p.setFill(Color.WHITE); 
        Rectangle fondo = new Rectangle(widht, height);
        t.setFont(Font.font("Arial", fondo.getHeight()/1.8));
        p.setFont(Font.font("Arial", fondo.getHeight()/1.8));
        fondo.setFill(Color.color(0.408, 0.718, 0.898));
        Rectangle fondo2 = new Rectangle(widht, height);
        fondo2.setFill(Color.color(0.392, 0.529, 0.667));
        super.getChildren().addAll(new StackPane(fondo, t), new StackPane(fondo2, p));
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }    

    public double getPaneHeight() {
        return height;
    }

    public void setPaneHeight(double height) {
        this.height = height;
    }

    public double getPaneWidht() {
        return widht;
    }

    public void setPaneWidht(double widht) {
        this.widht = widht;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof TurnoPane))
            return false;
        return turno.equals(((TurnoPane)o).turno);
    }
}
