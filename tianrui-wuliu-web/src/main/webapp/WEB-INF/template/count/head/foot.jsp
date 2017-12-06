<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bgfoot">
    <div class="wrap foot">
        <div class="footcont">
            <img src="${trRoot}/tianrui/images/footlogo2.png">
            <div class="footline">
                <ul>
                    <li><a href="/count/plan">首页</a></li>
                    <li><a href="/count/aboutUs">关于我们</a></li>
                    <li><a href="/count/contactUs">联系我们</a></li>
                </ul>
            </div>
            <div class="footline">
                <a href="http://www.miitbeian.gov.cn"  target="_blank"><p style="color: white;">Copyright © 2016 豫ICP备16036132号-1</p></a>
                <p>中原大易科技有限公司版权所有</p>
            </div>
            <div class="footxin">
                <a href="/zs" target="_blank">
                	<img src="${trRoot}/tianrui/images/xin.png">
             	</a>
            </div>
        </div>
        <div class="footmid">
            <div class="footimg">
                <img src="${trRoot}/tianrui/images/phone_load.png">
                <a href="/publicMember/loadPage" target="_blank"><h4>大易物流客户端APP</h4></a>
            </div>
        </div>
        <div class="foot400">
            <h3>全国服务热线</h3>
            <h4>400-056-1156</h4>
        </div>
    </div>
<script type="text/javascript">
Date.prototype.format = function(f){
    var o ={
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(f))f=f.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(f))f = f.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));return f
}
</script>
</div>