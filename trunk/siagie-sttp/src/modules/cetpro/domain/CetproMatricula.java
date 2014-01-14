package modules.cetpro.domain;
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
@Table(name="cetpro.m_cetpro_matricula")
public class CetproMatricula implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long 	id;
	private Long 	pk_docente;
	private Long 	pk_familia;
	private Long 	pk_modulo;
	private Long 	anno;
	private Date 	fecha_ini;
	private Date 	fecha_fin;
	private Long 	turno;
	private Long 	estado;
	
	private String apepat;
	private String apemat;
	private String nom;
	private String nomModulo;
	private String nomFamilia;
	private Long modulo;
	private Long tipoModulo;
	private String nombreDocente;
	private String nombreTurno;
	private String nombreEstado;
	private Long profesion;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cetpro_matricula")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPk_docente() {
		return pk_docente;
	}
	public void setPk_docente(Long pk_docente) {
		this.pk_docente = pk_docente;
	}
	
	public Long getAnno() {
		return anno;
	}
	public void setAnno(Long anno) {
		this.anno = anno;
	}
	public Date getFecha_ini() {
		return fecha_ini;
	}
	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public Long getTurno() {
		return turno;
	}
	public void setTurno(Long turno) {
		this.turno = turno;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	@Transient
	public String getApepat() {
		return apepat;
	}
	public void setApepat(String apepat) {
		this.apepat = apepat;
	}
	
	@Transient
	public String getApemat() {
		return apemat;
	}
	public void setApemat(String apemat) {
		this.apemat = apemat;
	}
	
	@Transient
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
		
	@Transient
	public Long getModulo() {
		return modulo;
	}
	public void setModulo(Long modulo) {
		this.modulo = modulo;
	}
	
	@Transient
	public Long getTipoModulo() {
		return tipoModulo;
	}
	public void setTipoModulo(Long tipoModulo) {
		this.tipoModulo = tipoModulo;
	}
		
	@Transient
	public String getNombreDocente() {
		return apepat+" "+apemat+" "+nom;
	}
	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}
	public Long getPk_familia() {
		return pk_familia;
	}
	public void setPk_familia(Long pk_familia) {
		this.pk_familia = pk_familia;
	}
	public Long getPk_modulo() {
		return pk_modulo;
	}
	public void setPk_modulo(Long pk_modulo) {
		this.pk_modulo = pk_modulo;
	}
	
	@Transient
	public String getNomModulo() {
		return nomModulo;
	}
	public void setNomModulo(String nomModulo) {
		this.nomModulo = nomModulo;
	}
	@Transient
	public String getNomFamilia() {
		return nomFamilia;
	}
	public void setNomFamilia(String nomFamilia) {
		this.nomFamilia = nomFamilia;
	}
	
	@Transient
	public String getNombreTurno() 					
	{
		if(turno==null)				{return "";}
		if(turno.longValue()==1L)		{return "MAÑANA";}
		if(turno.longValue()==2L)		{return "TARDE";}
		if(turno.longValue()==3L)		{return "NOCHE";}
		return "";
	}
	
	@Transient
	public String getNombreEstado() 					
	{
		if(estado==null)				{return "";}
		if(estado.longValue()<0L)		{return "DESHABILITADO";}
		if(estado.longValue()==1L)		{return "PENDIENTE CREACIÓN FECHAS";}
		if(estado.longValue()==2L)		{return "PRE-PUBLICADO";}
		if(estado.longValue()==3L && fecha_ini.compareTo(new Date())<=0) {return "EN CURSO";}	 										
		else if(estado.longValue()==3L) {return "PUBLICADO";}		
		if(estado.longValue()==5L)		{return "FINALIZADO";}
		return "";
	}
	
	@Transient
	public Long getProfesion() {
		return profesion;
	}
	public void setProfesion(Long profesion) {
		this.profesion = profesion;
	}
	
}