package mx.inntecsa.smem.dao;

import mx.inntecsa.smem.pojo.Configuracion;

@SuppressWarnings("rawtypes")
public interface ConfiguracionDao extends GenericDao {
	
	public Configuracion getConfiguracion();
}
