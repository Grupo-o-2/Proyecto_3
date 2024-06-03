package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class pruebas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebas frame = new pruebas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pruebas() {
		
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(500, 300);
       setResizable(false);

        JPanel mainPanel = new JPanel();

        // Panel 1 (no scrollable)
        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 50, 50);
        panel1.setLayout(new GridLayout(10, 1));
        for (int i = 1; i <= 10; i++) {
            panel1.add(new JLabel("Label " + i));
        }

        // Panel 2 (scrollable)
        JPanel panel2 = new JPanel();
        panel2.setBounds(60,60, 300, 300);
        panel2.setLayout(new GridLayout(20, 1));
        for (int i = 1; i <= 20; i++) {
            panel2.add(new JLabel("Scrollable Label " + i));
        }

        // Envolvemos panel2 en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel2);
        scrollPane.setBounds(100, 70, 300, 190);
       // scrollPane.setPreferredSize(new Dimension(200, 200));
        mainPanel.setLayout(null);

        // Añadimos los paneles al panel principal
        mainPanel.add(panel1);
        mainPanel.add(scrollPane);

        getContentPane().add(mainPanel);
        setVisible(true);
   
		
	}
}
