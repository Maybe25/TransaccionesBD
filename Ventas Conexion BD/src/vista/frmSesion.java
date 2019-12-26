package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.MySqlEmpleadoDAO;
import entidad.Empleado;
import util.Libreria;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;


public class frmSesion extends JFrame {
	
	MySqlEmpleadoDAO daoEmpleado=new MySqlEmpleadoDAO();
	
	private JPanel contentPane;
	private JButton btnIniciar;
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSesion frame = new frmSesion();
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
	public frmSesion() {
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnIniciarActionPerformed(arg0);
			}
		});
		btnIniciar.setBounds(82, 275, 200, 50);
		contentPane.add(btnIniciar);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		lblUsuario.setBounds(128, 81, 116, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setFont(new Font("Arial", Font.BOLD, 16));
		lblClave.setBounds(145, 169, 86, 14);
		contentPane.add(lblClave);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(82, 123, 189, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(82, 216, 189, 20);
		contentPane.add(txtClave);
	}
	protected void btnIniciarActionPerformed(ActionEvent arg0) {
		
		String login,clave;
		login=txtUsuario.getText();
		clave=txtClave.getText();
		
		Empleado emp=daoEmpleado.
				iniciarSesion(login, clave);
		
		if(emp==null)
			mensaje("Usuario y/o clave incorrectos");
		else{
			
			frmPrincipal frm=new frmPrincipal();
			
			frm.setExtendedState(MAXIMIZED_BOTH);
			
			frmPrincipal.lblDatos.
				setText("Bienvenido(a): "+
							emp.getPaterno()+" "+
							emp.getMaterno()+","+
							emp.getNombre());
			
			Libreria.codigoEmpleado=emp.getCodigo();
		
			frm.setVisible(true);
			
			dispose();
		}
			
	}
	
	void mensaje(String m){
		JOptionPane.showMessageDialog(null, m);
	}
}
  
  

