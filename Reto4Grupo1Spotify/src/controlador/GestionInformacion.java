package controlador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import modelo.Podcaster;

public class GestionInformacion {

	private GestionBD gestionBD;
	private String musico;
	private String podcaster;
	private String tipoDePerfil;
	private String artistaSeleccionado = "";
	private String albumSeleccionado = "";
	private String audioSeleccionado = "";
	private String cancionSeleccionada = "";
	private String idArtista = "";
	private int recogerIndiceCancion;
	private int recogerIndicePodcast;
	private String idCliente;
	private String premium;
	private String clienteSeleccionado = "";

	public GestionInformacion() {
		gestionBD = new GestionBD();
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

	public void guardarIdArtistaSeleccionado(String idArtista) {
		this.idArtista = idArtista;
	}

	public String devolverIdArtistaSeleccionado() {
		return idArtista;
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
	
	public void guardarCancionSeleccionado(String cancionSeleccionada) {
		this.cancionSeleccionada = cancionSeleccionada;

	}

	public String devolverCancionSeleccionado() {
		return cancionSeleccionada;
	}

	public String devolverTipoDePerfil() {
		return tipoDePerfil;
	}

	public void recogerMusico(String nombreMusico) {
		this.musico = nombreMusico;
	}

	public String pasrverMusico() {
		return musico;
	}

	public ArrayList<Podcaster> devolverPodcaster() {
		return gestionBD.queryPodcasters();
	}

	public void recogerPodcaster(String nombrePodcaster) {
		this.podcaster = nombrePodcaster;
	}

	public String pasrverPodcaster() {
		return podcaster;
	}

	public void recogerIndiceCancion(int indice) {
		this.recogerIndiceCancion = indice;
	}

	public int pasarIndiceCancion() {
		return recogerIndiceCancion;
	}

	public void recogerIndicePodcast(int indice) {
		this.recogerIndicePodcast = indice;
	}

	public int pasarIndicePodcast() {
		return recogerIndicePodcast;
	}

	public void sacarPremium(String usuario) {
		this.premium = gestionBD.sacarPremium(usuario);
	}

	public String devolverPremiun() {
		return premium;
	}

	public void guardarAudioSeleccionado(String audioSeleccionado) {
		this.audioSeleccionado = audioSeleccionado;

	}

	public String devolverAudioSeleccionado() {
		return audioSeleccionado;
	}

	public int devolverIdPlaylist(String titulo) {
		return gestionBD.idPlaylist(titulo);
	}
	
	public void sacarIdCliente(String usuario) {
		this.idCliente = gestionBD.sacarIdCliente(usuario);
	}
	
	public String devolverIdCliente() {
		return idCliente;
	}
	
	public void añadirCancionAPlaylist(String idAudio, int idPlaylist) {
		gestionBD.insertCancionEnPlaylist(idAudio, idPlaylist);
	}
	
	public void guardarClienteSeleccionado(String usuario) {
		this.clienteSeleccionado = usuario;
	}
	
	public String devolverClienteSeleccionado() {
		return clienteSeleccionado;
		
	}
}
