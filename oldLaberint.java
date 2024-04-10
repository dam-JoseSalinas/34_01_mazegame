
   public String convertirMapa() {
		FileReader sourceReader = new FileReader("fitxer");
		BufferedReader reader = new BufferedReader(sourceReader);
		
		
		String mapa = "";
		int comptadorLinea = 0;
		int numFiles = 0;
		int numColumnes = 0;
		
		String linea = "";
		while ( (linea=reader.readLine()) != null) {
			
			String novaLinea = "";
			//linea 0: se asignan cordenadas
			if(comptadorLinea==0){
				numFiles=Character.getNumericValue(linea.charAt(0));
				numColumnes=Character.getNumericValue(linea.charAt(2));
			//resto lineas: mapa
			} else {
				//por cada linea se recorren las letras
				
				for(int indice=0;indice<linea.length();indice++){
					char letra=linea.charAt(indice);
					char nuevaLetra;

					//primera linea del mapa
					if (comptadorLinea==1) {
						//primer caracter
						if(indice==0){
							nuevaLetra = MazeChars.CORNER_UL;
							novaLinea += nuevaLetra;
						//ultimo caracter
						} else if (indice==linea.length()-1) {
							nuevaLetra = MazeChars.CORNER_UR;
							novaLinea += nuevaLetra;
						//caracteres de en medio
						} else {
							nuevaLetra = MazeChars.LIMIT_H;
							novaLinea += nuevaLetra;

						}
					}

					//ultima linea del mapa
					if (comptadorLinea==numFiles) {
						if(indice==0){
							nuevaLetra = MazeChars.CORNER_DL;
							novaLinea += nuevaLetra;
						} else if (indice==linea.length()-1) {
							
							nuevaLetra = MazeChars.CORNER_DR;
							novaLinea += nuevaLetra;
						} else {
							nuevaLetra = MazeChars.LIMIT_H;
							novaLinea += nuevaLetra;

						}
					} 

					if(comptadorLinea>1 && comptadorLinea<numFiles ){
						if (indice==0) {
							if(letra=='X'){
								nuevaLetra = MazeChars.LIMIT_V;
								novaLinea += nuevaLetra;
							
							}
							if(letra=='E'){
								nuevaLetra = MazeChars.ARROW_RIGHT;
								novaLinea += nuevaLetra;
							}
						} else if(indice==numColumnes) {
							if(letra=='X'){
								nuevaLetra = MazeChars.LIMIT_V;
								novaLinea += nuevaLetra;
							}
							if(letra=='G'){
								nuevaLetra = ' ';
								novaLinea += nuevaLetra;
							}

						}
						else {
							if(letra=='X'&&indice==numColumnes-1){
								nuevaLetra = MazeChars.LIMIT_V;
								novaLinea += nuevaLetra;
							} else if(letra=='X') {
								nuevaLetra=MazeChars.WALL;
								novaLinea += nuevaLetra;
							}
							if(letra=='.'){
								nuevaLetra = MazeChars.EMPTY;
								novaLinea += nuevaLetra;
							}
						}
					}

				}
			}
			
			mapa +=novaLinea+"\n";
			comptadorLinea++;
			
		}
		reader.close();
		String mapaVacio = vaciarMapa(mapa, numFiles, numColumnes);
		return new String[]{mapa, mapaVacio};
		
	}

    public String actualitzarMapa(char[][] mapa){
		//BufferedWriter sortida = new BufferedWriter(new FileWriter(cami));
        return "";
   }
	public String vaciarMapa(String mapa, int numFiles, int numColumnes){
		
		String[] lineas = mapa.split("\n");
		String mapa = "";
		int comptadorLinea=0;
		for(String linea : lineas) {
			
			if(comptadorLinea == 0) {
				mapa+=linea+"\n";
			}
			else if(comptadorLinea == numFiles-1) {
				mapa+=linea+"\n";
			} else {
				String novaLinea = "";
				for (int i=0; i<linea.length(); i++) {
					
					char letra = linea.charAt(i);
					novaLinea+= ".";
				}
				mapa+=novaLinea+"\n"; 
			}
			comptadorLinea++;
		}
		return mapa;
		/*
			String novaLinea = "";
			//primera linea del mapa
			if (comptadorLinea==1) {
				novaLinea = linea;
			}
			//ultima linea del mapa
			else if (comptadorLinea==numFiles) {
				novaLinea = linea;
			} else {
				//por cada linea se recorren las letras
				for(int indice=0;indice<linea.length();indice++){
					
					char nuevaLetra;
					if (indice==0) {
						nuevaLetra=linea.charAt(indice);
						novaLinea+=nuevaLetra;
					} else if(indice==numColumnes){
						nuevaLetra=linea.charAt(indice);
						novaLinea+=nuevaLetra;
					} else {
						nuevaLetra = MazeChars.EMPTY;
						novaLinea += nuevaLetra;
					}

				}
			}
			
			System.out.println(linea + comptadorLinea);
			mapa +=novaLinea+"\n";
			comptadorLinea++;
		
		}*/