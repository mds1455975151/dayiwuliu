package com.tianrui.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.admin.MaterielRouteReq;
import com.tianrui.service.bean.MaterielRoute;
import com.tianrui.service.bean.MaterielRouteExample;

public interface MaterielRouteMapper {
    int countByExample(MaterielRouteExample example);

    int deleteByExample(MaterielRouteExample example);

    int deleteByPrimaryKey(String id);

    int insert(MaterielRoute record);

    int insertSelective(MaterielRoute record);

    List<MaterielRoute> selectByExample(MaterielRouteExample example);

    MaterielRoute selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MaterielRoute record, @Param("example") MaterielRouteExample example);

    int updateByExample(@Param("record") MaterielRoute record, @Param("example") MaterielRouteExample example);

    int updateByPrimaryKeySelective(MaterielRoute record);

    int updateByPrimaryKey(MaterielRoute record);

    /**
     * 查询已选路线
     * @author xcy
     * @param req
     * @return
     */
	List<MaterielRoute> querySelecedRoute(MaterielRouteReq req);

	/**
	 * 查询待选路线
	 * @author xcy
	 * @return
	 */
	List<MaterielRoute> queryWaitRoute(MaterielRouteReq req);
}