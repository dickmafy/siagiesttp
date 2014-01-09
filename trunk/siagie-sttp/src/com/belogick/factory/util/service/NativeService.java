package com.belogick.factory.util.service;

import com.belogick.factory.util.support.ServiceException;
import java.sql.Connection;
import java.util.List;
import org.hibernate.Query;

public abstract interface NativeService
{
  public abstract Connection getConnection()
    throws Exception;

  public abstract Query createQuery(String paramString)
    throws ServiceException;

  public abstract void executeQueryUpdate(String paramString)
    throws ServiceException;

  public abstract List executeQueryList(String paramString)
    throws ServiceException;

  public abstract List executeQueryHibernate(String paramString)
    throws ServiceException;

  public abstract List executeQueryNamed(String paramString)
    throws ServiceException;

  public abstract Object executeQueryNamedUnique(String paramString, Object[] paramArrayOfObject)
    throws ServiceException;

  public abstract List executeQueryNamed(String paramString, Object[] paramArrayOfObject)
    throws ServiceException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.NativeService
 * JD-Core Version:    0.6.2
 */