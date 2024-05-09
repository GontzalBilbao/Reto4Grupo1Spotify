package modelo;

import javax.swing.ImageIcon;

public class Audio {

	private String idAudio;
	private String nombre;
	private String duracion;
	private ImageIcon imagen;
	private String tipo;

	private boolean reproduciendo;

	public Audio() {

	}

	public Audio(String idAudio, String nombre, String duracion, ImageIcon imagen, String tipo) {

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

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean sonando() {
		if (!reproduciendo) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Audio [idAudio=" + idAudio + ", nombre=" + nombre + ", duracion=" + duracion + ", imagen=" + imagen
				+ ", tipo=" + tipo + "]";
	}

}
