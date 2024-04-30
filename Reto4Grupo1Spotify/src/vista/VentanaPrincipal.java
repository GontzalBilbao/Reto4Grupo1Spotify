package vista;


import javax.swing.JFrame;

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
import panel.PanelGestionarPodcaster;
import panel.PanelLogin;
import panel.PanelMenuAdministrador;
import panel.PanelMenuCliente;
import panel.PanelMusicoAlbume;
import panel.PanelPerfil;
import panel.PanelPlaylist;
import panel.PanelPodcasterPodcasts;
import panel.PanelRegistro;
import panel.PanelReproductorMusica;
import panel.PanelReproductorPodcasts;

import java.awt.Toolkit;

public class VentanaPrincipal extends JFrame {

	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icono/logopequeño.png"));
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Spotify");
		
		
	}

	public void cambiarDePanel(int i) {

		switch (i) {
		case 0:
			setContentPane(new PanelBienvenida(this));
			break;
		case 1:
			setContentPane(new PanelLogin(this));
			break;
		case 2: 
			setContentPane(new PanelRegistro(this));
			break;
		case 3:
			setContentPane(new PanelMenuCliente(this));
			break;
		case 4:
			setContentPane(new PanelDescubrirMusica(this) );
			break;
		case 5:
			setContentPane(new PanelMusicoAlbume(this));
		case 6:
			setContentPane(new PanelAlbumCancion(this));
			break;
		case 7:
			setContentPane(new PanelReproductorMusica(this) );
			break;
		case 8:
			setContentPane(new PanelDescubrirPodcasts(this));
			break;
		case 9:
			setContentPane(new PanelPodcasterPodcasts(this));
			break;
		case 10:
			setContentPane(new PanelReproductorPodcasts(this));
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
			setContentPane(new PanelGestionarMusico(this));
			break;
		case 15:
			setContentPane(new PanelAñadirMusico(this));
			break;
		case 16:
			setContentPane(new PanelAñadirAlbum(this));
			break;
		case 17:
			setContentPane(new PanelAñadirCancion(this));
			break;
		case 18:
			setContentPane(new PanelGestionarAlbum(this));
			break;
		case 19:
			setContentPane(new PanelGestionarCancion(this));
			break;
		case 20:
			setContentPane(new PanelGestionarPodcaster(this));
			break;
		case 21:
			setContentPane(new PanelAñadirPodcaster(this));
			break;
		case 22:
			setContentPane(new PanelAñadirPodcast(this));
			break;
		case 23:
			setContentPane(new PanelEstadistica(this));
			break;
		}

	}

	public static void main(String[] args) {
		VentanaPrincipal v = new VentanaPrincipal();
		v.setVisible(true);
		v.cambiarDePanel(0);
		
	}

}
