package principal.TDA;

public class Avion {
    private String identificacion;
    private String modelo;
    private int cantVuelos;
    private int cantAsientos;
    private double kmRecorridos;

    //Constructores
    public Avion(String identificacion) {
        this.identificacion = identificacion;
        this.modelo = "";
        this.cantVuelos = 0;
        this.cantAsientos = 0;
        this.kmRecorridos = 0.00;
    }
    //en caso de cargar un avion ya existente como en el caso del txt
    public Avion(String identificacion, String modelo, int cantVuelos, int cantAsientos, double kmRecorridos) {
        this.identificacion = identificacion;
        this.modelo = modelo;
        this.cantVuelos = cantVuelos;
        this.cantAsientos = cantAsientos;
        this.kmRecorridos = kmRecorridos;
    }
    //nuevo avion sin vuelos ni km recorridos
    public Avion(String identificacion, String modelo,  int cantAsientos) {
        this.identificacion = identificacion;
        this.modelo = modelo;
        this.cantVuelos = 0;
        this.cantAsientos = cantAsientos;
        this.kmRecorridos = 0.00;
    }
    //metodos
    //Getters y Setters
    public String getIdentificacion() {
        return this.identificacion;
    }
    public String getModelo() {
        return this.modelo;
    }
    public int getCantVuelos() {
        return this.cantVuelos;
    }
    public int getCantAsientos() {
        return this.cantAsientos;
    }
    public double getKmRecorridos() {
        return this.kmRecorridos;
    }

    public void setModelo(String nuevoModelo) {
        this.modelo = nuevoModelo;
    }
    public void setCantVuelos(int nuevaCantVuelos) {
        this.cantVuelos = nuevaCantVuelos;
    }
    public void setCantAsientos(int nuevaCantAsientos) {
        this.cantAsientos = nuevaCantAsientos;
    }
    public void setKmRecorridos(double nuevosKmRecorridos) {
        this.kmRecorridos = nuevosKmRecorridos;
    }
    @Override
    public String toString() {
        return "Avion\n" +
                "Identificacion: '" + getIdentificacion() +"'\n"+
                "Modelo: '" + getModelo() + "'\n" +
                "Cantidad de vuelos: " + getCantVuelos() + "\n" +
                "Cantidad de asientos: " + getCantAsientos() + "\n" +
                "Kilometros recorridos: " + getKmRecorridos() + "\n";
    }

    public boolean equals(Avion otroAvion) {
        boolean sonIguales = false;
        if (this.identificacion.equals(otroAvion.getIdentificacion())) {
            sonIguales = true;
        }
        return sonIguales;
    }
    
}
