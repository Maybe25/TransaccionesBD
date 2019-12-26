package interfaces;
import entidad.Empleado;
public interface UsuarioDAO {
	public Empleado iniciarSesion(String login,
			String clave); 
	
}
