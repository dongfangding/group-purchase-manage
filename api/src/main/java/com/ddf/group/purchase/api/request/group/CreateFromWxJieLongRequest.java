package com.ddf.group.purchase.api.request.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>通过微信接龙文本创建关联团购信息</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 23:15
 */
@Data
public class CreateFromWxJieLongRequest implements Serializable {

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
     * 1.尘 接龙内容
     *
     */
    @NotBlank(message = "接龙文本不能为空")
    private String text;


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

        final String[] split = text.split("\n");
        // 接龙内容名称,开头的固定内容格式#接龙跳过不处理
        String name = split[1];
        // 接龙格式示例
        String example = null;
        // 具体接龙内容
        List<String> userText = new ArrayList<>();
        // 补充信息
        String remarks = null;
        for (int i = 2; i < split.length; i++) {
            if (i == 2 && split[i] != null) {
                example = split[i];
            } else
            // 说明开始到了分割接龙内容和补充内容的地方了
            if (split[i - 1] == null) {
                remarks = split[i];
            } else {
                if (split[i] == null) {
                    continue;
                }
                // 这里都是具体的接龙内容了
                userText.add(split[i]);
            }
        }
        System.out.println("name = " + name);
        System.out.println("example = " + example);
        System.out.println("userText = " + userText);
        System.out.println("remarks = " + remarks);
    }

}
