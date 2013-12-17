package modules.horario.domain;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import modules.evaluaciones.domain.Pregunta;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.bytecode.javassist.FieldHandler;

@Entity
@Table(name="horario.m_silabo_cronograma")
public class SilaboCronograma implements Serializable
{
	

	
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	pk_meta;
	private	String 	contenido;
	private Long 	pk_unidad;
	private Long 	pk_seccion;
	private Long 	pk_docente;
	private Long 	estado;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_silabo_cronograma")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_meta() {
		return pk_meta;
	}
	public void setPk_meta(Long pk_meta) {
		this.pk_meta = pk_meta;
	}
	
	
	public Long getPk_unidad() {
		return pk_unidad;
	}
	public void setPk_unidad(Long pk_unidad) {
		this.pk_unidad = pk_unidad;
	}
	public Long getPk_seccion() {
		return pk_seccion;
	}
	public void setPk_seccion(Long pk_seccion) {
		this.pk_seccion = pk_seccion;
	}
	public Long getPk_docente() {
		return pk_docente;
	}
	public void setPk_docente(Long pk_docente) {
		this.pk_docente = pk_docente;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	





	
	
}