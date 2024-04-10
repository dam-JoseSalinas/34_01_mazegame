public class Avatar{
    private String nombre;
    private int intents;
    private char[][] mapaInvisibleActual;
    
    //CONSTRUCTOR
    public Avatar(String nombre, char[][] mapaInvisibleActual) {
        this.nombre = nombre;
        this.mapaInvisibleActual = mapaInvisibleActual;
    }
    
    //GETTERS SETTERS
    public String getNombre() {
		return this.nombre;
	}
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIntents() {
		return this.intents;
	}
    public void setIntents(int intents) {
		this.intents = intents;
	}
    public char[][] getMapaInvisibleACtual() {
		return this.mapaInvisibleActual;
	}
    public void setMapaInvisibleActual(char[][] mapaInvisibleActual) {
		this.mapaInvisibleActual = mapaInvisibleActual;
	}


    //METODES
    public void giraEsquerra() {

    }
    public void giraDreta() {

    }
    public void avança(int pases) {

    }
    public void avança1() {
        avança(1);
    }
    
}
