package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class frmPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JDesktopPane dp;
	private JMenuItem mntmBoleta;
	public static JLabel lblDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
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
	public frmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 505);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		JMenuItem mntmEmpleado = new JMenuItem("Empleado");
		mntmEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmEmpleadoActionPerformed(arg0);
			}
		});
		mnMantenimiento.add(mntmEmpleado);
		
		JMenuItem mntmLector = new JMenuItem("Lector");
		mntmLector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmLectorActionPerformed(arg0);
			}
		});
		mnMantenimiento.add(mntmLector);
		
		JMenuItem mntmLibro = new JMenuItem("Libro");
		mntmLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmLibroActionPerformed(arg0);
			}
		});
		
		JMenuItem mntmUsuario = new JMenuItem("Usuario");
		mnMantenimiento.add(mntmUsuario);
		mnMantenimiento.add(mntmLibro);
		
		JMenu mnProceso = new JMenu("Proceso");
		menuBar.add(mnProceso);
		
		mntmBoleta = new JMenuItem("Boleta");
		mntmBoleta.addActionListener(this);
		mnProceso.add(mntmBoleta);
		
		JMenuItem mntmTraslado = new JMenuItem("Traslado");
		mnProceso.add(mntmTraslado);
		
		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		JMenuItem mntmLibroXAutor = new JMenuItem("Libro x Autor");
		mnConsulta.add(mntmLibroXAutor);
		
		JMenuItem mntmPagoXLector = new JMenuItem("Pago x Lector");
		mnConsulta.add(mntmPagoXLector);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmPrestamos = new JMenuItem("Prestamos");
		mntmPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmPrestamosActionPerformed(arg0);
			}
		});
		mnReportes.add(mntmPrestamos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		dp = new JDesktopPane();
		contentPane.add(dp, BorderLayout.CENTER);
		
		lblDatos = new JLabel("Bienvenido:");
		lblDatos.setFont(new Font("Arial", Font.BOLD, 20));
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDatos, BorderLayout.NORTH);
		
		
	
	}

	protected void mntmLibroActionPerformed(ActionEvent arg0) {
		
		frmLibro f=new frmLibro();
		
		dp.add(f);
		
		f.setVisible(true);
	}
	
	
	
	protected void mntmEmpleadoActionPerformed(ActionEvent arg0) {
		
		
	}
	protected void mntmPrestamosActionPerformed(ActionEvent arg0) {
		
	}
	protected void mntmLectorActionPerformed(ActionEvent arg0) {
		
	
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmBoleta) {
			actionPerformedMntmBoleta(arg0);
		}
	}
	protected void actionPerformedMntmBoleta(ActionEvent arg0) {
		frmBoleta f=new frmBoleta();
		dp.add(f);
		f.setVisible(true);
	}
}
