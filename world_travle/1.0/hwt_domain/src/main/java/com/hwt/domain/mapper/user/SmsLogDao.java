package com.hwt.domain.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.hwt.domain.entity.user.SmsLog;
import com.hwt.domain.utils.sample.config.MyMapper;



/**
 * 短信日志表
 * @author JIAO_LIU_BABA
 */
@Mapper
public interface SmsLogDao extends MyMapper<SmsLog>{

}
