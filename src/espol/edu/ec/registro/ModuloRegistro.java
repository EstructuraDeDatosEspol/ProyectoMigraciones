/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.registro;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author SSAM
 */
public class ModuloRegistro {
    private Pane root;
    private LinkedList<RegistroMigrante> listRegistroMigrantes;
    private LinkedList<Persona> listPersonas;
    private HashMap<Integer,Continente> listContinentes;
    private LinkedList<Canton> listCantones;

    public ModuloRegistro() {
        root = new Pane();
        
        listContinentes = new ReadWriter().cargarContinentes("Continentes.txt");
        listPersonas = new ReadWriter().cargarPersonas("Persona.txt");
        listRegistroMigrantes = new ReadWriter().cargarRegistro("Registros.txt");
        listCantones = referenciarCantones(listContinentes);
        enlazarListas();
        System.out.println(listRegistroMigrantes.getLast());
        listCantones.get(39).getList().get(0).setAnio_movi(1);
        System.out.println(listRegistroMigrantes.getLast());
        System.out.println(listPersonas.getLast().getList());
        System.out.println(listCantones.get(39).getList());
        
        Button boton1 = new Button("Registrar");
        boton1.setDisable(true);
        boton1.setTranslateX(1100);
        boton1.setTranslateY(300);
        Button boton2 = new Button("Modificar");
        boton2.setDisable(true);
        boton2.setTranslateX(1100);
        boton2.setTranslateY(340);
        Button boton3 = new Button("Eliminar");
        boton3.setDisable(true);
        boton3.setTranslateX(1100);
        boton3.setTranslateY(380);
        DisenioFormulario disenioFormulario = new DisenioFormulario(root);
        
        boton1.setOnAction(e -> System.out.println("registrar"));
        boton2.setOnAction(e -> System.out.println("modificar"));
        boton3.setOnAction(e -> System.out.println("eliminar"));
        
        root.getChildren().addAll(boton1,boton2,boton3);
    }
    
    public void pantalla(Stage stage){
        stage.setScene(new Scene(root,2480,700));
    }
    
    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public LinkedList<RegistroMigrante> getListRegistroMigrantes() {
        return listRegistroMigrantes;
    }

    public void setListRegistroMigrantes(LinkedList<RegistroMigrante> listRegistroMigrantes) {
        this.listRegistroMigrantes = listRegistroMigrantes;
    }

    public LinkedList<Persona> getListPersonas() {
        return listPersonas;
    }

    public void setListPersonas(LinkedList<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }

    public HashMap<Integer, Continente> getListContinentes() {
        return listContinentes;
    }

    public void setListContinentes(HashMap<Integer, Continente> listContinentes) {
        this.listContinentes = listContinentes;
    }

    public LinkedList<Canton> getListCantones() {
        return listCantones;
    }

    public void setListCantones(LinkedList<Canton> listCantones) {
        this.listCantones = listCantones;
    }
    
    public static LinkedList<Canton> referenciarCantones(HashMap<Integer,Continente> mapaContinentes){
        LinkedList<Canton> lista = new LinkedList<>();
        for(Map.Entry<Integer, Continente> c: mapaContinentes.entrySet()){
            Continente con = c.getValue();//obtengo el continente perteneciente a ese continente
            for(Integer p: con.getMapaPaises().keySet()){
                Pais pais = con.getMapaPaises().get(p);//obtengo el pais perteneciente a ese continente(con clave p)
                for(Integer pr: pais.getMapaProvincias().keySet()){
                    Provincia provincia = pais.getMapaProvincias().get(pr);//obtengo la provincia perteneciente a ese pais(con clave pr)
                    for(Integer can: provincia.getMapaCantones().keySet()){
                        lista.addLast(provincia.getMapaCantones().get(can));
                    }
                }
            }
        }
        return lista;
    }
    
    public final void enlazarListas(){
        for(RegistroMigrante rm: listRegistroMigrantes){
            for(Persona p: listPersonas){
                if(rm.getPersona().equals(p))p.getList().addLast(rm);
            }
            for(Canton c: listCantones){
                if(rm.getCanton().equals(c))c.getList().addLast(rm);
            }
        }
    }
}
