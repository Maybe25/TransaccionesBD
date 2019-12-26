package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.MySqlConceptoDAO;
import entidad.Concepto;
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

public class frmBuscarConcepto extends JFrame implements ActionListener, KeyListener{
	//modelo para la tabla
	DefaultTableModel mConcepto=new DefaultTableModel();
	
	//
	MySqlConceptoDAO dao=new MySqlConceptoDAO();
	
	
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTable tblConcepto;
	private JButton btnEnviar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBuscarConcepto frame = new frmBuscarConcepto();
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
	public frmBuscarConcepto() {
		setTitle("Consulta Concepto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApellidos = new JLabel("Nombre:");
		lblApellidos.setBounds(21, 11, 73, 14);
		contentPane.add(lblApellidos);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(82, 8, 311, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 56, 475, 377);
		contentPane.add(scrollPane);
		
		tblConcepto = new JTable();
		tblConcepto.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblConcepto);
		
		btnEnviar = new JButton("");
		btnEnviar.addActionListener(this);
		btnEnviar.setIcon(new ImageIcon(frmBuscarConcepto.class.getResource("/img/Remove from basket.gif")));
		btnEnviar.setBounds(407, 7, 89, 38);
		contentPane.add(btnEnviar);
		//crear columnas al modelo mLector
		mConcepto.addColumn("Código");
		mConcepto.addColumn("Nombre");
		mConcepto.addColumn("Precio");
		//enviar el objeto "mLector" a la tabla tblLector
		tblConcepto.setModel(mConcepto);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEnviar) {
			actionPerformedBtnEnviar(arg0);
		}
	
	}
	
	
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			keyReleasedTxtApellidos(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTxtApellidos(KeyEvent arg0) {
		listado(txtNombre.getText());
	}
	protected void actionPerformedBtnEnviar(ActionEvent arg0) {
		//Paso 1: obtener la fila seleccionada de
		//la tabla tblConcepto
		int fila;
		fila=tblConcepto.getSelectedRow();
		//Paso 2: variables
		String cod,nom,pre;
		//Paso 3: obtener los valores según la fila y
		//columna seleccionada de la tabla
		cod=tblConcepto.getValueAt(fila, 0).toString();
		nom=tblConcepto.getValueAt(fila, 1).toString();
		pre=tblConcepto.getValueAt(fila, 2).toString();
		//Paso 4: enviar el valor de las variables a las
		//cajas que se encuentran en la GUI frmBoleta
		frmBoleta.txtCodigoConcepto.setText(cod);
		frmBoleta.txtDescripcion.setText(nom);
		frmBoleta.txtPrecio.setText(pre);
		//Paso 4: cerrar ventana actual
		dispose();
	}
	//método listado que recibe como parámetro el
		//nombre del concepto
		void listado(String nom){
			//1
			mConcepto.setRowCount(0);
			//2
			ArrayList<Concepto> lista=dao.
							listarConceptoXNombre(nom);
			//3
			for(Concepto con:lista){
				//4
				Object fila[]={con.getCodigo(),
								con.getNombre(),
								con.getPrecio()};
				//5
				mConcepto.addRow(fila);
			}
		}
}







