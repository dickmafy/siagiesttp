package dataware.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter 
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
	}


	public EncodingFilter() 
    {System.out.println("filter encoding here");}
    
	public void destroy() 
	{System.out.println("filter encoding destroy here");}
	
	public void init(FilterConfig fConfig) throws ServletException 
	{System.out.println("filter encoding start here");}
	
}