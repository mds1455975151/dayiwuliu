package com.tianrui.trwl.admin.web.redis;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.common.vo.Result;
import com.tianrui.common.vo.Visits;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;

@Controller
@RequestMapping("admin/redis")
public class RedisAction {

	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/system/redis");
		return view;
	}
	
	/** 获取访问次数类表*/
	@SuppressWarnings("unchecked")
	@RequestMapping("find")
	@ResponseBody
	public Result find(String type){
		Result rs = Result.getSuccessResult();
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		CacheClient cacheClient =wac.getBean(CacheClient.class);
		String key="";
		if(type.equals("number")){
			key=CacheHelper.buildKey(CacheModule.VCODE_NUMBER, "*");
		}else if(type.equals("pass")){
			key=CacheHelper.buildKey(CacheModule.LOGIN_PASS, "*");
		}
		Set<String> sd = cacheClient.getList(key);
		List<Visits> list = new ArrayList<Visits>();
		for(String d : sd){
			Visits vt =	cacheClient.getObj(d, Visits.class);
			list.add(vt);
		}
		Collections.sort(list,new Comparator<Object>(){
			@Override
			public int compare(Object paramT1, Object paramT2) {
				int one = ((Visits) paramT1).getNumber();
                int two = ((Visits) paramT2).getNumber();
                return two - one;
			}
		});
		
		rs.setData(list);
		return rs;
	}
	/** 取消访问禁止*/
	@RequestMapping("remove")
	@ResponseBody
	public Result remove(String id,String type){
		Result rs = Result.getSuccessResult();
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		CacheClient cacheClient =wac.getBean(CacheClient.class);
		String key="";
		if(type.equals("number")){
			key=CacheHelper.buildKey(CacheModule.VCODE_NUMBER, id);
		}else if(type.equals("pass")){
			key=CacheHelper.buildKey(CacheModule.LOGIN_PASS, id);
		}
		cacheClient.remove(key);
		return rs;
	}
}
