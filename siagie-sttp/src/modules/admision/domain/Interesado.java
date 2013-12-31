package modules.admision.domain;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name="admision.m_interesado")
public class Interesado implements Serializable
{
	private static final long serialVersionUID = 1L;
	private	Long 	id;
	private Long 	persona;
	private Long 	institucion;
	private Date 	fecha;
	private Long 	medio;
	private Long 	turno;
	private String 	horario;
	private	String	otros;
	private Long 	estado;
	
	private	String	nombreMedio;
	private	String	nombreTurno;	
	private Persona beanPersona;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_interesado")
	public Long getId() 							{return id;}
	public void setId(Long id)						{this.id = id;}
	
	@Column(name="persona")
	public Long getPersona() 						{return persona;}
	public void setPersona(Long persona) 			{this.persona = persona;}
	
	@Column(name="institucion")
	public Long getInstitucion() 					{return institucion;}
	public void setInstitucion(Long institucion) 	{this.institucion = institucion;}
	
	@Column(name="fecha")
	public Date getFecha() 							{return fecha;}
	public void setFecha(Date fecha) 				{this.fecha = fecha;}

	@Column(name="medio")
	public Long getMedio()							{return medio;}
	public void setMedio(Long medio) 				{this.medio = medio;}
	
	@Column(name="turno")
	public Long getTurno()							{return turno;}
	public void setTurno(Long turno) 				{this.turno = turno;}
	
	@Column(name="horario")
	public String getHorario()						{return horario;}
	public void setHorario(String horario) 			{this.horario = horario;}
	
	@Column(name="otros")
	public String getOtros() 						{return otros;}
	public void setOtros(String otros) 				{this.otros = otros;}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}
	
	@Transient
	public String getNombreMedio() 					
	{
		if(medio.longValue()==1L)	{return "Radio";}
		if(medio.longValue()==2L)	{return "Televisión";}
		if(medio.longValue()==3L)	{return "Periodico";}
		if(medio.longValue()==4L)	{return "Feria de Colegio";}
		if(medio.longValue()==5L)	{return "Recomendación";}
		if(medio.longValue()==6L)	{return "Otros Medios";}
		return "";
	}
	public void setNombreMedio(String nombreMedio) 	{this.nombreMedio = nombreMedio;}
	
	@Transient
	public String getNombreTurno() 					
	{
		if(turno.longValue()==1L)	{return "Mañana";}
		if(turno.longValue()==2L)	{return "Tarde";}
		if(turno.longValue()==3L)	{return "Noche";}
		return "";
	}
	public void setNombreTurno(String nombreTurno) 	{this.nombreTurno = nombreTurno;}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="persona",  insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.PROXY)
	public Persona getBeanPersona() 					{return beanPersona;}
	public void setBeanPersona(Persona beanPersona) 	{this.beanPersona = beanPersona;}
}