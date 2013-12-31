package modules.admision.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="admision.m_postulante_requisito")
public class PostulanteRequisito implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long postulante;
	private Long requisito;
	private Long estado;
	
	private String nombreRequisito;
	
	@Id
	@Column(name="pk_postulante")
	public Long getPostulante() 							{return postulante;}
	public void setPostulante(Long postulante) 				{this.postulante = postulante;}
	
	@Column(name="pk_requisito")	
	public Long getRequisito() 								{return requisito;}
	public void setRequisito(Long requisito) 				{this.requisito = requisito;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	@Transient
	public String getNombreRequisito() 						{return nombreRequisito;}
	public void setNombreRequisito(String nombreRequisito) 	{this.nombreRequisito = nombreRequisito;}
}