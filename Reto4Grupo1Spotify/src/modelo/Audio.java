package modelo;

public class Audio {
	
	private String idAudio;
	private String nombre;
	private String duracion;
	private String imagen;
	private String tipo;
	
	public Audio() {
		
	}

	public Audio(String idAudio, String nombre, String duracion, String imagen, String tipo) {
		super();
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
	public String toString() {
		return "Audio [idAudio=" + idAudio + ", nombre=" + nombre + ", duracion=" + duracion + ", imagen=" + imagen
				+ ", tipo=" + tipo + "]";
	}
	
	
	
	

}
