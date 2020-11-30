/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhangbaowei.grpcclient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangbaowei.grpcclient.pool.GrpcClientPool;
import com.zhangbaowei.grpcclient.pool.HelloWorldClientSingle;
import com.zhangbaowei.grpcclient.pool.HelloWorldFactory;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.grpcredis.MGetReply;
import io.grpc.examples.grpcredis.MGetRequest;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * A simple client that requests a mget from the
 *
 * @author zhangbaowei
 */
public class RpcRedis {
    private static final Logger logger = Logger.getLogger(RpcRedis.class.getName());
    private static GrpcClientPool grpcClientPool;
    private String target;

    private static <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        if (keys == null || values == null) {
            return null;
        }
        if (keys.size() == 0) {
            return null;
        }
        if (keys.size() != values.size()) {
            throw new RuntimeException("key value 不相等");
        }

        Map<K, V> map = new LinkedHashMap<>(values.size());

        for (int i = 0; i < keys.size(); i++) {
            K key = keys.get(i);
            V value = values.get(i);

            map.put(key, value);
        }
        return map;
    }

    public RpcRedis(String target) {
        this.target = target;
    }

    private GrpcClientPool getPool() {
        if (grpcClientPool == null) {
            synchronized (RpcRedis.class) {
                if (grpcClientPool == null) {
                    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
                    // 池中的最大连接数
                    poolConfig.setMaxTotal(8);
                    // 最少的空闲连接数
                    poolConfig.setMinIdle(0);
                    // 最多的空闲连接数
                    poolConfig.setMaxIdle(8);
                    // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
                    poolConfig.setMaxWaitMillis(-1);
                    // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
                    poolConfig.setLifo(true);
                    // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟
                    poolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L);// 连接耗尽时是否阻塞,默认为true

                    poolConfig.setBlockWhenExhausted(true);
//                    poolConfig.setTestOnBorrow(true);
//                    poolConfig.setTestOnReturn(true);
                    poolConfig.setTestWhileIdle(true);

                    //一定要关闭jmx，不然springboot启动会报已经注册了某个jmx的错误
                    poolConfig.setJmxEnabled(false);

                    grpcClientPool = new GrpcClientPool(new HelloWorldFactory(target), poolConfig);
                }
            }
        }
//        System.out.println("hashcode:" + grpcClientPool.hashCode());
        return grpcClientPool;
    }

    private static ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    private static <T> String serializeToString(T value) {
        try {
            return getMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static <T> T deserializeFromString(String json, Class<T> tClass) {
        try {
            if (json == null || json.equals("")) {
                return null;
            }
            return getMapper().readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static <T> T deserializeFromString(String json, TypeReference<T> typeReference) {
        try {
            if (json == null) {
                return null;
            }
            return getMapper().readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getAll(List<String> keys) {
        HelloWorldClientSingle helloWorldClientSingle = null;
        long l = System.currentTimeMillis();
        try {
            helloWorldClientSingle = getPool().borrowObject();
            List<String> greet = helloWorldClientSingle.mGet(keys);

            return zipToMap(keys, greet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (helloWorldClientSingle != null) {
                getPool().returnObject(helloWorldClientSingle);
            }
            long ts = System.currentTimeMillis() - l;
            if (ts > 5) {
                System.out.println("ts:" + ts + " keys : " + String.join(",", keys) + " pool :" + getPool().getNumActive());
            }
        }
    }

    public <T> Map<String, T> getAll(List<String> keys, TypeReference<T> typeReference) {
        Map<String, String> stringStringMap = getAll(keys);
        Map<String, T> map = new LinkedHashMap<>(keys.size());

        for (Map.Entry<String, String> stringStringEntry : stringStringMap.entrySet()) {
            map.put(stringStringEntry.getKey(), deserializeFromString(stringStringEntry.getValue(), typeReference));
        }

        return map;
    }

    public <T> Map<String, T> getAll(List<String> keys, Class<T> tClass) {

        Map<String, String> stringStringMap = getAll(keys);

        Map<String, T> map = new LinkedHashMap<>(keys.size());

        for (Map.Entry<String, String> stringStringEntry : stringStringMap.entrySet()) {
            map.put(stringStringEntry.getKey(), deserializeFromString(stringStringEntry.getValue(), tClass));
        }

        return map;
    }
}
