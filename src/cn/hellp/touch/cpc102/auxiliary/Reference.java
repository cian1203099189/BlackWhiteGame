package cn.hellp.touch.cpc102.auxiliary;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reference {
    private Reference() {}

    public static void setComponentEntity(Component component, Entity entity) {
        try {
            Method method = Component.class.getDeclaredMethod("setEntity", Entity.class);
            method.setAccessible(true);
            method.invoke(component, entity);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
