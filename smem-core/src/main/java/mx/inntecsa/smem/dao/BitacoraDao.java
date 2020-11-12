package mx.inntecsa.smem.dao;

import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.pojo.Bitacora;

@SuppressWarnings("rawtypes")
public interface BitacoraDao extends GenericDao {

	public List<Bitacora> getBitacoraPorParametros(String usuario, Date fechaInicio, Date fechaFin, String movimiento);
	public List<Bitacora> getBitacora(String usuario);
}
