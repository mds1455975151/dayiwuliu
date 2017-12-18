package com.tianrui.trwl.admin.web.report;

import java.util.HashMap;
import java.util.Map;

import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.common.utils.DateUtil;

public class TemplateTrajectory{
	
	private static final Map<String, String> templateMap;  
    static  
    {  
    	templateMap = new HashMap<String, String>();  //routeName,billNo
    	templateMap.put("卫辉市天瑞水泥有限公司00至天瑞集团周口水泥有限公司", "YD1712130278");
    	templateMap.put("汝州小屯00至天瑞集团周口水泥有限公司", "YD1712130325");
    	templateMap.put("卫辉市天瑞水泥有限公司至天瑞集团周口水泥有限公司", "YD1712130278");
    	templateMap.put("汝州小屯70至天瑞集团周口水泥有限公司", "YD1712130325");
    	templateMap.put("汝州小屯67至天瑞集团周口水泥有限公司", "YD1712130325");
    	templateMap.put("汝州小屯至天瑞集团周口水泥有限公司", "YD1712130325");
    	templateMap.put("天瑞集团禹州水泥有限公司至郑州天瑞水泥有限公司", "SP171130067");
    	templateMap.put("浅井至中牟熟料运输", "YD1708290965");
    	templateMap.put("荥阳至中牟熟料运输", "YD1710260054");
    	templateMap.put("辽塔至辽宁鸿河集团盛兴水泥运输", "YD1708020445");
    	templateMap.put("天津市南港码头至天津公司", "YD1708271176");
    	templateMap.put("天津市佳誉通商贸公司至天津公司", "YD1708271176");
    	templateMap.put("石龙区至商丘", "YD1709151370");
    	templateMap.put("青山至鸭河熟料运输线路", "YD1711280328");
    	templateMap.put("大连公司至天津公司熟料运输路线", "YD1707160155");
    	templateMap.put("瑞平石龙至信阳天瑞熟料运输线路", " YD1710230045");
    	templateMap.put("禹州一期至许昌公司熟料运输路线", "YD1709010088");
    	templateMap.put("大连公司至天津公司熟料运输路线", "YD1707160155");
    	templateMap.put("海城公司至金海岸", "YD1703220009");
    	templateMap.put("大连公司至金海岸", "YD1711090604");
    	templateMap.put("光山至信阳熟料运输线路叁壹贰国道", "YD1710170357");
    	templateMap.put("瑞平至姚电熟料运输路线", "YD1711280220");
    	templateMap.put("禹州一期至许昌公司熟料运输路线", "YD1709010088");
    	templateMap.put("卫辉至商丘熟料运输路线", "YD1707190773");
    	templateMap.put("汝州平煤矿产品至瑞平电厂", "YD1712060140");
    	templateMap.put("下陈站台平煤物流1至瑞平电厂", "YD1711070688");
    	templateMap.put("天瑞焦化至瑞平电厂", "YD1710210542");
    	templateMap.put("建北矿至浅井水泥厂", "SP171102214");
    	templateMap.put("顺通煤场至新登水泥厂", "SP170928213");
    	templateMap.put("顺通煤场至光山水泥厂", "SP171027021");
    	templateMap.put("火石咀煤矿至浅井水泥厂", "SP170929433");
    	templateMap.put("天瑞万象商贸物流有限公司至瑞平电厂", "YD1711130146");
    	templateMap.put("平顶山天安煤业香山矿有限公司至瑞平电厂", "YD1711200110");
	   	templateMap.put("铜川海发煤业至裕泰水泥", "SP171208002");
	   	templateMap.put("铜川海发煤业至南召水泥", "SP171211005");
	   	templateMap.put("铜川海发煤业至光山水泥", "SP170801128");
	   	templateMap.put("建北矿至小屯煤场", "SP171030003");
	   	templateMap.put("铜川海发煤业至小屯煤场", "SP170808017");
	   	templateMap.put("顺通煤场至巩义水泥厂", "SP170918483");
	   	templateMap.put("庇山矿至瑞平电厂", "YD1710040048");
	   	templateMap.put("长兴岛港口至大连天瑞神华原煤", "YD1710180121");
	   	templateMap.put("戎庄武汉煤焦矿产品至瑞平电厂", "YD1710260750");
	   	templateMap.put("下陈站台天旭商贸至瑞平电厂", "YD1711110024");
		templateMap.put("火石咀至小屯煤场", "SP170928269");
		templateMap.put("火石咀至萧县水泥厂", "SP170908111");
		templateMap.put("火石咀至卫辉水泥厂", "SP170929444");
		templateMap.put("火石咀煤矿至荥阳水泥厂", "SP170928214");
		templateMap.put("顺通煤场至卫辉水泥厂", "SP171014225");
		templateMap.put("下沟矿至萧县水泥厂", "SP171018143");
		templateMap.put("下沟矿至小屯煤场", "SP171020044");
		templateMap.put("建北矿至汝州水泥", "SP171108034");
		templateMap.put("建北矿至卫辉水泥", "SP171106023");
		templateMap.put("建北矿至光山水泥", "SP171026082");
		templateMap.put("建北矿至萧县水泥", "SP171210032");
		templateMap.put("建北矿至南召水泥", "SP171106029");
		templateMap.put("建北矿至裕泰水泥", "SP171208031");
		templateMap.put("下沟矿至浅井水泥厂", "SP171103113");
		templateMap.put("顺通煤场至浅井水泥厂", "SP171113124");
		templateMap.put("铜川海发煤场至浅井水泥", "SP171026070");
		templateMap.put("铜川海发煤业至卫辉水泥", "SP171017081");
		templateMap.put("铜川海发煤业至荥阳水泥", "SP171016124");
		templateMap.put("火石咀至汝州水泥厂", "SP170905080");
		templateMap.put("火石咀煤矿至裕泰公司", "SP170929395");
		templateMap.put("火石咀至光山水泥厂", "SP170905105");
		templateMap.put("火石咀至南召水泥厂", "SP170817226");
		templateMap.put("下沟矿至南召水泥厂", "SP171105124");
		templateMap.put("下沟矿至光山水泥厂", "SP171018063");
		templateMap.put("下沟矿至临汝镇水泥厂", "SP171104006");
		templateMap.put("下沟矿至汝州水泥厂", "SP171016082");
		templateMap.put("下沟矿至卫辉水泥厂", "SP171015223");
		templateMap.put("下沟矿至荥阳水泥厂", "SP170929455");
		templateMap.put("顺通煤场至荥阳水泥厂", "SP171105089");
		templateMap.put("建北矿至萧县水泥", "SP171211001");
		templateMap.put("建北矿至光山水泥", "SP171106034");
		templateMap.put("下沟煤矿至南召水泥厂", "SP170714101");
		templateMap.put("下沟矿至临汝镇水泥厂", "SP171107126");
		templateMap.put("下沟矿至光山水泥厂", "SP171019105");
		templateMap.put("顺通煤场至南召水泥厂", "SP171110077");
		templateMap.put("顺通煤场至小屯煤场", "SP171114136");
		templateMap.put("顺通煤场至裕泰水泥", "SP171113112");
		templateMap.put("顺通煤场至汝州水泥厂", "SP171020032");
		templateMap.put("顺通煤场至萧县", "SP171027243");
		templateMap.put("建北矿至汝州水泥厂", "SP170714097");
		templateMap.put("下沟煤矿至卫辉水泥厂", "SP170713088");
		templateMap.put("下沟煤矿至汝州水泥厂", "SP170713002");
		templateMap.put("下沟煤矿至荥阳水泥厂", "SP170713098");
		templateMap.put("铜川海发煤业到荥阳水泥厂", "SP170715068");
		templateMap.put("铜川海发煤业到卫辉水泥厂", "SP170716100");
		templateMap.put("铜川海发煤业至汝州水泥厂", "SP170709025");
		templateMap.put("亭南煤矿至光山水泥", "SP170712006");
		templateMap.put("铜川关庄煤场至光山公司", "SP171027263");
		templateMap.put("顺通煤场至禹州浅井水泥厂", "SP170703081");
		templateMap.put("旬邑燕家河至汝州水泥厂", "SP170531035");
		templateMap.put("顺通煤场至小屯煤场", "SP171114136");
		templateMap.put("顺通煤场至南召水泥厂", "SP171110077");
		templateMap.put("燕家河至小屯煤场", "SP170822065");
		templateMap.put("火石咀煤矿至禹州水泥浅井公司", "SP170628051");
		templateMap.put("燕家河至汝州水泥厂", "SP170605117");
		templateMap.put("顺通煤场至卫辉水泥厂", "SP171014202");
		templateMap.put("福达煤场至南召水泥", "SP171030088");
		templateMap.put("铜川至南召水泥", "SP170816017");
		templateMap.put("铜川煤场至荥阳公司", "SP170707047");
		templateMap.put("铜川关庄煤场至荥阳公司", "SP171031103");
		templateMap.put("火石咀煤矿至南召水泥", "SP170713312");
		templateMap.put("火石咀煤矿至光山水泥公司", "SP170712242");
		templateMap.put("建北矿至卫辉原煤运输路线", "SP170712026");
		templateMap.put("建北矿至临汝镇原煤运输路线", "SP170613056");
		templateMap.put("建北矿至汝州原煤运输路线", "SP170707224");
		templateMap.put("建北矿至小屯煤场原煤运输路线", "SP170621062");
		templateMap.put("建北矿至光山原煤运输路线", "SP170709011");
		templateMap.put("建北矿至南召原煤运输路线", "SP170515008");
		templateMap.put("顺通煤场至裕泰水泥", "SP171112123");
    	templateMap.put("铜川海发煤场至浅井水泥厂", "SP171204007");
    	templateMap.put("彬县火石咀煤矿至荥阳水泥厂", "SP170712218");
    	templateMap.put("建北矿至萧县原煤运输路线", "SP170527117");
    	templateMap.put("火石咀煤矿至汝州水泥有限公司u", "SP170713134");
    	templateMap.put("火石咀煤矿至萧县水泥有限公司", "SP170619002");
    	templateMap.put("火石咀煤矿至裕泰公司", "SP170929395");
    	templateMap.put("火石咀煤矿至小屯煤场", "SP170713270");
    	templateMap.put("火石咀至卫辉运输路线", "SP170714007");
    	templateMap.put("顺通煤场至汝州", "SP171020033");
    	templateMap.put("顺通煤场至荥阳水泥厂", "SP171108195");
    	templateMap.put("顺通煤场至光山水泥厂", "SP171025058");
    	templateMap.put("铜川海发煤场至萧县水泥厂", "SP171216005");
    	templateMap.put("铜川福达煤场至南召水泥", "SP171028123");
    	templateMap.put("天瑞集团石龙水泥有限公司至天瑞集团周口水泥有限公司", "YD1712070003");
    }
	public static Map<String, String> getTemplatemap() {
		return templateMap;
	}  
    public static void main(String[] args) {
		System.out.println(DateUtil.getDateString(1512195831902L, "yyyyMMdd HH:mm:ss"));
		System.out.println(DateUtil.getDateString(1512196800830L, "yyyyMMdd HH:mm:ss"));
	}
}
