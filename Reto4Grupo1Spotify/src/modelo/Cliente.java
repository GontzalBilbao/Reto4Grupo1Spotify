package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {

	private String idCliente, nombre, apellido, usuario, contrasena, fechaNac, fechaRegistro, tipo, idIdioma;
	private ArrayList<Cancion> favoritos;

	
	public Cliente() {
		
	}
	
	public Cliente(String idCliente, String nombre, String apellido, String usuario, String contrasena, String fechaNac,
			String fechaRegistro, String tipo, String idIdioma) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.fechaNac = fechaNac;
		this.fechaRegistro = fechaRegistro;
		this.tipo = tipo;
		this.idIdioma = idIdioma;
		favoritos = new ArrayList<Cancion>();
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}
	
	public ArrayList<Cancion> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(ArrayList<Cancion> favoritos) {
		this.favoritos = favoritos;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario="
				+ usuario + ", contrasena=" + contrasena + ", fechaNac=" + fechaNac + ", fechaRegistro=" + fechaRegistro
				+ ", tipo=" + tipo + ", idIdioma=" + idIdioma + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, contrasena, fechaNac, fechaRegistro, idCliente, idIdioma, nombre, tipo, usuario);
	}
	
	
}
