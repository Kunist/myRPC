package com.fanchen.myrpcV1.client;

import com.fanchen.myrpcV1.common.User;
import com.fanchen.myrpcV1.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    public static void main(String[] args) {

        // tmp

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 服务的方法1
        User userById = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的 user 为: " + userById);

        // 服务的方法2
        User user = User.builder().userName("李四").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务端插入的数据为: " + integer);
    }
}




