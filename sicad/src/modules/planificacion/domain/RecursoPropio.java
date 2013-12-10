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
@Table(name="planificacion.m_recurso")
public class RecursoPropio implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	pk_institucion;
	private Long	anio;
	private	Long	concepto;
	private	String	descripcion;
	private String 	arrendante_contratante;
	private Long 	periodicidad;
	private String	 merced_ingreso;
	private Date 	fecha_inicio;
	private Date 	fecha_fin;
	private	Long	estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_recurso")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}

	public Long getPk_institucion() {		return pk_institucion;	}
	public void setPk_institucion(Long pk_institucion) {		this.pk_institucion = pk_institucion;	}
	
	public Long getAnio() {		return anio;	}
	public void setAnio(Long anio) {		this.anio = anio;	}
	

	public Long getEstado() {		return estado;	}
	public void setEstado(Long estado) {		this.estado = estado;	}
	
	public Long getConcepto() {		return concepto;	}
	public void setConcepto(Long concepto) {		this.concepto = concepto;	}

	public String getDescripcion() {		return descripcion;	}
	public void setDescripcion(String descripcion) {		this.descripcion = descripcion;	}
	
	public String getArrendante_contratante() {		return arrendante_contratante;	}
	public void setArrendante_contratante(String arrendante_contratante) {		this.arrendante_contratante = arrendante_contratante;	}
	
	public Long getPeriodicidad() {		return periodicidad;	}
	public void setPeriodicidad(Long periodicidad) {		this.periodicidad = periodicidad;	}
	
	public String getMerced_ingreso() {		return merced_ingreso;	}
	public void setMerced_ingreso(String merced_ingreso) {		this.merced_ingreso = merced_ingreso;	}
	
	public Date getFecha_inicio() {		return fecha_inicio;	}
	public void setFecha_inicio(Date fecha_inicio) {		this.fecha_inicio = fecha_inicio;	}
	
	public Date getFecha_fin() {		return fecha_fin;	}
	public void setFecha_fin(Date fecha_fin) {		this.fecha_fin = fecha_fin;	}
	
	
	@Transient
	public String getNombreConcepto() 
	{
		if(concepto.longValue()==1L)	{return "concepto1";}
		if(concepto.longValue()==2L)	{return "concepto2";}
		if(concepto.longValue()==3L)	{return "concepto3";}
		return "";
	}
	
	@Transient
	public String getNombrePeriodicidad() 
	{
		if(periodicidad.longValue()==1L)	{return "periodicidad1";}
		if(periodicidad.longValue()==2L)	{return "periodicidad2";}
		if(periodicidad.longValue()==3L)	{return "periodicidad3";}
		return "";
	}
	
	
	
}
	