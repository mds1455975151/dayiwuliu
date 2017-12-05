/**
 * 登录后界面菜单与后台交互
 * 
 * @author kowuka 
 * @time 2016.04.12
 */

$(function(){ 
	$.ajax({
		url : CONTEXTPATH + '/mainMenu/queryMainMenu.json',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			if( !result || result.code !="000000" ){
				alert(result.error);
			}else{
				var menuList = result.data;
				$(".headnav").empty();
				for (var i = 0; i < menuList.length; i++) {
						var span = $("<span></span>").addClass("text").html(menuList[i].afternodename);
						var href = $("<a></a>").attr("tittle","").attr("href",menuList[i].afterurl+menuList[i].id);
						var li = $("<li></li>");
						if (menuList[i].id == $("#menuId").val()) {
							li.addClass("header_li");
							li.addClass("active");
							getNodeList(menuList[i]);
						}
						//li.click(getNodeList(menuList[i]));
						href.append(span);
						li.append(href);
						$(".headnav").append(li);
				}
				var html = "";
				$(".headnav").append(' <li class="dropdown" aria-labelledby="dropdownMenuDivider" id="menu-messages">'
                    +'<a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">'
                    +'    <i class="iconfont icon-huiyuan"></i> <span class="text2">账户设置</span> <b class="caret"></b>'
                    +'</a>'
                    +'<ul class="dropdown-menu">'
                    +'    <li><a data-toggle="modal" data-target="#account" ><i class="iconfont icon-huiyuan"></i> 个人信息</a></li>'
                    +'    <li class="divider"></li>'
                    +'    <li><a data-toggle="modal" data-target="#password"><i class="iconfont icon-xiugaimima"></i> 修改密码</a></li>'
                    +'    <li class="divider"></li>'
                    +'    <li><a data-toggle="modal" href="/user/loginOff"><i class="iconfont icon-tuichu1"></i> 注销</a></li>'
                    +'</ul>'
                    +'</li>');
			}
		}
	});
});

