package com.deliver.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateOperations {
    
    /**
     * Calcula a diferença entre duas datas. dateA - dateB
     * @param date
     * @return
     */
    public static int subtrairDatas(Date dateA, Date dateB) {
        long diff = dateA.getTime() - dateB.getTime();

        TimeUnit time = TimeUnit.DAYS; 
        int diffrence = (int) time.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println("The difference in days is : " + diffrence);
        return diffrence;
    }
}