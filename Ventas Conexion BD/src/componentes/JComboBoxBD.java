package componentes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import util.MySqlConectar;
public class JComboBoxBD extends JComboBox{
	
	//constructor
	public JComboBoxBD(String sql){
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConectar.getConectar();
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			addItem("[Seleccione]");
			while(rs.next()){
				addItem(rs.getString(1));
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
	}
	
}
