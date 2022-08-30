package com.fanchen.myrpcV2.client;

import com.fanchen.myrpcV2.common.RPCRequest;
import com.fanchen.myrpcV2.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    /**
     * 这里负责底层与服务端的通信, 发送的 Request, 接收的是 Response 对象
     * 客户端发起一次请求调用, Socket 建立连接, 发起请求 Request, 得到相应 Response
     * 这里的 request 是封装好的 (上层进行封装), 不同的 service 需要进行不同的封装
     * 客户端只知道 service 接口, 需要一层动态代理根据反射封装不同的 Service
     */
    public static RPCResponse sendRequest(String host, int port, RPCRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            RPCResponse response = (RPCResponse) objectInputStream.readObject();

            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
            return null;
        }
    }
}
