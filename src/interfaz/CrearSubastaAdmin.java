package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import fabrica.Fabrica;
import modelo.Galeria;
import modelo.Subasta;
import piezas.Pieza;
import usuarios.Usuario;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.GridLayout;

public class CrearSubastaAdmin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String BOTON_REGRESAR = "B1";
	Fabrica fabricaInicio= new Fabrica();		
	Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
			new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
	private JTextField textField;
	private JList listParticipantes;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearSubastaAdmin frame = new CrearSubastaAdmin();
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
	public CrearSubastaAdmin() {
		
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
		btnFin.setActionCommand("Fin-subasta");
		btnFin.addActionListener(this);
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(251, 42, 733, 519);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblCrearSubastas = new JLabel("Crear nueva subasta");
		lblCrearSubastas.setBounds(37, 28, 284, 25);
		lblCrearSubastas.setForeground(new Color(30, 163, 177));
		lblCrearSubastas.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(lblCrearSubastas);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(37, 84, 115, 17);
		panel_3.add(lblNombre);
		
		JLabel lblParticipantes = new JLabel("Participantes:");
		lblParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblParticipantes.setBounds(37, 122, 115, 17);
		panel_3.add(lblParticipantes);
		
		textField = new JTextField();
		textField.setToolTipText("Nombre de la subasta");
		textField.setBounds(136, 85, 205, 19);
		textField.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
		panel_3.add(textField);
		textField.setColumns(10);

		
		DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Usuario participante1 : galeriaInicio.obtenerCompradores()) {
        	if ( !(participante1.getNombre().equals("fake") )){
        		listModel.addElement(participante1.getNombre());
        	}
        }

        listParticipantes = new JList<>(listModel);
        listParticipantes.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
        listParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 10));
        listParticipantes.setToolTipText("Eliga los participantes de la subasta ( Si desea elegir varios use: Ctrl + click )");

      
        listParticipantes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Envuelve el JList en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(listParticipantes);
        scrollPane.setBounds(137, 125, 204, 69); // Ajusta el tamaño y la posición del JScrollPane

        // Añade el JScrollPane al panel
        panel_3.add(scrollPane);
        
		
		JLabel lblPiezas = new JLabel("Piezas:");
		lblPiezas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPiezas.setBounds(37, 226, 115, 17);
		panel_3.add(lblPiezas);
		
		

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(30, 163, 177), 2));
		
		for(Pieza pieza1 : galeriaInicio.getPiezasActuales()) {
		 JRadioButton btnPieza = new JRadioButton(pieza1.getTitulo()); 
			 
			 panel_5.add(btnPieza);
			 
		 } 
		
		JScrollPane scrollPane2 = new JScrollPane(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane2.setBounds(142, 233, 199, 146);
		panel_3.add(scrollPane2);
		 
		 
		
		JButton btnCrearSubasta = new JButton("Crear subasta");
		btnCrearSubasta.setForeground(new Color(253, 255, 252));
		btnCrearSubasta.setBackground(new Color(30, 163, 177));
			
		btnCrearSubasta.setActionCommand("Crear");
		btnCrearSubasta.addActionListener(this);
		btnCrearSubasta.setBounds(328, 486, 125, 23);
		panel_3.add(btnCrearSubasta);
		
		
		
	}
	
	
	
	public void regresar() {
		PanelInicialAdministrador ventanaInicial = new PanelInicialAdministrador();
		
		ventanaInicial.setVisible(true);
		this.dispose();
	}
	
	

	
	public void irMostrarSubastas() {
		MostrarSubastas ventanaMostrar = new MostrarSubastas();
		
		ventanaMostrar.setVisible(true);
		this.dispose();
	}
	
	public void irFinSubastas() {
		FinalizarSubastasAdmin ventanaFin = new FinalizarSubastasAdmin();
		
		ventanaFin.setVisible(true);
		this.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
        
        if (BOTON_REGRESAR.equals(comando)) {
        	regresar();
        }
        
        
        else if("Mostrar-subastas".equals(comando)) {
        	irMostrarSubastas();
        }
        
        else if("Fin-subasta".equals(comando)) {
        	irFinSubastas();
        }
      
		
	}
}
