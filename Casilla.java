public enum Casilla {

    SALIDA("Salida","","", 0),
    PARKING("Parking","","",0),
    SUERTE("Suerte","","",0),
    COMUNIDAD("Caja de la Comunidad","","",0),
    CASILLA5("Calle","Mount Hagen","Rojo", 100),
    CASILLA6("Calle","Valentine","Rojo", 110),
    CASILLA7("Calle","Van Horn","Rojo", 120),
    CASILLA8("Calle","Emerald Ranch","Azul",130),
    CASILLA9("Calle","Annesburg","Azul",160),
    CASILLA10("Calle","Rhodes","Azul",170),
    CASILLA11("Calle","Mount Shann","Verde",180),
    CASILLA12("Calle","Wapiti Indian Reservation","Verde",200),
    CASILLA13("Calle","Fort Wallace","Verde",210),
    CASILLA14("Calle","Caliga Hall","Naranja",220),
    CASILLA15("Calle","Lagras","Naranja",240),
    CASILLA16("Calle","Lakay","Naranja",250),
    CASILLA17("Calle","Thieves Landing","Morado",260),
    CASILLA18("Calle","Manzanita Post","Morado",300),
    CASILLA19("Calle","Rathskeller Fork","Morado",320),
    CASILLA20("Calle","Guarma","Rosa",340),
    CASILLA21("Calle","Tumbleweed","Rosa",400),
    CASILLA22("Calle","Armadillo","Rosa",430),
    CASILLA23("Calle","Blackwater","Gris",460),
    CASILLA24("Calle","Saint Denis","Gris",500),
    VEALACARCEL("VeALaCarcel","","", 0),
    CARCEL("Carcel", "", "", 0);

    private String tipo;
    private String nombre;
    private String color;
    private double precioCompra;
    private double precioAlquiler;

    Casilla (String tipo, String nombre, String color, double precioCompra){
        this.tipo = tipo;
        this.nombre = nombre;
        this.color = color;
        this.precioCompra = precioCompra;
        this.precioAlquiler = porcentaje(precioCompra);
    }

    public String getTipo(){
        return this.tipo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getColor(){
        return this.color;
    }
    public double getPrecioCompra(){
        return this.precioCompra;
    }
    public double getPrecioAlquiler(){
        return this.precioAlquiler;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setColor(String color){
        this.color = color;
    }    
    public void setPrecioCompra(double precioCompra){
        this.precioCompra = precioCompra;
    }
    public void setPrecioAlquiler(double precioAlquiler){
        this.precioAlquiler = precioAlquiler;
    }

    public double porcentaje(double num){
		return this.precioCompra * 0.25;
    }
}    