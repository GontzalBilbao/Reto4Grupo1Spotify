package panel;

import java.awt.Color;

import javax.swing.JPanel;

import vista.VentanaPrincipal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import modelo.PlayList;

import java.awt.Font;
import java.awt.GridLayout;

public class PanelEdicionPlayList extends JPanel {
	
	private ArrayList<PlayList> playlists = new ArrayList<PlayList>();
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEdicionPlayList(VentanaPrincipal vp) {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Atrás");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarDePanel(12);
			}
		});
		btnNewButton.setBounds(112, 50, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Perfil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.nPanel = 12;
				vp.cambiarDePanel(11);
			}
		});
		btnNewButton_1.setBounds(617, 50, 89, 23);
		add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		for (int i = 0; i < playlists.size(); i++) {
			JPanel panelItem = new JPanel();
			panelItem.setBorder(null);
			panelItem.setLayout(new GridLayout(1, 0));
			
			JLabel label1 = new JLabel(playlists.get(i).getTitulo());
			label1.setSize(50, 50);
			panelItem.add(label1);
	
			panelItem.setName("panel " + i);
			panelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
		panel.add(panelItem);
	}
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(116, 150, 600, 350);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Nombre PlayList");
		lblNewLabel.setForeground(new Color(0, 255, 0));
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 20));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setBounds(116, 95, 600, 50);
		add(lblNewLabel);
	}
}
