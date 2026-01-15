package com.ddf.group.purchase.api.request.group;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

/**
 * <p>通过微信接龙文本创建关联团购信息</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 23:15
 */
@Data
public class CreateFromWxJieLongRequest implements Serializable {

    private static final long serialVersionUID = -1860989076398913483L;

    /**
     * 接龙文本，全文粘贴过来，保留格式
     * 示例文本，注意每行都是真实换行的
     *
     * #接龙
     * 这个是接龙填的名称，简介啥的
     * 例 这个是对下面格式的说明，对系统没太大用处，无法解析，主要是接龙时别人用的
     *
     * 1.尘 这个是接龙的内容
     * 2.xx 这个是第二个用户在接龙
     *
     * 这里有个接龙补充信息，对系统也没用处
     *
     * =========================
     * 示例文本2，可以看到无论填不填写示例都是固定空一个空行
     * #接龙
     * 接龙，这次不填写接龙格式，也不填写补充信息
     *
     * 1.尘 消毒液两瓶
     * 2.295-909 消毒药三瓶
     *
     */
    @NotBlank(message = "接龙文本不能为空")
    private String text;

    /**
     * 解析接龙文本
     *
     * @return
     */
    public Data parseData() {
        Assert.isTrue(text != null && text.startsWith("#接龙"), "接龙文本不合法，请完整复制接龙文本内容");
        final String[] split = text.split("\n");
        // 接龙内容名称,开头的固定内容格式#接龙跳过不处理
        final Data data = Data.builder()
                .name(split[1])
                .build();
        List<UserData> userDataList = new ArrayList<>();
        String str;
        for (int i = 2; i < split.length; i++) {
            str = split[i];
            if (i == 2) {
                if (StrUtil.isNotBlank(str)) {
                    data.setExample(str);
                }
                continue;
                // 过滤本来就是空的，或者过滤掉示例
            } else if (StrUtil.isBlank(str) || (i == 3 &&  StrUtil.isNotBlank(str) && data.getExample() != null)) {
                continue;
            } else if (i == split.length - 1 && StrUtil.isBlank(split[i - 1])) {
                data.setRemarks(str);
                continue;
            }
            // 这里都是具体的接龙内容了
            // 1.尘 这个是接龙的内容
            final String[] userDataStr = str.split(" ");
            userDataList.add(UserData.builder()
                    .name(userDataStr[0].split("\\.")[1])
                    .content(userDataStr[1])
                    .build());
        }
        data.setUserDataList(userDataList);
        Assert.isTrue(CollUtil.isNotEmpty(userDataList), "接龙文本不完整，无参与人员~");
        return data;
    }


    /**
     * 解析出来的数据
     */
    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {

        /**
         * 接龙名称
         */
        private String name;

        /**
         * 接龙示例
         */
        private String example;

        /**
         * 补充信息
         */
        private String remarks;

        /**
         * 用户接龙内容
         */
        private List<UserData> userDataList;

    }


    /**
     * 用户的接龙内容
     */
    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserData {

        /**
         * 接龙时用户在群里的名称， 在本系统里目前必须是改好的名称即多少栋多少号，如295-909，否则无法对应数据
         */
        private String name;

        /**
         * 接龙的内容
         */
        private String content;

        /**
         * 获取楼栋号，必须在群聊中按规则改过名
         *
         * @return
         */
        public String getBuildingNo() {
            if (name != null && name.contains("-") && NumberUtil.isNumber(Character.toString(name.charAt(0)))) {
                return name.split("-")[0];
            }
            return null;
        }

        /**
         * 获取房间号，必须在群聊中按规则改过名
         *
         * @return
         */
        public String getRoomNo() {
            if (name != null && name.contains("-") && NumberUtil.isNumber(Character.toString(name.charAt(0)))) {
                return name.split("-")[1];
            }
            return null;
        }
    }


    public static void main(String[] args) {
        String text = "#接龙\n" +
                "这个是接龙填的名称，简介啥的\n" +
                "例 这个是对下面格式的说明，对系统没太大用处，无法解析，主要是接龙时别人用的\n" +
                "\n" +
                "1.295-909 五斤西瓜，十斤苹果\n" +
                "2.295-910 六斤西瓜，三斤苹果\n" +
                "3.295-911 十斤西瓜，五斤苹果\n" +
                "\n" +
                "大家快点接龙，三点结束";
        final CreateFromWxJieLongRequest request = new CreateFromWxJieLongRequest();
        request.setText(text);
        System.out.println(request.parseData());

        text = "#接龙\n" +
            "接龙，这次不填写接龙格式，也不填写补充信息\n" +
            "\n" +
            "1.尘 消毒液两瓶\n" +
            "2.295-909 消毒药三瓶";
        request.setText(text);
        System.out.println(request.parseData());

        Map<String, Object> map = new HashMap<>();
        map.put("request", request);
        System.out.println(map);
    }
}
