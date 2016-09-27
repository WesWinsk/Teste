import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class Compactador {

	//Presentation presentation = new Presentation();

	public void load(String filename){
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader in = new BufferedReader(fr);
			String line;
			String x = "";
			StaticList<String> listapalavras = new StaticList<String>(100);
			StaticList<String> listacodigospalavras = new StaticList<String>(100);
			StaticList<String> codigos = new StaticList<String>(100);
			
			while((line = in.readLine())!= null){
				x = x + line;
			}
		
			 retiraSpacos(x,listapalavras);
			  
			transfereListas(listapalavras, codigos, listacodigospalavras);
			System.out.println(listacodigospalavras.toString());
			escreveEmArquivo(codigos, listacodigospalavras);
			
			
			
		}catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	public StaticList<String> retiraSpacos(String x,StaticList<String> listapalavras){
		 
		for (int i = 0, j = 0; i < x.length(); i++) {
			if(x.charAt(i)==' '){
			listapalavras.insert(x.substring(j, i),listapalavras.numElements());
				j = i;
			}
		}
		return listapalavras;
	}
	
	public void transfereListas(StaticList<String> palavras,StaticList<String> codigos,StaticList<String> palavrasecodigos){
		int codigolista = 1;
		for(int i = 0; i < palavras.numElements();i++)
		if((palavrasecodigos.search(palavras.get(i))== -1)){// Se não existe na lista
			palavrasecodigos.insert(codigolista+"-"+palavras.get(i), palavrasecodigos.numElements());
			codigos.insert(codigolista+"-",codigos.numElements());
			codigolista++;
		}else if((palavrasecodigos.search(palavras.get(i))> -1)){// Se existe
			codigos.insert(palavrasecodigos.search(palavras.get(i))+"",codigos.numElements());
			codigolista++;
		}
			
		
		
	}
	
	public String escreveEmArquivo(StaticList<String> codigos,StaticList<String> palavrasecodigos){
	
		File f = new File ("Codigos.txt");  
		
		FileWriter fr;
		try {
			fr = new FileWriter (f);
			PrintWriter out = new PrintWriter (fr);
			out.println (codigos.toString());
			
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		
File f2 = new File ("PalavrasECodigos.txt");  
		
		FileWriter fr2;
		try {
			fr = new FileWriter (f2);
			PrintWriter out = new PrintWriter (fr);
			out.println (palavrasecodigos.toString());
			
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		
		
			
		
		return "";
	}
	
	
	
	
}
