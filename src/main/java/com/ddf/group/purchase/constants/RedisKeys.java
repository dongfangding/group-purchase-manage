package com.ddf.group.purchase.constants;

/**
 * <p>redis key 定义</p >
 *
 * 这个类的目的是还是想通过方法来生成key， 这样统一用到key的时候通过这个方法来收口，万一key规则变动，比较容易识别。
 * 这样方便辨识key规则中的变量到底存的是什么
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/21 20:15
 */
public interface RedisKeys {

    /**
     * 短信验证码key
     * %s mobile
     * %s 随机字符串
     */
    String SMS_CODE_KEY = "sms_code:%s:%s";


    /**
     * 获取短信验证码key
     *
     * @param mobile
     * @param random
     * @return
     */
    static String getSmsCodeKey(String mobile, String random) {
        return String.format(SMS_CODE_KEY, mobile, random);
    }


}
