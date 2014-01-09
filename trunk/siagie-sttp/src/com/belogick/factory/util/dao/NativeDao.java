package com.belogick.factory.util.dao;

import com.belogick.factory.util.support.DaoException;
import java.sql.Connection;
import java.util.List;
import org.hibernate.Query;

public abstract interface NativeDao
{
  public abstract Connection getConnection()
    throws DaoException;

  public abstract Query createQuery(String paramString)
    throws DaoException;

  public abstract void executeQueryUpdate(String paramString)
    throws DaoException;

  public abstract List executeQueryList(String paramString)
    throws DaoException;

  public abstract List executeQueryHibernate(String paramString)
    throws DaoException;

  public abstract List executeQueryNamed(String paramString)
    throws DaoException;

  public abstract Object executeQueryNamedUnique(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List executeQueryNamed(String paramString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.NativeDao
 * JD-Core Version:    0.6.2
 */