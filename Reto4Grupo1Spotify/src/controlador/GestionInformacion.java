package controlador;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import modelo.Artista;
import modelo.Musico;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class GestionInformacion {

	private GestionBD gestionBD;
	private String musico;
	private String podcaster;

	public GestionInformacion() {
		gestionBD = new GestionBD();
	}

	public boolean validarRegistro(VentanaPrincipal vp, String contraseña, String confirmarContraseña) {
		Pattern regexContraseña = Pattern
				.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$_@$!%*?&])([A-Za-z\\d$@_!%*?&]|[^ ]){6,15}$"); // Regex
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
		if (!contraseña.equals(confirmarContraseña)) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
		} else if (!validarContraseña.find()) {
			JOptionPane.showMessageDialog(null, "La contraseña no cumple los requisitos", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	public ArrayList<Musico> devolverMusico() {
		return gestionBD.queryMusico();
	}

	public void recogerMusico(String nombreMusico) {
		this.musico = nombreMusico;
	}
	public String pasrverMusico() {
		return musico;
	}
	
	
	public ArrayList<Podcaster> devolverPodcaster(){
		return gestionBD.queryPodcasters();
	}
	
	public void recogerPodcaster(String nombrePodcaster) {
		this.podcaster = nombrePodcaster;
	}
	public String pasrverPodcaster() {
		return podcaster;
	}

}
