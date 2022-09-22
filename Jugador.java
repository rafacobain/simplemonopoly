import java.util.ArrayList;

public class Jugador{
    private String nombre;
    private String figura;
    private double dinero;
    private ArrayList<Casilla> misCalles = new ArrayList<Casilla>();
    private int posicion;

    private boolean carcel = false;
    private int pena = 0;

    private final double DINEROINICIAL = 900;

    public Jugador(String nombre, String figura){
        this.nombre = nombre;
        this.figura = figura;
        this.dinero = DINEROINICIAL;
        this.posicion = 0;
    }

// -------------------------------------------------- SET:

    public void setNombre(String nuevoNombre){
       this.nombre = nuevoNombre;
    }
    public void setPosicion(int nuevaPosicion){
       this.posicion = nuevaPosicion;
    }
    public void setFigura(String nuevaFigura){
        this.figura = nuevaFigura;
    }
    public void setDinero(double nuevoDinero){
        this.dinero = nuevoDinero;
    }
    public void setMisCalles(ArrayList<Casilla> nuevoMisCalles){
        this.misCalles = nuevoMisCalles;
    }
    public void setCarcel(boolean carcel){
      this.carcel = carcel;
    }
    public void setPena(int pena){
      this.pena = pena;
    }
    
// -------------------------------------------------- GET:

    public double getDinero(){
        return this.dinero;
    }
    public double getDINEROINICIAL(){
        return this.DINEROINICIAL;
    }
    public int getPosicion(){
        return this.posicion;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getFigura(){
        return this.figura;
    }
    public ArrayList<Casilla> getMisCalles(){
        return this.misCalles;
    }
    public boolean getCarcel(){
      return this.carcel;
    }
    public int getPena(){
      return this.pena;
    }


// ----------------------------------------- COMPRA/VENTA:

    public void anadirCalle(Casilla miNuevaCalle){
        this.misCalles.add(miNuevaCalle);
    }

    public void perderCalle(Casilla miAntiguaCalle){
        this.misCalles.remove(miAntiguaCalle);
    }

// --------------------------------------------- IMPRIMIR:

    public String imprimeCalles(){
        System.out.print("\n    CALLES:\n"); 
        String salida = "";
        for (Casilla c:this.misCalles){
            salida = salida + ("\n         ---------------- " + c.getNombre() + "\n         COLOR: " + c.getColor() + "\n         COMPRA: " + c.getPrecioCompra() + "\n         ALQUILER: " + c.getPrecioAlquiler() + "\n");
        }
        return salida + "\n";
    }

    public String toString(){
        return "\n----------------------------------------- " + this.nombre + "\n  FICHA: "
        + this.figura + "\n  FONDOS: " + this.dinero + "\n  POSICION: "
        + this.posicion + "\n  CALLES:" + this.imprimeCalles()
        + "-----------------------------------------\n\n";
    }


}