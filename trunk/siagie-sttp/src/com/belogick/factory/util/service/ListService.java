package com.belogick.factory.util.service;

import com.belogick.factory.util.support.ServiceException;
import java.util.List;

public abstract interface ListService extends CrudService
{
  public abstract List listByObject(Object paramObject)
    throws ServiceException;

  public abstract List listByObjectStatus(Object paramObject, String paramString)
    throws ServiceException;

  public abstract List listByObjectEnabled(Object paramObject)
    throws ServiceException;

  public abstract List listByObjectEnabledDisabled(Object paramObject)
    throws ServiceException;

  public abstract List listByObject_OrderBy(Object paramObject, String paramString, boolean paramBoolean)
    throws ServiceException;

  public abstract List listObject_OrderAscBy(Object paramObject, String paramString)
    throws ServiceException;

  public abstract List listObject_OrderDscBy(Object paramObject, String paramString)
    throws ServiceException;

  public abstract List listByNested(Object paramObject, String paramString)
    throws ServiceException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.ListService
 * JD-Core Version:    0.6.2
 */