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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controlador.GestionInformacion;
import modelo.Album;
import modelo.Cancion;
import vista.VentanaPrincipal;

public class PanelAlbumCancion extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Album> albumes = new ArrayList<Album>();
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	private String idMusico = "";
	private String tituloAlbum = "";
	private String año = "";
	private String genero = "";
	private int albumSeleccionado = 0;

	/**
	 * Create the panel.
	 */
	public PanelAlbumCancion(VentanaPrincipal vp, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);

		idMusico = gestionInfo.devolverIdArtistaSeleccionado();
		gestionInfo.cargarAlbumesDelMusico(idMusico);
		albumes = gestionInfo.devolverAlbumes();
//		for (int i = 0; i < albumes.size(); i++) {
//			System.out.println(albumes.get(i).getTitulo());
//		}
		tituloAlbum = gestionInfo.devolverAlbumSeleccionado();
		for (int i = 0; i < albumes.size(); i++) {
			if (tituloAlbum.equals(albumes.get(i).getTitulo())) {
				albumSeleccionado = i;
				año = albumes.get(i).getAño();
				genero = albumes.get(i).getGenero();
				gestionInfo.cargarCancionesDelAlbum(albumes.get(i).getIdAlbum());
				canciones = gestionInfo.devolverCanciones();
			}
		}
		
		JLabel lblTituloLista = new JLabel("CANCIONES");

		lblTituloLista.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblTituloLista.setBounds(530, 11, 200, 28);

		lblTituloLista.setFont(new Font("Arial", Font.PLAIN, 40));
		lblTituloLista.setBounds(540, 11, 130, 28);

		add(lblTituloLista);

		JButton btnAtrás = new JButton("Atrás");
		btnAtrás.setBackground(Color.BLACK);
		btnAtrás.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtrás.setForeground(Color.WHITE);
		btnAtrás.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(5);
			}
		});
		btnAtrás.setBounds(10, 0, 90, 35);
		add(btnAtrás);

		JLabel lblTitulo = new JLabel("Nombre: " + tituloAlbum);
		lblTitulo.setBounds(204, 68, 228, 14);
		add(lblTitulo);

		JLabel lblImagenAlbum = new JLabel("New label");
		Image image = albumes.get(albumSeleccionado).getImagen().getImage();
		Image nuevaImagen = image.getScaledInstance(175, 190, Image.SCALE_SMOOTH);
		ImageIcon ImagenReescalada = new ImageIcon(nuevaImagen);
		lblImagenAlbum.setIcon(ImagenReescalada);
		lblImagenAlbum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblImagenAlbum.setBounds(10, 50, 175, 190);
		add(lblImagenAlbum);

		JLabel lblAño = new JLabel("Año publicación: " + año);
		lblAño.setBounds(204, 93, 228, 14);
		add(lblAño);

		JLabel lblGenero = new JLabel("<html>" + "Género: " + genero.replaceAll("\\n", "<br>") + "</html>");
		lblGenero.setVerticalAlignment(SwingConstants.TOP);
		lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenero.setBounds(204, 118, 228, 130);
		add(lblGenero);

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setBackground(Color.BLACK);
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.nPanel = 6;
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setBounds(686, 0, 90, 35);
		add(btnPerfil);

		// Crear un panel para contener los JLabels
		JPanel panelCanciones = new JPanel();
//						panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//						panel.setOpaque(false);
		panelCanciones.setLayout(new GridLayout(0, 1));

		// Agregar JLabels al panel
		for (int i = 0; i < canciones.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
//							panelItem.setOpaque(false);
//							panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
			panelItem.setLayout(new GridLayout());

			// Cargar imagen
			ImageIcon imageIcon = canciones.get(i).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

			// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(canciones.get(i).getNombre()); // podcasts.get(i).getNombre()
//							JLabel label2 = new JLabel("Autor: " + i);
			panelItem.add(label1);
//							panelItem.add(label2);
			// Le damos nombre para identificarlo
			panelItem.setName("panel " + i);
			// Añadir un borde al panelItem
			panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			// Añadir escuchador al panel
//			gestionInfo.recogerIndiceCancion(i);
			panelItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel clickedPanel = (JPanel) e.getSource();
					gestionInfo.guardarAlbumSeleccionado(tituloAlbum);
					gestionInfo.guardarCancionSeleccionado(((JLabel) clickedPanel.getComponent(1)).getText());
					gestionInfo.guardarPanelAnteriorAlbumCanciones(true);
					vp.cambiarDePanel(7);

				}
			});
			// Agregar panelItem al panel principal
			panelCanciones.add(panelItem);
		}

		// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneCanciones = new JScrollPane(panelCanciones);
		// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneCanciones.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneCanciones.setBorder(null);
