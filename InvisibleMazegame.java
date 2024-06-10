/*Exercici UF4_01.
Integrants de la colaboració:
	- Jose Salinas
	- Sergi Baulies Canovas
	- Delfina Pedrerol
Descripció: Joc Mazegame
*/


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Thread;

public class InvisibleMazegame {

	// M E M B R E S
	// atributs
	private Avatar avatar;
	private Laberint laberint;

	public InvisibleMazegame(String filename, String nomAvatar) {
		this.laberint = new Laberint(filename);
		this.avatar = new Avatar(nomAvatar, this.laberint.getMapaInvisible());
	}

	

	// metodes
	public void play() {
		//executa bucle
		while (true) {
			mostrarLentament(0,
							laberint.mostraInformacio(),
							laberint.getMapaInvisibleSerialitzat());
			executaComanda();
		}
	}
	//COMANDA
	public void executaComanda() {
		int status = readComanda();
		if (status == 2) {
			System.exit(0);
		}
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();	
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public int readComanda() {
		//segons el cas es retorna un status
		//status: 0 -> correcte, 1 -> error, 2 -> sortir
		//Llegir comanda
		System.out.print("\n> ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String comanda = "";
		try {
			comanda = reader.readLine();	
		} catch (IOException e) {
			return 1;
		}
		
		//Validar comanda
			//existeix un o varis alfanimerics sense cap espai(es redundant perque un espai no es alfanumeric, pero esta be mostra-ho).
			//Tambe pot tenir o no espais espais al principi o final
		if (!comanda.matches("\\s*\\d*\\[A-Za-z]+\\w+\\s*")) {
			//error
			System.out.println("Esculll una de les opcions →");
			return 1;
		}
		
		//Segons la comanda executar procediment
		comanda = comanda.strip().toLowerCase();
			if (comanda.equals("h")) {
				this.mostrarHelp();
			} else if (comanda.equals("l")) {
				avatar.giraEsquerra();
			} else if (comanda.equals("r")) {
				avatar.giraDreta();
			} else if (comanda.equals("q")) {
				System.out.println("Adeu CAGUETA!");
				return 2; //-> System.exit(0);
			} else if (comanda.equals("f")) {
				avatar.avança1();
			} else if (comanda.matches("\\d+f")) {
				int pases = Integer.parseInt(comanda.replaceAll(".*?(\\d+).*", "$1"));
				avatar.avança(pases);
			}
		System.out.println("Enter per continuar");
		return 0;
				
				
		
	}	

	// MISSATGES
	public static void mostrarLentament(float secs, String... text) {
		for (String linea : text) {
			for (char lletra : linea.toCharArray()) {
				System.out.print(lletra);
				try {
					Thread.sleep((long)(secs*1000));
				} catch (InterruptedException e){}
			}
			System.out.print("\n");
		}
	}

	public static void mostraBenvinguda() {
		mostrarLentament(0.05f, 
				"Joc del laberint", 
						"================",
						"H: mostra ajuda");
	}

	public void mostrarHelp() {
		mostrarLentament(0.001f, 
				"Les opcions disponibles són:",
						"H, h: mostra aquest text d\'ajuda",
						"L, l: gira a l'esquerra",
						"R, r: gira a la dreta",
						"F, f: mou una passa endavant",
						"nF, nf: mou n passes endavant",
						"Q, q: Sortir");
	}
	


	public static void main(String[] args) {
		//Mostrar capçalera
		mostraBenvinguda();
		//1. ARGUMENTS
		//Validicio argument existent
		if (args.length == 0) {
			System.out.println("No s'ha intrdouit cap argument. Espificifica un nom de laberint");
			return;
		}
		ArrayList<String>[] comParsed = parseArguments(args);
		
		//Validacio fitxer
		ArrayList<String> validsFilenames = getFilenameValids(comParsed[0]);
		if (validsFilenames.size() == 0) {
			System.out.println("No s'ha trobat cap fitxer valid. Espificifica un nom de laberint");
			return;
		}

		// CONFIGURACIO PER ARGUMENTS
		//mostrar ajuda directament
		//pases que es fara directament sense executar gameplay
		//limit d'intents
		//

		//INICI JOC
		
		String avatarname = obtenirAvatarname();

		for (String filename : validsFilenames) {
			InvisibleMazegame game = new InvisibleMazegame(filename, avatarname);
			game.play();
		}
	}

	//
	public static String obtenirAvatarname() {
		InputStreamReader src = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(src);
		try {
			String avatarname = reader.readLine();	
			reader.close();
			return avatarname;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	
	
	//METODES DE ARGUMENTS
	public static ArrayList<String>[] parseArguments(String[] args) {
		ArrayList<String> mazes = new ArrayList<String>();
		ArrayList<String> pases = new ArrayList<String>();
		ArrayList<String> avatarName = new ArrayList<String>(); 

		String flagActual = null;
		for (String arg : args) {
            if (arg.startsWith("-")) {
                flagActual = arg;
            } else {
				if(flagActual == null || flagActual.equals("-mazes")) {
					mazes.add(arg);
				} else if(flagActual.equals("-pases")) {
					pases.add(arg);
				} else if(flagActual.equals("-avatar-name")) {
					avatarName.add(arg);
				}
            }
        }
		ArrayList<String>[] comParsed = new ArrayList[]{mazes, pases, avatarName}; //Type safety: The expression of type ArrayList[] needs unchecked conversion to conform to ArrayList<String>[]
		return comParsed;
	}


	//METODES DE FITXERS
	public static ArrayList<String> getFilenameValids(ArrayList<String> filenames) {
		//if (!filename.matches("^\\w+[^\\s]\\w+$"))
		ArrayList<String> filenamesValids = new ArrayList<>();
		for (String filename : filenames) {
			//Validacio de fitxer existent
			filename = corregirFilename(filename);
			File file = new File(filename);
			if (file.exists()) {
				filenamesValids.add(filename);
			}
		}
		return filenamesValids;
	}
	public static String corregirFilename(String filename) {
		if(!filename.endsWith(".dat")) {
			filename += ".dat";
		}
		return filename;
	}

}
