
//地图选点
var map = null;                         //地图
var myDrawingManagerObject = null;      //地图选点
var infoWindow = null;                  //信息窗口
var geoc = new BMap.Geocoder();         //地理解析
var thisObject = "";                    //创建的对象
var rObj = {
    address: "",                //地址
    province: "",               //省
    city: "",                   //市
    district: "" ,              //区
    lon:"",
    lat:""
}           
var point = "";                         //地图上选择的点的经纬度对象

var opts = {
    width: 200,            // 信息窗口宽度
    height: 100,           // 信息窗口高度
    title: '<span class="dloal">详细位置</span>',    // 信息窗口标题
    enableMessage:false    // 禁止发送信息
};
function MapSetPoint() {
    if (!$("#Layout").length) {
        InitMapLayout();                    //初始化地图弹出层
        InitMap();                          //初始化地图
    }

    //回调函数
    this.callBackFun = null;

    //地图选点函数
    /*
   *  功能：地图选点
   *  param: provinceId :省份id  
   *  param: cityId:城市id
   *  param: districtId：区域id  
   *  param: callBack：点击使用此地址时的回调函数
   *  param: thisObject：创建的对象
   */
    this.openMap = function (option) {
        try {
            //lngInput = lngId||"";               //经度文本框id
            //latInput = latId||"";               //纬度文本框id
            provinceId = option.provinceId || "";      //省份id
            cityId = option.cityId || "";              //城市id
            areaId = option.districtId || "";          //区域id
            if (option.isInput) {
                var province = $("#" + provinceId).val();
                var city = $("#" + cityId).val();
                var area = $("#" + areaId).val();
            } else {
                if (provinceId != "") {
                    var province = $("#" + provinceId + " :selected").text();
                }
                if (provinceId != "") {
                    var city = $("#" + cityId + " :selected").text();
                }
                if (provinceId != "") {
                    var area = $("#" + areaId + " :selected").text();
                }
            }
            //创建的对象
            thisObject = option.thisObject;
            //回调函数
            if (typeof option.callBack != "undefined" && option.callBack != "" && option.callBack != null) {
                this.callBackFun = option.callBack;        
            }
            
            if (province != "请选择" && province != "") {
                if (city != "请选择" && city != "") {
                    if (area != "请选择" && area != "") {
                        map.setCenter(city + area);
                        map.setZoom(13);
                    } else {
                        map.setCenter(province + city);
                        map.setZoom(11);
                    }
                } else {
                    map.setCenter(province);
                    map.setZoom(8);
                }
                //$("#sWeatherTxt").val("选择城市");
            }
            $("#fullbg").show();
            $("#Layout").show();
        }
        catch (ex) {
        	
        }
    };
};

//地图选点图标点击事件
function drawingPoint() {
    if (myDrawingManagerObject != null) {
        myDrawingManagerObject.close();                         //关闭地图选点
    }
    myDrawingManagerObject = new BMapLib.DrawingManager(map, {
        isOpen: true,
        drawingType: BMAP_DRAWING_MARKER,
        enableDrawingTool: false,
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT,
            offset: new BMap.Size(5, 5),
            drawingModes: [
            //BMAP_DRAWING_MARKER,
            //BMAP_DRAWING_CIRCLE,
            //BMAP_DRAWING_POLYLINE,
            //BMAP_DRAWING_POLYGON,
            //BMAP_DRAWING_RECTANGLE
            ]
        }
    });
    myDrawingManagerObject.addEventListener('overlaycomplete', overlaycomplete);      //添加完点绑定事件
};

//初始化地图弹出层
function InitMapLayout() {
    try {
        var Layout = '<div id="Layout" class="Layout">'
                        + '<div class="layoutSearch">'
                           + '<div class="form-group pull-left" style="width:800px; padding-top:8px; margin-bottom:0;">'
                             + '<label class="col-xs-3 control-label no-padding-right" for="searchMapInput"> 搜索地址 </label>'
                                + '<div class="input-group col-xs-7" style="margin-top:2px;">'
                                    + '<input type="text" class="form-control search-query"  id="searchMapInput" placeholder="详细地址"  style="height:30px;"/>'
                                    +'<span class="input-group-btn">'
                                        + '<button type="button" class="btn btn-info btn-sm" onclick="searchMapAddress();" >'
                                            +'<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索'
                                        +'</button>'
                                    +'</span>'
                                + '</div>'
                            + '</div>'
                            + '<div class="LayoutClose"><a href="javascript:void(0);" onclick="closeLayout();"><i class="ace-icon glyphicon glyphicon-remove"></i></a></div>'
                        + '</div>'
                        + '<div id="addressContainer" style="width:0; height:500px;float:left; background:#fff;overflow:auto;">'
                        +'</div>'
                        + '<div class="mapDiv" id="mapDiv">'
                            //+ '<a href="javascript:void(0);" class="CCity"><i></i><input id="sWeatherTxt" class="inputext" type="text" value="选择城市"  readonly="readonly" style="padding-right:10px;"/></a>'
                            + '<a href="javascript:void(0);" class="CCity" title="地图选点" onclick="drawingPoint();"><img src="' + CONTEXTPATH + '/resources/js/utils/baidumap/address.png"/></a>'
                            + '<div id="mapLayout" style="height:500px" class="mapLayout">'
                            +'</div>'
                        +'</div>'
                    + '</div>';                         //拼接弹出层div
        $("body").append(Layout);           //把拼接的标签加到页面
        var offsetTop = ($(window).height() - 650) / 2 + $(window).scrollTop();
        var offsetLeft = ($(window).width() - 900) / 2 + $(window).scrollLeft();
        $("#Layout").offset({ top: offsetTop, left: offsetLeft });
        //解除focus事件
        $(".Layout").on("focus","#searchMapInput",function(e){
        	//console.info(11);
        	 e.preventDefault();
        });
        //$(".Layout").off("focusin","#searchMapInput");
        //$("#searchMapInput").off("focus");
    }
    catch (ex) {
    	console.info(ex);
    }
};

