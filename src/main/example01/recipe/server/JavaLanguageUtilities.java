package recipe.server;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * Performs different operations (currently a signle one) reated to java lanague construct like conversions
 * between data structures.
 */
public class JavaLanguageUtilities {
    public static <T> T[] constructArray(Class<T> type, Collection<T> head, T... tail) {
        T[] ary = (T[]) Array.newInstance(type, head.size() + tail.length);
        int index = 0;
        for (T element : head) {
            ary[index++] = element;
        }
        for (int tailIndex = 0; tailIndex < tail.length; index++, tailIndex++) {
            ary[index] = tail[tailIndex];
        }
        return ary;
    }
}
