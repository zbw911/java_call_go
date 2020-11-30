package com.zhangbaowei.grpcclient.pool;


import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author zhangbaowei
 */
public class GrpcClientPool extends GenericObjectPool<HelloWorldClientSingle> {

    public GrpcClientPool(PooledObjectFactory<HelloWorldClientSingle> factory) {
        super(factory);
    }

    public GrpcClientPool(PooledObjectFactory<HelloWorldClientSingle> factory, GenericObjectPoolConfig<HelloWorldClientSingle> config) {
        super(factory, config);
    }

    public GrpcClientPool(PooledObjectFactory<HelloWorldClientSingle> factory, GenericObjectPoolConfig<HelloWorldClientSingle> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}