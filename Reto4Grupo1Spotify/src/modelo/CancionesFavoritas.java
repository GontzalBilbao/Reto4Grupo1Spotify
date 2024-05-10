package modelo;

import java.util.Objects;

public class CancionesFavoritas {
	
	private String nombreCancion;
	private String duracion;
	private String 	artistaInvitado;
	private int reproducciones;
	
	public CancionesFavoritas() {
		
	}
	
	public CancionesFavoritas(String nombreCancion, String duracion, String artistaInvitado, int reproducciones) {
		super();
		this.nombreCancion = nombreCancion;
		this.duracion = duracion;
		this.artistaInvitado = artistaInvitado;
		this.reproducciones = reproducciones;
	}
	public String getNombreCancion() {
		return nombreCancion;
	}
	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getArtistaInvitado() {
		return artistaInvitado;
	}
	public void setArtistaInvitado(String artistaInvitado) {
		this.artistaInvitado = artistaInvitado;
	}
	public int getReproducciones() {
		return reproducciones;
	}
	public void setReproducciones(int reproducciones) {
		this.reproducciones = reproducciones;
	}
	@Override
	public int hashCode() {
		return Objects.hash(artistaInvitado, duracion, nombreCancion, reproducciones);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CancionesFavoritas other = (CancionesFavoritas) obj;
		return Objects.equals(artistaInvitado, other.artistaInvitado) && Objects.equals(duracion, other.duracion)
				&& Objects.equals(nombreCancion, other.nombreCancion) && reproducciones == other.reproducciones;
	}
	@Override
	public String toString() {
		return "Nombre: " + nombreCancion + " || duracion: " + duracion + " || reproducciones: " + reproducciones;
	}
	
	
	
	
}
