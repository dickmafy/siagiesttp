package modules.intranet.servicio;

import modules.horario.domain.SilaboUnidadCt;
import modules.marco.domain.ReferenteEducativo;

public class VistaReferenteEducativo {
	
	SilaboUnidadCt ctSilaboUnidadCt;
	ReferenteEducativo referenteEducativo;
	
	
	public VistaReferenteEducativo(SilaboUnidadCt ctSilaboUnidadCt,
			ReferenteEducativo referenteEducativo) {
		this.ctSilaboUnidadCt = ctSilaboUnidadCt;
		this.referenteEducativo = referenteEducativo;
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
	
	
}
