package com.fanchen.myrpcV2.client;

import com.fanchen.myrpcV2.common.RPCRequest;
import com.fanchen.myrpcV2.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    // 传入参数 Service 接口的 class 对象, 反射封装成一个 request
    private String host;
    private int port;

    // jdk 动态代理, 每次代理对象调用方法, 会经过此方法的增强 (反射获取 request 对象, socket 发送至客户端)
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // request 的构建, 使用了 lombok 中的 builder, 代码简洁
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args).paramsTypes(method.getParameterTypes()).build();
        // 数据传输
        RPCResponse response = IOClient.sendRequest(host, port, request);
        // 输出 response
        return response.getData();
    }
    <T> T getProxy(Class<T> clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) o;
    }
}
