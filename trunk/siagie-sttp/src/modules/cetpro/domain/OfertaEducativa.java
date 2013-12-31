package modules.cetpro.domain;
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

import modules.administracion.domain.Personal;

@Entity
@Table(name="cetpro.m_oferta")
public class OfertaEducativa implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	pk_oferta;
	private Long 	pk_institucion;
	private Long 	tipo;
	private Long 	modulo;
	private Long 	responsable;
	private String 	titulo;
	private String 	descripcion;
	private Long 	duracion;
	private Long 	semanas;
	private String 	fundamentacion;
	private String 	marco_legal;
	private Long 	participantes;
	private Double 	costos;
	private Date 	fecha_inicio;
	private Date 	fecha_termino;
	private Date 	vigencia_inicio;
	private Date 	vigencia_termino;
	private Long 	resolucion;
	private Date 	registro_fecha;
	private	Long	registro_usuario;
	private Long 	estado;
	
	private	Personal	beanResponsable;
	private String 		nombreEstado;
	private String 		nombreTipo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_oferta")
	public Long getPk_oferta() 											{return pk_oferta;}
	public void setPk_oferta(Long pk_oferta) 							{this.pk_oferta = pk_oferta;}
	
	@Column(name="pk_institucion")
	public Long getPk_institucion() 									{return pk_institucion;}
	public void setPk_institucion(Long pk_institucion) 					{this.pk_institucion = pk_institucion;}
	
	@Column(name="tipo")
	public Long getTipo() 												{return tipo;}
	public void setTipo(Long tipo) 										{this.tipo = tipo;}
	
	@Column(name="modulo")
	public Long getModulo() 											{return modulo;}
	public void setModulo(Long modulo) 									{this.modulo = modulo;}
	
	@Column(name="responsable")
	public Long getResponsable() 										{return responsable;}
	public void setResponsable(Long responsable) 						{this.responsable = responsable;}
	
	@Column(name="titulo")
	public String getTitulo() 											{return titulo;}
	public void setTitulo(String titulo) 								{this.titulo = titulo;}
	
	@Column(name="descripcion")
	public String getDescripcion() 										{return descripcion;}
	public void setDescripcion(String descripcion) 						{this.descripcion = descripcion;}
	
	@Column(name="duracion")
	public Long getDuracion() 											{return duracion;}
	public void setDuracion(Long duracion) 								{this.duracion = duracion;}
	
	@Column(name="semanas")
	public Long getSemanas() 											{return semanas;}
	public void setSemanas(Long semanas) 								{this.semanas = semanas;}
	
	@Column(name="fundamentacion")
	public String getFundamentacion() 									{return fundamentacion;}
	public void setFundamentacion(String fundamentacion) 				{this.fundamentacion = fundamentacion;}
	
	@Column(name="marco_legal")
	public String getMarco_legal() 										{return marco_legal;}
	public void setMarco_legal(String marco_legal) 						{this.marco_legal = marco_legal;}
	
	@Column(name="participantes")
	public Long getParticipantes() 										{return participantes;}
	public void setParticipantes(Long participantes) 					{this.participantes = participantes;}
	
	@Column(name="costos")
	public Double getCostos() 											{return costos;}
	public void setCostos(Double costos) 								{this.costos = costos;}
	
	@Column(name="fecha_inicio")
	public Date getFecha_inicio() 										{return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio) 						{this.fecha_inicio = fecha_inicio;}
	
	@Column(name="fecha_termino")
	public Date getFecha_termino() 										{return fecha_termino;}
	public void setFecha_termino(Date fecha_termino) 					{this.fecha_termino = fecha_termino;}
	
	@Column(name="vigencia_inicio")
	public Date getVigencia_inicio() 									{return vigencia_inicio;}
	public void setVigencia_inicio(Date vigencia_inicio) 				{this.vigencia_inicio = vigencia_inicio;}
	
	@Column(name="vigencia_termino")
	public Date getVigencia_termino() 									{return vigencia_termino;}
	public void setVigencia_termino(Date vigencia_termino) 				{this.vigencia_termino = vigencia_termino;}
	
	@Column(name="resolucion")
	public Long getResolucion() 										{return resolucion;}
	public void setResolucion(Long resolucion) 							{this.resolucion = resolucion;}
	
	@Column(name="registro_fecha")
	public Date getRegistro_fecha() 									{return registro_fecha;}
	public void setRegistro_fecha(Date registro_fecha) 					{this.registro_fecha = registro_fecha;}
	
	@Column(name="registro_usuario")
	public Long getRegistro_usuario() 									{return registro_usuario;}
	public void setRegistro_usuario(Long registro_usuario) 				{this.registro_usuario = registro_usuario;}
	
	@Column(name="estado")
	public Long getEstado() 											{return estado;}
	public void setEstado(Long estado) 									{this.estado = estado;}
	
	@Transient
	public String getNombreEstado() 									{
		if(estado.longValue()==0L)										{return "Rechazado";}
		if(estado.longValue()==1L)										{return "Pendiente";}
		if(estado.longValue()==2L)										{return "Enviado";}
		if(estado.longValue()==3L)										{return "Aprobado";}
		return "(No Definido)";
		}
	public void setNombreEstado(String nombreEstado) 					{this.nombreEstado = nombreEstado;}
	
	@Transient
	public String getNombreTipo() 										{
		if(tipo.longValue()==1L)													{return "Módulo Ocupacional";}
		if(tipo.longValue()==2L)													{return "Capacitación";}
		if(tipo.longValue()==3L)													{return "Actualización";}
		return "(No Definido)";
		}
	public void setNombreTipo(String nombreTipo) 						{this.nombreTipo = nombreTipo;}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="responsable",  insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.PROXY)
	public Personal getBeanResponsable() 								{return beanResponsable;}
	public void setBeanResponsable(Personal beanResponsable) 			{this.beanResponsable = beanResponsable;}
	

	
	
}