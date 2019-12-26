package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.MySqlLibroDAO;
import entidad.Libro;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class frmModelo extends JFrame implements ActionListener {

	
	DefaultTableModel mLibro=new DefaultTableModel();
	
	MySqlLibroDAO daoLibro=new MySqlLibroDAO();
	
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtCantidad;
	private JButton btnFiltar;
	private JTable tblLibro;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmModelo frame = new frmModelo();
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
	public frmModelo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Cantidad:");
		lblNewLabel.setBounds(10, 38, 63, 14);
		contentPane.add(lblNewLabel);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(83, 35, 86, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		btnFiltar = new JButton("Filtar");
		btnFiltar.addActionListener(this);
		btnFiltar.setBounds(288, 45, 89, 23);
		contentPane.add(btnFiltar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 559, 415);
		contentPane.add(scrollPane);
		
		tblLibro = new JTable();
		tblLibro.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblLibro);
		mLibro.addColumn("Codigo");
		mLibro.addColumn("Titulo");
		mLibro.addColumn("Precio");
		mLibro.addColumn("Cantidad");
		mLibro.addColumn("Autor");
		tblLibro.setModel(mLibro);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltar) {
			actionPerformedBtnFiltar(arg0);
		}
	}
	protected void actionPerformedBtnFiltar(ActionEvent arg0) {
		mLibro.setRowCount(0);
		int can;
		can=Integer.parseInt(txtCantidad.getText());
		ArrayList<Libro> lista=daoLibro.listado(can);
		for(Libro lib:lista){
			Object fila[]={lib.getCodigo(),
					lib.getDescripcion(),
					lib.getPrecio(),
					lib.getCantidad(),
					lib.getDatosAutor()};
			mLibro.addRow(fila);
		}
		
		
	}
}







