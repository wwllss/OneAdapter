package zy.utils;

import android.app.Activity;
import android.view.View;

import zy.annotation.ViewInject;

import java.lang.reflect.Field;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public class InjectUtil {

    public static void inject(Object obj) {
        inject(obj, null);
    }

    public static void inject(Object obj, View view) {
        if (obj == null) {
            return;
        }
        Field[] fields = FieldUtil.getFields(obj.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ViewInject.class)) {
                ViewInject annotation = field.getAnnotation(ViewInject.class);
                int id = annotation.value();
                try {
                    field.set(obj, view == null
                            ? ((Activity) obj).findViewById(id)
                            : view.findViewById(id)
                    );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
