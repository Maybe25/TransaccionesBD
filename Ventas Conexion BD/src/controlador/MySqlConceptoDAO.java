package controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entidad.Concepto;
import entidad.Lector;
import interfaces.ConceptoDAO;
import util.MySqlConectar;
public class MySqlConceptoDAO implements ConceptoDAO{
	public ArrayList<Concepto> listarConceptoXNombre(String nom) {
		ArrayList<Concepto> lista=new ArrayList<Concepto>();
		//3 clases
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//Paso 1: conexion a BD
			cn=MySqlConectar.getConectar();
			//Paso 2: sentencia sql
			String sql="select *from tb_concepto where nom_concepto like ?";
			//Paso 3: enviar la variable sql al
			//objeto pstm
			pstm=cn.prepareStatement(sql);
			//Paso 4: parametros "SI EXISTE"
			pstm.setString(1, nom+"%");
			//Paso 5: ejecutar
			rs=pstm.executeQuery();
			//Paso 6: declarar objeto de la clase Lector
			Concepto bean;
			//Paso 7: bucle para realizar recorrido 
			//sobre el objeto rs hasta que existan datos
			while(rs.next()){
				//Paso 8: crear el objeto bean
				bean=new Concepto();
				//Paso 9: setear el objeto "bean" con
				//los valores actuales del objeto "rs"
				bean.setCodigo(rs.getInt(1));//1 codigo
				bean.setNombre(rs.getString(2));
				bean.setPrecio(rs.getDouble(3));
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
