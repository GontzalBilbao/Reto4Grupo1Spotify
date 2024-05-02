package vista;


import java.awt.Toolkit;

import javax.swing.JFrame;

import panel.PanelBienvenida;
import panel.PanelLogin;
import panel.PanelMenuAdmin;
import panel.PanelMenuCliente;
import panel.PanelRegistro;

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
			setContentPane(new PanelMenuAdmin(this));
			break;
			
			
			
			
			
			
			
		}

	}

	public static void main(String[] args) {
		VentanaPrincipal v = new VentanaPrincipal();
		v.setVisible(true);
		v.cambiarDePanel(0);
		
	}

}
