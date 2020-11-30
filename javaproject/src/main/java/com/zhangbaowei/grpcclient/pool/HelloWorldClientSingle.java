package com.zhangbaowei.grpcclient.pool;

import com.zhangbaowei.grpcclient.RpcRedis;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.grpcredis.MGetReply;
import io.grpc.examples.grpcredis.MGetRequest;
import io.grpc.examples.grpcredis.RedisGrpc;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhangbaowei
 */
public class HelloWorldClientSingle {
    private static final Logger logger = Logger.getLogger(RpcRedis.class.getName());
    private final ManagedChannel channel;
    private RedisGrpc.RedisBlockingStub blockingStub;

    public HelloWorldClientSingle(String target) {
        channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        //需要用到存根时创建,不可复用
        blockingStub = RedisGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * 客户端方法
     *
     * @param name
     * @return
     */
    public List<String> mGet(Iterable<String> name) {

        MGetRequest request = MGetRequest.newBuilder()
                .addAllKeys(name)
                .build();
        MGetReply response;
        RedisGrpc.RedisBlockingStub blockingStub = RedisGrpc.newBlockingStub(channel);
        try {
            response = blockingStub.mGet(request);
            return response.getMessageList();
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            e.printStackTrace();
            return null;
        }
    }
}