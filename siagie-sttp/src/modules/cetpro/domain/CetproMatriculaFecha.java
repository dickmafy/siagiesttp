package modules.cetpro.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cetpro.m_cetpro_matricula_fecha")
public class CetproMatriculaFecha implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	id;
	private Long 	pk_cetpro_matricula;
	private Date 	fecha;
	private Long 	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cetpro_matricula_fecha")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPk_cetpro_matricula() {
		return pk_cetpro_matricula;
	}
	public void setPk_cetpro_matricula(Long pk_cetpro_matricula) {
		this.pk_cetpro_matricula = pk_cetpro_matricula;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	
	
	

	
	
	
}