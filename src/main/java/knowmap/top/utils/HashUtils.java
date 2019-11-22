package knowmap.top.utils;

import com.jfinal.kit.HashKit;

public class HashUtils {
    public static String hash(byte[] bytes) {
        return hash(new String(bytes));
    }

    public static String hash(String str) {
        return HashKit.md5(str);
    }

    /**
     *
     * @param baseMd5Hash 前端传过来经过md5加密后的字符串
     * @param salt 数据库存的salt
     * @return 此为加密后的结果，将与数据库password作对比
     */
    public static String hash(String baseMd5Hash, String salt) {
        return HashKit.sha1(hash(baseMd5Hash + salt));
    }
}

