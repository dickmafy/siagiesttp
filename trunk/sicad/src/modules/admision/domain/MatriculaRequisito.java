package modules.admision.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="admision.m_matricula_requisito")
public class MatriculaRequisito implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	private Long pk_matricula;
	private Long pk_requisito;
	
	private Boolean check;
	private String nombreRequisito;

	@Id
	@Column(name="pk_matricula")
	public Long getPk_matricula() 							{return pk_matricula;	}
	public void setPk_matricula(Long pk_matricula) 			{this.pk_matricula = pk_matricula;	}
	
	@Id
	@Column(name="pk_requisito")
	public Long getPk_requisito() 							{return pk_requisito;}
	public void setPk_requisito(Long pk_requisito)		 	{this.pk_requisito = pk_requisito;	}
	
	@Transient
	public Boolean getCheck() 								{return check;}
	public void setCheck(Boolean check) 					{this.check = check;}
	
	@Transient
	public String getNombreRequisito() 						{return nombreRequisito;}
	public void setNombreRequisito(String nombreRequisito) 	{this.nombreRequisito = nombreRequisito;}
}
