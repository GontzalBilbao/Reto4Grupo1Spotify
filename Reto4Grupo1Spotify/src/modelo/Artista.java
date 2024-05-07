package modelo;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Artista {
	
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

	public String getNombreArtista() {
		return nombreArtista;
	}

	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
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
	public int hashCode() {
		return Objects.hash(descripcion, imagen, nombreArtista);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombreArtista, other.nombreArtista);
	}

	@Override
	public String toString() {
		return "artista [nombreArtista=" + nombreArtista + ", imagen=" + imagen + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
	

}
