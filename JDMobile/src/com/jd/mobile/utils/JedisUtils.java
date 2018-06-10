package com.jd.mobile.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    private static JedisPoolConfig config;
    private static JedisPool pool;
    static {
        config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(2);
        pool = new JedisPool(config,"localhost",6379);
    }
    public static Jedis getJedis(){
        return pool.getResource();
    }
    public static void closeJedis(Jedis jedis){
        jedis.close();
    }
 }
