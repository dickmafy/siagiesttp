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
@Table(name="administracion.m_oferta")
public class Oferta implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	
	private Long institucion;
	private Long profesion;
	private Date vigenciaInicio;
	private Date vigenciaFin;	
	private Long resolucion;
	private Long solicitud;
	
	private String nombreProfesion;
	private Boolean check;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_oferta")
	public Long getId()    		                    		{return id;}
	public void setId(Long id)  		               	 	{this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion()							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="pk_profesion")	
	public Long getProfesion() 								{return profesion;}
	public void setProfesion(Long profesion) 				{this.profesion = profesion;}
	
	@Column(name="vigencia_inicio")
	public Date getVigenciaInicio() 						{return vigenciaInicio;}
	public void setVigenciaInicio(Date vigenciaInicio)	 	{this.vigenciaInicio = vigenciaInicio;}
	
	@Column(name="vigencia_fin")
	public Date getVigenciaFin() 							{return vigenciaFin;}
	public void setVigenciaFin(Date vigenciaFin) 			{this.vigenciaFin = vigenciaFin;}
	
	@Column(name="resolucion")
	public Long getResolucion() 							{return resolucion;}
	public void setResolucion(Long resolucion) 				{this.resolucion = resolucion;}
	
	@Column(name="solicitud")
	public Long getSolicitud() 								{return solicitud;}
	public void setSolicitud(Long solicitud) 				{this.solicitud = solicitud;}
	
	@Transient
	public Boolean getCheck() 								{return check;}
	public void setCheck(Boolean check) 					{this.check = check;}
	
	@Transient
	public String getNombreProfesion() 						{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 	{this.nombreProfesion = nombreProfesion;}
	
}