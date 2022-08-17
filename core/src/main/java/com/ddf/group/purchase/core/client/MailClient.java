package com.ddf.group.purchase.core.client;

import cn.hutool.core.util.RandomUtil;
import com.ddf.boot.common.core.util.MailUtil;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import com.ddf.group.purchase.core.config.properties.ApplicationProperties;
import com.ddf.group.purchase.core.repository.CommonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <p>发送邮件</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/28 00:01
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class MailClient {

    private final MailUtil mailUtil;
    private final ApplicationProperties applicationProperties;
    private final CommonRepository commonRepository;
    private final UserClient userClient;


    /**
     * 发送邮箱绑定激活邮件
     *
     * @param userId
     * @param email
     */
    @Async("mailThreadPool")
    public void sendEmailActive(Long userId, String email) {
        String activeToken = generateEmailToken(userId, email);
        String url = applicationProperties.getMailActiveUrl() + "?token=" + activeToken;
        String subject = "团购吧邮箱激活";
        String content = "<p>您的邮箱绑定请求已收到，请点击以下链接进行验证</p>";
        content += "<a href='" + url + "'>" + url + "</a>";
        content += "<p>如果以上链接无法打开，请复制链接到浏览器中打开，该链接五分钟内有效，请及时验证。</p>";
        content += "(该邮件由系统自动发出，请勿回复)";
        mailUtil.sendMimeMail(new String[] {email}, subject, content);
    }


    /**
     * 团购状态变更提醒邮件
     *
     * @param email
     * @param groupName
     * @param status
     */
    @Async("mailThreadPool")
    public void sendGroupLatestInfo(String email, String groupName, GroupPurchaseStatusEnum status) {
        String subject = "团购吧[" + groupName + "]信息变更提醒";
        String content = "<p>您在团购吧参与的团购[" + groupName + "]最新状态变更为<font color='red'>" + status.getValue()
                + "</fond>，请前往系统查询详情</p>"
                + "(该邮件由系统自动发出，请勿回复)";
        mailUtil.sendMimeMail(new String[] {email}, subject, content);
    }

    /**
     * 生成邮箱激活token
     *
     * @param userId
     * @param email
     * @return
     */
    private String generateEmailToken(Long userId, String email) {
        String token = RandomUtil.randomString(64);
        commonRepository.setEmailActiveToken(email, token, userId);
        return token;
    }

}
