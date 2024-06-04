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
import usuarios.Administrador;
import usuarios.Usuario;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class FinalizarSubastasAdmin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String BOTON_REGRESAR = "B1";
	Fabrica fabricaInicio= new Fabrica();		
	Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
			new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
	private JComboBox<String> comboBoxSubasta;
	private JPanel panel;
	private JPanel panel_3;
	private JLabel lblInfo;
	private JButton btnFinSubasta;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalizarSubastasAdmin frame = new FinalizarSubastasAdmin();
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
	public FinalizarSubastasAdmin() {
		
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
		
		panel = new JPanel();
		panel.setBounds(0, 0, 984, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(30, 163, 177));
		panel_1.setBounds(0, 0, 252, 561);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setForeground(new Color(30, 163, 177));
		btnRegresar.setBackground(new Color(253, 255, 252));
		btnRegresar.setBounds(64, 527, 125, 23);
		panel_1.add(btnRegresar);
		
		btnRegresar.setActionCommand(BOTON_REGRESAR);
		btnRegresar.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Mosaicos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(253, 255, 252));
		lblNewLabel.setBounds(74, 11, 191, 23);
		panel_1.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 41, 252, 23);
		panel_1.add(separator);
		
		JButton btnCrear = new JButton(new ImageIcon("./Img/boton.png"));
		btnCrear.setHorizontalAlignment(SwingConstants.LEFT);
		btnCrear.setSelectedIcon(new ImageIcon("./Img/boton.png"));
		btnCrear.setForeground(new Color(253, 255, 252));
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCrear.setText("Crear nueva subasta");
		btnCrear.setBackground(new Color(30, 163, 177));
		btnCrear.setBounds(22, 66, 211, 32);
		btnCrear.setBorder(null);
		btnCrear.setActionCommand("Crear-subasta");
		btnCrear.addActionListener(this);
		panel_1.add(btnCrear);
		
		JButton btnMostrar = new JButton(new ImageIcon("./Img/boton.png"));
		btnMostrar.setText("Mostrar subastas");
		btnMostrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnMostrar.setForeground(new Color(253, 255, 252));
		btnMostrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMostrar.setBorder(null);
		btnMostrar.setBackground(new Color(30, 163, 177));
		btnMostrar.setBounds(22, 109, 211, 32);
		btnMostrar.setActionCommand("Mostrar-subastas");
		btnMostrar.addActionListener(this);
		panel_1.add(btnMostrar);
		
		JButton btnFin = new JButton( new ImageIcon("./Img/boton.png"));
		btnFin.setText("Finalizar subasta");
		btnFin.setHorizontalAlignment(SwingConstants.LEFT);
		btnFin.setForeground(new Color(253, 255, 252));
		btnFin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFin.setBorder(null);
		btnFin.setBackground(new Color(30, 163, 177));
		btnFin.setBounds(22, 152, 211, 32);
		panel_1.add(btnFin);
		
		
		//Info
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(253, 255, 252));
		panel_2.setBounds(251, 0, 733, 43);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblAdministradorPrincipal = new JLabel("Administrador - Manejo de subastas");
		lblAdministradorPrincipal.setForeground(new Color(30, 163, 177));
		lblAdministradorPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdministradorPrincipal.setBounds(10, 11, 294, 23);
		panel_2.add(lblAdministradorPrincipal);
		
		
		panel_3 = new JPanel();
		panel_3.setBounds(251, 42, 733, 519);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblFinSubastas = new JLabel("Finalizar una subasta");
		lblFinSubastas.setBounds(37, 28, 284, 25);
		lblFinSubastas.setForeground(new Color(30, 163, 177));
		lblFinSubastas.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(lblFinSubastas);
		
		
			
		reiniciar();
			
		
		
	
	}
	
	public void reiniciar() {
		
		if (galeriaInicio.getSubastas().isEmpty()) {
			JLabel lblNoSubastas = new JLabel("No hay subastas en la galería actualmente.");
			lblNoSubastas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNoSubastas.setBounds(40, 85, 392, 25);
			panel_3.add(lblNoSubastas);
			lblNoSubastas.setVisible(true);
		}
		
		else {
			
			
			
			lblInfo = new JLabel("Las subastas actuales son:");
			lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblInfo.setBounds(37, 84, 321, 25);
			panel_3.add(lblInfo);
			
			btnFinSubasta = new JButton("Finalizar subasta");
			btnFinSubasta.setForeground(new Color(253, 255, 252));
			btnFinSubasta.setBackground(new Color(30, 163, 177));
			btnFinSubasta.setActionCommand("Fin");
			btnFinSubasta.addActionListener(this);
			btnFinSubasta.setBounds(37, 146, 186, 23);
			panel_3.add(btnFinSubasta);
			
			comboBoxSubasta = new JComboBox<String>();
			comboBoxSubasta.setBounds(282, 88, 195, 21);
			
			for (Subasta subasta :galeriaInicio.getSubastas()) {
				String nombre = subasta.getNombre();
				comboBoxSubasta.addItem(nombre);
			}
			
			panel_3.add(comboBoxSubasta);
			lblInfo.setVisible(true);
			btnFinSubasta.setVisible(true);
			comboBoxSubasta.setVisible(true);
		}
		
		
	}
	
	
	
	public void regresar() {
		PanelInicialAdministrador ventanaInicial = new PanelInicialAdministrador();
		
		ventanaInicial.setVisible(true);
		this.dispose();
	}
	
	
	public void irCrearSubastas() {
		CrearSubastaAdmin ventanaCrear = new CrearSubastaAdmin();
		
		ventanaCrear.setVisible(true);
		this.dispose();
	}
	
	public void irMostrarSubastas() {
		MostrarSubastas ventanaMostrar = new MostrarSubastas();
		
		ventanaMostrar.setVisible(true);
		this.dispose();
	}
	
	public void finalizarSubasta() {
		
		String seleccionada = (String)comboBoxSubasta.getSelectedItem();
		Subasta subasta = galeriaInicio.obtenerSubastaPorNombre(seleccionada);
		subasta.finalizarSubasta(galeriaInicio, seleccionada);
		galeriaInicio.salvarGaleria("Galeria.json");
	
		DialogsAdvertencias subastaDialog = new DialogsAdvertencias();
		subastaDialog.cambiarAdvertencia("Subasta finalizada", "La subasta" +subasta.getNombre()+" ha sido finalizada.");
		subastaDialog.setVisible(true);
		lblInfo.setVisible(false);
		btnFinSubasta.setVisible(false);
		comboBoxSubasta.setVisible(false);
		reiniciar();
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
        
        if (BOTON_REGRESAR.equals(comando)) {
        	regresar();
        }
        
        else if(("Fin").equals(comando)){
        	finalizarSubasta();
        }
        
        
        else if("Crear-subasta".equals(comando)) {
        	irCrearSubastas();
        }
        
        
        else if("Mostrar-subastas".equals(comando)) {
        	irMostrarSubastas();
        }
        
       
      
		
	}
}
