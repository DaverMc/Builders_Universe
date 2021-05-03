package de.buun.haven.util;

import de.buun.haven.Loggers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflections {

    private Reflections(){}

    public static void invokeMethod(Object object, Method method, Object...values){
        if(object == null){
            Loggers.logError(new NullPointerException());
            return;
        }
        try {
            method.invoke(object, values);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>[] parameters){
        try {
            return clazz.getConstructor(parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getFieldValue(Object object, String fieldName){
        Field field = getField(object, fieldName);
        if(field == null){
            Loggers.logError(new NullPointerException("Field is Null!"));
            return null;
        }
        try{
            return (T) field.get(object);
        }catch (IllegalAccessException e){
            Loggers.logError(e);
        }
        return null;
    }

    public static Field getField(Object object, String fieldName){
        Field field = null;
        try{
            field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
        }catch (NoSuchFieldException e){
            Loggers.logError(e);
        }
        return field;
    }

    public static void setFieldValue(Object object, String fieldName, Object value){
        Field field = getField(object, fieldName);
        if(field == null){
            Loggers.logError(new NullPointerException("Field is Null!"));
            return;
        }
        try{
            field.set(object, value);
        }catch (IllegalAccessException e){
            Loggers.logError(e);
        }
    }

}
