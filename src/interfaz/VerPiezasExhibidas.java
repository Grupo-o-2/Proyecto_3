package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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

public class VerPiezasExhibidas extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String BOTON_REGRESAR = "B1";
	Fabrica fabricaInicio= new Fabrica();		
	Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
			new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerPiezasExhibidas frame = new VerPiezasExhibidas();
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
	public VerPiezasExhibidas() {
		
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
		
		JButton btnRegistrar = new JButton(new ImageIcon("./Img/boton.png"));
		btnRegistrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistrar.setSelectedIcon(new ImageIcon("./Img/boton.png"));
		btnRegistrar.setForeground(new Color(253, 255, 252));
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrar.setText("  Registrar nueva pieza");
		btnRegistrar.setBackground(new Color(30, 163, 177));
		btnRegistrar.setBounds(22, 66, 211, 32);
		btnRegistrar.setBorder(null);
		btnRegistrar.setActionCommand("Registrar-pieza");
		btnRegistrar.addActionListener(this);
		panel_1.add(btnRegistrar);
		
		JButton btnConsignacion = new JButton(new ImageIcon("./Img/boton.png"));
		btnConsignacion.setText("  Revisión y devolución");
		btnConsignacion.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsignacion.setForeground(new Color(253, 255, 252));
		btnConsignacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsignacion.setBorder(null);
		btnConsignacion.setBackground(new Color(30, 163, 177));
		btnConsignacion.setBounds(22, 109, 211, 32);
		btnConsignacion.setActionCommand("Ir-consignacion");
		btnConsignacion.addActionListener(this);
		panel_1.add(btnConsignacion);
		
		JButton btnBodega = new JButton( new ImageIcon("./Img/boton.png"));
		btnBodega.setText("  Ver piezas en bodega");
		btnBodega.setHorizontalAlignment(SwingConstants.LEFT);
		btnBodega.setForeground(new Color(253, 255, 252));
		btnBodega.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBodega.setBorder(null);
		btnBodega.setBackground(new Color(30, 163, 177));
		btnBodega.setBounds(22, 152, 211, 32);
		btnBodega.setActionCommand("Ir-bodega");
		btnBodega.addActionListener(this);
		panel_1.add(btnBodega);
		
		JButton btnVerExhibidas = new JButton(new ImageIcon("./Img/boton.png"));
		btnVerExhibidas.setText("  Ver piezas en exhibición");
		btnVerExhibidas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerExhibidas.setForeground(new Color(253, 255, 252));
		btnVerExhibidas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerExhibidas.setBorder(null);
		btnVerExhibidas.setBackground(new Color(30, 163, 177));
		btnVerExhibidas.setBounds(22, 195, 211, 32);
		panel_1.add(btnVerExhibidas);
		
		JButton btnVerTodas = new JButton(new ImageIcon("./Img/boton.png"));
		btnVerTodas.setText("  Ver todas las piezas");
		btnVerTodas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerTodas.setForeground(new Color(253, 255, 252));
		btnVerTodas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerTodas.setBorder(null);
		btnVerTodas.setBackground(new Color(30, 163, 177));
		btnVerTodas.setBounds(22, 238, 211, 32);
		btnVerTodas.setActionCommand("Ir-todas");
		btnVerTodas.addActionListener(this);
		panel_1.add(btnVerTodas);
		
		
		
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
		
		JLabel lblPiezasExhibidas = new JLabel("Piezas exhibidas");
		lblPiezasExhibidas.setBounds(63, 48, 175, 25);
		lblPiezasExhibidas.setForeground(new Color(30, 163, 177));
		lblPiezasExhibidas.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(lblPiezasExhibidas);
		
		
		
		
		ArrayList<Pieza> exhibidas = new ArrayList<Pieza>();
		for (Pieza pieza: galeriaInicio.getPiezasActuales()) {
			if(pieza.isExhibida() == true) {
				exhibidas.add(pieza);
			}
		}
		
		
		if(exhibidas.isEmpty() == true) {
			JLabel lblNoPiezas = new JLabel("No hay piezas exhibidas en este momento.");
			lblNoPiezas.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNoPiezas.setBounds(63, 97, 357, 25);
			panel_3.add(lblNoPiezas);
			
			
		} 
		
		else {

			JPanel panel_4 = new JPanel();
			panel_4.setBounds(0, 103, 733, 416);
			panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));	
			for (Pieza pieza: exhibidas) {
				Panelpieza nueva = new Panelpieza(pieza);
				panel_4.add(nueva);
				panel_4.setVisible(true);
			}
			panel_3.add(panel_4);
		}
	}
	
	public void regresar() {
		PanelPiezasAdministrador ventanaInicial = new PanelPiezasAdministrador();
		
		ventanaInicial.setVisible(true);
		this.dispose();
	}
	
	public void irRegistrarPiezas() {
		RegistroPiezasAdmin ventanaRegistro = new RegistroPiezasAdmin();
		
		ventanaRegistro.setVisible(true);
		this.dispose();
	}
	
	
	public void irTodas() {
		VerTodasPiezas ventanaTodas = new VerTodasPiezas(galeriaInicio);
		
		ventanaTodas.setVisible(true);
		this.dispose();
	}
	
	public void irConsignacion() {
		ConsignacionPiezasAdmin ventanaConsignacion = new ConsignacionPiezasAdmin();
		
		ventanaConsignacion.setVisible(true);
		this.dispose();
	}
	
	public void irBodega() {
		VerPiezasBodega ventanaBodega = new VerPiezasBodega();
		
		ventanaBodega.setVisible(true);
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
        
        if (BOTON_REGRESAR.equals(comando)) {
        	
        	regresar();
        }
        
        else if("Registrar-pieza".equals(comando)) {
        	irRegistrarPiezas();
        }
        
        else if("Ir-todas".equals(comando)) {
        	irTodas();
        }
        
        else if("Ir-bodega".equals(comando)) {
        	irBodega();
        }
        
        else if("Ir-consignacion".equals(comando)) {
        	irConsignacion();
        }
		
	}
}
