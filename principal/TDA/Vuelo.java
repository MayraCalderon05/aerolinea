package principal.TDA;
import java.time.LocalTime;

public class Vuelo {
    
    private String numeroVuelo;
    private String idAvion;
    private String idRuta;
    private String dia;
    private LocalTime horarioSalida;
    private int cantPasajeros;
    private boolean aterrizado;

    //constructores 
    public Vuelo(String numeroVuelo, Avion avion, Ruta ruta,String dia, LocalTime horarioSalida, int cantPasajeros, boolean aterrizado){
        this.numeroVuelo = numeroVuelo;
        this.idAvion = avion.getIdentificacion();
        this.idRuta = ruta.getNumRuta();
        this.dia = dia;
        this.horarioSalida = horarioSalida;
        this.cantPasajeros = avion.getCantAsientos();
        this.aterrizado = aterrizado;
    }

    public Vuelo(String numeroVuelo, Avion avion, Ruta ruta,String dia, LocalTime horarioSalida){
        this.numeroVuelo = numeroVuelo;
        this.idAvion = avion.getIdentificacion();
        this.idRuta = ruta.getNumRuta();
        this.dia = dia;
        this.horarioSalida = horarioSalida;
        this.cantPasajeros = avion.getCantAsientos();
        this.aterrizado = false;

    }

    public Vuelo(String numeroVuelo){
        this.numeroVuelo = numeroVuelo;
        this.idAvion = "";
        this.idRuta = "";
        this.dia = "";
        this.horarioSalida = LocalTime.parse("00:00");
        this.cantPasajeros = 0;
        this.aterrizado = false;
    }

    //Observadores

    public String getNumeroVuelo(){
        return this.numeroVuelo;
    }
    public String getIdAvion(){
        return this.idAvion;
    }
    public String getIdRuta(){
        return this.idRuta;
    }  
    public String getDia(){
        return this.dia;
    }
    public LocalTime getHorarioSalida(){
        return this.horarioSalida;
    }
    public int getCantPasajeros(){
        return this.cantPasajeros;
    }
    public boolean getAterrizado(){
        return this.aterrizado;
    }
    public boolean equals(Vuelo otroVuelo){
        return this.numeroVuelo.equals(otroVuelo.getNumeroVuelo());
        
    }
    public String toString(){
        return "Vuelo\n"+
               "Numero de vuelo: '" + getNumeroVuelo() + "'\n"+
               "ID Avion: '" + getIdAvion() + "'\n"+
               "ID Ruta: '" + getIdRuta() + "'\n"+
               "Dia: '" + getDia() + "'\n"+
               "Horario de salida: " + getHorarioSalida() + "\n"+
               "Cantidad de pasajeros: " + getCantPasajeros() + "\n"+
               "Aterrizado: " + getAterrizado() + "\n";
    }
    //Modificadores
    public void setIdAvion(Avion nuevoIdAvion){
        this.idAvion = nuevoIdAvion.getIdentificacion();
    }   
    public void setIdRuta(Ruta nuevoIdRuta){
        this.idRuta = nuevoIdRuta.getNumRuta();
    }
    public void setDia(String nuevoDia){
        this.dia = nuevoDia;
    }
    public void setHorarioSalida(LocalTime nuevoHorarioSalida){
        this.horarioSalida = nuevoHorarioSalida;
    }
    public void setCantPasajeros(int nuevaCantPasajeros){
        this.cantPasajeros = nuevaCantPasajeros;
    }
    public void setAterrizado(boolean nuevoAterrizado){
        this.aterrizado = nuevoAterrizado;
    }
    
}