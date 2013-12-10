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
@Table(name="planificacion.m_capacitacion")
public class Capacitacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pk_institucion;
	private Long anio;
	private Long area;
	private Long hora_duracion;
	private String tema;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Long tipo;
	private String nombre;
	private Long sexo;
	private Long condicion;
	private Long convocatoria;
	private Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_capacitacion")
	public Long getId()								 		{return id;}
	public void setId(Long id) 						 		{this.id = id;}
	
	public Long getPk_institucion() {		return pk_institucion;	}
	public void setPk_institucion(Long pk_institucion) {		this.pk_institucion = pk_institucion;	}
	
	public Long getAnio() {		return anio;	}
	public void setAnio(Long anio) {		this.anio = anio;	}
	
	public Long getArea() {		return area;	}
	public void setArea(Long area) {		this.area = area;	}
	
	public Long getHora_duracion() {		return hora_duracion;	}
	public void setHora_duracion(Long hora_duracion) {		this.hora_duracion = hora_duracion;	}
	
	public String getTema() {		return tema;	}
	public void setTema(String tema) {		this.tema = tema;	}
	
	public Date getFecha_inicio() {		return fecha_inicio;	}
	public void setFecha_inicio(Date fecha_inicio) {	this.fecha_inicio = fecha_inicio;	}
	
	public Date getFecha_fin() {		return fecha_fin;	}
	public void setFecha_fin(Date fecha_fin) {		this.fecha_fin = fecha_fin;	}
	
	public Long getTipo() {		return tipo;	}
	public void setTipo(Long tipo) {		this.tipo = tipo;	}
	
	public String getNombre() {		return nombre;	}
	public void setNombre(String nombre) {		this.nombre = nombre;	}
	
	public Long getSexo() {		return sexo;	}
	public void setSexo(Long sexo) {		this.sexo = sexo;	}
	
	public Long getCondicion() {		return condicion;	}
	public void setCondicion(Long condicion) {		this.condicion = condicion;	}
	
	public Long getConvocatoria() {		return convocatoria;	}
	public void setConvocatoria(Long convocatoria) {		this.convocatoria = convocatoria;	}
	
	public Long getEstado() {		return estado;	}
	public void setEstado(Long estado) {		this.estado = estado;	}

	@Transient
	public String getNombreCondicion() 
	{
		if(condicion.longValue()==1L)	{return "semestre1";}
		if(condicion.longValue()==2L)	{return "semestre2";}
		if(condicion.longValue()==3L)	{return "semestre3";}
		return "";
	}
	
	@Transient
	public String getNombreTipo() 
	{
		if(tipo == null) 			{return "";}
		if(tipo.longValue()==1L)	{return "tipo1";}
		if(tipo.longValue()==2L)	{return "tipo2";}
		return "";	
	}
	
	@Transient
	public String getNombreConvocatoria() 
	{
		if(convocatoria == null) 			{return "";}
		if(convocatoria.longValue()==1L)	{return "convocatoria1";}
		if(convocatoria.longValue()==2L)	{return "convocatoria2";}
		return "";	
	}
	
	@Transient
	public String getNombreArea() 
	{
		if(area == null) 			{return "";}
		if(area.longValue()==1L)	{return "area1";}
		if(area.longValue()==2L)	{return "area2";}
		return "";	
	}
	
	
}
	
