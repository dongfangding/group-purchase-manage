package com.ddf.group.purchase.model.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>登录返回接口</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/23 23:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * 身份token
     */
    private String token;
}
