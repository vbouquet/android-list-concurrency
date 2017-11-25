package fr.parisnanterre.pmoo.androidm2td1.util;

import java.sql.Timestamp;


public class Time {

    public static Timestamp getRandomTimestamp() {
        long offset = Timestamp.valueOf("1920-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }
}
