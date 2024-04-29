package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Podcaster extends Artista {

	private String idPodcaster;
	private String genero;
	private ArrayList<Podcast> podcast;

	public Podcaster() {

	}

	public Podcaster(String nombreArtista, String imagen, String descripcion, String idPodcaster, String genero,
			ArrayList<Podcast> podcast) {
		super(nombreArtista, imagen, descripcion);
		this.idPodcaster = idPodcaster;
		this.genero = genero;
		this.podcast = podcast;
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(genero, idPodcaster, podcast);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Podcaster other = (Podcaster) obj;
		return Objects.equals(genero, other.genero) && Objects.equals(idPodcaster, other.idPodcaster)
				&& Objects.equals(podcast, other.podcast);
	}

	@Override
	public String toString() {
		return "Podcaster [idPodcaster=" + idPodcaster + ", genero=" + genero + ", podcast=" + podcast
				+ ", getNombreArtista()=" + getNombreArtista() + ", getImagen()=" + getImagen() + ", getDescripcion()="
				+ getDescripcion() + "]";
	}

}
