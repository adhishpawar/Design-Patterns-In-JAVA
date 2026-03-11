package core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonRegistry {

    private static final Map<Class<?>, Object> INSTANCES = new ConcurrentHashMap<>();

    // register singleton
    public static <T> void register(Class<T> type, T instance) {
        INSTANCES.put(type, instance);
    }

    // get singleton
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> type) {
        return (T) INSTANCES.get(type);
    }
}
