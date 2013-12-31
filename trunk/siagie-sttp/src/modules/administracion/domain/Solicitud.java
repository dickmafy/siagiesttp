package modules.administracion.domain;
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
@Table(name="administracion.m_solicitud")
public class Solicitud implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long institucion;
	private Date fecha;
	private Long registrante;
	private Long tipo;
	private Long motivo;
	private String descripcion;
	private String resolucion;
	private Long estado;
	private Date fecha_inicio;
	private Date fecha_termino;
	private String nombreMotivo;
	private String observacion;
	

	private String nombreTipo;
	private String nombreRegistrante;
	private String nombreEstado;
	private String nombreInstitucion;
	private String fechas;
	private String mostrarEstado;
	
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_solicitud")
	public Long getId()                            									{return id;}
	public void setId(Long id)                     									{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion()													{return institucion;}
	public void setInstitucion(Long institucion) 									{this.institucion = institucion;}
	
	@Column(name="tipo")
	public Long getTipo()                          									{return tipo;}
	public void setTipo(Long tipo)                 									{this.tipo = tipo;}
	
	@Column(name="fecha")
	public Date getFecha() 															{return fecha;}
	public void setFecha(Date fecha) 												{this.fecha = fecha;}
	
	@Column(name="registrante")
	public Long getRegistrante() 													{return registrante;}
	public void setRegistrante(Long registrante) 									{this.registrante = registrante;}
	
	@Column(name="motivo")
	public Long getMotivo() 														{return motivo;	}
	public void setMotivo(Long motivo) 												{this.motivo = motivo;	}
	
	@Column(name="descripcion")
	public String getDescripcion()				 									{return descripcion;}
	public void setDescripcion(String descripcion) 									{this.descripcion = descripcion;}
	
	@Column(name="resolucion")
	public String getResolucion() 													{return resolucion;	}
	public void setResolucion(String resolucion) 									{this.resolucion = resolucion;}
		
	
	@Column(name="estado")
	public Long getEstado()															{return estado;}
	public void setEstado(Long estado)												{this.estado = estado;}

	@Column(name="fecha_inicio")
	public Date getFecha_inicio() 													{return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio) 									{this.fecha_inicio = fecha_inicio;}
	
	@Column(name="fecha_termino")
	public Date getFecha_termino() 													{return fecha_termino;}
	public void setFecha_termino(Date fecha_termino) 								{this.fecha_termino = fecha_termino;}
	
	@Column(name="observaciones")
	public String getObservacion() 													{return observacion;}
	public void setObservacion(String observacion) 									{this.observacion = observacion;}
	
	@Transient
	public String getNombreTipo() 
	{
		if(tipo.longValue()==1L)													{return "Actualizar Datos del Director";}
		if(tipo.longValue()==2L)													{return "Modificar el Nombre de la Institución";}
		if(tipo.longValue()==3L)													{return "Modificar Dirección del Local Principal";}
		if(tipo.longValue()==4L)													{return "Modificar Oferta Educativa de la Institución";}
		if(tipo.longValue()==5L)													{return "Modificar el Número de vacantes por especialidad";}
		if(tipo.longValue()==6L)													{return "Cambiar Cronograma de Procesos de Admisión";}
		if(tipo.longValue()==7L)													{return "Renovación del Director";}
		if(tipo.longValue()==8L)													{return "Agregar Nuevo Convenio";}
		if(tipo.longValue()==9L)													{return "Agregar Nuevo Local/Ambientes";}
		if(tipo.longValue()==10L)													{return "Agregar Nueva Opcion de Solicitud";}
		return "(No Definido)";
	}
	
	public void setNombreTipo(String nombretipo) 									{this.nombreTipo = nombretipo;}
	
	@Transient
	public String getNombreRegistrante()											{return nombreRegistrante;}
	public void setNombreRegistrante(String nombreregistrante) 						{this.nombreRegistrante = nombreregistrante;}
	
	@Transient
	public String getNombreEstado() 												
	{
		if(estado.longValue()==0L)							{return "Anulado";}
		if(estado.longValue()==1L)							{return "Pendiente de Envìo";}
		if(estado.longValue()==2L)							{return "Enviado";}
		if(estado.longValue()==3L)							{return "Pendiente de Ejecuciòn";}
		if(estado.longValue()==4L)							{return "Rechazado";}
		if(estado.longValue()==5L)							{return "Ejecutado";}
		return "(No Definido)";
	}
	public void setNombreEstado(String nombreEstado) 							{this.nombreEstado = nombreEstado;}
	
	@Transient
	public String getNombreMotivo() 
	{
		if(motivo.longValue()==0L)							{return "Formato Incorrecto";}
		if(motivo.longValue()==1L)							{return "Pedido diferente a la solicitud";}
		if(motivo.longValue()==2L)							{return "Fuera de fecha";}
		if(motivo.longValue()==3L)							{return "Falta sello";}
		return "(No Definido)";
	}
	public void setNombreMotivo(String nombreMotivo) 		{this.nombreMotivo = nombreMotivo;}
	
	@Transient
	public String getNombreInstitucion() 										{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreinstitucion) 					{this.nombreInstitucion = nombreinstitucion;}
	
	@Transient
	public String getFechas() 													{return fechas;}
	public void setFechas(String fechas) 										{this.fechas = fechas;}
	
	
}