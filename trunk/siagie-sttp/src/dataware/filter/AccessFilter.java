package dataware.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.helper.StringHelper;

public class AccessFilter implements Filter 
{

	private static String URL_ACCESO_DENEGADO = "/index.jsp";
	private static String[] URL_ACCESO = new String[] {"/index.jsp","/login.jsf","/pages/login.jsf"};
	private StringHelper stringHelper = new StringHelper();
	
    /**
     * Default constructor. 
     */
    public AccessFilter() 
    {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() 
	{
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = ((HttpServletRequest) request).getSession();
		if(findStringInArray(httpRequest.getServletPath(), URL_ACCESO))
		{
			chain.doFilter(request, response);
			return;
		}
		if (session == null || session.getAttribute("") == null)
		{httpResponse.sendRedirect(httpRequest.getContextPath() + URL_ACCESO[0]);}
		else
		{chain.doFilter(request, response);}
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
	// TODO Auto-generated method stub
	}
	
	public boolean findStringInArray(String cadenaABuscar, String[] cadenas) {
		boolean respuesta = false;
		if (cadenas != null) {
			for (int i = 0; i < cadenas.length; i++) {
				if (cadenas[i] != null && !cadenas[i].trim().equals("")
						&& cadenas[i].trim().equals(cadenaABuscar)) {
					respuesta = true;
					break;
				}
			}
		}
		return respuesta;
	}
}