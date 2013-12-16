package modules.horario.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_unidad_ct")
public class SilaboUnidadCt implements Serializable
{
	//limpio
	
	
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	pk_silabo_cronograma;
	private	Long 	pk_ct;
	private	Long	prioridad;
	private Long 	estado;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_nota")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_silabo_cronograma() {
		return pk_silabo_cronograma;
	}
	public void setPk_silabo_cronograma(Long pk_silabo_cronograma) {
		this.pk_silabo_cronograma = pk_silabo_cronograma;
	}
	public Long getPk_ct() {
		return pk_ct;
	}
	public void setPk_ct(Long pk_ct) {
		this.pk_ct = pk_ct;
	}
	public Long getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
		
	
}