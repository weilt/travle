package com.hx.core.wyim.service;

import com.hx.core.wyim.entity.ImUser;

/**
 * Created by RO on 2018/3/7.
 * 通信业务接口
 */

public interface ImService {

    /**
     * 注册IM账号
     * @param imUser    用户信息
     * @return 可以返回第三方注册Token
     */
    String register(ImUser imUser) throws Exception;

    /**
     * 更新IM用户信息
     * @param imUser
     * @return  返回状态
     */
    String updateUserInfo(ImUser imUser) throws Exception;

    /**
     * 获取新token
     * @param imAccount IM账号
     * @return
     */
    String getNewToken(String imAccount) throws Exception;

    /**
     * 禁用账号
     * @param imAccount IM账号
     * @return
     */
    String blockImUser(String imAccount) throws Exception;

    /**
     * 启用账号
     * @param imAccount IM账号
     * @return
     */
    String unblockImUser(String imAccount) throws Exception;

    /**
     * 点对点通知
     * @param fromUserId    通知人
     * @param toUserId      被通知人
     * @param noticeContent 通知内容
     * @param sendType      发送类型  1表示只发在线，2表示会存离线，其他会报414错误。默认会存离线
     * @return
     */
    String singleNotice(String fromUserId, String toUserId, String noticeContent, int sendType) throws Exception;

    /**
     * 更新用户名片
     * @param imUser
     * @return
     * @throws Exception
     */
    String updateUinfo(ImUser imUser) throws Exception;
    
   
    /**
     * 发送普通消息----单聊
     * @param from				必须	发送者accid，用户帐号，最大32字符，必须保证一个APP内唯一 	
	 * @param ope				必须 	0：点对点个人消息，1：群消息（高级群），其他返回414
	 * @param to				必须	ope==0是表示accid即用户id，ope==1表示tid即群id
	 * @param type				必须 	0 表示文本消息,1 表示图片，2 表示语音，3 表示视频，4 表示地理位置信息，6 表示文件，100 自定义消息类型（特别注意，对于未对接易盾反垃圾功能的应用，该类型的消息不会提交反垃圾系统检测）
	 * @param body				必须	请参考下方消息示例说明中对应消息的body字段，最大长度5000字符，为一个JSON串
     * @return
     * @throws Exception
     */
    String sendMsgPTOP(String from,String ope,String to,int type,String body)throws Exception;
}
