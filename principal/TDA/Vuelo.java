package principal.TDA;
import java.time.LocalTime;

public class Vuelo {
    
    private String numeroVuelo;
    private Avion avion;
    private Ruta ruta;
    private String dia;
    private LocalTime horarioSalida;
    private boolean aterrizado;

    //constructores 
    //agregar vuelo desde el txt
    public Vuelo(String numeroVuelo, Avion avion, Ruta ruta,String dia, LocalTime horarioSalida, boolean aterrizado){
        this.numeroVuelo = numeroVuelo;
        this.avion = avion;
        this.ruta = ruta;
        this.dia = dia;
        this.horarioSalida = horarioSalida;
        this.aterrizado = aterrizado;
    }
    //agregar vuelo desde la interfaz
    public Vuelo(String numeroVuelo, Avion avion, Ruta ruta,String dia, LocalTime horarioSalida){
        this.numeroVuelo = numeroVuelo;
        this.avion = avion;
        this.ruta = ruta;
        this.dia = dia;
        this.horarioSalida = horarioSalida;
        this.aterrizado = false;

    }

    public Vuelo(String numeroVuelo){
        this.numeroVuelo = numeroVuelo;
        this.avion = null;
        this.ruta = null;
        this.dia = "";
        this.horarioSalida = LocalTime.parse("00:00");
        this.aterrizado = false;
    }

    //Observadores

    public String getNumeroVuelo(){
        return this.numeroVuelo;
    }
    public Avion getAvion(){
        return this.avion;
    }
    public Ruta getRuta(){
        return this.ruta;
    }  
    public String getDia(){
        return this.dia;
    }
    public LocalTime getHorarioSalida(){
        return this.horarioSalida;
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
               "Avion: '" + getAvion().getIdentificacion() + "'\n"+
               "Ruta: '" + getRuta().getNumRuta() + "'\n"+
               "Dia: '" + getDia() + "'\n"+
               "Horario de salida: " + getHorarioSalida() + "\n"+
               "Aterrizado: " + getAterrizado() + "\n";
    }
    //Modificadores
    //como se supone que quedan los modificadores de avion y ruta?
    
    public void setAvion(Avion nuevoAvion){
        this.avion = nuevoAvion;
    }
    public void setRuta(Ruta nuevaRuta){
        this.ruta = nuevaRuta;
    }
        
    public void setDia(String nuevoDia){
        this.dia = nuevoDia;
    }
    public void setHorarioSalida(LocalTime nuevoHorarioSalida){
        this.horarioSalida = nuevoHorarioSalida;
    }
    public void setAterrizado(boolean nuevoAterrizado){
        this.aterrizado = nuevoAterrizado;
    }
    
}