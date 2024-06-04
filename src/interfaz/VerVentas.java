package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fabrica.Fabrica;
import modelo.Galeria;
import modelo.Subasta;
import piezas.Pieza;
import usuarios.Usuario;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;

public class VerVentas extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String BOTON_SESION = "B1";
	private String BOTON_PIEZAS = "B4";
	private String BOTON_SUBASTAS = "B5";
	private String BOTON_USUARIOS = "B6";
	private String BOTON_HISTORIALES = "B7";
	private String BOTON_VENTAS = "B8";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerVentas frame = new VerVentas();
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
	public VerVentas() {
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
		
		JButton cerrarSesion = new JButton("Cerrar sesión");
		cerrarSesion.setBackground(new Color(253, 255, 252));
		cerrarSesion.setBounds(64, 527, 125, 23);
		panel_1.add(cerrarSesion);
		
		cerrarSesion.setActionCommand(BOTON_SESION);
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
		manejoPiezas.setText("  Manejo de piezas");
		manejoPiezas.setBackground(new Color(30, 163, 177));
		manejoPiezas.setBounds(41, 66, 211, 32);
		manejoPiezas.setBorder(null);
		panel_1.add(manejoPiezas);
		manejoPiezas.setActionCommand(BOTON_PIEZAS);
		manejoPiezas.addActionListener(this);
		
		JButton manejoSubastas = new JButton(new ImageIcon("./Img/boton.png"));
		manejoSubastas.setText("  Manejo de subastas");
		manejoSubastas.setHorizontalAlignment(SwingConstants.LEFT);
		manejoSubastas.setForeground(new Color(253, 255, 252));
		manejoSubastas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		manejoSubastas.setBorder(null);
		manejoSubastas.setBackground(new Color(30, 163, 177));
		manejoSubastas.setBounds(41, 109, 211, 32);
		panel_1.add(manejoSubastas);
		manejoSubastas.setActionCommand(BOTON_SUBASTAS);
		manejoSubastas.addActionListener(this);
		
		JButton btnManejoDeUsuarios = new JButton( new ImageIcon("./Img/boton.png"));
		btnManejoDeUsuarios.setText("  Manejo de usuarios");
		btnManejoDeUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		btnManejoDeUsuarios.setForeground(new Color(253, 255, 252));
		btnManejoDeUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnManejoDeUsuarios.setBorder(null);
		btnManejoDeUsuarios.setBackground(new Color(30, 163, 177));
		btnManejoDeUsuarios.setBounds(41, 152, 211, 32);
		panel_1.add(btnManejoDeUsuarios);
		btnManejoDeUsuarios.setActionCommand(BOTON_USUARIOS);
		btnManejoDeUsuarios.addActionListener(this);
		
		JButton btnVerHistoriales = new JButton(new ImageIcon("./Img/boton.png"));
		btnVerHistoriales.setText("  Ver historiales");
		btnVerHistoriales.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerHistoriales.setForeground(new Color(253, 255, 252));
		btnVerHistoriales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerHistoriales.setBorder(null);
		btnVerHistoriales.setBackground(new Color(30, 163, 177));
		btnVerHistoriales.setBounds(41, 195, 211, 32);
		panel_1.add(btnVerHistoriales);
		btnVerHistoriales.setActionCommand(BOTON_HISTORIALES);
		btnVerHistoriales.addActionListener(this);
		
		JButton btnVerVentas = new JButton(new ImageIcon("./Img/boton.png"));
		btnVerVentas.setText("  Ver ventas");
		btnVerVentas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerVentas.setForeground(new Color(253, 255, 252));
		btnVerVentas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerVentas.setBorder(null);
		btnVerVentas.setBackground(new Color(30, 163, 177));
		btnVerVentas.setBounds(41, 238, 211, 32);
		panel_1.add(btnVerVentas);
		btnVerHistoriales.setActionCommand(BOTON_VENTAS);
		btnVerHistoriales.addActionListener(this);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(253, 255, 252));
		panel_2.setBounds(251, 0, 733, 43);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblAdministradorPrincipal = new JLabel("Administrador - Inicio");
		lblAdministradorPrincipal.setForeground(new Color(30, 163, 177));
		lblAdministradorPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdministradorPrincipal.setBounds(10, 11, 221, 23);
		panel_2.add(lblAdministradorPrincipal);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(251, 42, 733, 519);
		panel.add(panel_3);
		
	//añadir a panel_3
	
		
	
	}
	
	public void regresarInicio() {
		InterfazPrincipal ventanaLogin = new InterfazPrincipal();
		
		ventanaLogin.setVisible(true);
		this.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
        
        if (BOTON_SESION.equals(comando)) {
        	
        	regresarInicio();
        }
        
        else if (BOTON_PIEZAS.equals(comando)) {
        	PanelPiezasAdministrador ventanaPiezas = new PanelPiezasAdministrador();
    		
        	ventanaPiezas.setVisible(true);
    		this.dispose();
        }
        
        else if (BOTON_SUBASTAS.equals(comando)) {
        	PanelSubastasAdministrador ventanaSubastas = new PanelSubastasAdministrador();
        	
        	ventanaSubastas.setVisible(true);
        	this.dispose();
        }
        else if (BOTON_USUARIOS.equals(comando)) {
        	PanelUsuariosAdministrador ventanaUsuarios = new PanelUsuariosAdministrador();
        	
        	ventanaUsuarios.setVisible(true);
        	this.dispose();
        }
		
	}
}
