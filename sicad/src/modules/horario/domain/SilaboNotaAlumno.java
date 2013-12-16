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
@Table(name="horario.m_nota")
public class SilaboNotaAlumno implements Serializable
{
	//limpio
	
	
	private static final long serialVersionUID = 1L;
	private Long    id;
	private Long	pk_silabo_alumno;
	private	Long 	pk_unidad_ct;
	private	Double nota;
	private Long 	estado;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_nota")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_silabo_alumno() {
		return pk_silabo_alumno;
	}
	public void setPk_silabo_alumno(Long pk_silabo_alumno) {
		this.pk_silabo_alumno = pk_silabo_alumno;
	}
	public Long getPk_unidad_ct() {
		return pk_unidad_ct;
	}
	public void setPk_unidad_ct(Long pk_unidad_ct) {
		this.pk_unidad_ct = pk_unidad_ct;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	
	
	
	
}