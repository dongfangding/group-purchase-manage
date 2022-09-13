package com.ddf.group.purchase.api.response.group;

import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
* <p>团购市场列表响应</p >
*
* @author Snowball
* @version 1.0
* @date 2022/05/23 23:11
*/
@Data
public class MarketplaceGroupPurchaseListResponse implements Serializable {

    private static final long serialVersionUID = -7237625160468971690L;

    private Long id;

    /**
     * 团购名称
     */
    private String name;

    /**
     * 团长uid
     */
    private Long groupMasterUid;

    /**
     * 团长名称
     */
    private String groupMasterName;

    /**
     * 团长头像
     */
    private String groupMasterAvatarUrl;

    /**
     * 状态
     * 1. 已创建
     * 2. 已到货
     * 3. 已完成
     * 4. 已取消
     */
    private Integer status;

    /**
     * 团购富文本内容
     */
    private String content;

    /**
     * 创建时间秒时间戳
     */
    private Long ctime;

    /**
     * 更新时间秒时间戳
     */
    private Long mtime;

    /**
     * 是否公开到团购市场，不公开的话，不会在市场展示
     */
    private Boolean publicFlag;

    /**
     * 个人微信名片二维码地址
     */
    private String wxIdCardUrl;

    /**
     * 图片附件地址，多个用逗号分隔
     */
    private String picUrls;

    public List<String> getPicUrlList() {
        List<String> imageList = new ArrayList<>();
        if (StringUtils.isNotBlank(picUrls)) {
            imageList.addAll(Arrays.stream(picUrls.split(",")).collect(Collectors.toList()));
        }
        if (StringUtils.isBlank(goodPic)) {
            imageList.add(goodPic);
        }
        return imageList;
    }

    /**
     * 视频附件地址
     */
    private String videoUrl;

    /**
     * 团购开始时间
     */
    private Long startTime;

    /**
     * 团购结束时间
     */
    private Long endTime;

    /**
     * 服务小区
     */
    private Long serviceCommunityId;

    /**
     * 商品记录id
     */
    private Long goodId;


    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品描述
     */
    private String goodDescription;

    /**
     * 商品图片
     */
    private String goodPic;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 剩余商品库存
     */
    private Integer remainingStock;

    /**
     * 限制类型，默认不限购，如限购数量上限、起购数量
     */
    private String limitType;

    /**
     * 限制数量，与limit_type匹配使用
     */
    private Long limitValue;

    public String getStatusName() {
        return GroupPurchaseStatusEnum.resolve(status).getDesc();
    }

    public String getFormatStartTime() {
        return DateUtils.standardFormatSeconds(startTime);
    }

    public String getFormatEndTime() {
        return DateUtils.standardFormatSeconds(endTime);
    }
}
