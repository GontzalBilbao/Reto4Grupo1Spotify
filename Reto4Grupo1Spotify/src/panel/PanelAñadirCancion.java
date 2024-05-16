package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import modelo.Album;
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelAñadirCancion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtDuracion;
	private JTextField txtInvitado;
	private JLabel lblImagenAlbum;

	private String nombreEscrito;

	private String[] arrayMusicos;
	private String[] arrayAlbumes = new String[1];

	private ArrayList<Musico> musicos = new ArrayList<Musico>();
	private ArrayList<Album> albumes = new ArrayList<Album>();

	private JComboBox<String> comBoxMusicos;
	private JComboBox<String> comBoxAlbumes;

	/**
	 * Create the panel.
	 */
	public PanelAñadirCancion(VentanaPrincipal vp, GestionBD gestionBD) {
		setSize(vp.getSize());
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();
		albumes = gestionBD.devolverAlbumes();

		cargarMusicos();
		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "");
				vp.cambiarDePanel(19);
			}
		});
		btnFinalizar.setBounds(560, 450, 200, 50);
		add(btnFinalizar);

		JLabel lblAñadirMusico = new JLabel("AÑADIR CANCION");
		lblAñadirMusico.setBounds(150, 15, 500, 65);
		lblAñadirMusico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñadirMusico.setFont(new Font("Sitka Subheading", Font.BOLD, 40));
		add(lblAñadirMusico);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(19);
			}
		});
		btnAtras.setBounds(650, 25, 100, 35);
		add(btnAtras);

		JLabel lblNombre = new JLabel("Nombre de la  cancion: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(115, 185, 200, 20);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBounds(115, 220, 200, 25);
		add(txtNombre);

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
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreEscrito = txtNombre.getText();
				boolean validado = validarCampos(nombreEscrito, vp);

				if (validado == true) {
					// añadir query de gestionBD de insertar album
					JOptionPane.showMessageDialog(vp, "Se ha agregado la cancion.");
				}
			}
		});
		btnAñadir.setBounds(115, 450, 150, 30);
		add(btnAñadir);

		comBoxMusicos = new JComboBox<String>(arrayMusicos);
		comBoxMusicos.setBounds(150, 120, 200, 35);
		add(comBoxMusicos);

		comBoxAlbumes = new JComboBox<String>();
		comBoxAlbumes.setBounds(390, 120, 200, 35);
		add(comBoxAlbumes);

		lblImagenAlbum = new JLabel();
		lblImagenAlbum.setBounds(560, 185, 200, 200);
		add(lblImagenAlbum);
		
		JLabel lblComBoxMusico = new JLabel("Musico:");
		lblComBoxMusico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComBoxMusico.setBounds(150, 89, 200, 20);
		add(lblComBoxMusico);
		
		JLabel lblComBoxAlbum = new JLabel("Album:");
		lblComBoxAlbum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComBoxAlbum.setBounds(390, 89, 200, 20);
		add(lblComBoxAlbum);

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

		comBoxAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < albumes.size(); i++) {
					if (comBoxAlbumes.getSelectedItem() != null) {
						if (comBoxAlbumes.getSelectedItem().toString().equals(albumes.get(i).getTitulo())) {
							Image image = albumes.get(i).getImagen().getImage();
							Image nuevaImagen = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
							ImageIcon ImagenReescalada = new ImageIcon(nuevaImagen);
							lblImagenAlbum.setIcon(ImagenReescalada);
							lblImagenAlbum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						}
					}
				}
			}
		});

	}

	private void cargarMusicos() {
		arrayMusicos = new String[musicos.size()];
		for (int i = 0; i < musicos.size(); i++) {
			arrayMusicos[i] = musicos.get(i).getNombreArtistico();
		}
	}

	private boolean validarCampos(String txtNombre, VentanaPrincipal vp) {
		boolean validar = false;
		if (txtNombre.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(vp, "El nombre no puede estar vacio.");
			validar = false;
		} else {
			validar = true;
		}
		return validar;
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
