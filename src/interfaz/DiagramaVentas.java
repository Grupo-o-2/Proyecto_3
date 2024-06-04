package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DiagramaVentas extends JPanel{

	private int[][] diagrama;
	
	public DiagramaVentas(int[][] diagrama) {
		this.diagrama = diagrama;
	}
	
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		int particionX = 0;
		int particionY = 0;
		
		int tamañoX = getWidth();
		int tamañoY = getHeight();
		
		int dX = tamañoX/12 -1;
		int dY = tamañoY/32 -1 ;
		
		for (int i = 0; i<12; i++)
		{
			particionY = 0;
			for (int j = 0; j<32; j++)
			{
				g2d.setColor(Color.BLACK);
				if (diagrama[i][j] == 0)
				{
					g2d.setColor(new Color(253, 255, 252));
				}else if (diagrama[i][j] == 1)
				{
					g2d.setColor(new Color(30, 163, 177));
				}else if (diagrama[i][j] == 2)
				{
					g2d.setColor(new Color(8, 76, 97));
				}
				g2d.fillRect(particionX, particionY, dX, dY);
				particionY+=dY;
			}
			particionX+=dX;
		}
		
		particionX = 0;
		particionY = 0;
		
		g2d.setColor(Color.BLACK);
		
		for (int i = 0; i<12; i++)
		{
			particionY = 0;
			for (int j = 0; j<32; j++)
			{
				g2d.drawLine(0, particionY, tamañoX, particionY);
				g2d.drawLine(particionX, 0, particionX, tamañoY);
				particionY+=dY;
			}
			g2d.drawLine(0, particionY, tamañoX, particionY);
			particionX+=dX;
		}
		g2d.drawLine(particionX, 0, particionX, tamañoY);
		repaint();
		
	}
	
}
