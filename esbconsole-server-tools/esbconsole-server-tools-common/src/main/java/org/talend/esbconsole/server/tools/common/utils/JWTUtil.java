package org.talend.esbconsole.server.tools.common.utils;

import org.talend.esbconsole.server.tools.common.constant.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT的工具类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class JWTUtil {

    // 有效期为
    public static final Long JWT_EFFICIENT_TIME = 7 * 24 * 60 * 60 * 1000L;// 60 * 60 *1000 一个小时
    // 设置秘钥明文
    public static final String JWT_KEY = "esbconsole";

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    /**
     * 生成jtw
     *
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject       token中要存放的数据（json格式）
     * @param efficientTime token超时时间
     * @return
     */
    public static String createJWT(String subject, Long efficientTime) {
        JwtBuilder builder = getJwtBuilder(subject, efficientTime, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long efficientTime, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillisecond = System.currentTimeMillis();
        Date nowData = new Date(nowMillisecond);
        if (efficientTime == null) {
            efficientTime = JWTUtil.JWT_EFFICIENT_TIME;
        }
        long expireTime = nowMillisecond + efficientTime;
        Date expDate = new Date(expireTime);
        return Jwts.builder()
                // 唯一的ID
                .setId(uuid)
                // 主题 可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("talend")
                // 签发时间
                .setIssuedAt(nowData).signWith(signatureAlgorithm, secretKey) // 使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

//    public static void main(String[] args) throws Exception {
//        // String jwt = createJWT("2123");
//        Claims claims = parseJWT(
//                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyOTY2ZGE3NGYyZGM0ZDAxOGU1OWYwNjBkYmZkMjZhMSIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTYzOTk2MjU1MCwiZXhwIjoxNjM5OTY2MTUwfQ.NluqZnyJ0gHz-2wBIari2r3XpPp06UMn4JS2sWHILs0");
//        String subject = claims.getSubject();
//        System.out.println(subject);
//        // System.out.println(claims);
//    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWTUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(Constants.AUTHORIZATION);
        if (StringUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }
}
