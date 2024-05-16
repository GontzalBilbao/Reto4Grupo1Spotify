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
import modelo.Musico;
import vista.VentanaPrincipal;

public class PanelMusicoAlbumes extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Musico> musicos = new ArrayList<Musico>();
	private ArrayList<Album> albumes = new ArrayList<Album>();
	private String nombreArtista = "";
	private String caracteristica = "";
	private String descripcion = "";
	private int musicoSeleccionado = 0;
	private String idMusico = "";

	/**
	 * Create the panel.
	 */
	public PanelMusicoAlbumes(VentanaPrincipal vp, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.WHITE);
		setLayout(null);

		gestionInfo.cargarMusicos();
		musicos = gestionInfo.devolverMusicos();
		nombreArtista = gestionInfo.devolverArtistaSeleccionado();
		for (int i = 0; i < musicos.size(); i++) {
			if (nombreArtista.equals(musicos.get(i).getNombreArtistico())) {
				musicoSeleccionado = i;
				idMusico = musicos.get(i).getIdMusico();
				caracteristica = musicos.get(i).getCaracteristica();
				descripcion = musicos.get(i).getDescripcion();
				gestionInfo.cargarAlbumesDelMusico(musicos.get(i).getIdMusico());
				albumes = gestionInfo.devolverAlbumes();
			}
		}

		JLabel lblTituloLista = new JLabel("ÁLBUMES");
		lblTituloLista.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblTituloLista.setBounds(540, 11, 130, 28);
		add(lblTituloLista);

		JButton btnAtrás = new JButton("Atrás");
		btnAtrás.setBackground(Color.BLACK);
		btnAtrás.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtrás.setForeground(Color.WHITE);
		btnAtrás.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(4);
			}
		});
		btnAtrás.setBounds(10, 0, 90, 35);
		add(btnAtrás);

		JLabel lblNombre = new JLabel("Nombre: " + nombreArtista);
		lblNombre.setBounds(204, 68, 228, 14);
		add(lblNombre);

		JLabel lblImagenArtista = new JLabel("New label");
		Image image = musicos.get(musicoSeleccionado).getImagen().getImage();
		Image nuevaImagen = image.getScaledInstance(175, 190, Image.SCALE_SMOOTH);
		ImageIcon ImagenReescalada = new ImageIcon(nuevaImagen);
		lblImagenArtista.setIcon(ImagenReescalada);
		lblImagenArtista.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblImagenArtista.setBounds(20, 50, 175, 190);
		add(lblImagenArtista);

		JLabel lblCaracteristica = new JLabel("Característica: " + caracteristica);
		lblCaracteristica.setBounds(204, 93, 228, 14);
		add(lblCaracteristica);

		JLabel lblDescripcion = new JLabel(
				"<html>" + "Descripción: " + descripcion.replaceAll("\\n", "<br>") + "</html>");
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(204, 118, 228, 130);
		add(lblDescripcion);

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setBackground(Color.BLACK);
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.nPanel = 5;
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setBounds(686, 0, 90, 35);
		add(btnPerfil);

		// Crear un panel para contener los JLabels
		JPanel panelAlbumes = new JPanel();
//						panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//						panel.setOpaque(false);
		panelAlbumes.setLayout(new GridLayout(0, 1));
		
		// Agregar JLabels al panel
		for (int i = 0; i < albumes.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
//							panelItem.setOpaque(false);
//							panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
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
					gestionInfo.guardarIdArtistaSeleccionado(idMusico);
					vp.cambiarDePanel(6);
//					JOptionPane.showMessageDialog(null, "Has hecho clic en: " + clickedPanel.getName()
//							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
//													+ ((JLabel) clickedPanel.getComponent(2)).getText());

				}
			});
			// Agregar panelItem al panel principal
			panelAlbumes.add(panelItem);
		}

		// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneAlbumes = new JScrollPane(panelAlbumes);
		// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneAlbumes.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneAlbumes.setBorder(null);
//						scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//						scrollPane.setOpaque(false);
		scrollPaneAlbumes.setSize(323, 502);
		scrollPaneAlbumes.setLocation(453, 50);
		// Agregar el JScrollPane a la ventana
		add(scrollPaneAlbumes);
		
		// Crear un panel para contener los JLabels
		JPanel panelOtrosMusicos = new JPanel();
//				panel.setBackground(new java.awt.Color(0, 0, 0, 0));
//				panel.setOpaque(false);
		panelOtrosMusicos.setLayout(new GridLayout(1, 0));

		removerMusicoElegido();
		// Agregar JLabels al panel
		for (int i = 0; i < musicos.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
//					panelItem.setOpaque(false);
//					panelItem.setBackground(new java.awt.Color(0, 0, 0, 0));
			panelItem.setLayout(new GridLayout(0, 1));

			// Cargar imagen
			ImageIcon imageIcon = musicos.get(i).getImagen();
			Image image1 = imageIcon.getImage();
			Image newImage = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon newImageIcon = new ImageIcon(newImage);
			JLabel imageLabel = new JLabel(newImageIcon);
			panelItem.add(imageLabel);

			// Agregar JLabels debajo de la imagen
			JLabel label1 = new JLabel(musicos.get(i).getNombreArtistico()); // podcasts.get(i).getNombre()
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
					vp.cambiarDePanel(5);
//					JOptionPane.showMessageDialog(null, "Has hecho clic en: " + clickedPanel.getName()
//							+ " que tiene los labels:" + ((JLabel) clickedPanel.getComponent(1)).getText()); // + " y "
//											+ ((JLabel) clickedPanel.getComponent(2)).getText());

				}
			});
			// Agregar panelItem al panel principal
			panelOtrosMusicos.add(panelItem);
		}

		// Crear un JScrollPane y agregar el panel
		JScrollPane scrollPaneOtrosMusicos = new JScrollPane(panelOtrosMusicos);
		// Como se mueve muy despacio vamos a darle un poco de velocidad
		scrollPaneOtrosMusicos.getVerticalScrollBar().setUnitIncrement(30);
		scrollPaneOtrosMusicos.setBorder(null);
//				scrollPane.setBackground(new java.awt.Color(0, 0, 0, 0));
//				scrollPane.setOpaque(false);
		scrollPaneOtrosMusicos.setSize(412, 283);
		scrollPaneOtrosMusicos.setLocation(10, 269);
		// Agregar el JScrollPane a la ventana
		add(scrollPaneOtrosMusicos);
		
		String textoLabelAlbumes = "NO HAY ÁLBUMES DE ESTE ARTISTA GUARDADOS";
		JLabel lblNoHayAlbumes = new JLabel("<html>	" + textoLabelAlbumes.replaceAll("\\n", "<br>") + "</html>");
		lblNoHayAlbumes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoHayAlbumes.setVerticalAlignment(SwingConstants.CENTER);
		lblNoHayAlbumes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNoHayAlbumes.setBounds(520, 190, 189, 175);
		add(lblNoHayAlbumes);

		JLabel lblTituloListaMusicos = new JLabel("OTROS MUSICOS");
		lblTituloListaMusicos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblTituloListaMusicos.setBounds(111, 241, 200, 28);
		add(lblTituloListaMusicos);
		
		if(albumes.isEmpty()) {
			panelAlbumes.setVisible(false);
			scrollPaneAlbumes.setVisible(false);
		} else {
			lblNoHayAlbumes.setVisible(false);
		}
	}

	private void removerMusicoElegido() {
		for (int i = 0; i < musicos.size(); i++) {
			if (musicos.get(i).getNombreArtistico().equals(nombreArtista)) {
				musicos.remove(i);
			} else {

			}
		}

	}
}
