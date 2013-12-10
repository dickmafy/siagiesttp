package modules.planificacion.domain;
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
@Table(name="planificacion.m_documentos")
public class Documento implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	institucion;
	private Long	tipo;
	private	String	observaciones;
	private	Date	fecha;
	private	Long	estado;
	private String 	nombreTipoDocumento;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_documento")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion() 							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="tipo")
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="observaciones")
	public String getObservaciones() 						{return observaciones;}
	public void setObservaciones(String observaciones) 		{this.observaciones = observaciones;}
	
	@Column(name="fecha")
	public Date getFecha() 									{return fecha;}
	public void setFecha(Date fecha) 						{this.fecha = fecha;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}

	@Transient
	public String getNombreTipoDocumento() 
	{
		
		if(tipo == null) 			{return "";}
		if(tipo.longValue()==1L)	{return "Manual de Organización y Funciones (MOF)";}
		if(tipo.longValue()==2L)	{return "Reglamento de Organización y Funciones (ROF)";}
		if(tipo.longValue()==3L)	{return "Cuadro de Asignación de Personal (CAP)";}
		if(tipo.longValue()==4L)	{return "Texto Unico de Procedimientos Administrativos (TUPA)";}
		if(tipo.longValue()==5L)	{return "Ogranigrama";}
		if(tipo.longValue()==6L)	{return "Informe de Gestión Anual";}
		if(tipo.longValue()==7L)	{return "Cuadro de Horas";}
		return "";	
	}
	
	
}