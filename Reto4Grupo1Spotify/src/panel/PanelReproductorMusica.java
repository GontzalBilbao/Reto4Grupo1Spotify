package panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import controlador.ControladorDeSonido;
import controlador.GestionBD;
import vista.VentanaPrincipal;

public class PanelReproductorMusica extends JPanel {

	private static final long serialVersionUID = 1L;

	private int intinerador = 0;
	private ControladorDeSonido controladorDeSonido;
	private boolean aleatorio = false;
	private JLabel lblIconoGrande;
	private JLabel lblImagenCancion;
	private JLabel lblTitulo;
	private JButton btnPlay;
	private JButton btnPlayStop;

	/**
	 * Create the panel.
	 */
	public PanelReproductorMusica(VentanaPrincipal v, GestionBD gestion) {
	

		controladorDeSonido = new ControladorDeSonido(gestion.queryAudioCancion());

		for (int i = 0; i < 5; i++) {
			System.out.println(gestion.queryAudioCancion().get(i).getNombre());
		}

		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);

		lblIconoGrande = new JLabel("");
		lblIconoGrande.setIcon(new ImageIcon("icono/spotifyicon.png"));
		lblIconoGrande.setBounds(0, 0, 100, 100);
		add(lblIconoGrande);

		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(1);
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.setBounds(200, 36, 90, 35);
		add(btnAtras);

		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.cambiarDePanel(11);
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.setBounds(500, 36, 90, 35);
		add(btnPerfil);

		JButton btnCancionAnterior = new JButton("<");
		btnCancionAnterior.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionAnterior.setBackground(Color.BLACK);
		btnCancionAnterior.setForeground(Color.WHITE);
		btnCancionAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Para que vuelba al inicio de reproduccion sin dar erro */
				if (aleatorio = true)
				if (aleatorio = true)
					if (intinerador == 0) {
						intinerador = gestion.queryAudioCancion().size() - 1;
					} else {
						intinerador = (intinerador - 1) % gestion.queryAudioCancion().size();
					}
				controladorDeSonido.setCancionEnReproduccion(intinerador);
				lblImagenCancion.setIcon(gestion.queryAlbum().get(0).getImagen());
				lblTitulo.setText("<html>" + gestion.queryAudioCancion().get(intinerador).getNombre() + "</html>");

			}
		});
		btnCancionAnterior.setBounds(165, 265, 70, 30);
		add(btnCancionAnterior);

		JButton btnCancionSiguiente = new JButton(">");
		btnCancionSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				intinerador = (intinerador + 1) % gestion.queryAudioCancion().size();

				controladorDeSonido.setCancionEnReproduccion(intinerador);
				lblImagenCancion.setIcon(gestion.queryAlbum().get(0).getImagen());
				lblTitulo.setText("<html>" + gestion.queryAudioCancion().get(intinerador).getNombre() + "</html>");
				btnPlay.setVisible(true);
				btnPlayStop.setVisible(false);
			}
		});
		btnCancionSiguiente.setBackground(Color.BLACK);
		btnCancionSiguiente.setForeground(Color.WHITE);
		btnCancionSiguiente.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancionSiguiente.setBounds(565, 265, 70, 30);
		add(btnCancionSiguiente);

		btnPlay = new JButton("PLAY");
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setBackground(Color.BLACK);
		btnPlay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeSonido.reproducir(intinerador);
				btnPlay.setVisible(false);
				btnPlayStop.setVisible(true);
			}
		});
		btnPlay.setBounds(355, 425, 90, 30);
		add(btnPlay);

		JButton btnFavorito = new JButton("FAVORITO");
		btnFavorito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFavorito.setBackground(Color.BLACK);
		btnFavorito.setForeground(Color.WHITE);
		btnFavorito.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFavorito.setBounds(230, 470, 150, 30);
		add(btnFavorito);

		lblImagenCancion = new JLabel();
		lblImagenCancion.setIcon(gestion.queryAlbum().get(intinerador).getImagen());
		lblImagenCancion.setBounds(275, 150, 250, 250);
		add(lblImagenCancion);

		lblTitulo = new JLabel("");
		lblTitulo.setText(gestion.queryAudioCancion().get(intinerador).getNombre());
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(275, 100, 250, 30);
		add(lblTitulo);

		btnPlayStop = new JButton("STOP");
		btnPlayStop.setVisible(false);
		btnPlayStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeSonido.continuarCancion(btnPlayStop);
			}
		});
		btnPlayStop.setBackground(Color.BLACK);
		btnPlayStop.setForeground(Color.WHITE);
		btnPlayStop.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPlayStop.setBounds(355, 425, 90, 30);
		add(btnPlayStop);
		
		JButton btnmMenu = new JButton("MENU");
		btnmMenu.setBackground(Color.BLACK);
		btnmMenu.setForeground(Color.WHITE);
		btnmMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnmMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnmMenu.setBounds(420, 470, 150, 30);
		add(btnmMenu);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(btnmMenu, popupMenu_1);
		
		JMenuItem mntmAñadirAPlayList = new JMenuItem("Añadir  a PalyList");
		mntmAñadirAPlayList.setForeground(Color.WHITE);
		mntmAñadirAPlayList.setBackground(Color.BLACK);
		popupMenu_1.add(mntmAñadirAPlayList);
		
		JMenuItem mntmExportarCancion = new JMenuItem("Exportear Canción");
		mntmExportarCancion.setBackground(Color.BLACK);
		mntmExportarCancion.setForeground(Color.WHITE);
		popupMenu_1.add(mntmExportarCancion);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
