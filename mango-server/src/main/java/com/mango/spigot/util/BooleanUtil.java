package com.mango.spigot.util;

public class BooleanUtil {

    public static boolean isBoolean(String s) {
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
    }

}
