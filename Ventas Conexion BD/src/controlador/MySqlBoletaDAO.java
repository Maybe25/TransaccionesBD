package controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import entidad.Boleta;
import entidad.Detalle;
import interfaces.BoletaDAO;
import util.MySqlConectar;
public class MySqlBoletaDAO implements BoletaDAO{
	public int registrarBoleta(Boleta bol, ArrayList<Detalle> lista) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm1=null,pstm2=null;
		try {
			cn=MySqlConectar.getConectar();
			//anular el commit del objeto cn
			cn.setAutoCommit(false);
			//GRABAR CABECERA "BOLETA"
			String sql1="insert into tb_boleta "+
					"values(?,?,?,?,?)";
			pstm1=cn.prepareStatement(sql1);
			pstm1.setInt(1, bol.getNumBoleta());
			pstm1.setString(2, bol.getCodigoLector());
			pstm1.setString(3, bol.getFecha());
			pstm1.setInt(4, bol.getCodigoEmpleado());
			pstm1.setDouble(5, bol.getTotal());
			salida=pstm1.executeUpdate();
			//GRABAR DETALLE
			String sql2="insert into tb_detalle_boleta "+
					"values(?,?,?,?)";
			//bucle "normal" o "for each"
			for(Detalle det:lista){
				pstm2=cn.prepareStatement(sql2);
				pstm2.setInt(1, bol.getNumBoleta());
				pstm2.setInt(2, det.getCodigoConcepto());
				pstm2.setDouble(3, det.getPrecio());
				pstm2.setInt(4, det.getCantidad());
				salida=pstm2.executeUpdate();
			}
			
			//habilitar el commit
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm2!=null) pstm2.close();
				if(pstm1!=null) pstm1.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

}


