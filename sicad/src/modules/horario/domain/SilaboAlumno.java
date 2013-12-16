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
@Table(name="horario.m_silabo_alumno")
public class SilaboAlumno implements Serializable
{
	//limpio
	
//	 pk_silabo_alumno serial NOT NULL,
//	  pk_silabo_cronograma integer NOT NULL,
//	  pk_alumno integer NOT NULL,
//	  nota1 integer,
//	  nota2 integer,
//	  nota3 integer,
//	  estado integer,
	
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	pk_silabo_cronograma;
	private	Date 	pk_alumno;
	private	String nombre_completo;
	private Long 	estado;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_silabo_alumno")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_silabo_cronograma() {
		return pk_silabo_cronograma;
	}
	public void setPk_silabo_cronograma(Long pk_silabo_cronograma) {
		this.pk_silabo_cronograma = pk_silabo_cronograma;
	}
	public Date getPk_alumno() {
		return pk_alumno;
	}
	public void setPk_alumno(Date pk_alumno) {
		this.pk_alumno = pk_alumno;
	}
	
	
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	
	
	
	
	
}