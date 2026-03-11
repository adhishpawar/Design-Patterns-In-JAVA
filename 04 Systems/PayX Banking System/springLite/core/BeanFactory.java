package core;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Constructor;

public class BeanFactory {

    private static final Map<Class<?>, Object> cache = new HashMap<>();

    public static <T> T getBean(Class<T> type) {
        try {
            // if already created — return existing
            if (cache.containsKey(type)) {
                return type.cast(cache.get(type));
            }

            // create new instance by reflection
            Constructor<T> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            T obj = constructor.newInstance();

            // cache instance
            cache.put(type, obj);

            System.out.println("Bean created: " + type.getSimpleName());

            return obj;

        } catch (Exception e) {
            throw new RuntimeException("Bean creation failed for " + type, e);
        }
    }
}
