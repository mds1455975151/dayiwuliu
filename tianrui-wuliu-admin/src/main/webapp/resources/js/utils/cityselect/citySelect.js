var PCD_obj = null;

//城市选择的构造函数
function citySelect() {
    
    //初始化城市选择
    this.initCitySelect = function (inputId,thisObj,callBack,e) {
        $(".citybox").hide();
        this.inputTextId = inputId||"";     //文本框的id
        PCD_obj = thisObj || null;           //初始化的对象
        this.EventcallBack = callBack || null;    //回调函数
        this.inputProvinceText = '';
        this.inputCityText = '';
        this.inputDistrictText = '';
        this.inputProvinceValueId = '';
        this.inputCityValueId = '';
        this.inputDistrictValueId = '';

        if (!$("#" + inputId + "Div").length) {
            //拼接省市区框架
            var CityDivHtml = '<div class="citybox f12"  id="' + inputId + 'Div">'
                                    + '<ul>'
                                        //+ '<li class="active" id="' + inputId + '_common">常用</li>'
                                        + '<li id="' + inputId + '_province">省</li>'
                                        + '<li id="' + inputId + '_city">市</li>'
                                        + '<li class="no-border-right" id="' + inputId + '_district">区县</li>'
                                    + '</ul>'
                                    + '<div class="cityTags fix" id="' + inputId + 'Container">'
                                    + '</div>'
                                    + '<div class="clear-data">'
                                        + '<a href="javascript:void(0);" onclick="clearCity();">清空</a>'
                                    + '</div>'
                                + '</div>';
            $("#" + inputId).parent().append(CityDivHtml);
            $("#" + inputId + "_province").on("click", function () { select_provinceClick(); });
            $("#" + inputId + "_city").on("click", function () { select_cityClick(); });
            $("#" + inputId + "_district").on("click", function () { select_districtClick(); });
        }
        //选择省市区文本框点击事件
        $("#" + inputId + "Div").show();
        $("#" + inputId + "_province").click();
        $(document).click(function () {
            //PCD_obj = null;
            $("#" + inputId + "Div").hide();
        });
        $("#" + inputId + "Div").click(function (e) {
            stopBubble(e);
        });
        stopBubble(e);
    };
};

//省份头部点击事件
function select_provinceClick() {
    //添加选中样式
    $("#" + PCD_obj.inputTextId + "Div").find("li").removeClass("active");
    $("#" + PCD_obj.inputTextId + "_province").addClass("active");
    //拼接省份
    spellPrvince();
    PCD_obj.inputCityValueId = '';
};

//拼接省份
function spellPrvince() {
    var data = province_data;
    var dataLen = data.length;
    var tempName;
    //将省市区按照拼音排序
    for (var i = 0; i < dataLen; i++) {
        for(var j = 0; j < dataLen-i-1; j++){
            if (data[j].ShortCName.toUpperCase() > data[j + 1].ShortCName.toUpperCase()) {
                tempName = data[j];
                data[j] = data[j + 1];
                data[j + 1] = tempName;
            }
        } 
    }
    //按照首字母不同进行拼接
    var AGhtml = '<dl><dt>A-G</dt><dd>';
    var HKhtml = '<dl><dt>H-K</dt><dd>';
    var LShtml = '<dl><dt>L-S</dt><dd>';
    var TZhtml = '<dl><dt>T-Z</dt><dd>';
    $.each(data, function (i, n) {
        var firstWord = n.ShortCName.substr(0,1).toUpperCase();         //获取省份的大写首字母
        switch (true) {         
            case firstWord >= 'A' && firstWord <= 'G':
                AGhtml += '<a href="javascript:void(0);" value="' + n.Bin + '" onclick="select_cityClick(\'' + n.Bin + '\',\'' + n.Name + '\');">' + n.Name + '</a>';
                break;
            case firstWord >= 'H' && firstWord <= 'K':
                HKhtml += '<a href="javascript:void(0);" value="' + n.Bin + '" onclick="select_cityClick(\'' + n.Bin + '\',\'' + n.Name + '\');">' + n.Name + '</a>';
                break;
            case firstWord >= 'L' && firstWord <= 'S':
                LShtml += '<a href="javascript:void(0);" value="' + n.Bin + '" onclick="select_cityClick(\'' + n.Bin + '\',\'' + n.Name + '\');">' + n.Name + '</a>';
                break;
            case firstWord >= 'T' && firstWord <= 'Z':
                TZhtml += '<a href="javascript:void(0);" value="' + n.Bin + '" onclick="select_cityClick(\'' + n.Bin + '\',\'' + n.Name + '\');">' + n.Name + '</a>';
                break;
            default: break;
        }
    });
    AGhtml += '</dd></dl>';
    HKhtml += '</dd></dl>';
    LShtml += '</dd></dl>';
    TZhtml += '</dd></dl>';
    $("#" + PCD_obj.inputTextId + "Container").html(AGhtml + HKhtml + LShtml + TZhtml);
};


