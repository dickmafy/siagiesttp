package modules.horario.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_silabo_nota")
public class SilaboNota implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long seccion;
	private Long criterio;
	
	@Id	
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="pk_criterio")
	public Long getCriterio() 								{return criterio;}
	public void setCriterio(Long criterio) 					{this.criterio = criterio;}
	
}