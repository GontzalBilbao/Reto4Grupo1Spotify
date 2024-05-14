package modelo;

public class PlayList {
	
	private int idList = 0;
	private String titulo = null;
	private String fechaCreacion = null;
	private String idCliente = null;

	public PlayList() {
		
	}

	public PlayList(int idList, String titulo, String fechaCreacion, String idCliente) {

		this.idList = idList;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.idCliente = idCliente;
	}

	public int getIdList() {
		return idList;
	}

	public void setIdList(int idList) {
		this.idList = idList;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "PlayList [idList=" + idList + ", titulo=" + titulo + ", fechaCreacion=" + fechaCreacion + ", idCliente="
				+ idCliente + "]";
	}

}
