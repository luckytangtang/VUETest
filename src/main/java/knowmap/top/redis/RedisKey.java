package knowmap.top.redis;

import knowmap.top.managers.userAccount.entity.UserAccount;
import knowmap.top.utils.HashUtils;

public class RedisKey {
    private static volatile RedisKey singleInstance;

    public static RedisKey getInstance() {
        if (singleInstance == null) {
            synchronized (RedisKey.class) {
                if (singleInstance == null) {
                    singleInstance = new RedisKey();
                }
            }
        }

        return singleInstance;
    }

    public <T> String createKey(Class<T> clazz, Long id) {
        if (clazz == null || id == null) {
            throw new RuntimeException("params is null");
        }
        // hash key后长度一样
        return HashUtils.hash(clazz.getSimpleName() + id);
    }

    public static void main(String[] args) {
        String s = RedisKey.getInstance().createKey(UserAccount.class, 1000000L);
        System.out.println(s.length());
    }
}
