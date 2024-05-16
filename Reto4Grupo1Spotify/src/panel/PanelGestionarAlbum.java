package panel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private String caracteristica = "";
	private String descripcionArtista = "";
	private int musicoSeleccionado = 0;

	private JLabel albumActual;

	private JComboBox<String> comBoxMusicos;

	private String[] arrayMusicos;

	public PanelGestionarAlbum(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();

		cargarMusicos();
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
			}
		}

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
				gestionBD.cargarAlbumesDelMusico(comBoxMusicos.getSelectedItem().toString());
				albumes = gestionBD.devolverAlbumes();
				nombreArtista = gestionInfo.devolverArtistaSeleccionado();

			}
		});
		comBoxMusicos.setBounds(250, 105, 200, 35);
		add(comBoxMusicos);

		gestionBD.cargarAlbumesDelMusico(comBoxMusicos.getSelectedItem().toString());
		albumes = gestionBD.devolverAlbumes();

		// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneAlbumes = new JScrollPane(panelAlbumes);
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

		albumActual = new JLabel("Album Seleccionado: Ninguno");
		albumActual.setBounds(560, 115, 200, 25);
		add(albumActual);
	}

	private void cargarMusicos() {
		arrayMusicos = new String[musicos.size()];
		for (int i = 0; i < musicos.size(); i++) {
			arrayMusicos[i] = musicos.get(i).getNombreArtistico();
		}

	}
}
