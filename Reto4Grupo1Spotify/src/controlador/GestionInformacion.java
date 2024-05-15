package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import vista.VentanaPrincipal;

public class GestionInformacion {

	private String artistaSeleccionado = "";

	private String albumSeleccionado = "";

	private String clienteSeleccionado = "";

	private String playlistSeleccionada = "";

	public GestionInformacion() {

	}

	public boolean validarContrasena(boolean campos, String contraseña, String confirmarContraseña) {
		boolean vuelta = false;
		Pattern regexContraseña = Pattern
				.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$_@$!%*?&])([A-Za-z\\d$@_!%*?&]|[^ ]){8,15}$"); // Regex
																												// para
																												// comprobar
																												// que
																												// la
																												// nueva
																												// contraseña
																												// a
																												// registrar
																												// cumple
																												// unos
																												// requisitos
		Matcher validarContraseña = regexContraseña.matcher(contraseña);

		if (campos == false) {
			vuelta = false;
		} else if (!contraseña.equals(confirmarContraseña)) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
			vuelta = false;
		} else if (!validarContraseña.find()) {
			JOptionPane.showMessageDialog(null, "La contraseña no cumple los requisitos", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
			vuelta = false;
		} else {
			vuelta = true;
		}
		return vuelta;
	}

	public void guardarArtistaSeleccionado(String nombreArtista) {
		this.artistaSeleccionado = nombreArtista;
	}

	public String devolverArtistaSeleccionado() {
		return artistaSeleccionado;
	}

	public void guardarAlbumSeleccionado(String tituloAlbum) {
		this.albumSeleccionado = tituloAlbum;

	}

	public String devolverAlbumSeleccionado() {
		return albumSeleccionado;
	}
	
	public void guardarClienteSeleccionado(String usuario) {
		this.clienteSeleccionado = usuario;
	}
	
	public String devolverClienteSeleccionado() {
		return clienteSeleccionado;
		
	}
	
	public void guardarIdClienteSeleccionado(String idCliente) {
		this.clienteSeleccionado = idCliente;
	}
	
	public String devolverIdClienteSeleccionado() {
		return clienteSeleccionado;
		
	}
	
	public void guardarTituloPlayListSeleccionada(String titulo) {
		this.playlistSeleccionada = titulo;
	}
	
	public String devolverTituloPlayListSeleccionada() {
		return playlistSeleccionada;
		
	}
}
