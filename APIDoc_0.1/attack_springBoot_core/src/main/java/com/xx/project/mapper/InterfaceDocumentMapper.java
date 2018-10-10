package com.xx.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xx.project.entity.InterfaceDocument;

@Mapper
public interface InterfaceDocumentMapper {

	/**
	 * 查询数据量
	 * @param map
	 * @return
	 */
	int queryListCount(Map<String, Object> map);

	/**
	 * 查询数据
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryList(Map<String, Object> map);

	/**
	 * 添加数据
	 * @param interfaceDocument
	 * @return
	 */
	@Insert("insert into interface_document(projectId,userId,name,url,isDelete,param,returnValue,remark,createTime,updateTime)"
			+ "values(#{projectId},#{userId},#{name},#{url},#{isDelete},#{param},#{returnValue},#{remark},NOW(),NOW())")
	int insert(InterfaceDocument interfaceDocument);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Select("select * from interface_document where id=#{id}")
	InterfaceDocument findById(@Param("id")Integer id);

	/**
	 * 修改
	 * @param interfaceDocument
	 * @return
	 */
	int update(InterfaceDocument interfaceDocument);

	/**
	 * 删除/恢复
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("update interface_document set isDelete = #{status} ,updateTime = NOW() where id = #{id}")
	int delete(@Param("id")int id, @Param("status")Integer status);
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@Select("select name,param,returnValue,remark from interface_document where id=#{id}")
	InterfaceDocument findDescById(@Param("id")Integer id);
    
}