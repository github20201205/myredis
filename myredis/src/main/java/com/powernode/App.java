package com.powernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JedisPool jedisPool = RedisUtils.open("192.168.186.132", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushAll();
        jedis.set("name", "lisi");
        jedis.set("age", "12");

        Transaction multi = jedis.multi();
        multi.set("sex", "nan");
        List<Object> exec = multi.exec();
        for (Object object : exec)
        {
            System.out.println(object);
        }
        jedisPool.close();
    }
}
