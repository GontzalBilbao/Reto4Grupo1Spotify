package modelo;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Album {

	private String idAlbum, titulo, año, genero;
	private ImageIcon imagen;

	public Album(String idAlbum, String titulo, String año, String genero, ImageIcon imagen) {
		super();
		this.idAlbum = idAlbum;
		this.titulo = titulo;
		this.año = año;
		this.genero = genero;
		this.imagen = imagen;
	}

	public String getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(año, genero, idAlbum, imagen, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(año, other.año) && Objects.equals(genero, other.genero)
				&& Objects.equals(idAlbum, other.idAlbum) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Album [idAlbum=" + idAlbum + ", titulo=" + titulo + ", año=" + año + ", genero=" + genero + ", imagen="
				+ imagen + "]";
	}

	
}