function getNodeList(menu) {
	var nodesList = menu.nodeList || [];
	var ul = $("<ul></ul>");
	for (var i = 0; i < nodesList.length; i++) {
		var _i = $("<i></i>").addClass("icon icon-pencil");
		var span = $("<span></span>").html(nodesList[i].afternodename);
		var href = $("<a></a>").attr("href",nodesList[i].afterurl+menu.id);
		var li = $("<li></li>");
		/** 系统管理二级菜单样式 */
		if (nodesList[i].nodename == "用户管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-user");
		} else if (nodesList[i].nodename == "角色管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-jiaoseguanli1");
		} else if (nodesList[i].nodename == "菜单管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-wenjian");
		/** 档案管理二级菜单样式 */
		} else if (nodesList[i].nodename == "组织档案") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-zuzhi");
		} else if (nodesList[i].nodename == "计量单位") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-celiang");
		} else if (nodesList[i].nodename == "货物档案") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-goods");
		} else if (nodesList[i].nodename == "我的货物") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-huowu");
		} else if (nodesList[i].nodename == "地点档案") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-dizhi");
		} else if (nodesList[i].nodename == "路线档案") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-luxian");
		} else if (nodesList[i].nodename == "运价策略") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-qianbi");
		} else if (nodesList[i].nodename == "我的会员") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-huiyuan1");
			/** 会员车辆管理二级菜单样式 */
		} else if (nodesList[i].nodename == "注册用户管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-yonghu");
		} else if (nodesList[i].nodename == "司机档案管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-siji-copy");
		} else if (nodesList[i].nodename == "车辆档案管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-trunk");
		} 
		else if (nodesList[i].nodename == "车辆运力管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-trunk");
		} 
		else if (nodesList[i].nodename == "待审车辆管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-trunk");
		} 
		else if (nodesList[i].nodename == "中交兴路车辆管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-trunk");
		} 
		else if (nodesList[i].nodename == "车主档案管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-jiaoseguanli");
			/** 计划运单管理二级菜单样式 */
		} else if (nodesList[i].nodename == "平台计划管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-shengchanjihua");
		} else if (nodesList[i].nodename == "平台运单管理") {
			_i.removeAttr("class");
			_i.addClass("iconfont icon-order");
		} else if (nodesList[i].nodename == "调价管控"){
			_i.removeAttr("class");
			_i.addClass("iconfont icon-cost");
		} else if (nodesList[i].nodename == "计划报表统计"){
			_i.removeAttr("class");
			_i.addClass("iconfont").html('&#xe63f;');
		} else if (nodesList[i].nodename == "运单报表统计"){
			_i.removeAttr("class");
			_i.addClass("iconfont").html('&#xe63e;');
		} else if (nodesList[i].nodename == "车主信用管理"){
			_i.removeAttr("class");
			_i.addClass("iconfont").html('&#xe63e;');
		} else if (nodesList[i].nodename == "车主信用月报表"){
			_i.removeAttr("class");
			_i.addClass("iconfont").html('&#xe63f;');
		} else if (nodesList[i].nodename == "车主信用评级表"){
			_i.removeAttr("class");
			_i.addClass("iconfont").html('&#xe63f;');
		} else if(nodesList[i].nodename == "统计图形管理"){
			_i.removeAttr("class");
			_i.addClass("iconfont").html('&#xe640;');
		}else if(nodesList[i].nodename == "统计路线管理"){
			_i.removeAttr("class");
			_i.addClass("iconfont").html('&#xe641;');
		}else if(nodesList[i].nodename == "客商档案"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe645;');
		}else if(nodesList[i].nodename == "开票车辆档案"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe645;');
		}else if(nodesList[i].nodename == "安联运单管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe645;');
		}else if(nodesList[i].nodename == "大易/交通部运单管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe63e;');
		}else if(nodesList[i].nodename == "安联/交通部运单管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe63f;');
		}else if(nodesList[i].nodename == "我的收货员"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe628;');
		}else if(nodesList[i].nodename == "待付运费"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe646;');
		}
		else if(nodesList[i].nodename == "提现管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe64a;');
		}
		else if(nodesList[i].nodename == "账户管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe622;');
		}else if(nodesList[i].nodename == "审核统计管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe775;');
		}
		else if(nodesList[i].nodename == "接口访问记录"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe6ff;');
		}
		else if(nodesList[i].nodename == "LED展示管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe648;');
		}
		else if(nodesList[i].nodename == "接口访问记录"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe6ff;');
		}
		else if(nodesList[i].nodename == "银行卡管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe65c;');
		}
		else if(nodesList[i].nodename == "调度管理"){
			_i.removeAttr("class");
			_i.addClass("iconfontt").html('&#xe645;');
		}
		href.append(_i).append(span);
		
		
		
		li.append(href);
		var subUrl = "";
		if (nodesList[i].afterurl != null && nodesList[i].afterurl.indexOf("?") > 0) {
			subUrl = nodesList[i].afterurl.substring(0, nodesList[i].afterurl.indexOf("?"));
		} else {
			subUrl = (nodesList[i].afterurl == null ? "" : nodesList[i].afterurl);
		}
		if (window.location.href.indexOf(subUrl) > 0) {
			li.siblings().removeAttr("class");
			li.addClass("active");
		} else {
			$("#sidebar ul li").eq(0).addClass("active");
		}
		
		ul.append(li);
	}
	$("#sidebar").empty();
	$("#sidebar").append(ul);
}
//MD5 js加密 方法，调用 hex_md5()即可进行加密
var hexcase = 0;
function hex_md5(a) {
	if (a == "")
		return a;
	return rstr2hex(rstr_md5(str2rstr_utf8(a)))
}
function hex_hmac_md5(a, b) {
	return rstr2hex(rstr_hmac_md5(str2rstr_utf8(a), str2rstr_utf8(b)))
}
function md5_vm_test() {
	return hex_md5("abc").toLowerCase() == "900150983cd24fb0d6963f7d28e17f72"
}
function rstr_md5(a) {
	return binl2rstr(binl_md5(rstr2binl(a), a.length * 8))
}
function rstr_hmac_md5(c, f) {
	var e = rstr2binl(c);
	if (e.length > 16) {
		e = binl_md5(e, c.length * 8)
	}
	var a = Array(16), d = Array(16);
	for ( var b = 0; b < 16; b++) {
		a[b] = e[b] ^ 909522486;
		d[b] = e[b] ^ 1549556828
	}
	var g = binl_md5(a.concat(rstr2binl(f)), 512 + f.length * 8);
	return binl2rstr(binl_md5(d.concat(g), 512 + 128))
}
function rstr2hex(c) {
	try {
		hexcase
	} catch (g) {
		hexcase = 0
	}
	var f = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
	var b = "";
	var a;
	for ( var d = 0; d < c.length; d++) {
		a = c.charCodeAt(d);
		b += f.charAt((a >>> 4) & 15) + f.charAt(a & 15)
	}
	return b
}
function str2rstr_utf8(c) {
	var b = "";
	var d = -1;
	var a, e;
	while (++d < c.length) {
		a = c.charCodeAt(d);
		e = d + 1 < c.length ? c.charCodeAt(d + 1) : 0;
		if (55296 <= a && a <= 56319 && 56320 <= e && e <= 57343) {
			a = 65536 + ((a & 1023) << 10) + (e & 1023);
			d++
		}
		if (a <= 127) {
			b += String.fromCharCode(a)
		} else {
			if (a <= 2047) {
				b += String
						.fromCharCode(192 | ((a >>> 6) & 31), 128 | (a & 63))
			} else {
				if (a <= 65535) {
					b += String.fromCharCode(224 | ((a >>> 12) & 15),
							128 | ((a >>> 6) & 63), 128 | (a & 63))
				} else {
					if (a <= 2097151) {
						b += String.fromCharCode(240 | ((a >>> 18) & 7),
								128 | ((a >>> 12) & 63),
								128 | ((a >>> 6) & 63), 128 | (a & 63))
					}
				}
			}
		}
	}
	return b
}
function rstr2binl(b) {
	var a = Array(b.length >> 2);
	for ( var c = 0; c < a.length; c++) {
		a[c] = 0
	}
	for ( var c = 0; c < b.length * 8; c += 8) {
		a[c >> 5] |= (b.charCodeAt(c / 8) & 255) << (c % 32)
	}
	return a
}
function binl2rstr(b) {
	var a = "";
	for ( var c = 0; c < b.length * 32; c += 8) {
		a += String.fromCharCode((b[c >> 5] >>> (c % 32)) & 255)
	}
	return a
}
function binl_md5(p, k) {
	p[k >> 5] |= 128 << ((k) % 32);
	p[(((k + 64) >>> 9) << 4) + 14] = k;
	var o = 1732584193;
	var n = -271733879;
	var m = -1732584194;
	var l = 271733878;
	for ( var g = 0; g < p.length; g += 16) {
		var j = o;
		var h = n;
		var f = m;
		var e = l;
		o = md5_ff(o, n, m, l, p[g + 0], 7, -680876936);
		l = md5_ff(l, o, n, m, p[g + 1], 12, -389564586);
		m = md5_ff(m, l, o, n, p[g + 2], 17, 606105819);
		n = md5_ff(n, m, l, o, p[g + 3], 22, -1044525330);
		o = md5_ff(o, n, m, l, p[g + 4], 7, -176418897);
		l = md5_ff(l, o, n, m, p[g + 5], 12, 1200080426);
		m = md5_ff(m, l, o, n, p[g + 6], 17, -1473231341);
		n = md5_ff(n, m, l, o, p[g + 7], 22, -45705983);
		o = md5_ff(o, n, m, l, p[g + 8], 7, 1770035416);
		l = md5_ff(l, o, n, m, p[g + 9], 12, -1958414417);
		m = md5_ff(m, l, o, n, p[g + 10], 17, -42063);
		n = md5_ff(n, m, l, o, p[g + 11], 22, -1990404162);
		o = md5_ff(o, n, m, l, p[g + 12], 7, 1804603682);
		l = md5_ff(l, o, n, m, p[g + 13], 12, -40341101);
		m = md5_ff(m, l, o, n, p[g + 14], 17, -1502002290);
		n = md5_ff(n, m, l, o, p[g + 15], 22, 1236535329);
		o = md5_gg(o, n, m, l, p[g + 1], 5, -165796510);
		l = md5_gg(l, o, n, m, p[g + 6], 9, -1069501632);
		m = md5_gg(m, l, o, n, p[g + 11], 14, 643717713);
		n = md5_gg(n, m, l, o, p[g + 0], 20, -373897302);
		o = md5_gg(o, n, m, l, p[g + 5], 5, -701558691);
		l = md5_gg(l, o, n, m, p[g + 10], 9, 38016083);
		m = md5_gg(m, l, o, n, p[g + 15], 14, -660478335);
		n = md5_gg(n, m, l, o, p[g + 4], 20, -405537848);
		o = md5_gg(o, n, m, l, p[g + 9], 5, 568446438);
		l = md5_gg(l, o, n, m, p[g + 14], 9, -1019803690);
		m = md5_gg(m, l, o, n, p[g + 3], 14, -187363961);
		n = md5_gg(n, m, l, o, p[g + 8], 20, 1163531501);
		o = md5_gg(o, n, m, l, p[g + 13], 5, -1444681467);
		l = md5_gg(l, o, n, m, p[g + 2], 9, -51403784);
		m = md5_gg(m, l, o, n, p[g + 7], 14, 1735328473);
		n = md5_gg(n, m, l, o, p[g + 12], 20, -1926607734);
		o = md5_hh(o, n, m, l, p[g + 5], 4, -378558);
		l = md5_hh(l, o, n, m, p[g + 8], 11, -2022574463);
		m = md5_hh(m, l, o, n, p[g + 11], 16, 1839030562);
		n = md5_hh(n, m, l, o, p[g + 14], 23, -35309556);
		o = md5_hh(o, n, m, l, p[g + 1], 4, -1530992060);
		l = md5_hh(l, o, n, m, p[g + 4], 11, 1272893353);
		m = md5_hh(m, l, o, n, p[g + 7], 16, -155497632);
		n = md5_hh(n, m, l, o, p[g + 10], 23, -1094730640);
		o = md5_hh(o, n, m, l, p[g + 13], 4, 681279174);
		l = md5_hh(l, o, n, m, p[g + 0], 11, -358537222);
		m = md5_hh(m, l, o, n, p[g + 3], 16, -722521979);
		n = md5_hh(n, m, l, o, p[g + 6], 23, 76029189);
		o = md5_hh(o, n, m, l, p[g + 9], 4, -640364487);
		l = md5_hh(l, o, n, m, p[g + 12], 11, -421815835);
		m = md5_hh(m, l, o, n, p[g + 15], 16, 530742520);
		n = md5_hh(n, m, l, o, p[g + 2], 23, -995338651);
		o = md5_ii(o, n, m, l, p[g + 0], 6, -198630844);
		l = md5_ii(l, o, n, m, p[g + 7], 10, 1126891415);
		m = md5_ii(m, l, o, n, p[g + 14], 15, -1416354905);
		n = md5_ii(n, m, l, o, p[g + 5], 21, -57434055);
		o = md5_ii(o, n, m, l, p[g + 12], 6, 1700485571);
		l = md5_ii(l, o, n, m, p[g + 3], 10, -1894986606);
		m = md5_ii(m, l, o, n, p[g + 10], 15, -1051523);
		n = md5_ii(n, m, l, o, p[g + 1], 21, -2054922799);
		o = md5_ii(o, n, m, l, p[g + 8], 6, 1873313359);
		l = md5_ii(l, o, n, m, p[g + 15], 10, -30611744);
		m = md5_ii(m, l, o, n, p[g + 6], 15, -1560198380);
		n = md5_ii(n, m, l, o, p[g + 13], 21, 1309151649);
		o = md5_ii(o, n, m, l, p[g + 4], 6, -145523070);
		l = md5_ii(l, o, n, m, p[g + 11], 10, -1120210379);
		m = md5_ii(m, l, o, n, p[g + 2], 15, 718787259);
		n = md5_ii(n, m, l, o, p[g + 9], 21, -343485551);
		o = safe_add(o, j);
		n = safe_add(n, h);
		m = safe_add(m, f);
		l = safe_add(l, e)
	}
	return Array(o, n, m, l)
}
function md5_cmn(h, e, d, c, g, f) {
	return safe_add(bit_rol(safe_add(safe_add(e, h), safe_add(c, f)), g), d)
}
function md5_ff(g, f, k, j, e, i, h) {
	return md5_cmn((f & k) | ((~f) & j), g, f, e, i, h)
}
function md5_gg(g, f, k, j, e, i, h) {
	return md5_cmn((f & j) | (k & (~j)), g, f, e, i, h)
}
function md5_hh(g, f, k, j, e, i, h) {
	return md5_cmn(f ^ k ^ j, g, f, e, i, h)
}
function md5_ii(g, f, k, j, e, i, h) {
	return md5_cmn(k ^ (f | (~j)), g, f, e, i, h)
}
function safe_add(a, d) {
	var c = (a & 65535) + (d & 65535);
	var b = (a >> 16) + (d >> 16) + (c >> 16);
	return (b << 16) | (c & 65535)
}
function bit_rol(a, b) {
	return (a << b) | (a >>> (32 - b))
};
function updatemyPass(){
	var reg = /^[A-Za-z0-9]+$/;
	if ($.trim($("#yuanmima").val()) == "") {
		$("#passMessage").html("原密码不能为空,请输入...");
		return;
	}
	
	if (password != hex_md5($.trim($("#yuanmima").val()))) {
		$("#passMessage").html("原密码输入错误，请重新输入...");
		document.getElementById('yuanmima').value = '';
		$("#yuanmima").focus();
		return;
	}
		if ($.trim($("#pass1").val()) == "") {
			$("#passMessage").html("密码不能为空,请输入...");
			return;
		} else if (!reg.test($("#pass1").val())) {
			$("#passMessage").html("密码只能是数字或字母,请重新输入...");
			return;
		} else if ($.trim($("#pass1").val()).length <3) {
			$("#passMessage").html("密码长度少于3位,请重新输入...");
			return;
		} else if ($.trim($("#pass1").val()).length > 12) {
			$("#passMessage").html("密码长度大于12位,请重新输入...");
			return;
		}
		if ($.trim($("#pass2").val()) == "") {
			$("#passMessage").html("确认密码不能为空,请输入...");
			return;
		} else if (!reg.test($("#pass2").val())) {
			$("#passMessage").html("确认密码只能是数字,请重新输入...");
			return;
		}
		if ($.trim($("#pass1").val()) != $.trim($("#pass2").val())) {
			$("#passMessage").html("密码和确认密码不一致，请重新输入...");
			return;
		}
	var queryCond = {
		passWord : $.trim($("#pass1").val())
	};
	$.ajax( {
		type : "get",
		url : '/user/updatemyPass',
		cache : false,
		data : queryCond,
		beforeSend : function() {
			 $("#passMessage").html("正在修改，请稍候...");
			 return true;
		},
		success : function(result) {
			if( !result || result.code !="000000" ){
				$("#passMessage").html(result.error);
			}else{
				alert("修改密码成功...");
				$("#passMessage").html("修改密码成功...");
				$("#closed").click();
			}
		}
	});
}
function updatemyUser(){
	var reg = /^1[3|4|5|7|8]\d{9}$/;
	if ($.trim($("#username").val()) == "") {
		$("#usermessage").html("姓名不能为空,请输入...");
		return;
	}
	
	if ($.trim($("#usertel").val()) == "") {
		$("#usermessage").html("手机号不能为空,请输入...");
		return;
	} else if (!reg.test($("#usertel").val())) {
		$("#usermessage").html("手机号输入不合法,请重新输入...");
		return;
	} 
	var queryCond = {
		username : $.trim($("#username").val()),
		tel : $.trim($("#usertel").val())
	};
	$.ajax( {
		type : "get",
		url : '/user/updatemyUser',
		cache : false,
		data : queryCond,
		beforeSend : function() {
			 $("#usermessage").html("正在修改，请稍候...");
			 return true;
		},
		success : function(result) {
			if( !result || result.code !="000000" ){
				$("#usermessage").html(result.error);
			}else{
				alert("修改信息成功...");
				$("#usermessage").html("修改信息成功...");
				$("#closeduser").click();
			}
		}
	});
}