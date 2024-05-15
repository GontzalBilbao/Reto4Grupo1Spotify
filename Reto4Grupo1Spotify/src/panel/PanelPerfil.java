package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import controlador.GestionInformacion;
import modelo.PlayList;
import vista.VentanaPrincipal;

public class PanelPerfil extends JPanel {
	
	private ArrayList<PlayList> playlists = new ArrayList<PlayList>();

	private static final long serialVersionUID = 1L;
	private String usuario = null;

	/**
	 * Create the panel.
	 */
	public PanelPerfil(VentanaPrincipal vp, GestionInformacion gestionInfo) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblPlaylists = new JLabel("PLAYLISTS");
		lblPlaylists.setBounds(320, 100, 260, 51);
		lblPlaylists.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaylists.setForeground(Color.GREEN);
		lblPlaylists.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		add(lblPlaylists);
		
		JList<String> listaPlaylist = new JList<String>();
		listaPlaylist.setBounds(330, 150, 350, 355);
		add(listaPlaylist);
		
		usuario = gestionInfo.devolverClienteSeleccionado();
		gestionInfo.cargarPlayLists(usuario);
		playlists = gestionInfo.devolverPlayLists();
		for (int i = 0; i < playlists.size(); i++) {
			System.out.println(playlists.get(i).getTitulo());
		}
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < playlists.size(); i++){
		    listModel.addElement(playlists.get(i).getTitulo());
		}
		listaPlaylist.setModel(listModel);
		
		JLabel lblUsuario = new JLabel("-" + usuario + "-");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.GREEN);
		lblUsuario.setFont(new Font("Lucida Bright", Font.BOLD, 40));
		lblUsuario.setBounds(50, 382, 250, 50);
		add(lblUsuario);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblTipo.setForeground(Color.LIGHT_GRAY);
		lblTipo.setBounds(90, 432, 80, 30);
		add(lblTipo);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(70, 390, 5, 72);
		add(panel);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(Color.LIGHT_GRAY);
		panelFoto.setBounds(70, 167, 200, 200);
		add(panelFoto);
		panelFoto.setLayout(null);
		
		String letra = usuario.substring(0,1);
		
		JLabel lblLetra = new JLabel(letra.toUpperCase());
		lblLetra.setForeground(Color.DARK_GRAY);
		lblLetra.setFont(new Font("Lucida Bright", Font.BOLD, 99));
		lblLetra.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetra.setBounds(0, 0, 200, 200);
		panelFoto.add(lblLetra);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(vp.nPanel);
			}
		});
		btnAtras.setBounds(75, 85, 89, 23);
		add(btnAtras);
		
		JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionInfo.guardarPanelAnteriorAlbumCanciones(false);
				gestionInfo.guardarPlaylistSeleccionada(listaPlaylist.getSelectedValue().toString());
				vp.cambiarDePanel(7);
			}
		});
		btnNewButton.setBounds(550, 118, 130, 23);
		add(btnNewButton);

	}
}
