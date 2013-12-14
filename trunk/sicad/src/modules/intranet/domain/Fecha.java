package modules.intranet.domain;

import java.util.Date;

public class Fecha {

	private Date fechaInicial;
	private Date fechaFinal;
	private String dia;
	private Long codigoDia;
	private Long estado;
	private Date fechaListada;
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Long getCodigoDia() {
		return codigoDia;
	}
	public void setCodigoDia(Long codigoDia) {
		this.codigoDia = codigoDia;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public Date getFechaListada() {
		return fechaListada;
	}
	public void setFechaListada(Date fechaListada) {
		this.fechaListada = fechaListada;
	}
	
	
	
}
