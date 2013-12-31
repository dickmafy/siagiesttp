package modules.admision.domain;
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
@Table(name="admision.m_persona")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long documento_tipo;
	private String documento_nro;
	private String nombres;
	private String apellido_paterno;
	private String apellido_materno;
	private Long genero;
	private Long estado_civil;
	private Long nacimiento_lugar;
	private Date nacimiento_fecha;
	private String  correo;
	private String telefonos;
	private Long celular;
	private Long ubigeo;
	private String direccion;
	private String referencia;
	private Long colegio_tipo;
	private String colegio;
	private Long estado;
	
	private Long apox_vinculo;
	private String apox_nombres;
	private String apox_dni;
	private Long apoy_vinculo;
	private String apoy_nombres;
	private String apoy_dni;
	
	private String 	nombreCompleto;
	private	String	nombreDocumento;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_persona")
	public Long getId() 										{return id;}
	public void setId(Long id) 									{this.id = id;}
	
	@Column(name="documento_tipo")
	public Long getDocumento_tipo() 							{return documento_tipo;}	
	public void setDocumento_tipo(Long documento_tipo) 			{this.documento_tipo = documento_tipo;}
	
	@Column(name="documento_nro")
	public String getDocumento_nro() 							{return documento_nro;}
	public void setDocumento_nro(String documento_nro) 			{this.documento_nro = documento_nro;}
	
	@Column(name="nombres")
	public String getNombres() 									{return nombres;}
	public void setNombres(String nombres) 						{this.nombres = nombres;}
	
	@Column(name="apellido_paterno")
	public String getApellido_paterno() 						{return apellido_paterno;}
	public void setApellido_paterno(String apellido_paterno) 	{this.apellido_paterno = apellido_paterno;}
	
	@Column(name="apellido_materno")
	public String getApellido_materno() 						{return apellido_materno;}
	public void setApellido_materno(String apellido_materno) 	{this.apellido_materno = apellido_materno;}
	
	@Column(name="genero")
	public Long getGenero() 									{return genero;}
	public void setGenero(Long genero) 							{this.genero = genero;}
	
	@Column(name="estado_civil")
	public Long getEstado_civil() 								{return estado_civil;}
	public void setEstado_civil(Long estado_civil) 				{this.estado_civil = estado_civil;}
	
	@Column(name="nacimiento_lugar")
	public Long getNacimiento_lugar() 							{return nacimiento_lugar;}
	public void setNacimiento_lugar(Long nacimiento_lugar) 		{this.nacimiento_lugar = nacimiento_lugar;}
	
	@Column(name="nacimiento_fecha")
	public Date getNacimiento_fecha() 							{return nacimiento_fecha;}
	public void setNacimiento_fecha(Date nacimiento_fecha) 		{this.nacimiento_fecha = nacimiento_fecha;}
	
	@Column(name="correo")
	public String getCorreo() 									{return correo;}
	public void setCorreo(String correo) 						{this.correo = correo;}
	
	@Column(name="telefonos")
	public String getTelefonos() 								{return telefonos;}
	public void setTelefonos(String telefonos) 					{this.telefonos = telefonos;}
	
	@Column(name="celular")
	public Long getCelular() 									{return celular;}
	public void setCelular(Long celular) 						{this.celular = celular;}
	
	@Column(name="ubigeo")
	public Long getUbigeo() 									{return ubigeo;}
	public void setUbigeo(Long ubigeo) 							{this.ubigeo = ubigeo;}
	
	@Column(name="direccion")
	public String getDireccion() 								{return direccion;}
	public void setDireccion(String direccion) 					{this.direccion = direccion;}
	
	@Column(name="referencia")
	public String getReferencia() 								{return referencia;}
	public void setReferencia(String referencia) 				{this.referencia = referencia;}
	
	@Column(name="colegio_tipo")
	public Long getColegio_tipo() 								{return colegio_tipo;}
	public void setColegio_tipo(Long colegio_tipo) 				{this.colegio_tipo = colegio_tipo;}
	
	@Column(name="colegio")
	public String getColegio() 									{return colegio;}
	public void setColegio(String colegio) 						{this.colegio = colegio;}
	
	@Column(name="estado")
	public Long getEstado() 									{return estado;}
	public void setEstado(Long estado) 							{this.estado = estado;}
	
	@Column(name="apoderadox_vinculo")
	public Long getApox_vinculo() 								{return apox_vinculo;}
	public void setApox_vinculo(Long apox_vinculo) 				{this.apox_vinculo = apox_vinculo;}
	
	@Column(name="apoderadox_nombres")
	public String getApox_nombres() 							{return apox_nombres;}
	public void setApox_nombres(String apox_nombres) 			{this.apox_nombres = apox_nombres;}
	
	@Column(name="apoderadox_dni")
	public String getApox_dni() 								{return apox_dni;}
	public void setApox_dni(String apox_dni) 					{this.apox_dni = apox_dni;}
	
	@Column(name="apoderadoy_vinculo")
	public Long getApoy_vinculo() 								{return apoy_vinculo;}
	public void setApoy_vinculo(Long apoy_vinculo) 				{this.apoy_vinculo = apoy_vinculo;}
	
	@Column(name="apoderadoy_nombres")
	public String getApoy_nombres() 							{return apoy_nombres;}
	public void setApoy_nombres(String apoy_nombres) 			{this.apoy_nombres = apoy_nombres;}
	
	@Column(name="apoderadoy_dni")
	public String getApoy_dni() 								{return apoy_dni;}
	public void setApoy_dni(String apoy_dni) 					{this.apoy_dni = apoy_dni;}
	
	@Transient
	public String getNombreCompleto() 							{return nombres+" "+apellido_paterno+" "+apellido_materno;}
	public void setNombreCompleto(String nombreCompleto)		{this.nombreCompleto = nombreCompleto;}
	
	@Transient
	public String getNombreDocumento() 							
	{
		if(documento_tipo.longValue()==1L)		{return "DNI "+documento_nro;}
		if(documento_tipo.longValue()==2L)		{return "PARTIDA "+documento_nro;}
		if(documento_tipo.longValue()==3L)		{return "CE "+documento_nro;}
		if(documento_tipo.longValue()==4L)		{return "PASAPORTE "+documento_nro;}
		return "";
	}
	public void setNombreDocumento(String nombreDocumento) 		{this.nombreDocumento = nombreDocumento;}
}

