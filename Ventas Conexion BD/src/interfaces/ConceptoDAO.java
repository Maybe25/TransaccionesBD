package interfaces;
import java.util.ArrayList;
import entidad.Concepto;
public interface ConceptoDAO {
	public ArrayList<Concepto> 
			listarConceptoXNombre(String nom);
}
