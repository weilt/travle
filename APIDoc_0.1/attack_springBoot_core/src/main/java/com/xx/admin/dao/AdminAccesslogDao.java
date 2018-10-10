package com.xx.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminAccesslogDao {

	
	/**
	 * 添加操作日志
	 * @param id 唯一标识值
	 * @param handleContent 操作内容 如：XX对XXX进行了（查询，修改，添加，删除等等）
	 * @param configKey 配置键(就是进入该方法的路径)
	 * @param userId 创建人（操作人）
	 * @param clientIpAndName 当前操作机器的ip地址和名称
	 * @return true false
	 */
	@Insert("INSERT INTO admin_accesslog(id,handleContent,configKey,userId,createTime,clientIpAndName)"
			+ "values(#{id},#{handleContent},#{configKey},#{userId},NOW(),#{clientIpAndName})")
	int insert(@Param("id")String id,@Param("handleContent")String handleContent,@Param("configKey")String configKey,
			@Param("userId")int userId,@Param("clientIpAndName")String clientIpAndName);
	
	/**
	 * 查询日志数据信息
	 * @param map
	 * @return
	 */
	List<Map> queryList(Map<String, Object> map);
	/**
	 * 查询日志数据条数
	 * @param map
	 * @return
	 */
	Integer queryListCount(Map<String, Object> map);
	
	/**
	 * 条件删除数据信息
	 * @param map
	 * @return
	 */
	int deleteAccounTiaojian(Map<String, Object> map);
	
	/**
	 * 删除日志信息
	 * @param list
	 * @return
	 */
	int deleteAccoun(List<String> list);
}
