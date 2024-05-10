package modelo;

import java.util.Objects;

public class PodcastsFavoritos {
	
	private String nombrePodcast;
	private String duracion;
	private String colaborador;
	private int reproducciones;
	
	public PodcastsFavoritos() {
		
	}
	
	public PodcastsFavoritos(String nombrePodcast, String duracion, String colaborador, int reproducciones) {
		this.nombrePodcast = nombrePodcast;
		this.duracion = duracion;
		this.colaborador = colaborador;
		this.reproducciones = reproducciones;
	}
	public String getNombrePodcast() {
		return nombrePodcast;
	}
	public void setNombrePodcast(String nombrePodcast) {
		this.nombrePodcast = nombrePodcast;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getColaborador() {
		return colaborador;
	}
	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}
	public int getReproducciones() {
		return reproducciones;
	}
	public void setReproducciones(int reproducciones) {
		this.reproducciones = reproducciones;
	}
	@Override
	public int hashCode() {
		return Objects.hash(colaborador, duracion, nombrePodcast, reproducciones);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PodcastsFavoritos other = (PodcastsFavoritos) obj;
		return Objects.equals(colaborador, other.colaborador) && Objects.equals(duracion, other.duracion)
				&& Objects.equals(nombrePodcast, other.nombrePodcast) && reproducciones == other.reproducciones;
	}
	@Override
	public String toString() {
		return "nombre: " + nombrePodcast + " || duracion=" + duracion + " || reproducciones=" + reproducciones ;
	}
	
	
	
	
	
}
