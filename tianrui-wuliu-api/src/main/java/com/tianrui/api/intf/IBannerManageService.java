package com.tianrui.api.intf;

import javax.servlet.http.HttpServletRequest;

import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.common.vo.Result;

/**
 * banner管理业务接口
 * @author xcy
 */
public interface IBannerManageService {

	/**
	 * 查询banner管理图片信息
	 * @author xcy
	 * @return
	 */
	Result queryBanner();
	
	/**
	 * banner管理图片新增
	 * @author xcy
	 * @param bannerReq
	 * @return
	 */
	int bannerAdd(BannerManagerReq bannerReq);

	/**
	 * banner管理图片删除
	 * @author xcy
	 * @param bannerReq
	 * @return
	 */
	Result delBanner(BannerManagerReq bannerReq);

	/**
	 * banner管理图片发布
	 * @author xcy
	 * @param bannerReq
	 * @return
	 */
	Result pushBnner(BannerManagerReq bannerReq);

	/**
	 * 启用或禁用banner管理图片信息
	 * @author xcy
	 * @param bannerReq
	 * @return
	 */
	Result enableOrDisable(BannerManagerReq bannerReq);

}
