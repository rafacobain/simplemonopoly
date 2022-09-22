import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Monopoly {
    private Random rand;
    private Scanner sc;
    private ArrayList<CajaDeComunidad> cajaDeComunidad;

    public Monopoly(){
        rand = new Random();
        sc = new Scanner(System.in);
        cajaDeComunidad = new ArrayList<CajaDeComunidad>();
        cajaDeComunidad.add(CajaDeComunidad.CC1);
        cajaDeComunidad.add(CajaDeComunidad.CC2);
        cajaDeComunidad.add(CajaDeComunidad.CC3);
    }

    //----------------------------------------------

    public int menu(Jugador jug){

        System.out.println("\nTIENES " + jug.getDinero() + " euros\n");

        System.out.println("1. Tirar dados.\n2. Mostrar mis calles.");

        return eligeOpcion();
    }

    public int eligeOpcion(){
        int respuesta = -1;
        boolean respuestaEnRango = false;

        do{
            try{
                System.out.print("OPCION: ");
                respuesta = Integer.parseInt(sc.nextLine());
            }   catch (Exception e){
                System.out.println("Valor incorrecto");
            }

            if (respuesta == 1 || respuesta == 2){
                respuestaEnRango = true;
            }

        } while (!respuestaEnRango);

        return respuesta;
    }

    public int tirarDados(){
        int dado1 = rand.nextInt(6-1)+1;
        int dado2 = rand.nextInt(6-1)+1;

        System.out.println("Valor del dado 1: " + dado1);
        System.out.println("Valor del dado 2: " + dado2);

        return dado1+dado2;
    }

    public int tirarDadosCarcel(Jugador jug){
        int sale = 0;
        int dado1 = rand.nextInt(6-1)+1;
        int dado2 = rand.nextInt(6-1)+1;


        System.out.print("\n------------------------------CARCEL------------------------------\n");
        System.out.print("\nTurno de carcel: ");
        System.out.print(jug.getPena()+1);
        System.out.print("\n");
        System.out.println("Valor del dado 1: " + dado1);
        System.out.println("Valor del dado 2: " + dado2);

        if ((dado1 == dado2) || (jug.getPena() >=3)){
            System.out.print("Karem ha hecho la de prostituta borracha y consigue sacarte de la carcel.\n");
            jug.setCarcel(false);
            sale = dado1 + dado2;
        }
        else System.out.print("Hoy no.\n");
        System.out.print("\n------------------------------------------------------------------\n\n");
        jug.setPena(jug.getPena()+1);

        return sale;
    }

    public void moverJugador(Jugador jug, int pasos, Tablero tabl){
        int tamaTablero = tabl.getCasillas().size();

        for (int i = 0; i < pasos; i++){
            jug.setPosicion(jug.getPosicion()+1);

            if (jug.getPosicion() >= tamaTablero){
                jug.setPosicion(0);
                dineroCasillaInicial(jug);
            }
        }
    }

    private void dineroCasillaInicial(Jugador jug){
        double dineroASumar = jug.getDINEROINICIAL() * 0.1;

        jug.setDinero(jug.getDinero() + dineroASumar);
        System.out.println("Se suman " + dineroASumar + " euros al jugador " + jug.getFigura().toUpperCase() + " (" + jug.getNombre().toUpperCase() + ") por pasar por la primera casilla.");
        System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
        System.out.println("");
    }

    public boolean calleConDueno(ArrayList<Jugador> jugadores, Casilla calle){
        boolean propietario = false;
        
        for (Jugador i : jugadores){
            for (Casilla j : i.getMisCalles()){
                if (calle == j){
                    propietario = true;
                }
            }
        }

        return propietario;
    }

    public Jugador quienEsElDueno(ArrayList<Jugador> jugadores, Casilla calle){
        Jugador propietario = new Jugador("", "");

        for (Jugador i : jugadores){
            for (Casilla j : i.getMisCalles()){
                if (calle == j){
                    propietario = i;
                }
            }
        }

        return propietario;
    }

    public void comprar(Jugador jug, Casilla calle){
        int respuesta = -1;

        do{
            try{
                System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
                System.out.println("Quieres comprar la calle " + calle.getNombre() + "[" + calle.getPrecioCompra() + " euros]?");
                System.out.print("1. SI\n2. NO\nRESPUESTA: ");
                respuesta = Integer.parseInt(sc.nextLine());
            }   catch (Exception e){
                System.out.println("Valor incorrecto.\n");
            }
        }   while(respuesta != 1 && respuesta != 2);

        if (respuesta == 1){
            if (jug.getDinero() >= calle.getPrecioCompra()){
                jug.anadirCalle(calle);
                jug.setDinero(jug.getDinero()-calle.getPrecioCompra());
                System.out.println("Se ha comprado la calle");
                System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
                System.out.println("");
            }   else {
                System.out.println("No tienes dinero para comprar la calle");
            }
        }   else{
            System.out.println("No ha comprado la calle");
        }
    }

    public void pagarAlquiler(Jugador jug, Jugador jugRival, Casilla calle){

        if (jug.getDinero() - calle.getPrecioAlquiler() >= 0){
            jugRival.setDinero(jugRival.getDinero()+calle.getPrecioAlquiler());
            jug.setDinero(jug.getDinero()-calle.getPrecioAlquiler());
        } else {
            jugRival.setDinero(jugRival.getDinero()+calle.getPrecioAlquiler());
            jug.setDinero(0);
        }

        System.out.print("\n-----------------------------ALQUILER-----------------------------\n");
        System.out.println("\nCaiste en la calle " + calle.getNombre() + " del jugador " + jugRival.getFigura().toUpperCase() + " (" + jugRival.getNombre().toUpperCase() + ")");
        System.out.println("Pagaste " + calle.getPrecioAlquiler() + " euros de alquiler");
        System.out.print("\n------------------------------------------------------------------\n");
        System.out.println("\nTIENES " + jug.getDinero() + " euros");
        System.out.println("");
    }

    public void venderMiCalle(Jugador jug, Casilla calle){
        int respuesta = -1;

        System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
        System.out.println("");
        System.out.print("Eres el propietario de la calle "+calle.getNombre()+".\n");
        System.out.print("Puedes vender la calle a la banca por "+(calle.getPrecioCompra()*0.5)+" euros.\n");
        System.out.print("1. VENDER\n2. NO VENDER\n");
        respuesta = eligeOpcion();

        if (respuesta == 1){
            jug.perderCalle(calle);
            jug.setDinero(jug.getDinero()+calle.getPrecioCompra()*0.5);
            System.out.print("Has vendido la calle "+calle.getNombre()+".\n");
            System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
            System.out.println("");
        } else System.out.print("NO has vendido la calle "+calle.getNombre()+".\n");
    }

    public boolean jugadorArruinado(ArrayList<Jugador> jugadores){
        boolean arruinado = false;

        for (Jugador i : jugadores){
            if (i.getDinero() <= 0){
                arruinado = true;
            }
        }

        return arruinado;
    }

    public Jugador queJugadorSeHaArruinado(ArrayList<Jugador> jugadores){
        Jugador jug = new Jugador("NONE", "NONE");

        for (Jugador i : jugadores){
            if (i.getDinero() <= 0){
                jug = i;
            }
        }

        return jug;
    }

    public CajaDeComunidad cartaCajaDeComunidadAleatoria(){
        int aleatorio = rand.nextInt(cajaDeComunidad.size());

        return cajaDeComunidad.get(aleatorio);
    }

    public void sacarTarjetaSuerte(Jugador jugador){
        Suerte miSuerte = new Suerte();
        TarjetaSuerte miTarjetaSuerte = miSuerte.sacaTarjetaSuerte();

        System.out.print("\n------------------------------SUERTE------------------------------\n\n" + miTarjetaSuerte.getOpcion() + "\n(INTRO para continuar)\n");
        sc.nextLine();
        System.out.print("\n" + miTarjetaSuerte.getResolucion() + "\n");

        if (miTarjetaSuerte.getPrecio() < 0) System.out.print("Has perdido ");
        else System.out.print("Has ganado ");
        if (miTarjetaSuerte.getPrecio() < 0) System.out.print(-(miTarjetaSuerte.getPrecio()));
        else System.out.print(miTarjetaSuerte.getPrecio());

        System.out.print(" euros.\n");
        System.out.print("\n------------------------------------------------------------------\n");
        jugador.setDinero(jugador.getDinero() + miTarjetaSuerte.getPrecio());
        System.out.println("\nTIENES " + jugador.getDinero() + " euros\n");
    }
}