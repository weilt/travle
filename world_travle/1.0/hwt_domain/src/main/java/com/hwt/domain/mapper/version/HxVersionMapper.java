package com.hwt.domain.mapper.version;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.HxUserTeamUser;
import com.hwt.domain.entity.version.HxVersion;
import com.hwt.domain.entity.version.vo.HxVersionVo;

/**
 * 版本 持久层 
 * @author Administrator
 *
 */
@Mapper
public interface HxVersionMapper  {
	
	/**
	 * 查询所有的版本信息
	 * @return
	 */
	@Select("select * from hx_version")
	List<HxVersion> queryAll();
	
	/**
	 * 根据ID查询版本详情
	 * @param id 主键ID
	 * @return
	 */
	@Select("select * from hx_version where id=#{id}")
	HxVersion queryById(@Param("id")Long id);

	/**
	 * 添加版本信息
	 * @param hxVersion 版本信息
	 * @return
	 */
	int insertVersion(HxVersion hxVersion);
	
	/**
	 * 修改版本信息
	 * @param hxVersion
	 * @return
	 */
	int updateVersion(HxVersion hxVersion);

	/**
	 * 根据条件返回总条数
	 * @param map
	 * @return
	 */
	long queryHxVersionCountByMap(Map<String, Object> map);

	/**
	 * 根据条件返回数据
	 * @param map
	 * @return
	 */
	List<HxVersion> queryHxVersionByMap(Map<String, Object> map);

	/**
	 * 返回最大的版本号
	 * @param android_or_ios
	 * @return
	 */
	@Select("select max(version_number) from hx_version where android_or_ios=#{android_or_ios}")
	Long get_max_version_number(@Param("android_or_ios")Integer android_or_ios);

	/**
	 * 通过手机类型返回最新
	 * @param i
	 * @return
	 */
	@Select("select *  from hx_version where android_or_ios=#{android_or_ios} ORDER BY id DESC LIMIT 0,1")
	HxVersionVo query_by_android_or_ios_new(@Param("android_or_ios")int android_or_ios);
}