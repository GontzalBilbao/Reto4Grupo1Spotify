package modelo;

import java.util.Objects;

public class Cancion {
	
	private String artistaInvitado;
	private String idAlbum;
	private String idCancion;
	
	public Cancion() {
		
	}

	public Cancion(String artistaInvitado, String idAlbum, String idCancion) {
		this.artistaInvitado = artistaInvitado;
		this.idAlbum = idAlbum;
		this.idCancion = idCancion;
	}

	public String getArtistaInvitado() {
		return artistaInvitado;
	}

	public void setArtistaInvitado(String artistaInvitado) {
		this.artistaInvitado = artistaInvitado;
	}

	public String getIsAlbum() {
		return idAlbum;
	}

	public void setIsAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(String idCancion) {
		this.idCancion = idCancion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(artistaInvitado, idCancion, idAlbum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return Objects.equals(artistaInvitado, other.artistaInvitado) && Objects.equals(idCancion, other.idCancion)
				&& Objects.equals(idAlbum, other.idAlbum);
	}

	@Override
	public String toString() {
		return "Cancion [artistaInvitado=" + artistaInvitado + ", isAlbum=" + idAlbum + ", idCancion=" + idCancion + "]";
	}
	
	
	
	
	
}
