package knowmap.top.redis;

import com.jfinal.plugin.redis.Redis;

public class RedisManager {
    private volatile static RedisManager singleInstance;

    private RedisManager() {}

    private static RedisManager getInstance() {
        if (singleInstance == null) {
            synchronized (RedisManager.class) {
                if (singleInstance == null) {
                    singleInstance = new RedisManager();
                }
            }
        }

        return singleInstance;
    }


    public void set(Long id, Object o, int seconds, String cacheName) {
        if (id == null || o == null) {
            throw new RuntimeException("params is invalid");
        }

        if (seconds <= 0) {
            // 永久存储
            Redis.use(cacheName).set(RedisKey.getInstance().createKey(o.getClass(), id), o);
        } else {
            // 有限时间存储
            Redis.use(cacheName).setex(RedisKey.getInstance().createKey(o.getClass(), id), seconds, o);
        }
    }

    public <T> T get(Long id, Class<T> clazz, String cacheName) {
        if (id == null || clazz == null) {
            throw new RuntimeException("params is invalid");
        }

        return (T)Redis.use(cacheName).get(RedisKey.getInstance().createKey(clazz, id));
    }
}
