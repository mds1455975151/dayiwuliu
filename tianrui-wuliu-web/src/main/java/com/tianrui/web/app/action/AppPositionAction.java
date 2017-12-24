package com.tianrui.web.app.action;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberPositionService;
import com.tianrui.api.req.front.position.PositionListSaveReq;
import com.tianrui.api.req.front.position.PositionSaveReq;
import com.tianrui.api.req.money.TrackReq;
import com.tianrui.api.tracking.intf.ITrackingService;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
  * @ClassName: AppPositionAction 
  * @Description: 上传当前位置
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月5日 上午10:35:07 
  *
 */
@Controller
@RequestMapping("/app/position")
public class AppPositionAction {
	
	public Logger logger = LoggerFactory.getLogger(AppPositionAction.class);
	@Autowired
	IMemberPositionService positionService;
	@Autowired
	ITrackingService trackingService;

	/**上传位置-单点位置上传
	 */
	@RequestMapping(value="/uploadPosition",method=RequestMethod.POST)
	@ApiParamRawType(PositionSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult uploadPosition(AppParam<PositionSaveReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
	
		//拼装查询条件
		PositionSaveReq saveBean =appParam.getBody();	
		saveBean.setCurrId(uId);
		Result rs =positionService.savePosition(saveBean);
		
		return AppResult.valueOf(rs);
	}
	
	@RequestMapping(value="/uploadPositionlist",method=RequestMethod.POST)
	@ApiParamRawType(PositionListSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult uploadPositionlist(AppParam<PositionListSaveReq> appParam) throws Exception{
		//获取当前用户
		Result rs = Result.getSuccessResult();
		String uId = appParam.getHead().getId();
		PositionListSaveReq req = appParam.getBody();	
		logger.info("本次传点个数="+req.getList().size());
		List<PositionSaveReq> body = req.getList();
		List<PositionSaveReq> bandList = new ArrayList<PositionSaveReq>();
		
		Long x = 0l;
		Long timeStap = System.currentTimeMillis();
		Long timebegig = System.currentTimeMillis() - (body.get(body.size()-1).getTimeStap()-body.get(0).getTimeStap());
		for (int i = body.size()-1; i >= 0; i--) {
			PositionSaveReq saveBean = body.get(i);
			x = body.get(body.size()-1).getTimeStap()-body.get(i).getTimeStap();
			saveBean.setTimeStap(timeStap-x);
			bandList.add(saveBean);
		}
		
		for (int i = bandList.size()-1; i >= 0; i--) { 
			logger.info("位置时间="+bandList.get(i).getTimeStap());
			PositionSaveReq saveBean = bandList.get(i);	
			saveBean.setCurrId(uId);
			positionService.savePositionList(saveBean);
		}
		
		TrackReq treq = new TrackReq();
		treq.setDriverid(uId);
		treq.setBeginTime(timebegig);
		treq.setEndTime(timeStap);
		treq.setLat(bandList.get(0).getLat());
		treq.setLng(bandList.get(0).getLon());
		trackingService.save(treq);
		
		return AppResult.valueOf(rs);
	}
	
}
