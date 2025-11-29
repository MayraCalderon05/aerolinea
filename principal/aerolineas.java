package principal;

import principal.TDA.*;

//import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalTime;

public class aerolineas {
    public static void main(String[] args) {
        // Llamamos la carga de datos una sola vez al inicio
        Lectura.cargarTodo();
        // iniciamos las variables globales
        Avion[] aviones = Lectura.aviones;
        Ruta[] rutas = Lectura.rutas;
        Vuelo[][] vuelos = Lectura.vuelos;

        Scanner sc = new Scanner(System.in);

        // Lectura.mostrarMatrizVuelos(vuelos);
        agregarVuelo(vuelos, aviones, rutas, sc);
        Lectura.mostrarMatrizVuelos(vuelos);
        mostrarHorariosSinVuelos(vuelos);

        sc.close();
    }

    // * */ metodo para agregar un nuevo avion
    public static void agregarAvion(Avion[] aviones, Scanner sc) {
        String identificacion;
        String modelo;
        int cantAsientos;

        System.out.println("Ingrese la identificacion del avion:");
        identificacion = sc.next();
        if (validarIdAvion(identificacion)) {
            if (existeAvion(aviones, identificacion)) {
                System.out.println("El avion ingresado ya existe en la base de datos");
            } else {
                System.out.println("Ingrese el modelo del avion:");
                modelo = sc.next();

                System.out.println("Ingrese la cantidad de asientos que tiene el avión");
                cantAsientos = sc.nextInt();
                // valores imposibles
                if (cantAsientos < 0 || cantAsientos > 300) {
                    System.out.println("La cantidad de asientos es inválida");
                } else {
                    Avion nuevoAvion = new Avion(identificacion, modelo, cantAsientos);
                    System.out.println("Avion registrado con éxito");
                    guardarAvion(aviones, nuevoAvion);
                    System.out.println("Avion guardado con éxito");
                }

            }
        } else {
            System.out.println("El identificador del avión no es válido");
        }

    }

    public static void guardarAvion(Avion[] array, Avion nuevoAvion) {
        int i = 0;
        boolean noGuardado = true;
        // mientras la variable guardado sea verdadera
        while (i < array.length && noGuardado) {
            if (array[i] == null) {
                array[i] = nuevoAvion;
                noGuardado = false;
            } else {
                i++;
            }
        }
    }

    public static boolean existeAvion(Avion[] aviones, String id) {
        boolean encontrado = false;
        if (Lectura.encontrarAvionPorId(aviones, id) != null) {
            encontrado = true;
        }

        return encontrado;
    }

    public static boolean validarIdAvion(String id) {
        // si no esta bien estructurado no entra en ningun condicional y retorna falso
        boolean valido = false;

        // ?verifico que tenga el guion en el medio y que no se pase de caracteres y ahi
        // analizo el resto
        // necesito que tenga al menos 6 caracteres para que pueda ser valido
        if (existeGuion(id) && id.length() >= 6) {
            // la posicion 2 es del guion, no la toma en el substring
            String iniciales = id.substring(0, 2);
            // me refiero al resto de la cadena despues de las iniciales
            String resto;

            // analizo las primeras dos iniciales
            switch (iniciales) {
                case "LV":
                    char postGuion = id.charAt(3);
                    switch (postGuion) {
                        // id tipo LV-X
                        case 'X':
                            resto = id.substring(4);
                            valido = verificarNumeros(resto);
                            break;
                        case 'S':
                            char ultimaSigla = id.charAt(4);
                            // para id de tipo LV-SX
                            if (ultimaSigla == 'X') {
                                resto = id.substring(5);
                            } else {
                                // id tipo LV-S
                                resto = id.substring(4);
                            }
                            valido = verificarNumeros(resto);
                            break;
                        default:
                            // si solo es tipo LV verifico que el resto sean letras
                            resto = id.substring(3);

                            if (!verificarNumeros(resto)) {
                                valido = true;
                            }
                            break;
                    }
                    break;

                case "LQ":
                    resto = id.substring(3);
                    if (!verificarNumeros(resto)) {
                        valido = true;
                    }
                    break;
                default:
                    valido = false;
                    break;
            }
        }
        return valido;
    }

