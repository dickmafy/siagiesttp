package modules.cetpro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_cetpro_ct")
public class CetproCt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pk_cetpro_matricula;
	private Long pk_ct;
	private Long prioridad;
	private Long estado;
	
	public CetproCt() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cetpro_ct")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="pk_cetpro_matricula")
	public Long getPk_cetpro_matricula() 						{return pk_cetpro_matricula;}
	public void setPk_cetpro_matricula(Long pk_cetpro_matricula){this.pk_cetpro_matricula = pk_cetpro_matricula;}

	@Column(name="pk_ct")
	public Long getPk_ct() 										{return pk_ct;}
	public void setPk_ct(Long pk_ct) 							{this.pk_ct = pk_ct;}

	@Column(name="prioridad")
	public Long getPrioridad() 									{return prioridad;}
	public void setPrioridad(Long prioridad) 					{this.prioridad = prioridad;}

	@Column(name="estado")
	public Long getEstado() 									{return estado;}
	public void setEstado(Long estado) 							{this.estado = estado;}

	
	

}
