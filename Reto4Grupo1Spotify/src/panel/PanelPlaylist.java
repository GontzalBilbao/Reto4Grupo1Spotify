package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.GestionBD;
import controlador.GestionFicheros;
import controlador.GestionInformacion;
import modelo.Album;
import modelo.PlayList;
import vista.VentanaPrincipal;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Cliente;

public class PanelPlaylist extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNuevaPlayList;
	private String usuario = null;
	private String nuevaPlayList = null;
	private Cliente cliente;
	private String idCliente = null;
	
	private ArrayList<PlayList> playlists = new ArrayList<PlayList>();

	/**
	 * Create the panel.
	 */
	public PanelPlaylist(VentanaPrincipal vp, GestionInformacion gestionInfo, GestionFicheros gestionFicheros) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		
		JLabel lblLogin = new JLabel("MIS PLAYLISTS");
		lblLogin.setForeground(Color.GREEN);
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblLogin.setBounds(42, 110, 260, 51);
		add(lblLogin);
		
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(3);
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.setBounds(42, 43, 90, 35);
		add(btnAtras);
		
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.nPanel = 12;
				vp.cambiarDePanel(11);
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPerfil.setBounds(675, 43, 90, 35);
		add(btnPerfil);
		
		JList list = new JList();
		list.setBounds(42, 163, 363, 354);
		add(list);
		
//		JList<String> lista = new JList<String>(playlists.toArray(new String[playlists.size()]));
//		list.add(lista);
//		
		usuario = gestionInfo.devolverClienteSeleccionado();
		gestionInfo.cargarPlayLists(usuario);
		playlists = gestionInfo.devolverPlayLists();
		for (int i = 0; i < playlists.size(); i++) {
			System.out.println(playlists.get(i).getTitulo());
		}
		
		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < playlists.size(); i++){
		    listModel.addElement(playlists.get(i).getTitulo());
		}
		list.setModel(listModel);
		
		textFieldNuevaPlayList = new JTextField();
		textFieldNuevaPlayList.setBounds(429, 213, 336, 35);
		add(textFieldNuevaPlayList);
		textFieldNuevaPlayList.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nueva PlayList");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNewLabel.setBounds(429, 182, 336, 35);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("INSERTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if("".equals(textFieldNuevaPlayList.getText())) {
					
					JOptionPane.showMessageDialog(null, "Introduzca una nueva PlayList", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					nuevaPlayList = textFieldNuevaPlayList.getText();
					
					idCliente = "123";
					
					gestionInfo.añadirPlayList(nuevaPlayList, idCliente);
					JOptionPane.showMessageDialog(null, "PlayList añadida con éxito", "PlayList Guardada",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(429, 258, 163, 45);
		add(btnNewButton);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestionInfo.eliminarPlayList(list.getSelectedValue().toString());
				JOptionPane.showMessageDialog(null, "PlayList eliminada con éxito", "PlayList Eliminada",
						JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBounds(602, 258, 163, 45);
		add(btnEliminar);
		
		JButton btnNewButton_1 = new JButton("EXPORTAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestionFicheros.imprimirInformacion(playlists);
				JOptionPane.showMessageDialog(null, "PlayList exportada con éxito", "PlayList Exportada",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(429, 313, 336, 81);
		add(btnNewButton_1);
	}
}
