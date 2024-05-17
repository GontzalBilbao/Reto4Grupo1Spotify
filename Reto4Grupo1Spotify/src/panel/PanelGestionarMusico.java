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
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelGestionarMusico extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Musico> musicos = new ArrayList<Musico>();

	private JLabel lblMusicoActual;
	private String musicoActual = "";

	private JPanel panelItem;

	private String caracteristica = "";
	private String descripcion = "";

	private String idMusico = "";
	private String nuevoNombre = "";
	private String nuevaCaracteristica = "";
	private String nuevaDescripcion = "";

	private int musicoSeleccionado = 0;

	public PanelGestionarMusico(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
//		setBackground(Color.DARK_GRAY);
		setLayout(null);

		gestionBD.cargarMusicos();
		musicos = gestionBD.devolverMusicos();

		JPanel panelMusicos = new JPanel();
		panelMusicos.setBorder(new LineBorder(Color.black, 1, true));

		panelMusicos.setLayout(new GridLayout(0, 1));

// Agregar JLabels al panel
		for (int i = 0; i < musicos.size(); i++) {
			musicoSeleccionado = i;
			panelItem = new JPanel();
			panelItem.setBorder(null);
			panelItem.setLayout(new GridLayout());

// Cargar imagen
			ImageIcon imageIcon = musicos.get(i).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(musicos.get(i).getNombreArtistico()); // podcasts.get(i).getNombre()
//			JLabel label2 = new JLabel("Autor: " + i);
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
					musicoActual = ((JLabel) clickedPanel.getComponent(1)).getText();
					lblMusicoActual.setText("Musico Actual: " + musicoActual);

					for (int i = 0; i < musicos.size(); i++) {
						musicoSeleccionado = i;
						if (musicoActual.equals(musicos.get(i).getNombreArtistico())) {
							idMusico = musicos.get(musicoSeleccionado).getIdMusico();
							nuevoNombre = musicos.get(musicoSeleccionado).getNombreArtistico();
							caracteristica = musicos.get(musicoSeleccionado).getCaracteristica();
							descripcion = musicos.get(musicoSeleccionado).getDescripcion();
						}

					}

					System.out.println(idMusico);
					System.out.println(nuevoNombre);
					System.out.println(caracteristica);
					System.out.println(descripcion);

				}
			});
// Agregar panelItem al panel principal
			panelMusicos.add(panelItem);
		}

// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneMusicos = new JScrollPane(panelMusicos);
// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneMusicos.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneMusicos.setSize(500, 425);
		scrollPaneMusicos.setLocation(30, 100);
		add(scrollPaneMusicos);

		JLabel lblMusicos = new JLabel("MUSICOS");
		lblMusicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicos.setFont(new Font("Arial", Font.PLAIN, 40));
		lblMusicos.setBounds(30, 11, 550, 67);
		add(lblMusicos);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String opcionSeleccionada = JOptionPane.showInputDialog(null,
						"Selecciona una opción \n [1] Nombre del artista \n [2] tipo (Solista/Grupo) \n [3] Descripcion",
						"Modificar Musico");
				int opcion = 0;

				try {
					opcion = Integer.parseInt(opcionSeleccionada);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "No es un valor numerico");
				}
				switch (opcion) {
				case 1:
					nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo nombre para el Musico " + musicoActual,
							"Modificar nombre");

					gestionBD.modificarMusico(musicoActual, caracteristica, descripcion, idMusico);
					break;
				case 2:
					nuevaCaracteristica = JOptionPane.showInputDialog(null, "Nuevo tipo para el Musico " + musicoActual,
							"Modificar tipo");
					gestionBD.modificarMusico(musicoActual, caracteristica, descripcion, idMusico);
					break;
				case 3:
					nuevaDescripcion = JOptionPane.showInputDialog(null,
							"Nueva descripcion para el Musico " + musicoActual, "Modificar descripcion");
					System.out.println(musicoActual + caracteristica + nuevaDescripcion);
					gestionBD.modificarMusico(musicoActual, caracteristica, descripcion, idMusico);
					break;
				default:
					JOptionPane.showMessageDialog(null, "No es una opcion valida");
					break;
				}

			}
		});
		btnModificar.setBounds(560, 145, 200, 50);
		add(btnModificar);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (musicoActual != null) {

					boolean borrado = gestionBD.borrarMusico(musicoActual);
					if (borrado != false) {
						JOptionPane.showMessageDialog(null, "Musico borrado!");
						panelMusicos.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un artista para eliminarlo");
				}
				panelMusicos.repaint();

			}
		});

		btnEliminar.setBounds(560, 435, 200, 50);
		add(btnEliminar);

		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(15);
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

		lblMusicoActual = new JLabel("Musico Actual: Ninguno");
		lblMusicoActual.setBounds(560, 120, 200, 20);
		add(lblMusicoActual);
	}
}
