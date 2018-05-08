package cn.sugar.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 创建时间:   2018/5/5
 * ZhangQing
 * 功能描述:
 */
public class Demo {

    @Test
    public void demo1() {

        Jedis jedis = new Jedis("192.168.25.3", 6379);
        // jedis.set("name", "张三");
        System.out.println(jedis.get("name"));

    }

    @Test
    public void demo2() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config, "192.168.25.3", 6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("name"));
        jedis.close();

    }
}
