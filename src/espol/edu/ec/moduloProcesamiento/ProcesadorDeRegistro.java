package espol.edu.ec.moduloProcesamiento;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import espol.edu.ec.tda.DoubleLinkedList;
import espol.edu.ec.tda.Stack;

/**
 * Clase principal para el módulo de registros,
 * Aquí se generean los datos necesarios.
 * Created by MiguelPS on 29-Jun-18.
 * @Autor Miguel Angel Patiño San-yeng
 * @Autor Steven Araujo.
 * @Autor Kenny Camba.
 */
public class ProcesadorDeRegistro {

    final String REGISTROS_PATH        = "src/espol/edu/ec/recursos/files/migraciones.csv";
    final String NUEVOS_REGISTROS_PATH = "src/espol/edu/ec/recursos/files/Registros.txt";

    final private int COSTA   = 0;
    final private int SIERRA  = 1;
    final private int ORIENTE = 2;
    final private int INSULAR = 3;

    Map<String,Integer> entradasPorProvincia;
    Map<String,Integer> salidasPorProvincia;
    Map<String,Integer> provinciaCodigo;  // identifica a cada provincia con su respectiva region


    // ArrayList de Stacks (4 pilas en cada uno) con el numero de registros por region con
    // sus respectivas provincias [costa,sierra,Oriente,Insular]
    ArrayList<Stack<Map.Entry<String,Integer>>> entradasPorRegion = new ArrayList<>();
    ArrayList<Stack<Map.Entry<String,Integer>>> salidasPorRegion = new ArrayList<>();

    //pilas con total de registros de entradas y salidas, haciendo uso de las 8 pilas anteriores.
    Stack<Map.Entry<String,Integer>> totalEntradas;
    Stack<Map.Entry<String,Integer>> totalSalidas;

    Stack<Map.Entry<String,Integer>> principalesEntradas;
    Stack<Map.Entry<String,Integer>> principalesSalidas;

    /**
     * constructor Principal, instancia las pilas.
     */
    public ProcesadorDeRegistro() {

        this.entradasPorProvincia = new HashMap<>();
        this.salidasPorProvincia  = new HashMap<>();
        this.provinciaCodigo      = new HashMap<>();
        this.totalEntradas   = new DoubleLinkedList<>();
        this.totalSalidas    = new DoubleLinkedList<>();
        
        this.principalesEntradas =new DoubleLinkedList<>();
        this.principalesSalidas = new DoubleLinkedList<>();

        for(int i=0; i<4; i++){
            entradasPorRegion.add(new DoubleLinkedList<>());
            salidasPorRegion.add(new DoubleLinkedList<>());
        }

        procesarPorTipoMigracion();   
        procesarTotal_EntradasSalidas();
    }

    /**
     * agrega los datos a la pila del respectivo tipo de migracion (entrada/salida)
     * y por la respectiva region.
     */
    public void procesarPorTipoMigracion(){

        cargarProvincias();

        cargarRegistros(NUEVOS_REGISTROS_PATH);
        cargarRegistros(REGISTROS_PATH);

        agregarAPila(entradasPorProvincia, entradasPorRegion);
        agregarAPila(salidasPorProvincia, salidasPorRegion);

    }
    

    /**
     * genera las pilas de todas las provincias por entrada y salida, y las ordena .
     */
    public void procesarTotal_EntradasSalidas(){

        Stack<Map.Entry<String,Integer>> copia1=new DoubleLinkedList<>();

        int i=0;
        for(Stack<Map.Entry<String,Integer>> pila: entradasPorRegion){
            
            while (!pila.isEmpty()){
                Map.Entry<String,Integer> entry=pila.pop();
                this.totalEntradas.push(entry);
                copia1.push(entry);
            }
            entradasPorRegion.set(i++,copia1);
            copia1=new DoubleLinkedList<>();
        }
        
        Stack<Map.Entry<String,Integer>> copia2=new DoubleLinkedList<>();
        i=0;
        for (Stack<Map.Entry<String,Integer>> pila: salidasPorRegion){
            while (!pila.isEmpty()){
                Map.Entry<String,Integer> entry = pila.pop();
                this.totalSalidas.push(entry);
                copia2.push(entry);
            }
            salidasPorRegion.set(i++,copia2);
            copia2=new DoubleLinkedList<>();
        }

        System.out.println(totalEntradas);
        System.out.println(totalSalidas);
        
        this.totalEntradas=ordenarPila(totalEntradas);
        this.totalSalidas=ordenarPila(totalSalidas);
        
        System.out.println(totalEntradas);
        System.out.println(totalSalidas);
        
        

        i=0;
        for(Stack<Map.Entry<String,Integer>> pila : entradasPorRegion){
            entradasPorRegion.set(i++, ordenarPila(pila));
        }
        i=0;
        for(Stack<Map.Entry<String,Integer>> pila : salidasPorRegion) {
            salidasPorRegion.set(i++, ordenarPila(pila));
        }
    }


