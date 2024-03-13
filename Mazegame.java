/*Exercici UF4_01.
Integrants de la colaboració:
	- Jose Salinas
	- Sergi Baulies Canovas
	- Delfina Pedrerol
Descripció: Joc Mazegame
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Mazegame {
	public static String filePath;

	public static void mostraBenvinguda() {
		System.out.println("Joc del laberint");
		System.out.println("================");
		System.out.println("H: mostra ajuda");
		System.out.println("");
	}

	public static void mostrarMapa() throws Exception {
		InputStreamReader sourceReader = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(sourceReader);
		String linea = "";
		while ( (linea=reader.readLine() ) != null) {
			System.out.println(linea);
		}
	}
	public static void main(String[] args) {
		//Validicio argument de fitxer .dat existent
		if (args.length < 1) {return;}
		filePath = args[0];
		
		//Mostrar capçalera
		mostraBenvinguda();
		
		//Mostrar informació i mapa
		int comptadorIntents = 1;
		while (true) {
			//Mostrar informacio
			System.out.println("Laberint: " + filePath);
			System.out.printf("%nIntents actuals: %d%n", comptadorIntents);
			comptadorIntents++;
			
			try {
			//Mostrar mapa
				mostrarMapa();
			//Executa comanda per actualitzar informacio i mapa
				executaComanda();
			//Enter per seguent bucle
				System.out.print("Enter per continuar");
				new BufferedReader(new InputStreamReader(System.in)).readLine();
			} catch (Exception e){}
		}
			
	}
	
	public static void executaComanda() throws Exception{
		//Llegir comanda
		System.out.print("> ");
		InputStreamReader sourceReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(sourceReader);
		String comanda = reader.readLine();
		
		//Segons la comanda executar procediment
		switch (comanda.toLowerCase()) {
			case "h":
				mostrarHelp();
				break;
			case "l":
				girarEsquerra();
				break;
			case "r":
				girarDreta();
				break;
			case "f":
				mouPasEndavant();
				break;
			case "nf":
				mouNPasEndavant();
				break;
			case "q":
				System.out.println("Adeu CAGUETA!");
				System.exit(0);
			default:
				System.out.println("Esculll una de les opcions→");
		}
	}
	
	public static void mostrarHelp() {
		String helpText = """
			Les opcions disponibles són:
			H: mostra aquest text d\'ajuda
			L: gira a l'esquerra
			R: gira a la dreta
			F: mou una passa endavant
			nF: mou n passes endavant
			Q: Sortir""";
		System.out.println(helpText);
	}
	public static void girarEsquerra() {
		System.out.println("girar_esquerra");
	}
	public static void girarDreta() {
		System.out.println("girar_dreta");
	}
	public static void mouPasEndavant() {
		System.out.println("endavant");
	}
	public static void mouNPasEndavant() {
		System.out.println("endavant N");
	}
}
