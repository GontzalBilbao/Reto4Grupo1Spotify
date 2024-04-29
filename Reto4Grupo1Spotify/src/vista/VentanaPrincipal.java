package vista;


import javax.swing.JFrame;

import panel.PanelBienvenida;
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
			
			break;
		case 2: 
			
			break;
		case 3:
			
			break;
			
			
			
		}

	}

	public static void main(String[] args) {
		VentanaPrincipal v = new VentanaPrincipal();
		v.setVisible(true);
		v.cambiarDePanel(0);
		
	}

}
