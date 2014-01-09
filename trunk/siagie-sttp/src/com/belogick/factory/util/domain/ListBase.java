/*   */ package com.belogick.factory.util.domain;
/*   */ 
/*   */ import java.io.Serializable;
/*   */ import java.util.ArrayList;
/*   */ 
/*   */ public class ListBase<T> extends ArrayList<Object>
/*   */   implements Serializable
/*   */ {
/*   */   private static final long serialVersionUID = 6125789787242234720L;
/*   */ 
/*   */   public int getSize()
/*   */   {
/* 7 */     return size();
/*   */   }
/*   */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.domain.ListBase
 * JD-Core Version:    0.6.2
 */