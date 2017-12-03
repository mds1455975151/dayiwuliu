package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.req.app.AppGetCodeReq;
import com.tianrui.api.req.app.AppMemberReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.req.money.CheckPasswordReq;
import com.tianrui.api.req.money.SavePasswordReq;
import com.tianrui.api.resp.goods.SelectAppPlanGoodsResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.common.vo.UserLoginVo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class CheckMoenyTestTest {
	public static Logger logger =LoggerFactory.getLogger(CheckMoenyTestTest.class);
	@Autowired
	ICapitalAccountService capitalAccountService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	private CacheClient cache ;

	@Autowired
	IPlanGoodsService planGoodsService;
	
	@Test
	public void app() throws Exception{
		PlanGoodsReq req = new PlanGoodsReq();
		PaginationVO<SelectAppPlanGoodsResp> page = planGoodsService.appSelect(req);
		System.out.println("...");
	}
	
	
	public void chcj(){
		AppMemberReq req =new AppMemberReq();
		req.setAccount("18337129805");
		String key = CacheHelper.buildKey(CacheModule.MOENY_APP_UPT_PASSWORD, new String[]{"3",req.getAccount()});
		UserLoginVo value=(UserLoginVo) cache.getObj(key,UserLoginVo.class);
		System.out.println("succe");
	}
	
	
	public void sendAuthCodeMsg() throws Exception {
		System.out.println("开始");
		AppGetCodeReq req = new AppGetCodeReq();
		req.setAccount("18337129805");
		req.setType("3");
		Result rs= systemMemberService.sendAuthCodeMsg(req);
		System.out.println(rs.getCode()+rs.getError());
		System.out.println("结束");
	}
	
	public void checkPassword() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		CheckPasswordReq req = new CheckPasswordReq();
		req.setId("xxxxxxx33");
		req.setCellphone("18337129805");
		req.setCheckType("2");
		req.setPassword("111111113");
		req.setGesturepass("2222222");
		Result rs = capitalAccountService.checkPassword(req);
		System.out.println(rs.getCode()+rs.getError());
		System.out.println("执行结束");
	}
	
	public void saveOrUptAcountPassword() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		SavePasswordReq req = new SavePasswordReq();
		req.setId("xxxxxxx33");
		req.setCellphone("18337129805");
		req.setCheckType("2");
//		req.setPassword("11111111");
//		req.setOldPassword("12345633");
		req.setGesturepass("2222222");
		req.setOldGesturepass("123456897");
		Result rs = capitalAccountService.saveOrUptAcountPassword(req);
		System.out.println(rs.getCode()+rs.getError());
		System.out.println("执行结束");
	}
	
}
