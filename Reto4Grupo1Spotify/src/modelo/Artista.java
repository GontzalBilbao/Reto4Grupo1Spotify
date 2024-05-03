package modelo;

import java.util.Objects;

public class Artista {
	
	private String nombreArtistico;
	private String imagen;
	private String descripcion;
	
	
	public Artista() {
		
	}


	public Artista(String nombreArtistico, String imagen, String descripcion) {
		super();
		this.nombreArtistico = nombreArtistico;
		this.imagen = imagen;
		this.descripcion = descripcion;
	}


	public String getNombreArtistico() {
		return nombreArtistico;
	}


	public void setNombreArtistico(String nombreArtistico) {
		this.nombreArtistico = nombreArtistico;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
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
