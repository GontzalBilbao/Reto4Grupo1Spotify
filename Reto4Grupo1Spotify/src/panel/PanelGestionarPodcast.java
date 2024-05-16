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
import controlador.GestionInformacion;
import vista.VentanaPrincipal;

public class PanelGestionarPodcast extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelGestionarPodcast(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(vp.getSize());
//		setBackground(Color.DARK_GRAY);
		setLayout(null);
		setVisible(true);

		JPanel panelPodcasts = new JPanel();
		panelPodcasts.setBorder(new LineBorder(Color.black, 1, true));
		add(panelPodcasts);

		JScrollPane spPanelPodcasts = new JScrollPane(panelPodcasts);
		spPanelPodcasts.getVerticalScrollBar();
		spPanelPodcasts.setBorder(null);
		spPanelPodcasts.setSize(500, 425);
		spPanelPodcasts.setLocation(30, 100);
		add(spPanelPodcasts);

		JLabel lblPodcasts = new JLabel("PODCASTS");
		lblPodcasts.setHorizontalAlignment(SwingConstants.CENTER);
		lblPodcasts.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		lblPodcasts.setBounds(30, 11, 550, 67);
		add(lblPodcasts);

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
				vp.cambiarDePanel(23);
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
