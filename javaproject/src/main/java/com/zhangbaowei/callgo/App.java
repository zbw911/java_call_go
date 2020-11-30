package com.zhangbaowei.callgo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangbaowei.callgo.types.GoString;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + getDefaultCharSet());


        LibGoRedis.INSTANCE.CreateRedisClient("127.0.0.1:7000", "passwd123");

        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                String[] keys = "value=key-pv-90681136,key-pv-90867627,key-pv-90685368,key-pv-90888519,key-pv-90896651,key-pv-90832660,key-pv-90898159,key-pv-85916926,key-pv-90870620,key-pv-90838750,key-pv-90173668,key-pv-89866516,key-pv-90875304,key-pv-90861689,key-pv-908761393,key-pv-90896692,key-pv-90873175,key-pv-90599927,key-pv-90835738,key-pv-77158220".split(",");
                String s = serializeToString(keys);
                String s1 = LibGoRedis.INSTANCE.MGet(s);

                System.out.println(finalI + ":" + s1);
                countDownLatch.countDown();
            });

            thread.start();
        }

        countDownLatch.await();

//        System.out.println("中文");
    }

    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
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

//    private static void test() {
//        String[] nums = new String[]{"hello", "world"};
//        Memory arr = new Memory(nums.length * Native.getNativeSize(String.class));
//        arr.write(0, nums, 0, nums.length);
//        // fill in the GoSlice class for type mapping
//        GoSlice slice = new GoSlice;
//        slice.data = arr;
//        slice.len = nums.length;
//        slice.cap = nums.length;
//    }
}