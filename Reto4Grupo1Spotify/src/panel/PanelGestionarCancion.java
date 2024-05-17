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
import modelo.Cancion;
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelGestionarCancion extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Musico> musicos = new ArrayList<Musico>();
	private ArrayList<Album> albumes = new ArrayList<Album>();
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();

	private JComboBox<String> comBoxMusicos;
	private JComboBox<String> comBoxAlbumes;

	private String[] arrayMusicos = new String[1];
	private String[] arrayAlbumes = new String[1];

	private String nombreArtista = "";
	private String tipo = "";
	private String descripcionArtista = "";

	private JLabel lblAlbumActual;
	private String cancionActual = "";

	private String nombreAlbum = "";
	private String titulo = "";
	private String año = "";
	private String genero = "";

	public PanelGestionarCancion(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setLayout(null);

		gestionInfo.cargarMusicos();
		musicos = gestionInfo.devolverMusicos();
		nombreArtista = gestionInfo.devolverArtistaSeleccionado();
		for (int i = 0; i < musicos.size(); i++) {
			if (nombreArtista.equals(musicos.get(i).getNombreArtistico())) {
				tipo = musicos.get(i).getCaracteristica();
				descripcionArtista = musicos.get(i).getDescripcion();

				gestionInfo.cargarAlbumesDelMusico(nombreArtista);
				albumes = gestionInfo.devolverAlbumes();
				nombreAlbum = gestionInfo.devolverAlbumSeleccionado();

				for (int j = 0; j < albumes.size(); j++) {
					if (titulo.equals(nombreAlbum)) {
						titulo = albumes.get(j).getTitulo();
						año = albumes.get(j).getAño();
						genero = albumes.get(j).getGenero();
						gestionInfo.cargarCancionesDelAlbum(titulo);
						canciones = gestionInfo.devolverCanciones();
					}
				}
			}
		}

		cargarMusicos();

		JPanel panelCanciones = new JPanel();
		panelCanciones.setLayout(new GridLayout(0, 1));

		comBoxMusicos = new JComboBox<String>(arrayMusicos);
		comBoxMusicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < musicos.size(); i++) {
					if (nombreArtista.equals(musicos.get(i).getNombreArtistico())) {
						gestionInfo.cargarAlbumesDelMusico(nombreArtista);
						albumes = gestionInfo.devolverAlbumes();
					}
				}

				if (comBoxAlbumes != null) {
					comBoxAlbumes.removeAllItems();
				}

				cargarAlbumesPorArtista(comBoxMusicos.getSelectedItem().toString(), gestionInfo);

				for (int i = 0; i < albumes.size(); i++) {
					comBoxAlbumes.addItem(arrayAlbumes[i]);
				}
				repaint();
				comBoxAlbumes.repaint();
			}
		});
		comBoxMusicos.setBounds(115, 110, 150, 30);
		add(comBoxMusicos);

		comBoxAlbumes = new JComboBox<String>(arrayAlbumes);
		comBoxAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comBoxAlbumes.getSelectedItem() != null) {
					nombreAlbum = comBoxAlbumes.getSelectedItem().toString();
				}

				for (int i = 0; i < albumes.size(); i++) {
					String codAlbum = albumes.get(i).getIdAlbum();
					if (nombreAlbum.equals(albumes.get(i).getTitulo())) {
						gestionInfo.cargarCancionesDelAlbum(codAlbum);
						canciones = gestionInfo.devolverCanciones();
					}
				}

				if (nombreAlbum != null) {

					if (panelCanciones != null) {
						panelCanciones.removeAll();
					}
					for (int i = 0; i < canciones.size(); i++) {

						JPanel panelItem = new JPanel();
						panelItem.setBorder(null);
						panelItem.setLayout(new GridLayout());

						// Cargar imagen
						ImageIcon imageIcon = canciones.get(i).getImagen();
						Image image1 = imageIcon.getImage();
						Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
						ImageIcon newImageIcon = new ImageIcon(newImage);
						JLabel imageLabel = new JLabel(newImageIcon);
						panelItem.add(imageLabel);

						// Agregar JLabels debajo de la imagen
						JLabel label1 = new JLabel(canciones.get(i).getNombre());
						panelItem.add(label1);
//						panelItem.add(label2);
						// Le damos nombre para identificarlo
						panelItem.setName("panel " + i);
						// Añadir un borde al panelItem
						panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						// Añadir escuchador al panel
						panelItem.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								JPanel clickedPanel = (JPanel) e.getSource();
								cancionActual = ((JLabel) clickedPanel.getComponent(1)).getText();
								lblAlbumActual.setText("Cancion: " + cancionActual);
							}
						});
						// Agregar panelItem al panel principal
						panelCanciones.add(panelItem);
					}
					panelCanciones.repaint();
				}
			}
		});
		comBoxAlbumes.setBounds(360, 110, 150, 30);
		add(comBoxAlbumes);

		JScrollPane scrollPaneCanciones = new JScrollPane(panelCanciones);
		scrollPaneCanciones.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneCanciones.setBorder(null);
		scrollPaneCanciones.setSize(500, 375);
		scrollPaneCanciones.setLocation(30, 150);
		add(scrollPaneCanciones);

		JLabel lblCanciones = new JLabel("CANCIONES");
		lblCanciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblCanciones.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		lblCanciones.setBounds(30, 11, 550, 67);
		add(lblCanciones);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Nombre del artista?");
			}
		});
		btnModificar.setBounds(560, 145, 200, 50);
		add(btnModificar);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (cancionActual != null) {

					boolean borrado = gestionBD.borrarMusico(cancionActual);
					if (borrado != false) {
						JOptionPane.showMessageDialog(null, "Cancion borrada!");
						panelCanciones.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona una cancion para eliminarla");
				}
				repaint();
				panelCanciones.repaint();

			}
		});
		btnEliminar.setBounds(560, 435, 200, 50);
		add(btnEliminar);

		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(17);
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

		JLabel lblMusico = new JLabel("Musico: ");
		lblMusico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMusico.setBounds(30, 110, 75, 30);
		add(lblMusico);


		JLabel lblAlbum = new JLabel("Album: ");
		lblAlbum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlbum.setBounds(275, 110, 75, 30);
		add(lblAlbum);

		lblAlbumActual = new JLabel("Cancion: Ninguna");
		lblAlbumActual.setBounds(560, 115, 200, 25);
		add(lblAlbumActual);

	}

	private void cargarMusicos() {
		arrayMusicos = new String[musicos.size()];
		for (int i = 0; i < musicos.size(); i++) {
			arrayMusicos[i] = musicos.get(i).getNombreArtistico();
		}
	}

	private void cargarAlbumesPorArtista(String nombreArtistico, GestionInformacion gestionInfo) {
//		albumes.clear();
		gestionInfo.cargarAlbumesDelMusico(nombreArtistico);
		albumes = gestionInfo.devolverAlbumes();
//		for (int i = 0; i < albumes.size(); i++) {
//		System.out.println(albumes.get(i).getTitulo());
//	}
		arrayAlbumes = new String[albumes.size()];
		for (int i = 0; i < albumes.size(); i++) {
			arrayAlbumes[i] = albumes.get(i).getTitulo();
		}

	}

}
