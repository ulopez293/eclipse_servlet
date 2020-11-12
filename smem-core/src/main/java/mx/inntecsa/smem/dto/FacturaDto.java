package mx.inntecsa.smem.dto;

import java.util.List;

public class FacturaDto implements java.io.Serializable {

	private List<DetalleFacturaDto> listaDetalles;
	
	private String total;
	private String subtotal;
	private String iva;
	private String penalizacion;
	private String descuento;

	public List<DetalleFacturaDto> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<DetalleFacturaDto> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(String penalizacion) {
		this.penalizacion = penalizacion;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

}
