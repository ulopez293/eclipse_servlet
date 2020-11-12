package mx.inntecsa.smem.dao;

import java.util.List;

import mx.inntecsa.smem.dto.CentroTrabajoBloqueadoDto;

@SuppressWarnings("rawtypes")
public interface BloqueoCTDao extends GenericDao {
	
	public List<Integer> getCentrosTrabajoBloqueados();
	public List<CentroTrabajoBloqueadoDto> getConsultaCtblq(int entidad, int unidadRegional);
	public void actualizarEstatusCT(List<Integer> centrosTrabajo, boolean bloqueo);
}
