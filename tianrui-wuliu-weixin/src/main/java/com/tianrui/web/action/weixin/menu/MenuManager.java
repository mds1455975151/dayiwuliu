package com.tianrui.web.action.weixin.menu;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianrui.web.action.weixin.menu.entity.Button;
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
	public final static String REDIRECT_URI = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	public final static String appid = "wxf22ce076abf3d066";
	public final static String appSecret = "4add7800a76bf23866778b14d69bf6d4";
	/**
	 * 定义菜单结构
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static Menu getMenu() throws UnsupportedEncodingException {
		//服务地址
		String baseuri="http://183-lisijia.imwork.net";
		//授权类型
		String scope = "snsapi_base";
		//项目网页授权访问地址
		String url = baseuri + "/weixin/login/wxLogin";
		String state = "";
		
		ViewButton btn01 = new ViewButton();
		state = "member";
		btn01.setName("会员管理");
		btn01.setType("view");
		btn01.setUrl(REDIRECT_URI.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(url,"UTF-8")).replace("SCOPE", scope).replace("STATE", state));

		ViewButton btn02 = new ViewButton();
		state = "vehicle";
		btn02.setName("车辆管理");
		btn02.setType("view");
		btn02.setUrl(REDIRECT_URI.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(url,"UTF-8")).replace("SCOPE", scope).replace("STATE", state));

		ViewButton btn03 = new ViewButton();
		state = "driver";
		btn03.setName("司机管理");
		btn03.setType("view");
		btn03.setUrl(REDIRECT_URI.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(url,"UTF-8")).replace("SCOPE", scope).replace("STATE", state));

		ViewButton btn04 = new ViewButton();
		state = "kaipiao";
		btn04.setName("开票车辆管理");
		btn04.setType("view");
		btn04.setUrl(REDIRECT_URI.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(url,"UTF-8")).replace("SCOPE", scope).replace("STATE", state));

		ViewButton btn05 = new ViewButton();
		state = "mation";
		btn05.setName("用户信息");
		btn05.setType("view");
		btn05.setUrl(REDIRECT_URI.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(url,"UTF-8")).replace("SCOPE", scope).replace("STATE", state));

		ViewButton btn31 = new ViewButton();
		state = "myrenzhegn";
		btn31.setName("我的认证");
		btn31.setType("view");
		btn31.setUrl(REDIRECT_URI.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(url,"UTF-8")).replace("SCOPE", scope).replace("STATE", state));

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("后台管理");
		mainBtn1.setSub_button(new Button[] { btn01, btn02, btn03, btn04});

//		ComplexButton mainBtn2 = new ComplexButton();
//		mainBtn2.setName("我的运单");
//		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("个人中心");
		mainBtn3.setSub_button(new Button[] { btn05  });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		// 调用接口获取凭证
		Tokens token = CommonUtil.getToken(appid,appSecret);

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
