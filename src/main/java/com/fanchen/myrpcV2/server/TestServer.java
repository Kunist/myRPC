package com.fanchen.myrpcV2.server;

import com.fanchen.myrpcV2.service.BlogService;
import com.fanchen.myrpcV2.service.Impl.BlogServiceImpl;
import com.fanchen.myrpcV2.service.Impl.UserServiceImpl;
import com.fanchen.myrpcV2.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
//        Map<String, Object> serviceProviders = new HashMap<String, Object>();
//        // 暴露两个服务接口, 即再 RPCServer 中加一个 HashMap
//        serviceProviders.put("com.fanchen.myrpcV2.service.BlogService", blogService);
//        serviceProviders.put("com.fanchen.myrpcV2.service.UserService", userService);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.providerServiceInterface(userService);
        serviceProvider.providerServiceInterface(blogService);
        RPCServer rpcServer = new ThreadPoolRPCRPCServer(serviceProvider);
        rpcServer.start(8899);
    }
}
