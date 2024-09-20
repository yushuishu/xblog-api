package com.shuishu.blog.common.config.mybatis.mapper;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2024/9/20 18:33
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public class MapperServiceProxy<I, M> implements InvocationHandler {

    private final M mapper;
    private final Object baseService;
    private final Map<Method, Method> methodCache = new HashMap<>();

    public MapperServiceProxy(M mapper, Object baseService) {
        this.mapper = mapper;
        this.baseService = baseService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method targetMethod = methodCache.computeIfAbsent(method, m -> {
            try {
                return mapper.getClass().getMethod(m.getName(), m.getParameterTypes());
            } catch (NoSuchMethodException e) {
                try {
                    return baseService.getClass().getMethod(m.getName(), m.getParameterTypes());
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException("Method not found in either Mapper or BaseService", ex);
                }
            }
        });
        return targetMethod.invoke(targetMethod.getDeclaringClass().isInstance(mapper) ? mapper : baseService, args);
    }

    @SuppressWarnings("unchecked")
    public static <I, M> I createProxy(Class<I> interfaceClass, M mapper, Object baseService) {
        return (I) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new MapperServiceProxy<>(mapper, baseService)
        );
    }

}
