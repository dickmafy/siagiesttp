package modules.seguridad.domain;
import java.io.Serializable;
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
import org.hibernate.bytecode.javassist.FieldHandled;
import org.hibernate.bytecode.javassist.FieldHandler;

import com.belogick.factory.util.helper.DateHelper;

import java.util.Date;

@Entity
@Table(name="seguridad.usuario")
public class Usuario implements Serializable, FieldHandled
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long perfil;
	private String usuario;	
	private String contrasena;
	private Long pertenencia;
	private Date creacion;
	private Date modificacion;
	private Date acceso;
	private Long institucion;
	private String nombres;
	private String correo;
	private Long estado;
	
	private String nombrePerfil;
	private String nombreUsuario;
	private	Long   estadoPerfil;
	private String ultimoAcceso;
	
	private Perfil beanPerfil;
	private FieldHandler fieldHandler;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_usuario")
	public Long getId()                                    	{return id;}
	public void setId(Long id)                           	{this.id=id;}
	
	@Column(name="pk_perfil")
	public Long getPerfil()                                 {return perfil;}
	public void setPerfil(Long perfil)                      {this.perfil=perfil;}

	@Column(name="usuario")
	public String getUsuario()               				{return usuario;}
	public void setUsuario(String usuario)                  {this.usuario=usuario;}

	@Column(name="contrasena")
	public String getContrasena()         					{return contrasena;}
	public void setContrasena(String contrasena)            {this.contrasena=contrasena;}

	@Column(name="creacion")
	public Date getCreacion() 								{return creacion;}
	public void setCreacion(Date creacion) 					{this.creacion = creacion;}
	
	@Column(name="modificacion")
	public Date getModificacion() 							{return modificacion;}
	public void setModificacion(Date modificacion) 			{this.modificacion = modificacion;}
	
	@Column(name="acceso")
	public Date getAcceso()                                 {return acceso;}
	public void setAcceso(Date acceso)                      {this.acceso=acceso;}

	@Column(name="institucion")
	public Long getInstitucion() 							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="pertenencia")
	public Long getPertenencia() 							{return pertenencia;}
	public void setPertenencia(Long pertenencia) 			{this.pertenencia = pertenencia;}
	
	@Column(name="nombres")
	public String getNombres() 								{return nombres;}
	public void setNombres(String nombres) 					{this.nombres = nombres;}
	
	@Column(name="correo_electronico")
	public String getCorreo() 								{return correo;}
	public void setCorreo(String correo) 					{this.correo = correo;}
	
	@Column(name="estado")
	public Long getEstado()                                 {return estado;}
	public void setEstado(Long estado)                    	{this.estado=estado;}
	
	@Transient
	public Long getEstadoPerfil() 							{return estadoPerfil;}
	public void setEstadoPerfil(Long estadoPerfil) 			{this.estadoPerfil = estadoPerfil;}
	
	@Transient
	public String getNombrePerfil() 						{return nombrePerfil;}
	public void setNombrePerfil(String nombrePerfil) 		{this.nombrePerfil = nombrePerfil;}
	
	@Transient
	public String getNombreUsuario() 						{return nombreUsuario;}
	public void setNombreUsuario(String nombreUsuario) 		{this.nombreUsuario = nombreUsuario;}
	
	@Transient
	public String getUltimoAcceso() 						
	{
		if(acceso!=null)		{return DateHelper.getDateFormat("dd/MM/yyyy HH:mm",acceso);}
		return "";
	}
	public void setUltimoAcceso(String ultimoAcceso) 		{this.ultimoAcceso = ultimoAcceso;}	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="pk_perfil",  insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.PROXY)
	public Perfil getBeanPerfil()									 
	{if (fieldHandler != null) 	{return (Perfil) fieldHandler.readObject(this, "beanPerfil", beanPerfil);} 	return beanPerfil;}
	 
	public void setBeanPerfil(Perfil beanPerfil) 
	{if (fieldHandler != null)	{this.beanPerfil = (Perfil) fieldHandler.writeObject(this, "beanPerfil", this.beanPerfil, beanPerfil);  return;}	this.beanPerfil = beanPerfil;}
	
	@Transient
	public FieldHandler getFieldHandler() 									{return fieldHandler;}
	public void setFieldHandler(FieldHandler fieldHandler)					{this.fieldHandler = fieldHandler;}
}