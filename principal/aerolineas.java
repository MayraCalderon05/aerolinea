package principal;

import principal.TDA.*;
import java.util.Scanner;

public class aerolineas {
    public static void main(String[] args) {
        Avion[] aviones = Lectura.cargarAvion();
        Scanner sc = new Scanner(System.in);

        System.out.println("ingresar id");
        String id = sc.next();

        boolean esValido = validarIdAvion(id);
        System.out.println(esValido);

    }

    // metodo para agregar un nuevo avion
    public static void agregarAvion(Avion[] aviones) {

        String identificacion;
        String modelo;
        int cantVuelos;
        int cantAsientos;
        double kmRecorridos;

        System.out.println("Ingrese la identificacion del avion:");

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
        int[] numeros = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        boolean valido = true;
        int i = 0;
        if (resto.length() <= 3) {
            while (valido && i < resto.length()) {
                char caracter = resto.charAt(i);

                // verifico que sea un digito numerico por CODIGO ASCII
                if (caracter >= '0' && caracter <= '9') {
                    int digito = Integer.parseInt(String.valueOf(caracter));
                    int j = 0;
                    boolean noEncontrado = true;
                    // solo verifica que sean numeros, para el caso que necesite numeros en el resto
                    // de los digitos
                    do {
                        if (digito != numeros[j]) {
                            noEncontrado = false;
                        }
                        j++;
                    } while (j < numeros.length && noEncontrado);

                    if (!noEncontrado) {
                        valido = false;
                    } else {
                        i++;

                    }
                } else {
                    valido = false;
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
