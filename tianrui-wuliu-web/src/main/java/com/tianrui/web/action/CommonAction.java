package com.tianrui.web.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
  * @ClassName: CommonAction 
  * @Description: 通用action
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月24日 上午11:25:11 
  *
 */
@Controller
@RequestMapping("/trwuliu/common")
public class CommonAction {

	
	public static Logger loger=org.slf4j.LoggerFactory.getLogger(CommonAction.class);
	//用户实名认证页面
	@RequestMapping("/certification")
	public ModelAndView certification(String type) throws IOException{
		ModelAndView model =new ModelAndView("/error/msg");
		if( StringUtils.isNotBlank(type) && "2".equals(type) ){
			model.addObject("error", "呃，还未通过司机认证！");
			model.addObject("message", "请上传驾照通过司机认证,才能开始运输业务.");
			model.addObject("href", "<a href=\"/trwuliu/Member/authenPage\"><span>点此立即进行认证</span></a>");
		}else if( StringUtils.isNotBlank(type) && "1".equals(type) ){
			model.addObject("error", "呃，还未通过实名认证！");
			model.addObject("message", "请在实名认证通过之后,再点击此连接");
			model.addObject("href", "<a href=\"/trwuliu/Member/authenPage\"><span>点此立即进行认证</span></a>");
		}else if( StringUtils.isNotBlank(type) && "4".equals(type) ){
			model.addObject("error", "呃，还未成为车主！");
			model.addObject("message", "请先在我的车辆中进行车辆和司机绑定！");
			model.addObject("href", "<a href=\"/trwuliu/Member/myVehicle/myVehiclePage\"><span>点此进行车辆管理</span></a>");
		}
		
		model.addObject("title", "未通过平台身份认证");
		model.addObject("hrefFlag", true);
		return model;
	}
	
	
	//成为货主申请
	@RequestMapping("/toOwner")
	public ModelAndView toOwner(@RequestParam(defaultValue = "0") Integer type) throws IOException{
		
		ModelAndView model =new ModelAndView("/error/msg");
		if(1 == type){
			model.addObject("message", "您的组织机构已被禁用，请联系管理员！");
		}else {
			model.addObject("message", "请联系系统管理员为您添加组织机构！");
		}
		model.addObject("error", "呃，暂无可用组织！");
		model.addObject("title", "无可用组织");
		model.addObject("hrefFlag", false);
		return model;
	}
}
