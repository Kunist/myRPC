package com.fanchen.myrpcV2.server;

import java.util.HashMap;
import java.util.Map;


/**
 * 之前这里使用 Map 简单实现的
 * 存放服务接口与服务端对应的实现类
 * 服务启动时要暴露其相关的实现类
 * 根据 request 中的 interface 调用服务端中相关实现类
 */
public class ServiceProvider {
    /**
     * 一个实现类可能实现多个接口
     */

    private Map<String, Object> interfaceProvider;

    public ServiceProvider() {
        this.interfaceProvider = new HashMap<>();
    }

    public void providerServiceInterface(Object service) {
//        String serviceName = service.getClass().getName();
        Class< ? >[] interfaces = service.getClass().getInterfaces();

        for (Class clazz : interfaces) {
            interfaceProvider.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }

}
