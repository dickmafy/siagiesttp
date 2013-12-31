package modules.seguridad.domain;
import java.io.Serializable;
import java.util.List;

public class VariableAcceso implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<Variable> variables;
		
	public List<Variable> getVariables() 				{return variables;}
	public void setVariables(List<Variable> variables) 	{this.variables = variables;}

	public Variable getVariable(Long codigo)
	{
		for(int i=0; i<variables.size(); i++)
		{
			if((variables.get(i).getCodigo()).longValue()==codigo.longValue())
			{return variables.get(i);}
		}
		return null;
	}
	
	public String getValor(Long codigo)
	{
		for(int i=0; i<variables.size(); i++)
		{
			if((variables.get(i).getCodigo()).longValue()==codigo.longValue())
			{return variables.get(i).getValor();}
		}
		return null;
	}
	
	public int getValorInteger(Long codigo)
	{return Integer.parseInt(getValor(codigo));}
	
	public Long getValorLong(Long codigo)
	{return Long.valueOf(getValor(codigo));}
}