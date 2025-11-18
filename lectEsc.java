//No le des bola a esto


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
// No usamos ArrayList por restricción, trabajamos con arrays

public class lectEsc {
    public static void main(String[] args) {
        //la ubicacion es desde el disco
        String rutasEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Rutas.txt";
        String avionesEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Aviones.txt";
        String vuelosEntrada = "C:\\Users\\Usuario\\Documents\\Desarrollo de algoritmos\\tpFinal\\recursos\\Vuelos.txt";
        //String avionesSalida = "recursos/Aviones.txt";

        // Arrays para guardar los objetos en memoria (sin ArrayList)
        Avion[] aviones = readAviones(avionesEntrada);
        Ruta[] rutas = readRutas(rutasEntrada);
        Vuelo[] vuelos = readVuelos(vuelosEntrada);

        int cantAviones = countNonNull(aviones);
        int cantRutas = countNonNull(rutas);
        int cantVuelos = countNonNull(vuelos);

        // Mostrar un resumen
        System.out.println("--- Resumen ---");
        System.out.println("Aviones leidos: " + cantAviones);
        System.out.println("Rutas leidas: " + cantRutas);
        System.out.println("Vuelos leidos: " + cantVuelos);

        // Ejemplo: modificar algo en memoria (p. ej. cambiar el modelo del primer avion)
        if (cantAviones > 0) {
            Avion a = aviones[0];
            System.out.println("Antes: " + a);
            a.setModelo(a.getModelo() + " (modificado)");
            System.out.println("Despues: " + a);
        }

        // Guardar cambios de vuelta en los archivos (sobrescribe)
        saveAviones(avionesEntrada, aviones);
        saveRutas(rutasEntrada, rutas);
        saveVuelos(vuelosEntrada, vuelos);
    }

