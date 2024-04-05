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
   public void mostrarMapa(String filePath) throws Exception {
		InputStreamReader sourceReader = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(sourceReader);
		String linea = "";
		int contador=0;
		while ( (linea=reader.readLine() ) != null) {
		    if(contador==0){
		        continue;
		    }
		    String nuevaLinea="";
		    contador++;
		    for(int i=0;i<linea.length();i++){
			    char letra=linea.charAt(i);
			    if(letra=='X'){
			    
			    } else if(letra=='.'){
			    
			    }else if(letra=='G'){
			    
			    }else if(letra=='E'){
			    
			    }
			    System.out.print(letra);
			}
		}
	}
	public static void main(String [] args){
	    Laberint lab=new Laberint();
	    try{
	        lab.mostrarMapa("laberint01.dat");
        } catch(Exception e){
            
        }
	}
}