    private static boolean verificarNumeros(String resto) {
        boolean valido = true;
        int i = 0;
        if (resto.length() <= 3) {
            while (valido && i < resto.length()) {
                char caracter = resto.charAt(i);

                // verifico que sea un digito numerico por CODIGO ASCII
                if (caracter < '0' || caracter > '9') {
                    valido = false;
                } else {
                    i++;
                }
            }
        } else {
            valido = false;
        }

        return valido;

    }

    private static boolean existeGuion(String id) {
        boolean existe;
        if (id.charAt(2) == '-') {
            existe = true;
        } else {
            existe = false;
        }
        return existe;
    }

    public static String mostrarDatosAvion(Avion[] array, String id) {
        String info = "";
        // validar primero si el id que se ingreso puede llegar a existir
        if (validarIdAvion(id)) {
            Avion encontrado = Lectura.encontrarAvionPorId(array, id);
            if (encontrado != null) {
                info = encontrado.toString();
            } else {
                info = "No se encontró ningún avión con el ID ingresado.";
            }
        } else {
            info = "El ID del avión ingresado no es válido.";
        }

        return info;
    }

    // metodo para cargar un nuevo vuelo
    private static boolean existeVuelo(Vuelo[][] vuelos, String num) {
        boolean existe = false;
        int i = 0;
        int j = 0;
        do {
            while (j < vuelos[i].length) {
                if (vuelos[i][j] != null && vuelos[i][j].getNumeroVuelo().equals(num)) {
                    existe = true;
                }
                j++;
            }
            j = 0;
            i++;
        } while (!existe && i < vuelos.length);

        return existe;
    }

    private static Avion solicitarAvion(Avion[] aviones, Scanner sc) {
        String idAvion;
        Avion avionEncontrado = null;
        boolean continuar = true;

        do {
            System.out.println("Ingrese la identificación del avión:");
            idAvion = sc.next();

            avionEncontrado = Lectura.encontrarAvionPorId(aviones, idAvion);

            if (avionEncontrado == null) {
                String respuesta = enviarConfirmacion(sc, "No se encontró el avión con ID: " + idAvion);
                continuar = mensajeConfirmacion(respuesta);
            }

        } while (avionEncontrado == null && continuar);

        return avionEncontrado;

    }

    private static Ruta solicitarRuta(Ruta[] rutas, Scanner sc) {
        String idRuta;
        Ruta rutaEncontrada = null;
        boolean continuar = true;

        do {
            System.out.println("Ingrese el ID de la ruta:");
            idRuta = sc.next();

            rutaEncontrada = Lectura.encontrarRutaPorId(rutas, idRuta);

            if (rutaEncontrada == null) {
                String respuesta = enviarConfirmacion(sc, "No se encontró la ruta con ID: " + idRuta);
                continuar = mensajeConfirmacion(respuesta);
            }

        } while (rutaEncontrada == null && continuar);

        return rutaEncontrada;

    }

    public static String solicitarDia(Scanner sc) {
        boolean continua = true;
        String dia;
        int posI = -1;

        do {
            System.out.println("Ingrese el día de la semana:");
            dia = sc.next();
            posI = Lectura.obtenerIndiceDia(dia);

            if (posI == -1) {
                String rta = enviarConfirmacion(sc, "El día ingresado no es válido.");
                continua = mensajeConfirmacion(rta);
            }
        } while (continua && posI == -1);

        return dia;
    }

