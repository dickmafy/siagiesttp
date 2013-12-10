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
@Table(name="administracion.m_convenio")
public class Convenio implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long institucion;
	private Long empresa;
    private String nombre;
    private Long modalidad;
    private Long alcance;
    private Long tipo;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String beneficio;
    private String compromiso;
    private String responsable;
    private Long estado;
    private String nombreAlcance;
    private String nombreTipo;
    private String nombreModalidad;
    private String nombreInstitucion;
    private Long solicitud;
    private Boolean existFile;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_convenio")	
	public Long getId()					               	{return id;}	
	public void setId(Long id) 			               	{this.id = id;}
	
	@Column(name="nombre")	
	public String getNombre()           	           	{return nombre;}
	public void setNombre(String nombre)    	       	{this.nombre = nombre;}
	
	@Column(name="modalidad")	
	public Long getModalidad()                  	   	{return modalidad;}
	public void setModalidad(Long modalidad)     	   	{this.modalidad = modalidad;}
	
	@Column(name="alcance")	
	public Long getAlcance()                        	{return alcance;}
	public void setAlcance(Long alcance)          		{this.alcance = alcance;}
	
	@Column(name="tipo")	
	public Long getTipo()                           	{return tipo;}
	public void setTipo(Long tipo)		                {this.tipo = tipo;}
	
	@Column(name="fecha_inicio")	
	public Date getFecha_inicio()       	            {return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio)	    {this.fecha_inicio = fecha_inicio;}
	
	@Column(name="fecha_fin")	
	public Date getFecha_fin()  	                    {return fecha_fin;}
	public void setFecha_fin(Date fecha_fin)            {this.fecha_fin = fecha_fin;}
	
	@Column(name="beneficios")	
	public String getBeneficio()                 	    {return beneficio;}
	public void setBeneficio(String beneficio)      	{this.beneficio = beneficio;}
	
	@Column(name="compromisos")	
	public String getCompromiso()                     	{return compromiso;}
	public void setCompromiso(String compromiso)      	{this.compromiso = compromiso;}

	@Column(name="estado")	
	public Long getEstado()                            	{return estado;}
	public void setEstado(Long estado)                 	{this.estado = estado;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion() 						{return institucion;}
	public void setInstitucion(Long institucion) 		{this.institucion = institucion;}
	
	@Column(name="pk_empresa")
	public Long getEmpresa() 							{return empresa;}
	public void setEmpresa(Long empresa) 				{this.empresa = empresa;}
	
	@Column(name="responsables")
	public String getResponsable() 						{return responsable;}
	public void setResponsable(String responsable) 		{this.responsable = responsable;}
	
	@Column(name="solicitud")
	public Long getSolicitud() 							{return solicitud;}
	public void setSolicitud(Long solicitud) 			{this.solicitud = solicitud;}
	
	@Transient
	public String getNombreAlcance() {
		if(alcance==1L) return "NACIONAL";
		if(alcance==2L) return "REGIONAL";
		if(alcance==3L) return "LOCAL";
		if(alcance==4L) return "INTERNACIONAL";
		return "";
		
	}
	public void setNombreAlcance(String nombreAlcance)    		{this.nombreAlcance = nombreAlcance;}
	
	@Transient
	public String getNombreTipo() {
		if(tipo==1L) return "PRIVADO";
		if(tipo==2L) return "PÚBLICO";
		return "";
	}
	public void setNombreTipo(String nombreTipo)          		{this.nombreTipo = nombreTipo;}
	
	@Transient
	public String getNombreModalidad() {
		if(modalidad==1L) return "EDUCATIVA";
		if(modalidad==2L) return "EMPRESARIAL";
		if(modalidad==3L) return "IGLESIA";
		return "";
	}
	public void setNombreModalidad(String nombreModalidad) 		{this.nombreModalidad = nombreModalidad;}
	
	@Transient
	public String getNombreInstitucion() 						{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 	{this.nombreInstitucion = nombreInstitucion;}
	
	@Transient
	public Boolean getExistFile() 						{return existFile;}
	public void setExistFile(Boolean existFile) 		{this.existFile = existFile;}

}