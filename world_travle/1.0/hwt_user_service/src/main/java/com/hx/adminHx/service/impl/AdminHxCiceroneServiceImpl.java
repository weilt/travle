package com.hx.adminHx.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
import com.hwt.domain.mapper.cicerone.HxCiceroneInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.adminHx.service.AdminHxCiceroneService;
import com.hx.adminHx.service.vo.PageResultHxCiceroneInfo;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.service.ImService;

@Service
public class AdminHxCiceroneServiceImpl implements AdminHxCiceroneService{

	@Autowired
	private HxCiceroneInfoMapper hxCiceroneInfoMapper;
	@Autowired
	private HxUserMapper hxUserMapper;
	@Autowired
	private ImService imService;
	@Override
	public PageResultHxCiceroneInfo queryByMap(Map<String, Object> map) {
		//总条数
		long count = hxCiceroneInfoMapper.queryCountByMapToAdmin(map);
		//数据
		List<Map<String, Object>> list = hxCiceroneInfoMapper.queryByMapToAdmin(map);
		
		//总申请数
		long applyTotal = 0;
//		//总通过总数
//		long adoptTotal = hxCiceroneInfoMapper.queryCountByStatus(2);
//		
//		//未处理总数
//		long unauditedTotal = hxCiceroneInfoMapper.queryCountByStatus(1);
//		//拒绝总数
//		long failedTotal = hxCiceroneInfoMapper.queryCountByStatus(3);
		//总通过总数
		long adoptTotal =0;
		
		//未处理总数
		long unauditedTotal = 0;
		//拒绝总数
		long failedTotal = 0;
		
		List<HxCiceroneInfo> ciceroneInfos = hxCiceroneInfoMapper.queryAll();
		
		if(ObjectHelper.isNotEmpty(ciceroneInfos)){
			applyTotal = ciceroneInfos.size();
			for (HxCiceroneInfo hxCiceroneInfo : ciceroneInfos) {
				if (hxCiceroneInfo.getStatus()==1){
					unauditedTotal++;
				}else if(hxCiceroneInfo.getStatus()==2){
					adoptTotal++;
				}else{
					failedTotal++;
				}
			}
		}
		
		PageResultHxCiceroneInfo pageResult = new PageResultHxCiceroneInfo();
		pageResult.setCount(count);
		pageResult.setDataList(list);
		pageResult.setAdoptTotal(adoptTotal);
		pageResult.setApplyTotal(applyTotal);
		pageResult.setFailedTotal(failedTotal);
		pageResult.setUnauditedTotal(unauditedTotal);
		return pageResult;
	}
	
	@Transactional
	@Override
	public void cicerone_verification(Integer status, String reason, Long user_id) throws Exception {
		String msg = "你申请的导游经过审核，";
		HxCiceroneInfo record = new HxCiceroneInfo();
		if(status == 3 ){
			if (ObjectHelper.isEmpty(reason)){
				throw new RuntimeException("未通过，必须添加原因");
			}
			msg += "未通过，原因："+reason;
		}else{
			msg += "通过";
			record.setExamine_time(new Date().getTime());
		}
		
		
		record.setUser_id(user_id);
		record.setStatus(status);
		record.setReason(reason);
		int i = hxCiceroneInfoMapper.cicerone_verification(record);
		if (i==0){
			throw new RuntimeException("该数据已审核了");
		}
		
		//发送消息
		//申请imid
		String im_id = hxUserMapper.queryImIdByUserId(user_id);
		imService.singleNotice(HXSystemConfig.HX_OFFICIAL_IM_ID, im_id, GsonUtil.toJson(new FriendOperationNotice(im_id, null, 12, status+"")), 2);
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		String sendMsgPTOP = imService.sendMsgPTOP(HXSystemConfig.HX_OFFICIAL_IM_ID, "0", im_id, 0, GsonUtil.toJson(map));
		System.out.println(sendMsgPTOP);
	}

	@Override
	public Map<String, Object> query_infoById(Long id) {
		
		return hxCiceroneInfoMapper.queryById(id);
	}

}
