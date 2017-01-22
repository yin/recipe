package com.github.yin.recipe.templating.st4;

import org.stringtemplate.v4.misc.Misc;
import org.stringtemplate.v4.misc.ObjectModelAdaptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Looksup properties for StringTemplate.
 */
public class AutoValueAdaptor extends ObjectModelAdaptor {
    public synchronized Object lookupMethod(Object o, String propertyName, Object value, Class<?> c) {
        Method m = Misc.getMethod(c, propertyName);
        try {
            if (m != null) {
                this.classAndPropertyToMemberCache.put(c, propertyName, m);
                value = Misc.invokeMethod(m, o, value);
            } else {
                return super.lookupMethod(o, propertyName, value, c);
            }
        } catch (Exception var10) {
            this.throwNoSuchProperty(c, propertyName, var10);
        }

        return value;
    }
}
