package modelo;

import javax.swing.ImageIcon;

public class Podcaster extends Artista {

	private String idPodcaster;
	private String genero;

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
	public String getNombreArtista() {
		// TODO Auto-generated method stub
		return super.getNombreArtista();
	}

	@Override
	public void setNombreArtista(String nombreArtista) {
		// TODO Auto-generated method stub
		super.setNombreArtista(nombreArtista);
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

	@Override
	public String toString() {
		return "Podcaster [getIdPodcaster()=" + getIdPodcaster() + ", getGenero()=" + getGenero()
				+ ", getNombreArtista()=" + getNombreArtista() + ", getImagen()=" + getImagen() + ", getDescripcion()="
				+ getDescripcion() + "]";
	}


}
