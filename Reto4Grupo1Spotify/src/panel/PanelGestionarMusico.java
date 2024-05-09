package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import vista.VentanaPrincipal;

public class PanelGestionarMusico extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGestionarMusico(VentanaPrincipal v, GestionBD gestionBD) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JPanel panelMusicos = new JPanel();
		add(panelMusicos);
		
		JScrollPane spPanelMusicos = new JScrollPane(panelMusicos);
		spPanelMusicos.getVerticalScrollBar();
		spPanelMusicos.setBorder(null);
		spPanelMusicos.setSize(500, 425);
		spPanelMusicos.setLocation(30, 100);
		add(spPanelMusicos);
		
		JLabel lblMusicos = new JLabel("MUSICOS");
		lblMusicos.setForeground(new Color(255, 255, 255));
		lblMusicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicos.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		lblMusicos.setBounds(30, 11, 550, 67);
		add(lblMusicos);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Nombre del artista?");
			}
		});
		btnModificar.setBounds(560, 145, 200, 50);
		add(btnModificar);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(560, 435, 200, 50);
		add(btnBorrar);
		
		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(15);
			}
		});
		btnAñadir.setBounds(560, 290, 200, 50);
		add(btnAñadir);
		
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(13);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);
	}
}
