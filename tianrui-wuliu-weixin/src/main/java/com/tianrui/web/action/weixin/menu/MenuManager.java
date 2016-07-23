package com.tianrui.web.action.weixin.menu;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianrui.web.action.weixin.menu.entity.Button;
import com.tianrui.web.action.weixin.menu.entity.ClickButton;
import com.tianrui.web.action.weixin.menu.entity.ComplexButton;
import com.tianrui.web.action.weixin.menu.entity.Menu;
import com.tianrui.web.action.weixin.menu.entity.MenuUtil;
import com.tianrui.web.action.weixin.menu.entity.ViewButton;
import com.tianrui.web.action.weixin.util.entity.Tokens;
import com.tianrui.web.action.weixin.util.util.CommonUtil;


/**
 * 
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年7月23日上午11:56:19
 *
 * @修改人：lsj
 * @修改时间：2016年7月23日上午11:56:19
 * @修改备注：
 *
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	//网页授权url
	public final static String redirect_uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	/**
	 * 定义菜单结构
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static Menu getMenu() throws UnsupportedEncodingException {
		String baseuri="http://183-lisijia.imwork.net";
		String appid="wxf22ce076abf3d066";
		String scope = "snsapi_userinfo";
//		ClickButton btn11 = new ClickButton();
//		btn11.setName("我的车辆");
//		btn11.setType("click");
//		btn11.setKey("oschina");
//
		ViewButton btn12 = new ViewButton();
		String btn12Url = baseuri + "/authorize/authorize";
		String state = "mycare";
		btn12.setName("我的车辆");
		btn12.setType("view");
		btn12.setUrl(redirect_uri.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(btn12Url,"UTF-8")).replace("SCOPE", scope).replace("STATE", state));

		ViewButton btn13 = new ViewButton();
		btn13.setName("我的司机");
		btn13.setType("view");
		btn13.setUrl("http://www.iteye.com");

		ViewButton btn21 = new ViewButton();
		btn21.setName("发布的运单");
		btn21.setType("view");
		btn21.setUrl("http://m.taobao.com");

		ViewButton btn22 = new ViewButton();
		btn22.setName("承运的运单");
		btn22.setType("view");
		btn22.setUrl("http://m.jd.com");

		ViewButton btn23 = new ViewButton();
		btn23.setName("运输的运单");
		btn23.setType("view");
		btn23.setUrl("http://m.vipshop.com");

//		ViewButton btn24 = new ViewButton();
//		btn24.setName("当当网");
//		btn24.setType("view");
//		btn24.setUrl("http://m.dangdang.com");
//
//		ViewButton btn25 = new ViewButton();
//		btn25.setName("苏宁易购");
//		btn25.setType("view");
//		btn25.setUrl("http://m.suning.com");

		ViewButton btn31 = new ViewButton();
		btn31.setName("我的认证");
		btn31.setType("view");
		btn31.setUrl("http://www.duopao.com");

//		ViewButton btn32 = new ViewButton();
//		btn32.setName("一窝88");
//		btn32.setType("view");
//		btn32.setUrl("http://www.yi588.com");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("我的运力");
		mainBtn1.setSub_button(new Button[] { btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的运单");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("个人中心");
		mainBtn3.setSub_button(new Button[] { btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 第三方用户唯一凭证
		String appId = "wxf22ce076abf3d066";
		// 第三方用户唯一凭证密钥
		String appSecret = "4add7800a76bf23866778b14d69bf6d4";

		// 调用接口获取凭证
		Tokens token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
	}
}
