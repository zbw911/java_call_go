package com.zhangbaowei.callgo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.zhangbaowei.callgo.types.GoString;

import java.io.*;

public interface LibGoRedis extends Library {
    LibGoRedis INSTANCE = Native.load("libgoredis", LibGoRedis.class);

    String MGet(String msg);

    void CreateRedisClient(String nodes, String password);
}
