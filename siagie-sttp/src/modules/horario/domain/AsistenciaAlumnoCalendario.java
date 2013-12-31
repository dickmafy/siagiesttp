package modules.horario.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario.m_asistencia_alumno_calendario")
public class AsistenciaAlumnoCalendario implements Serializable
{
	//limpio
	
//
//	 pk_asistencia_alumno serial NOT NULL,
//	  pk_silabo_calendario integer NOT NULL,
//	  pk_silabo_alumno integer NOT NULL,
//	  asistencia integer,
//	  estado integer,
	
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	pk_silabo_calendario;
	private	Long 	pk_silabo_alumno;
	private Long 	asistencia;
	private Long 	estado;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_asistencia_alumno")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_silabo_calendario() {
		return pk_silabo_calendario;
	}
	public void setPk_silabo_calendario(Long pk_silabo_calendario) {
		this.pk_silabo_calendario = pk_silabo_calendario;
	}
	public Long getPk_silabo_alumno() {
		return pk_silabo_alumno;
	}
	public void setPk_silabo_alumno(Long pk_silabo_alumno) {
		this.pk_silabo_alumno = pk_silabo_alumno;
	}
	public Long getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(Long asistencia) {
		this.asistencia = asistencia;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}

	
	
	
	
}