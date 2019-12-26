package entidad;
public class Libro {
	private int codigo;
	private String descripcion;
	private double precio;
	private int cantidad;
	private int codigoAutor;
	//atributo para la consulta
	private String datosAutor;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCodigoAutor() {
		return codigoAutor;
	}
	public void setCodigoAutor(int codigoAutor) {
		this.codigoAutor = codigoAutor;
	}
	public String getDatosAutor() {
		return datosAutor;
	}
	public void setDatosAutor(String datosAutor) {
		this.datosAutor = datosAutor;
	}
	
	
	
	
}
