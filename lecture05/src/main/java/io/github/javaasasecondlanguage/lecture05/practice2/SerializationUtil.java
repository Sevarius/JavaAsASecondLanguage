package io.github.javaasasecondlanguage.lecture05.practice2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Serialize an [instance] with a specification:
 * 1. [instance] has a default (no-arg) constructor
 * 2. [instance] super class is Object
 * 3. [instance] use concrete classes (not interfaces) as fields
 *
 * Bonus:
 * Serialize Collections and nested objects as nested Maps
 */
public class SerializationUtil {

    /**
     *
     * Serialize each field of [obj] as an entry in HashMap
     * class A {
     *     Integer i;
     * }
     *
     * var a = new A(); a.i = 42   ->   Map.of("i", 42)
     *
     * @param obj - object to serialize
     *
     * @return
     */
    static Map<String, ?> serialize(Object obj) throws IllegalAccessException {
        //throw new RuntimeException("Not implemented");
        var res = new HashMap<String, Object>();
        try {
            for (var field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                res.put(field.getName(), field.get(obj));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *
     * @param obj - map representation of object
     * @param clazz - target type of deserialization
     */
    static <T> T deserialize(Map<String, ?> obj, Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //throw new RuntimeException("Not implemented");
        var ctor = clazz.getDeclaredConstructor();
        T instance = ctor.newInstance();
        for (Field declaredField : clazz.getDeclaredFields()) {
            declaredField.setAccessible(true);
            declaredField.set(instance, obj.get(declaredField.getName()));
        }
        return instance;
    }
}