package com.viseo;

import java.lang.reflect.Field;

public class TestUtil {
    public static void inject(Object target, String fieldName, Object value){
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Field getField(Object target, String fieldName) {
        Field field = null;
        Class clazz = target.getClass();
        while (field == null && clazz != Object.class) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }
}
