package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import piezas.Pieza;

public class Panelpieza extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Panelpieza(Pieza pieza) {
		
		setBackground(new Color(253, 255, 252));
		setBorder(new LineBorder(new Color(30, 163, 177)));
		setSize(126,131);
		setMaximumSize(new Dimension(126,131));
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{126, 0};
		gbl_panel_4.rowHeights = new int[]{71, 14, 14, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./Img/default.jpeg"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("  Nombre: "+ pieza.getTitulo()+ "  ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("  Precio: "+ Integer.toString(pieza.getValor()));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 2;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
	}

}
