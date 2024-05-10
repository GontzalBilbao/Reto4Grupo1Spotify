package vista;

import java.awt.Toolkit;

import javax.swing.JFrame;

import controlador.GestionBD;
import controlador.GestionFicheros;
import controlador.GestionInformacion;
import panel.PanelAlbumCancion;
import panel.PanelAñadirAlbum;
import panel.PanelAñadirCancion;
import panel.PanelAñadirMusico;
import panel.PanelAñadirPodcast;
import panel.PanelAñadirPodcaster;
import panel.PanelBienvenida;
import panel.PanelDescubrirMusica;
import panel.PanelDescubrirPodcasts;
import panel.PanelEstadistica;
import panel.PanelGestionarAlbum;
import panel.PanelGestionarCancion;
import panel.PanelGestionarMusico;
import panel.PanelGestionarPodcast;
import panel.PanelGestionarPodcaster;
import panel.PanelLogin;
import panel.PanelMenuAdministrador;
import panel.PanelMenuCliente;
import panel.PanelMusicoAlbumes;
import panel.PanelPerfil;
import panel.PanelPlaylist;
import panel.PanelPodcasterPodcasts;
import panel.PanelRegistro;
import panel.PanelReproductorMusica;
import panel.PanelReproductorPodcasts;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icono/logopequeño.png"));
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Spotify");

	}

	GestionBD gestionBD = new GestionBD();
	GestionInformacion gestionInfo = new GestionInformacion();
	GestionFicheros gestionFich = new GestionFicheros();

	public void cambiarDePanel(int i) {

		switch (i) {
		case 0:
			setContentPane(new PanelBienvenida(this));
			break;
		case 1:
			setContentPane(new PanelLogin(this, gestionBD, gestionInfo));
			break;
		case 2:
			setContentPane(new PanelRegistro(this, gestionBD, gestionInfo));
			break;
		case 3:
			setContentPane(new PanelMenuCliente(this));
			break;
		case 4:
			setContentPane(new PanelDescubrirMusica(this, gestionBD, gestionInfo));

			break;
		case 5:
			setContentPane(new PanelMusicoAlbumes(this, gestionBD, gestionInfo));
			break;
		case 6:
			setContentPane(new PanelAlbumCancion(this, gestionBD, gestionInfo));
			break;
		case 7:
			setContentPane(new PanelReproductorMusica(this, gestionBD, gestionInfo));
			break;
		case 8:
			setContentPane(new PanelDescubrirPodcasts(this, gestionBD, gestionInfo));
			break;
		case 9:
			setContentPane(new PanelPodcasterPodcasts(this, gestionBD, gestionInfo));
			break;
		case 10:
			setContentPane(new PanelReproductorPodcasts(this, gestionBD, gestionInfo));
			break;
		case 11:
			setContentPane(new PanelPerfil(this));
			break;
		case 12:
			setContentPane(new PanelPlaylist(this));
			break;
		case 13:
			setContentPane(new PanelMenuAdministrador(this));
			break;
		case 14:
			setContentPane(new PanelGestionarMusico(this, gestionBD));
			break;
		case 15:
			setContentPane(new PanelAñadirMusico(this, gestionBD));
			break;
		case 16:
			setContentPane(new PanelAñadirAlbum(this, gestionBD));
			break;
		case 17:
			setContentPane(new PanelAñadirCancion(this, gestionBD));
			break;
		case 18:
			setContentPane(new PanelGestionarAlbum(this, gestionBD));
			break;
		case 19:
			setContentPane(new PanelGestionarCancion(this, gestionBD));
			break;
		case 20:
			setContentPane(new PanelGestionarPodcaster(this, gestionBD));
			break;
		case 21:
			setContentPane(new PanelAñadirPodcaster(this, gestionBD));
			break;
		case 22:
			setContentPane(new PanelGestionarPodcast(this, gestionBD));
		case 23:
			setContentPane(new PanelAñadirPodcast(this, gestionBD));
			break;
		case 24:
			setContentPane(new PanelEstadistica(this, gestionBD));
			break;
		}

	}

	public void lanzarVentana() {
		this.cambiarDePanel(3);
		this.setVisible(true);
	}

}
