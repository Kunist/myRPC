package com.fanchen.myrpcV1.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 在上一版中, 我们的 Request 仅仅只发送了一个 id 参数过去,
 * 这显然是不合理的, 因为服务端不会只有一个方法, 因此值传递参数不会知道调用哪个方法
 * 因此一个 RPC 请求中, client 发送应该是需要调用的 Service 接口名, 方法, 参数, 参数类型
 * 这样服务端就能根据这些信息, 根据反射调用相应的方法.
 * 还是使用 Java 自带的序列化方式
 */

@Data
@Builder
public class RPCRequest implements Serializable {
    // 服务类名, 客户端只知道接口名, 在服务端中用接口名指向实现类
    private String interfaceName;
    // 方法名
    private String methodName;
    // 参数列表
    private Object[] params;
    // 参数类型
    private Class<?>[] paramsTypes;
}
