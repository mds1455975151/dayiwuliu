<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>车辆开票认证管理</title>
    <meta name="keywords" content=" 天瑞" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot }/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot }/base.css" rel="stylesheet">
    <link href="${stylesRoot }/style.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="recPageNo" value="${pageNo }">
    <!--公共头部begin-->
    <jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
     <!--后台左侧布局end-->
                <!--后台右侧布局begin-->
            <div class="col-md-10 ">
               <div class="ht_content">
                    <div class="user_shtitle mt10">
                        <h3>基本信息</h3>
                    </div>
                    <!--基本信息begin-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="info_base">
                                <div class="info_base_solo">
                                    <label>门禁单号：</label>
                                    <input type="text" value="00">
                                </div>
                                <div class="info_base_solo">
                                    <label>提货单号：</label>
                                    <input type="text">
                                </div>
                                <div class="info_base_solo">
                                    <label>门禁时间：</label>
                                    <input type="text">
                                </div>
                                <div class="info_base_solo">
                                    <label>车号：</label>
                                    <input type="text">
                                </div>
                                <div class="info_base_solo">
                                    <label>物料：</label>
                                    <input type="text">
                                </div>
                                <div class="info_base_solo">
                                    <label>客户：</label>
                                    <input type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="user_shtitle mt20">
                        <h3>认证文件</h3>
                    </div>
                    <!--认证begin-->
                    <div class="info_rz">
                        <div class="info_rzsolo">
                            <p>行驶证照片：</p>
                            <span><img src="${trRoot}/images/sfz.png"></span>
                        </div>
                        <div class="info_rzsolo">
                            <p>车辆照片：</p>
                            <span><img src="${trRoot}/images/sfz.png"></span>
                        </div>
                        <div class="info_rzsolo">
                            <p>车辆照片：</p>
                            <span><img src="${trRoot}/images/sfz.png"></span>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col-md-12">
                            <div class="info_btn">
                                    <button class="btn btn-success btn-sm" type="submit">保存</button>
                                    <button class="btn btn-danger btn-sm" type="submit">返回</button>
                            </div>
                        </div>
                    </div>
                    <div class="user_shtitle mt20">
                        <h3>修改记录</h3>
                    </div>
                    <div class="track-rcol">
                        <div class="track-list">
                            <ul>
                                <li>
                                    <i class="node-icon"></i>
                                    <span class="time">2016-03-10 18:07:15</span>
                                    <span class="txt">修改人：小花儿</span>
                                    <span class="zhedie"><img src="${trRoot}/images/infor.png"></span>
                                    <div class="info_econt" style="display:none">
                                        <div class="info_eleft">
                                            <h3>原有资料</h3>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>账号：</label>
                                                <span>4512578时88</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <p>驾驶证照片：</p>
                                                <div>
                                                    <img src="${trRoot}/images/sfz.png">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="info_eleft">
                                            <h3>修改后资料</h3>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>账号：</label>
                                                <span>4512578时88</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <i class="node-icon"></i>
                                    <span class="time">2016-03-10 18:07:15</span>
                                    <span class="txt">修改人：小花儿</span>
                                    <span class="zhedie"><img src="${trRoot}/images/infor.png"></span>
                                    <div class="info_econt" style="display:none">
                                        <div class="info_eleft">
                                            <h3>原有资料</h3>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>账号：</label>
                                                <span>4512578时88</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <p>驾驶证照片：</p>
                                                <div>
                                                    <img src="${trRoot}/images/sfz.png">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="info_eleft">
                                            <h3>修改后资料</h3>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>账号：</label>
                                                <span>4512578时88</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <i class="node-icon"></i>
                                    <span class="time">2016-03-10 18:07:15</span>
                                    <span class="txt">感谢您在京东购物，欢迎您再次光临！</span>
                                    <span class="zhedie"><img src="${trRoot}/images/infor.png"></span>
                                    <div class="info_econt" style="display:none">
                                        <div class="info_eleft">
                                            <h3>原有资料</h3>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>账号：</label>
                                                <span>4512578时88</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <p>驾驶证照片：</p>
                                                <div>
                                                    <img src="${trRoot}/images/sfz.png">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="info_eleft">
                                            <h3>修改后资料</h3>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>账号：</label>
                                                <span>4512578时88</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                            <div class="info_esolo">
                                                <label>原来司机账号：</label>
                                                <span>4512578时间的开关即可</span>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
                <!--后台右侧布局end-->
            </div>
            <!--后台整体布局end-->
    </div>
    <!--侧边栏end-->
</div>
<%@include file="../../common/footer.jsp" %>
<script type="text/javascript">
    $(function () {
        $(".zhedie").on('click',function(){
            var div_hs = $(this).next();
            div_hs.toggle();
            if(div_hs.css("display") == 'none') {
            	$(this).children("img").attr('src',"${trRoot}/images/infor.png");
            }else {
            	$(this).children("img").attr('src',"${trRoot}/images/infol.png");
            }
        });
    });
</script>
</body>
</html>