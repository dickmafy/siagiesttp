package modules.admision.domain;

import java.util.List;

import javax.faces.model.ListDataModel;

import modules.admision.domain.ProcesoOferta;

import org.primefaces.model.SelectableDataModel;

public class ProfesionDataModel extends ListDataModel<ProcesoOferta> implements SelectableDataModel<ProcesoOferta> 
{
	
	public ProfesionDataModel() {  
    }  
  
    public ProfesionDataModel(List<ProcesoOferta> data) {  
        super(data);  
    }  
	
	@Override
	public ProcesoOferta getRowData(String rowKey) {
		 	List<ProcesoOferta> cars = (List<ProcesoOferta>) getWrappedData();  
         
	        for(ProcesoOferta car : cars) {  
	            if(car.getId().equals(rowKey))  
	               return car;  
	        }  
	          
	        return null; 
	}
	@Override
	public Object getRowKey(ProcesoOferta oferta) {
		return oferta.getId();  
	}
	
	
}