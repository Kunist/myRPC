package com.fanchen.myrpcV2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * 这个实现类代表着 Java 原始的 BIO 监听模式, 来一个任务, 就 new 一个线程去处理
 * 处理任务的工作见 WorkThread
 */
public class SimpleRPCRPCServer implements RPCServer{
    // 存着服务接口名 -> service 对象的 map
    public ServiceProvider serviceProvider;

    public SimpleRPCRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动了");
            // BIO 的方式监听 Socket
            while (true) {
                Socket socket = serverSocket.accept();
                // 开启一个新线程去处理
                new Thread(new WorkThread(socket, serviceProvider));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    public void stop() {
    }

}
