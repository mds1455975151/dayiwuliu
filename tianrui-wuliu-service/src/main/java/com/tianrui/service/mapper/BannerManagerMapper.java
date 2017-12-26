package com.tianrui.service.mapper;

import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.api.resp.admin.BannerManagerResp;
import com.tianrui.service.bean.BannerManager;
import com.tianrui.service.bean.BannerManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}