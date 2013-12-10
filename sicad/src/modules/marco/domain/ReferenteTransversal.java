package modules.marco.domain;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="marco.m_referente_transversal")
public class ReferenteTransversal implements Serializable
{
	private static final long serialVersionUID = 1L;
	private PK_ReferenteTransversal pk;
	
	@EmbeddedId
	public PK_ReferenteTransversal getPk() 			{return pk;}
	public void setPk(PK_ReferenteTransversal pk) 	{this.pk = pk;}
	
}