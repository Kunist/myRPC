package com.fanchen.myrpcV2.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 在上一版中, response 传输的是 User 对象,
 * 显然在一个应用中我们不可能之传输一种类型的数据.
 * 由此我们将传输对象抽象成为 Object,
 * RPC 需要经过网络传输, 有可能失败, 类似于 http,
 * 引入状态码和状态信息表示服务调用成功还是失败.
 */

@Data
@Builder
public class RPCResponse implements Serializable {
    // 状态信息
    private int code;
    private String message;
    // 具体数据
    private Object data;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }
    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).message("服务器发生错误").build();
    }
}
