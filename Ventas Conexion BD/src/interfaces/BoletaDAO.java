package interfaces;
import java.util.ArrayList;
import entidad.Boleta;
import entidad.Detalle;
public interface BoletaDAO {
	public int registrarBoleta(Boleta bol,
				ArrayList<Detalle> lista);
}
