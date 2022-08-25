package com.fanchen.myrpcV0.server;

import com.fanchen.myrpcV0.common.User;
import com.fanchen.myrpcV0.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了 id 为" + id + "的用户");
        // 模拟从数据库中取数据的行为
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }
}
