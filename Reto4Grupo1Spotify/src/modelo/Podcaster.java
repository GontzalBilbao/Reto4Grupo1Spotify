package modelo;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Podcaster extends Artista {

	private String idPodcaster;
	private String genero;
	private ArrayList<Podcast> podcast = new ArrayList<Podcast>();

	public Podcaster() {

	}

	public Podcaster(String idPodcaster, String nombreArtistico, String genero, ImageIcon imagen, String descripcion) {
		super(nombreArtistico, imagen, descripcion);
		this.idPodcaster = idPodcaster;
		this.genero = genero;

	}

	public String getIdPodcaster() {
		return idPodcaster;
	}

	public void setIdPodcaster(String idPodcaster) {
		this.idPodcaster = idPodcaster;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public ArrayList<Podcast> getPodcast() {
		return podcast;
	}

	public void setPodcast(ArrayList<Podcast> podcast) {
		this.podcast = podcast;
	}

	@Override
	public String toString() {
		return "Podcaster [idPodcaster=" + idPodcaster + ", genero=" + genero + ", podcast=" + podcast
				+ ", getNombreArtistico()=" + getNombreArtistico() + ", getImagen()=" + getImagen()
				+ ", getDescripcion()=" + getDescripcion() + "]";
	}

}
