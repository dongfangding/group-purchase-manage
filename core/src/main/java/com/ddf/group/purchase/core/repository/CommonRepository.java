package com.ddf.group.purchase.core.repository;

import com.ddf.group.purchase.core.constants.RedisKeyEnum;
import com.ddf.group.purchase.core.constants.RedisKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>通用的或者一些边缘业务的仓储</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/28 15:20
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CommonRepository {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 设置短信验证码
     *
     * @param mobile
     * @param uuid
     * @param randomCode
     * @return
     */
    public void setSmsCode(String mobile, String uuid, String randomCode) {
        stringRedisTemplate.opsForValue().set(
                RedisKeys.getSmsCodeKey(mobile, uuid), randomCode,
                RedisKeyEnum.SMS_CODE_KEY.getTimeout());
    }

    /**
     * 获取短信验证码
     *
     * @param mobile
     * @param uuid
     * @return
     */
    public String getSmsCode(String mobile, String uuid) {
        return stringRedisTemplate.opsForValue().get(RedisKeys.getSmsCodeKey(mobile, uuid));
    }

    /**
     * 设置邮箱激活token
     *
     * @param email
     * @param token
     * @return
     */
    public void setEmailActiveToken(String email, String token) {
        stringRedisTemplate.opsForValue().set(RedisKeys.getEmailActiveTokenKey(email), token, RedisKeyEnum.EMAIL_ACTIVE_TOKEN_KEY.getTimeout());
    }

    /**
     * 获取邮箱激活token
     *
     * @param email
     * @return
     */
    public String getEmailActiveToken(String email) {
        return stringRedisTemplate.opsForValue().get(RedisKeys.getEmailActiveTokenKey(email));
    }


}
