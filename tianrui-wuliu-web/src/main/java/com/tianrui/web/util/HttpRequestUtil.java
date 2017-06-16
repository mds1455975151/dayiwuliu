package com.tianrui.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;


import com.alibaba.fastjson.JSONObject;


public class HttpRequestUtil {

	public static void main(String[] args) {
//		readTxt();
		System.out.println(putRequest("6217858000061216567"));
	}
	
	public static String putRequest(String bankcord){
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?cardNo="+bankcord+"&cardBinCheck=true&callback=true";
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
//            out.print("key="+JSON.toJSONString(req));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = in.readLine()) != null) {
                result += line;
            }
            JSONObject obj = JSONObject.parseObject(result);
            System.out.println(obj);
            if((boolean) obj.get("validated")){
            	String bankname = readTxt().get(obj.get("bank")).toString();
            	result = bankname;
            }else{
            	result = "未识别的银行卡";
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
	}
	public static JSONObject readTxt(){
	    String	type = "{\"JNBANK\":\"济宁银行\",\"EGBANK\":\"恒丰银行\",\"JLBANK\":\"吉林银行\",\"GDB\":\"广东发展银行\",\"HURCB\":\"湖北省农村信用社\",\"TCCB\":\"天津银行\",\"CSCB\":\"长沙银行\",\"LSBANK\":\"莱商银行\",\"YXCCB\":\"玉溪市商业银行\",\"BZMD\":\"驻马店银行\",\"DAQINGB\":\"龙江银行\",\"HANABANK\":\"韩亚银行\",\"HKBEA\":\"东亚银行\",\"CMBC\":\"中国民生银行\",\"SPABANK\":\"平安银行\",\"BANKWF\":\"潍坊银行\",\"DYCB\":\"德阳商业银行\",\"BOCY\":\"朝阳银行\",\"SXCB\":\"绍兴银行\",\"BJRCB\":\"北京农村商业银行\",\"BODD\":\"丹东银行\",\"BOHAIB\":\"渤海银行\",\"GYCB\":\"贵阳市商业银行\",\"MTBANK\":\"浙江民泰商业银行\",\"PSBC\":\"中国邮政储蓄银行\",\"CZBANK\":\"浙商银行\",\"NBYZ\":\"鄞州银行\",\"BSB\":\"包商银行\",\"DLB\":\"大连银行\",\"SRCB\":\"深圳农村商业银行\",\"URMQCCB\":\"乌鲁木齐市商业银行\",\"HBHSBANK\":\"湖北银行黄石分行\",\"XTB\":\"邢台银行\",\"WHCCB\":\"威海市商业银行\",\"CZRCB\":\"常州农村信用联社\",\"TACCB\":\"泰安市商业银行\",\"LZYH\":\"兰州银行\",\"HRXJB\":\"华融湘江银行\",\"JLRCU\":\"吉林农信\",\"LSBC\":\"临商银行\",\"HNRCC\":\"湖南省农村信用社\",\"JSB\":\"晋商银行\",\"TCRCB\":\"江苏太仓农村商业银行\",\"SDEB\":\"顺德农商银行\",\"NBBANK\":\"宁波银行\",\"CMB\":\"招商银行\",\"LYCB\":\"辽阳市商业银行\",\"CDB\":\"国家开发银行\",\"HNRCU\":\"河南省农村信用\",\"CDCB\":\"成都银行\",\"ZZBANK\":\"郑州银行\",\"NHB\":\"南海农村信用联社\",\"JINCHB\":\"晋城银行JCBANK\",\"KLB\":\"昆仑银行\",\"HSBANK\":\"徽商银行\",\"YDRCB\":\"尧都农商行\",\"CITIC\":\"中信银行\",\"NJCB\":\"南京银行\",\"NXBANK\":\"宁夏银行\",\"TRCB\":\"天津农商银行\",\"NXRCU\":\"宁夏黄河农村商业银行\",\"SRBANK\":\"上饶银行\",\"GLBANK\":\"桂林银行\",\"ABC\":\"中国农业银行\",\"SCCB\":\"三门峡银行\",\"SJBANK\":\"盛京银行\",\"ZRCBANK\":\"张家港农村商业银行\",\"CEB\":\"中国光大银行\",\"ZBCB\":\"齐商银行\",\"XYBANK\":\"信阳银行\",\"GZRCU\":\"贵州省农村信用社\",\"DRCBCL\":\"东莞农村商业银行\",\"CGNB\":\"南充市商业银行\",\"XLBANK\":\"中山小榄村镇银行\",\"HBYCBANK\":\"湖北银行宜昌分行\",\"NYNB\":\"广东南粤银行\",\"BGB\":\"广西北部湾银行\",\"ARCU\":\"安徽省农村信用社\",\"GDRCC\":\"广东省农村信用社联合社\",\"FXCB\":\"阜新银行\",\"WZCB\":\"温州银行\",\"QDCCB\":\"青岛银行\",\"CRCBANK\":\"重庆农村商业银行\",\"AYCB\":\"安阳银行\",\"BJBANK\":\"北京银行\",\"SXRCCU\":\"陕西信合\",\"XABANK\":\"西安银行\",\"HZCB\":\"杭州银行\",\"ICBC\":\"中国工商银行\",\"ZJKCCB\":\"张家口市商业银行\",\"H3CB\":\"内蒙古银行\",\"CZCB\":\"浙江稠州商业银行\",\"GRCB\":\"广州农商银行\",\"WHRCB\":\"武汉农村商业银行\",\"JXRCU\":\"江西省农村信用\",\"BOD\":\"东莞银行\",\"BOC\":\"中国银行\",\"CBBQS\":\"城市商业银行资金清算中心\",\"BOQH\":\"青海银行\",\"SHRCB\":\"上海农村商业银行\",\"JSRCU\":\"江苏省农村信用联合社\",\"KSRB\":\"昆山农村商业银行\",\"ZYCBANK\":\"遵义市商业银行\",\"CBKF\":\"开封市商业银行\",\"JSBANK\":\"江苏银行\",\"HBRCU\":\"河北省农村信用社\",\"BHB\":\"河北银行\",\"BOP\":\"平顶山银行\",\"HDBANK\":\"邯郸银行\",\"GSRCU\":\"甘肃省农村信用\",\"NCB\":\"南昌银行\",\"QLBANK\":\"齐鲁银行\",\"TZCB\":\"台州银行\",\"ASCB\":\"鞍山银行\",\"SHBANK\":\"上海银行\",\"FDB\":\"富滇银行\",\"LSCCB\":\"乐山市商业银行\",\"JJBANK\":\"九江银行\",\"JZBANK\":\"晋中市商业银行\",\"JXBANK\":\"嘉兴银行\",\"CSRCB\":\"常熟农村商业银行\",\"CCB\":\"中国建设银行\",\"BOSZ\":\"苏州银行\",\"LANGFB\":\"廊坊银行\",\"CIB\":\"兴业银行\",\"WRCB\":\"无锡农村商业银行\",\"GZB\":\"赣州银行\",\"KORLABANK\":\"库尔勒市商业银行\",\"COMM\":\"交通银行\",\"ZJTLCB\":\"浙江泰隆商业银行\",\"YNRCC\":\"云南省农村信用社\",\"BOYK\":\"营口银行\",\"HKB\":\"汉口银行\",\"XXBANK\":\"新乡银行\",\"SZSBK\":\"石嘴山银行\",\"HBC\":\"湖北银行\",\"CQBANK\":\"重庆银行\",\"HXBANK\":\"华夏银行\",\"GXRCU\":\"广西省农村信用\",\"LYBANK\":\"洛阳银行\",\"FSCB\":\"抚顺银行\",\"SCRCU\":\"四川省农村信用\",\"ZJNX\":\"浙江省农村信用社联合社\",\"SDRCU\":\"山东农信\",\"JRCB\":\"江苏江阴农村商业银行\",\"DZBANK\":\"德州银行\",\"ZGCCB\":\"自贡市商业银行\",\"ORBANK\":\"鄂尔多斯银行\",\"DYCCB\":\"东营市商业银行\",\"YQCCB\":\"阳泉银行\",\"JHBANK\":\"金华银行\",\"FJHXBC\":\"福建海峡银行\",\"HZCCB\":\"湖州市商业银行\",\"HSBK\":\"衡水银行\",\"CCQTGB\":\"重庆三峡银行\",\"BOJZ\":\"锦州银行\",\"SPDB\":\"上海浦东发展银行\",\"BOCD\":\"承德银行\",\"WJRCB\":\"吴江农商银行\",\"YBCCB\":\"宜宾市商业银行\",\"XCYH\":\"许昌银行\",\"NHQS\":\"农信银清算中心\",\"BOZK\":\"周口银行\",\"CDRCB\":\"成都农商银行\",\"GCB\":\"广州银行\"}";
		JSONObject obj = JSONObject.parseObject(type);
	    return obj;
	}
}
