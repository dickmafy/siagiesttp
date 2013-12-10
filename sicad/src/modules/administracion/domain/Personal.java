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
import javax.persistence.Transient;

import modules.mantenimiento.domain.Puesto;

@Entity
@Table(name="administracion.m_personal")
public class Personal implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long   	id;
	private String 	codigo;
	private String 	dni;
	private	Long	puesto;
	private String 	nombres;
	private String 	apepat;
	private String 	apemat;
	private Date 	fecnac;
	private Boolean	sexo;
	private Long   	ubigeo;
	private String 	direccion;
	private String 	referencia;
	private String 	telefono_fijo;
	private String 	telefono_celular;
	private String 	correo;
	private Long   	estado;
	private Long   	institucion;
	private Long	condicion;
	private	String	funcion;
	
	private String 	nombreCompleto;
	private Puesto 	beanPuesto;
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_personal")
	public Long getId()                                     {return id;}
	public void setId(Long id)                              {this.id = id;}
	
	@Column(name="pk_institucion")
	public Long getInstitucion()							{return institucion;}
	public void setInstitucion(Long institucion) 			{this.institucion = institucion;}
	
	@Column(name="codigo")
	public String getCodigo()                               {return codigo;}
	public void setCodigo(String codigo)                    {this.codigo = codigo;}
	
	@Column(name="dni")
	public String getDni()                                  {return dni;}
	public void setDni(String dni)                          {this.dni = dni;}
	
	@Column(name="puesto")
	public Long getPuesto() 								{return puesto;}
	public void setPuesto(Long puesto) 						{this.puesto = puesto;}
	
	@Column(name="nombres")
	public String getNombres()                              {return nombres;}
	public void setNombres(String nombres)                  {this.nombres = nombres;}
	
	@Column(name="apellido_paterno")
	public String getApepat() 								{return apepat;}
	public void setApepat(String apepat) 					{this.apepat = apepat;}
	
	@Column(name="apellido_materno")
	public String getApemat() 								{return apemat;}
	public void setApemat(String apemat) 					{this.apemat = apemat;}
	
	@Column(name="fecha_nacimiento")
	public Date getFecnac() 								{return fecnac;}
	public void setFecnac(Date fecnac) 						{this.fecnac = fecnac;}
	
	@Column(name="sexo")
	public Boolean getSexo()                                {return sexo;}	
	public void setSexo(Boolean sexo)                       {this.sexo = sexo;}
	
	@Column(name="ubigeo")
	public Long getUbigeo()                                 {return ubigeo;}
	public void setUbigeo(Long ubigeo)                      {this.ubigeo = ubigeo;}
	
	@Column(name="direccion")
	public String getDireccion()                            {return direccion;}
	public void setDireccion(String direccion)              {this.direccion = direccion;}
	
	@Column(name="referencia")
	public String getReferencia()                           {return referencia;}
	public void setReferencia(String referencia)            {this.referencia = referencia;}
	
	@Column(name="telefono_fijo")
	public String getTelefono_fijo()                        {return telefono_fijo;}
	public void setTelefono_fijo(String telefono_fijo)      {this.telefono_fijo = telefono_fijo;}
	
	@Column(name="telefono_celular")
	public String getTelefono_celular()                     {return telefono_celular;}
	public void setTelefono_celular(String telefono_celular){this.telefono_celular = telefono_celular;}
	
	@Column(name="correo")
	public String getCorreo()                               {return correo;}
	public void setCorreo(String correo)                    {this.correo = correo;}
	
	@Column(name="estado")
	public Long getEstado()                                 {return estado;}
	public void setEstado(Long estado)                      {this.estado = estado;}
	
	@Column(name="condicion")
	public Long getCondicion() 								{return condicion;}
	public void setCondicion(Long condicion) 				{this.condicion = condicion;}
	
	@Column(name="funcion")
	public String getFuncion() 								{return funcion;}
	public void setFuncion(String funcion) 					{this.funcion = funcion;}
	
	@Transient
	public String getNombreCompleto() 						{return nombres+" "+apepat+" "+apemat;	}
	public void setNombreCompleto(String nombreCompleto) 	{this.nombreCompleto = nombreCompleto;}
	
	@ManyToOne 
	@JoinColumn(name="puesto",  insertable=false, updatable=false) 
	public Puesto getBeanPuesto() 							{return beanPuesto;}
	public void setBeanPuesto(Puesto beanPuesto) 			{this.beanPuesto = beanPuesto;}
}