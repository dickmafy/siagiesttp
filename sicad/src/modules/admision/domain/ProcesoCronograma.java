package modules.admision.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.belogick.factory.util.helper.DateHelper;

@Entity
@Table(name="admision.m_proceso_cronograma")
public class ProcesoCronograma implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long pk_proceso;
	private Long pk_actividad;
	private Date fecha_inicio;
	private Date fecha_fin;
	
	private String nombreActividad;
	
	@Id
	@Column(name="pk_proceso")
	public Long getPk_proceso() 					{return pk_proceso;}
	public void setPk_proceso(Long pk_proceso) 		{this.pk_proceso = pk_proceso;}
	
	@Column(name="pk_actividad")
	public Long getPk_actividad() 					{return pk_actividad;}
	public void setPk_actividad(Long pk_actividad) 	{this.pk_actividad = pk_actividad;}
	
	@Column(name="fecha_inicio")
	public Date getFecha_inicio() 					{return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio) 	{this.fecha_inicio = fecha_inicio;}
	
	@Column(name="fecha_fin")
	public Date getFecha_fin() 						{return fecha_fin;}
	public void setFecha_fin(Date fecha_fin) 		{this.fecha_fin = fecha_fin;}	
	
	@Transient
	public String getNombreActividad() 						
	{
		if(pk_actividad.longValue()==1L)	{return "Venta de Prospectos de Admision";}
		if(pk_actividad.longValue()==2L)	{return "Inscripción al Proceso de Admisión";}
		if(pk_actividad.longValue()==3L)	{return "Publicación de Postulantes Regulares - Aptos";}
		if(pk_actividad.longValue()==4L)	{return "Publicación de Postulantes Exonerados en Todas sus Modalidades - Aptos";}
		if(pk_actividad.longValue()==5L)	{return "Examen de Admisión Regular";}
		if(pk_actividad.longValue()==6L)	{return "Examen de Admisión Otras Modalidades";}
		if(pk_actividad.longValue()==7L)	{return "Publicación de Resultados y Cuadros de Merito";}
		if(pk_actividad.longValue()==8L)	{return "Matricula Regular";}
		if(pk_actividad.longValue()==9L)	{return "Matricula Extemporanea";}
		return "";
	}
	public void setNombreActividad(String nombreActividad) 	{this.nombreActividad = nombreActividad;}
	
	@Transient
	public boolean validateActividad()
	{
		if(fecha_fin==null && fecha_inicio==null) 				{return false;}
		if(fecha_fin==null && fecha_inicio!=null)				{return false;}
		if(fecha_fin!=null && fecha_inicio==null)				{return false;}
		if(DateHelper.difDateDay(fecha_inicio, fecha_fin)>0)	{return false;}
		return true;
	}
	
	@Transient
	public ProcesoCronograma setBean(Long actividad)
	{
		ProcesoCronograma obj=new ProcesoCronograma();
		obj.pk_actividad=actividad;
		return obj;
	}
}