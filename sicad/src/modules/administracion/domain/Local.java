package modules.administracion.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="administracion.m_local")
public class Local implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long institucion;
	private Boolean principal;
	private Long encargado;
	private String nombre;
	private Long ubigeo;
	private String direccion; 
	private String referencia;
	private String telefonos;
	private String latitud;
	private String longitud;
	private Date vigenciaInicio;
	private Date vigenciaFin;
	private Long capacidad;
	private Long estado;
	private Long resolucion;
	private Long solicitud;
	
	private Personal beanPersonal;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_local")
	public Long getId()                             	{return id;}
	public void setId(Long id)                      	{this.id = id;}
	
	@Column(name="encargado")
	public Long getEncargado()                      	{return encargado;}
	public void setEncargado(Long encargado)        	{this.encargado = encargado;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion()						{return institucion;}
	public void setInstitucion(Long institucion) 		{this.institucion = institucion;}
	
	@Column(name="nombre")
	public String getNombre()                       	{return nombre;}
	public void setNombre(String nombre)            	{this.nombre = nombre;}
		
	@Column(name="ubigeo")
	public Long getUbigeo()                         	{return ubigeo;}
	public void setUbigeo(Long ubigeo)              	{this.ubigeo = ubigeo;}
	
	@Column(name="direccion")
	public String getDireccion()                    	{return direccion;}
	public void setDireccion(String direccion)      	{this.direccion = direccion;}
	
	@Column(name="referencia")
	public String getReferencia()                   	{return referencia;}
	public void setReferencia(String referencia)    	{this.referencia = referencia;}
	
	@Column(name="telefonos")
	public String getTelefonos()                   		{return telefonos;}
	public void setTelefonos(String telefonos)      	{this.telefonos = telefonos;}
	
	@Column(name="estado")
	public Long getEstado()                        	 	{return estado;}
	public void setEstado(Long estado)             		{this.estado = estado;}
	
	@Column(name="principal")
	public Boolean getPrincipal() 						{return principal;}
	public void setPrincipal(Boolean principal) 		{this.principal = principal;}
	
	@Column(name="latitud")
	public String getLatitud() 							{return latitud;}
	public void setLatitud(String latitud) 				{this.latitud = latitud;}
	
	@Column(name="longitud")
	public String getLongitud() 						{return longitud;}
	public void setLongitud(String longitud) 			{this.longitud = longitud;}
	
	@Column(name="capacidad")
	public Long getCapacidad() 							{return capacidad;}
	public void setCapacidad(Long capacidad) 			{this.capacidad = capacidad;}
	
	@Column(name="vigencia_inicio")
	public Date getVigenciaInicio() 					{return vigenciaInicio;}
	public void setVigenciaInicio(Date vigenciaInicio) 	{this.vigenciaInicio = vigenciaInicio;}
	
	@Column(name="vigencia_fin")
	public Date getVigenciaFin() 						{return vigenciaFin;}
	public void setVigenciaFin(Date vigenciaFin) 		{this.vigenciaFin = vigenciaFin;}
	
	@Column(name="resolucion")
	public Long getResolucion() 							{return resolucion;}
	public void setResolucion(Long resolucion) 				{this.resolucion = resolucion;}
	
	@Column(name="solicitud")
	public Long getSolicitud() 								{return solicitud;}
	public void setSolicitud(Long solicitud) 				{this.solicitud = solicitud;}
	
	
	@ManyToOne 
	@JoinColumn(name="encargado",  insertable=false, updatable=false) 
	public Personal getBeanPersonal() 					{return beanPersonal;}
	public void setBeanPersonal(Personal beanPersonal) 	{this.beanPersonal = beanPersonal;}
}