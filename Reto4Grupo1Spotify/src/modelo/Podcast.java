package modelo;

import java.util.Objects;

public class Podcast extends Audio {

	private String Descripcion;

	public Podcast() {

	}

	public Podcast(String idAudio, String nombre, int duracion, String imagen, String tipo, String descripcion) {
		super(idAudio, nombre, duracion, imagen, tipo);
		this.Descripcion = descripcion;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(Descripcion);
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
		return Objects.equals(Descripcion, other.Descripcion);
	}

	@Override
	public String toString() {
		return "Podcast [Descripcion=" + Descripcion + ", getIdAudio()=" + getIdAudio() + ", getNombre()=" + getNombre()
				+ ", getDuracion()=" + getDuracion() + ", getImagen()=" + getImagen() + ", getTipo()=" + getTipo()
				+ "]";
	}

}
