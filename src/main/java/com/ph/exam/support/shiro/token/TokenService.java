package com.ph.exam.support.shiro.token;

public interface TokenService {


    /**
     * 创建token
     *
     * @param jobNumber 工号
     * @param salt      盐
     * @param issuer    签发机构
     */
    String createToken(String jobNumber, String salt, String issuer);

    /**
     * 验证token
     *
     * @param token  token
     * @param salt   盐
     * @param issuer 签发机构
     */
    void verify(String token, String salt, String issuer);

    /**
     * 获取工号
     *
     * @param token token
     * @return java.lang.String
     * @author SunMingyao

     */
    String getJobNumber(String token);

    /**
     * 获取salt
     *
     * @param token token
     * @return java.lang.String
     * @author SunMingyao
     *
     */
    String getSalt(String token);

    /**
     *  获取签发机构
     *
     * @author SunMingyao
     * @param token  token
     */
    String getIssuer(String token);

}
