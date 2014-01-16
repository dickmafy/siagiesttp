package modules.intranet.servicio;

import modules.cetpro.domain.CetproCt;
import modules.horario.domain.SilaboUnidadCt;
import modules.marco.domain.ReferenteEducativo;

public class VistaReferenteEducativo {
	
	SilaboUnidadCt ctSilaboUnidadCt;
	ReferenteEducativo referenteEducativo;
	CetproCt cetproCt;
	
	public VistaReferenteEducativo(SilaboUnidadCt ctSilaboUnidadCt,ReferenteEducativo referenteEducativo,CetproCt cetproCt) {
		this.ctSilaboUnidadCt = ctSilaboUnidadCt;
		this.referenteEducativo = referenteEducativo;
		this.cetproCt = cetproCt;
	}
	
	
	public SilaboUnidadCt getCtSilaboUnidadCt() {
		return ctSilaboUnidadCt;
	}
	public void setCtSilaboUnidadCt(SilaboUnidadCt ctSilaboUnidadCt) {
		this.ctSilaboUnidadCt = ctSilaboUnidadCt;
	}
	public ReferenteEducativo getReferenteEducativo() {
		return referenteEducativo;
	}
	public void setReferenteEducativo(ReferenteEducativo referenteEducativo) {
		this.referenteEducativo = referenteEducativo;
	}


	public CetproCt getCetproCt() {
		return cetproCt;
	}


	public void setCetproCt(CetproCt cetproCt) {
		this.cetproCt = cetproCt;
	}
	
	
}
