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
@Table(name="intranet.m_silabo_docente")
public class silabo_docente implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pk_unidad;
	private Long pk_docente;
	private	Long pk_seccion;
	private Date fecha;
	private	Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_silabo_cronograma")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_docente() {
		return pk_docente;
	}
	public void setPk_docente(Long pk_docente) {
		this.pk_docente = pk_docente;
	}
	public Long getPk_seccion() {
		return pk_seccion;
	}
	public void setPk_seccion(Long pk_seccion) {
		this.pk_seccion = pk_seccion;
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
	public Long getPk_unidad() {
		return pk_unidad;
	}
	public void setPk_unidad(Long pk_unidad) {
		this.pk_unidad = pk_unidad;
	}

	
	  
	
	
}
	