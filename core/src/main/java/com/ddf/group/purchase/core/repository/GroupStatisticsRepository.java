package com.ddf.group.purchase.core.repository;

import com.ddf.group.purchase.api.enume.GroupStatisticsTypeEnum;
import com.ddf.group.purchase.core.constants.RedisKeys;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * <p>团购相关统计仓储</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 16:55
 */
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GroupStatisticsRepository {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 增加团购统计次数
     *
     * @param groupId
     * @param userId
     * @param type
     * @return
     */
    public Long incrementStatisticsValue(Long groupId, Long userId, GroupStatisticsTypeEnum type) {
        try {
            return stringRedisTemplate.opsForHash().increment(RedisKeys.getGroupStatisticsKey(groupId), type.name(), 1);
        } catch (Exception e) {
            log.error("[团购统计]-增加统计次数失败， groupId = {}, type = {}, userId = {}", groupId, type, userId);
        }
        return 0L;
    }

    /**
     * 获取团购统计次数
     *
     * @param groupId
     * @param type
     * @return
     */
    public Long getStatisticsValue(Long groupId, GroupStatisticsTypeEnum type) {
        try {
            final Object o = stringRedisTemplate.opsForHash()
                    .get(RedisKeys.getGroupStatisticsKey(groupId), type.name());
            return Objects.isNull(o) ?  0L: Long.parseLong(o.toString());
        } catch (Exception e) {
            log.error("[团购统计]-获取统计次数失败， groupId = {}, type = {}", groupId, type);
        }
        return 0L;
    }


    /**
     * 获取团购的所有统计指标
     *
     * @param groupId
     * @return
     */
    public Map<Object, Object> getStatisticsAllValue(Long groupId) {
        return stringRedisTemplate.opsForHash().entries(RedisKeys.getGroupStatisticsKey(groupId));
    }
}
