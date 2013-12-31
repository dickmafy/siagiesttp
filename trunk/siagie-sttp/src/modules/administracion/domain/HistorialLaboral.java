package modules.administracion.domain;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="administracion.m_historial_laboral")
public class HistorialLaboral implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long   	id;
	private Long 	personal;
	private	Long	tipo;
	private	Long	institucion;
	private	String	funcion;
	private Long	puesto;
	private Date	inicio;
	private	Date	fin;
	private	Long	resolucion;
	private	Long	estado;
	
	private String 	tiempo;
	private String 	nombreInstitucion;
	private String 	nombrePuesto;
	private	String	nombreTipo;
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_laboral")
	public Long getId()                                     {return id;}
	public void setId(Long id)                              {this.id = id;}
	
	@Column(name="pk_personal")
	public Long getPersonal() 								{return personal;}
	public void setPersonal(Long personal) 					{this.personal = personal;}
	
	@Column(name="institucion")
	public Long getInstitucion()							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="tipo")	
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="funcion")
	public String getFuncion() 								{return funcion;}
	public void setFuncion(String funcion) 					{this.funcion = funcion;}
	
	@Column(name="puesto")
	public Long getPuesto() 								{return puesto;}
	public void setPuesto(Long puesto) 						{this.puesto = puesto;}
	
	@Column(name="fecha_inicio")
	public Date getInicio() 								{return inicio;}
	public void setInicio(Date inicio) 						{this.inicio = inicio;}
	
	@Column(name="fecha_fin")
	public Date getFin() 									{return fin;}
	public void setFin(Date fin) 							{this.fin = fin;}
	
	@Column(name="resolucion")
	public Long getResolucion() 							{return resolucion;}
	public void setResolucion(Long resolucion) 				{this.resolucion = resolucion;}
	
	@Column(name="estado")
	public Long getEstado()                                 {return estado;}
	public void setEstado(Long estado)                      {this.estado = estado;}
	
	@Transient
	public String getTiempo() 								
	{
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(inicio);
		cal2.setTime(fin);
		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();
		long diff = milis2 - milis1;
		long dias = diff / (24 * 60 * 60 * 1000);
		long meses=dias/30;
		long anios=meses/12;
		if(anios>0)	
		{meses=anios*12 - meses;}
		cal1=null;
		cal2=null;
		tiempo="";
		if(anios==1)	{tiempo="1 año ";}
		if(anios>1)		{tiempo=anios+" años ";}
		if(meses==1)	{tiempo=tiempo+"1 mes";}
		if(meses>1)		{tiempo=tiempo+""+meses+" meses";}
		
		return tiempo;
	}
	public void setTiempo(String tiempo) 					{this.tiempo = tiempo;}
	
	@Transient
	public String getNombreInstitucion() 							{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 		{this.nombreInstitucion = nombreInstitucion;}
	
	@Transient
	public String getNombrePuesto() 								{return nombrePuesto;}
	public void setNombrePuesto(String nombrePuesto) 				{this.nombrePuesto = nombrePuesto;}
	
	@Transient
	public String getNombreTipo() 									
	{
		if(tipo.longValue()==1L)	{return "ISTP";}
		if(tipo.longValue()==2L)	{return "CETPRO";}
		if(tipo.longValue()==3L)	{return "DRE";}
		if(tipo.longValue()==4L)	{return "UGEL";}
		return "";
	}
	public void setNombreTipo(String nombreTipo) 					{this.nombreTipo = nombreTipo;}
	
}