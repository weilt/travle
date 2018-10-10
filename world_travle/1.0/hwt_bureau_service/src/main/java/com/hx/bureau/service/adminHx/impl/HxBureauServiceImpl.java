package com.hx.bureau.service.adminHx.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hwt.domain.entity.bureau.HxBureauUser;
import com.hwt.domain.mapper.admin.AdminSystemconfigMapper;
import com.hwt.domain.mapper.bureau.HwTravelBureauMapper;
import com.hwt.domain.mapper.bureau.HxBureauUserMapper;
import com.hx.bureau.service.Vo.PageResultHxBureauInfo;
import com.hx.bureau.service.adminHx.HxBureauService;
import com.hx.bureau.service.utils.BureauConfigKey;
import com.hx.core.utils.MessageUtil;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.entity.SMSUser;
import com.hx.core.wyim.entity.emu.SMSNotice;
import com.hx.core.wyim.service.SmsService;

@Service
public class HxBureauServiceImpl implements HxBureauService{
		
	@Autowired
	private HwTravelBureauMapper hwTravelBureauMapper;
	
	@Autowired
	private HxBureauUserMapper hxBureauUserMapper;
	
	@Autowired
	private SmsService smsService;
	@Autowired
	private AdminSystemconfigMapper adminSystemconfigMapper;

	@Override
	public PageResultHxBureauInfo queryByMap(Map<String, Object> map) {
			//总条数
			long count = hwTravelBureauMapper.queryCountByMapToAdmin(map);
		//数据
		List<Map<String, Object>> list = hwTravelBureauMapper.queryByMapToAdmin(map);
		
		//总申请数
		long applyTotal = 0;
	
		//总通过总数
		long adoptTotal =0;
		
		//未处理总数
		long unauditedTotal = 0;
		//拒绝总数
		long failedTotal = 0;
		
		List<HwTravelBureau> hwTravelBureaus = hwTravelBureauMapper.queryAll();
		
		if(ObjectHelper.isNotEmpty(hwTravelBureaus)){
			applyTotal = hwTravelBureaus.size();
			for (HwTravelBureau hwTravelBureau : hwTravelBureaus) {
				if (hwTravelBureau.getState()==0){
					unauditedTotal++;
				}else if(hwTravelBureau.getState()==1){
					adoptTotal++;
				}else{
					failedTotal++;
				}
			}
		}
		
		PageResultHxBureauInfo pageResult = new PageResultHxBureauInfo();
		pageResult.setCount(count);
		pageResult.setDataList(list);
		pageResult.setAdoptTotal(adoptTotal);
		pageResult.setApplyTotal(applyTotal);
		pageResult.setFailedTotal(failedTotal);
		pageResult.setUnauditedTotal(unauditedTotal);
		return pageResult;										
	}

	@Override
	@Transactional
	public void bureaut_verification(Long bureau_id, Integer status, String reason) throws Exception {
		
		HwTravelBureau record = hwTravelBureauMapper.selectByPrimaryKey(bureau_id);
		
		
		record.setReason(reason);
		//收短信手机号
		String contacts_phome = record.getContacts_phome();
		///收短信用户名
		String contacts_name = record.getContacts_name();
		//旅行社登录账户
		String legal_person_phome = record.getLegal_person_phome();
		//旅行社登录地址
		String bureauloginurl = BureauConfigKey.bureauLoginUrl;
		
		if (record.getState()==1){
			throw new RuntimeException("该数据已通过了");
		}
		record.setState(status);
		SMSUser smsUser = new SMSUser();
		smsUser.setMobiles(new String[]{contacts_phome});
		
		record.setExamine_time(new Date().getTime());
		if(status == 2 ){
			if (ObjectHelper.isEmpty(reason)){
				throw new RuntimeException("未通过，必须添加原因");
			}
			
			smsUser.setTemplateid(SMSNotice.bureaufail.smsTypeTemplateNumber);
			smsUser.setParams(new String[]{record.getContacts_name(),reason});
			
		}else{
			
			
			String pass = adminSystemconfigMapper.queryValueByConfigKey(BureauConfigKey.bureauInitPassword);
			String key = MessageUtil.MD5(record.getLegal_person_phome(), 16);
			String passWord = ObjectHelper.passWord(pass, key);
			HxBureauUser hxBureauUser = new HxBureauUser();
			
			//法人手机号作为默认登录账号
			hxBureauUser.setBureau_user_account(record.getLegal_person_phome());
			hxBureauUser.setPassword(passWord);
			hxBureauUser.setPwd_salt(key);
			hxBureauUser.setCreate_time(System.currentTimeMillis());
			hxBureauUser.setIs_legal(1);
			hxBureauUser.setPhone(record.getLegal_person_phome());
			hxBureauUser.setReal_name(record.getLegal_person());
			hxBureauUser.setBureau_id(record.getBureau_id());
			hxBureauUserMapper.insertSelective(hxBureauUser);
			
			smsUser.setTemplateid(SMSNotice.bureausuccess.smsTypeTemplateNumber);
			smsUser.setParams(new String[]{contacts_name,bureauloginurl,legal_person_phome,pass});
		}
		
		hwTravelBureauMapper.updateByPrimaryKeySelective(record);
		
		//发送短息
		String sendtemplate = smsService.sendtemplate(smsUser);
		System.out.println(sendtemplate);
		
	}

	@Override
	public HwTravelBureau query_infoById(Long bureau_id) {
		
		return hwTravelBureauMapper.selectByPrimaryKey(bureau_id);
	}
	
}
