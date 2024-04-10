import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Laberint{
   private String fitxer;
   private FileReader sourceReader;
   private BufferedReader reader;

   private String nom;
   private int numFiles;
   private int numColumnes;
   private char[][] mapa;
   private char[][] mapaInvisible;

   //CONSTRUCTOR
	public Laberint(String fitxer) {
		this.fitxer = fitxer;
		this.nom = this.fitxer.replace(".dat", "");
		if (this.validarContingut() == 1) {System.exit(1);}
		mapa = new char[numFiles][numColumnes];
		mapaInvisible = new char[numFiles][numColumnes];
		procesarMapa();
		procesarMapaInvisible();
		
	}
	//MANIPULACIO INTERNA DEL FITXER PER OBTENIR MAPA
	private int validarContingut() {
		try {
			this.sourceReader = new FileReader(this.fitxer);
			this.reader = new BufferedReader(sourceReader);
			
			//Validar primera linea de coordenadas
			String primeraLinea = reader.readLine();
			//Uso de regex para encontrar y capturar un patron que: tenga al menos 2 digitos y en medio de estas una x
			this.numFiles = Integer.parseInt(primeraLinea.replaceAll("\\s*?(\\d+)\\s*?[xX]\\s*?(\\d+)\\s*", "$1"));
			this.numColumnes = Integer.parseInt(primeraLinea.replaceAll("\\s*?(\\d+)\\s*?[xX]\\s*?(\\d+)\\s*", "$2"));

			/*
			 * TODO: Validacion de resto de lineas
			 * por ahora se presupone que el formato es correcto: tamaño de lines respecto a coordenadas correcto, letras correspondietes usadas, ...
			 */
		} 
		catch (FileNotFoundException e) {
			System.out.println("El fichero " + this.fitxer + " no existe");
			return 1;
		} catch (IOException e) {
			System.out.println("Error en el reader de validaContingut");
			return 1;
		} catch (NumberFormatException e) {
			System.out.println("El fichero no utiliza un formato correcto de coordenadas");
			return 1;
		}
		return 0;
	}

    private void procesarMapa() {
		try {
			String lineaActual = "";
			int numFilaActual = 0;
			while ( (lineaActual=reader.readLine()) != null) {
				for (int numColumnaActual = 0; numColumnaActual < lineaActual.length(); numColumnaActual++) {
					char mapChar = converteixCaracter(Character.toUpperCase(lineaActual.charAt(numColumnaActual)), numFiles-1, numColumnes-1, numFilaActual, numColumnaActual);
					mapa[numFilaActual][numColumnaActual] = mapChar;
				}
				numFilaActual++;
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Error en el reader de procesaMapa");
		}
    }

	private void recorrerMapa() {

	}
	private void procesarMapaInvisible() {

	}

	private char converteixCaracter(char lletra, int nFiles, int nCols, int filaActual, int colActual) {
		if (lletra == 'X') {
			//esquinas
			if (filaActual == 0 && colActual == 0) {return MazeChars.CORNER_UL;}
			if (filaActual == 0 && colActual == nCols) {return MazeChars.CORNER_UR;}
			if (filaActual == nFiles && colActual == 0) {return MazeChars.CORNER_DL;}
			if (filaActual == nFiles && colActual == nCols) {return MazeChars.CORNER_DR;}
			//filas limites
			if (filaActual == 0 && colActual > 0 && colActual < nCols) {return MazeChars.LIMIT_H;}
			if (filaActual == nFiles && colActual > 0 && colActual < nCols) {return MazeChars.LIMIT_H;}
			//filas laterales
			if (colActual == 0 && filaActual > 0 && filaActual < nCols) {return MazeChars.LIMIT_V;}
			if (colActual == nCols && filaActual > 0 && filaActual < nCols) {return MazeChars.LIMIT_V;}
			return MazeChars.WALL;

		}
		if (lletra == 'E') {
			if (filaActual == 0) {return MazeChars.ARROW_DOWN;}
			if (filaActual == nFiles) {return MazeChars.ARROW_UP;}
			if (colActual == 0) {return MazeChars.ARROW_RIGHT;}
			if (colActual == nCols) {return MazeChars.ARROW_LEFT;}
		}
		if (lletra == 'G') {
			return ' ';
		}
		if (lletra == '.') {
			return MazeChars.EMPTY;
		}
		return '!';
	
	}




	//METODES: getter i setters
	public String getNom() {
		return this.nom;
	}
	public char[][] getMapa() {
		return mapa;
	}
	public int getNumFiles() {
		return this.numFiles;
	}
	public int getNumColumnes() {
		return this.numColumnes;
	}
	public char[][] getMapaInvisible() {
		return this.mapaInvisible;
	}

	public String SerializarMapa(char[][] mapa) {
		String textMapa = "";
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[i].length; j++) {
				textMapa += mapa[i][j];
			}
			textMapa += "\n";
		}
		return textMapa;
	}
	public String getMapaActualSerialitzat() {
		return SerializarMapa(this.mapa);
	}
	public String getMapaInvisibleSerialitzat() {
		return SerializarMapa(this.mapaInvisible);
	}



	//METODOES PEL JOC

	public String mostraInformacio() {
		return "";
	}

	public String actualitzarMapaInvisible() {
		return "";
	}
	
	

	public static void main(String [] args){
		
	    
	}
}

