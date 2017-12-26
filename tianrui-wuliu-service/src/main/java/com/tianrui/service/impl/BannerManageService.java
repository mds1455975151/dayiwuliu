package com.tianrui.service.impl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IBannerManageService;
import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.BannerManager;
import com.tianrui.service.mapper.BannerManagerMapper;

/**
 * banner管理实现类
 * @author xcy
 */
@Service
public class BannerManageService implements IBannerManageService {

	@Autowired
	private BannerManagerMapper bannerManagerMapper;
	
	@Override
	public Result queryBanner() {
		Result result = Result.getSuccessResult();
		BannerManager bannerManager = new BannerManager();
		bannerManager.setStatus("Y");
		List<BannerManager> bannerList = bannerManagerMapper.queryBanner(bannerManager);
		if(!bannerList.isEmpty()){
			result.setData(bannerList);
		}else{
			result.setError("没有查询到数据！");
		}
		return result;
	}

	@Override
	public int bannerAdd(BannerManagerReq bannerReq) {
		bannerReq.setId(UUIDUtil.getId());
		bannerReq.setCreateDate(System.currentTimeMillis());
		bannerReq.setPicStatus((byte)1);
		bannerReq.setPushStatus((byte)0);
		bannerReq.setStatus("Y");
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
			bm.setStatus("N");
			bm.setModifier(bannerReq.getModifier());
			bm.setModifierTime(System.currentTimeMillis());
			int count = bannerManagerMapper.updateByPrimaryKey(bm);
			if(count>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				return result;
			}
		}
		result.setError("删除失败");
		return result;
	}

	@Override
	public Result pushBnner(BannerManagerReq bannerReq) {
		Result result = Result.getSuccessResult();
		BannerManager bm = bannerManagerMapper.selectByPrimaryKey(bannerReq.getId());
		if(bm != null){
			bm.setPushStatus((byte)1);
			bm.setModifier(bannerReq.getModifier());
			bm.setModifierTime(System.currentTimeMillis());
			int count = bannerManagerMapper.updateByPrimaryKey(bm);
			if(count>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				return result;
			}
		}
		result.setError("发布失败！");
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
		result.setError("操作失败！");
		return result;
	}

}
