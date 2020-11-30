//package com.zhangbaowei.grpcclient;
//

//import io.netty.util.internal.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GoRedisAutoConfig {
////    private final RedisProperties properties;
////
////    public GoRedisAutoConfig(RedisProperties properties) {
////        this.properties = properties;
////    }
//
//    @Value("${spring.redis-ext.predixy.host:}")
//    String host;
//    @Value("${spring.redis-ext.predixy.port:}")
//    String port;
//
//    @Autowired
//    RedisClient redisClient;
//
//    @Bean
//
//    public RpcRedis goRedis() {
////        return null;
////        if (properties.getCluster() == null
////                || properties.getCluster().getNodes() == null
////                || properties.getCluster().getNodes().size() == 0) {
////            System.out.println("go redis 仅支持 cluster 模式");
////            return null;
////        }
//
//        if (StringUtil.isNullOrEmpty(host)) {
//            return null;
//        }
//
//        System.out.println("go redis rpc 模式");
//        return new RpcRedis(host + ":" + port, redisClient);
//    }
//}