package entidad;
public class Boleta {
	private int numBoleta;
	private String codigoLector,fecha;
	private int codigoEmpleado;
	private double total;
	
	
	public int getNumBoleta() {
		return numBoleta;
	}
	public void setNumBoleta(int numBoleta) {
		this.numBoleta = numBoleta;
	}
	public String getCodigoLector() {
		return codigoLector;
	}
	public void setCodigoLector(String codigoLector) {
		this.codigoLector = codigoLector;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCodigoEmpleado() {
		return codigoEmpleado;
	}
	public void setCodigoEmpleado(int codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
