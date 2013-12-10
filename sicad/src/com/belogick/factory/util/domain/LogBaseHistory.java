package com.belogick.factory.util.domain;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="log_h")
public class LogBaseHistory implements Serializable 
{
	private static final long serialVersionUID = -3514757868619163337L;
	private Long 	id;
	private String 	usuario;
	private String 	sistema;
	private String 	modulo;
	private String 	operacion;
	private Date 	fecha;
	private String 	host;
	private String 	ip_local;	
	private String 	ip_publica;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_log", insertable=true)	
	public Long getId()								{return id;}
	public void setId(Long id)						{this.id=id;}
	
	@Column(name="usuario")	
	public String getUsuario() 						{return usuario;}
	public void setUsuario(String usuario) 			{this.usuario = usuario;}
	
	@Column(name="sistema")
	public String getSistema() 						{return sistema;}
	public void setSistema(String sistema) 			{this.sistema = sistema;}
	
	@Column(name="modulo")
	public String getModulo() 						{return modulo;}
	public void setModulo(String modulo) 			{this.modulo = modulo;}
	
	@Column(name="operacion")
	public String getOperacion() 					{return operacion;}
	public void setOperacion(String operacion)	 	{this.operacion = operacion;}
	
	@Column(name="fecha")
	public Date getFecha() 							{return fecha;}
	public void setFecha(Date fecha) 				{this.fecha = fecha;}
		
	@Column(name="host")
	public String getHost() 						{return host;}
	public void setHost(String host) 				{this.host = host;}
	
	@Column(name="ip_privada")
	public String getIp_local() 					{return ip_local;}
	public void setIp_local(String ip_local) 		{this.ip_local = ip_local;}
	
	@Column(name="ip_publica")
	public String getIp_publica() 					{return ip_publica;}
	public void setIp_publica(String ip_publica) 	{this.ip_publica = ip_publica;}	
}
