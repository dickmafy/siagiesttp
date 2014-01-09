/*    */ package com.belogick.factory.util.helper;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.InetAddress;
/*    */ import java.net.URL;
/*    */ import java.net.UnknownHostException;
/*    */ 
/*    */ public class NetworkHelper
/*    */ {
/*    */   public static String getHost()
/*    */   {
/*    */     try
/*    */     {
/* 24 */       InetAddress address = InetAddress.getLocalHost();
/* 25 */       address = InetAddress.getLocalHost();
/* 26 */       String hostName = address.getHostName();
/* 27 */       return hostName.substring(hostName.indexOf("/") + 1);
/*    */     }
/*    */     catch (UnknownHostException e) {
/* 30 */       e.printStackTrace();
/* 31 */     }return null;
/*    */   }
/*    */ 
/*    */   public static String getIpLocal()
/*    */   {
/*    */     try
/*    */     {
/* 43 */       InetAddress address = InetAddress.getLocalHost();
/* 44 */       address = InetAddress.getLocalHost();
/* 45 */       String ip = address.getHostAddress();
/* 46 */       return ip.substring(ip.indexOf("/") + 1);
/*    */     }
/*    */     catch (UnknownHostException e) {
/* 49 */       e.printStackTrace();
/* 50 */     }return null;
/*    */   }
/*    */ 
/*    */   public static String getIpPublica()
/*    */   {
/* 59 */     String ip = null;
/*    */     try
/*    */     {
/* 63 */       URL tempURL = new URL("http://checkip.dyndns.com/");
/* 64 */       HttpURLConnection tempConn = (HttpURLConnection)tempURL.openConnection();
/* 65 */       InputStream tempInStream = tempConn.getInputStream();
/* 66 */       InputStreamReader tempIsr = new InputStreamReader(tempInStream);
/* 67 */       BufferedReader tempBr = new BufferedReader(tempIsr);
/* 68 */       ip = tempBr.readLine();
/* 69 */       ip = ip.replace("<html><head><title>Current IP Check</title></head><body>Current IP Address: ", "");
/* 70 */       ip = ip.replace("</body></html>", "");
/* 71 */       tempBr.close();
/* 72 */       tempInStream.close();
/* 73 */       tempURL = null;
/* 74 */       tempConn = null;
/* 75 */       tempInStream = null;
/* 76 */       tempIsr = null;
/* 77 */       tempBr = null;
/*    */     }
/*    */     catch (Exception ex) {
/* 80 */       ip = "Unknown";
/* 81 */     }return ip;
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.NetworkHelper
 * JD-Core Version:    0.6.2
 */