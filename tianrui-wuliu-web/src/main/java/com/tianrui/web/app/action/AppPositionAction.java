package com.tianrui.web.app.action;


import java.util.ArrayList;
import java.util.Collections;
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
		if(body.size()>0){//位置按时间排序
			int leng = body.size();
			int sss = 0;
			for (int b = 0; b < leng; b++) {
				Long min = 0l;
				for (int i = 0; i < body.size(); i++) {
					if(body.get(i).getTimeStap()>min){
						min = body.get(i).getTimeStap();
						sss = i;
					}
				}
				bandList.add(body.get(sss));
				body.remove(sss);
			}
		}
		for (int i = bandList.size(); i > 0; i--) { 
			logger.info("位置时间="+bandList.get(i-1).getTimeStap());
			PositionSaveReq saveBean = bandList.get(i-1);	
			saveBean.setCurrId(uId);
			positionService.savePosition(saveBean);
		}
		return AppResult.valueOf(rs);
	}
	
	public static void main(String[] args) {
		List<PositionSaveReq> body = new ArrayList<PositionSaveReq>();
		PositionSaveReq save = new PositionSaveReq();
		save.setTimeStap(12l);
		save.setCurrId("12");
		body.add(save);
		
		PositionSaveReq sg = new PositionSaveReq();
		sg.setTimeStap(1l);
		sg.setCurrId("1");
		body.add(sg);
		
		PositionSaveReq ss = new PositionSaveReq();
		ss.setTimeStap(133l);
		ss.setCurrId("133");
		body.add(ss);
		
		PositionSaveReq st = new PositionSaveReq();
		st.setTimeStap(17l);
		st.setCurrId("17");
		body.add(st);
	
		List<PositionSaveReq> bandList = new ArrayList<PositionSaveReq>();
		if(body.size()>0){
			int leng = body.size();
			int sss = 0;
			for (int b = 0; b < leng; b++) {
				Long min = 0l;
				for (int i = 0; i < body.size(); i++) {
					if(body.get(i).getTimeStap()>min){
						min = body.get(i).getTimeStap();
						sss = i;
					}
				}
				bandList.add(body.get(sss));
				body.remove(sss);
			}
		}
		for(PositionSaveReq sp : bandList){
			System.out.println("-----"+sp.getTimeStap()+","+sp.getCurrId());
		}
		for (int i = bandList.size(); i > 0; i--) {
			System.out.println("-----=="+bandList.get(i-1).getTimeStap()+","+bandList.get(i-1).getCurrId());
		}
	}
	
	
	
	
	
}
