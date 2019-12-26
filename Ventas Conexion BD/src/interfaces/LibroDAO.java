package interfaces;
import java.util.ArrayList;
import entidad.Libro;
public interface LibroDAO {
	//definir métodos
	public int insertLibro(Libro lib);
	public int updateLibro(Libro lib);
	public int deleteLibro(int cod);
	public ArrayList<Libro> listLibros();
	
	///para la evaluacion EL2
	public ArrayList<Libro> listado(int cantidad);
	
	//para el reporte
	public ArrayList<Libro> listarLibroXAutor(int codAutor);
	
}
