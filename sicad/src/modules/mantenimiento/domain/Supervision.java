package modules.mantenimiento.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mantenimiento.m_supervision")
public class Supervision implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long nivel;
	private Long subnivel;
	private String ruc;
	private String nombre;
	private String descripcion;
	private String correo;
	private String web;
	private Long ubigeo;
	private String direccion;
	private String referencia;
	private String telefonos;
	private String fax;
	private Long estado;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_supervision")
	public Long getId() 							{return id;	}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="nombre")
	public String getNombre() 						{return nombre;}
	public void setNombre(String nombre) 			{this.nombre = nombre;}
	
	@Column(name="descripcion")
	public String getDescripcion() 					{return descripcion;}
	public void setDescripcion(String descripcion) 	{this.descripcion = descripcion;}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}
	
	@Column(name="direccion")
	public String getDireccion() 					{return direccion;}
	public void setDireccion(String direccion) 		{this.direccion = direccion;}

	@Column(name="referencia")
	public String getReferencia() 					{return referencia;}
	public void setReferencia(String referencia) 	{this.referencia = referencia;}

	@Column(name="ubigeo")
	public Long getUbigeo() 						{return ubigeo;}
	public void setUbigeo(Long ubigeo) 				{this.ubigeo = ubigeo;}

	@Column(name="telefonos")
	public String getTelefonos() 					{return telefonos;}
	public void setTelefonos(String telefonos) 		{this.telefonos = telefonos;}

	@Column(name="fax")
	public String getFax() 							{return fax;}
	public void setFax(String fax) 					{this.fax = fax;}

	@Column(name="web")
	public String getWeb() 							{return web;}
	public void setWeb(String web) 					{this.web = web;}

	@Column(name="correo")
	public String getCorreo() 						{return correo;}
	public void setCorreo(String correo) 			{this.correo = correo;}
	
	@Column(name="nivel")
	public Long getNivel() 							{return nivel;}
	public void setNivel(Long nivel) 				{this.nivel = nivel;}
	
	@Column(name="subnivel")
	public Long getSubnivel() 						{return subnivel;}
	public void setSubnivel(Long subnivel) 			{this.subnivel = subnivel;}
	
	@Column(name="ruc")
	public String getRuc() 							{return ruc;}
	public void setRuc(String ruc) 					{this.ruc = ruc;}
	
	
}