package com.hx.core.wyim.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.entity.SMSUser;
import com.hx.core.wyim.entity.emu.SMSNotice;
import com.hx.core.wyim.entity.system.SystemNotice;
import com.hx.core.wyim.service.ImService;
import com.hx.core.wyim.service.SmsService;

@Service
public class SentNotice {
	
	@Autowired
	private ImService imService;
	
	@Autowired
	private SmsService smsService;
	
	/**
	 * 发送系统消息
	 * @param im_id      接受消息用户id
	 * @param title			消息标题
	 * @param description	描述
	 * @param url			链接
	 * @param type			 1 文本 2 账单 3 html 4 导游确认订单 5 游客对导游评价 6 游客对线路评价 7 其他消息 0 没有
	 * @param actualId		//   null 没有 4 导游确认订单 5 游客对导游评价 6 游客对线路评价
	 * @param aboutBills	//账单实体
	 * @throws Exception		
	 */
	public void sendSystem(String im_id,String title,String description,String url,int type,String actualId,String aboutBills) throws Exception{
		
		SystemNotice systemNotice = new SystemNotice(title, description, url, type, System.currentTimeMillis(), actualId, aboutBills);
		//发送消息
		
		imService.singleNotice(HXSystemConfig.HX_OFFICIAL_IM_ID, im_id,
				GsonUtil.toJson(new FriendOperationNotice(null,null, 10000, GsonUtil.toJson(systemNotice))), 2);
	}
	
	
	/**
	 * 发送系统短信
	 */
	public void sendSystemSms(String contacts_phome,int template,String order_num) throws Exception{
		//发送消息
		SMSUser smsUser = new SMSUser();
		smsUser.setMobiles(new String[]{contacts_phome});
		smsUser.setTemplateid(template);
		if (!ObjectHelper.isEmpty(order_num)){
			smsUser.setParams(new String[]{order_num});
		}
		
		smsService.sendtemplate(smsUser);
	}
}
