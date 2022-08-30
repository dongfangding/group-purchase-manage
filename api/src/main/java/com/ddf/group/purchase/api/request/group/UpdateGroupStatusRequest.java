package com.ddf.group.purchase.api.request.group;

import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>更改团购状态参数类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/07/14 18:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGroupStatusRequest implements Serializable {
    private static final long serialVersionUID = 1516322558409231083L;

    /**blockPuzzle
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 」
     * 要更改的状态{@link GroupPurchaseStatusEnum}
     */
    @NotNull(message = "要更改的状态不能为空")
    private GroupPurchaseStatusEnum status;

    public static void main(String[] args) {
        int zeroTimes = 0;
        int hit = 0;
        for (int i = 0; i < 10000; i++) {
            hit = randomAmount();
            if (hit == 0) {
                zeroTimes ++;
            }
        }
        System.out.println("zeroTimes = " + zeroTimes);
    }

    public static Integer randomAmount() {
        Map<Integer, Integer> rateMap = new HashMap<>();
        rateMap.put(100, 5000);
        rateMap.put(200, 4000);
        rateMap.put(300, 3000);
        rateMap.put(400, 2000);
        rateMap.put(500, 1000);
        rateMap.put(1000, 500);
        rateMap.put(1500, 300);
        rateMap.put(6000, 0);

        int random = new Random().nextInt(10000);
        final Set<Integer> rateList = rateMap.keySet();
        for (Integer currentRate : rateList) {
            if ((random -= currentRate) < 0) {
                return rateMap.get(currentRate);
            }
        }
        System.out.println("random = " + random);
        throw new RuntimeException("概率错误~");
    }
}
