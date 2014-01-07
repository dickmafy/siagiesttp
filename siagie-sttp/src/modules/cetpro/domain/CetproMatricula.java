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

	private Long 	pk_cetpro_matricula;
	private Long 	pk_docente;
	private Long 	pk_unidad;
	private Long 	anno;
	private Date 	fecha_ini;
	private Date 	fecha_fin;
	private Long 	turno;
	private Long 	estado;
	
	private String apepat;
	private String apemat;
	private String nom;
	private String nomUnidad;
	private Long modulo;
	private Long tipoModulo;
	private String nombreDocente;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_cetpro_matricula")
	public Long getPk_cetpro_matricula() {
		return pk_cetpro_matricula;
	}
	public void setPk_cetpro_matricula(Long pk_cetpro_matricula) {
		this.pk_cetpro_matricula = pk_cetpro_matricula;
	}
	public Long getPk_docente() {
		return pk_docente;
	}
	public void setPk_docente(Long pk_docente) {
		this.pk_docente = pk_docente;
	}
	public Long getPk_unidad() {
		return pk_unidad;
	}
	public void setPk_unidad(Long pk_unidad) {
		this.pk_unidad = pk_unidad;
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
	public String getNomUnidad() {
		return nomUnidad;
	}
	public void setNomUnidad(String nomUnidad) {
		this.nomUnidad = nomUnidad;
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
	

}