    /**
     * comprueba a que region pertenece cada provincia y la agrega en la respectiva pila.
     * @param datos Map con los datos de registros.
     * @param pilas arrayList de las pilas.
     */
    private void agregarAPila(Map<String, Integer> datos, ArrayList<Stack<Map.Entry<String, Integer>>> pilas){
        String provincia;
        for (Map.Entry<String,Integer> entry: datos.entrySet()){
            provincia= entry.getKey();
            switch (provinciaCodigo.get(provincia)){
                case COSTA:   pilas.get(COSTA).push(entry);break;
                case SIERRA:  pilas.get(SIERRA).push(entry);break;
                case ORIENTE: pilas.get(ORIENTE).push(entry);break;
                case INSULAR: pilas.get(INSULAR).push(entry);break;
                default:break;
            }
        }
    }

    /**
     * Carga las provincias existentes en el archivo /recursos/files/Provincias.txt
     * para agregar al contador de registros por primera vez (asigna el valor de cero).
     */
    private void cargarProvincias(){

        String file = "src/espol/edu/ec/recursos/files/Provincias.txt";
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String provincia[] = line.split(",");

                entradasPorProvincia.put(provincia[2],0);
                salidasPorProvincia.put(provincia[2],0);

                provinciaCodigo.put(provincia[2],Integer.parseInt(provincia[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param path  String de la ruta de los registros (migraciones.csv y los nuevos registros generados)
     */
    public void cargarRegistros(String path){

        BufferedReader br = null;
        String line = "";
        String encode="iso-8859-1";
        
        if(path.equals(NUEVOS_REGISTROS_PATH)) encode="UTF8";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path),encode));
            br.readLine();

            while ((line = br.readLine()) != null) {

                String registro[] = line.split(";");
                    if (registro[0].equals("Entrada")) {
                        entradasPorProvincia.put(registro[3], entradasPorProvincia.get(registro[3]) + 1);
                    } else {
                        salidasPorProvincia.put(registro[3], salidasPorProvincia.get(registro[3]) + 1);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ordena la pila de menor a mayor, es decir, la primera provincia al salir de la pila
     * será la de mayor numero de registros.
     * @param pila pila desordenada de Map.Entry
     * @return pila ordenada.
     */
    public Stack<Map.Entry<String,Integer>> ordenarPila(Stack<Map.Entry<String,Integer>> pila) {

        Stack<Map.Entry<String,Integer>> ordenada = new  DoubleLinkedList<>();

        while(!pila.isEmpty())
        {
            Map.Entry<String,Integer> tmpEntry = pila.pop();

            while(!ordenada.isEmpty() && (ordenada.peek().getValue() > tmpEntry.getValue())) {
                pila.push(ordenada.pop());
            }
            ordenada.push(tmpEntry);
        }
        return ordenada;
    }


    public Stack<Map.Entry<String,Integer>> getEntradasCosta(){
        return this.entradasPorRegion.get(COSTA);
    }
    public Stack<Map.Entry<String,Integer>> getEntradaSierra(){
        return this.entradasPorRegion.get(SIERRA);
    }
    public Stack<Map.Entry<String,Integer>> getEntradaOriente(){
        return this.entradasPorRegion.get(ORIENTE);
    }
    public Stack<Map.Entry<String,Integer>> getEntradaInsular(){
        return this.entradasPorRegion.get(INSULAR);
    }
    public Stack<Map.Entry<String,Integer>> getSalidaCosta(){
        return this.salidasPorRegion.get(COSTA);
    }
    public Stack<Map.Entry<String,Integer>> getSalidaSierra(){
        return this.salidasPorRegion.get(SIERRA);
    }
    public Stack<Map.Entry<String,Integer>> getSalidaOriente(){
        return this.salidasPorRegion.get(ORIENTE);
    }
    public Stack<Map.Entry<String,Integer>> getSalidaInsular(){
        return this.salidasPorRegion.get(INSULAR);
    }

    public Stack<Map.Entry<String,Integer>> getTotalEntradas(){
        return this.totalEntradas;
    }
    public Stack<Map.Entry<String,Integer>> getTotalSalidas(){
        return this.totalSalidas;
    }
}
