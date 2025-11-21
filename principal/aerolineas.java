package principal;

import principal.TDA.*;
import java.util.Scanner;

public class aerolineas {
    public static void main(String[] args) {
        Avion[] aviones = Lectura.cargarAvion();
        Scanner sc = new Scanner(System.in);

        
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
                    Avion nuevoAvion = new Avion(identificacion, modelo, 0, cantAsientos, 0.00);
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
        //mientras la variable guardado sea verdadera
        while (i < array.length && noGuardado) {
            if (array[i] == null) {
                array[i] = nuevoAvion;
                noGuardado = false;
            } else {
                i++;
            }
        }
    }

    public static boolean existeAvion(Avion[] array, String id) {
        boolean encontrado = false;
        int i = 0;

        do {
            if (array[i] != null && array[i].getIdentificacion().equals(id)) {
            encontrado = true;
        } else {
            i++;
        }
        } while (!encontrado && i < array.length);

        return encontrado;

    }

    public static boolean validarIdAvion(String id) {
        // si no esta bien estructurado no entra en ningun condicional y retorna falso
        boolean valido = false;

        // ?verifico que tenga el guion en el medio y que no se pase de caracteres y ahi
        // analizo el resto
        if (existeGuion(id)) {
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
}
