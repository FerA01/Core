package org.core.utilidades.util;

import org.core.utilidades.util.exception.NumeroFueraDeRangoException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    public static String getFechaFormato(Date date, String formato){
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(date);
    }
    public static Date getFechaHoy(){
        return Calendar.getInstance().getTime();
    }
    public static Date cambiarFecha(Date date, int dia, int mes, int anio){
        try {
            EnRango rangoDia = new EnRango(1, 31);
            EnRango rangoMes = new EnRango(1, 12);
            EnRango rangoAnio = new EnRango(1900, 2999);

            rangoDia.enRango(dia);
            rangoMes.enRango(mes);
            rangoAnio.enRango(anio);
            date.setDate(dia);
            date.setMonth(mes-1);
            int anioAux = anio-1900;
            date.setYear(anioAux);
            return date;
        }catch (NumeroFueraDeRangoException exception){
            logger.log(Level.WARNING, exception.getMessage());
        }
        return date;
    }
    public static Date getPrimeraHora(Date date){
        return transformarTiempoPrimeraHora(date);
    }
    public static Date getUltimaHora(Date date){
        return transformarTiempoUltimaHora(date);
    }
    private static Date transformarTiempoPrimeraHora(Date date){
        Date fecha = (Date) date.clone();
        date.setSeconds(0);
        date.setMinutes(0);
        date.setHours(0);
        return fecha;
    }
    private static Date transformarTiempoUltimaHora(Date date){
        Date fecha = (Date) date.clone();
        fecha.setSeconds(59);
        fecha.setMinutes(59);
        fecha.setHours(23);
        return fecha;
    }
    public static boolean compararEntreFechas(Date fechaAnterior, Date fechaPosterior, Date fecha){
        return fecha.after(fechaAnterior) && fecha.before(fechaPosterior);
    }
    public static boolean esFechaAnterior(Date fechaAnterior, Date fecha){
        return fechaAnterior.before(fecha);
    }
    public static boolean esFechaPosterior(Date fechaPosterior, Date fecha){
        return fechaPosterior.after(fecha);
    }
}
