package wrq.rotation.gateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;
public class JWTUtil {
    private static String SECRET="jojo";//密钥

    public static String getToken(Map<String, String> payload){//生成JWT
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        JWTCreator.Builder builder = JWT.create();
        payload.forEach((key,value)->{
            builder.withClaim(key,value);//Payload
        });
        String token = builder
                .withExpiresAt(calendar.getTime())//JWT过期时间
                .sign(Algorithm.HMAC256(SECRET));//Signature
        return token;
    }

    public static DecodedJWT verifyToken(String token){//验证JWT
        DecodedJWT payload=JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);//解码
        return payload;
    }

}
