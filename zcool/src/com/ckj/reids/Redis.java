package com.ckj.reids;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class Redis {
    public Jedis js = new Jedis("124.223.167.161", 6379);

    public Redis() {
        js.auth("021112Cz");
        js.connect();
    }
    public String hget(String key, String field) {
        return js.hget(key, field);
    }

    public Set<String> themeKeys(String theme) {
        return js.keys("dataClassByTheme:" + theme + "*");
    }

    public Set<String> nameKeys(String name) {
        return js.keys("dataClassByTheme:*:*" + name + "*");
    }


    public List<String> hmget(String key, String... fields) {
        return js.hmget(key, fields);
    }

    public Set<String> zrange(String key, int start, int end) {
        return js.zrange(key, start, end);
    }
}
