package com.ddf.group.purchase.core.assembler;

import cn.hutool.core.collection.CollUtil;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import com.ddf.group.purchase.api.enume.GroupStatisticsTypeEnum;
import com.ddf.group.purchase.api.response.group.GroupPurchaseTraceDTO;
import com.ddf.group.purchase.api.response.group.GroupStatisticsDTO;
import com.ddf.group.purchase.api.response.group.GroupTraceStepRes;
import com.ddf.group.purchase.core.converter.GroupPurchaseInfoConvert;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseTrace;
import com.ddf.group.purchase.core.repository.GroupStatisticsRepository;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>团购相关组装器</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 18:21
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GroupAssembler {

    private final GroupStatisticsRepository groupStatisticsRepository;


    /**
     * 组装团购的统计数据
     * fixme 怎么批量呢?
     *
     * @param groupIds
     * @return
     */
    public Map<Long, GroupStatisticsDTO> mapGroupStatistics(List<Long> groupIds) {
        Map<Long, GroupStatisticsDTO> rtnMap = Maps.newHashMapWithExpectedSize(groupIds.size());
        for (Long id : groupIds) {
            final Map<Object, Object> valueMap = groupStatisticsRepository.getStatisticsAllValue(id);
            final GroupStatisticsDTO statisticsDTO = GroupStatisticsDTO.init();
            if (CollUtil.isEmpty(valueMap)) {
                rtnMap.put(id, statisticsDTO);
                continue;
            }
            valueMap.forEach((key, value) -> {
                if (Objects.equal(GroupStatisticsTypeEnum.VIEW.name(), key)) {
                    statisticsDTO.setViewCount(Long.parseLong(value.toString()));
                } else if (Objects.equal(GroupStatisticsTypeEnum.PAY.name(), key)) {
                    statisticsDTO.setPayCount(Long.parseLong(value.toString()));
                } else if (Objects.equal(GroupStatisticsTypeEnum.RETURN.name(), key)) {
                    statisticsDTO.setReturnCount(Long.parseLong(value.toString()));
                }
            });
            rtnMap.put(id, statisticsDTO);
        }
        return rtnMap;
    }

    public GroupTraceStepRes traceAssembler(List<GroupPurchaseTrace> traceList) {
        final GroupPurchaseTrace latestTrace = traceList.get(traceList.size() - 1);
        final GroupTraceStepRes res = new GroupTraceStepRes();
        if (Objects.equal(GroupPurchaseStatusEnum.CANCELED.getValue(), latestTrace.getStatus())) {
            res.setTraces(GroupPurchaseInfoConvert.INSTANCE.convertTrace(traceList));
            res.setCurrentStep(traceList.size());
            return res;
        }
        final Map<Integer, GroupPurchaseTrace> traceMap = traceList.stream()
                .collect(Collectors.toMap(GroupPurchaseTrace::getStatus, Function.identity()));
        final List<GroupPurchaseTraceDTO> dtos = GroupPurchaseStatusEnum.listNormalStep();
        for (GroupPurchaseTraceDTO dto : dtos) {
            if (traceMap.containsKey(dto.getStatus())) {
                dto.setCtime(traceMap.get(dto.getStatus()).getCtime());
            }
        }
        res.setCurrentStep(GroupPurchaseStatusEnum.resolve(latestTrace.getStatus()).getStep());
        res.setTraces(dtos);
        return res;
    }
}
