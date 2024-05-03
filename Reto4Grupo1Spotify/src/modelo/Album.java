package modelo;

public class Album {
	
	private String idAlbum;
	private String titulo;
	private String año;
	private String genero;
	private String imagen;
	private String idMusico;
	
	public Album() {

	}

	public Album(String idAlbum, String titulo, String año, String genero, String imagen, String idMusico) {
		super();
		this.idAlbum = idAlbum;
		this.titulo = titulo;
		this.año = año;
		this.genero = genero;
		this.imagen = imagen;
		this.idMusico = idMusico;
	}

	public String getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getIdMusico() {
		return idMusico;
	}

	public void setIdMusico(String idMusico) {
		this.idMusico = idMusico;
	}

	@Override
	public String toString() {
		return "Album [idAlbum=" + idAlbum + ", titulo=" + titulo + ", año=" + año + ", genero=" + genero + ", imagen="
				+ imagen + ", idMusico=" + idMusico + "]";
	}
	
	
	
	

}
