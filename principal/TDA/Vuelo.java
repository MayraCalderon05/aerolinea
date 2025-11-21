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
    public Vuelo(String numeroVuelo, String idAvion, String idRuta,String dia, LocalTime horarioSalida, int cantPasajeros, boolean aterrizado){
        this.numeroVuelo = numeroVuelo;
        this.idAvion = idAvion;
        this.idRuta = idRuta;
        this.dia = dia;
        this.horarioSalida = horarioSalida;
        this.cantPasajeros = cantPasajeros;
        this.aterrizado = aterrizado;

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
    public void setIdAvion(String nuevoIdAvion){
        this.idAvion = nuevoIdAvion;
    }   
    public void setIdRuta(String nuevoIdRuta){
        this.idRuta = nuevoIdRuta;
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