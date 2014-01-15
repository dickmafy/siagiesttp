package modules.administracion.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="administracion.m_institucion")
public class Institucion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long formacion;
	private Long resolcre;
	private Long resolren;
	private Long resolrev;
	private String codigo;
	private String nombre;
	private String ruc;
	private Long gestion;
	private String correo;
	private String web;
	private Long supervision;
	private String mision;
	private String vision;
	private Long estado;
	
	private String nombreFormacion;
	private String nombreGestion;
	private String nombreCompleto;
    
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pk_institucion")
	public Long getId()                        		{return id;}
	public void setId(Long id)                 	 	{this.id = id;}
	
	@Column(name="codigo")
	public String getCodigo()                   	{return codigo;}
	public void setCodigo(String codigo)        	{this.codigo = codigo;}
	
	@Column(name="nombre")
	public String getNombre()                   	{return nombre;}
	public void setNombre(String nombre)        	{this.nombre = nombre;}
	
	@Column(name="gestion")
	public Long getGestion()                  	 	{return gestion;}
	public void setGestion(Long gestion)      	  	{this.gestion = gestion;}
	
	@Column(name="ruc")
	public String getRuc()                    	 	{return ruc;}
	public void setRuc(String ruc)             	   	{this.ruc = ruc;}

	@Column(name="formacion")
	public Long getFormacion()                	  	{return formacion;}
	public void setFormacion(Long formacion)  	  	{this.formacion = formacion;}
	
	@Column(name="resolucion_creacion")
	public Long getResolcre() 						{return resolcre;}
	public void setResolcre(Long resolcre) 			{this.resolcre = resolcre;}
	
	@Column(name="resolucion_renovacion")
	public Long getResolren() 						{return resolren;}
	public void setResolren(Long resolren) 			{this.resolren = resolren;}
	
	@Column(name="resolucion_revalidado")
	public Long getResolrev() 						{return resolrev;}
	public void setResolrev(Long resolrev) 			{this.resolrev = resolrev;}
	
	@Column(name="supervision")
	public Long getSupervision() 					{return supervision;}	
	public void setSupervision(Long supervision) 	{this.supervision = supervision;}
	
	@Column(name="web")
	public String getWeb()                  		{return web;}
	public void setWeb(String web)           		{this.web = web;}
	
	@Column(name="correo")
	public String getCorreo() 						{return correo;}
	public void setCorreo(String correo) 			{this.correo = correo;}
	
	@Column(name="mision")
	public String getMision() 						{return mision;}
	public void setMision(String mision) 			{this.mision = mision;}
	
	@Column(name="vision")
	public String getVision() 						{return vision;}
	public void setVision(String vision) 			{this.vision = vision;}
	
	@Column(name="estado")
	public Long getEstado()                  		{return estado;}
	public void setEstado(Long estado)       		{this.estado = estado;}
	
	@Transient
	public String getNombreFormacion() 						
	{
		if(formacion==null)				return "";
		if(formacion.longValue()==1L)	return "IEST";
		if(formacion.longValue()==2L)	return "CETPRO BASICO";
		if(formacion.longValue()==3L)	return "CETPRO INTERMEDIO";
		return "";
	}
	public void setNombreFormacion(String nombreFormacion) 		{this.nombreFormacion = nombreFormacion;}
	
	@Transient
	public String getNombreGestion() 
	{
		if(gestion==null)			return "";
		if(gestion.longValue()==1L)	return "PUBLICA";
		if(gestion.longValue()==2L)	return "PRIVADA";
		if(gestion.longValue()==3L)	return "POR CONVENIO";
		return "";
	}
	public void setNombreGestion(String nombreGestion)			{this.nombreGestion = nombreGestion;}
	
	@Transient
	public String getNombreCompleto() 							{return getNombreFormacion()+" \""+getNombre()+"\"";}
	public void setNombreCompleto(String nombreCompleto)		{this.nombreCompleto = nombreCompleto;}
}