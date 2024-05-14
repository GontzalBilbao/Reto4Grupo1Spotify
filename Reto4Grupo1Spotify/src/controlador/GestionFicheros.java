package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.PlayList;

public class GestionFicheros {


	public void imprimirInformacion(ArrayList<PlayList> playlists) {
	    try {
	        BufferedWriter escribir = new BufferedWriter(new FileWriter("fichero.txt"));
	        escribir.write("\nPlayLists: \n");
	        for(int i = 0; i < playlists.size(); i++) {
	            PlayList playlist = playlists.get(i);
	            escribir.write( "Título: " + playlist.getTitulo() +
	                            "\nFecha de Creación: " + playlist.getFechaCreacion() + "\n\n");
	        }
	        escribir.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    leerInformacionEnConsola();
	}


private void leerInformacionEnConsola() {
	
	try {
		BufferedReader imprimir = new BufferedReader(new FileReader("fichero.txt"));
		String line = null;
		try {
			while((line = imprimir.readLine()) != null){
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
}
	
}
