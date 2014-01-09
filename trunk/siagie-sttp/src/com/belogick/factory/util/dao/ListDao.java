package com.belogick.factory.util.dao;

import com.belogick.factory.util.support.DaoException;
import java.util.List;

public abstract interface ListDao extends CrudDao
{
  public abstract List listByObject(Object paramObject)
    throws DaoException;

  public abstract List listByObjectStatus(Object paramObject, String paramString)
    throws DaoException;

  public abstract List listByObjectEnabled(Object paramObject)
    throws DaoException;

  public abstract List listByObjectEnabledDisabled(Object paramObject)
    throws DaoException;

  public abstract List listByObject_OrderBy(Object paramObject, String paramString, boolean paramBoolean)
    throws DaoException;

  public abstract List listObject_OrderAscBy(Object paramObject, String paramString)
    throws DaoException;

  public abstract List listObject_OrderDscBy(Object paramObject, String paramString)
    throws DaoException;

  public abstract List listByNested(Object paramObject, String paramString)
    throws DaoException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.dao.ListDao
 * JD-Core Version:    0.6.2
 */