/*Exercici UF4_01.
Integrants de la colaboració:
	- Jose Salinas
Descripció: Joc Mazegame


*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Mazegame {
	public static void MostraMenuPrincipal() {
	
	}

	public static void main(String[] args) throws IOException {
		//Mostra benvinguda
		System.out.println("Joc del laberint");
		System.out.println("================");
		System.out.println("H: mostra ajuda");
		System.out.println("");
		System.out.println("Laberint: " + args[0]);
		
		//Llegeix fitxer y mostra
		InputStreamReader cursor = new FileReader(args[0]);
		BufferedReader reader = new BufferedReader(cursor);
		String linea = "";
		while ( (linea=reader.readLine() ) != null) {
			System.out.println(linea);
		}
		
		//Llegeix opcio y executa
	}
	
	static void executaOpcio() {
		System.out.print("> ");
		String userInput = new BufferedReader(new InputStreamReader(System.in)).readLine();
		switch (userInput.toLowerCase()) {
			case "h":
				mostrar_help();
				break;
			case "l":
				girar_esquerra();
				break;
			case "r":
				girar_dreta();
				break;
			case "f":
				break;
			case "nf":
				break;
			case "q":
				System.out.println("Adeu CAGUETA!");
				System.exit(0);
			default:
				System.out.println("Esculll una de les opcions→");
		}
	}
	
	static void mostrarHelp() {
		String helpText = """
		Les opcions disponibles són:
		H: mostra aquest text d\'ajuda
		L: gira a l'esquerra
		R: gira a la dreta
		F: mou una passa endavant
		nF: mou n passes endavant
		Q: Sortir
		"""
		System.out.println(helpText);
	}
	static void girar_esquerra() {
		System.out.println("girar_esquerra");
	}
	static void girar_dreta() {
		System.out.println("girar_dreta");
	}
}
