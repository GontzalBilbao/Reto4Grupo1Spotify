package modelo;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Cancion extends Audio {

	private String idAlbum, artistaInvitado;

	public Cancion() {

	}

	public Cancion(String idAudio, String idAlbum, String nombre, String duracion, String artistaInvitado,
			ImageIcon imagen, String tipo) {
		super(idAudio, nombre, duracion, imagen, tipo);
		this.idAlbum = idAlbum;
		this.artistaInvitado = artistaInvitado;
	}

	public String getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getArtistaInvitado() {
		return artistaInvitado;
	}

	public void setArtistaInvitado(String artistaInvitado) {
		this.artistaInvitado = artistaInvitado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(artistaInvitado, idAlbum);
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
		Cancion other = (Cancion) obj;
		return Objects.equals(artistaInvitado, other.artistaInvitado) && Objects.equals(idAlbum, other.idAlbum);
	}

	@Override
	public String toString() {
		return "Cancion [idAlbum=" + idAlbum + ", artistaInvitado=" + artistaInvitado + ", getIdAudio()=" + getIdAudio()
				+ ", getNombre()=" + getNombre() + ", getDuracion()=" + getDuracion() + "]";
	}
}
