package com.aprolab.sicad.persistence;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.aprolab.sicad.utils.AppKeys;
import com.belogick.factory.util.dao.impl.GenericDaoJpa;


public class JPAPersistenceUtil{
	private static ApplicationContext context;
	public static GenericDaoJpa getGenericDaoJpaWebBean(){
		
		ServletContext sctx = (ServletContext)FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		WebApplicationContext wactx = WebApplicationContextUtils.getWebApplicationContext(sctx);
		return (GenericDaoJpa)wactx.getBean(AppKeys.GENERIC_DAO_JPA_BEAN_NAME);
			
		
	}
	
	public static Session getWebSession(){
		return (Session)getGenericDaoJpaWebBean().getJpaTemplate()
	    		 .getEntityManagerFactory()
	    		 .createEntityManager()
	    		 .getDelegate();
	}
	
	public static GenericDaoJpa getGenericDaoJpaBean(){
		
		if (context==null) {
			context = 
				new ClassPathXmlApplicationContext(
						new String[] {"/META-INF/applicationContext-hibernate.xml",
	              "/META-INF/applicationContext-dao.xml"});
		}
		return (GenericDaoJpa)context.getBean(AppKeys.GENERIC_DAO_JPA_BEAN_NAME);
			
		
	}
	
	public static Session getSession(){
		return (Session)getGenericDaoJpaBean().getJpaTemplate()
	    		 .getEntityManagerFactory()
	    		 .createEntityManager()
	    		 .getDelegate();
	}
}
