package modules.marco.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PK_ReferenteTransversal implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long profesion;
	private Long transversal;
	
	@Column(name="pk_profesion", insertable = true, updatable = false)
	public Long getProfesion() 						{return profesion;}
	public void setProfesion(Long profesion)	 	{this.profesion = profesion;}
	
	@Column(name="pk_transversal", insertable = true, updatable = false)
	public Long getTransversal() 					{return transversal;}
	public void setTransversal(Long transversal) 	{this.transversal = transversal;}
}