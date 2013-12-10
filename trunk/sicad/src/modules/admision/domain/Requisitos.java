package modules.admision.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="administracion.m_requisito")
public class Requisitos implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long institucion;
	private Long requisito;
	private Long modalidad;
	private Long tipo;
	
	private Boolean check;
	private String nombreTipo;
	private String nombreModalidad;
	private String nombreRequisito;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_requisitos")
	public Long getId() 									{return id;}
	public void setId(Long id)								{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion() 							{return institucion;}
	public void setInstitucion(Long institucion)		 	{this.institucion = institucion;}
	
	@Column(name="tipo")	
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="modalidad")
	public Long getModalidad() 								{return modalidad;}
	public void setModalidad(Long modalidad) 				{this.modalidad = modalidad;}
	
	@Column(name="requisito")
	public Long getRequisito() 								{return requisito;}
	public void setRequisito(Long requisito)		 		{this.requisito = requisito;}
	
	@Transient
	public String getNombreTipo() 					
	{
		if(tipo.longValue()==1L)	{return "ADMISION";}
		if(tipo.longValue()==2L)	{return "MATRICULA";}
		if(tipo.longValue()==3L)	{return "RETIRO";}
		if(tipo.longValue()==4L)	{return "TRASLADO";}
		return "";
	}
	public void setNombreTipo(String nombreTipo) 			{this.nombreTipo = nombreTipo;}
	
	@Transient
	public String getNombreModalidad() 
	{
		if(modalidad.longValue()==0L)	{return "REGULAR";}
		if(modalidad.longValue()==1L)	{return "PRIMEROS PUESTOS";}
		if(modalidad.longValue()==2L)	{return "DEPORTISTA CALIFICADO";}
		if(modalidad.longValue()==3L)	{return "PLAN INTEGRAL REPARACIONES";}
		if(modalidad.longValue()==4L)	{return "ARTISTA CALIFICADO";}
		if(modalidad.longValue()==5L)	{return "DISCAPACIDAD";}
		return "";
	}
	public void setNombreModalidad(String nombreModalidad) 	{this.nombreModalidad = nombreModalidad;}
	
	@Transient
	public String getNombreRequisito() 						{return nombreRequisito;}
	public void setNombreRequisito(String nombreRequisito) 	{this.nombreRequisito = nombreRequisito;}
	
	@Transient
	public Boolean getCheck() 								{return check;}
	public void setCheck(Boolean check) 					{this.check = check;}
}
