package mx.inntecsa.smem.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConfiguracionId implements java.io.Serializable {

	private int diasCorrectivos;
	private int diasPreventivos;
	private int diasCancelaCorrectivos;

	@Column(name = "dias_correctivos", nullable = false)
	public int getDiasCorrectivos() {
		return this.diasCorrectivos;
	}

	public void setDiasCorrectivos(int diasCorrectivos) {
		this.diasCorrectivos = diasCorrectivos;
	}

	@Column(name = "dias_preventivos", nullable = false)
	public int getDiasPreventivos() {
		return diasPreventivos;
	}

	public void setDiasPreventivos(int diasPreventivos) {
		this.diasPreventivos = diasPreventivos;
	}
	
	@Column(name = "dias_cancela_correctivos", nullable = false)
	public int getDiasCancelaCorrectivos() {
		return diasCancelaCorrectivos;
	}

	public void setDiasCancelaCorrectivos(int diasCancelaCorrectivos) {
		this.diasCancelaCorrectivos = diasCancelaCorrectivos;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConfiguracionId))
			return false;
		ConfiguracionId castOther = (ConfiguracionId) other;

		return (this.getDiasCorrectivos() == castOther.getDiasCorrectivos())
				&& (this.getDiasPreventivos() == castOther
						.getDiasPreventivos());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getDiasCorrectivos();
		result = 37 * result + this.getDiasPreventivos();
		return result;
	}

}
