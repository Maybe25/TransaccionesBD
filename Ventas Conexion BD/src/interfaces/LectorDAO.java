package interfaces;
import java.util.ArrayList;
import entidad.Lector;
public interface LectorDAO {
	public ArrayList<Lector> 
			listarLectorXApellidos(String ape);
}
