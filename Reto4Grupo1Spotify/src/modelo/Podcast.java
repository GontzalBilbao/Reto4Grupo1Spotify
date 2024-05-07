package modelo;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Podcast extends Audio {

	private String descripcion;
	private String idPodcaster;
	
	public Podcast() {

	}

	public Podcast(String idAudio, String idPodcaster,  String nombre, String duracion, String colaboradores, String descripcion, ImageIcon imagen, String tipo) {
		super(idAudio, nombre, duracion, imagen, tipo);
		this.descripcion = descripcion;
		this.idPodcaster = idPodcaster;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getIdPodcaster() {
		return idPodcaster;
	}

	public void setIdPodcaster(String idPodcaster) {
		this.idPodcaster = idPodcaster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descripcion);
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
		Podcast other = (Podcast) obj;
		return Objects.equals(descripcion, other.descripcion);
	}

	@Override
	public String toString() {
		return "Podcast [Descripcion=" + descripcion + ", getIdAudio()=" + getIdAudio() + ", getNombre()=" + getNombre()
				+ ", getDuracion()=" + getDuracion() + ", getImagen()=" + getImagen() + ", getTipo()=" + getTipo()
				+ "]";
	}

}
