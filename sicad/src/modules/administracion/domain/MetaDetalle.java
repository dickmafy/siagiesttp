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
@Table(name="administracion.m_meta_detalle")
public class MetaDetalle implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long 	id;
	private	Long	meta;
	private	Long	tipo;
	private	Long	unidad;
	private	Long	ingresantes;
	private	Long	regular;
	private	Long	rezagados;
	private	Long	retiros;
	private	Long	traslados;
	private	Long	metaTotal;
	private	Long	metaAvance;
	private	Long	resolucion;
	private	Long	solicitud;
	private	Long	permiso;
	private	Long	cantidad;
	private	Long	estado;
	
	private String nombrePermiso;
	private String nombreEstado;
	private String nombreUnidad;
	private String nombreModulo;
	private	Long	valorModulo;
	private	Long	valorHoras;
	private	String	nombreProfesion;
	  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_detalle")
	public Long getId()    		                    		{return id;}
	public void setId(Long id)  		               	 	{this.id = id;}
	
	@Column(name="pk_meta")
	public Long getMeta() 									{return meta;}
	public void setMeta(Long meta) 							{this.meta = meta;}
	
	@Column(name="pk_tipo")
	public Long getTipo() 									{return tipo;}
	public void setTipo(Long tipo) 							{this.tipo = tipo;}
	
	@Column(name="pk_unidad")
	public Long getUnidad() 								{return unidad;}
	public void setUnidad(Long unidad) 						{this.unidad = unidad;}
	
	@Column(name="ingresantes")
	public Long getIngresantes() 							{return ingresantes;}
	public void setIngresantes(Long ingresantes) 			{this.ingresantes = ingresantes;}
	
	@Column(name="regular")
	public Long getRegular() 								{return regular;}
	public void setRegular(Long regular) 					{this.regular = regular;}
	
	@Column(name="rezagados")
	public Long getRezagados() 								{return rezagados;}
	public void setRezagados(Long rezagados) 				{this.rezagados = rezagados;}
	
	@Column(name="retiros")
	public Long getRetiros() 								{return retiros;}
	public void setRetiros(Long retiros) 					{this.retiros = retiros;}
	
	@Column(name="traslados")
	public Long getTraslados() 								{return traslados;}
	public void setTraslados(Long traslados) 				{this.traslados = traslados;}
	
	@Column(name="meta_total")
	public Long getMetaTotal() 								{return metaTotal;}
	public void setMetaTotal(Long metaTotal) 				{this.metaTotal = metaTotal;}
	
	@Column(name="meta_avance")
	public Long getMetaAvance() 							{return metaAvance;}
	public void setMetaAvance(Long metaAvance) 				{this.metaAvance = metaAvance;}
	
	@Column(name="resolucion")
	public Long getResolucion() 							{return resolucion;}
	public void setResolucion(Long resolucion) 				{this.resolucion = resolucion;}
	
	@Column(name="solicitud")
	public Long getSolicitud() 								{return solicitud;}
	public void setSolicitud(Long solicitud) 				{this.solicitud = solicitud;}
	
	@Column(name="permiso")
	public Long getPermiso() 								{return permiso;}
	public void setPermiso(Long permiso) 					{this.permiso = permiso;}
	
	@Column(name="cantidad")
	public Long getCantidad() 								{return cantidad;}
	public void setCantidad(Long cantidad) 					{this.cantidad = cantidad;}
	
	@Column(name="estado")
	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}
	
	@Transient
	public String getNombreUnidad() 						{return nombreUnidad;}
	public void setNombreUnidad(String nombreUnidad) 		{this.nombreUnidad = nombreUnidad;}
	
	@Transient
	public String getNombreModulo() 						{return nombreModulo;}
	public void setNombreModulo(String nombreModulo) 		{this.nombreModulo = nombreModulo;}
	
	@Transient
	public Long getValorModulo() 							{return valorModulo;}
	public void setValorModulo(Long valorModulo) 			{this.valorModulo = valorModulo;}
	
	@Transient
	public String getNombrePermiso() 						
	{
		if(permiso==null)				{return "";}
		if(permiso.longValue()==1L)		{return "NORMAL";}
		if(permiso.longValue()==2L)		{return "DESDOBLAMIENTO";}
		return "";
	}
	public void setNombrePermiso(String nombrePermiso) 		{this.nombrePermiso = nombrePermiso;}
	
	@Transient
	public String getNombreEstado() 					
	{
		if(estado==null)				{return "";}
		if(estado.longValue()<0L)		{return "DESHABILITADO";}
		if(estado.longValue()==1L)		{return "PENDIENTE";}
		if(estado.longValue()==2L)		{return "PRE-PUBLICADO";}
		if(estado.longValue()==3L)		{return "PUBLICADO";}
		return "";
	}
	public void setNombreEstado(String nombreEstado) 		{this.nombreEstado = nombreEstado;}
	
	@Transient
	public Long getValorHoras() 							{return valorHoras;}
	public void setValorHoras(Long valorHoras) 				{this.valorHoras = valorHoras;}
	
	@Transient
	public String getNombreProfesion() 						{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 	{this.nombreProfesion = nombreProfesion;}
	
	
}