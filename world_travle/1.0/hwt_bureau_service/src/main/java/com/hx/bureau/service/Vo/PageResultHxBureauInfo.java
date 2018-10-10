package com.hx.bureau.service.Vo;

import java.util.Map;

import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hx.core.page.asyn.PageResult;

/**
 * 
 * @author Administrator
 *
 */
public class PageResultHxBureauInfo extends PageResult<Map<String, Object>> {
	
	private Long applyTotal;//总申请数
	
	private Long adoptTotal;//通过总数
	
	private Long unauditedTotal;//未处理总数

	private Long failedTotal;//拒绝总数

	public Long getApplyTotal() {
		return applyTotal;
	}

	public void setApplyTotal(Long applyTotal) {
		this.applyTotal = applyTotal;
	}

	public Long getAdoptTotal() {
		return adoptTotal;
	}

	public void setAdoptTotal(Long adoptTotal) {
		this.adoptTotal = adoptTotal;
	}

	public Long getUnauditedTotal() {
		return unauditedTotal;
	}

	public void setUnauditedTotal(Long unauditedTotal) {
		this.unauditedTotal = unauditedTotal;
	}

	public Long getFailedTotal() {
		return failedTotal;
	}

	public void setFailedTotal(Long failedTotal) {
		this.failedTotal = failedTotal;
	}
	
	
	
}
