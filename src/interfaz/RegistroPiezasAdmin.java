package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import usuarios.Artista;
import usuarios.Comprador;
import usuarios.Usuario;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

public class RegistroPiezasAdmin extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	

	private JPanel contentPane;
	private String BOTON_REGRESAR = "B8";
	private String BOTON_REGISTRAR = "B9";
	private String BOTON_BODEGA = "B10";
	private JComboBox<String> comboBox;
	Fabrica fabricaInicio= new Fabrica();		
	Galeria galeriaInicio = fabricaInicio.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
			new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
	private JTextField txtTitulo;
	private JTextField txtFechaDeCreacin;
	private JTextField txtLugarDeCreacin;
	private JTextField txtValor;
	private JList listAutores;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroPiezasAdmin frame = new RegistroPiezasAdmin();
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
	public RegistroPiezasAdmin() {
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
		manejoPiezas.setBounds(22, 66, 211, 32);
		manejoPiezas.setBorder(null);
		panel_1.add(manejoPiezas);
		
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
		btnVerExhibidas.setActionCommand("Ir-exhibidas");
		btnVerExhibidas.addActionListener(this);
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
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(251, 43, 733, 518);
		panel_5.setLayout(null);
		panel.add(panel_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 733, 103);
		panel_3.setLayout(null);
		
		
		
		JLabel lblManejoDePiezas = new JLabel("Registro de piezas");
		lblManejoDePiezas.setBounds(39, 25, 185, 25);
		lblManejoDePiezas.setForeground(new Color(30, 163, 177));
		lblManejoDePiezas.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(lblManejoDePiezas);
		
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("Escoge el tipo de pieza");
		comboBox.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
		comboBox.setBounds(77, 69, 126, 26);
		comboBox.addItem("Vídeo");
		comboBox.addItem("Escultura");
		comboBox.addItem("Fotografía");
		comboBox.addItem("Impresión");
		comboBox.addItem("Pintura");
		comboBox.addItemListener(this);
		panel_3.add(comboBox);
		panel_5.add(panel_3);
		
		//Panel cambiante
		JPanel panel_4 = new JPanel();
		
		panel_4.setBounds(0, 103, 733, 415);
		
		panel_5.add(panel_4);
		panel_4.setLayout(null);
		
		txtTitulo = new JTextField();
		txtTitulo.setToolTipText("Titulo de la pieza");
		txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtTitulo.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
		txtTitulo.setBounds(79, 22, 400, 28);
		txtTitulo.setText("Titulo de la pieza");
		panel_4.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		
		txtFechaDeCreacin = new JTextField();
		txtFechaDeCreacin.setToolTipText("Fecha de creación ( Formato -> añomesdía (Ej: 20280529) )");
		txtFechaDeCreacin.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
		txtFechaDeCreacin.setBounds(79, 64, 400, 28);
		txtFechaDeCreacin.setText("Fecha de creación ( Formato -> añomesdía (Ej: 20280529) )");
		txtFechaDeCreacin.setColumns(10);
		panel_4.add(txtFechaDeCreacin);
		
		txtLugarDeCreacin = new JTextField();
		txtLugarDeCreacin.setToolTipText("Lugar de creación");
		txtLugarDeCreacin.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
		txtLugarDeCreacin.setBounds(79, 102, 400, 28);
		txtLugarDeCreacin.setText("Lugar de creación");
		txtLugarDeCreacin.setColumns(10);
		panel_4.add(txtLugarDeCreacin);
		
		txtValor = new JTextField();
		txtValor.setToolTipText("Valor de la pieza");
		txtValor.setBorder(new LineBorder(new Color(30, 163, 177), 2));
		txtValor.setBounds(79, 140, 400, 28);
		txtValor.setText("Valor de la pieza\r\n");
		txtValor.setColumns(10);
		panel_4.add(txtValor);
		
		
	
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Usuario artista : galeriaInicio.obtenerArtistas()) {
            listModel.addElement(artista.getNombre());
        }

        listAutores = new JList<>(listModel);
        listAutores.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
        listAutores.setFont(new Font("Tahoma", Font.PLAIN, 10));
        listAutores.setToolTipText("Selecciona los autores de la pieza (Si desea seleccionar varios use: Ctrl + click)");

        // Permitir la selección múltiple
        listAutores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Envuelve el JList en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(listAutores);
        scrollPane.setBounds(79, 178, 400, 100); // Ajusta el tamaño y la posición del JScrollPane

        // Añade el JScrollPane al panel
        panel_4.add(scrollPane);
        
		JButton btnRegistrarPieza = new JButton("Registrar");
		btnRegistrarPieza.setForeground(new Color(253, 255, 252));
		btnRegistrarPieza.setBackground(new Color(30, 163, 177));
		btnRegistrarPieza.setActionCommand(BOTON_REGISTRAR);
		btnRegistrarPieza.addActionListener(this);
		btnRegistrarPieza.setBounds(320, 368, 125, 23);
		panel_4.add(btnRegistrarPieza);
		
		
		
		
		
		
	}
	
	public boolean verificarFecha(String fecha) {
		return  Galeria.esFechaValida(fecha);
		
	}
	
	

	
	public void registrar() {
		if( (txtFechaDeCreacin.getText()).isEmpty() || (txtValor.getText()).isEmpty() || (txtLugarDeCreacin.getText()).isEmpty()   
				|| (txtTitulo.getText()).isEmpty() )    {
			
			DialogsAdvertencias fechaDialog = new DialogsAdvertencias();
			fechaDialog.cambiarAdvertencia("Campo vacío", "Uno o algunos de los campos se encuentran vacíos.");
			fechaDialog.setVisible(true);
			
		}
		
		else {
			
			if(Galeria.esNumero((txtValor.getText()) ) == false) {
				DialogsAdvertencias numerosDialog = new DialogsAdvertencias();
				numerosDialog.cambiarAdvertencia("Campo incorrecto", "Se espera que el valor de la pieza sea un número.");
				numerosDialog.setVisible(true);
			}
			
			else if(listAutores.getSelectedIndex() == -1) {
				DialogsAdvertencias listaDialog = new DialogsAdvertencias();
				listaDialog.cambiarAdvertencia("Autor no seleccionado", "Debe seleccionar el autor de la pieza para continuar.");
				listaDialog.setVisible(true);
			}
			
			else {
				
				if ((verificarFecha(txtFechaDeCreacin.getText())) == true) {
		
					if((String) comboBox.getSelectedItem() == "Vídeo") {
						
						DialogRegistrarVideo especificaciones = new DialogRegistrarVideo(this);
						especificaciones.setVisible(true);
					
					}
					
					else if((String) comboBox.getSelectedItem() == "Escultura") {
						DialogRegistrarEscultura especificaciones = new DialogRegistrarEscultura(this);
						especificaciones.setVisible(true);
					}
					
					else if((String) comboBox.getSelectedItem() == "Fotografía") {
						DialogRegistrarFotografia especificaciones = new DialogRegistrarFotografia(this);
						especificaciones.setVisible(true);
					}
					
					else if((String) comboBox.getSelectedItem() == "Pintura") {
						DialogRegistrarPintura especificaciones = new DialogRegistrarPintura(this);
						especificaciones.setVisible(true);
					}
					
					else if((String) comboBox.getSelectedItem() == "Impresión") {
						DialogRegistrarImpresion especificaciones = new DialogRegistrarImpresion(this);
						especificaciones.setVisible(true);
					}
				}
				
				else {
					DialogsAdvertencias fechaDialog = new DialogsAdvertencias();
					fechaDialog.cambiarAdvertencia("Fecha inválida", "El formato de fecha igresado es inválido.");
					fechaDialog.setVisible(true);
				}
			}
			
		}
		
	}
	
	public void regresar() {
		PanelPiezasAdministrador ventanaInicial = new PanelPiezasAdministrador();
		
		ventanaInicial.setVisible(true);
		this.dispose();
		galeriaInicio.salvarGaleria("Galeria.json");
	}
	
	public void irBodega() {
		VerPiezasBodega ventanaBodega = new VerPiezasBodega();
		
		ventanaBodega.setVisible(true);
		this.dispose();
		galeriaInicio.salvarGaleria("Galeria.json");
		
	}
	
	public void irExhibidas() {
		VerPiezasExhibidas ventanaExhibidas = new VerPiezasExhibidas();
		
		ventanaExhibidas.setVisible(true);
		this.dispose();
	}
	
	public void irTodas() {
		VerTodasPiezas ventanaTodas = new VerTodasPiezas();
		
		ventanaTodas.setVisible(true);
		this.dispose();
	}
	
	public void irConsignacion() {
		ConsignacionPiezasAdmin ventanaConsignacion = new ConsignacionPiezasAdmin();
		
		ventanaConsignacion.setVisible(true);
		this.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
        
        if (BOTON_REGRESAR.equals(comando)) {
        	
        	regresar();
        }
        
        else if (BOTON_REGISTRAR.equals(comando)) {
        	registrar();
        }
        
        else if("Ir-bodega".equals(comando)) {
        	irBodega();
        }
        
        else if("Ir-todas".equals(comando)) {
        	irTodas();
        }
        
        else if("Ir-exhibidas".equals(comando)) {
        	irExhibidas();
        }
        
        else if("Ir-consignacion".equals(comando)) {
        	irConsignacion();
        }
        
        
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource()==comboBox) {
            String seleccionado=(String)comboBox.getSelectedItem();
           
        }
		
	}
	
	//Getters

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public Fabrica getFabricaInicio() {
		return fabricaInicio;
	}

	public Galeria getGaleriaInicio() {
		return galeriaInicio;
	}

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	public JTextField getTxtFechaDeCreacin() {
		return txtFechaDeCreacin;
	}

	public JTextField getTxtLugarDeCreacin() {
		return txtLugarDeCreacin;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JList getListAutores() {
		return listAutores;
	}
}
