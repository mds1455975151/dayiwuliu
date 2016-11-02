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
<script type="text/javascript" src="${trRoot}/tianrui/js/highcharts-cn-china.js" charset="utf-8"></script>

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
    //初始化地图
    $('#container').highcharts('Map', {

        chart : {
            spacingBottom:30,

            events: {
                drilldown: function (e) {

                    if (!e.seriesOptions) {
                        var chart = this;
                        var cname=e.point.properties["cn-name"];
                        //chart.showLoading('<i class="icon-spinner icon-spin icon-3x"></i>');
                        // 加载城市数据
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
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },
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


</script>

</body>
</html>