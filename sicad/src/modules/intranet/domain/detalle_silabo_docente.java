package modules.intranet.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intranet.m_detalle_silabo_docente")
public class detalle_silabo_docente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date pk_silabo_cronograma;
	private Long pk_alumno;
	private	Long estado;
	  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_detalle_silabo_docente")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Date getPk_silabo_cronograma() {
		return pk_silabo_cronograma;
	}
	public void setPk_silabo_cronograma(Date pk_silabo_cronograma) {
		this.pk_silabo_cronograma = pk_silabo_cronograma;
	}
	public Long getPk_alumno() {
		return pk_alumno;
	}
	public void setPk_alumno(Long pk_alumno) {
		this.pk_alumno = pk_alumno;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}

	
	
	
}
	