package com.swordssorcery.server;

import org.apache.commons.math3.util.Precision;

public class ExperienceTableTest {
    public static void main(String... args) {
        System.out.println((double)1/4);

        for(int i = 1; i <= 1000; i++) {
            System.out.println("level: "+i+" xp: " + xpAtLevel(i) + " distance: "+(xpAtLevel(i) - xpAtLevel(i-1))+" converted back: "+xpToLevel(xpAtLevel(i)));
        }


    }

    private static double xpMurtiplier = 30;

    public static long xpAtLevel(int level) {
        if(level <= 1) {
            return 0;
        }

        return (long) (Math.pow(level,2) * ((level*level)/4)+60) / 2;

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

        return (int) Math.round(Math.pow(8*xp-240, 0.25));

        //return (int) Math.round(Math.sqrt(2)*Math.pow(xp - 60, 0.25)); -> jo
    }
}
