import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Laberint{
   private int intents;
   private String fitxer;
   
   public String actualizarMapa(){
       
        return "";
   }
   public String[] convertirMapa(String filePath) throws Exception {
		InputStreamReader sourceReader = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(sourceReader);
		String linea = "";
		String text = "";
		int contador=0;
		int numFilas=0;
		int numColumnas=0;
		char[][] coordenadas;
		while ( (linea=reader.readLine() ) != null) {
			String nuevaLinea = "";
			//linea 0: se asignan cordenadas
			if(contador==0){
				numFilas=Character.getNumericValue(linea.charAt(0));
				numColumnas=Character.getNumericValue(linea.charAt(2));
			//resto lineas: mapa
			} else {
				//por cada linea se recorren las letras
				for(int indice=0;indice<linea.length();indice++){
					char letra=linea.charAt(indice);
					char nuevaLetra;

					//primera linea del mapa
					if (contador==1) {
						//primer caracter
						if(indice==0){
							nuevaLetra = MazeChars.CORNER_UL;
							nuevaLinea += nuevaLetra;
						//ultimo caracter
						} else if (indice==linea.length()-1) {
							nuevaLetra = MazeChars.CORNER_UR;
							nuevaLinea += nuevaLetra;
						//caracteres de en medio
						} else {
							nuevaLetra = MazeChars.LIMIT_H;
							nuevaLinea += nuevaLetra;

						}
					}

					//ultima linea del mapa
					if (contador==numFilas) {
						if(indice==0){
							nuevaLetra = MazeChars.CORNER_DL;
							nuevaLinea += nuevaLetra;
						} else if (indice==linea.length()-1) {
							nuevaLetra = MazeChars.CORNER_DR;
							nuevaLinea += nuevaLetra;
						} else {
							nuevaLetra = MazeChars.LIMIT_H;
							nuevaLinea += nuevaLetra;

						}
					} 

					if(contador>1 && contador<numFilas ){
						if (indice==0) {
							if(letra=='X'){
								nuevaLetra = MazeChars.LIMIT_V;
								nuevaLinea += nuevaLetra;
							}
							if(letra=='E'){
								nuevaLetra = MazeChars.ARROW_RIGHT;
								nuevaLinea += nuevaLetra;
							}
						} else if(indice==numColumnas) {
							if(letra=='X'){
								nuevaLetra = MazeChars.LIMIT_V;
								nuevaLinea += nuevaLetra;
							}
							if(letra=='G'){
								nuevaLetra = ' ';
								nuevaLinea += nuevaLetra;
							}

						}
						else {
							if(letra=='X'&&indice==numColumnas-1){
								nuevaLetra = MazeChars.LIMIT_V;
								nuevaLinea += nuevaLetra;
							} else if(letra=='X') {
								nuevaLetra=MazeChars.WALL;
								nuevaLinea += nuevaLetra;
							}
							if(letra=='.'){
								nuevaLetra = MazeChars.EMPTY;
								nuevaLinea += nuevaLetra;
							}
						}
					}

				}
			}
			
			text +=nuevaLinea+"\n";
			contador++;
			
		}
		String mapaVacio = vaciarMapa(text, numFilas, numColumnas);
		return new String[]{text, mapaVacio};
		
	}
	public String vaciarMapa(String mapa, int numFilas, int numColumnas){
		String[] lineas = mapa.split("\n");
		String text = "";
		int contador=0;
		for(String linea : lineas) {
			if(contador == 0) {
				text+=linea+"\n";
			}
			else if(contador == numFilas-1) {
				text+=linea+"\n";
			} else {
				String nuevaLinea = "";
				for (int i=0; i<linea.length(); i++) {
					char letra = linea.charAt(i);
					nuevaLinea+= ".";
				}
				text+=nuevaLinea+"\n"; 
			}
			contador++;
		}
		return text;
		/*
			String nuevaLinea = "";
			//primera linea del mapa
			if (contador==1) {
				nuevaLinea = linea;
			}
			//ultima linea del mapa
			else if (contador==numFilas) {
				nuevaLinea = linea;
			} else {
				//por cada linea se recorren las letras
				for(int indice=0;indice<linea.length();indice++){
					char nuevaLetra;
					if (indice==0) {
						nuevaLetra=linea.charAt(indice);
						nuevaLinea+=nuevaLetra;
					} else if(indice==numColumnas){
						nuevaLetra=linea.charAt(indice);
						nuevaLinea+=nuevaLetra;
					} else {
						nuevaLetra = MazeChars.EMPTY;
						nuevaLinea += nuevaLetra;
					}

				}
			}
			
			System.out.println(linea + contador);
			text +=nuevaLinea+"\n";
			contador++;
		
		}*/
		
	}
	public static void main(String [] args){
	    Laberint lab=new Laberint();
	    try{
	        String[] mapa = lab.convertirMapa("laberint01.dat");	
			System.out.println(mapa[0]);
			System.out.println(mapa[1]);
        } catch(Exception e){
            
        }
	}
}

