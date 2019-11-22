package knowmap.top.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jfinal.kit.PropKit;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt 工具
 */
public class JwtUtils {
    /**
     * 签发JWT
     * @param
     * @param subject 可以是JSON数据 尽可能少
     * @return  String
     *
     */
    public static String createJWT(String subject, String secret) {
        PropKit.use("config.txt");
        Date expiresDate = DateUtils.addDays(new Date(), PropKit.getInt("JwtExpire"));
        //签发时间
        Date istDate = new Date();

        //设置过期时间
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = null;
        try {
            token = JWT.create()
                    .withHeader(map)
                    .withClaim("object",  subject)
                    .withExpiresAt(expiresDate)
                    .withIssuedAt(istDate)
                    .sign(Algorithm.HMAC256(secret));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return token;
    }

    /**
     * 验证JWT
     * @param token
     * @return null 校验失败， 否则校验成功
     */
    public static String validateJWT(String token, String secret) {
        JWTVerifier verifier;
        DecodedJWT jwt;
        try {
            verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return jwt.getClaims().get("object").asString();
    }


    public static String createSecret() {
        PropKit.use("config.txt");
        int len;
        if ((len = PropKit.getInt("SecretLen")) <= 5) {
            len = 10;
        }
        return RandomStringUtils.randomAscii(len);
    }

    public static String createSalt() {
        return RandomStringUtils.randomAscii(6);
    }

    public static void main(String[] args) {
        System.out.println(createSecret());
    }
}
