package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vista.VentanaPrincipal;

public class PanelBienvenida extends JPanel{

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelBienvenida(VentanaPrincipal v) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblBienvenida = new JLabel("BIENVENIDO A SPOTIFY");
		lblBienvenida.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblBienvenida.setForeground(Color.GREEN);
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(270, 150, 260, 51);
		add(lblBienvenida);
		
		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(350, 200, 100, 100);
		add(lblIconoGrande);
		
		/**
		 * Agrega un MouseListener al panel para detectar eventos del mouse.
		 */
		addMouseListener(new MouseListener() {

			/**
	     * Método invocado cuando se libera un botón del mouse.
	     */
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			/**
	     * Método invocado cuando se presiona un botón del mouse.
	     */
			@Override
			public void mousePressed(MouseEvent e) {
				/**
				 * Este variable es el sustituto de thread sleep
				 * Indica que luego de presionar el click pasen 3 segundos y cambie de panel
				 */
				long tiempoDeActivacion = System.currentTimeMillis() + 1500; // TENGO QUE CAMBIARLO A 3000 cuando este todo listo
	               while (System.currentTimeMillis() < tiempoDeActivacion) {// Espera activa para pausar la ejecución durante 3 segundos.
	               }
				v.cambiarDePanel(1);// Llama al método cambiarPanel(1) del objeto FramePrincipal.

			}
			
			/**
	     * Método invocado cuando el mouse sale del área del componente.
	     */
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			/**
	     * Método invocado cuando el mouse entra en el área del componente.
	     */
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			/**
	     * Método invocado cuando se realiza un clic con el mouse.
	     */
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
	
	
}