    public static LocalTime solicitarHorario(Scanner sc) {
        boolean continua = true;
        String horarioSalidaStr;
        LocalTime horarioSalida;
        int posJ = -1;

        do {
            System.out.println("Ingrese el horario de salida (hh:mm):");
            horarioSalidaStr = sc.next();
            horarioSalida = LocalTime.parse(horarioSalidaStr);
            posJ = Lectura.obtenerIndiceHorario(horarioSalida);

            if (posJ == -1) {
                String rta = enviarConfirmacion(sc, "El horario ingresado no es válido.");
                continua = mensajeConfirmacion(rta);
            }
        } while (continua && posJ == -1);

        return horarioSalida;
    }

    public static void agregarVuelo(Vuelo[][] vuelos, Avion[] aviones, Ruta[] rutas, Scanner sc) {
        String numeroVuelo;

        System.out.println("Ingrese el número de vuelo:");
        numeroVuelo = sc.next();
        // valido que no exista
        if (!existeVuelo(vuelos, numeroVuelo)) {

            Avion avion = solicitarAvion(aviones, sc);
            if (avion != null) {

                Ruta ruta = solicitarRuta(rutas, sc);
                if (ruta != null) {
                    boolean continuar = true;

                    do {
                        String dia = solicitarDia(sc);
                        LocalTime horarioSalida = solicitarHorario(sc);

                        int posI = Lectura.obtenerIndiceDia(dia);
                        int posJ = Lectura.obtenerIndiceHorario(horarioSalida);
                        // busca que las posiciones sean validas por si el usuario cancela la operacion
                        if (posI != -1 && posJ != -1) {
                            if (vuelos[posI][posJ] != null) {
                                String rta = enviarConfirmacion(sc, "Ya existe un vuelo en ese día y horario.");
                                continuar = mensajeConfirmacion(rta);
                            } else {
                                vuelos[posI][posJ] = new Vuelo(numeroVuelo, avion, ruta, dia, horarioSalida);
                                System.out.println("Vuelo agregado con éxito.");
                                continuar = false;
                            }
                        } else {
                            System.out.println("Operación cancelada");
                            //no continua el bucle porque si la posición es -1 en ambas es porque cancelaron la operacion en el modulo
                            //ya que si el modulo tira dia u horarioSalida en nulo o invalido es porque el usuario se cansó
                            continuar = false;
                        }

                    } while (continuar);
                } else {
                    System.out.println("Operación cancelada");
                }
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.out.println("Este vuelo ya existe.");
        }
    }

    private static boolean mensajeConfirmacion(String rta) {
        boolean confirmacion;
        if (rta.equalsIgnoreCase("SI")) {
            confirmacion = true;
        } else {
            confirmacion = false;
        }

        return confirmacion;
    }

    private static String enviarConfirmacion(Scanner sc, String mensaje) {
        System.out.println(mensaje + "\n¿Desea volver a intentarlo?");
        String rta = sc.next();

        return rta;
    }

    // metodo de ordenamiento QUICK SORT para ordenar vuelos por distancia en km de
    // forma ascendente
    // primero pongo todos los vuelos en un arreglo para que sea mas facil
    private static Vuelo[] filtrarDia(Vuelo[][] mat, String dia) {
        Vuelo[] listaVuelos = new Vuelo[cuentaElementos(mat, dia)];
        int fila = Lectura.obtenerIndiceDia(dia);
        int i = 0;
        for (int columna = 0; columna < mat[fila].length; columna++) {
            if (mat[fila][columna] != null) {
                listaVuelos[i] = mat[fila][columna];
                i++;
            }
        }
        return listaVuelos;
    }

    // defino el tamaño del arreglo
    private static int cuentaElementos(Vuelo[][] mat, String dia) {
        int indiceFila = Lectura.obtenerIndiceDia(dia);
        int i = 0;
        int contador = 0;

        while (i < mat[indiceFila].length) {
            if (mat[indiceFila][i] != null) {
                contador++;
            }
            i++;
        }

        return contador;
    }

    // busco la distancia por el ID de ruta
    private static double obtenerDistanciaVuelo(Vuelo vuelo) {
        double distancia = 0.00;
        if (vuelo != null) {
            distancia = vuelo.getRuta().getDistancia();
        }
        return distancia;
    }

    // metodo para elegir un pivot segun la mediana
    private static int mediana(Vuelo[] arr, int inicio, int fin) {
        int indice;
        // en caso de que los parametros no sean validos
        if (inicio < 0 || fin >= arr.length || inicio > fin) {
            indice = -1;

        } else {
            int medio = (inicio + fin) / 2;
            double rutaInicio = obtenerDistanciaVuelo(arr[inicio]);
            double rutaMedio = obtenerDistanciaVuelo(arr[medio]);
            double rutaFin = obtenerDistanciaVuelo(arr[fin]);

            // rutaInicio < rutaMedio < rutaFin OR rutaFin < rutaMedio < rutaInicio
            // en caso de que rutaMedio este en el medio de los valores
            if (((rutaInicio <= rutaMedio) && (rutaMedio <= rutaFin)) ||
                    ((rutaFin <= rutaMedio) && (rutaMedio <= rutaInicio))) {
                indice = medio;

                // rutaMedio < rutaInicio < rutaFin OR rutaFin < rutaInicio < rutaMedio
                // en caso de que rutaInicio este en el medio de los valores
            } else if (((rutaMedio <= rutaInicio) && (rutaInicio <= rutaFin)) ||
                    ((rutaFin <= rutaInicio) && (rutaInicio <= rutaMedio))) {
                indice = inicio;
            } else {
                // que la rutaFin sea la que esta en el medio ya que no hay otro caso
                indice = fin;
            }
        }

        return indice;
    }

    // ahora si, ordeno
    // metodo para intercambar los lugares
    private static int particion(Vuelo[] arr, int inicio, int fin, int pivote) {
        // estos son los indices que se usan para ubicar los elementos
        int izq, der;
        Vuelo aux;
        izq = inicio;
        der = fin;

        double distPivote = obtenerDistanciaVuelo(arr[pivote]);

        while (izq <= der) {
            // busco el elemento que tenga mayor distancia en km que el pivote
            // desde la izquierda
            while (obtenerDistanciaVuelo(arr[izq]) < distPivote) {
                izq++;
            }

            // busco el elemento que tenga menor distancia en km que el pivote
            // desde la derecha
            while (obtenerDistanciaVuelo(arr[der]) > distPivote) {
                der--;
            }

            // intercambiar los lugares si no se cruzaron los indices izq y der
            if (izq <= der) {
                aux = arr[izq];
                arr[izq] = arr[der];
                arr[der] = aux;
                // este aumento es para que salga del bucle
                izq++;
                der--;
            }
        }
        // retorno la posicion donde se cruzan los indices ya que ahi se parte el
        // arreglo
        return izq;
    }

    // metodo quicksort de ordenamiento
    private static void quicksort(Vuelo[] lista, int inicio, int fin) {
        int indice, particion;

        // CASO BASE: inicio >= fin
        if (inicio < fin) {
            indice = mediana(lista, inicio, fin);
            // confirmo que el pivote sea valido
            if (indice >= 0) {
                particion = particion(lista, inicio, fin, indice);
                // ordena la parte izquierda
                quicksort(lista, inicio, particion - 1);
                // ordena la parte derecha
                quicksort(lista, particion, fin);
            }
        }

    }

    public static void ordenarXdistancia(Vuelo[][] mat, Scanner sc) {
        System.out.println("ingrese el día del que desea obtener información");
        String dia = sc.next();
        if (Lectura.obtenerIndiceDia(dia) != -1) {
            Vuelo[] lista = filtrarDia(mat, dia);

            if (lista.length == 0) {
                System.out.println("No hay vuelos para el día " + dia);
            } else {
                quicksort(lista, 0, lista.length - 1);
                Lectura.escritura(lista);

            }
        } else {
            System.out.println("El día ingresado no es válido");
        }
    }

    public static void vueloAterrizado(Vuelo[][] vuelos, String numeroVuelo) {
        boolean encontrado = false;
        int i = 0;
        int j = 0;
        while (i < vuelos.length && !encontrado) {
            j = 0;
            while (j < vuelos[0].length && !encontrado) {
                if (vuelos[i][j] != null) {
                    if (vuelos[i][j].getNumeroVuelo().equals(numeroVuelo) && !vuelos[i][j].getAterrizado()) {
                        encontrado = true;
                        vuelos[i][j].setAterrizado(true);
                        actualizarAvionAterrizado(vuelos[i][j]);
                    } else {
                        j++;
                    }
                }
            }
            i++;
        }
    }

    public static void actualizarAvionAterrizado(Vuelo vuelo) {
        // actualiza la cantidad de vuelos del avion
        int nuevosVuelos = vuelo.getAvion().getCantVuelos() + 1;
        vuelo.getAvion().setCantVuelos(nuevosVuelos);
        // actualiza los km recorridos del avion
        double nuevosKm = vuelo.getAvion().getKmRecorridos() + vuelo.getRuta().getDistancia();
        vuelo.getAvion().setKmRecorridos(nuevosKm);
    }

    // para establecer el espacio del arreglo que busca las rutas
    private static int contarRutas(double distMin, double distMax, Ruta[] rutas) {
        int contador = 0;

        for (int i = 0; i < rutas.length; i++) {
            double distancia = rutas[i].getDistancia();
            if ((distancia >= distMin) && (distancia <= distMax)) {
                contador++;
            }

        }

        return contador;
    }

    public static Ruta[] buscarRutas(double distMin, double distMax, Ruta[] rutas) {
        int tamanio = contarRutas(distMin, distMax, rutas);
        Ruta[] encontradas;
        if (tamanio == 0) {
            encontradas = new Ruta[0];
        } else {
            encontradas = new Ruta[tamanio];
            int j = 0;
            for (int i = 0; i < rutas.length; i++) {
                double distancia = rutas[i].getDistancia();
                if ((distancia >= distMin) && (distancia <= distMax)) {
                    encontradas[j] = rutas[i];
                    j++;
                }
            }
        }

        return encontradas;
    }

    public static void encuentraRutas(Ruta[] rutas, Scanner sc) {
        System.out.println("Ingrese la distancia mínima a partir de la que quiere buscar");
        int distMin = sc.nextInt();
        if (distMin >= 0) {
            System.out.println("Ingrese la distancia máxima hasta donde quiere buscar");
            int distMax = sc.nextInt();

            Ruta[] rutasEncontradas = buscarRutas(distMin, distMax, rutas);
            if (rutasEncontradas.length > 0) {
                for (int i = 0; i < rutasEncontradas.length; i++) {
                    System.out.println(rutasEncontradas[i].toString() + "\n");
                }
            } else {
                System.out.println("No se encontraron rutas en el rango de distancia ingresado");
            }
        } else {
            System.out.println("La distancia mínima ingresada no es válida");
        }
    }

    private static int calcularHorariosSinVuelos(Vuelo[][] mat, int i, int j) {
        int cantSinVuelos;
        // primer caso base, cuando el largo de la columna sea invalido
        if (j == mat[0].length) {
            j = 0;
            i++;
        }
        // paso recursivo: mientras que las filas sean validas
        if (i < mat.length) {
            if ((mat[i][j] == null)) {
                cantSinVuelos = 1 + calcularHorariosSinVuelos(mat, i, j + 1);
            } else {
                cantSinVuelos = calcularHorariosSinVuelos(mat, i, j + 1);
            }
            // si las filas no son validas, segundo caso base
        } else {
            cantSinVuelos = 0;
        }

        return cantSinVuelos;
    }

    public static void mostrarHorariosSinVuelos(Vuelo[][] mat) {
        int cant = calcularHorariosSinVuelos(mat, 0, 0);
        System.out.println("La cantidad de horarios sin vuelos en la semana es de: " + cant);
    }
}
