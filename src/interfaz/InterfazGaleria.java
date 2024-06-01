package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfazGaleria extends JFrame {
	private PanelContenedorLogin panelLogin;
	public static int WIDTH_G = 1700;
	public static int HEIGHT_G = 1000;
	
	private PanelLateralLogin lateral;
	private PanelGeneralLogin general;
	
	public InterfazGaleria(){
		
		setTitle("Galeria");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(WIDTH_G,HEIGHT_G);
		setResizable(false);
		
		panelLogin = new PanelContenedorLogin();
		panelLogin.setSize(WIDTH_G, HEIGHT_G);
	
		
		add(panelLogin);
		
	}
	
	
	public static void main(String[] args) {
		InterfazGaleria main = new InterfazGaleria();
		
	}
}