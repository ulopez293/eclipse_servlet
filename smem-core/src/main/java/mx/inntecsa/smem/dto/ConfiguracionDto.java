package mx.inntecsa.smem.dto;

@SuppressWarnings("serial")
public class ConfiguracionDto  extends GenericDto implements java.io.Serializable {

	private Integer diasPreventivos;
	private Integer diasCorrectivos;
	private Integer diasCancelaCorrectivos;

	private static ConfiguracionDto instance = null;

    public static ConfiguracionDto getInstance() {
      
    	if(instance == null) {
         instance = new ConfiguracionDto();
    	}
      
    	return instance;
    }
	   
	public Integer getDiasPreventivos() {
		return diasPreventivos;
	}
	
	public void setDiasPreventivos(Integer diasPreventivos) {
		this.diasPreventivos = diasPreventivos;
	}
	
	public Integer getDiasCorrectivos() {
		return diasCorrectivos;
	}
	
	public void setDiasCorrectivos(Integer diasCorrectivos) {
		this.diasCorrectivos = diasCorrectivos;
	}

	public Integer getDiasCancelaCorrectivos() {
		return diasCancelaCorrectivos;
	}

	public void setDiasCancelaCorrectivos(Integer diasCancelaCorrectivos) {
		this.diasCancelaCorrectivos = diasCancelaCorrectivos;
	}

	@Override
	public String getDescripcionBitacora() {
		return "Configuracion: preventivos: " + diasPreventivos + " correctivos: " + diasCorrectivos;
	}
}
