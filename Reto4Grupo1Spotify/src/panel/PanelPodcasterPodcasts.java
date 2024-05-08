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

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.Podcast;
import modelo.Podcaster;
import vista.VentanaPrincipal;

public class PanelPodcasterPodcasts extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Podcaster> podcasters = new ArrayList<Podcaster>();
	private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
	private String nombreArtista = "";
	private String genero = "";
	private String descripcion = "";
	private int podcasterSeleccionado = 0;

	/**
	 * Create the panel.
	 */
	public PanelPodcasterPodcasts(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);

		gestionBD.cargarPodcasters();
		podcasters = gestionBD.devolverPodcasters();
		nombreArtista = gestionInfo.devolverArtistaSeleccionado();
		for (int i = 0; i < podcasters.size(); i++) {
			if (nombreArtista.equals(podcasters.get(i).getNombreArtista())) {
				podcasterSeleccionado = i;
				genero = podcasters.get(i).getGenero();
				descripcion = podcasters.get(i).getDescripcion();
				gestionBD.cargarPodcastsDelPodcaster(podcasters.get(i).getIdPodcaster());
				podcasts = gestionBD.devolverPodcasts();
			}
		}

		JLabel lblTituloLista = new JLabel("PODCASTS");
		lblTituloLista.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblTituloLista.setBounds(540, 11, 130, 28);
		add(lblTituloLista);

		JButton btnAtrás = new JButton("Atrás");
		btnAtrás.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(8);
			}
		});
		btnAtrás.setBounds(10, 11, 89, 23);
		add(btnAtrás);

		JLabel lblNombre = new JLabel("Nombre: " + nombreArtista);
		lblNombre.setBounds(204, 68, 228, 14);
		add(lblNombre);

		JLabel lblImagenArtista = new JLabel("New label");
		Image image = podcasters.get(podcasterSeleccionado).getImagen().getImage();
		Image nuevaImagen = image.getScaledInstance(175, 190, Image.SCALE_SMOOTH);
		ImageIcon ImagenReescalada = new ImageIcon(nuevaImagen);
		lblImagenArtista.setIcon(ImagenReescalada);
		lblImagenArtista.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblImagenArtista.setBounds(20, 50, 175, 190);
		add(lblImagenArtista);

		JLabel lblGenero = new JLabel("Género: " + genero);
		lblGenero.setBounds(204, 93, 228, 14);
		add(lblGenero);

		JLabel lblDescripcion = new JLabel(
				"<html>" + "Descripción: " + descripcion.replaceAll("\\n", "<br>") + "</html>");
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(204, 118, 228, 120);
		add(lblDescripcion);

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setBounds(685, 11, 89, 23);
		add(btnPerfil);
		
		// Crear un panel para contener los JLabels
		JPanel panelPodcasts = new JPanel();
//						panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//						panel.setOpaque(false);
		panelPodcasts.setLayout(new GridLayout(0, 1));

		// Agregar JLabels al panel
		for (int i = 0; i < podcasts.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
//							panelItem.setOpaque(false);
//							panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
			panelItem.setLayout(new GridLayout());

			// Cargar imagen
			ImageIcon imageIcon = podcasters.get(podcasterSeleccionado).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

			// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(podcasts.get(i).getNombre()); // podcasts.get(i).getNombre()
//							JLabel label2 = new JLabel("Autor: " + i);
			panelItem.add(label1);
//							panelItem.add(label2);
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
//													+ ((JLabel) clickedPanel.getComponent(2)).getText());

				}
			});
			// Agregar panelItem al panel principal
			panelPodcasts.add(panelItem);
		}

		// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPanePodcasts = new JScrollPane(panelPodcasts);
		// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPanePodcasts.getVerticalScrollBar().setUnitIncrement(30);
		scrollPanePodcasts.setBorder(null);
//						scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//						scrollPane.setOpaque(false);
		scrollPanePodcasts.setSize(300, 500);
		scrollPanePodcasts.setLocation(453, 50);
		// Agregar el JScrollPane a la ventana
		add(scrollPanePodcasts);

		// Crear un panel para contener los JLabels
		JPanel panelOtrosPodcasters = new JPanel();
//				panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//				panel.setOpaque(false);
		panelOtrosPodcasters.setLayout(new GridLayout(1, 0));

		removerPodcasterElegido();
		// Agregar JLabels al panel
		for (int i = 0; i < podcasters.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
//					panelItem.setOpaque(false);
//					panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
			panelItem.setLayout(new GridLayout(0, 1));

			// Cargar imagen
			ImageIcon imageIcon = podcasters.get(i).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

			// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(podcasters.get(i).getNombreArtista()); // podcasts.get(i).getNombre()
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
					gestionInfo.guardarArtistaSeleccionado(((JLabel) clickedPanel.getComponent(1)).getText());
					vp.cambiarDePanel(9);
					JOptionPane.showMessageDialog(null, "Has hecho clic en: " + clickedPanel.getName()
							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
//											+ ((JLabel) clickedPanel.getComponent(2)).getText());

				}
			});
			// Agregar panelItem al panel principal
			panelOtrosPodcasters.add(panelItem);
		}

		// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneOtrosPodcasters = new JScrollPane(panelOtrosPodcasters);
		// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneOtrosPodcasters.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneOtrosPodcasters.setBorder(null);
//				scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//				scrollPane.setOpaque(false);
		scrollPaneOtrosPodcasters.setSize(412, 283);
		scrollPaneOtrosPodcasters.setLocation(20, 267);
		// Agregar el JScrollPane a la ventana
		add(scrollPaneOtrosPodcasters);

		

	}

	private void removerPodcasterElegido() {
		for (int i = 0; i < podcasters.size(); i++) {
			if (podcasters.get(i).getNombreArtista().equals(nombreArtista)) {
				podcasters.remove(i);
			} else {

			}
		}

	}
}
