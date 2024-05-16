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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Album;
import modelo.Cancion;
import modelo.Musico;
import vista.VentanaPrincipal;
import javax.swing.JComboBox;

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
	private String caracteristica = "";
	private String descripcionArtista = "";
	private int musicoSeleccionado = 0;

	private int albumSeleccionado = 0;
	private String titulo = "";
	private String año = "";
	private String genero = "";

	public PanelGestionarCancion(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();
		nombreArtista = gestionInfo.devolverArtistaSeleccionado();
		for (int i = 0; i < musicos.size(); i++) {
			if (nombreArtista.equals(musicos.get(i).getNombreArtistico())) {
				musicoSeleccionado = i;
				caracteristica = musicos.get(i).getCaracteristica();
				descripcionArtista = musicos.get(i).getDescripcion();
				gestionBD.cargarAlbumesDelMusico(nombreArtista);
				albumes = gestionBD.devolverAlbumes();
				for (int j = 0; j < albumes.size(); j++) {
					if (titulo.equals(albumes.get(j).getTitulo())) {
						albumSeleccionado = j;
						titulo = albumes.get(j).getTitulo();
						año = albumes.get(j).getAño();
						genero = albumes.get(j).getGenero();
						gestionBD.cargarCancionesDelAlbum(titulo);
						canciones = gestionBD.devolverCanciones();
					}
				}
			}
		}

		cargarMusicos();

		crearPanelCanciones();

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

		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(560, 435, 200, 50);
		add(btnBorrar);

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

		comBoxMusicos = new JComboBox<String>(arrayMusicos);
		comBoxMusicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comBoxAlbumes.removeAllItems();
				cargarAlbumesPorArtista(comBoxMusicos.getSelectedItem().toString(), gestionBD);
				for (int i = 0; i < albumes.size(); i++) {
					comBoxAlbumes.addItem(arrayAlbumes[i]);
				}
				repaint();
			}
		});
		comBoxMusicos.setBounds(115, 110, 150, 30);
		add(comBoxMusicos);

		JLabel lblAlbum = new JLabel("Album: ");
		lblAlbum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlbum.setBounds(275, 110, 75, 30);
		add(lblAlbum);

		comBoxAlbumes = new JComboBox<String>();

		comBoxAlbumes.setBounds(360, 110, 150, 30);
		add(comBoxAlbumes);

	}

	private void cargarMusicos() {
		arrayMusicos = new String[musicos.size()];
		for (int i = 0; i < musicos.size(); i++) {
			arrayMusicos[i] = musicos.get(i).getNombreArtistico();
		}
	}

	private void cargarAlbumesPorArtista(String nombreArtistico, GestionBD gestion) {
		albumes.clear();
		gestion.cargarAlbumesDelMusico(nombreArtistico);
		albumes = gestion.devolverAlbumes();
		arrayAlbumes = new String[albumes.size()];
		for (int i = 0; i < albumes.size(); i++) {
			arrayAlbumes[i] = albumes.get(i).getTitulo();
		}

	}

	private void crearPanelCanciones() {

		JPanel panelCanciones = new JPanel();
		panelCanciones.setLayout(new GridLayout(0, 1));

		for (int i = 0; i < albumes.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
			panelItem.setLayout(new GridLayout());

// Cargar imagen
			ImageIcon imageIcon = albumes.get(albumSeleccionado).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(albumes.get(i).getTitulo()); // podcasts.get(i).getNombre()
//			JLabel label2 = new JLabel("Autor: " + i);
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			panelItem.add(label1);
//			panelItem.add(label2);
// Le damos nombre para identificarlo
			panelItem.setName("panel " + i);
// Añadir un borde al panelItem
			panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
// Añadir escuchador al panel
			panelItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel clickedPanel = (JPanel) e.getSource();
					JOptionPane.showMessageDialog(null, "Has hecho clic en: " + clickedPanel.getName()
							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
//									+ ((JLabel) clickedPanel.getComponent(2)).getText());

				}
			});
			panelCanciones.add(panelItem);
		}

		JScrollPane scrollPaneCanciones = new JScrollPane(panelCanciones);
		scrollPaneCanciones.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneCanciones.setBorder(null);
		scrollPaneCanciones.setSize(500, 375);
		scrollPaneCanciones.setLocation(30, 150);
		add(scrollPaneCanciones);
	}

}
