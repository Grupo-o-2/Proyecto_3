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

public class PanelInicialComprador extends JFrame implements ActionListener {

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
					PanelInicialComprador frame = new PanelInicialComprador();
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
	public PanelInicialComprador() {
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
		panel_1.setBackground(new Color(255, 200, 87));
		panel_1.setBounds(0, 0, 252, 561);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton cerrarSesion = new JButton("Cerrar sesión");
		cerrarSesion.setForeground(new Color(255, 200, 87));
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
		separator.setForeground(new Color(253, 255, 252));
		separator.setBounds(0, 41, 252, 23);
		panel_1.add(separator);
		
		JButton btnCompraPieza = new JButton(new ImageIcon("./Img/boton.png"));
		btnCompraPieza.setHorizontalAlignment(SwingConstants.LEFT);
		btnCompraPieza.setSelectedIcon(new ImageIcon("./Img/boton.png"));
		btnCompraPieza.setForeground(new Color(253, 255, 252));
		btnCompraPieza.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCompraPieza.setText(" Comprar pieza");
		btnCompraPieza.setBackground(new Color(255, 200, 87));
		btnCompraPieza.setBounds(22, 66, 211, 32);
		btnCompraPieza.setBorder(null);
		panel_1.add(btnCompraPieza);
		btnCompraPieza.setActionCommand("Comprar-pieza");
		btnCompraPieza.addActionListener(this);
		
		JButton btnConsignarPieza = new JButton(new ImageIcon("./Img/boton.png"));
		btnConsignarPieza.setText(" Consignar pieza");
		btnConsignarPieza.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsignarPieza.setForeground(new Color(253, 255, 252));
		btnConsignarPieza.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsignarPieza.setBorder(null);
		btnConsignarPieza.setBackground(new Color(255, 200, 87));
		btnConsignarPieza.setBounds(22, 109, 211, 32);
		panel_1.add(btnConsignarPieza);
		btnConsignarPieza.setActionCommand("Consignar-pieza");
		btnConsignarPieza.addActionListener(this);
		
		JButton btnConsultas = new JButton( new ImageIcon("./Img/boton.png"));
		btnConsultas.setText(" Consultas");
		btnConsultas.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultas.setForeground(new Color(253, 255, 252));
		btnConsultas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultas.setBorder(null);
		btnConsultas.setBackground(new Color(255, 200, 87));
		btnConsultas.setBounds(22, 152, 211, 32);
		panel_1.add(btnConsultas);
		btnConsultas.setActionCommand(BOTON_USUARIOS);
		btnConsultas.addActionListener(this);
		
		JButton btnParticiparSubasta = new JButton(new ImageIcon("./Img/boton.png"));
		btnParticiparSubasta.setText(" Participar en subasta");
		btnParticiparSubasta.setHorizontalAlignment(SwingConstants.LEFT);
		btnParticiparSubasta.setForeground(new Color(253, 255, 252));
		btnParticiparSubasta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnParticiparSubasta.setBorder(null);
		btnParticiparSubasta.setBackground(new Color(255, 200, 87));
		btnParticiparSubasta.setBounds(22, 195, 211, 32);
		panel_1.add(btnParticiparSubasta);
		btnParticiparSubasta.setActionCommand(BOTON_HISTORIALES);
		btnParticiparSubasta.addActionListener(this);
		
		JButton btnSolAumento = new JButton(new ImageIcon("./Img/boton.png"));
		btnSolAumento.setText(" Solicitar aumento");
		btnSolAumento.setHorizontalAlignment(SwingConstants.LEFT);
		btnSolAumento.setForeground(new Color(253, 255, 252));
		btnSolAumento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSolAumento.setBorder(null);
		btnSolAumento.setBackground(new Color(255, 200, 87));
		btnSolAumento.setBounds(22, 238, 211, 32);
		panel_1.add(btnSolAumento);
		btnSolAumento.setActionCommand(BOTON_VENTAS);
		
		JButton btnVerHistoriales = new JButton(new ImageIcon("./Img/boton.png"));
		btnVerHistoriales.setText("  Ver historiales");
		btnVerHistoriales.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerHistoriales.setForeground(new Color(253, 255, 252));
		btnVerHistoriales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerHistoriales.setBorder(null);
		btnVerHistoriales.setBackground(new Color(255, 200, 87));
		btnVerHistoriales.setActionCommand("B8");
		btnVerHistoriales.setBounds(22, 280, 211, 32);
		panel_1.add(btnVerHistoriales);
		btnSolAumento.addActionListener(this);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(253, 255, 252));
		panel_2.setBounds(251, 0, 733, 43);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblAdministradorPrincipal = new JLabel("Comprador - Inicio");
		lblAdministradorPrincipal.setForeground(new Color(255, 200, 87));
		lblAdministradorPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdministradorPrincipal.setBounds(10, 11, 221, 23);
		panel_2.add(lblAdministradorPrincipal);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(251, 42, 733, 519);
		panel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));	
		
		for (Pieza pieza: galeriaInicio.getPiezasActuales()) {
			Panelpieza nueva = new Panelpieza(pieza);
			nueva.setBorder(new LineBorder(new Color(255, 200, 87)));
			panel_3.add(nueva);
		}
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
        else if (BOTON_VENTAS.equals(comando)) {
        	DialogFechaVenta fechaVenta = new DialogFechaVenta(); 
        	fechaVenta.setVisible(true);
        	this.dispose();
        }
		
	}
}
