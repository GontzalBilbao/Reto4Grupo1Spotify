package modelo;

import java.util.ArrayList;

public class Musico extends Artista {
	
	private String idMusico;
	private String caracteristica;
	private ArrayList<Album> album = new ArrayList<Album>();
	
	public Musico() {
		
	}

	public Musico(String idMusico, String nombreArtistico, String imagen,  String caracteristica, String descripcion) {
		super(nombreArtistico, imagen, descripcion);
		this.idMusico = idMusico;
		this.caracteristica = caracteristica;
	}

	public String getIdMusico() {
		return idMusico;
	}

	public void setIdMusico(String idMusico) {
		this.idMusico = idMusico;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public ArrayList<Album> getAlbum() {
		return album;
	}

	public void setAlbum(ArrayList<Album> album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Musico [idMusico=" + idMusico + ", caracteristica=" + caracteristica + ", getNombreArtistico()="
				+ getNombreArtistico() + ", getImagen()=" + getImagen() + ", getDescripcion()=" + getDescripcion()
				+ "]";
	}
	
	
	
	
	
	
	
	

}
