package interfaz;

import javax.swing.JPanel;

public class PanelContenedorLogin extends JPanel{
	
	private PanelLateralLogin lateral;
	private PanelGeneralLogin general;
	
	public PanelContenedorLogin() {
		
	
		lateral = new PanelLateralLogin();
		general = new PanelGeneralLogin();
		
		lateral.setBounds(0,0, 500,1000);
		general.setBounds(500,0, 1200, 1000);
		
		add(lateral);
		add(general);
		
		
	}

	
}
