package mx.inntecsa.smem.dao;

import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.enums.EstatusServicio;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.pojo.ProgramacionServicio;

/**
 * @author INNTECSA
 *
 */
@SuppressWarnings("rawtypes")
public interface ProgramacionServicioDao extends GenericDao {
	
	public List<ProgramacionServicio> getProgramacionServiciosPorEstatus(String urct, EstatusServicio []estatus);
	
	public List<ProgramacionServicio> getProgramacionServiciosPorParametros(int idCentroTrabajo, TipoServicio tipoServicio, 
			EstatusServicio estatusServicio, Date fechaInicio, Date fechaFin, String folio);

	public ProgramacionServicio getProgramacionServicioPorIdContratoDetalle(Integer idContratoDetalle);
	
	/**
	 * @param idUniverso
	 * @return ultima programacion cerrada exitosamente sin importar si es preventivo o correctivo
	 */
	public ProgramacionServicio getUltimaProgramacioPorUniverso(Long idUniverso);
	
	
	/**
	 * @param idUniverso
	 * @return regresa true si existe un servicio correctivo sin cerrar exitosamente
	 */
	public boolean getExisteCorrectivoEnProcesoPorUniverso(Long idUniverso);
	
	public boolean getExistenProgramacionesAbiertasPorProveedor(int idProveedor);
	
	/**Metodo para validar si exiten servicios preventivos o correctivos activos
	 * @param idCentroTrabajo
	 * @return regresa verdadero si existe un servicio
	 */
	public boolean getExistenServiciosPorIdCentroTrabajo(int idCentroTrabajo);
	
	public boolean getServiciosEnProcesoPorUniverso(Long idUniverso);
	
	public String getUltimoConsecutivoFolio(int anio);
}
