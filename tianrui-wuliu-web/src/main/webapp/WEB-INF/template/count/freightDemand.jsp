<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>大易物流</title>
    <meta name="keywords" content="大易物流"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">
	<link href="${trRoot}/tianrui/css/huoyun.css" rel="stylesheet">
</head>
<body>
<!-- head -->
<div class="bghygray">
    <div class="wrap1400">
        <label><a href="/publicMember/registerPage" class="colorred"> [免费注册]</a></label>
        <label><a href="/count/route">请[登录]</a></label>
        <label>欢迎来到大易物流平台！</label>
    </div>
</div>
<div class="w1200">
    <div class="hynav">
        <img src="${trRoot}/tianrui/images/hylogo.png">
        <ul>
        	<li ><a href="/count/plan"><h4>首页</h4></a></li>
            <li class="withbg"><a href="/count/freightDemand"><h4>货运基地</h4></a></li>
            <li><a href="/count/freightPlan"><h4>货运计划</h4></a></li>
            <li><a href="/count/onWaybill"><h4>在途运单</h4></a></li>
        </ul>
    </div>
</div>
<!-- head -->

<div class="bgpurple">
    <div class="wrap1920">
        <div class="alldata">
            <div class="dataline fl">
                <h3>货运总计划量</h3>
                <h4 class="coloryello">61291.4万吨</h4>
            </div>
            <div class="dataline fr">
                <h3>车辆总数</h3>
                <h4 class="colorred">61291.4万吨</h4>
            </div>
            <div class="dataline fl">
                <h3>交易总量</h3>
                <h4 class="colorlv">61291.4万元</h4>
            </div>
            <div class="dataline fr">
                <h3>运费总量</h3>
                <h4 class="colorju">61291.4万元</h4>
            </div>
        </div>
    </div>
</div>
<div class="w1200">
    <div class="hysearch">
        <div class="zhaohuo">
            <div class="zhaohuoleft">
                <label>找货</label>
                <span><img src="${trRoot}/tianrui/images/hydown.png" id="gengduo"></span>
            </div>
            <div class="zhaohuoright"></div>
        </div>
        <div class="searcont">
            <div class="sline">
                <label>装货地：</label>
                <input type="text" placeholder="请输入装货地">
            </div>
            <div class="sline">
                <label>卸货地：</label>
                <input type="text" placeholder="请输入装货地">
            </div>
            <div class="sline">
                <label>货物名称：</label>
                <input type="text" placeholder="请输入装货地">
            </div>
            <div class="sline">
                <label>时间：</label>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                       style="width:140px"/>
                <em>-</em>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cz"
                       style="width:140px"/>
            </div>
            <div class="sline">
                <button type="submit" class="btn btnblue" onclick="init()">搜索</button>
                <button type="submit" class="btn btnyello" onclick="reset()">重置</button>

            </div>
        </div>
    </div>
</div>

