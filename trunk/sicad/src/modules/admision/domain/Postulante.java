package modules.admision.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="admision.m_postulante")
public class Postulante implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long   	id;
	private Long 	proceso;
	private Long 	profesion;
	private Long	turno;
	private Long 	persona;
	private Date	pago_fecha;
	private Long	pago_banco;
	private String	pago_recibo;
	private Double	puntuacion;
	private Long 	modalidad;
	private	Date	regfec;
	private	Long	regusr;
	private Long	ingreso;
	private Long 	estado;
	
	
	private String postulanteDni;
	private String postulanteNombres;
	private String postulanteApellidos;
	private Long   personaId;
	private String nombreEspecialidad;
	
	private String nombreProfesion;
	
	private String nombreTurno;
	private String nombreEstado;
	private String nombreIngreso;
	private String nombreModalidad;
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_postulante")
	public Long getId()                                     {return id;}
	public void setId(Long id)                              {this.id = id;}
	
	@Column(name="pk_proceso")
	public Long getProceso()								{return proceso;}
	public void setProceso(Long proceso) 					{this.proceso = proceso;}
	
	@Column(name="pk_persona")
	public Long getPersona()								{return persona;}
	public void setPersona(Long persona) 					{this.persona = persona;}
	
	@Column(name="pk_profesion")	
	public Long getProfesion() 								{return profesion;}
	public void setProfesion(Long profesion)			 	{this.profesion = profesion;}
	
	@Column(name="pk_turno")
	public Long getTurno() 									{return turno;}
	public void setTurno(Long turno) 						{this.turno = turno;}
	
	@Column(name="pago_fecha")
	public Date getPago_fecha() 							{return pago_fecha;}
	public void setPago_fecha(Date pago_fecha) 				{this.pago_fecha = pago_fecha;}
	
	@Column(name="pago_banco")
	public Long getPago_banco()                            	{return pago_banco;}
	public void setPago_banco(Long pago_banco)              {this.pago_banco = pago_banco;}
		
	@Column(name="pago_recibo")
	public String getPago_recibo() 							{return pago_recibo;}
	public void setPago_recibo(String pago_recibo) 			{this.pago_recibo = pago_recibo;}
	
	@Column(name="puntuacion")
	public Double getPuntuacion() 							{return puntuacion;}
	public void setPuntuacion(Double puntuacion)			{this.puntuacion = puntuacion;}
	
	@Column(name="modalidad")
	public Long getModalidad() 								{return modalidad;}
	public void setModalidad(Long modalidad) 				{this.modalidad = modalidad;}
	
	@Temporal(TemporalType.DATE)
	@Column(name="registro_fecha", updatable = false)
	public Date getRegfec() 								{return regfec;}	
	public void setRegfec(Date regfec) 						{this.regfec = regfec;}
	
	@Column(name="registro_usuario", updatable = false)
	public Long getRegusr() 								{return regusr;}
	public void setRegusr(Long regusr) 						{this.regusr = regusr;}
	
	@Column(name="ingreso")
	public Long getIngreso() 								{return ingreso;}
	public void setIngreso(Long ingreso)		 			{this.ingreso = ingreso;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	
	@Transient
	public String getPostulanteDni() 								{return postulanteDni;}
	public void setPostulanteDni(String postulanteDni) 				{this.postulanteDni = postulanteDni;}
	
	@Transient
	public String getPostulanteNombres() 							{return postulanteNombres;}
	public void setPostulanteNombres(String postulanteNombres) 		{this.postulanteNombres = postulanteNombres;}
	
	@Transient
	public String getPostulanteApellidos() 							{return postulanteApellidos;}
	public void setPostulanteApellidos(String postulanteApellidos) 	{this.postulanteApellidos = postulanteApellidos;}
	
	@Transient
	public String getNombreEspecialidad() 							{return nombreEspecialidad;}
	public void setNombreEspecialidad(String nombreEspecialidad) 	{this.nombreEspecialidad = nombreEspecialidad;}
	
	@Transient
	public String getNombreProfesion() 								{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 			{this.nombreProfesion = nombreProfesion;}
	
	@Transient
	public Long getPersonaId() 										{return personaId;}
	public void setPersonaId(Long personaId) 						{this.personaId = personaId;}
	
	@Transient
	public String getNombreTurno() 
	{
		if(turno.longValue()==1L)	{return "MAÑANA";}
		if(turno.longValue()==2L)	{return "TARDE";}
		if(turno.longValue()==3L)	{return "NOCHE";}
		return "";
	}
	
	public void setNombreTurno(String nombreTurno) 
	{this.nombreTurno = nombreTurno;}
	
	@Transient
	public String getNombreEstado() 
	{
		if(estado.longValue()==0L)	{return "ANULADO";}
		if(estado.longValue()==1L)	{return "PENDIENTE REQUISITOS";}
		if(estado.longValue()==2L)	{return "PENDIENTE PAGO";}
		if(estado.longValue()==3L)	{return "PRE-PUBLICADO";}
		if(estado.longValue()==4L)	{return "POSTULANTE";}
		return "";
	}
	public void setNombreEstado(String nombreEstado) 
	{this.nombreEstado = nombreEstado;}
		
	@Transient
	public String getNombreIngreso() 					
	{
		if(ingreso==null)			{return "POSTULANTE";}
		if(ingreso.longValue()==0L)	{return "NSP";}
		if(ingreso.longValue()==1L)	{return "NO INGRESANTE";}
		if(ingreso.longValue()==2L)	{return "INGRESANTE";}
		return "POSTULANTE";
	}
	public void setNombreIngreso(String nombreIngreso) 				{this.nombreIngreso = nombreIngreso;}
	
	@Transient
	public String getNombreModalidad() 								
	{
		if(modalidad==null)				{return "";}
		if(modalidad.longValue()==0L)	{return "REGULAR";}
		if(modalidad.longValue()==1L)	{return "PRIMEROS PUESTOS";}
		if(modalidad.longValue()==2L)	{return "DEPORTISTA CALIFICADO";}
		if(modalidad.longValue()==3L)	{return "PLAN INTEGRAL REPARACIONES";}
		if(modalidad.longValue()==4L)	{return "ARTISTA CALIFICADO";}
		if(modalidad.longValue()==5L)	{return "DISCAPACIDAD";}
		return "";
	}
	public void setNombreModalidad(String nombreModalidad) 			{this.nombreModalidad = nombreModalidad;}	
}