//城市头部点击事件
function select_cityClick(pCode,pText) {
    $("#" + PCD_obj.inputTextId + "Container").html("");
    //添加选中样式
    $("#" + PCD_obj.inputTextId + "Div").find("li").removeClass("active");
    $("#" + PCD_obj.inputTextId + "_city").addClass("active");
    //拼接城市
    if (typeof pCode != "undefined" || pCode != null) {
        PCD_obj.inputProvinceValueId = pCode;   //记为全局变量，防止点击区域后再点击城市没有数据
        PCD_obj.inputProvinceText = pText;
    }
    if(PCD_obj.inputProvinceValueId!=''){
        var data = city_data;
        var cityHtml = '<dl><dd>';
        $.each(data, function (i, n) {
            if (n.ProvinceBin == PCD_obj.inputProvinceValueId) {
                cityHtml += '<a href="javascript:void(0);" value="' + n.Bin + '" pValue="' + n.ProvinceBin + '" onclick="select_districtClick(\'' + n.Bin + '\',\'' + n.Name + '\');">' + n.Name + '</a>';
            }
        });
        cityHtml += '</dd></dl>';
        $("#" + PCD_obj.inputTextId + "Container").html(cityHtml);
        if (cityHtml == '<dl><dd></dd></dl>') {
            $("#" + PCD_obj.inputTextId + "Div").hide();
            var rData = {
                provinceText: PCD_obj.inputProvinceText
                , cityText: ""
                , districtText: ""
                , provinceValue: PCD_obj.inputProvinceValueId
                , cityValue: PCD_obj.inputProvinceValueId
                , districtValue: PCD_obj.inputProvinceValueId
            };
            PCD_obj.EventcallBack(rData);
        }
    }
};

//区域头部点击事件
function select_districtClick(cCode, cText) {
    //拼接区域
    if (typeof cCode != "undefined" || cCode != null) {
        PCD_obj.inputCityValueId = cCode;
        PCD_obj.inputCityText = cText;
    }
    if (cCode == 0) {
        $("#" + PCD_obj.inputTextId + "Div").hide();
        var rData = {
            provinceText: PCD_obj.inputProvinceText
            , cityText: ""
            , districtText: ""
            , provinceValue: PCD_obj.inputProvinceValueId
            , cityValue: ''
            , districtValue: ''
        };
        PCD_obj.EventcallBack(rData);
        return;
    }
    $("#" + PCD_obj.inputTextId + "Container").html("");
    //添加选中样式
    $("#" + PCD_obj.inputTextId + "Div").find("li").removeClass("active");
    $("#" + PCD_obj.inputTextId + "_district").addClass("active");
    
    if (PCD_obj.inputCityValueId != '') {
        var data = district_data;
        var districtHtml = '<dl><dd>';
        $.each(data, function (i, n) {
            if (n.CityBin == PCD_obj.inputCityValueId) {
                districtHtml += '<a href="javascript:void(0);" value="' + n.Bin + '" pValue="' + n.CityBin + '" ppValue="' + n.ProvinceBin + '" onclick="select_overClick(\'' + n.Bin + '\',\'' + n.Name + '\');">' + n.Name + '</a>';
            }
        });
        districtHtml += '</dd></dl>';
        $("#" + PCD_obj.inputTextId + "Container").html(districtHtml);
        if (districtHtml == '<dl><dd></dd></dl>') {
            $("#" + PCD_obj.inputTextId + "Div").hide();
            var rData = {
                provinceText: PCD_obj.inputProvinceText
                , cityText: PCD_obj.inputCityText
                , districtText: ""
                , provinceValue: PCD_obj.inputProvinceValueId
                , cityValue: PCD_obj.inputCityValueId
                , districtValue: PCD_obj.inputCityValueId
            };
            PCD_obj.EventcallBack(rData);
        }
    }
};

//区域名称的点击事件
function select_overClick(dCode, dText) {
    $("#" + PCD_obj.inputTextId + "Div").hide();
    if (typeof dCode != "undefined" || dCode != null) {
        PCD_obj.inputDistrictValueId = dCode;
        PCD_obj.inputDistrictText = dText;
    };
    var rData = {
        provinceText: PCD_obj.inputProvinceText
        , cityText: PCD_obj.inputCityText
        , districtText: PCD_obj.inputDistrictText == "不限" ? '' : PCD_obj.inputDistrictText
        , provinceValue: PCD_obj.inputProvinceValueId
        , cityValue: PCD_obj.inputCityValueId
        , districtValue: PCD_obj.inputDistrictValueId
    };
    PCD_obj.EventcallBack(rData);
};

/***************************
*阻止浏览器的冒泡事件
*/ 
function stopBubble(e) {
    e = e || window.event;
    //如果提供了事件对象，则这是一个非IE浏览器
    if (e && e.stopPropagation) {
        //因此它支持W3C的stopPropagation()方法
        e.stopPropagation();
    }
    else {
        //否则，我们需要使用IE的方式来取消事件冒泡
        window.event.cancelBubble = true;
    }
};

//清空数据
function clearCity() {
    $("#" + PCD_obj.inputTextId + "Div").hide();
    var rData = {
        provinceText: ''
        , cityText: ''
        , districtText: ''
        , provinceValue: ''
        , cityValue: ''
        , districtValue: ''
    }
    PCD_obj.EventcallBack(rData);
};