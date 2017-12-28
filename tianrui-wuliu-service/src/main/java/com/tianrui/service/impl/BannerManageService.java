package com.tianrui.service.impl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IBannerManageService;
import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.api.resp.admin.BannerManagerResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.BannerManager;
import com.tianrui.service.mapper.BannerManagerMapper;

/**
 * banner管理实现类
 * @author xcy
 */
@Service
public class BannerManageService implements IBannerManageService {

	Logger logger=LoggerFactory.getLogger(BannerManageService.class);
	
	@Autowired
	private BannerManagerMapper bannerManagerMapper;
	
	@Override
	public Result queryBanner(BannerManagerReq bannerReq) {
		Result result = Result.getSuccessResult();
		bannerReq.setStatus(Constant.YES_STR);
		List<BannerManager> bannerList = bannerManagerMapper.queryBanner(bannerReq);
		if(!bannerList.isEmpty()){
			result.setData(bannerList);
		}else{
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public int bannerAdd(BannerManagerReq bannerReq) {
		bannerReq.setId(UUIDUtil.getId());
		bannerReq.setCreateDate(System.currentTimeMillis());
		bannerReq.setPicStatus((byte)1);
		bannerReq.setPushStatus((byte)0);
		bannerReq.setStatus(Constant.YES_STR);
		BannerManager bm = new BannerManager();
		try {
			PropertyUtils.copyProperties(bm, bannerReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int count = bannerManagerMapper.insert(bm);
		return count;
	}

	@Override
	public Result delBanner(BannerManagerReq bannerReq) {
		Result result = Result.getSuccessResult();
		BannerManager bm = bannerManagerMapper.selectByPrimaryKey(bannerReq.getId());
		if(bm != null){
			bm.setStatus(Constant.NOT_STR);
			bm.setModifier(bannerReq.getModifier());
			bm.setModifierTime(System.currentTimeMillis());
			int count = bannerManagerMapper.updateByPrimaryKey(bm);
			if(count>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				return result;
			}
		}
		result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		return result;
	}

	@Override
	public Result pushBnner(BannerManagerReq bannerReq) {
		Result result = Result.getSuccessResult();
		String[] ids = bannerReq.getPushBannerIds().split(";");
		logger.info("待发布图片："+ids.length+","+bannerReq.getPushBannerIds());
		for (String bannerId : ids) {
			BannerManager bm = bannerManagerMapper.selectByPrimaryKey(bannerId);
			if(bm != null){
				bm.setPushStatus((byte)1);
				bm.setModifier(bannerReq.getModifier());
				bm.setModifierTime(System.currentTimeMillis());
				int count = bannerManagerMapper.updateByPrimaryKey(bm);
				if(count>0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.SYSTEM_ERROR);
				}
			}
		}
		return result;
	}
	
	@Override
	public Result enableOrDisable(BannerManagerReq bannerReq) {
		Result result = Result.getSuccessResult();
		BannerManager bm = bannerManagerMapper.selectByPrimaryKey(bannerReq.getId());
		if(bm != null){
			String picStatus = bm.getPicStatus().toString();
			if(picStatus.equals(Constant.ZERO_STR)){
				bm.setPicStatus((byte)1);
			}else if(picStatus.equals(Constant.ONE_STR)){
				bm.setPicStatus((byte)0);
			}
			int count = bannerManagerMapper.updateByPrimaryKey(bm);
			if(count>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				return result;
			}
		}
		result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		return result;
	}

	@Override
	public Result queryPushBanner(BannerManagerReq bannerReq) {
		Result result = Result.getSuccessResult();
		//查询有效的and待发布and启用的数据
		List<BannerManager> bannerList = bannerManagerMapper.queryPushBanner(bannerReq);
		if(!bannerList.isEmpty()){
			result.setData(bannerList);
		}else{
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public Result queryAppBanner(BannerManagerReq req) {
		Result result = Result.getSuccessResult();
	  	//设置有效的数据'Y'
		req.setStatus(Constant.YES_STR);
		List<BannerManager> appBanner = bannerManagerMapper.queryAppBanner(req);
		if(!appBanner.isEmpty()){
			result.setData(appBanner);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

}
