package principal;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
//import java.util.Arrays;

import principal.TDA.Avion;
import principal.TDA.Ruta;

public class Lectura {

    public static void lecturaArchivos() {
        // ruta desde el disco
        // String vuelosEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de
        // algoritmos\\tpFinal\\recursos\\Vuelos.txt";

    }

    public static Avion[] cargarAvion() {
        // ruta hacia el archivo txt
        String avionesEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Aviones.txt";
        // creo un arreglo con 2 espacios mas en caso de guardar mas aviones
        Avion[] aviones = new Avion[22];
        String linea = null;
        // posicion de la linea
        int posicion = 0;

        try {
            FileReader lectorArchivo = new FileReader(avionesEntrada);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            while ((linea = bufferLectura.readLine()) != null) {
                String[] datos = linea.split(";");

                // declaro las variables para guardar los datos leidos
                String identificacion = datos[0];
                String modelo = datos[1];
                int cantVuelos = Integer.parseInt(datos[2]);
                int cantAsientos = Integer.parseInt(datos[3]);
                double kmRecorridos = Double.parseDouble(datos[4]);

                // creo el avion con los datos obtenidos
                Avion nuevoAvion = new Avion(identificacion, modelo, cantVuelos, cantAsientos, kmRecorridos);
                // lo guardo en el arreglo
                aviones[posicion] = nuevoAvion;
                posicion++;
            }

            bufferLectura.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nEl archivo aviones no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en el archivo aviones.");
        }

        return aviones;
    }

    public static Ruta[] cargarRutas() {
        String rutasEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Rutas.txt";
        Ruta[] rutas = new Ruta[22];
        String linea = null;
        int posicion = 0;

        try {
            FileReader lectorArchivo = new FileReader(rutasEntrada);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            while ((linea = bufferLectura.readLine()) != null) {
                String[] datos = linea.split(";");

                String numRuta = datos[0];
                String ciudadOrigen = datos[1];
                String ciudadDestino = datos[2];
                double distancia = Double.parseDouble(datos[3]);
                boolean internacional;
                if (datos[4].equalsIgnoreCase("Si")) {
                    internacional = true;
                } else {
                    internacional = false;
                }

                Ruta nuevaRuta = new Ruta(numRuta, ciudadOrigen, ciudadDestino, distancia, internacional);
                rutas[posicion] = nuevaRuta;
                posicion++;
            }

            bufferLectura.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nEl archivo rutas no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en el archivo rutas.");
        }

        return rutas;
    }
}
