<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${trRoot}/tianrui/css/bootstrap.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/base.css" rel="stylesheet">
    <link href="${trRoot}/tianrui/css/dystyle.css" rel="stylesheet">
    

</head>
<body>
<!-- head -->
<jsp:include page="head/conhead.jsp"></jsp:include>
<!-- head -->

<div class="data_yunying wrap">
    <h5 class="fbold">运营数据</h5>
    <div class="data_road">
        <div class="data_roadtit">
            <label>运输路线</label>
        </div>
        <div class="data_roadall">
            <div class="data_roadl">
                <div id="container" style="height: 500px; min-width: 310px; max-width: 800px; margin: 0 auto"></div>
            </div>
            <div class="data_roadm">
                <div class="data_percent">
                    <div class="data_pline">
                        <label >河南</label>
                        <span class="coloryello">9条</span>
                    </div>
                    <div class="data_pline">
                        <label >河南</label>
                        <span class="colorgreen">99条</span>
                    </div>
                    <div class="data_pline">
                        <label >河南</label>
                        <span class="colorred">9999条</span>
                    </div>
                </div>
                <div class="data_big">
                    <h5>运输最热路线</h5>
                    <p>洛阳---------漯河</p>
                    <p>洛阳--南阳--信阳--漯河</p>
                    <p>洛阳--南阳--信阳--漯河</p>
                    <p>洛阳--南阳--信阳--漯河</p>
                    <p>洛阳--南阳--信阳--漯河</p>
                    <p>洛阳--南阳--信阳--漯河</p>
                </div>
            </div>
            <div class="data_roadr mt60">
                <div class="data_roadrt border_goods">
                    <h5>运输路线总数</h5>
                    <p>
                        <label class="colorgreen">86456452</label>
                        <span>条</span>
                    </p>
                </div>
                <div class="data_roadrb">
                    <h5>本月新增</h5>
                    <p>
                        <label class="colorred">864512256452</label>
                        <span>条</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bghui">
    <div class="wrap foot">
        <div class="footcont">
            <p>中原大易科技 Copyright © 2016 豫ICP备12615289号-86</p>
        </div>
        <div class="footimg">
            <p>安卓客户端</p>
            <img src="images/erw.png">
        </div>
    </div>

</div>

<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${trRoot}/tianrui/js/bootstrap.js"></script>
<script src="${trRoot}/tianrui/js/highcharts.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-map.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-data.js"></script>
<script src="${trRoot}/tianrui/js/highcharts-drilldown.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/228/zroo4bdf/cn-china-by-peng8.js"></script>
<script>
$(function () {
    Highcharts.setOptions({
        lang:{
            drillUpText:"返回 > {series.name}"
        }
    });

    var data = Highcharts.geojson(Highcharts.maps['countries/cn/custom/cn-all-china']),small = $('#container').width() < 400;

    // 给城市设置随机数据
    $.each(data, function (i) {
        this.drilldown = this.properties['drill-key'];
        this.value = i;
    });
    function getPoint(e){
        console.log(e.point.name);
    }
    function getShow(e){
        alert(1);
    }
    //初始化地图
    $('#container').highcharts('Map', {

        chart : {
            spacingBottom:30,

            events: {
                drilldown: function (e) {

                    if (!e.seriesOptions) {
                        var chart = this;
                        /*   mapKey = 'countries/china/' + e.point.drilldown + '-all',
                         fail = setTimeout(function () {
                         if (!Highcharts.maps[mapKey]) {
                         chart.showLoading('<i class="icon-frown"></i> 加载失败 ' + e.point.name);

                         fail = setTimeout(function () {
                         chart.hideLoading();
                         }, 1000);
                         }
                         }, 10000);*/
                        var cname=e.point.properties["cn-name"];
                        chart.showLoading('<i class="icon-spinner icon-spin icon-3x"></i>');
                        // 加载城市数据
                        $.ajax({
                            type: "GET",
                            url: "http://data.hcharts.cn/jsonp.php?filename=GeoMap/json/"+ e.point.drilldown+".geo.json",
                            contentType: "application/json; charset=utf-8",
                            dataType:'jsonp',
                            crossDomain: true,
                            success: function(json) {
                                data = Highcharts.geojson(json);

                                $.each(data, function (i) {

                                    this.value = i;
                                    this.events={};
                                    this.events.click=getPoint;

                                });
                                chart.hideLoading();

                                chart.addSeriesAsDrilldown(e.point, {
                                    name: e.point.name,
                                    data: data,
                                    events:{
                                        show:function(){
                                            alert(1);
                                        }
                                    },
                                    dataLabels: {
                                        enabled: true,
                                        format: '{point.name}'
                                    }
                                });
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {

                            }
                        });
                    }


                    this.setTitle(null, { text: cname });
                },
                drillup: function () {
                    this.setTitle(null, { text: '中国' });
                }
            }
        },
        tooltip: {
            formatter:function(){
                var htm="各省运输路线数<br/>";

                if(this.point.drilldown){
                    htm+=this.point.properties["cn-name"];
                }else{
                    htm+=this.point.name;
                }
                htm+=":"+this.point.value;
                return htm;

            }
        },
        credits:{
            href:"javascript:goHome()",
            text:" "
        },
        title : {
            text : ' '
        },

        subtitle: {
            text: '中国',
            floating: true,
            align: 'right',
            y: 50,
            style: {
                fontSize: '16px'
            }
        },

        legend: small ? {} : {
            // enabled: false,
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },
        //tooltip:{
        //pointFormat:"{point.properties.cn-name}:{point.value}"
        //},
        colorAxis: {
            min: 0,
            minColor: '#f7f7f7',
            maxColor: '#d3d3d3',
            labels:{
                style:{
                    "color":"red","fontWeight":"bold"
                }
            }
        },

        mapNavigation: {
            enabled: true,
            buttonOptions: {
                verticalAlign: 'bottom'
            }
        },

        plotOptions: {
            map: {
                states: {
                    hover: {
                        color: '#048ddd'
                    }
                }
            }
        },

        series : [{
            data : data,
            name: '中国',
            dataLabels: {
                enabled: true,
                format: '{point.properties.cn-name}'
            }
        }],

        drilldown: {
            activeDataLabelStyle: {
                color: '#000000',
                textDecoration: 'none',
                background:'none',
                font:'normal'
            },
            drillUpButton: {
                relativeTo: 'spacingBox',
                position: {
                    x: 0,
                    y: 60
                }
            }
        }
    });
});

var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
        52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
        -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
        -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
        41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);


function base64decode(str) {
    var c1, c2, c3, c4;
    var i, len, out;

    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
        /* c1 */
        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c1 == -1);
        if(c1 == -1)
            break;

        /* c2 */
        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c2 == -1);
        if(c2 == -1)
            break;

        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

        /* c3 */
        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if(c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while(i < len && c3 == -1);
        if(c3 == -1)
            break;

        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

        /* c4 */
        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if(c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while(i < len && c4 == -1);
        if(c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}
function goHome(){
    window.open("http://www.peng8.net/");
}
function getGithub()
{
    $.getJSON("https://api.github.com/repos/peng8/GeoMap/contents/json/bei_jing.geo.json", function(data){
        console.log(base64decode(data.content));
    });

}
</script>

</body>
</html>