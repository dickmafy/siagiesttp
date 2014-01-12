package modules.cetpro.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="cetpro.m_cetpro_matricula_alumno")
public class CetproMatriculaAlumno implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long 	id;
	private Long 	pk_cetpro_matricula;
	private Long 	pk_persona;
	private Long 	estado;
	private String	alumno_dni;
	private String	alumno_apepat;
	private String	alumno_apemat;
	private String	alumno_nom;
	private String	alumno_nom_completo;
	private Long 	asistio;
	private String 	asistioNom;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cetpro_matricula_alumno")
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
	public Long getPk_persona() {
		return pk_persona;
	}
	public void setPk_persona(Long pk_persona) {
		this.pk_persona = pk_persona;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	@Transient
	public String getAlumno_dni() {
		return alumno_dni;
	}
	public void setAlumno_dni(String alumno_dni) {
		this.alumno_dni = alumno_dni;
	}
	@Transient
	public String getAlumno_apepat() {
		return alumno_apepat;
	}
	public void setAlumno_apepat(String alumno_apepat) {
		this.alumno_apepat = alumno_apepat;
	}
	@Transient
	public String getAlumno_apemat() {
		return alumno_apemat;
	}
	public void setAlumno_apemat(String alumno_apemat) {
		this.alumno_apemat = alumno_apemat;
	}
	@Transient
	public String getAlumno_nom() {
		return alumno_nom;
	}
	public void setAlumno_nom(String alumno_nom) {
		this.alumno_nom = alumno_nom;
	}
	@Transient
	public String getAlumno_nom_completo() {
		return alumno_apepat+" "+alumno_apemat+" "+alumno_nom;
	}
	public void setAlumno_nom_completo(String alumno_nom_completo) {
		this.alumno_nom_completo = alumno_nom_completo;
	}
	@Transient
	public Long getAsistio() {
		return asistio;
	}
	public void setAsistio(Long asistio) {
		this.asistio = asistio;
	}
	@Transient
	public String getAsistioNom() {
		if(asistio==1L)
		{return "Asistió";}
		else
		{return "No Asistió";}
	}
	public void setAsistioNom(String asistioNom) {
		this.asistioNom = asistioNom;
	}
	
	
	

}