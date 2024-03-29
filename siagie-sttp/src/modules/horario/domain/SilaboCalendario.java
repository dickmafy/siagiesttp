package modules.horario.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import modules.evaluaciones.domain.Pregunta;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.bytecode.javassist.FieldHandler;

@Entity
@Table(name="horario.m_silabo_calendario")
public class SilaboCalendario implements Serializable
{

	
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	pk_silabo_cronograma;
	private	Date 	fecha;
	private Long 	estado;
	// estado = 2 = fecha no aparece en asistencia 
	private Long 	alumno;
	private Long	asistencia;
	private Long	silaboCalendario;
	private Long	asistenciaAlumno;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_silabo_calendario")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_silabo_cronograma() {
		return pk_silabo_cronograma;
	}
	public void setPk_silabo_cronograma(Long pk_silabo_cronograma) {
		this.pk_silabo_cronograma = pk_silabo_cronograma;
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
	
	
	@Transient
	public Long getAlumno() {
		return alumno;
	}
	public void setAlumno(Long alumno) {
		this.alumno = alumno;
	}
	@Transient
	public Long getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(Long asistencia) {
		this.asistencia = asistencia;
	}
	@Transient
	public Long getSilaboCalendario() {
		return silaboCalendario;
	}
	public void setSilaboCalendario(Long silaboCalendario) {
		this.silaboCalendario = silaboCalendario;
	}
	@Transient
	public Long getAsistenciaAlumno() {
		return asistenciaAlumno;
	}
	public void setAsistenciaAlumno(Long asistenciaAlumno) {
		this.asistenciaAlumno = asistenciaAlumno;
	}
	
	
		
	
	
}