package controlador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entidad.Libro;
import interfaces.LibroDAO;
import util.MySqlConectar;

public class MySqlLibroDAO implements LibroDAO{

	public int insertLibro(Libro lib) {
		/*
			El método insertLibro retorna un entero debido:
			-Si existe exito retorna 1
			-Sino -1
		*/
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//PASO 1: conexion a BD
			cn=MySqlConectar.getConectar();
			//PASO 2: sentencia SQL
			String sql="insert into tb_libro values(null,?,?,?,?)";
			//PASO 3: enviar la sentencia SQL al objeto "pstm";
			pstm=cn.prepareStatement(sql);
			//PASO 4: parametros "?"
			pstm.setString(1, lib.getDescripcion());
			pstm.setDouble(2, lib.getPrecio());
			pstm.setInt(3, lib.getCantidad());
			pstm.setInt(4, lib.getCodigoAutor());
			//PASO 5: ejecutar sentencia SQL
			salida=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	public int updateLibro(Libro lib) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConectar.getConectar();
			String sql="update tb_libro set des_lib=?,pre_lib=?,"+
						"can_lib=?,cod_autor=? where cod_lib=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, lib.getDescripcion());
			pstm.setDouble(2, lib.getPrecio());
			pstm.setInt(3, lib.getCantidad());
			pstm.setInt(4, lib.getCodigoAutor());
			pstm.setInt(5, lib.getCodigo());
			salida=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	public int deleteLibro(int cod) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConectar.getConectar();
			String sql="delete from tb_libro where cod_lib=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			salida=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	public ArrayList<Libro> listLibros() {
		ArrayList<Libro> lista=new ArrayList<Libro>();
		//3 clases
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//Paso 1: conexion a BD
			cn=MySqlConectar.getConectar();
			//Paso 2: sentencia sql
			String sql="select lib.cod_lib,lib.des_lib,"+
						"lib.pre_lib,lib.can_lib,"+
						"lib.cod_autor,"+
						"CONCAT(a.cod_autor,'--',"+
						"a.ape_autor,' ',a.nom_autor) "+
						"from tb_libro lib inner join "+
						"tb_autor a on "+
						"lib.cod_autor=a.cod_autor";
			//Paso 3: enviar la variable sql al
			//objeto pstm
			pstm=cn.prepareStatement(sql);
			//Paso 4: parametros "NO EXISTE"
			//Paso 5: ejecutar
			rs=pstm.executeQuery();
			//Paso 6: declarar objeto de la clase Libro
			Libro bean;
			//Paso 7: bucle para realizar recorrido 
			//sobre el objeto rs hasta que existan datos
			while(rs.next()){
				//Paso 8: crear el objeto bean
				bean=new Libro();
				//Paso 9: setear el objeto "bean" con
				//los valores actuales del objeto "rs"
				bean.setCodigo(rs.getInt(1));//1 codigo
				bean.setDescripcion(rs.getString(2));
				bean.setPrecio(rs.getDouble(3));
				bean.setCantidad(rs.getInt(4));
				bean.setDatosAutor(rs.getString(6));
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

	public ArrayList<Libro> listado(int cantidad) {
		ArrayList<Libro> lista=new ArrayList<Libro>();
		//3 clases
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//Paso 1: conexion a BD
			cn=MySqlConectar.getConectar();
			//Paso 2: sentencia sql
			String sql="select lib.cod_lib,lib.des_lib,"+
						"lib.pre_lib,lib.can_lib,"+
						"lib.cod_autor,"+
						"CONCAT(a.ape_autor,' ',a.nom_autor) "+
						"from tb_libro lib inner join "+
						"tb_autor a on "+
						"lib.cod_autor=a.cod_autor where lib.can_lib>=?";
			//Paso 3: enviar la variable sql al
			//objeto pstm
			pstm=cn.prepareStatement(sql);
			//Paso 4: parametros "NO EXISTE"
			pstm.setInt(1, cantidad);
			//Paso 5: ejecutar
			rs=pstm.executeQuery();
			//Paso 6: declarar objeto de la clase Libro
			Libro bean;
			//Paso 7: bucle para realizar recorrido 
			//sobre el objeto rs hasta que existan datos
			while(rs.next()){
				//Paso 8: crear el objeto bean
				bean=new Libro();
				//Paso 9: setear el objeto "bean" con
				//los valores actuales del objeto "rs"
				bean.setCodigo(rs.getInt(1));//1 codigo
				bean.setDescripcion(rs.getString(2));
				bean.setPrecio(rs.getDouble(3));
				bean.setCantidad(rs.getInt(4));
				bean.setDatosAutor(rs.getString(6));
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

	public ArrayList<Libro> listarLibroXAutor(int codAutor) {
		ArrayList<Libro> lista=new ArrayList<Libro>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConectar.getConectar();
			String sql="call sp_listarLibroXAutor(?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, codAutor);
			rs=cstm.executeQuery();
			Libro bean;
			while(rs.next()){
				bean=new Libro();
				bean.setCodigo(rs.getInt(1));
				bean.setDescripcion(rs.getString(2));
				bean.setPrecio(rs.getDouble(3));
				bean.setCantidad(rs.getInt(4));
				bean.setDatosAutor(rs.getString(5));
				lista.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

}












