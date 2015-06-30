package com.swordssorcery.server;

import org.apache.commons.math3.util.Precision;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ExperienceTableTest {
    public static void main(String... args) {
        System.out.println(xpToLevel(38));

        for(int i = 1; i <= 1000; i++) {
            System.out.println("level: "+i+" xp: " + xpAtLevel(i) + " distance: "+(xpAtLevel(i) - xpAtLevel(i-1))+" converted back: "+xpToLevel(xpAtLevel(i)));
        }


    }

    private static double xpMurtiplier = 30;

    public static long xpAtLevel(int level) {
        if(level <= 1) {
            return 0;
        }

        //(x^2*((x*x)/4)+60)/2
        //System.out.println((double)((Math.pow((double)level,(double)2) * (((double)level*(double)level)/(double)4)+(double)60) / (double)2));
        return (long) Math.ceil((Math.pow((double)level, (double)2) * (((double)level * (double)level) / (double)4) + (double)60) / (double)2);

        //return (long) (Math.pow(level,2) * ((level*level)/4)+60); -> jo

        //return (int) ((level * 50 - 50)  + Math.pow(level, 2)/0.33);

        //return (int) (xpMurtiplier * level * level - xpMurtiplier * level);

        //return (long) Precision.round((long) (4 * (Math.pow(level, 3) - 3 * Math.pow(level, 2) + 10 * level) - 30), -1);
    }

    public static int xpToLevel(long xp) {
        //return (int) ((xpMurtiplier + Math.sqrt(xpMurtiplier * xpMurtiplier - 4 * xpMurtiplier * (-xp) ))/ (2 * xpMurtiplier));
        //return (int) ((Math.sqrt(xpMurtiplier*125+xpMurtiplier*4*xp)-xpMurtiplier)/(xpMurtiplier*2));
        //return (int) (4 / (Math.log(xp)/Math.log(3) - 3 * Math.log(xp)/Math.log(2) - 10 / xp) + 30);
        if( xp < 32) {
            return 1;
        }

        System.out.println(Math.pow(8 * xp - 240, 0.25));

        //return (int)(Math.pow(xp,0.75)*root(xp-30, 4));
        return (int) Math.floor(Math.pow((double) 8 * (double)xp - (double)240, 0.25));

        //return (int) Math.round(Math.sqrt(2)*Math.pow(xp - 60, 0.25)); -> jo
    }

    public static double root(double num, double root)
    {
        return Math.pow(Math.E, Math.log(num)/root);
    }
}
