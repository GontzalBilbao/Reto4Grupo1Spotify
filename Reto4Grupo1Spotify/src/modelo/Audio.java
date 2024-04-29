package modelo;

import java.util.Objects;


public class Audio {

	private String idAudio;
	private String nombre;
	private int duracion;
	private String imagen;
	private String tipo;
	
	public Audio() {
		
	}

	public Audio(String idAudio, String nombre, int duracion, String imagen, String tipo) {
		this.idAudio = idAudio;
		this.nombre = nombre;
		this.duracion = duracion;
		this.imagen = imagen;
		this.tipo = tipo;
	}

	public String getIdAudio() {
		return idAudio;
	}

	public void setIdAudio(String idAudio) {
		this.idAudio = idAudio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, idAudio, imagen, nombre, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Audio other = (Audio) obj;
		return duracion == other.duracion && Objects.equals(idAudio, other.idAudio)
				&& Objects.equals(imagen, other.imagen) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Audio [idAudio=" + idAudio + ", nombre=" + nombre + ", duracion=" + duracion + ", imagen=" + imagen
				+ ", tipo=" + tipo + "]";
	}
	
	
	
	
	
	
	
	
}
