package modules.mantenimiento.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mantenimiento.m_empresa")
public class Empresa implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long   id;
	private Long tipo;
	private String ruc;
	private String razon_social;
	private Long negocio;
	private Long sociedad;
	private Long ubigeo;
	private String direccion;
	private String referencia;
	private String correo;
	private String web;
	private String telefonos;
	private Long   estado;
	private String nombrenegocio;
	private String nombreTipo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_empresa")
	public Long getId() 							{return id;	}
	public void setId(Long id) 						{this.id = id;}
	
	@Column(name="tipo")
	public Long getTipo() 							{return tipo;}
	public void setTipo(Long tipo) 					{this.tipo = tipo;}
	
	@Column(name="ruc")
	public String getRuc() 							{return ruc;}
	public void setRuc(String ruc) 					{this.ruc = ruc;}
	
	@Column(name="razon_social")
	public String getRazon_social() 				 {return razon_social;}
	public void setRazon_social(String razon_social) {this.razon_social = razon_social;}
	
	@Column(name="negocio")
	public Long getNegocio() 						 {return negocio;}
	public void setNegocio(Long negocio) 			 {this.negocio = negocio;}
	
	@Column(name="sociedad")
	public Long getSociedad() 						 {return sociedad;}
	public void setSociedad(Long sociedad) 			 {this.sociedad = sociedad;}
	
	@Column(name="ubigeo")
	public Long getUbigeo() 						 {return ubigeo;}
	public void setUbigeo(Long ubigeo) 				 {this.ubigeo = ubigeo;}
	
	@Column(name="direccion")
	public String getDireccion() 					 {return direccion;}
	public void setDireccion(String direccion)       {this.direccion = direccion;}
	
	@Column(name="referencia")
	public String getReferencia() 					{return referencia;}
	public void setReferencia(String referencia)    {this.referencia = referencia;}
	
	@Column(name="correo")
	public String getCorreo() 						{return correo;}
	public void setCorreo(String correo) 			{this.correo = correo;}
	
	@Column(name="web")
	public String getWeb() 							{return web;}
	public void setWeb(String web) 					{this.web = web;}
	
	@Column(name="telefonos")
	public String getTelefonos() 					{return telefonos;}
	public void setTelefonos(String telefonos) 		{this.telefonos = telefonos;}
	
	@Column(name="estado")
	public Long getEstado() 						{return estado;}
	public void setEstado(Long estado) 				{this.estado = estado;}
	
	@Transient
	public String getNombrenegocio() {
		if(negocio==1L) return "Educación";
		if(negocio==2L) return "Tecnología";
		return "";
	}
	public void setNombrenegocio(String nombrenegocio) 	{this.nombrenegocio = nombrenegocio;}
	
	@Transient
	public String getNombreTipo() {
		if(tipo==null) return ""; 
		if(tipo==1L) return "S.A.C.";
		if(tipo==2L) return "S.A.A.";
		if(tipo==3L) return "E.I.R.L.";
		if(tipo==4L) return "S.R.L.";
		if(tipo==5L) return "S.A.";
		return "";
	}
	public void setNombreTipo(String nombreTipo) 		{this.nombreTipo = nombreTipo;}
	
}