package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JList;

public class RegistroPiezas extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String BOTON_REGRESAR = "B8";
	private JComboBox comboBox;
	Fabrica fabricaInicio= new Fabrica();		
	Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
			new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
	private JTextField txtTituloDeLa;
	private JTextField txtFechaDeCreacin;
	private JTextField txtLugarDeCreacin;
	private JTextField txtValorDeLa;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroPiezas frame = new RegistroPiezas();
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
	public RegistroPiezas() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(251, 41, 733, 520);
		panel.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblManejoDePiezas = new JLabel("Registro de piezas");
		lblManejoDePiezas.setBounds(63, 48, 400, 25);
		lblManejoDePiezas.setForeground(new Color(30, 163, 177));
		lblManejoDePiezas.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(lblManejoDePiezas);
		
		comboBox = new JComboBox();
		comboBox.setBounds(75, 104, 185, 22);
		comboBox.addItem("Vídeo");
		comboBox.addItem("Escultura");
		comboBox.addItem("Fotografía");
		comboBox.addItem("Impresión");
		comboBox.addItem("Pintura");
		comboBox.addItemListener(this);
		panel_3.add(comboBox);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 141, 731, 377);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		txtTituloDeLa = new JTextField();
		txtTituloDeLa.setText("Titulo de la pieza");
		txtTituloDeLa.setBounds(76, 11, 379, 25);
		panel_4.add(txtTituloDeLa);
		txtTituloDeLa.setColumns(10);
		
		txtFechaDeCreacin = new JTextField();
		txtFechaDeCreacin.setText("Fecha de creación ( Formato -> añomesdía (Ej: 20280529) )");
		txtFechaDeCreacin.setColumns(10);
		txtFechaDeCreacin.setBounds(76, 47, 379, 25);
		panel_4.add(txtFechaDeCreacin);
		
		txtLugarDeCreacin = new JTextField();
		txtLugarDeCreacin.setText("Lugar de creación");
		txtLugarDeCreacin.setColumns(10);
		txtLugarDeCreacin.setBounds(76, 83, 379, 25);
		panel_4.add(txtLugarDeCreacin);
		
		txtValorDeLa = new JTextField();
		txtValorDeLa.setText("Valor de la pieza\r\n");
		txtValorDeLa.setColumns(10);
		txtValorDeLa.setBounds(76, 119, 379, 25);
		panel_4.add(txtValorDeLa);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Alice");
        listModel.addElement("Bob");

        
                
        // Permitir la selección múltiple
        
		JList list = new JList(listModel);
		list.setToolTipText("Selecciona los autores de la pieza");
		list.setBounds(76, 155, 379, 59);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		panel_4.add(list);
		
	}
	
	public void regresar() {
		PanelPiezasAdministrador ventanaInicial = new PanelPiezasAdministrador();
		
		ventanaInicial.setVisible(true);
		this.dispose();
		galeriaInicio.salvarGaleria("GaleriaAnterior.json");
	}
	
	public void opcionesVideo() {
		galeriaInicio.crearVideo(BOTON_REGRESAR, ALLBITS, BOTON_REGRESAR, BOTON_REGRESAR, null, null, null, null, BOTON_REGRESAR, rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled, BOTON_REGRESAR, serialVersionUID, serialVersionUID, ABORT, BOTON_REGRESAR);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
        
        if (BOTON_REGRESAR.equals(comando)) {
        	
        	regresar();
        }
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource()==comboBox) {
            String seleccionado=(String)comboBox.getSelectedItem();
           
        }
		
	}
}
