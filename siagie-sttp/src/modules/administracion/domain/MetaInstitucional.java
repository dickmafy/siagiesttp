package modules.administracion.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="administracion.m_meta_institucional")
public class MetaInstitucional implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;	
	private Long institucion;
	private Long profesion;
	private Long annio;
	private Long turno;
	
	private Long proceso;
	private Long meta_matricula;
	private Long meta_egresados;
	private Long meta_titulados;
	
	private Long resolucion;	
	private Long solicitud;
	private	Long estado;
	
	private String nombreProfesion;
	private String nombreProceso;
	private String nombreTurno;
	private String nombreEstado;
	  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_meta")
	public Long getId()    		                    		{return id;}
	public void setId(Long id)  		               	 	{this.id = id;}
	
	@Column(name="institucion")
	public Long getInstitucion()							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="profesion")	
	public Long getProfesion() 								{return profesion;}
	public void setProfesion(Long profesion) 				{this.profesion = profesion;}
	
	@Column(name="resolucion")
	public Long getResolucion() 							{return resolucion;}
	public void setResolucion(Long resolucion) 				{this.resolucion = resolucion;}
	
	@Column(name="solicitud")
	public Long getSolicitud() 								{return solicitud;}
	public void setSolicitud(Long solicitud) 				{this.solicitud = solicitud;}
	
	@Transient
	public String getNombreProfesion() 						{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 	{this.nombreProfesion = nombreProfesion;}
	
	@Column(name="annio")
	public Long getAnnio() 									{return annio;	}
	public void setAnnio(Long annio) 						{this.annio = annio;	}
	
	@Column(name="proceso")
	public Long getProceso() 								{return proceso;	}
	public void setProceso(Long proceso) 					{this.proceso = proceso;	}
	
	@Column(name="meta_matricula")
	public Long getMeta_matricula() 						{return meta_matricula;	}
	public void setMeta_matricula(Long meta_matricula) 		{this.meta_matricula = meta_matricula;	}
	
	@Column(name="meta_egresados")
	public Long getMeta_egresados() 						{return meta_egresados;	}
	public void setMeta_egresados(Long meta_egresados) 		{this.meta_egresados = meta_egresados;	}
	
	@Column(name="meta_titulados")
	public Long getMeta_titulados() 						{return meta_titulados;	}
	public void setMeta_titulados(Long meta_titulados) 		{this.meta_titulados = meta_titulados;	}

	@Column(name="turno")
	public Long getTurno() 									{return turno;	}
	public void setTurno(Long turno) 						{this.turno = turno;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	@Transient
	public String getNombreProceso() 					
	{
		if(proceso==null)					{return "";}
		if(proceso.longValue()==0L)		{return "-";}
		if(proceso.longValue()==1L)		{return "ENERO";}
		if(proceso.longValue()==2L)		{return "FEBRERO";}
		if(proceso.longValue()==3L)		{return "MARZO";}
		if(proceso.longValue()==4L)		{return "ABRIL";}
		if(proceso.longValue()==5L)		{return "MAYO";}
		if(proceso.longValue()==6L)		{return "JUNIO";}
		if(proceso.longValue()==7L)		{return "JULIO";}
		if(proceso.longValue()==8L)		{return "AGOSTO";}
		if(proceso.longValue()==9L)		{return "SEPTIEMBRE";}
		if(proceso.longValue()==10L)		{return "OCTUBRE";}
		if(proceso.longValue()==11L)		{return "NOVIEMBRE";}
		if(proceso.longValue()==12L)		{return "DICIEMBRE";}
		return "";
	}
	
	
	@Transient
	public String getNombreTurno() 					
	{
		if(turno==null)				{return "";}
		if(turno.longValue()==1L)		{return "MAÑANA";}
		if(turno.longValue()==2L)		{return "TARDE";}
		if(turno.longValue()==3L)		{return "NOCHE";}
		return "";
	}
	
	@Transient
	public String getNombreEstado() 					
	{
		if(estado==null)				{return "";}
		if(estado.longValue()<0L)		{return "DESHABILITADO";}
		if(estado.longValue()==1L)		{return "PENDIENTE SECCIÓN";}
		if(estado.longValue()==2L)		{return "PENDIENTE ASIGNACIÓN DOCENTE";}
		if(estado.longValue()==3L)		{return "PENDIENTE SILLABOS";}
		if(estado.longValue()==4L)		{return "PUBLICADO";}
		return "";
	}
	
}