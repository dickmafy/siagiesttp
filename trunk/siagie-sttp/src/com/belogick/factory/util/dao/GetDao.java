package com.belogick.factory.util.dao;

import com.belogick.factory.util.support.DaoException;

public abstract interface GetDao extends ListDao
{
  public abstract Object findByObject(Object paramObject)
    throws DaoException;

  public abstract Object findById(Object paramObject)
    throws DaoException;

  public abstract Object findById(Class paramClass, Long paramLong)
    throws DaoException;

  public abstract void deleteByField(Object paramObject, String paramString1, String paramString2)
    throws DaoException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.GetDao
 * JD-Core Version:    0.6.2
 */