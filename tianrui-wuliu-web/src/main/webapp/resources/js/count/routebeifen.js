$(function () {
	selectRoute("河南");
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
                    }
                    selectRoute(cname);
                    this.setTitle(null, { text: cname });
                },
                drillup: function () {
                    this.setTitle(null, { text: '中国' });
                }
            }
        },
        tooltip: {
            formatter:function(){
                var htm="";

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

function selectRoute(name){
	$.ajax({
		url : "/count/routeLine",//
		data : {"routename":name},
		type : "post",
		success : function(rs){
			if(rs.code==000000){
				var list = rs.data;
				if(list.length==0){
					$("#sumroutedate").html("暂无");
					$("#remarkname").html(name);
				}else{
					$("#sumroutedate").html(list[0].sumdate+"个");
					$("#remarkname").html(list[0].desc1);
				}
				
			}
		}	
	});
}

