package vista;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;
import javax.swing.table.DefaultTableModel;

import controlador.MySqlBoletaDAO;
import entidad.Boleta;
import entidad.Detalle;
import util.Libreria;

import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.ImageIcon;


public class frmBoleta extends JInternalFrame implements KeyListener, ActionListener {
	//crear 3 modelos para la tabla detalle
	DefaultTableModel mDetalle=new DefaultTableModel();
	//crear un objeto global de la clase MySqlBoletaDAO
	MySqlBoletaDAO daoBoleta=new MySqlBoletaDAO();
	
	private JButton btnBuscarAlumno;
	private JLabel lbl_1;
	private JLabel lbl_2;
	private JLabel lbl_3;
	public static JTextField txtCodigoLector;
	public static JTextField txtNombre;
	public static JTextField txtApellido;
	private JLabel lbl_4;
	private JLabel lbl_5;
	public static JTextField txtCodigoConcepto;
	public static JTextField txtDescripcion;
	private JButton btnBuscarConcepto;
	private JLabel lbl_6;
	public static JTextField txtPrecio;
	private JTextField txtCantidad;
	private JButton btnAgregar;
	private JButton btnGrabar;
	private JTextField txtNumero;
	private JLabel lbl_8;
	private JPanel panel;
	private JLabel lbl_9;
	private JLabel lbl_10;
	private JLabel lbl_11;
	private JTextField txtTotal;
	private JPanel panel_1;
	private JPanel panel2;
	private JLabel lbl_13;
	private JTextField txtFecha;
	private JScrollPane scp_1;
	private JTable tblDetalle;
	private JButton btnEliminar;
	private JButton btnNuevo;
	private JButton btnSalir;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBoleta frame = new frmBoleta();
					frame.setVisible(true);
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmBoleta() {
		setTitle("Boleta de Venta");
		setBounds(100, 100, 789, 628);
		getContentPane().setLayout(null);
		
		btnGrabar = new JButton("Grabar");
		btnGrabar.setIcon(new ImageIcon(frmBoleta.class.getResource("/img/Save as.gif")));
		btnGrabar.addActionListener(this);
		btnGrabar.setBounds(150, 512, 104, 64);
		getContentPane().add(btnGrabar);
		
		lbl_8 = new JLabel("BOLETA DE VENTA");
		lbl_8.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_8.setBounds(290, 11, 178, 31);
		getContentPane().add(lbl_8);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(595, 3, 168, 66);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(53, 35, 105, 20);
		panel.add(txtNumero);
		txtNumero.setColumns(10);
		
		lbl_9 = new JLabel("Nro:");
		lbl_9.setBounds(10, 38, 46, 14);
		panel.add(lbl_9);
		
		lbl_10 = new JLabel("RUC: 10407708551");
		lbl_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_10.setBounds(20, 11, 148, 14);
		panel.add(lbl_10);
		
		lbl_11 = new JLabel("Total:");
		lbl_11.setBounds(608, 519, 46, 14);
		getContentPane().add(lbl_11);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(677, 512, 86, 20);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Empleado"
				+ "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 89, 355, 148);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnBuscarAlumno = new JButton("");
		btnBuscarAlumno.setIcon(new ImageIcon(frmBoleta.class.getResource("/img/busca.png")));
		btnBuscarAlumno.setBounds(276, 11, 69, 41);
		panel_1.add(btnBuscarAlumno);
		btnBuscarAlumno.addActionListener(this);
		
		lbl_1 = new JLabel("Codigo:");
		lbl_1.setBounds(10, 35, 46, 14);
		panel_1.add(lbl_1);
		
		txtCodigoLector = new JTextField();
		txtCodigoLector.setEditable(false);
		txtCodigoLector.setBounds(66, 32, 97, 20);
		panel_1.add(txtCodigoLector);
		txtCodigoLector.setColumns(10);
		
		lbl_2 = new JLabel("Nombre:");
		lbl_2.setBounds(10, 71, 69, 14);
		panel_1.add(lbl_2);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(66, 63, 279, 20);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		lbl_3 = new JLabel("Apellidos:");
		lbl_3.setBounds(10, 97, 69, 14);
		panel_1.add(lbl_3);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setBounds(66, 96, 279, 20);
		panel_1.add(txtApellido);
		txtApellido.setColumns(10);
		
		lbl_13 = new JLabel("Fecha:");
		lbl_13.setBounds(608, 499, 46, 14);
		getContentPane().add(lbl_13);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(677, 493, 86, 20);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(frmBoleta.class.getResource("/img/nuevo2.png")));
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(25, 512, 115, 64);
		getContentPane().add(btnNuevo);
		
		
		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(frmBoleta.class.getResource("/img/Closed folder.gif")));
		btnSalir.addActionListener(this);
		btnSalir.setBounds(264, 514, 101, 61);
		getContentPane().add(btnSalir);
		
		
				
				scp_1 = new JScrollPane();
				scp_1.setBounds(10, 248, 753, 214);
				getContentPane().add(scp_1);
				
				tblDetalle = new JTable();
				scp_1.setViewportView(tblDetalle);
				
				panel2 = new JPanel();
				panel2.setBorder(new TitledBorder(null, "Datos Expediente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel2.setBounds(410, 89, 353, 148);
				getContentPane().add(panel2);
				panel2.setLayout(null);
				
				lbl_4 = new JLabel("Codigo:");
				lbl_4.setBounds(10, 39, 46, 14);
				panel2.add(lbl_4);
				
				txtCodigoConcepto = new JTextField();
				txtCodigoConcepto.setEditable(false);
				txtCodigoConcepto.setBounds(83, 36, 86, 20);
				panel2.add(txtCodigoConcepto);
				txtCodigoConcepto.setColumns(10);
				
				lbl_5 = new JLabel("Descripci\u00F3n:");
				lbl_5.setBounds(10, 67, 83, 14);
				panel2.add(lbl_5);
				
				txtDescripcion = new JTextField();
				txtDescripcion.setEditable(false);
				txtDescripcion.setBounds(83, 60, 260, 20);
				panel2.add(txtDescripcion);
				txtDescripcion.setColumns(10);
				
				btnBuscarConcepto = new JButton("");
				btnBuscarConcepto.setIcon(new ImageIcon(frmBoleta.class.getResource("/img/busca.png")));
				btnBuscarConcepto.addActionListener(this);
				btnBuscarConcepto.setBounds(281, 11, 62, 41);
				panel2.add(btnBuscarConcepto);
				
				lbl_6 = new JLabel("Precio:");
				lbl_6.setBounds(10, 92, 46, 14);
				panel2.add(lbl_6);
				
				txtCantidad = new JTextField();
				txtCantidad.setBounds(83, 117, 86, 20);
				panel2.add(txtCantidad);
				txtCantidad.setColumns(10);
				
				btnAgregar = new JButton("");
				btnAgregar.setIcon(new ImageIcon(frmBoleta.class.getResource("/img/abrir2.png")));
				btnAgregar.setBounds(196, 91, 62, 36);
				panel2.add(btnAgregar);
				
				txtPrecio = new JTextField();
				txtPrecio.setEditable(false);
				txtPrecio.setBounds(83, 89, 86, 20);
				panel2.add(txtPrecio);
				txtPrecio.setColumns(10);
				
				btnEliminar = new JButton("");
				btnEliminar.setIcon(new ImageIcon(frmBoleta.class.getResource("/img/cerrar.png")));
				btnEliminar.addActionListener(this);
				btnEliminar.setBounds(281, 92, 62, 36);
				panel2.add(btnEliminar);
				
				JLabel lblCantidad = new JLabel("Cantidad :");
				lblCantidad.setBounds(10, 116, 63, 14);
				panel2.add(lblCantidad);
				btnAgregar.addActionListener(this);
				//columnas para el modelo detalle
				mDetalle.addColumn("codigo");
				mDetalle.addColumn("descripcion");
				mDetalle.addColumn("precio");
				mDetalle.addColumn("cantidad");
				mDetalle.addColumn("importe");
				//enviar el modelo a la tabla
				tblDetalle.setModel(mDetalle);
				//mostrar fecha actual
				txtFecha.setText(
					new SimpleDateFormat("yyyy-MM-dd").
					format(new Date()));
				
				
				
	}
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}


	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSalir) {
			btnSalirActionPerformed(arg0);
		}
		if (arg0.getSource() == btnNuevo) {
			btnNuevoActionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			btnEliminarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnBuscarConcepto) {
			btnBuscarConceptoActionPerformed(arg0);
		}
		if (arg0.getSource() == btnGrabar) {
			btnGrabarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			btnAgregarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnBuscarAlumno) {
			btnBuscarAlumnoActionPerformed(arg0);
		}
	}
	protected void btnBuscarAlumnoActionPerformed(ActionEvent arg0) {
		frmBuscarLector frm=new frmBuscarLector();	
		frm.setVisible(true);
		
	}
	protected void btnAgregarActionPerformed(ActionEvent arg0) {
		//Paso 1: obtener los valores de la GUI
		String cod,nom;
		int can;
		double pre;
		cod=txtCodigoConcepto.getText();
		nom=txtDescripcion.getText();
		can=Integer.parseInt(txtCantidad.getText());
		pre=Double.parseDouble(txtPrecio.getText());
		//Paso 2: arreglo de Object con los valores
		//de las variables
		Object fila[]={cod,nom,pre,can,(pre*can)};
		//Paso 3: enviar el arreglo "fila" al modelo
		//mDetalle
		mDetalle.addRow(fila);
		//Paso 4: invocar al método totalPagar
		double tp=totalPagar();
		txtTotal.setText(""+tp);
		
	}
	protected void btnGrabarActionPerformed(ActionEvent arg0) {
		//BOLETA
		//Paso 1: leer los controles de GUI para 
		//almacenar su valor en un objeto de la clase
		//Boleta
		String numBoleta,codLector,fecha,total;
		numBoleta=txtNumero.getText();
		codLector=txtCodigoLector.getText();
		fecha=txtFecha.getText();
		total=txtTotal.getText();
		//Paso 2: crear un objeto de la clase Boleta
		//y setear las variables con los atributos
		Boleta boleta=new Boleta();
		boleta.setNumBoleta(Integer.parseInt(numBoleta));
		boleta.setCodigoLector(codLector);
		boleta.setCodigoEmpleado(Libreria.codigoEmpleado);
		boleta.setFecha(fecha);
		boleta.setTotal(Double.parseDouble(total));
		//DETALLE
		//Paso 1: crear un arreglo de objetos de la 
		//clase Detalle
		ArrayList<Detalle> data=new ArrayList<Detalle>();
		//Paso 2: bucle para realizar recorrido sobre 
		//el JTable "tblDetalle"
		for(int i=0;i<tblDetalle.getRowCount();i++){
			//Paso 3: crear un objeto de la clase Detalle
			Detalle det=new Detalle();
			//Paso 4: obtener los valores de la fila 
			//actual
			String codConcepto,pre,can;
			codConcepto=""+tblDetalle.getValueAt(i,0);
			pre=""+tblDetalle.getValueAt(i,2);
			can=""+tblDetalle.getValueAt(i,3);
			//Paso 5:setear el objeto "det" con los
			//valores de las variables
			det.setCodigoConcepto(
					Integer.parseInt(codConcepto));
			det.setPrecio(Double.parseDouble(pre));
			det.setCantidad(Integer.parseInt(can));
			//Paso 6: enviar el objeto "det" al arreglo
			//data
			data.add(det);
		}
		//invocar al método registrarBoleta
		int resultado;
		resultado=daoBoleta.registrarBoleta(boleta,data);
		//validar
		if(resultado!=-1){
			mensaje("Registro correcto");
			txtNumero.setText("");
			txtCodigoLector.setText("");
			txtNombre.setText("");
			txtApellido.setText("");
			txtCodigoConcepto.setText("");
			txtDescripcion.setText("");
			txtPrecio.setText("");
			txtCantidad.setText("0");
			txtTotal.setText("0");
			mDetalle.setRowCount(0);
		}
		else{
			mensaje("Error en el registro de la Boleta");
		}
		
	}
	protected void btnBuscarConceptoActionPerformed(ActionEvent arg0) {
		frmBuscarConcepto frm=new frmBuscarConcepto();
		frm.setVisible(true);
	}
	protected void btnEliminarActionPerformed(ActionEvent arg0) {
		//Paso 1: Obtener la fila seleccionada
		int fila;
		fila=tblDetalle.getSelectedRow();
		//Paso 2: eliminar la fila seleccionada
		//del modelo mDetalle
		mDetalle.removeRow(fila);
		//Paso 3: actualizar el total pagar
		double tp=totalPagar();
		txtTotal.setText(""+tp);
	}
	
	protected void btnNuevoActionPerformed(ActionEvent arg0) {

	}
	protected void btnSalirActionPerformed(ActionEvent arg0) {
		dispose();
	}
	void mensaje(String string){
		JOptionPane.showMessageDialog(null, string);
	}
	double totalPagar(){
		double total=0;
		for(int i=0;i<tblDetalle.getRowCount();i++){
			total+=Double.parseDouble(
				tblDetalle.getValueAt(i,4).toString());
		}
		return total;
	}
	
}
