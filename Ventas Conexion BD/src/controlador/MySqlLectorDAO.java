package controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entidad.Lector;
import entidad.Libro;
import interfaces.LectorDAO;
import util.MySqlConectar;
public class MySqlLectorDAO implements LectorDAO{

	public ArrayList<Lector> listarLectorXApellidos(String ape) {
		ArrayList<Lector> lista=new ArrayList<Lector>();
		//3 clases
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConectar.getConectar();
			String sql="select *from tb_lector where ape_lec like ?";
			//Paso 3: enviar la variable sql al objeto pstm
			pstm=cn.prepareStatement(sql);
			//Paso 4: parametros "SI EXISTE"
			pstm.setString(1, ape+"%");
			//Paso 5: ejecutar
			rs=pstm.executeQuery();
			//Paso 6: declarar objeto de la clase Lector
			Lector bean;
			
			while(rs.next()){
				//Paso 8: crear el objeto bean
				bean=new Lector();
				//Paso 9: setear el objeto "bean" con
				//los valores actuales del objeto "rs"
				bean.setCodigo(rs.getString(1));//1 codigo
				bean.setNombre(rs.getString(2));
				bean.setApellidos(rs.getString(3));
				bean.setDni(rs.getInt(4));
				bean.setSexo(rs.getString(5));
				//Paso 10: adicionar el objeto "bean" 
				//al arreglo "lista"
				lista.add(bean);
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
		return lista;

	}

}
