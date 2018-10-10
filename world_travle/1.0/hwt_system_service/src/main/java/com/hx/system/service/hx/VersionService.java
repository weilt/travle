package com.hx.system.service.hx;

import java.util.List;

import com.hwt.domain.entity.version.vo.HxVersionVo;

public interface VersionService {

	/**
	 * 获得当前版本详情
	 * @return
	 */
	List<HxVersionVo> verificationVersion();

}
