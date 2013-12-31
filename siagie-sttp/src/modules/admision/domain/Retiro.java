package modules.admision.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="admision.m_retiro")
public class Retiro implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long    matricula;
	private Long    persona;
	private Long    motivo;
	private String  observacion;
	private Long    estado;
	
	
	private Long   especialidad;
	private Long   proceso;
	private Long   turno;
	private Long   seccion;
	
	private String nombres;
	private String apellidos;
	private String correo;
	private Long   estado_civil;
	private Long   ubigeo;
	
	private String nombreSeccion;
	private String nombreTurno;
	private String nombreEspecialidad;
	private String nombreEstado; 
	private String nombreProceso;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_retiro")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_matricula")
	public Long getMatricula() 								{return matricula;}
	public void setMatricula(Long matricula) 				{this.matricula = matricula;}
	
	
	@Column(name="motivo")
	public Long getMotivo() 								{return motivo;}
	public void setMotivo(Long motivo) 					{this.motivo = motivo;}
	
	@Column(name="observacion")
	public String getObservacion() 							{return observacion;}
	public void setObservacion(String observacion) 			{this.observacion = observacion;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}	
	public void setEstado(Long estado)	    				{this.estado = estado;}
	
	
	@Transient
	public String getNombreSeccion() 						
	{
		if(seccion==null)           {return "";}
		if(seccion.longValue()==1L) {return "A";}
		if(seccion.longValue()==2L) {return "B";}
		if(seccion.longValue()==3L) {return "C";}
		return "";
	}
	public void setNombreSeccion(String nombreSeccion) 			 {this.nombreSeccion = nombreSeccion;}
	
	
	@Transient
	public String getNombreTurno() 
	{
		if(turno==null)				{return "";}
		if(turno.longValue()==1L)	{return "DIURNO";}
		else						{return "NOCTURNO";}
	}
	public void setNombreTurno(String nombreTurno) 				 {this.nombreTurno = nombreTurno;}
	
	@Transient
	public String getNombreEstado() {
	
		if(estado_civil==null)           {return "";}
		if(estado_civil.longValue()==1L) {return "SOLTERO";}
		if(estado_civil.longValue()==2L) {return "CASADO";}
		if(estado_civil.longValue()==3L) {return "VIUDO";}
		return "";
	} 
	public void setNombreEstado(String nombreEstado) 			 {this.nombreEstado = nombreEstado;}
	
	@Transient
	public String getNombreEspecialidad() 						 {return nombreEspecialidad;}
	public void setNombreEspecialidad(String nombreEspecialidad) {this.nombreEspecialidad = nombreEspecialidad;}
	
	@Column(name="pk_persona")
	public Long getPersona() 									 {return persona;}
	public void setPersona(Long persona) 						 {this.persona = persona;}
		
	@Transient
	public Long getEspecialidad() 								 {return especialidad;}
	public void setEspecialidad(Long especialidad) 				 {this.especialidad = especialidad;}
	
	@Transient
	public Long getProceso() 									 {return proceso;}
	public void setProceso(Long proceso) 						 {this.proceso = proceso;}
	
	@Transient
	public Long getTurno() 										 {return turno;}
	public void setTurno(Long turno) 						     {this.turno = turno;}
	
	@Transient
	public Long getSeccion() 									 {return seccion;}
	public void setSeccion(Long seccion) 						 {this.seccion = seccion;}
	
	@Transient
	public String getNombres() 									 {return nombres;}
	public void setNombres(String nombres) 					     {this.nombres = nombres;}
	
	@Transient
	public String getApellidos() 							     {return apellidos;}
	public void setApellidos(String apellidos) 				{this.apellidos = apellidos;}
	
	@Transient
	public String getCorreo() 								{return correo;}
	public void setCorreo(String correo) 					{this.correo = correo;}
	
	@Transient
	public Long getEstado_civil() 							{return estado_civil;}
	public void setEstado_civil(Long estado_civil) 			{this.estado_civil = estado_civil;}
	
	@Transient
	public Long getUbigeo() 								{return ubigeo;}	
	public void setUbigeo(Long ubigeo) 						{this.ubigeo = ubigeo;}
	
	@Transient
	public String getNombreProceso() 						{return nombreProceso;}
	public void setNombreProceso(String nombreProceso) 		{this.nombreProceso = nombreProceso;}

}