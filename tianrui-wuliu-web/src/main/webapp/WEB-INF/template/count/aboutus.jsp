<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>关于我们</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">
    

</head>
<body>
<!-- head -->
<div class="bghui">
    <div class="wrap">
        <!--登录头部行begin-->
        <div class="header">
            <div class="header_left">
                <label class="mr10">大易物流平台</label>
                <a href="/count/route"><label> 请[登录]</label></a>
                <a href="/publicMember/registerPage" class="colorreg"><label> [免费注册]</label></a>
            </div>
            <div class="header_right">
                <ul class="about_head">
                    <li><a href="/count/contactUs">联系我们</a></li>
                    <li class="select"><a href="/count/aboutUs">关于我们</a></li>
                </ul>
            </div>
        </div>
        <!--登录头部行end-->
    </div>
    <div class="clear"></div>
</div>
<!-- head -->

<div class="aboutimg">
</div>
<div class="wrap">
    <div class="aboutus">
        <h3>关于我们</h3>
        <h4>ABOUT US</h4>
        <div class="content">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中原大易科技有限公司隶属于天瑞集团股份有限公司，其前身为天瑞集团物流运输管理部，主要从事天瑞集团所属企业铸造、水泥、煤电、铝业、矿产品等原材料及产成品专业运输管理运营、仓储及物流代理服务，运输管理业务覆盖范围拓展到河南、山东、辽宁、山西、内蒙、新疆、天津等十个省份。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2016年09月05日，中原大易科技有限公司注册成立，企业注册资本5000万。凭借传统物流服务优势与管理经验，积极拓展电商物流网络覆盖渠道，构建线上线下端到端的立体化服务网络，实现物流过程的全程可视化管理.。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中原大易科技有限公司是交通部无车承运人试点单位，依托河南省国家税务局货运业务网络服务平台，通过车辆定位、运单数据、业务完整，保障运输业务数据真实有效，规避了企业的涉税风险，并积极拓展线上线下交易、保险、金融、融资租赁等相关服务。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过物流大数据分析技术，帮助货主制定不同的发货计划，帮助承运司机获得线路预报，提前做好承运准备，真正为客户提供创造性的物流服务与体验，引领中国现代物流的前进与发展，实现中国物流行业的蜕变与革新。打造物流行业最优秀的信息服务平台。</p>
        </div>
    </div>
</div>
<jsp:include page="head/foot.jsp"></jsp:include>

<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
</body>
</html>