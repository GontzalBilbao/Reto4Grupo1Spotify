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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Podcast;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class PanelGestionarPodcast extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

	private JLabel lblPodcasterActual;
	private String podcasterActual;

	private JPanel panelPodcasts;

	private String nombrePodcaster = "";

	private JComboBox<String> comBoxPodcaster;
	private JScrollPane scrollPanePodcasts;

	private String[] arrayPodcasters;

	public PanelGestionarPodcast(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(vp.getSize());
		setLayout(null);
		setVisible(true);

		gestionBD.cargarPodcasters();
		podcasters = gestionBD.devolverPodcasters();

		cargarArrayPodcaters();

		panelPodcasts = new JPanel();
		panelPodcasts.setLayout(new GridLayout(0, 1));
		add(panelPodcasts);

		JLabel lblComBoxPodcasters = new JLabel("Podcasters: ");
		lblComBoxPodcasters.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComBoxPodcasters.setBounds(125, 105, 100, 35);
		add(lblComBoxPodcasters);

		comBoxPodcaster = new JComboBox<String>(arrayPodcasters);
		comBoxPodcaster.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nombrePodcaster = comBoxPodcaster.getSelectedItem().toString();
				for (int i = 0; i < podcasters.size(); i++) {
					String codPodcaster = podcasters.get(i).getIdPodcaster();
					if (nombrePodcaster.equals(podcasters.get(i).getNombreArtistico())) {
						gestionBD.cargarPodcastsDelPodcaster(codPodcaster);
						podcasts = gestionBD.devolverPodcasts();
					}
				}
				System.out.println();

				System.out.println(podcasts);

				if (panelPodcasts != null) {
					panelPodcasts.removeAll();
				}
				for (int i = 0; i < podcasts.size(); i++) {

					JPanel panelItem = new JPanel();
					panelItem.setBorder(null);
					panelItem.setLayout(new GridLayout());

					// Cargar imagen
					ImageIcon imageIcon = podcasts.get(i).getImagen();
					Image image1 = imageIcon.getImage();
					Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
					ImageIcon newImageIcon = new ImageIcon(newImage);
					JLabel imageLabel = new JLabel(newImageIcon);
					panelItem.add(imageLabel);

					// Agregar JLabels debajo de la imagen
					JLabel label1 = new JLabel(podcasts.get(i).getNombre());
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
							podcasterActual = ((JLabel) clickedPanel.getComponent(1)).getText();
							lblPodcasterActual.setText("Podcast: " + podcasterActual);
						}
					});
					// Agregar panelItem al panel principal
					panelPodcasts.add(panelItem);
				}
				panelPodcasts.repaint();
			}
		});

		comBoxPodcaster.setBounds(250, 105, 200, 35);
		add(comBoxPodcaster);

		gestionBD.cargarPodcastsDelPodcaster(comBoxPodcaster.getSelectedItem().toString());
		podcasts = gestionBD.devolverPodcasts();

		// Crear un JScrollPane y agregar el panel
		scrollPanePodcasts = new JScrollPane(panelPodcasts);
		scrollPanePodcasts.getVerticalScrollBar().setUnitIncrement(30);
		scrollPanePodcasts.setBorder(null);
		scrollPanePodcasts.setSize(500, 400);
		scrollPanePodcasts.setLocation(30, 150);
		add(scrollPanePodcasts);

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

		lblPodcasterActual = new JLabel("Podcast: Ninguno");
		lblPodcasterActual.setBounds(560, 115, 200, 25);
		add(lblPodcasterActual);

	}

	private void cargarArrayPodcaters() {
		arrayPodcasters = new String[podcasters.size()];
		for (int i = 0; i < podcasters.size(); i++) {
			arrayPodcasters[i] = podcasters.get(i).getNombreArtistico();
		}
	}

}
