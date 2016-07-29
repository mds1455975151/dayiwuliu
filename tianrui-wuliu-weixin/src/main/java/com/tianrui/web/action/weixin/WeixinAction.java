package com.tianrui.web.action.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberPositionService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.front.position.PositionSaveReq;
import com.tianrui.api.req.weixin.CancelMember;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.web.action.weixin.util.entity.ReceiveXmlEntity;
import com.tianrui.web.action.weixin.util.util.FormatXmlProcess;
import com.tianrui.web.action.weixin.util.util.ReceiveXmlProcess;
import com.tianrui.web.action.weixin.util.util.SignUtil;
import com.tianrui.web.filter.TimeFilter;
import com.tianrui.web.util.SessionManager;


@Controller
@RequestMapping("/weixin/")
public class WeixinAction {

	@Autowired
	IMemberPositionService positionService;
	
	@RequestMapping(value = "index",method=RequestMethod.GET)
	@ResponseBody
	public String indxe(
			String signature,
			String timestamp,
			String nonce,
			String echostr 
			) throws IOException{
		// 通过 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
		if (!SignUtil.checkSignature(signature, timestamp, nonce)) {
			echostr = "";
		}
		return echostr;
	}
	
	/**
	 * 处理事件
	 * @throws Exception 
	 */
	@RequestMapping(value = "index",method=RequestMethod.POST)
	@ResponseBody
	public String main(
			String signature,
			String timestamp,
			String nonce,
			String echostr ,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			String respXml = null;
			respXml = processRequest(request,response);
			return respXml;
		}
		return null;
	}
	public String processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			FormatXmlProcess formatxmls = new FormatXmlProcess();
			
			/** 读取接收到的xml消息 */
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			String xml = sb.toString();	//次即为接收到微信端发送过来的xml数据
			
			ReceiveXmlEntity xmlEntity =new ReceiveXmlEntity();
			xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);//解析xml数据
			// xml格式的消息数�?
			String respXml = null;
			String msgType = xmlEntity.getMsgType();//接受的消息类型
			
			String evenkey=xmlEntity.getEvent();//按钮key值
			String contxml="hehe haha";//发送文字内容
			if("LOCATION".equals(evenkey)){
				//维度
				String latitude = xmlEntity.getLatitude();
				//经度
				String longitude = xmlEntity.getLongitude();
				//精度
				String precision = xmlEntity.getPrecision();
				contxml = "当前位置：\n  维度："+latitude+"\n  经度："+longitude+"\n  精度："+precision+"\n token:"+TimeFilter.TOKEN.getAccessToken();
				//拼装查询条件
	//			PositionSaveReq saveBean = new PositionSaveReq();	
	////			saveBean.setCurrId(uId);
	//			Result rs =positionService.savePosition(saveBean);
			}
			respXml = formatxmls.formatXmlAllContent(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), contxml);
			return respXml;
		}

}