//  初始化地图
function InitMap() {
    try{
        //实例化地图
        map = new BMap.Map("mapLayout", { enableMapClick: false });
        //创建经纬度      
        var initPoint = new BMap.Point(116.404, 39.915);
        //设置地图中心点         
        map.centerAndZoom(initPoint, 14);
        //左上角，添加默认缩放平移控件
        var top_left_navigation = new BMap.NavigationControl({ offset: { width: 30, height: 20 } });
        map.addControl(top_left_navigation);
        //启用滚轮大小缩放
        map.enableScrollWheelZoom();
    }
    catch (ex) {
    	
    }
};

//添加完点事件
function overlaycomplete(e) {
    try{
        var marker = e.overlay;                                 //获取该点
        myDrawingManagerObject.close();                         //关闭地图选点
        marker.enableDragging();                                //让该点可以拖动
        marker.addEventListener('dragstart', dragstart);        //绑定拖动开始事件
        marker.addEventListener('dragend', dragend);            //绑定拖动结束事件
        point = marker.getPosition();                           //获取该点的经纬度对象
        getAddress(point,1);                                      //解析地理位置
    }
    catch (ex) {
    	
    }
};

//解析地理位置
function getAddress(pt,isDrawing) {
    try{
        geoc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            rObj.province = addComp.province;
            rObj.city = addComp.city;
            rObj.district = addComp.district;
            rObj.lon = pt.lng;
            rObj.lat = pt.lat;
            if (isDrawing) {
                rObj.address = addComp.street + addComp.streetNumber;
                infoWindow = new BMap.InfoWindow("地址：" + addComp.province + (addComp.city == addComp.province ? '' : addComp.city) + addComp.district + addComp.street + addComp.streetNumber + "<br/><input type='button' onclick='useAddressClick();' class='btn btn-white btn-pink btn-sm' style='position:absolute;right:0;bottom:0;' value='使用此地址'>", opts);  // 创建信息窗口对象 
                map.openInfoWindow(infoWindow, pt);         //开启信息窗口
                infoWindow.disableCloseOnClick();           //信息窗口不随地图的点击事件而关闭 
            }       
        });
    }
    catch (ex) {
    	
    }
};

//使用此地址点击事件
function useAddressClick() {
    try {
        if (typeof thisObject.callBackFun != "undefined" && thisObject.callBackFun != null && thisObject.callBackFun != "") {
            thisObject.callBackFun(rObj);
        }
        closeLayout();  //关闭地图选点看板
    }
    catch (ex) {
    	
    }
};

//拖动开始事件
function dragstart() {
    try{
        infoWindow.close();     //关闭信息窗口
    }
    catch (ex) {
    	
    }
};

//拖动结束事件
function dragend() {
    try{
        point = this.getPosition();       //获取该点的经纬度对象
        getAddress(point,1);                //解析地理位置
    }
    catch (ex) {
    	
    }
};

function searchMapAddress() {
    var address = $("#searchMapInput").val();
    if (address != "") {
        map.clearOverlays();        //清除地图上的覆盖物
        var local = new BMap.LocalSearch("全国", {
            renderOptions: { map: map, panel: "addressContainer" },
            pageCapacity: 10
        });
        local.search(address);
        local.setInfoHtmlSetCallback(searchMapAddressBack);                 //气泡出现回调函数
        local.setSearchCompleteCallback(function () {                       //检索完成回调函数
            $("#mapDiv").animate({ width: '700px' }, "fast");
            $("#addressContainer").animate({ width: '200px' }, "fast").css("border-right","1px solid #666");
        });
    }
};

//搜索地址按钮点击 事件
function searchMapAddressBack(pois, html) {
    $(html).append("<br/><br/><input type='button' onclick='useAddressClick();' class='btn btn-white btn-pink btn-sm' style='position:absolute;right:0;bottom:0;' value='使用此地址'>");
    //pois.marker.enableDragging();
    var point = pois.marker.getPosition();                           //获取该点的经纬度对象
 //   rObj.address = pois.title;
    rObj.address = pois.address;
    getAddress(point,0);                                      //解析地理位置
};

//点击回车查询地址
function KeySearchAddress(e) {
    e = e || window.event;
    if (e.keyCode == 13) { //回车键的键值为13   
        searchMapAddress();
    }

};

//关闭地图选点面板
function closeLayout() {
    map.clearOverlays();        //清除地图上的覆盖物
    $("#Layout").hide();
    $("#fullbg").hide();
    $("#addressContainer").html('').css("border", "none").width(0);
    $("#searchMapInput").val('');
    $("#mapDiv").width(900);
    $("#point_Div").show();
    $("#shipperreceiver").parent().show();
};
