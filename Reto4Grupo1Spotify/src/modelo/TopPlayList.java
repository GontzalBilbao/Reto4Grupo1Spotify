package modelo;

import java.util.Objects;

public class TopPlayList {
	
	private String nombreAudio;
	private String duracion;
	private String tipo;
	private int reptoducciones;
	private int numeroEnPlaylist;
	
	public TopPlayList() {
		
	}

	public TopPlayList(String nombreAudio, String duracion, String tipo, int reptoducciones, int numeroEnPlaylist) {
		this.nombreAudio = nombreAudio;
		this.duracion = duracion;
		this.tipo = tipo;
		this.reptoducciones = reptoducciones;
		this.numeroEnPlaylist = numeroEnPlaylist;
	}

	public String getNombreAudio() {
		return nombreAudio;
	}

	public void setNombreAudio(String nombreAudio) {
		this.nombreAudio = nombreAudio;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getReptoducciones() {
		return reptoducciones;
	}

	public void setReptoducciones(int reptoducciones) {
		this.reptoducciones = reptoducciones;
	}

	public int getNumeroEnPlaylist() {
		return numeroEnPlaylist;
	}

	public void setNumeroEnPlaylist(int numeroEnPlaylist) {
		this.numeroEnPlaylist = numeroEnPlaylist;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, nombreAudio, numeroEnPlaylist, reptoducciones, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopPlayList other = (TopPlayList) obj;
		return Objects.equals(duracion, other.duracion) && Objects.equals(nombreAudio, other.nombreAudio)
				&& numeroEnPlaylist == other.numeroEnPlaylist && reptoducciones == other.reptoducciones
				&& Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Nombre: " + nombreAudio + " || Duracion=" + duracion + " || Tipo=" + tipo
				+ " || Reptoducciones=" + reptoducciones + " || Numero En Playlist=" + numeroEnPlaylist;
	}
	
	
	
	
}
