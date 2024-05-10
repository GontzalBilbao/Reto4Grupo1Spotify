package modelo;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Podcaster extends Artista {

	private String idPodcaster;
	private String genero;

	private ArrayList<Podcast> podcast = new ArrayList<Podcast>();

	public Podcaster() {

	}

	public Podcaster(String idPodcaster, String nombreArtista, String genero, ImageIcon imagen, String descripcion) {
		super(nombreArtista, imagen, descripcion);
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

	@Override
	public String getNombreArtistico() {
		return super.getNombreArtistico();
	}

	@Override

	public void setNombreArtistico(String nombreArtistico) {

		super.setNombreArtistico(nombreArtistico);
	}

	@Override
	public ImageIcon getImagen() {
		// TODO Auto-generated method stub
		return super.getImagen();
	}

	@Override
	public void setImagen(ImageIcon imagen) {
		// TODO Auto-generated method stub
		super.setImagen(imagen);
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return super.getDescripcion();
	}

	@Override
	public void setDescripcion(String descripcion) {
		// TODO Auto-generated method stub
		super.setDescripcion(descripcion);
	}

	public String toString() {
		return "Podcaster [getIdPodcaster()=" + getIdPodcaster() + ", getGenero()=" + getGenero()
				+ ", getNombreArtista()=" + getNombreArtistico() + ", getImagen()=" + getImagen()
				+ ", getDescripcion()=" + getDescripcion() + "]";

	}

}
