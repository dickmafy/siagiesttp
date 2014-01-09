package com.belogick.factory.util.service;

import com.belogick.factory.util.support.ServiceException;
import java.util.Collection;

public abstract interface CrudService extends NativeService
{
  public abstract Object save(Object paramObject)
    throws ServiceException;

  public abstract void saveCollection(Collection paramCollection)
    throws ServiceException;

  public abstract void delete(Object paramObject)
    throws ServiceException;

  public abstract void deleteCollection(Collection paramCollection)
    throws ServiceException;

  public abstract void updateStatus(Object paramObject, Long paramLong)
    throws ServiceException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.CrudService
 * JD-Core Version:    0.6.2
 */