package zy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyuan
 * @date 2017/8/2.
 */
public class FieldUtil {

    private static final Map<Class<?>,Field[]> CACHE = new HashMap<>();

    public static Field[] getFields(Class<?> clazz) {
        if (CACHE.containsKey(clazz)){
            return CACHE.get(clazz);
        }
        if (clazz == null) {
            return new Field[0];
        }
        String clazzName = clazz.getName();
        if (clazzName == null
                || clazzName.length() == 0
                || clazzName.startsWith("java.")
                || clazzName.startsWith("javax.")
                || clazzName.startsWith("android.")) {
            return new Field[0];
        }
        Class<?> superClass = clazz.getSuperclass();
        Field[] superFields = getFields(superClass);
        List<Field> fieldList = new ArrayList<>();
        Collections.addAll(fieldList, superFields);

        Field[] selfFields = clazz.getDeclaredFields();
        for (Field field : selfFields) {
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers)) {
                fieldList.add(field);
            }
        }
        Field[] fields = fieldList.toArray(new Field[fieldList.size()]);
        CACHE.put(clazz,fields);
        return fields;
    }

}
