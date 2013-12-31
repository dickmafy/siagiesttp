package com.aprolab.sicad.jobs;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.aprolab.sicad.persistence.JPAPersistenceUtil;


public class MatenimientoLog extends QuartzJobBean
{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		/*
		Date fecha = arg0.getFireTime();
		System.out.println("Copia de Logs: "+fecha);
		int result = -1;
		result = JPAPersistenceUtil.getSession()
		.createSQLQuery("INSERT INTO log_h SELECT * FROM log WHERE fecha < :bandera")
		.setTimestamp("bandera", fecha).executeUpdate();
		
		System.out.println("Copia de Logs Culminada: "+result);
		
		
		result = JPAPersistenceUtil.getSession()
		.createSQLQuery("DELETE FROM log WHERE fecha < :bandera")
		.setTimestamp("bandera", fecha).executeUpdate();
		System.out.println("Eliminacion de logs Culminada: "+result);
		*/
	}

	
}
