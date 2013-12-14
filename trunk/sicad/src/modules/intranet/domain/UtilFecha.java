package modules.intranet.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import antlr.collections.List;

/**
 *
 * @author mew
 */
public class UtilFecha {

    private String fecha;
    private int dia;
    private int mes;
    private int anio;
    

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;


        try {
            date = formatter.parse(fecha);
        } catch (ParseException ex) {
            
        }



        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);


        int anio_ = calendar.get(Calendar.YEAR);
        int mes_ = calendar.get(Calendar.MONTH)+1;
        int dia_ = calendar.get(Calendar.DAY_OF_MONTH);

        this.setAnio(anio_);
        this.setDia(dia_);
        this.setMes(mes_);

        this.fecha = fecha;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }


    public static void main(String[] args) {
        getNumerodeDiaMesAñodeUnaFecha();
        Date fechaHoy = new Date();
        System.out.println(">>>>" + getDiaDeLaSemana(fechaHoy));
        getConfiguracionHorariayHoraExacta();  
        
        
        System.out.println("-------------------PARTE 2------------");

        Date fechaInicial = new Date();
        Integer cantidad = 7;
        Boolean lunes = true;
        Boolean martes = false;
        Boolean miercoles = false;
        Boolean jueves = false;
        Boolean viernes = false;
        Boolean sabado = false;
        Boolean domingo = true;
        
        
        getFechaConNombreDia(fechaInicial,cantidad,lunes,martes,miercoles,jueves,viernes,sabado,domingo);
        
        
		

    }

    
   
    
    
	private static void getFechaConNombreDia(Date fechaInicial,Integer cantidad,Boolean lunes,
			Boolean martes,Boolean miercoles,Boolean jueves,Boolean viernes,Boolean sabado,Boolean domingo) {
		GregorianCalendar gcalendarInicial = new GregorianCalendar();
		gcalendarInicial.setTime(fechaInicial); 
		GregorianCalendar gcalendarFinal = new GregorianCalendar();
		gcalendarFinal.add(Calendar.DAY_OF_WEEK, cantidad); // # dias hasta el que va calcular.
		while (gcalendarInicial.before(gcalendarFinal)) {

			gcalendarInicial.add(Calendar.DAY_OF_WEEK, 1);

		
			if(getDiaDeLaSemana(gcalendarInicial).equals("Lunes"))
			{
				System.out.println(getDiaDeLaSemana(gcalendarInicial));
			}
			if(getDiaDeLaSemana(gcalendarInicial).equals("Martes"))
			{
				System.out.println(getDiaDeLaSemana(gcalendarInicial));
			}
			if(getDiaDeLaSemana(gcalendarInicial).equals("Miercoles"))
			{
				System.out.println(getDiaDeLaSemana(gcalendarInicial));
			}
			if(getDiaDeLaSemana(gcalendarInicial).equals("Jueves"))
			{
				System.out.println(getDiaDeLaSemana(gcalendarInicial));
			}
			if(getDiaDeLaSemana(gcalendarInicial).equals("Viernes"))
			{
				System.out.println(getDiaDeLaSemana(gcalendarInicial));
			}
			if(getDiaDeLaSemana(gcalendarInicial).equals("Sabado"))
			{
				System.out.println(getDiaDeLaSemana(gcalendarInicial));
			}
			if(getDiaDeLaSemana(gcalendarInicial).equals("Domingo"))
			{
				System.out.println(getDiaDeLaSemana(gcalendarInicial));
			}
			
					
			SimpleDateFormat sdf;
			sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
			System.out.println("Fecha: "+ sdf.format(gcalendarInicial.getTime()));

		}
	}

	private static void getConfiguracionHorariayHoraExacta() {
		TimeZone tz = Calendar.getInstance().getTimeZone();    
        System.out.println(tz.getDisplayName()); // (i.e. Moscow Standard Time)    
       System.out.println(tz.getID()); 
       
       Date date = new Date();  
       SimpleDateFormat sdf;  
       sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");  
       System.out.println(sdf.format(date));
	}

	private static void getNumerodeDiaMesAñodeUnaFecha() {
		UtilFecha fecha = new UtilFecha();
        fecha.setFecha("28/03/2010");
        System.out.println(fecha.getAnio()+" "+fecha.getMes()+" "+fecha.getDia());
	}
    
	
	 public static String getDiaDeLaSemana(GregorianCalendar cal){
	    	
	    	//DOMINGO es 1 ,Lunes = 2...Sabado = 7
	    	switch (cal.get(Calendar.DAY_OF_WEEK)-1) 
	    	{
			
			case 1:
				return "Lunes";
			case 2:
				return "Martes";
			case 3:
				return "Miercoles";
			case 4:
				return "Jueves";
			case 5:
				return "Viernes";
			case 6:
				return "Sabado";
			case 0:
				return "Domingo";
			default:
				break;
			}
			return "";
	    		
	    			
	    }
	 
	 
    public static int getDiaDeLaSemana(Date d){
    	GregorianCalendar cal = new GregorianCalendar();
    	cal.setTime(d);
    	
    	//DOMINGO es 1 ,Lunes = 2...Sabado = 7
    	switch (cal.get(Calendar.DAY_OF_WEEK)-1) {
		
		case 1:
			System.out.println("Lunes1");
			break;
		case 2:
			System.out.println("Martes1");
			break;
		case 3:
			System.out.println("Miercoles1");
			break;
		case 4:
			System.out.println("Jueves1");
			break;
		case 5:
			System.out.println("Viernes1");
			break;
		case 6:
			System.out.println("Sabado1");
			break;	
		case 0:
			System.out.println("Domingo1");
			break;
			
		default:
			break;
		}
    	
    
    		
    		
    	return cal.get(Calendar.DAY_OF_WEEK)-1;		
    }
    


}