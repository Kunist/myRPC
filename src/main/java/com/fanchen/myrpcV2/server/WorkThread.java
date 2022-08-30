package com.fanchen.myrpcV2.server;

import com.fanchen.myrpcV2.common.RPCRequest;
import com.fanchen.myrpcV2.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * 这里负责解析得到的 request 请求, 执行服务方法, 返回给客户端
 * 1. 从 request 得到 interfaceName
 * 2. 根据 interfaceName 在 serviceProviders Map 中获取服务端的实现类
 * 3. 从 request 中得到方法名, 参数, 利用反射执行服务中的方法
 * 4. 得到结果, 封装成 response, 写入 socket
 */

@AllArgsConstructor
public class WorkThread implements Runnable{
    private Socket socket;
    private ServiceProvider serviceProvider;


    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // 读取客户端传过来的 request
            RPCRequest request = (RPCRequest) ois.readObject();
            // 反射调用服务方法获得返回值
            RPCResponse response = getResponse(request);
            // 写入到客户端
            oos.writeObject(response);
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("从 IO 中读取数据错误");
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        // 得到服务
        String interfaceName = request.getInterfaceName();
        // 得到服务端相应服务类实现
        Object service = serviceProvider.getService(interfaceName);
        // 反射调用方法
        Method method = null;
        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行错误");
            return RPCResponse.fail();
        }
    }

}
