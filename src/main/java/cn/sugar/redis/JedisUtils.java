package cn.sugar.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 创建时间:   2018/5/5
 * ZhangQing
 * 功能描述:
 */
public class JedisUtils {

    private final static JedisPool jedisPool;

    static {
        // 加载配置文件
        InputStream in = JedisUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获得池子对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大闲置个数
        poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));
        // 最小闲置个数
        poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));
        // 最大连接数
        poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));
        jedisPool = new JedisPool(poolConfig, pro.getProperty("redis.url"), Integer.parseInt(pro.get("redis.port")
                .toString()));
    }

    /**
     * 从池子中获取jedis
     *
     * @return
     */
    public static Jedis returnJedis() {
        return jedisPool.getResource();
    }
}