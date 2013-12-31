package modules.evaluaciones.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PK_Balotario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long balotario;
	private Long instrumento;
	
	@Column(name="pk_balotario", insertable = true, updatable = false)
	public Long getBalotario() 						{return balotario;}
	public void setBalotario(Long balotario) 		{this.balotario = balotario;}
	
	@Column(name="pk_instrumento", insertable = true, updatable = false)
	public Long getInstrumento() 					{return instrumento;}
	public void setInstrumento(Long instrumento) 	{this.instrumento = instrumento;}
	
	
}