package com.tianrui.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.api.resp.admin.BannerManagerResp;
import com.tianrui.service.bean.BannerManager;
import com.tianrui.service.bean.BannerManagerExample;

public interface BannerManagerMapper {
    int countByExample(BannerManagerExample example);

    int deleteByExample(BannerManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(BannerManager record);

    int insertSelective(BannerManager record);

    List<BannerManager> selectByExample(BannerManagerExample example);

    BannerManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BannerManager record, @Param("example") BannerManagerExample example);

    int updateByExample(@Param("record") BannerManager record, @Param("example") BannerManagerExample example);

    int updateByPrimaryKeySelective(BannerManager record);

    int updateByPrimaryKey(BannerManager record);

    /**
	 * 查询banner管理图片信息
	 * @author xcy
	 * @return
	 */
	List<BannerManager> queryBanner(BannerManager bannerManager);

	/**
	 * 查询待发布的and启用的and有效的banner图片数据
	 * @author xcy
	 * @return
	 */
	List<BannerManager> queryPushBanner();

	/**
	 * 手机端显示banner图片
	 * @author xcy
	 * @return
	 */
	List<BannerManager> queryAppBanner(BannerManagerReq req);
}