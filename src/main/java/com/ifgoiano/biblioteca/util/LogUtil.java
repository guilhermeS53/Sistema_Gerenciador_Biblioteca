package com.ifgoiano.biblioteca.util;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtil {
    
        public static void log(Object obj, Method method, Object... params) {
        System.out.println("Class: " + obj.getClass().getName());
        System.out.println("Method: " + method.getName());
        System.out.println("Params: " + Arrays.toString(params));
    }
}