//						scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//						scrollPane.setOpaque(false);
		scrollPaneCanciones.setSize(323, 502);
		scrollPaneCanciones.setLocation(453, 50);
		// Agregar el JScrollPane a la ventana
		add(scrollPaneCanciones);

		// Crear un panel para contener los JLabels
		JPanel panelOtrosAlbumes = new JPanel();
//				panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//				panel.setOpaque(false);
		panelOtrosAlbumes.setLayout(new GridLayout(1, 0));

		removerAlbumElegido();
		// Agregar JLabels al panel
		for (int i = 0; i < albumes.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
//					panelItem.setOpaque(false);
//					panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
			panelItem.setLayout(new GridLayout(0, 1));

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
					gestionInfo.guardarAlbumSeleccionado(((JLabel) clickedPanel.getComponent(1)).getText());
					gestionInfo.guardarIdArtistaSeleccionado(idMusico);
					vp.cambiarDePanel(6);

				}
			});
			// Agregar panelItem al panel principal
			panelOtrosAlbumes.add(panelItem);
		}

		// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneOtrosAlbumes = new JScrollPane(panelOtrosAlbumes);
		// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneOtrosAlbumes.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneOtrosAlbumes.setBorder(null);
//				scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//				scrollPane.setOpaque(false);
		scrollPaneOtrosAlbumes.setSize(412, 283);
		scrollPaneOtrosAlbumes.setLocation(10, 269);
		// Agregar el JScrollPane a la ventana
		add(scrollPaneOtrosAlbumes);

		String textoLabelCanciones = "NO HAY CANCIONES DE ESTE ARTISTA GUARDADOS";
		JLabel lblNoHayCanciones = new JLabel("<html>	" + textoLabelCanciones.replaceAll("\\n", "<br>") + "</html>");
		lblNoHayCanciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoHayCanciones.setVerticalAlignment(SwingConstants.CENTER);
		lblNoHayCanciones.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNoHayCanciones.setBounds(520, 190, 189, 175);
		add(lblNoHayCanciones);

		String textoLabelOtrosAlbumes = "NO HAY MÁS ÁLBUMES DE ESTE ARTISTA GUARDADOS";
		JLabel lblNoHayMasAlbumes = new JLabel(
				"<html>	" + textoLabelOtrosAlbumes.replaceAll("\\n", "<br>") + "</html>");
		lblNoHayMasAlbumes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoHayMasAlbumes.setVerticalAlignment(SwingConstants.CENTER);
		lblNoHayMasAlbumes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNoHayMasAlbumes.setBounds(120, 320, 214, 175);
		add(lblNoHayMasAlbumes);

		JLabel lblTituloListaAlbumes = new JLabel("OTROS ALBUMES");
		lblTituloListaAlbumes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblTituloListaAlbumes.setBounds(111, 241, 200, 28);
		add(lblTituloListaAlbumes);

		if (canciones.isEmpty()) {
			panelCanciones.setVisible(false);
			scrollPaneCanciones.setVisible(false);
		} else {
			lblNoHayCanciones.setVisible(false);
		}

		if (albumes.size() < 1) {
			panelOtrosAlbumes.setVisible(false);
			scrollPaneOtrosAlbumes.setVisible(false);
		} else {
			lblNoHayMasAlbumes.setVisible(false);
		}
	}

	private void removerAlbumElegido() {
		for (int i = 0; i < albumes.size(); i++) {
			if (albumes.get(i).getTitulo().equals(tituloAlbum)) {
				albumes.remove(i);
			} else {

			}
		}

	}
}
