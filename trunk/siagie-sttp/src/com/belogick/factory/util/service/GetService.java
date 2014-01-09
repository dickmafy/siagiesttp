package com.belogick.factory.util.service;

import com.belogick.factory.util.support.ServiceException;

public abstract interface GetService extends ListService
{
  public abstract Object findByObject(Object paramObject)
    throws ServiceException;

  public abstract Object findById(Object paramObject)
    throws ServiceException;

  public abstract Object findById(Class paramClass, Long paramLong)
    throws ServiceException;

  public abstract void deleteByField(Object paramObject, String paramString1, String paramString2)
    throws ServiceException;
}

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.service.GetService
 * JD-Core Version:    0.6.2
 */