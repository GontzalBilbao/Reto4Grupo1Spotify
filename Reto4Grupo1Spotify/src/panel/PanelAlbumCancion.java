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
	public PanelAlbumCancion(VentanaPrincipal vp, GestionBD gestionBD, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);

		idMusico = gestionInfo.devolverArtistaSeleccionado();
		gestionBD.cargarAlbumesDelMusico(idMusico);
		albumes = gestionBD.devolverAlbumes();
		for (int i = 0; i < albumes.size(); i++) {
			System.out.println(albumes.get(i).getTitulo());
		}
		tituloAlbum = gestionInfo.devolverAlbumSeleccionado();
		for (int i = 0; i < albumes.size(); i++) {
			if (tituloAlbum.equals(albumes.get(i).getTitulo())) {
				albumSeleccionado = i;
				año = albumes.get(i).getAño();
				genero = albumes.get(i).getGenero();
				gestionBD.cargarCancionesDelAlbum(albumes.get(i).getIdAlbum());
				canciones = gestionBD.devolverCanciones();
			}
		}
		
		for (int i = 0; i < canciones.size(); i++) {
			System.out.println(canciones.get(i).getNombre());
		}

		JLabel lblTituloLista = new JLabel("CANCIONES");
		lblTituloLista.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblTituloLista.setBounds(540, 11, 130, 28);
		add(lblTituloLista);

		JButton btnAtrás = new JButton("Atrás");
		btnAtrás.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(4);
			}
		});
		btnAtrás.setBounds(10, 11, 89, 23);
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
		lblImagenAlbum.setBounds(20, 50, 175, 190);
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
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setBounds(685, 11, 89, 23);
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
			panelItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel clickedPanel = (JPanel) e.getSource();
					gestionInfo.guardarAlbumSeleccionado(((JLabel) clickedPanel.getComponent(1)).getText());
					vp.cambiarDePanel(7);
					JOptionPane.showMessageDialog(null, "Has hecho clic en: " + clickedPanel.getName()
							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
//													+ ((JLabel) clickedPanel.getComponent(2)).getText());

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
		scrollPaneCanciones.setSize(300, 500);
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
					gestionInfo.guardarArtistaSeleccionado(idMusico);
					vp.cambiarDePanel(6);
					JOptionPane.showMessageDialog(null, "Has hecho clic en: " + clickedPanel.getName()
							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
//											+ ((JLabel) clickedPanel.getComponent(2)).getText());

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
		scrollPaneOtrosAlbumes.setLocation(20, 267);
		// Agregar el JScrollPane a la ventana
		add(scrollPaneOtrosAlbumes);

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
