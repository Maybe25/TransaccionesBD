package vista;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import componentes.JComboBoxBD;
import controlador.MySqlLibroDAO;
import entidad.Libro;

import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class frmLibro extends JInternalFrame implements ActionListener, MouseListener, KeyListener {
	
	
	MySqlLibroDAO dao=new MySqlLibroDAO();
	
	DefaultTableModel mLibro=new  DefaultTableModel();
	
	int numFilas=-1;
	//objeto que manipule el archivo 
	//"SENTENCIAS_SQL"
	ResourceBundle rb=ResourceBundle.
			getBundle("SENTENCIAS_SQL");
	
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblMensaje;
	private JScrollPane scpScroll;
	private JTable tblLibro;		
	private JLabel lblCdigo;
	private JLabel lblTtulo;
	private JLabel lblPrecio;
	private JLabel lblCantidad;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnNuevo;
	private JLabel lblAutor;
	private JComboBox cboAutor;
	private JButton btnCerrar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//LINEA DE CODIGO PARA CARGAR EL ESTILO DEL JFRAME
					//dirección
					//https://malalanayake.wordpress.com/2012/10/16/java-themes-with-jtattoo/
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					//com.jtattoo.plaf.texture.TextureLookAndFeel
					//com.jtattoo.plaf.smart.SmartLookAndFeel
					//com.jtattoo.plaf.noire.NoireLookAndFeel
					frmLibro frame = new frmLibro();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public frmLibro() {
		setResizable(false);
		setTitle("LIBRO");
		setBounds(100, 100, 893, 479);
		getContentPane().setLayout(null);

		lblMensaje = new JLabel("Mantenimiento de Libro");
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBackground(Color.LIGHT_GRAY);
		lblMensaje.setOpaque(true);
		lblMensaje.setBounds(10, 11, 869, 33);
		getContentPane().add(lblMensaje);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 65, 588, 374);
		getContentPane().add(scpScroll);
		
		tblLibro = new JTable();
		tblLibro.addKeyListener(this);
		tblLibro.addMouseListener(this);
		tblLibro.setFillsViewportHeight(true);
		scpScroll.setViewportView(tblLibro);
		

		
		lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(608, 68, 46, 14);
		getContentPane().add(lblCdigo);
		
		lblTtulo = new JLabel("Descripcion:");
		lblTtulo.setBounds(608, 97, 78, 14);
		getContentPane().add(lblTtulo);
		
		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(608, 132, 46, 14);
		getContentPane().add(lblPrecio);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(608, 157, 61, 14);
		getContentPane().add(lblCantidad);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(687, 65, 46, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(687, 94, 180, 20);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(687, 122, 61, 20);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(687, 154, 71, 20);
		getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(frmLibro.class.getResource("/img/Save as.gif")));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(742, 252, 125, 49);
		getContentPane().add(btnRegistrar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(frmLibro.class.getResource("/img/abrir2.png")));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(608, 330, 133, 49);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(frmLibro.class.getResource("/img/cerrar.png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(742, 330, 125, 49);
		getContentPane().add(btnEliminar);
		
		//columnas al modelo mLibro
		mLibro.addColumn("Código");
		mLibro.addColumn("Descripción");
		mLibro.addColumn("Precio");
		mLibro.addColumn("Cantidad");
		mLibro.addColumn("Autor");
		//enviar el modelo "mLibro" al JTable "tblLibro"
		tblLibro.setModel(mLibro);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(frmLibro.class.getResource("/img/nuevo.png")));
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(608, 252, 133, 49);
		getContentPane().add(btnNuevo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(608, 196, 46, 14);
		getContentPane().add(lblAutor);
		
		cboAutor = new JComboBoxBD(rb.getString("SQL_AUTOR"));
		cboAutor.setBounds(687, 193, 180, 20);
		getContentPane().add(cboAutor);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setIcon(new ImageIcon(frmLibro.class.getResource("/img/Closed folder.gif")));
		btnCerrar.setBounds(682, 390, 133, 49);
		getContentPane().add(btnCerrar);
		//
		listado();
		//validar el valor de la variable numFilas
		if(numFilas!=-1)
			mostrarDatos(0);
		
		
	}
	
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
	}
	protected void actionPerformedBtnGrabar(ActionEvent arg0) {
		
		
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int respuesta;
		respuesta=JOptionPane.
			showConfirmDialog(null,
					"Seguro de elimar registro",
					"Sistema",JOptionPane.YES_NO_OPTION);
		if(respuesta==0){//siiiiiiiiiiiiiiiiiii
			int cod,salida;
			cod=Integer.parseInt(txtCodigo.getText());
			salida=dao.deleteLibro(cod);
			if(salida!=-1){
				mensaje("Registro eliminado correc.");
				listado();
			}
			else
				mensaje("Error en la eliminacion");
			
		}
	}
	protected void actionPerformedBtnCerrar(ActionEvent arg0) {
		dispose();	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
		// TODO Auto-generated method stub
		
	}
	void mensaje(String m){
		JOptionPane.showMessageDialog(null, m);
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		//PASO 1: variables para leer los valores de la GUI
		String des,pre,can;
		int pos;
		des=txtDescripcion.getText();
		pre=txtPrecio.getText();
		can=txtCantidad.getText();
		pos=cboAutor.getSelectedIndex();
		//PASO 2: validar
		if(des.equals("")){
			mensaje("Descripción es obligatorio");
			txtDescripcion.requestFocus();
		}
	
		else if(pos==0)
			mensaje("Seleccione Autor");
		else{
			
			Libro lib=new Libro();
			
			lib.setDescripcion(des);
			lib.setPrecio(Double.parseDouble(pre));
			lib.setCantidad(Integer.parseInt(can));
			lib.setCodigoAutor(pos);
			
			int salida;
			salida=dao.insertLibro(lib);
			
			if(salida!=-1){
				mensaje("Registro guardado correc.");
				listado();
			}
			else
				mensaje("Error en el registro");
			
			
		}	
	
	
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		//PASO 1: variables para leer los valores de la GUI
		String cod,des,pre,can;
		int pos;
		cod=txtCodigo.getText();
		des=txtDescripcion.getText();
		pre=txtPrecio.getText();
		can=txtCantidad.getText();
		pos=cboAutor.getSelectedIndex();
		//PASO 2: validar
		if(des.equals("")){
			mensaje("Descripción es obligatorio");
			txtDescripcion.requestFocus();
		}
		
		else if(pos==0)
			mensaje("Seleccione Autor");
		else{
			//PASO 3: crear objeto de la clase Libro
			Libro lib=new Libro();
			//PASO 4: setear el objeto "lib" con los 
			//valores de las variables
			lib.setCodigo(Integer.parseInt(cod));
			lib.setDescripcion(des);
			lib.setPrecio(Double.parseDouble(pre));
			lib.setCantidad(Integer.parseInt(can));
			lib.setCodigoAutor(pos);
			//PASO 5: variable para alacenar el valor de
			//retorno del método insertLibro, dicho método
			//recibe como parametro al objeto "lib"
			int salida;
			salida=dao.updateLibro(lib);
			//PASO 6: validar el valor de salida
			if(salida!=-1){
				mensaje("Registro actualizado correc.");
				listado();
			}
			else
				mensaje("Error en la actualizacion");
			
			
		}		
	}
	//método listado
	void listado(){
		//Paso 1: borrar las filas del modelo "mLibro"
		mLibro.setRowCount(0);
		//Paso 2: objeto para almacenar el valor de 
		//retorno del método listLibros
		ArrayList<Libro> data=dao.listLibros();
		//Paso 3: bucle "for"
		for(Libro bean:data){
		  //Paso 4: crear una arreglo de la clase Object 
		  Object fila[]={bean.getCodigo(),
				  		bean.getDescripcion(),
				  		bean.getPrecio(),
				  		bean.getCantidad(),
				  		bean.getDatosAutor()};	
		  //Paso 5: adicionar el arreglo "fila" al 
		  //objeto "mLibro"
		  mLibro.addRow(fila);
		  //incrementar variable
		  numFilas++;
		}
	}


	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblLibro) {
			mouseClickedTblLibro(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblLibro(MouseEvent arg0) {
		//variable para almacenar el indice de 
		//la fila seleccionada
		int fila=tblLibro.getSelectedRow();
		mostrarDatos(fila);
	}
	
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == tblLibro) {
			keyReleasedTblLibro(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTblLibro(KeyEvent arg0) {
		//variable para almacenar el indice de 
		//la fila seleccionada
		int fila=tblLibro.getSelectedRow();
		mostrarDatos(fila);
	}
	void mostrarDatos(int fila){
		 if(fila!=-1){
			//variables
			String cod,des,pre,can,autor;
			//obtener los valores según fila y columna
			cod=""+tblLibro.getValueAt(fila, 0);
			des=""+tblLibro.getValueAt(fila, 1);
			pre=""+tblLibro.getValueAt(fila, 2);
			can=""+tblLibro.getValueAt(fila, 3);
			autor=""+tblLibro.getValueAt(fila, 4);
			//mostrar datos en las cajas
			txtCodigo.setText(cod);
			txtDescripcion.setText(des);
			txtPrecio.setText(pre);
			txtCantidad.setText(can);
			cboAutor.setSelectedItem(autor);
		 }	
	}
}






