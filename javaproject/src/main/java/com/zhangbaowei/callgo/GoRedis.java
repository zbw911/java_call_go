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

package com.zhangbaowei.callgo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangbaowei.callgo.types.GoString;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple client that requests a mget from the
 *
 * @author zhangbaowei
 */
public class GoRedis {
    //    private static boolean init = false;
    private final String nodes;
    private final String password;

    public GoRedis(String nodes, String password) {
        this.nodes = nodes;
        this.password = password;
//        LibGoRedis.INSTANCE.CreateRedisClient(new GoString.ByValue(nodes), new GoString.ByValue(password));
    }

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
        String s = serializeToString(keys);
        String s1 = LibGoRedis.INSTANCE.MGet(s);
//        System.out.println(s1);
        List<String> results = deserializeFromString(s1, new TypeReference<List<String>>() {
        });
        return zipToMap(keys, results);
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
