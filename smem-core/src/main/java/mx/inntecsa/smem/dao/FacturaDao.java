package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.dto.DetalleFacturaDto;
import mx.inntecsa.smem.enums.Mes;
import mx.inntecsa.smem.enums.TipoServicio;

@SuppressWarnings("rawtypes")
public interface FacturaDao extends GenericDao {

	public List<DetalleFacturaDto> getDetallesFactura(int idContrato, TipoServicio tipoServicio, Mes mesBusqueda);
}
