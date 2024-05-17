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
import modelo.Album;
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelGestionarAlbum extends JPanel {

	private static final long serialVersionUID = 1L;


	private ArrayList<Musico> musicos = new ArrayList<Musico>();
	private ArrayList<Album> albumes = new ArrayList<Album>();
	private String nombreArtista = "";

	private String albumActual;
	private JLabel lblAlbumActual;

	private JComboBox<String> comBoxMusicos;
	private JScrollPane scrollPaneAlbumes;

	private String[] arrayMusicos;

	public PanelGestionarAlbum(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setLayout(null);

		gestionInfo.cargarMusicos();
		musicos = gestionInfo.devolverMusicos();

//<<<<<<< HEAD
//		cargarMusicos();
//		gestionInfo.cargarMusicos();
//		musicos = gestionInfo.devolverMusicos();
//		nombreArtista = gestionInfo.devolverArtistaSeleccionado();
//		for (int i = 0; i < musicos.size(); i++) {
//			if (nombreArtista.equals(musicos.get(i).getNombreArtistico())) {
//				musicoSeleccionado = i;
//				caracteristica = musicos.get(i).getCaracteristica();
//				descripcionArtista = musicos.get(i).getDescripcion();
//				gestionInfo.cargarAlbumesDelMusico(nombreArtista);
//				albumes = gestionInfo.devolverAlbumes();
//			}
//		}
//=======
		cargarArrayMusicos();
//
//		gestionBD.cargarMusicos();
//		musicos = gestionBD.devolverMusicos();
//>>>>>>> refs/remotes/origin/Dani

		JPanel panelAlbumes = new JPanel();
		panelAlbumes.setLayout(new GridLayout(0, 1));

		JLabel lblComBoxMusicos = new JLabel("Musicos: ");
		lblComBoxMusicos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComBoxMusicos.setBounds(125, 105, 100, 35);
		add(lblComBoxMusicos);

		comBoxMusicos = new JComboBox<String>(arrayMusicos);
		comBoxMusicos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//<<<<<<< HEAD
//				gestionInfo.cargarAlbumesDelMusico(comBoxMusicos.getSelectedItem().toString());
//				albumes = gestionInfo.devolverAlbumes();
//				nombreArtista = gestionInfo.devolverArtistaSeleccionado();
//=======
				nombreArtista = comBoxMusicos.getSelectedItem().toString();
				for (int i = 0; i < musicos.size(); i++) {
					if (nombreArtista.equals(musicos.get(i).getNombreArtistico())) {
						gestionInfo.cargarAlbumesDelMusico(nombreArtista);
						albumes = gestionInfo.devolverAlbumes();
					}
				}
				if (panelAlbumes != null) {
					panelAlbumes.removeAll();
					panelAlbumes.repaint();
				}
				for (int i = 0; i < albumes.size(); i++) {
//>>>>>>> refs/remotes/origin/Dani

					JPanel panelItem = new JPanel();
					panelItem.setBorder(null);
					panelItem.setLayout(new GridLayout());

					// Cargar imagen
					ImageIcon imageIcon = albumes.get(i).getImagen();
					Image image1 = imageIcon.getImage();
					Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
					ImageIcon newImageIcon = new ImageIcon(newImage);
					JLabel imageLabel = new JLabel(newImageIcon);
					panelItem.add(imageLabel);

					// Agregar JLabels debajo de la imagen
					JLabel label1 = new JLabel(albumes.get(i).getTitulo()); // podcasts.get(i).getNombre()
//					JLabel label2 = new JLabel("Autor: " + i);
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
							albumActual = ((JLabel) clickedPanel.getComponent(1)).getText();
							lblAlbumActual.setText("Album: " + albumActual);
						}
					});
					// Agregar panelItem al panel principal
					panelAlbumes.add(panelItem);
				}
			}
		});

		comBoxMusicos.setBounds(250, 105, 200, 35);
		add(comBoxMusicos);

		gestionInfo.cargarAlbumesDelMusico(comBoxMusicos.getSelectedItem().toString());
		albumes = gestionInfo.devolverAlbumes();

		// Crear un JScrollPane y agregar el panel
		scrollPaneAlbumes = new JScrollPane(panelAlbumes);
		// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneAlbumes.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneAlbumes.setBorder(null);
		scrollPaneAlbumes.setSize(500, 400);
		scrollPaneAlbumes.setLocation(30, 150);
		// Agregar el JScrollPane a la ventana
		add(scrollPaneAlbumes);

		JLabel lblAlbumes = new JLabel("ALBUMES");
		lblAlbumes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumes.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		lblAlbumes.setBounds(30, 11, 550, 67);
		add(lblAlbumes);

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
				vp.cambiarDePanel(16);
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

		lblAlbumActual = new JLabel("Album: Ninguno");
		lblAlbumActual.setBounds(560, 115, 200, 25);
		add(lblAlbumActual);
	}

	private void cargarArrayMusicos() {
		arrayMusicos = new String[musicos.size()];
		for (int i = 0; i < musicos.size(); i++) {
			arrayMusicos[i] = musicos.get(i).getNombreArtistico();
		}

	}
}
