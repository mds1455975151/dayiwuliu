package com.tianrui.service.mapper;

import com.tianrui.api.req.admin.MaterialReq;
import com.tianrui.service.bean.Materiel;
import com.tianrui.service.bean.MaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterielMapper {
    int countByExample(MaterielExample example);

    int deleteByExample(MaterielExample example);

    int deleteByPrimaryKey(String id);

    int insert(Materiel record);

    int insertSelective(Materiel record);

    List<Materiel> selectByExample(MaterielExample example);

    Materiel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Materiel record, @Param("example") MaterielExample example);

    int updateByExample(@Param("record") Materiel record, @Param("example") MaterielExample example);

    int updateByPrimaryKeySelective(Materiel record);

    int updateByPrimaryKey(Materiel record);

    /**
     * 搜索已选物料
     * @author xcy
     * @param req
     * @return
     */
	List<Materiel> querySelecedMaterial(MaterialReq req);

	/**
	 * 查询待选物料
	 * @author xcy
	 * @param req
	 * @return
	 */
	List<Materiel> queryWaitMate(MaterialReq req);
}