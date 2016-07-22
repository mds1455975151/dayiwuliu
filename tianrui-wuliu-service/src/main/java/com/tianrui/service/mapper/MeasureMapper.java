package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.Measure;

public interface MeasureMapper {
	
	
	/**
	 * 
	 * @描述:根据id删除
	 * @param id
	 * @return
	 * @返回类型 int
	 * @创建人 lsj
	 * @创建时间 2016年5月16日下午2:40:41
	 */
	int deleteByPrimaryKey(String id);
	/**
	 * 添加
	 * @描述:
	 * @param record
	 * @return
	 * @返回类型 int
	 * @创建人 lsj
	 * @创建时间 2016年5月16日下午2:41:24
	 */
	int insertSelective(Measure record);
	
	 /**
     * 列表查询
     * @param info
     * @return
     */
    List<Measure> selectByEntity(Measure info);
    /**
     * 
     * @描述:根据id查询
     * @param id
     * @return
     * @返回类型 List<Measure>
     * @创建人 lsj
     * @创建时间 2016年5月16日下午3:19:31
     */
    List<Measure> selectByPrimaryKey(String id);
    /**
     * 
     * @描述:查询相同量纲间的关系
     * @param record
     * @return
     * @返回类型 List<Measure>
     * @创建人 lsj
     * @创建时间 2016年5月17日上午8:48:35
     */
    List<Measure> selectByorClm(Measure record);
    /**
     * 
     * @描述:修改
     * @param record
     * @return
     * @返回类型 int
     * @创建人 lsj
     * @创建时间 2016年5月16日下午2:43:00
     */
    int updateByPrimaryKeySelective(Measure record);
    /**
     * 
     * @描述:模糊查询
     * @param measure
     * @return
     * @返回类型 List<Measure>
     * @创建人 lsj
     * @创建时间 2016年5月19日下午4:51:45
     */
    List<Measure> findMeasureBlur(Measure measure);
    /**
     * 
     * @描述:measureMain 非空查询
     * @param measure
     * @return
     * @返回类型 List<Measure>
     * @创建人 lsj
     * @创建时间 2016年5月20日下午2:33:16
     */
    List<Measure> findMeasureMainNotNull();
}
