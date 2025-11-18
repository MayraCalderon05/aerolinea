import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;

public class lectura {
    public static void main(String[] args) {
        // ruta desde el disco
        String rutasEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Rutas.txt";
        String avionesEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Aviones.txt";
        String vuelosEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Vuelos.txt";

        String[] rutas = leerArchivo(rutasEntrada);
        String[] aviones = leerArchivo(avionesEntrada);
        String[] vuelos = leerArchivo(vuelosEntrada);
        
        imprimirLineas(rutas);
        imprimirLineas(aviones);
        imprimirLineas(vuelos);

    }

    public static String[] leerArchivo(String ruta){
        String[] lineas = new String[0];
        int contador = 0;
        String linea = null;

        try {
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            //? cuenta las lineas del archivo
            while ((linea = bufferLectura.readLine()) != null) {
                contador++;
            }
            bufferLectura.close();

            //creo un array con la cantidad de lineas del archivo
            lineas = new String[contador];

            //vuelvo a abrir el archivo para leer sus lineas y guardarlas en un arreglo
            lectorArchivo = new FileReader(ruta);
            bufferLectura = new BufferedReader(lectorArchivo);

            int i = 0;
            while ((linea = bufferLectura.readLine()) != null) {
                lineas[i] = linea;
                i++;
            }
            bufferLectura.close();
            // bufferEscritura.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }

        return lineas;
    }
    public static void imprimirLineas(String[] lineas){
        for (int i = 0; i < lineas.length; i++) {
            System.out.println(lineas[i]);
        }
    }
}
