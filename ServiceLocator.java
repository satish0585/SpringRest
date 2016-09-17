package com.cg.esol.ivu.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author cwaghuld
 * @version 1.0
 */
public class ServiceLocator {
	
	/**
	 * <p> Static Object of ServiceLocator Class
	 */
	private static ServiceLocator serviceLocator = new ServiceLocator();
	
	/**
	 * WebApplicationContext reference
	 */
	private WebApplicationContext springWebContext = null;
	
	/**
	 * <p>
	 * Private Constructor used to initialize 
	 */
	private ServiceLocator(){
		
		ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance()
						.getExternalContext().getContext();		
		springWebContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
	/**
	 * <p>
	 * Acts as object factory for this class 
	 * 
	 * 
	 * @return ServiceLocator object
	 */
	public static ServiceLocator getInstance(){
		if(serviceLocator == null){
			return serviceLocator;
		}
		return serviceLocator;
	}
	
	/**
	 * <p>
	 * Return located spring bean object.
	 * 
	 * @param beanName
	 * @return Object
	 */
	public Object getBean(String beanName){
		
		return springWebContext.getBean(beanName);
		
	}
	
}
