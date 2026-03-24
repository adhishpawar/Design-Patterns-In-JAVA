package springLite.aop.proxy;

public interface Interceptor {
    void before(String beanName, String methodName, Object[] args);
    void after(String beanName, String methodName, Object result, long durationMs);
}

