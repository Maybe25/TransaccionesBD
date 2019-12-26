package util;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySqlConectar {
	
	public static Connection getConectar(){
		Connection cn=null;
		try {
			//PASO 1: Referencia al jar
			Class.forName("com.mysql.jdbc.Driver");
			//PASO 2: variables
			String url,user,pass;
			url="jdbc:mysql://localhost:3306/sis_biblioteca";
			user="root";
			pass="mysql";
			//PASO 3: establecer conexion con BD y enviarlo al objeto "cn"
			cn=DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
	
	
}
