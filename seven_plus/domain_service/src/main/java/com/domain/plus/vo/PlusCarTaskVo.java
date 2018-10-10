package com.domain.plus.vo;

import com.domain.plus.entity.PlusCar;
import lombok.Data;

/**
 * @Auther: Zhoudu
 * @Date: 2018/9/3 15:34
 * @Description:
 */
@Data
public class PlusCarTaskVo extends PlusCar {
    private Long orderId;
}
