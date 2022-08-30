package com.fanchen.myrpcV2.client;

import com.fanchen.myrpcV2.common.Blog;
import com.fanchen.myrpcV2.common.User;
import com.fanchen.myrpcV2.service.BlogService;
import com.fanchen.myrpcV2.service.UserService;

public class RPCClient {
    public static void main(String[] args) {

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 服务的方法1
        User userById = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的 user 为: " + userById);

        // 服务的方法2
        User user = User.builder().userName("李四").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务端插入的数据为: " + integer);

        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("从服务端得到的blog为: " + blogById);
    }
}