<div class="bghycont">
    <div class="hycontlist">
        <div class="divju"></div>
        <div class="w1200">
            <div class="hyaddr">
                <div class="hyaddrl fl">
                    <img src="${trRoot}/tianrui/images/hybegin.png">
                    <span>河南省汝州市</span>
                </div>
                <div class="hyaddrr fr">
                    <img src="${trRoot}/tianrui/images/hyend.png">
                    <span>河南省汝州市</span>
                </div>
            </div>
            <div class="hyroad">
                <div class="hyroad_solo fl">
                    <div class="leftfont" id="leftfont1">
                    </div>
                    <div class="lefttip" id="lefttip1">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>
                <div class="hyroadmid fl">
                    <div class="line1">
                        <div class="hyroad_add fl">
                            <img src="${trRoot}/tianrui/images/hytime.png">
                            <span>2017-12-5 至 2017-12-30</span>
                        </div>
                        <div class="fr">
                            <img src="${trRoot}/tianrui/images/hygps.png">
                            <span>6000公里</span>
                        </div>
                    </div>
                    <div class="line2">
                        <img src="${trRoot}/tianrui/images/hyline.png">
                    </div>
                    <div class="line3">
                        <div class="huo fl">
                            <img src="${trRoot}/tianrui/images/hygoods.png">
                            <span>熟料500000吨</span>
                        </div>
                        <div class="price fl">
                            <label>60元/吨</label>
                        </div>
                        <div class="chengyun fr">
                            <a href=""><button type="submit" class="btn">承运</button></a>
                        </div>
                    </div>
                </div>
                <div class="hyroad_solo fr">
                    <div class="rightfont" id="rightfont1">
                    </div>
                    <div class="righttip" id="righttip1">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="hycontlist">
        <div class="divblue"></div>
        <div class="w1200">
            <div class="hyaddr">
                <div class="hyaddrl fl">
                    <img src="${trRoot}/tianrui/images/hybegin.png">
                    <span>河南省汝州市</span>
                </div>
                <div class="hyaddrr fr">
                    <img src="${trRoot}/tianrui/images/hyend.png">
                    <span>河南省汝州市</span>
                </div>
            </div>
            <div class="hyroad">
                <div class="hyroad_solo fl">
                    <div class="leftfont" id="">
                    </div>
                    <div class="lefttip" id="">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>
                <div class="hyroadmid fl">
                    <div class="line1">
                        <div class="hyroad_add fl">
                            <img src="${trRoot}/tianrui/images/hytime.png">
                            <span>2017-12-5 至 2017-12-30</span>
                        </div>
                        <div class="fr">
                            <img src="${trRoot}/tianrui/images/hygps.png">
                            <span>6000公里</span>
                        </div>
                    </div>
                    <div class="line2">
                        <img src="${trRoot}/tianrui/images/hyline.png">
                    </div>
                    <div class="line3">
                        <div class="huo fl">
                            <img src="${trRoot}/tianrui/images/hygoods.png">
                            <span>熟料500000吨</span>
                        </div>
                        <div class="price fl">
                            <label>60元/吨</label>
                        </div>
                        <div class="chengyun fr">
                            <a href=""><button type="submit" class="btn">承运</button></a>
                        </div>
                    </div>
                </div>
                <div class="hyroad_solo fr">
                    <div class="rightfont">
                    </div>
                    <div class="righttip">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="hycontlist">
        <div class="divgreen"></div>
        <div class="w1200">
            <div class="hyaddr">
                <div class="hyaddrl fl">
                    <img src="${trRoot}/tianrui/images/hybegin.png">
                    <span>河南省汝州市</span>
                </div>
                <div class="hyaddrr fr">
                    <img src="${trRoot}/tianrui/images/hyend.png">
                    <span>河南省汝州市</span>
                </div>
            </div>
            <div class="hyroad">
                <div class="hyroad_solo fl">
                    <div class="leftfont" id=" ">
                    </div>
                    <div class="lefttip" id=" ">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>
                <div class="hyroadmid fl">
                    <div class="line1">
                        <div class="hyroad_add fl">
                            <img src="${trRoot}/tianrui/images/hytime.png">
                            <span>2017-12-5 至 2017-12-30</span>
                        </div>
                        <div class="fr">
                            <img src="${trRoot}/tianrui/images/hygps.png">
                            <span>6000公里</span>
                        </div>
                    </div>
                    <div class="line2">
                        <img src="${trRoot}/tianrui/images/hyline.png">
                    </div>
                    <div class="line3">
                        <div class="huo fl">
                            <img src="${trRoot}/tianrui/images/hygoods.png">
                            <span>熟料500000吨</span>
                        </div>
                        <div class="price fl">
                            <label>60元/吨</label>
                        </div>
                        <div class="chengyun fr">
                            <button type="submit">承运</button>
                        </div>
                    </div>
                </div>
                <div class="hyroad_solo fr">
                    <div class="rightfont">
                    </div>
                    <div class="righttip">
                        <p>河南省郑州市郑东新区商务外环与九如路交叉口20号海联大厦11楼1101室</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<jsp:include page="head/foot.jsp"></jsp:include>

<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>

<script type="text/javascript">
    $(function () {
        //弹出地址层
        $("#leftfont1").mouseover(function (){
            $("#lefttip1").css("display","block");
        }).mouseout(function (){
            $("#lefttip1").css("display","none");
        });
        $("#rightfont1").mouseover(function (){
            $("#righttip1").css("display","block");
        }).mouseout(function (){
            $("#righttip1").css("display","none");
        });

        //找货查询
        $(".zhaohuo").on('click',function(){
            var div1 = $(".searcont");
            var div2 = $(".zhaohuoright");
            div1.toggle();
            div2.toggle();
            if(div1.css("display") == 'none') {
                $("#gengduo").attr('src',"${trRoot}/tianrui/images/hydown.png");
            }
            else {

                $("#gengduo").attr('src',"${trRoot}/tianrui/images/hyup.png");
            }
        });

        function show_hidden(obj) {
            if(obj.css("display") == 'block') {
                obj.css("display",'none');

            } else if(obj.css("display") == 'none'){
                obj.css("display",'block');
            }
        }


    });
</script>
</body>
</html>