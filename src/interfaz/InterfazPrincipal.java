package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fabrica.Fabrica;
import modelo.Galeria;
import modelo.Subasta;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import piezas.Pieza;
import usuarios.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;


public class InterfazPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String TIPO_ADMINISTRADOR = "Administrador";
	private static final String TIPO_CAJERO = "Cajero";
	private static final String TIPO_COMPRADOR = "Comprador";
	private static final String TIPO_OPERADOR = "Operador";
	private static final String TIPO_EMPLEADO = "Empleado";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try {
					
					
					InterfazPrincipal frame = new InterfazPrincipal();
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
	
	
	public static final String BOTON_1 = "B1";
	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField contraseña;
	public InterfazPrincipal() {
		setResizable(false);
		setTitle("Galeria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		;
		//panelLogin = new PanelLogin(this);
		//panelLogin.setBorder(null);
		//getContentPane().add(panelLogin);
		
		
		
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 255, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 163, 177));
		panel.setBounds(0, 0, 353, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(90, 163, 168, 52);
		lblNewLabel.setForeground(new Color(253, 255, 252));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Somos una galería online dedicada ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(253, 255, 252));
		lblNewLabel_1.setBounds(54, 226, 278, 45);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("a la exposición de piezas exclusivas.");
		lblNewLabel_1_1.setForeground(new Color(253, 255, 252));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(54, 249, 261, 45);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenido");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(253, 255, 252));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(90, 163, 168, 52);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("obras de arte maravillosas.");
		lblNewLabel_1_2.setForeground(new Color(253, 255, 252));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(85, 329, 278, 45);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Inicia sesión para descubrir");
		lblNewLabel_1_2_1.setForeground(new Color(253, 255, 252));
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBounds(75, 308, 278, 45);
		panel.add(lblNewLabel_1_2_1);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(new Color(253, 255, 252));
		panelInfo.setBounds(352, 0, 632, 561);
		contentPane.add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel txtIniciarSesion = new JLabel("Iniciar Sesión");
		txtIniciarSesion.setBounds(159, 67, 308, 47);
		txtIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		txtIniciarSesion.setForeground(new Color(50, 48, 49));
		txtIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelInfo.add(txtIniciarSesion);
		
		usuario = new JTextField();
		usuario.setForeground(new Color(253, 255, 252));
		usuario.setBackground(new Color(30, 163, 177));
		usuario.setToolTipText("Usuario");
		usuario.setBounds(108, 212, 421, 36);
		panelInfo.add(usuario);
		usuario.setColumns(10);
		
		contraseña = new JPasswordField();
		contraseña.setForeground(new Color(253, 255, 252));
		contraseña.setBackground(new Color(30, 163, 177));
		contraseña.setToolTipText("Password");
		contraseña.setBounds(108, 315, 421, 36);
		panelInfo.add(contraseña);
		
		JButton botonSesion = new JButton("Iniciar sesión");
		botonSesion.setBackground(new Color(30, 163, 177));
		botonSesion.setForeground(new Color(253, 255, 252));
		botonSesion.setBounds(260, 392, 124, 28);
		panelInfo.add(botonSesion);
		
		botonSesion.setActionCommand(BOTON_1);
		botonSesion.addActionListener(this);
		
		JLabel imgUsuario = new JLabel("Usuario");
		imgUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		imgUsuario.setIcon(new ImageIcon("./Img/avatar.png"));
		imgUsuario.setBounds(108, 173, 124, 28);
		panelInfo.add(imgUsuario);
		
		JLabel imgContraseña = new JLabel("Contraseña");
		imgContraseña.setIcon(new ImageIcon("./Img/cerrar.png"));
		imgContraseña.setFont(new Font("Tahoma", Font.PLAIN, 16));
		imgContraseña.setBounds(108, 280, 124, 28);
		panelInfo.add(imgContraseña);
		
		
	}
	
	
	
	
	public void iniciarSesion() {
		Fabrica fabricaInicio= new Fabrica();		
		Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		galeriaInicio.cargarGaleria("Galeria.json");
		galeriaInicio.salvarGaleria("GaleriaAnterior.json");
		
		String login = usuario.getText();
        String contraseña_1 = new String(contraseña.getPassword());
        
        String tipo = galeriaInicio.obtenerTipoPorLogin(login);
        
        int accion = galeriaInicio.verificacionSesion(login, contraseña_1, tipo);
        
		if (accion == 1){
			if(tipo == TIPO_ADMINISTRADOR) {
				
				PanelInicialAdministrador ventanaAdmin = new PanelInicialAdministrador();
				
				ventanaAdmin.setVisible(true);
				this.dispose();
			}
			
			else if(tipo == TIPO_COMPRADOR) {
				
				PanelInicialComprador ventanaComprador = new PanelInicialComprador();
				ventanaComprador.setVisible(true);
				this.dispose();
			}
			
			else if(tipo == TIPO_OPERADOR) {
				
				PanelInicialOperador ventanaOperador = new PanelInicialOperador();
				ventanaOperador.setVisible(true);
				this.dispose();
			}
			
			else if(tipo == TIPO_CAJERO) {
				
				PanelInicialCajero ventanaCajero = new PanelInicialCajero();
				ventanaCajero.setVisible(true);
				this.dispose();
			}
			
			else if(tipo == TIPO_EMPLEADO) {
				
				PanelInicialEmpleado ventanaEmpleado = new PanelInicialEmpleado();
				ventanaEmpleado.setVisible(true);
				this.dispose();
			}
			
		}
		
		else if  (accion == 0){
			//cuadro emergente - Login no existe
				JOptionPane.showMessageDialog(this, "El login ingresado no existe",
						"Login inexistente", JOptionPane.ERROR_MESSAGE);
			
		}
		
		else if (accion == 2) {
			JOptionPane.showMessageDialog(this, "El login ingresado no eeeexiste",
					"Tipo incorrecto", JOptionPane.ERROR_MESSAGE);
			// cuadro emergente - No pertenece al tipo
		}
		
		else if (accion == 3) {
			JOptionPane.showMessageDialog(this, "La contraseña es incorrecta",
					"Contraseña incorrecta", JOptionPane.ERROR_MESSAGE);
			//cuadro emergente - la contraseña no es correcta
		}
		
	}
	
     public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            
            if (BOTON_1.equals(comando)) {
            	iniciarSesion();
            	
            }

     }
		
		
		
		
		
	}
	
	
