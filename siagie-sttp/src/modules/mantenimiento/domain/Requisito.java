package modules.mantenimiento.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mantenimiento.m_requisito")
public class Requisito implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private Long estado;
	private Boolean check; 
	private Long pk_matricual;
	private Long requisito_matricula;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_requisito")
	public Long getId() 							{return id;}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="descripcion")
	public String getDescripcion() 					{return descripcion;	}
	public void setDescripcion(String descripcion) 	{this.descripcion = descripcion;	}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}

	@Transient
	public Boolean getCheck() 								{return check;}
	public void setCheck(Boolean check) 					{this.check = check;}
	
	@Transient
	public Long getPk_matricual() {		return pk_matricual;	}
	public void setPk_matricual(Long pk_matricual) {		this.pk_matricual = pk_matricual;	}
	
	@Transient
	public Long getRequisito_matricula() {		return requisito_matricula;	}
	public void setRequisito_matricula(Long requisito_matricula) {		this.requisito_matricula = requisito_matricula;	}
	
	
}