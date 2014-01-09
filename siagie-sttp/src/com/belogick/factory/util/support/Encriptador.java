/*    */ package com.belogick.factory.util.support;
/*    */ 
/*    */ import java.io.PrintStream;

/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/*    */ import org.jfree.util.Log;
/*    */ 
/*    */ public class Encriptador
/*    */ {
/*    */   public static Base64 base;
/*    */ 
/*    */   public static String encryptBlowfish(String to_encrypt, String strkey)
/*    */   {
/*    */     try
/*    */     {
/* 26 */       base = new Base64();
/* 27 */       SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
/* 28 */       Cipher cipher = Cipher.getInstance("Blowfish");
/* 29 */       cipher.init(1, key);
/* 30 */       String encriptado = new String(cipher.doFinal(to_encrypt.getBytes()));
/* 31 */       return base.encodeAsString(encriptado.getBytes());
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 35 */       Log.debug(e); } return null;
/*    */   }
/*    */ 
/*    */   public static String decryptBlowfish(String to_decrypt, String strkey)
/*    */   {
/*    */     try
/*    */     {
/* 48 */       base = new Base64();
/* 49 */       byte[] b64_dec = base.decode(to_decrypt);
/* 50 */       String desEncriptado = new String(b64_dec);
/* 51 */       SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
/* 52 */       Cipher cipher = Cipher.getInstance("Blowfish");
/* 53 */       cipher.init(2, key);
/* 54 */       System.out.println("stringz " + to_decrypt);
/* 55 */       System.out.println("bytesz " + to_decrypt.getBytes());
/* 56 */       System.out.println("bytes64s " + desEncriptado);
/* 57 */       byte[] decrypted = cipher.doFinal(to_decrypt.getBytes());
/* 58 */       System.out.println(".....:.. " + decrypted);
/* 59 */       return new String(decrypted);
/*    */     }
/*    */     catch (Exception e) {
/* 62 */       Log.debug(e); } return null;
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.support.Encriptador
 * JD-Core Version:    0.6.2
 */