package com.belogick.factory.util.dao;

import com.belogick.factory.util.support.DaoException;
import java.util.Collection;

public abstract interface CrudDao extends NativeDao
{
  public abstract Object save(Object paramObject)
    throws DaoException;

  public abstract void saveCollection(Collection paramCollection)
    throws DaoException;

  public abstract void delete(Object paramObject)
    throws DaoException;

  public abstract void deleteCollection(Collection paramCollection)
    throws DaoException;

  public abstract void updateStatus(Object paramObject, Long paramLong)
    throws DaoException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.CrudDao
 * JD-Core Version:    0.6.2
 */