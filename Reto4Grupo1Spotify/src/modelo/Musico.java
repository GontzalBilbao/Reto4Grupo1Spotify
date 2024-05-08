package modelo;

import javax.swing.ImageIcon;

public class Musico extends Artista {

	private String idMusico;
	private String caracteristica;

	public Musico() {

	}

	public Musico(String idMusico, String nombreArtista, String caracteristica, ImageIcon imagen, String descripcion) {
		super(nombreArtista, imagen, descripcion);
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
		return "Musico [getIdMusico()=" + getIdMusico() + ", getCaracteristica()=" + getCaracteristica()
				+ ", getNombreArtista()=" + getNombreArtista() + ", getImagen()=" + getImagen() + ", getDescripcion()="
				+ getDescripcion() + "]";
	}


}
