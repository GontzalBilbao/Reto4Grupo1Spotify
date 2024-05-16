package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Podcaster;
import vista.VentanaPrincipal;
import javax.swing.SwingConstants;

public class PanelDescubrirPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	/**
	 * Create the panel.
	 */

	public PanelDescubrirPodcasts(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {

		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);

		gestionBD.cargarPodcasters();
		podcasters = gestionBD.devolverPodcasters();
		for (int i = 0; i < podcasters.size(); i++) {
//			System.out.println(podcasters.get(i).getDescripcion());
		}

		// Crear un panel para contener los JLabels
				JPanel panel = new JPanel();
//				panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//				panel.setOpaque(false);

		panel.setLayout(new GridLayout(1, 0));


		// Agregar JLabels al panel

		for (int i = 0; i < podcasters.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);

//					panelItem.setOpaque(false);
//					panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));

			panelItem.setLayout(new GridLayout(0, 1));



			// Cargar imagen
			ImageIcon imageIcon = podcasters.get(i).getImagen();
			Image image = imageIcon.getImage();
			Image newImage = image.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

			// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(podcasters.get(i).getNombreArtistico()); // podcasts.get(i).getNombre()

//					JLabel label2 = new JLabel("Autor: " + i);

			label1.setSize(50, 50);
			panelItem.add(label1);

//					panelItem.add(label2);
			// Le damos nombre para identificarlo
			panelItem.setName("panel " + i);
			// Añadir un borde al panelItem
			panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			// Añadir escuchador al panel
			panelItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel clickedPanel = (JPanel) e.getSource();
					gestionInfo.guardarArtistaSeleccionado(((JLabel) clickedPanel.getComponent(1)).getText());
					vp.cambiarDePanel(9);
					JOptionPane.showMessageDialog(vp, "Has hecho clic en: " + clickedPanel.getName()
							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
//											+ ((JLabel) clickedPanel.getComponent(2)).getText());

						}
					});
		// Agregar panelItem al panel principal
					panel.add(panelItem);
				}

		// Crear un JScrollPane y agregar el panel
				JScrollPane scrollPane = new JScrollPane(panel);
		//Como se mueve muy despacio vamos a darle un poco de velocidad
				scrollPane.getVerticalScrollBar().setUnitIncrement(30);
				scrollPane.setBorder(null);
//				scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//				scrollPane.setOpaque(false);

		scrollPane.setSize(765, 370);
		scrollPane.setLocation(10, 135);

		// Agregar el JScrollPane a la ventana

		add(scrollPane);

		JLabel lblTituloLista = new JLabel("DESCUBRIR PODCASTS");
		lblTituloLista.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloLista.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTituloLista.setBounds(270, 100, 260, 51);
		add(lblTituloLista);


		JButton btnAtrás = new JButton("ATRAS");
		btnAtrás.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtrás.setBackground(Color.BLACK);
		btnAtrás.setForeground(Color.WHITE);
		btnAtrás.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(3);
			}
		});
		btnAtrás.setBounds(200, 36, 90, 35);
		add(btnAtrás);

		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.setBackground(Color.BLACK);
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setBounds(500, 36, 90, 35);
		add(btnPerfil);
		
		JLabel lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);


	}

}