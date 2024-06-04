package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import modelo.Galeria;

public class DialogFechaVenta extends JDialog implements ActionListener{

	private VerVentas verVentas;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFecha;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			DialogFechaVenta dialog = new DialogFechaVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogFechaVenta() {

		
		verVentas = new VerVentas();
		verVentas.setVisible(true);
		
		setBounds(100, 100, 450, 411);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(253, 255, 252));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese la fecha");
			lblNewLabel.setForeground(new Color(30, 163, 177));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(20, 0, 231, 39);
			contentPanel.add(lblNewLabel);
		}
		{
			txtFecha = new JTextField();
			txtFecha.setToolTipText("Fecha");
			txtFecha.setText("Fecha");
			txtFecha.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
			txtFecha.setBackground(new Color(253, 255, 252));
			txtFecha.setBounds(41, 76, 268, 29);
			contentPanel.add(txtFecha);
			txtFecha.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(30, 163, 177));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(30, 163, 177));
				okButton.setBackground(new Color(253, 255, 252));
				okButton.setActionCommand("OK5");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(30, 163, 177));
				cancelButton.setBackground(new Color(253, 255, 252));
				cancelButton.setActionCommand("Cancel4");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void registroAceptado(){
		
		if (  (txtFecha.getText()).isEmpty()){
			
			DialogsAdvertencias fechaDialog = new DialogsAdvertencias();
			fechaDialog.cambiarAdvertencia("Campo vacío", "Ingrese una fecha.");
			fechaDialog.setVisible(true);
			
		}
		
		else if(    Galeria.esNumero((txtFecha.getText()) ) == false){
			DialogsAdvertencias numerosDialog = new DialogsAdvertencias();
			numerosDialog.cambiarAdvertencia("Campo incorrecto", "El valor ingresado no es válido.");
			numerosDialog.setVisible(true);
			
		}
		
		else {
			verVentas.dispose();
			VerVentas diagramaVentas = new VerVentas(txtFecha.getText());
			diagramaVentas.setVisible(true);
			dispose();
		}
			
		
		
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if("OK5".equals(comando)) {
			registroAceptado();
			
		}else if("Cancel4".equals(comando)) {
			dispose();
		}
		}
	}

