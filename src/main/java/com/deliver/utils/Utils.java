package com.deliver.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    
    private static final Logger log = LoggerFactory.getLogger(Utils.class);


    /**
     * Recebe uma data no formato dd/MM/yyyy e retorna um objeto Date
     * @param date
     * @return Date
     */
    public static Date stringToDate(String date) {
        if (date == null) return null;
        SimpleDateFormat df = new SimpleDateFormat(date);
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
    public static String dateToString(Date date, DatePattern pattern) {
        if (date == null) return "";
        SimpleDateFormat df = new SimpleDateFormat(pattern.getPattern());
        return df.format(date);
    }

    /**
     * Formata no padrão 9.999,99
     * @param money
     * @return
     */
    public static String formmatMoney(BigDecimal money) {
        if (money == null) return "";
        DecimalFormat df = new DecimalFormat("#.###,00");
        return df.format(money);
    }
}