    // --------- Lectura ---------
    private static Avion[] readAviones(String path) {
        // Primer pase: contar lineas no vacias
        int count = 0;
        String linea;
        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) count++;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo Aviones no encontrado: " + ex.getMessage());
            return new Avion[0];
        } catch (IOException ex) {
            System.err.println("Error leyendo Aviones: " + ex.getMessage());
            return new Avion[0];
        }

        Avion[] arr = new Avion[count];
        int idx = 0;
        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] parts = linea.split(";");
                if (parts.length >= 5) {
                    String matricula = parts[0];
                    String modelo = parts[1];
                    int capacidad = parseIntSafe(parts[2]);
                    int asientos = parseIntSafe(parts[3]);
                    long precio = parseLongSafe(parts[4]);
                    arr[idx++] = new Avion(matricula, modelo, capacidad, asientos, precio);
                } else {
                    System.err.println("Linea Aviones con formato inesperado: " + linea);
                }
            }
        } catch (IOException ex) {
            System.err.println("Error leyendo Aviones (segunda pasada): " + ex.getMessage());
        }
        return arr;
    }

    private static Ruta[] readRutas(String path) {
        int count = 0;
        String linea;
        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) count++;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo Rutas no encontrado: " + ex.getMessage());
            return new Ruta[0];
        } catch (IOException ex) {
            System.err.println("Error leyendo Rutas: " + ex.getMessage());
            return new Ruta[0];
        }

        Ruta[] arr = new Ruta[count];
        int idx = 0;
        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] parts = linea.split(";");
                if (parts.length >= 5) {
                    String id = parts[0];
                    String origen = parts[1];
                    String destino = parts[2];
                    int distancia = parseIntSafe(parts[3]);
                    boolean internacional = parts[4].equalsIgnoreCase("Si") || parts[4].equalsIgnoreCase("S") || parts[4].equalsIgnoreCase("Yes");
                    arr[idx++] = new Ruta(id, origen, destino, distancia, internacional);
                } else {
                    System.err.println("Linea Rutas con formato inesperado: " + linea);
                }
            }
        } catch (IOException ex) {
            System.err.println("Error leyendo Rutas (segunda pasada): " + ex.getMessage());
        }
        return arr;
    }

    private static Vuelo[] readVuelos(String path) {
        int count = 0;
        String linea;
        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) count++;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo Vuelos no encontrado: " + ex.getMessage());
            return new Vuelo[0];
        } catch (IOException ex) {
            System.err.println("Error leyendo Vuelos: " + ex.getMessage());
            return new Vuelo[0];
        }

        Vuelo[] arr = new Vuelo[count];
        int idx = 0;
        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] parts = linea.split(";");
                if (parts.length >= 5) {
                    String id = parts[0];
                    String matriculaAvion = parts[1];
                    String idRuta = parts[2];
                    String dia = parts[3];
                    String hora = parts[4];
                    arr[idx++] = new Vuelo(id, matriculaAvion, idRuta, dia, hora);
                } else {
                    System.err.println("Linea Vuelos con formato inesperado: " + linea);
                }
            }
        } catch (IOException ex) {
            System.err.println("Error leyendo Vuelos (segunda pasada): " + ex.getMessage());
        }
        return arr;
    }

    // --------- Escritura ---------
    private static void saveAviones(String path, Avion[] lista) {
        try (FileWriter fw = new FileWriter(path); BufferedWriter bw = new BufferedWriter(fw)) {
            for (Avion a : lista) {
                if (a == null) continue;
                bw.write(String.join(";",
                        a.getMatricula(),
                        a.getModelo(),
                        String.valueOf(a.getCapacidad()),
                        String.valueOf(a.getAsientos()),
                        String.valueOf(a.getPrecio())));
                bw.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error escribiendo Aviones: " + ex.getMessage());
        }
    }

    private static void saveRutas(String path, Ruta[] lista) {
        try (FileWriter fw = new FileWriter(path); BufferedWriter bw = new BufferedWriter(fw)) {
            for (Ruta r : lista) {
                if (r == null) continue;
                bw.write(String.join(";",
                        r.getId(),
                        r.getOrigen(),
                        r.getDestino(),
                        String.valueOf(r.getDistancia()),
                        r.isInternacional() ? "Si" : "No"));
                bw.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error escribiendo Rutas: " + ex.getMessage());
        }
    }

    private static void saveVuelos(String path, Vuelo[] lista) {
        try (FileWriter fw = new FileWriter(path); BufferedWriter bw = new BufferedWriter(fw)) {
            for (Vuelo v : lista) {
                if (v == null) continue;
                bw.write(String.join(";",
                        v.getId(),
                        v.getMatriculaAvion(),
                        v.getIdRuta(),
                        v.getDia(),
                        v.getHora()));
                bw.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error escribiendo Vuelos: " + ex.getMessage());
        }
    }

    // --------- Helpers ---------
    private static int parseIntSafe(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return 0; }
    }

    private static long parseLongSafe(String s) {
        try { return Long.parseLong(s.trim()); } catch (Exception e) { return 0L; }
    }

    private static int countNonNull(Object[] arr) {
        if (arr == null) return 0;
        int c = 0; for (Object o : arr) if (o != null) c++; return c;
    }

    // --------- Clases simples para objetos ---------
    static class Avion {
        private String matricula;
        private String modelo;
        private int capacidad;
        private int asientos;
        private long precio;

        public Avion(String matricula, String modelo, int capacidad, int asientos, long precio) {
            this.matricula = matricula;
            this.modelo = modelo;
            this.capacidad = capacidad;
            this.asientos = asientos;
            this.precio = precio;
        }

        public String getMatricula() { return matricula; }
        public String getModelo() { return modelo; }
        public int getCapacidad() { return capacidad; }
        public int getAsientos() { return asientos; }
        public long getPrecio() { return precio; }
        public void setModelo(String modelo) { this.modelo = modelo; }

        @Override
        public String toString() {
            return matricula + " - " + modelo + " (cap:" + capacidad + ", asientos:" + asientos + ", precio:" + precio + ")";
        }
    }

    static class Ruta {
        private String id;
        private String origen;
        private String destino;
        private int distancia;
        private boolean internacional;

        public Ruta(String id, String origen, String destino, int distancia, boolean internacional) {
            this.id = id; this.origen = origen; this.destino = destino; this.distancia = distancia; this.internacional = internacional;
        }

        public String getId() { return id; }
        public String getOrigen() { return origen; }
        public String getDestino() { return destino; }
        public int getDistancia() { return distancia; }
        public boolean isInternacional() { return internacional; }
    }

    static class Vuelo {
        private String id;
        private String matriculaAvion;
        private String idRuta;
        private String dia;
        private String hora;

        public Vuelo(String id, String matriculaAvion, String idRuta, String dia, String hora) {
            this.id = id; this.matriculaAvion = matriculaAvion; this.idRuta = idRuta; this.dia = dia; this.hora = hora;
        }

        public String getId() { return id; }
        public String getMatriculaAvion() { return matriculaAvion; }
        public String getIdRuta() { return idRuta; }
        public String getDia() { return dia; }
        public String getHora() { return hora; }
    }

}
