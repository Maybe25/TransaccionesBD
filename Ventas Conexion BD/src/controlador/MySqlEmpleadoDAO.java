package controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entidad.Empleado;
import interfaces.UsuarioDAO;
import util.MySqlConectar;
public class MySqlEmpleadoDAO implements UsuarioDAO{

	public Empleado iniciarSesion(String login, String clave) {
		Empleado emp=null;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConectar.getConectar();
			String sql="select cod_emp,nom_emp,"+
					"ape_pat_emp,ape_mat_emp,log_emp,"+
					"pas_emp from tb_empleado where "+
					"log_emp=? and pas_emp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			rs=pstm.executeQuery();
			if(rs.next()){
				emp=new Empleado();
				emp.setCodigo(rs.getInt(1));
				emp.setNombre(rs.getString(2));
				emp.setPaterno(rs.getString(3));
				emp.setMaterno(rs.getString(4));
				emp.setLogin(rs.getString(5));
				emp.setClave(rs.getString(6));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return emp;
	}
	
	
}
