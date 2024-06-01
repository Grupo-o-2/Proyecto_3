package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLateralLogin extends JPanel {

	public PanelLateralLogin() {
		setVisible(true);
		setBackground(new Color(8,76, 97));
		JPanel centrado = new JPanel();           
		centrado.setLayout(new GridLayout(1,4));
		
		
		JLabel bienvenido = new JLabel("Bienvenido");
		//JLabel textoGen = new JLabel ("Descubre obras de");
		//JLabel textoGen2 = new JLabel ("arte maravillosas.");
		//JButton conocenos = new JButton("¡Conocenos!");
		
		bienvenido.setFont(new Font("Courier New", Font.BOLD, 20));
		bienvenido.setForeground(Color.WHITE);
		
		
		centrado.add(bienvenido);
		//centrado.add(textoGen);
		//centrado.add(textoGen2);
		//centrado.add(conocenos);
		bienvenido.setBounds(120, 100, 100, 50);
		centrado.setBounds(120, 100,600 ,400 );
		add(centrado);
	}	
}