package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import modelo.Album;
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelAñadirCancion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField txtDuracion;
	private JTextField txtInvitado;

	private String[] arrayMusicos;
	private String[] arrayAlbumes = new String[1];

	private ArrayList<Musico> musicos = new ArrayList<Musico>();
	private ArrayList<Album> albumes = new ArrayList<Album>();

	private JComboBox<String> comBoxMusicos;
	private JComboBox<String> comBoxAlbumes;

	/**
	 * Create the panel.
	 */
	public PanelAñadirCancion(VentanaPrincipal v, GestionBD gestionBD) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();
		albumes = gestionBD.devolverAlbumes();

		cargarArtistas();

		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JLabel lblAñadirMusico = new JLabel("AÑADIR CANCION");
		lblAñadirMusico.setBounds(125, 15, 550, 65);
		lblAñadirMusico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirMusico.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		add(lblAñadirMusico);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(19);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

		JLabel lblNombre = new JLabel("Nombre de la  cancion: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(115, 185, 200, 20);
		add(lblNombre);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(115, 220, 200, 25);
		add(textField);

		JLabel lblGenero = new JLabel("Duracion (m:s):");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenero.setBounds(115, 255, 200, 20);
		add(lblGenero);

		txtDuracion = new JTextField();
		txtDuracion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(115, 290, 200, 25);
		add(txtDuracion);

		JLabel lblLanzamiento = new JLabel("Artista invitado:");
		lblLanzamiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLanzamiento.setBounds(115, 325, 200, 20);
		add(lblLanzamiento);

		txtInvitado = new JTextField();
		txtInvitado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtInvitado.setColumns(10);
		txtInvitado.setBounds(115, 360, 200, 25);
		add(txtInvitado);

		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.setBounds(115, 450, 150, 30);
		add(btnAñadir);

		comBoxMusicos = new JComboBox<String>(arrayMusicos);
		comBoxMusicos.setBounds(115, 100, 200, 35);
		add(comBoxMusicos);

		comBoxAlbumes = new JComboBox<String>();
		comBoxAlbumes.setBounds(355, 100, 200, 35);
		add(comBoxAlbumes);

		comBoxMusicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comBoxAlbumes.removeAllItems();
				cargarAlbumesPorArtista(comBoxMusicos.getSelectedItem().toString(), gestionBD);
				comBoxAlbumes.setSelectedItem(null);
				for (int i = 0; i < albumes.size(); i++) {
					comBoxAlbumes.addItem(arrayAlbumes[i]);
				}
				repaint();
			}
		});

	}

	private void cargarArtistas() {
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
}
