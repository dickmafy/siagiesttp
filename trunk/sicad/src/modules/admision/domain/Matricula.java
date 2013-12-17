package modules.admision.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="admision.m_matricula")
public class Matricula implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private	Long proceso;
	private Long especialidad;
	private Long turno;
	private Long seccion;
	private Long persona;
	private Date	pago_fecha;
	private Long	pago_banco;
	private String	pago_recibo;
	
	private Long estado;
	private	Long modalidad;
	
	private String nombreEstado;
	private String nombreEspecialidad;
	private String nombreExonerado;
	private String nombreProfesion;
	private String nombreTurno;
	
	private String personaNombre;
	private String personaPaterno;
	private String personaMaterno;
	private String personaCorreo;
	private String personaTelefono;
	private String personaCelular;
	private String personaDireccion;
	private String personaDni;
	
	private Long silabo;
	private Long silaboAlumno;
	
	@Transient
	private Long asistio;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_matricula")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_proceso")
	public Long getProceso() 								{return proceso;}
	public void setProceso(Long proceso) 					{this.proceso = proceso;}
	
	@Column(name="pk_especialidad")
	public Long getEspecialidad() 							{return especialidad;}
	public void setEspecialidad(Long especialidad) 			{this.especialidad = especialidad;}
	
	@Column(name="pk_turno")
	public Long getTurno() 									{return turno;}
	public void setTurno(Long turno) 						{this.turno = turno;}
	
	@Column(name="pk_seccion")
	public Long getSeccion() 								{return seccion;}
	public void setSeccion(Long seccion) 					{this.seccion = seccion;}
	
	@Column(name="pk_persona")
	public Long getPersona() 								{return persona;}
	public void setPersona(Long persona) 					{this.persona = persona;}
	
	@Column(name="pago_fecha")
	public Date getPago_fecha() 							{return pago_fecha;}
	public void setPago_fecha(Date pago_fecha) 				{this.pago_fecha = pago_fecha;}
		
	@Column(name="pago_banco")
	public Long getPago_banco()                            	{return pago_banco;}
	public void setPago_banco(Long pago_banco)              {this.pago_banco = pago_banco;}
			
	@Column(name="pago_recibo")
	public String getPago_recibo() 							{return pago_recibo;}
	public void setPago_recibo(String pago_recibo) 			{this.pago_recibo = pago_recibo;}
	
	@Column(name="modalidad")
	public Long getModalidad() 								{return modalidad;	}
	public void setModalidad(Long modalidad) 				{this.modalidad = modalidad;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}	
	public void setEstado(Long estado)	    				{this.estado = estado;}
	
	
	
	private static final String[] estadoVal = {
		"PENDIENTE","PENDIENTE REQUISITOS",
		"PENDIENTE PAGO","PRE MATRICULADO",
		"MATRICULADO","RETIRADO"}; 
	
	@Transient
	public String getNombreEstado() 
	{
		return estadoVal[estado.intValue() % estadoVal.length];
	}
	
	
	
	public void setNombreEstado(String nombreEstado) 
	{this.nombreEstado = nombreEstado;}
	

	@Transient
	public String getNombreTurno() 
	{
		if(turno==null)				{return "";}
		if(turno.longValue()==1L)	{return "MA�ANA";}
		if(turno.longValue()==2L)	{return "TARDE";}
		if(turno.longValue()==3L)	{return "NOCHE";}
		else						{return "T";}
	}
	public void setNombreTurno(String nombreTurno) 
	{this.nombreTurno = nombreTurno;}
	
	
	@Transient
	public String getNombreEspecialidad() 							{return nombreEspecialidad;}
	public void setNombreEspecialidad(String nombreEspecialidad) 	{this.nombreEspecialidad = nombreEspecialidad;}
	
	@Transient
	public String getNombreExonerado() 								{return nombreExonerado;}
	public void setNombreExonerado(String nombreExonerado) 			{this.nombreExonerado = nombreExonerado;}
	
	@Transient
	public String getNombreProfesion() 								{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 			{this.nombreProfesion = nombreProfesion;}
	
	@Transient
	public String getPersonaNombre() 						{return personaNombre;}
	public void setPersonaNombre(String personaNombre) 		{this.personaNombre = personaNombre;}
	
	@Transient
	public String getPersonaPaterno() 						{return personaPaterno;}
	public void setPersonaPaterno(String personaPaterno) 	{this.personaPaterno = personaPaterno;}
	
	@Transient
	public String getPersonaMaterno() 						{return personaMaterno;}
	public void setPersonaMaterno(String personaMaterno) 	{this.personaMaterno = personaMaterno;}
	
	@Transient
	public String getPersonaCorreo() 						{return personaCorreo;}
	public void setPersonaCorreo(String personaCorreo) 		{this.personaCorreo = personaCorreo;}
	
	@Transient
	public String getPersonaTelefono() 						{return personaTelefono;}
	public void setPersonaTelefono(String personaTelefono) 	{this.personaTelefono = personaTelefono;}
	
	@Transient
	public String getPersonaCelular() 						{return personaCelular;}
	public void setPersonaCelular(String personaCelular) 	{this.personaCelular = personaCelular;}
	
	@Transient
	public String getPersonaDireccion() 					{return personaDireccion;}
	public void setPersonaDireccion(String personaDireccion) 	{this.personaDireccion = personaDireccion;}
	
	@Transient
	public String getPersonaDni() 							{return personaDni;}
	public void setPersonaDni(String personaDni) 			{this.personaDni = personaDni;}
	
	@Transient
	public Long getSilabo() {
		return silabo;
	}
	public void setSilabo(Long silabo) {
		this.silabo = silabo;
	}
	@Transient
	public Long getSilaboAlumno() {
		return silaboAlumno;
	}
	public void setSilaboAlumno(Long silaboAlumno) {
		this.silaboAlumno = silaboAlumno;
	}
	public Long getAsistio() {
		return asistio;
	}
	public void setAsistio(Long asistio) {
		this.asistio = asistio;
	}
	
	

}