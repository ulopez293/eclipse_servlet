package mx.inntecsa.smem.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuracion")
public class Configuracion implements java.io.Serializable {

	private ConfiguracionId id;

	@EmbeddedId
	@AttributeOverrides({
	@AttributeOverride(name = "diasCorrectivos", column = @Column(name = "dias_correctivos", nullable = false)),
	@AttributeOverride(name = "diasPreventivos", column = @Column(name = "dias_preventivos", nullable = false)),
	@AttributeOverride(name = "diasCancelaCorrectivos", column = @Column(name = "dias_cancela_correctivos", nullable = false))})
	public ConfiguracionId getId() {
		return this.id;
	}

	public void setId(ConfiguracionId id) {
		this.id = id;
	}

}
