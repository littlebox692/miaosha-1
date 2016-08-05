package com.miaosha.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.miaosha.entity.Miaosha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Yan on 2016/8/3.
 */
public class RedisDao {

    private final JedisPool jedisPool;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RuntimeSchema<Miaosha> miaoshaRuntimeSchema = RuntimeSchema.createFrom(Miaosha.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Miaosha getMiaosha(long miaoshaId) {
        //从redis缓存获取秒杀对象
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                //redis 是key-value型数据库，数据保存为键值对
                //jedis没有实现对象序列化，需要自定义序列化，采用protostuff
                //需要对对象进行序列化
                //从内存中读取对象-->byte[]数组中-->反序列化-->获得对象Object
                String key = "miaoshaId:" + miaoshaId;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    Miaosha miaosha = miaoshaRuntimeSchema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, miaosha, miaoshaRuntimeSchema);
                    //反序列化成功
                    return miaosha;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    public String putMiaosha(Miaosha miaosha) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "miaoshaId:" + miaosha.getMiaoshaId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(miaosha, miaoshaRuntimeSchema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                //若上面的方法执行正确则返回字符串“ok”，若执行错误，则返回错误信息。
                return result;
            } finally {
                jedis.close();
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }
}
