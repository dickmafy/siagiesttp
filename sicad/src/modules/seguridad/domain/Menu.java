package modules.seguridad.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="seguridad.menu")
public class Menu implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long sistema;
	private Long modulo;
	private Long menu;
	private String titulo;
	private String descripcion;
	private String accion;
	private String metodo;
	private Long estado;

	private	Boolean selected;
	
	@Id
	@Column(name="pk_menu")
	public Long getId()                        		   	{return id;}
	public void setId(Long id)                   			{this.id=id;}

	@Column(name="sistema")
	public Long getSistema() 							{return sistema;}
	public void setSistema(Long sistema) 				{this.sistema = sistema;}
	
	@Column(name="modulo")
	public Long getModulo() 							{return modulo;}
	public void setModulo(Long modulo) 					{this.modulo = modulo;}
	
	@Column(name="menu")
	public Long getMenu() 								{return menu;}
	public void setMenu(Long menu) 						{this.menu = menu;}

	@Column(name="titulo")
	public String getTitulo()                       	{return titulo;}
	public void setTitulo(String titulo)            	{this.titulo=titulo;}

	@Column(name="descripcion")
	public String getDescripcion()                  	{return descripcion;}
	public void setDescripcion(String descripcion)  	{this.descripcion=descripcion;}

	@Column(name="accion")
	public String getAccion() 							{return accion;}
	public void setAccion(String accion) 				{this.accion = accion;}
	
	@Column(name="metodo")
	public String getMetodo()                       	{return metodo;}
	public void setMetodo(String metodo)            	{this.metodo=metodo;}

	@Column(name="estado")
	public Long getEstado()                         	{return estado;}
	public void setEstado(Long estado)              	{this.estado=estado;}
	
	@Transient
	public Boolean getSelected() 						{return selected;}
	public void setSelected(Boolean selected) 			{this.selected = selected;}
}