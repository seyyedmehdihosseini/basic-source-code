package com.basicsourcecode.utils.beanUtils;

import com.basicsourcecode.entity.BasicEntity;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.Field;

public final class BeanUtilsCustom extends BeanUtils {
    private BeanUtilsCustom() {
    }

    public static <S extends BasicEntity<P>, B, P extends Serializable> void copyDeepProperties(S source, S target, Class<B> stopClass) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        while (sourceClass != null && !sourceClass.equals(stopClass)) {
            Field[] fields = sourceClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Field targetField = targetClass.getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, field.get(source));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // اگر فیلد موجود نیست یا مشکلی در دسترسی وجود دارد، آن را نادیده بگیرید
                }
            }
            sourceClass = sourceClass.getSuperclass();
        }
    }

}
