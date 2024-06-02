package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fabrica.Fabrica;
import modelo.Galeria;
import modelo.Subasta;
import piezas.Pieza;
import usuarios.Usuario;

public class PanelPiezasAdministrador extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String BOTON_REGRESAR = "B1";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPiezasAdministrador frame = new PanelPiezasAdministrador();
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
	public PanelPiezasAdministrador() {
		Fabrica fabricaInicio= new Fabrica();		
		Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		galeriaInicio.cargarGaleria("Galeria.json");
		galeriaInicio.salvarGaleria("GaleriaAnterior.json");
		
		setResizable(false);
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(30, 163, 177));
		panel_1.setBounds(0, 0, 252, 561);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton cerrarSesion = new JButton("Regresar");
		cerrarSesion.setBackground(new Color(253, 255, 252));
		cerrarSesion.setBounds(64, 527, 125, 23);
		panel_1.add(cerrarSesion);
		
		cerrarSesion.setActionCommand(BOTON_REGRESAR);
		cerrarSesion.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Mosaicos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(253, 255, 252));
		lblNewLabel.setBounds(74, 11, 191, 23);
		panel_1.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 41, 252, 23);
		panel_1.add(separator);
		
		JButton manejoPiezas = new JButton(new ImageIcon("./Img/boton.png"));
		manejoPiezas.setHorizontalAlignment(SwingConstants.LEFT);
		manejoPiezas.setSelectedIcon(new ImageIcon("./Img/boton.png"));
		manejoPiezas.setForeground(new Color(253, 255, 252));
		manejoPiezas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		manejoPiezas.setText("  Registrar nueva pieza");
		manejoPiezas.setBackground(new Color(30, 163, 177));
		manejoPiezas.setBounds(41, 66, 211, 32);
		manejoPiezas.setBorder(null);
		panel_1.add(manejoPiezas);
		
		JButton manejoSubastas = new JButton(new ImageIcon("./Img/boton.png"));
		manejoSubastas.setText("  Revisión y devolución");
		manejoSubastas.setHorizontalAlignment(SwingConstants.LEFT);
		manejoSubastas.setForeground(new Color(253, 255, 252));
		manejoSubastas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		manejoSubastas.setBorder(null);
		manejoSubastas.setBackground(new Color(30, 163, 177));
		manejoSubastas.setBounds(41, 109, 211, 32);
		panel_1.add(manejoSubastas);
		
		JButton btnManejoDeUsuarios = new JButton( new ImageIcon("./Img/boton.png"));
		btnManejoDeUsuarios.setText("  Ver piezas en bodega");
		btnManejoDeUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		btnManejoDeUsuarios.setForeground(new Color(253, 255, 252));
		btnManejoDeUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnManejoDeUsuarios.setBorder(null);
		btnManejoDeUsuarios.setBackground(new Color(30, 163, 177));
		btnManejoDeUsuarios.setBounds(41, 152, 211, 32);
		panel_1.add(btnManejoDeUsuarios);
		
		JButton btnVerHistoriales = new JButton(new ImageIcon("./Img/boton.png"));
		btnVerHistoriales.setText("  Ver piezas en exhibición");
		btnVerHistoriales.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerHistoriales.setForeground(new Color(253, 255, 252));
		btnVerHistoriales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerHistoriales.setBorder(null);
		btnVerHistoriales.setBackground(new Color(30, 163, 177));
		btnVerHistoriales.setBounds(41, 195, 211, 32);
		panel_1.add(btnVerHistoriales);
		
		JButton btnVerVentas = new JButton(new ImageIcon("./Img/boton.png"));
		btnVerVentas.setText("  Ver todas las piezas");
		btnVerVentas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerVentas.setForeground(new Color(253, 255, 252));
		btnVerVentas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerVentas.setBorder(null);
		btnVerVentas.setBackground(new Color(30, 163, 177));
		btnVerVentas.setBounds(41, 238, 211, 32);
		panel_1.add(btnVerVentas);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(253, 255, 252));
		panel_2.setBounds(251, 0, 733, 43);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblAdministradorPrincipal = new JLabel("Administrador - Manejo de piezas");
		lblAdministradorPrincipal.setForeground(new Color(30, 163, 177));
		lblAdministradorPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdministradorPrincipal.setBounds(10, 11, 294, 23);
		panel_2.add(lblAdministradorPrincipal);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(251, 42, 733, 519);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblManejoDePiezas = new JLabel("Manejo de piezas");
		lblManejoDePiezas.setBounds(63, 48, 175, 25);
		lblManejoDePiezas.setForeground(new Color(30, 163, 177));
		lblManejoDePiezas.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(lblManejoDePiezas);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenido al manejo de piezas. Aquí puedes realizar las siguientes funciones:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(63, 104, 633, 25);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("• Registrar en el sistema una nueva pieza.");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(63, 142, 633, 25);
		panel_3.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("• Revisar y si es necesario devolver una pieza.");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(63, 163, 633, 25);
		panel_3.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("• Ver las piezas que se encuentran en bodega.");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(63, 184, 633, 25);
		panel_3.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("• Ver las piezas que se encuentran en exhibición.");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2_1.setBounds(63, 205, 633, 25);
		panel_3.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("• Ver todas las piezas actuales de la galería.");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2_1_1.setBounds(63, 226, 633, 25);
		panel_3.add(lblNewLabel_1_1_2_1_1);
	}
	
	public void regresar() {
		PanelInicialAdministrador ventanaInicial = new PanelInicialAdministrador();
		
		ventanaInicial.setVisible(true);
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
        
        if (BOTON_REGRESAR.equals(comando)) {
        	
        	regresar();
        }
		
	}
}
