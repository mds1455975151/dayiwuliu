package com.tianrui.web.action.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianrui.api.intf.IMemberPositionService;
import com.tianrui.api.req.front.position.PositionSaveReq;
import com.tianrui.common.vo.Result;
import com.tianrui.web.action.weixin.util.entity.ReceiveXmlEntity;
import com.tianrui.web.action.weixin.util.util.FormatXmlProcess;
import com.tianrui.web.action.weixin.util.util.ReceiveXmlProcess;
import com.tianrui.web.action.weixin.util.util.SignUtil;


@Controller
@RequestMapping("/weixin/")
public class WeixinAction {

	@Autowired
	IMemberPositionService positionService;
	
	
	@RequestMapping(value = "index",method=RequestMethod.GET)
	public void indxe(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String signature = request.getParameter("signature");
		// 时间�?
		String timestamp = request.getParameter("timestamp");
		// 随机�?
		String nonce = request.getParameter("nonce");
		// 随机字符�?
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		// 通过�?�� signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	
	/**
	 * 处理事件
	 * @throws Exception 
	 */
	@RequestMapping(value = "index",method=RequestMethod.POST)
	public void main(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 将请求�?响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名�?时间戳�?随机�?
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		PrintWriter out = response.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			String respXml = null;
			respXml = processRequest(request,response);
			System.out.println("lastxml="+respXml);
			out.print(respXml);	
		}
		out.close();
		out = null;	
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
			contxml = "当前位置：\n  维度："+latitude+"\n  经度："+longitude+"\n  精度："+precision;
			//拼装查询条件
			PositionSaveReq saveBean = new PositionSaveReq();	
//			saveBean.setCurrId(uId);
			Result rs =positionService.savePosition(saveBean);
		}
		respXml = formatxmls.formatXmlAllContent(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), contxml);
		return respXml;
	}

}
