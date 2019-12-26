package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.MySqlLectorDAO;
import entidad.Lector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class frmBuscarLector extends JFrame implements ActionListener, KeyListener{
	
	DefaultTableModel mLector=new DefaultTableModel();
	
	MySqlLectorDAO dao=new MySqlLectorDAO();
	
	private JPanel contentPane;
	private JTextField txtApellidos;
	private JTable tblLector;
	private JButton btnEnviar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBuscarLector frame = new frmBuscarLector();
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
	public frmBuscarLector() {
		setTitle("Consulta Lector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(21, 11, 73, 14);
		contentPane.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(this);
		txtApellidos.setBounds(82, 8, 311, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 56, 475, 377);
		contentPane.add(scrollPane);
		
		tblLector = new JTable();
		tblLector.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblLector);
		
		btnEnviar = new JButton("");
		btnEnviar.addActionListener(this);
		btnEnviar.setIcon(new ImageIcon(frmBuscarLector.class.getResource("/img/Remove from basket.gif")));
		btnEnviar.setBounds(407, 7, 89, 38);
		contentPane.add(btnEnviar);
		//crear columnas al modelo mLector
		mLector.addColumn("Código");
		mLector.addColumn("Nombres");
		mLector.addColumn("Apellidos");
		//enviar el objeto "mLector" a la tabla tblLector
		tblLector.setModel(mLector);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEnviar) {
			actionPerformedBtnEnviar(arg0);
		}
	
	}
	
	
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtApellidos) {
			keyReleasedTxtApellidos(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTxtApellidos(KeyEvent arg0) {
		listado(txtApellidos.getText());
	}
	protected void actionPerformedBtnEnviar(ActionEvent arg0) {
		
		
		int fila;
		fila=tblLector.getSelectedRow();
		
		String cod,nom,ape;
		
		cod=tblLector.getValueAt(fila, 0).toString();
		nom=tblLector.getValueAt(fila, 1).toString();
		ape=tblLector.getValueAt(fila, 2).toString();
		
		frmBoleta.txtCodigoLector.setText(cod);
		frmBoleta.txtNombre.setText(nom);
		frmBoleta.txtApellido.setText(ape);
		
		dispose();
	}

	void listado(String ape){
		//1
		mLector.setRowCount(0);
		//2
		ArrayList<Lector> lista=dao.
						listarLectorXApellidos(ape);
		//3
		for(Lector lec:lista){
			//4
			Object fila[]={lec.getCodigo(),
							lec.getNombre(),
							lec.getApellidos()};
			//5
			mLector.addRow(fila);
		}
	}
	
	
}







