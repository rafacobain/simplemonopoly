import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String preferencia;

        Monopoly monopoly = new Monopoly();
        Tablero tablero = new Tablero();
        Jugador jug1 = new Jugador("JUG1", "ZAPATO");
        Jugador jug2 = new Jugador("JUG2", "SOMBRERO");
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

        int opcionMenu = 0;
        int tiradaDados = 0;
        boolean turnoTerminado = false;
        Jugador propietarioCalle;
        Casilla calleEnCuestion;
        CajaDeComunidad cartaComunidad;
        
        System.out.print("Introduce un nombre para el primer jugador: ");
        preferencia = sc.nextLine();
        jug1.setNombre(preferencia);
        System.out.print("Introduce una figura para el primer jugador: ");
        preferencia = sc.nextLine();
        jug1.setFigura(preferencia);

        System.out.println();

        System.out.print("Introduce un nombre para el segundo jugador: ");
        preferencia = sc.nextLine();
        jug2.setNombre(preferencia);
        System.out.print("Introduce una figura para el segundo jugador: ");
        preferencia = sc.nextLine();
        jug2.setFigura(preferencia);

        jugadores.add(jug1);
        jugadores.add(jug2);

        System.out.println();

// PARTIDA --------------------------------------------------------------------------------------------------------------------------------

        while (!monopoly.jugadorArruinado(jugadores)){

            for (int i = 0; (i < jugadores.size()) && (!monopoly.jugadorArruinado(jugadores)); i++){

                turnoTerminado = false;
                
                System.out.println("------------------------------------------------------------ TURNO DEL JUGADOR " + jugadores.get(i).getFigura().toUpperCase() + " (" + jugadores.get(i).getNombre().toUpperCase() + ")     -- POSICION: " + jugadores.get(i).getPosicion());

                while (!turnoTerminado){
                    opcionMenu = monopoly.menu(jugadores.get(i));

                    if (opcionMenu == 1){

                        if (jugadores.get(i).getCarcel()) tiradaDados = monopoly.tirarDadosCarcel(jugadores.get(i));
                        else tiradaDados = monopoly.tirarDados();

                        monopoly.moverJugador(jugadores.get(i), tiradaDados, tablero);
                        System.out.println("Casilla numero [" + jugadores.get(i).getPosicion() + "]");
                        calleEnCuestion = tablero.getCalle(jugadores.get(i).getPosicion());

//-----------CALLE

                        if (calleEnCuestion.getTipo() == "Calle"){
                            if (monopoly.calleConDueno(jugadores, calleEnCuestion)){
                                propietarioCalle = monopoly.quienEsElDueno(jugadores, calleEnCuestion);
                                if (jugadores.get(i) != propietarioCalle){
                                    monopoly.pagarAlquiler(jugadores.get(i), propietarioCalle, calleEnCuestion);
                                }
                                else if (jugadores.get(i) == propietarioCalle){
                                    monopoly.venderMiCalle(jugadores.get(i), calleEnCuestion);
                                }
                            }
                            else {
                                monopoly.comprar(jugadores.get(i), calleEnCuestion);
                            }
                        }

//-------COMUNIDAD

                        else if(calleEnCuestion.getTipo() == "Caja de la Comunidad"){

                            cartaComunidad = monopoly.cartaCajaDeComunidadAleatoria();

                            System.out.println("\n-----------------------------COMUNIDAD-----------------------------\n");
                            System.out.println(cartaComunidad.getEnunciado());
                            System.out.println("\n-------------------------------------------------------------------\n");
                            jugadores.get(i).setDinero(jugadores.get(i).getDinero() + cartaComunidad.getDinero());
                            System.out.println("TIENES " + jugadores.get(i).getDinero() + " euros\n");
                        }

//----------SUERTE

                        else if(calleEnCuestion.getTipo() == "Suerte"){
                            monopoly.sacarTarjetaSuerte(jugadores.get(i));
                        }

//---------PARKING
                        
                        else if(calleEnCuestion.getTipo() == "Parking"){
                            System.out.println("Caiste en el Parking");
                        }

//----------SALIDA
                        
                        else if(calleEnCuestion.getTipo() == "Salida"){
                            System.out.println("Caiste en la casilla de salida.");
                        }

//-----VEALACARCEL

                        else if(calleEnCuestion.getTipo() == "VeALaCarcel"){
                            System.out.println("\n--------------------------VE A LA CARCEL--------------------------\n");
                            System.out.print("Tanta tonteria... al final te han pillado. Acabas en la carcel.\n");
                            System.out.println("\n------------------------------------------------------------------");
                            jugadores.get(i).setPosicion(7);
                            jugadores.get(i).setCarcel(true);
                            jugadores.get(i).setPena(0);
                        }

//----------------

                        else if(calleEnCuestion.getTipo() == "Carcel"){
                            System.out.println("Vas a la carcel... de visita.");
                        }


                        turnoTerminado = true;

                    } else if (opcionMenu == 2){
                        System.out.println(jugadores.get(i).imprimeCalles());
                    }

                    System.out.println();
                }
            }
        }

        System.out.println("El jugador " + monopoly.queJugadorSeHaArruinado(jugadores).getFigura().toUpperCase() + " (" + monopoly.queJugadorSeHaArruinado(jugadores).getNombre().toUpperCase() + ") se ha ARRUINADO.");
    }
}
