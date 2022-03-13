package com.deliver.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    
    private static final Logger log = LoggerFactory.getLogger(Utils.class);


    /**
     * Recebe uma data no formato dd/MM/yyyy e retorna um objeto Date
     * @param date
     * @return Date
     */
    public static Date stringToDate(String date, String pattern) {
        if (date == null) return null;

        SimpleDateFormat df = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
        try {
            return df.parse(date);
        } catch (ParseException e) {
            log.error("Falha ao converter data: {}", date);
        }
        return null;
    }

    /**
     * Retorna uma string do objeto Date formatado em dd/MM/yyyy
     * @param date
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) return "";
        SimpleDateFormat df = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
        return df.format(date);
    }


    public static Date getDateWithoutTimeUsingFormat(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            log.error("Falha ao converter data: {}", new Date());
        }
        return null;
    }


    /**
     * Formata no padrão 9.999,99
     * @param money
     * @return
     */
    public static String formatMoney(BigDecimal money) {
        if (money == null) return "";
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return df.format(money);
    }
}