package com.hx.core.wyim.service.impl;



import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hx.core.wyim.entity.ImUser;
import com.hx.core.wyim.ntes.NtesCommon;
import com.hx.core.wyim.service.ImService;


/**
 * Created by RO on 2018/3/7. 网易云信的业务实现
 */
@Service
public class NtesImServiceImpl implements ImService {

	@Override
	public String register(ImUser imUser) throws Exception {
		return NtesCommon.regiest(imUser.getAccid(), imUser.getName(), imUser.getProps(), imUser.getIcon(),
				imUser.getToken(), imUser.getSign(), imUser.getEmail(), imUser.getBirth(), imUser.getMobile(),
				imUser.getGender(), imUser.getEx());
	}

	@Override
	public String updateUserInfo(ImUser imUser) throws Exception {
		return NtesCommon.updateImUser(imUser.getAccid(), imUser.getProps(), imUser.getToken());
	}

	@Override
	public String getNewToken(String account) throws Exception {
		return NtesCommon.getNewToken(account);
	}

	@Override
	public String blockImUser(String imAccount) throws Exception {
		return NtesCommon.blockUser(imAccount, false);
	}

	@Override
	public String unblockImUser(String imAccount) throws Exception {
		return NtesCommon.unblockUser(imAccount);
	}

	/**
	 * 点对点通知
	 * 
	 * @param fromUserId
	 *            通知人
	 * @param toUserId
	 *            被通知人
	 * @param noticeContent
	 *            通知内容
	 * @param sendType
	 *            发送类型 1表示只发在线，2表示会存离线，其他会报414错误。默认会存离线
	 * @return
	 */
	@Override
	@Async
	public String singleNotice(String fromUserId, String toUserId, String noticeContent, int sendType)
			throws Exception {
		return NtesCommon.sendAttachMsg(fromUserId, toUserId, noticeContent, null, null, null, sendType, null);
	}

	/**
	 * 更新用户名片
	 */
	@Override
	public String updateUinfo(ImUser imUser) throws Exception {
		// TODO Auto-generated method stub
		return NtesCommon.updateUinfo(imUser.getAccid(), imUser.getName(), imUser.getIcon(), imUser.getSign(), imUser.getEmail(),
				imUser.getBirth(), imUser.getMobile(), imUser.getGender(), imUser.getEx());
	}

	@Override
	@Async
	public String sendMsgPTOP(String from, String ope, String to, int type, String body) throws Exception {
		// TODO Auto-generated method stub
		return NtesCommon.sendMsg(from, ope, to, type, body, false, null, null, null, null, null, null, null, null, null, null, null);
	}
}
