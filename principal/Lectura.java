package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalTime;

import principal.TDA.Avion;
import principal.TDA.Ruta;
import principal.TDA.Vuelo;

public class Lectura {
    // String vuelosEntrada = 
    //"C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Vuelos.txt";
    // String avionesEntrada =
    // "C:\\Users\\MORENA\\Desktop\\DA\\aerolinea\\recursos\\Aviones.txt";

    public static Avion[] aviones;
    public static Ruta[] rutas;
    public static Vuelo[][] vuelos;

    public static void cargarTodo() {
        aviones = Lectura.cargarAvion();
        rutas = Lectura.cargarRutas();
        vuelos = Lectura.cargarVuelos(aviones, rutas);
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
        Ruta[] rutas = new Ruta[20];
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

    public static Vuelo[][] cargarVuelos(Avion[] aviones, Ruta[] rutas) {
        String vuelosEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Vuelos.txt";
        Vuelo[][] vuelos = new Vuelo[7][15];
        String linea = null;
        int posI = 0;
        int posJ = 0;

        try {
            FileReader lectorArchivo = new FileReader(vuelosEntrada);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            while ((linea = bufferLectura.readLine()) != null) {
                String[] datos = linea.split(";");

                String numeroVuelo = datos[0];
                String idAvion = datos[1];
                Avion avion = encontrarAvionPorId(aviones, idAvion);
                String idRuta = datos[2];
                Ruta ruta = encontrarRutaPorId(rutas, idRuta);
                String dia = datos[3];
                LocalTime horarioSalida = LocalTime.parse(datos[4]);

                // obtiene las posiciones del vuelo en la matriz
                posI = obtenerIndiceDia(dia);
                posJ = obtenerIndiceHorario(horarioSalida);
                // crea el vuelo y lo guarda en la posicion correspondiente
                vuelos[posI][posJ] = new Vuelo(numeroVuelo, avion, ruta, dia, horarioSalida);
            }

            bufferLectura.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nEl archivo vuelos no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en el archivo vuelos.");
        }

        return vuelos;
    }

    public static Avion encontrarAvionPorId(Avion[] aviones, String idAvion) {
        Avion resultado = null;
        int i = 0;
        boolean encontrado = false;
        while (i < aviones.length && !encontrado) {
            if (aviones[i] != null) {
                if (aviones[i].getIdentificacion().equals(idAvion)) {
                    encontrado = true;
                    resultado = aviones[i];
                }
            }
            i++;
        }
        return resultado;
    }

    public static Ruta encontrarRutaPorId(Ruta[] rutas, String idRuta) {
        Ruta resultado = null;
        int i = 0;
        boolean encontrado = false;
        while (i < rutas.length && !encontrado) {
            if (rutas[i] != null) {
                if (rutas[i].getNumRuta().equals(idRuta)) {
                    encontrado = true;
                    resultado = rutas[i];
                }
            }
            i++;

        }
        return resultado;
    }

    public static int obtenerIndiceDia(String dia) {
        String[] diasSemana = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
        int indice = -1;
        int i = 0;
        while (i < diasSemana.length && indice == -1) {
            if (diasSemana[i].equalsIgnoreCase(dia)) {
                indice = i;
            } else {
                i++;
            }
        }
        return indice;
    }

    public static int obtenerIndiceHorario(LocalTime horario) {
        // teniendo en cuenta que los horarios son cada una hora desde las 08:00 hasta
        // las 22:00
        LocalTime[] horarios = {
                LocalTime.parse("08:00"),
                LocalTime.parse("09:00"),
                LocalTime.parse("10:00"),
                LocalTime.parse("11:00"),
                LocalTime.parse("12:00"),
                LocalTime.parse("13:00"),
                LocalTime.parse("14:00"),
                LocalTime.parse("15:00"),
                LocalTime.parse("16:00"),
                LocalTime.parse("17:00"),
                LocalTime.parse("18:00"),
                LocalTime.parse("19:00"),
                LocalTime.parse("20:00"),
                LocalTime.parse("21:00"),
                LocalTime.parse("22:00")
        };
        int indice = -1;
        int i = 0;
        while (i < horarios.length && indice == -1) {
            if (horarios[i].equals(horario)) {
                indice = i;
            } else {
                i++;
            }
        }
        return indice;
    }

    public static void escritura(Vuelo[] vuelosOrdenados) {
        String escritura = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\vuelosOrdenados.txt";
        String linea = "Vuelos ordenados por distancia del día\n";
        String arr = "";

        for (int i = 0; i < vuelosOrdenados.length; i++) {
            double distancia = vuelosOrdenados[i].getRuta().getDistancia();
            arr += "Vuelo: " + vuelosOrdenados[i].getNumeroVuelo() + "\n" +
                    "   Ruta: " + vuelosOrdenados[i].getRuta().getNumRuta() + "\n" +
                    "   Avion: " + vuelosOrdenados[i].getAvion().getIdentificacion() + "\n" +
                    "   Distancia en km: " + distancia + "\n\n";
        }

        try {
            FileWriter escritorArchivo = new FileWriter(escritura);
            BufferedWriter bufferEscritura = new BufferedWriter(escritorArchivo);

            bufferEscritura.write(linea + arr);

            bufferEscritura.close();
        } catch (IOException ex) {
            System.err.println("Error escribiendo en algun archivo.");
        }

    }
}
