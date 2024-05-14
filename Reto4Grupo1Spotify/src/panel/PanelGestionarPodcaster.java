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
import javax.swing.border.LineBorder;

import controlador.GestionBD;
import vista.VentanaPrincipal;

public class PanelGestionarPodcaster extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelGestionarPodcaster(VentanaPrincipal vp, GestionBD gestionBD) {
		setSize(vp.getSize());
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JPanel panelPodcasters = new JPanel();
		panelPodcasters.setBorder(new LineBorder(Color.black, 1, true));
		add(panelPodcasters);

		JScrollPane spPanelPodcasters = new JScrollPane(panelPodcasters);
		spPanelPodcasters.getVerticalScrollBar();
		spPanelPodcasters.setBorder(null);
		spPanelPodcasters.setSize(500, 425);
		spPanelPodcasters.setLocation(30, 100);
		add(spPanelPodcasters);

		JLabel lblPodcasters = new JLabel("PODCASTERS");
		lblPodcasters.setHorizontalAlignment(SwingConstants.CENTER);
		lblPodcasters.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		lblPodcasters.setBounds(30, 11, 550, 67);
		add(lblPodcasters);

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
				vp.cambiarDePanel(21);
			}
		});
		btnAñadir.setBounds(560, 290, 200, 50);
		add(btnAñadir);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(13);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

	}

}
