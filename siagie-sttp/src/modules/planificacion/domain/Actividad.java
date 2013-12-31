package modules.planificacion.domain;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="planificacion.m_actividad")
public class Actividad implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long    id;
	private	Long	estrategia;
	private Long	actividad;
	private	Long	tarea;
	private	String	descripcion;
	private	String	meta;
	private	String	meta_cumplimiento;
	private String  indicador;
	private Date    fecha_inicio;
	private Date    fecha_fin;
	private	Long	ejecucion_inicio;
	private	Long	ejecucion_fin;
	private	Double	costo_presupuestado;
	private	Long	costo_ejecutado;
	private	Long	duracion;
	private	Long	partida;
	private	Long	responsable;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_actividad")
	public Long getId()								 			{return id;}
	public void setId(Long id) 						 			{this.id = id;}
	
	@Column(name="pk_estrategia")
	public Long getEstrategia() 								{return estrategia;}
	public void setEstrategia(Long estrategia) 					{this.estrategia = estrategia;}
	
	@Column(name="actividad")
	public Long getActividad() 									{return actividad;}
	public void setActividad(Long actividad) 					{this.actividad = actividad;}
	
	@Column(name="tarea")
	public Long getTarea() 										{return tarea;}
	public void setTarea(Long tarea) 							{this.tarea = tarea;}
	
	@Column(name="descripcion")
	public String getDescripcion() 								{return descripcion;}
	public void setDescripcion(String descripcion) 				{this.descripcion = descripcion;}
	
	@Column(name="meta")
	public String getMeta() 									{return meta;}
	public void setMeta(String meta) 							{this.meta = meta;}
	
	@Column(name="meta_cumplimiento")
	public String getMeta_cumplimiento() 						{return meta_cumplimiento;}
	public void setMeta_cumplimiento(String meta_cumplimiento) 	{this.meta_cumplimiento = meta_cumplimiento;}
	
	@Column(name="indicador")
	public String getIndicador() 								{return indicador;}
	public void setIndicador(String indicador) 					{this.indicador = indicador;}
	
	@Column(name="fecha_inicio")
	public Date getFecha_inicio() 								{return fecha_inicio;}
	public void setFecha_inicio(Date fecha_inicio) 				{this.fecha_inicio = fecha_inicio;}
	
	@Column(name="fecha_fin")
	public Date getFecha_fin() 									{return fecha_fin;}
	public void setFecha_fin(Date fecha_fin) 					{this.fecha_fin = fecha_fin;}
	
	@Column(name="ejecucion_inicio")
	public Long getEjecucion_inicio() 							{return ejecucion_inicio;}
	public void setEjecucion_inicio(Long ejecucion_inicio) 		{this.ejecucion_inicio = ejecucion_inicio;}
	
	@Column(name="ejecucion_fin")
	public Long getEjecucion_fin() 								{return ejecucion_fin;}
	public void setEjecucion_fin(Long ejecucion_fin) 			{this.ejecucion_fin = ejecucion_fin;}
	
	@Column(name="costo_presupuestado")
	public Double getCosto_presupuestado() 						{return costo_presupuestado;}
	public void setCosto_presupuestado(Double costo_presupuestado) {this.costo_presupuestado = costo_presupuestado;}
	
	@Column(name="costo_ejecutado")
	public Long getCosto_ejecutado() 							{return costo_ejecutado;}
	public void setCosto_ejecutado(Long costo_ejecutado) 		{this.costo_ejecutado = costo_ejecutado;}
	
	@Column(name="duracion")
	public Long getDuracion() 									{return duracion;}
	public void setDuracion(Long duracion) 						{this.duracion = duracion;}
	
	@Column(name="partidad")
	public Long getPartida() 									{return partida;}
	public void setPartida(Long partida) 						{this.partida = partida;}
	
	@Column(name="responsable")
	public Long getResponsable() 								{return responsable;}
	public void setResponsable(Long responsable) 				{this.responsable = responsable;}
}
	