package com.tianrui.service.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.api.resp.front.cargoplan.PlanRouteResp;
import com.tianrui.service.admin.bean.FileRoute;

public interface FileRouteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_route
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_route
     *
     * @mbggenerated
     */
    int insert(FileRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_route
     *
     * @mbggenerated
     */
    int insertSelective(FileRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_route
     *
     * @mbggenerated
     */
    FileRoute selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_route
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FileRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_route
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FileRoute record);
    
    List<FileRoute> selectByCondition(FileRoute record);
    
    List<FileRoute> selectAll();
    
    long countByCondition(FileRoute record);
    
    List<PlanRouteResp> selectByPlanIds(@Param("ids")List<String> ids);

    /**
     * 查询所有路线
     * @author xcy
     * @param req
     * @return
     */
	List<FileRoute> queryRoute(RouteReq req);
}