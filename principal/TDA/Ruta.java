package principal.TDA;

public class Ruta {
    private String numRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distancia;
    private boolean internacional;

    //constructores
    public Ruta(String numRuta) {
        this.numRuta = numRuta;
        this.ciudadOrigen = "";
        this.ciudadDestino = "";
        this.distancia = 0.0;
        this.internacional = false;
    }

    public Ruta(String numRuta, String ciudadOrigen, String ciudadDestino, double distancia, boolean internacional) {
        this.numRuta = numRuta;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distancia = distancia;
        this.internacional = internacional;
    }

    //metodos
    //Getters y Setters
    public String getNumRuta() {
        return this.numRuta;
    }
    public String getCiudadOrigen() {
        return this.ciudadOrigen;
    }
    public String getCiudadDestino() {
        return this.ciudadDestino;
    }
    public double getDistancia(){
        return this.distancia;
    }
    public boolean isInternacional() {
        return this.internacional;
    }

    public void setNumRuta(String nuevoNumRuta){
        this.numRuta = nuevoNumRuta;
    }
    public void setCiudadOrigen(String nuevaCiudadOrigen){
        this.ciudadOrigen = nuevaCiudadOrigen;
    }
    public void setCiudadDestino(String nuevaCiudadDestino){   
        this.ciudadDestino = nuevaCiudadDestino;
    }
    public void setDistancia(double nuevaDistancia){
        this.distancia = nuevaDistancia;
    }
    public void setInternacional(boolean nuevoInternacional){
        this.internacional = nuevoInternacional;
    }

    @Override
    public String toString() {
        return "Ruta\n" +
                "Numero de ruta: '" + getNumRuta() + "'\n" +
                "Ciudad de origen: '" + getCiudadOrigen() + "'\n" +
                "Ciudad de destino: '" + getCiudadDestino() + "'\n" +
                "Distancia: " + getDistancia() + "\n" +
                "Internacional: " + isInternacional() + "\n";
    }
    public boolean equals(Ruta otraRuta) {
        boolean sonIguales = false;
        if (this.numRuta.equals(otraRuta.getNumRuta())) {
            sonIguales = true;
        }
        return sonIguales;
    }
    
}
