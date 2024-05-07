package modelo;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Artista {
	
	private String nombreArtistico;
	private String nombreArtista;
	private ImageIcon imagen;
	private String descripcion;
	
	
	public Artista() {
		
	}


	public Artista(String nombreArtista, ImageIcon imagen, String descripcion) {
		this.nombreArtista = nombreArtista;
		this.imagen = imagen;
		this.descripcion = descripcion;
	}


	public String getNombreArtistico() {
		return nombreArtistico;
	}


	public void setNombreArtistico(String nombreArtistico) {
		this.nombreArtistico = nombreArtistico;
	}


	public ImageIcon getImagen() {
		return imagen;
	}


	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "Artista [nombreArtistico=" + nombreArtistico + ", imagen=" + imagen + ", descripcion=" + descripcion
				+ "]";
	}

	
	
	
	
	
	
	

}
