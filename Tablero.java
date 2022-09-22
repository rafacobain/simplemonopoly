import java.util.ArrayList;

public class Tablero{

    private ArrayList<Casilla> casillas = new ArrayList<>();

    public Tablero(){
        this.casillas.add(Casilla.SALIDA);         //1
        this.casillas.add(Casilla.CASILLA5);    //2
        this.casillas.add(Casilla.CASILLA6);    //3
        this.casillas.add(Casilla.CASILLA7);    //4
        this.casillas.add(Casilla.SUERTE);      //5
        this.casillas.add(Casilla.CASILLA8);    //6
        this.casillas.add(Casilla.CASILLA9);    //7
        this.casillas.add(Casilla.CARCEL);         //8
        this.casillas.add(Casilla.CASILLA10);   //9
        this.casillas.add(Casilla.COMUNIDAD);   //10
        this.casillas.add(Casilla.CASILLA11);   //11
        this.casillas.add(Casilla.CASILLA12);   //12
        this.casillas.add(Casilla.CASILLA13);   //13
        this.casillas.add(Casilla.CASILLA14);   //14
        this.casillas.add(Casilla.PARKING);         //15
        this.casillas.add(Casilla.CASILLA15);   //16
        this.casillas.add(Casilla.CASILLA16);   //17
        this.casillas.add(Casilla.SUERTE);      //18
        this.casillas.add(Casilla.CASILLA17);   //19
        this.casillas.add(Casilla.CASILLA18);   //20
        this.casillas.add(Casilla.CASILLA19);   //21
        this.casillas.add(Casilla.VEALACARCEL);     //22
        this.casillas.add(Casilla.COMUNIDAD);   //23
        this.casillas.add(Casilla.CASILLA20);   //24
        this.casillas.add(Casilla.CASILLA21);   //25
        this.casillas.add(Casilla.CASILLA22);   //26
        this.casillas.add(Casilla.CASILLA23);   //27
        this.casillas.add(Casilla.CASILLA24);   //28
    }

    public Casilla getCalle(int pos){
        return this.casillas.get(pos);
    }

    public ArrayList<Casilla> getCasillas(){
        return this.casillas;
    }

}