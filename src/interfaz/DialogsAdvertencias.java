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

import javax.swing.SwingConstants;

public class DialogsAdvertencias extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblInfo;
	private JLabel lbPrincipal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			DialogsAdvertencias dialog = new DialogsAdvertencias();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogsAdvertencias() {
		setBounds(100, 100, 450, 235);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(253, 255, 252));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lbPrincipal = new JLabel("¡Cuidado!");
			lbPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
			lbPrincipal.setFont(new Font("Tahoma", Font.BOLD, 20));
			lbPrincipal.setForeground(new Color(30, 163, 177));
			lbPrincipal.setBounds(0, 31, 436, 35);
			contentPanel.add(lbPrincipal);
		}
		{
			lblInfo = new JLabel("Si no ingresas las especificaciones la pieza no se registrará.");
			lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblInfo.setBounds(0, 76, 436, 28);
			contentPanel.add(lblInfo);
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
				okButton.setActionCommand("OK2");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public void cambiarAdvertencia(String principal, String advertencia) {
		lbPrincipal.setText(principal);
		lblInfo.setText(advertencia);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if("OK2".equals(comando)) {
			setVisible(false);
		}
		}

}
