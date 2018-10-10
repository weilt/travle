package com.hwt.domain.entity.mg.scenic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/6/25 17:08
 * @Description:
 */
@ApiModel(value = "景点首页返回")
public class HxSpotIndexVO implements Serializable {
    @ApiModelProperty(value = "热门景点")
    private List<ScenicSpotVO> hotSpot;
    @ApiModelProperty(value = "推荐景点")
    private List<ScenicSpotVO> recommendSpot;

    public List<ScenicSpotVO> getHotSpot() {
        return hotSpot;
    }

    public void setHotSpot(List<ScenicSpotVO> hotSpot) {
        this.hotSpot = hotSpot;
    }

    public List<ScenicSpotVO> getRecommendSpot() {
        return recommendSpot;
    }

    public void setRecommendSpot(List<ScenicSpotVO> recommendSpot) {
        this.recommendSpot = recommendSpot;
    }
}
