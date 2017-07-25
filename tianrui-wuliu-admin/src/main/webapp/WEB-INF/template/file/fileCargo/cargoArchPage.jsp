<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tianrui.service.admin.bean.Users" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Users user = (Users) request.getSession().getAttribute("session_user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>天瑞物流平台-货物档案</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${stylesRoot}/bootstrap.css" rel="stylesheet">
    <link href="${stylesRoot}/base.css" rel="stylesheet">
    <link href="${stylesRoot}/style.css" rel="stylesheet">
    <link href="${stylesRoot}/tr-media.css" rel="stylesheet">
    <link href="${stylesRoot}/easyTree.css" rel="stylesheet">
    <link href="${stylesRoot}/jquery-ui.min.css" rel="stylesheet">
    <link href="${stylesRoot}/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="${stylesRoot}/imgcut.css" rel="stylesheet">
    <link rel="Shortcut Icon" href="${imagesRoot}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${stylesRoot }/pagination/pagination.css" />
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div class="container-fluid">
		<!--公共头部begin-->
		<jsp:include page="../../common/header.jsp" flush="false"></jsp:include>
		<!--公共头部end-->
		    <!--后台右侧布局begin-->
		    <div class="col-md-10 ">
		        <div class="ht_content">
		            <div id="content-header">
		                <h3>货物档案</h3>
		            </div>
		            <!--查询框begin-->
		            <div class="row">
		                <div class="col-md-12">
		                    <div class="contuser_search">
		                    	 <div class="ht_div">
		                            <label>物料名称：</label>
		                            <input type="text" placeholder=" " maxlength="16" id="cargo_materName">
		                        </div>
		                        <div class="ht_div">
		                            <label>物料编码：</label>
		                            <input type="text" placeholder=" " maxlength="16" id="cargo_materCode">
		                        </div>
		                        <div class="ht_div">
		                            <label>物料助记码：</label>
		                            <input type="text" placeholder=" " maxlength="5" id="cargo_materMNCode">
		                        </div>
		                        <div class="ht_div">
		                            <label>物料类别：</label>
		                            <select class="form-control" id="cargo_materClass">
		                                <option value="" >全部</option>
		                                <option>水泥熟料</option>
		                                <option>煤炭及产品</option>
		                                <option>普通硅酸盐水泥</option>
		                                <option>矿渣硅酸盐水泥</option>
		                                <option>粉煤灰硅酸盐水泥</option>
		                                <option>火山灰质硅酸盐水泥</option>
		                                <option>特种水泥</option>
		                                <option>复合硅酸盐水泥</option>
										<option>金属矿产及冶炼制成品</option>
										<option>黑色金属冶炼产品</option>
										<option>非金属矿产类</option>
										<option>石膏、石膏粉</option>
										<option>水泥助磨剂</option>
		                            </select>
		                        </div>
		                        <div class="ht_divbtn">
			                        <button class="btn btnblue" type="submit" id="cargo_search">搜索</button>
			                        <button class="btn btngreen" type="reset" id="cargo_reset">重置</button>
		                        </div>
		                    </div>
		                    <!--  
		                    <div class="contuser_search">
		                    	<div class="ht_div">
		                            <label>货物规格：</label>
		                            <input type="text" placeholder=" " maxlength="16" id="cargo_spec">
		                        </div>
		                        <div class="ht_div">
		                            <label>货物型号：</label>
		                            <input type="text" placeholder=" " maxlength="16" id="cargo_model">
		                        </div>
		                        <div class="ht_div">
		                            <label>计量单位：</label>
		                            <select class="form-control" id="cargo_mainMeasUnit">
		                                <option value="" >请选择</option>
		                                <c:forEach items="${measureBase }" var="aa">
				                        	<option value="${aa.measureName }">${aa.measureName }</option>
			                        	</c:forEach>
		                            </select>
		                        </div>
		                         <div class="ht_div">
		                            <label>组织名称：</label>
		                            <input type="text" placeholder=" " maxlength="20" id="cargo_orgName">
		                            <input type="hidden" id="cargo_orgId">
		                        </div>
		                        -->
		                </div>
		            </div>
		            <!--查询框end-->
		            <div class="row mt15">
		                <div class="col-md-12">
		                    <div class="content-user">
		                        <div class="content-tou">
		                            <button data-toggle="modal" data-target="#addModal"><i
		                                    class="glyphicon glyphicon-plus" id="addButton"></i><span>新增</span></button>
		                            <button id="batchDisable"><i class="iconfont icon-tingyong"></i><span>批量停用</span></button>
		                           <!-- 
		                            <a href=" ">
		                                <button id="importButton"><i class="iconfont icon-daoru06"></i>导入</button>
		                            </a>
		                            <button id="exportButton"><i class="iconfont icon-daochu1"></i><span>导出</span></button>
		                        	 -->
		                        </div>
		                        <!--用户表格begin-->
		                        <table class="table table-bordered">
		                            <thead>
		                            <tr><th data-options="field:'ck',checkbox:true"><input type="checkbox" onclick="selectAll()" name="allUser"></th>
		                                <th>物料编码</th>
		                                <th>物料名称</th>
		                                <th>计量单位</th>
		                                <th>物料类别</th>
		                                <th>状态</th>
		                                <th>发票类型</th>
		                                <th>规格</th>
		                                <th>型号</th>
		                                <th>物料助记码</th>
		                                <th>操作</th>
		                            </tr>
		                            </thead>
		                            <tbody id="cargo_tbody">
		                            
		                            	<!-- TO GET DATA! -->
		                            
		                            </tbody>
		                        </table>
		                        <!--用户表格end-->
		                    </div>
		                </div>
		            </div>
		             <!-- 分页部分  开始-->
			            <div class="row pr20 fr">
							<%@include file="../../common/pagination.jsp" %>
			            </div>
			         <!-- 分页部分 结束 -->
		        </div>
		    </div>
		    <!--后台右侧布局end-->
		</div>
		<!--后台整体布局end-->
		</div>
		<!--侧边栏end-->
	
	</div>
	<!--新增货物begin-->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id=" ">物料档案新增</h4>
	            </div>
	            <div class="modal-body" style="height: 460px;">
	                <div class="usermodal userlabel3">
		                <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>物料编码：</label><input type="text" maxlength="16"  id="modal_add_materCode">
		                </div>
		                <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>物料名称：</label><input type="text" maxlength="16" id="modal_add_materName">
		                </div>
		                <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>物料类别：</label>
		                    <select class="form-control" id="modal_add_materClass">
		                        <option>请选择</option>
		                                <option>水泥熟料</option>
		                                <option>煤炭及产品</option>
		                                <option>普通硅酸盐水泥</option>
		                                <option>矿渣硅酸盐水泥</option>
		                                <option>粉煤灰硅酸盐水泥</option>
		                                <option>火山灰质硅酸盐水泥</option>
		                                <option>特种水泥</option>
		                                <option>复合硅酸盐水泥</option>
										<option>金属矿产及冶炼制成品</option>
										<option>黑色金属冶炼产品</option>
										<option>非金属矿产类</option>
										<option>石膏、石膏粉</option>
										<option>水泥助磨剂</option>
		                    </select>
		                </div>
		                 <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>计量单位：</label>
		                    <select class="form-control" id="modal_add_mainMeasUnit">
		                    <!-- <option>请选择</option> -->
		                         <c:forEach items="${measureBase }" var="aa">
				                      <option value="${aa.measureName }">${aa.measureName }</option>
			                      </c:forEach>
		                    </select>
		                </div>
		                <div class="model_width">
		                    <label>货物型号：</label><input type="text" maxlength="16" id="modal_add_model">
		                </div>
		                <div class="model_width">
		                    <label>货物规格：</label><input type="text" maxlength="16" id="modal_add_spec">
		                </div>
		               <div class="model_width">
		                    <label>物料助记码：</label><input type="text" maxlength="5" id="modal_add_materMNCode">
		                </div>
		              	<!-- 
		               <div class="model_width">
		                    <label>支付类型：</label>
							<select class="form-control" id="modal_add_payType">
								<option value="0">在线支付</option>
								<option value="1">发票单支付</option>
							</select>
		                </div>
		              	 -->
		               <div class="model_width">
		                    <label>发票类型：</label>
							<select class="form-control" id="modal_add_desc1">
								<option value="540100000003">熟料运输费用</option>
								<option value="540100000016">原煤运输费用</option>
								<option value="540100000018">水泥运输费用</option>
							</select>
		                </div>
		                <div class="model_width">
		                    <label>货物图片：</label>
		                    <span><button class="btn byellow tx_contr" id="modal_add_imgButton">货物图片</button></span>
		                </div>
		            </div>
	                <div class="clear"></div>
	                <div class="file_note">提示：图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</div>
	                <div class=" ">
	                    <div class="user_oldtx"  id="modal_add_img">
	                        <img id="add_cargoImg" src="${imagesRoot}/mei.jpg" class="img-rounded">
	                    </div>
	                    <!-- 以下隐藏项为图片回退用  -->
	                    <input type="hidden" id="add_cargoImgBack_1">
	                    <input type="hidden" id="add_cargoImgBack_2">
	                    <input type="hidden" id="add_cargoImgBack_3">
	                    <!-- 以下隐藏项为判断图片是否更换用  -->
	                    <input type="hidden" id="add_imgCompare_back">
	                    <!--物料档案图片上传begin-->
	                    <div class="acc_touxiang" id="modal_add_imgBox">
	                        <!--图片裁剪框-->
	                        <div class="imageBox imgBox_file" id="modal_add_imgBoxFile">
	                            <div class="thumbBox" id="modal_add_thumbBox"></div>
	                            <div class="spinner" style="display: none" id="modal_add_spinner">Loading...</div>
	                        </div>
	                        <!--图片裁剪框end-->
	                        <!--操作按钮begin-->
	                        <div class="action wfile">
	                            <!-- <input type="file" id="file" style=" width: 200px">-->
	                            <div class="new-contentarea tc">
	                                <a href="javascript:void(0)" class="upload-img">
	                                    <label for="mondal_add_uploadFile" style="text-align: center;">选择图片</label>
	                                </a>
	                                <input type="file" class="" name="upload-file" id="mondal_add_uploadFile"/>
	                            </div>
	                            <button id="modal_add_btncancel" class="Btnsty_peyton peytonbg1 tx_cancel">
	                                <i class="iconfont icon-huitui"></i></button>
	                            <button id="modal_add_btnCrop" class="Btnsty_peyton peytonbg1"> 裁切</button>
	                            <button id="modal_add_btnZoomIn" class="Btnsty_peyton peytonbg1"><i class="iconfont icon-bf-add"></i>
	                            </button>
	                            <button id="modal_add_btnZoomOut" class="Btnsty_peyton peytonbg1">
	                                <i class="iconfont icon-jianhao"></i></button>
	                        </div>
	                        <!--操作按钮end-->
	                        <div class="tx_shouqi" id="modal_add_packUp">
	                            <img src="${imagesRoot}/jtup.png">
	                            <h4>收起</h4>
	                        </div>
	                    </div>
	                    <!--物料档案图片上传end-->
	                </div>
	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="modal_add_save">保存</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--新增货物end-->
	
	<!--修改货物begin-->
	<div class="modal fade" id="edit_mate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id=" ">物料档案修改</h4>
	                <input type="hidden" id="modal_edit_id">
	            </div>
	            <div class="modal-body" style="height: 460px;">
	                <div class="usermodal userlabel3">
		                <div class="model_width">
		                    <label>
		                    <i style="color: #ff2f00;">*</i>物料编码：
		                    </label>
		                    <input type="text" readonly="readonly" maxlength="16" id="modal_edit_materCode">
		                </div>
		                <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>物料名称：
		                    </label>
		                    <input type="text" readonly="readonly" maxlength="16" id="modal_edit_materName">
		                </div>
		                <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>物料类别：</label>
		                    <select class="form-control" disabled id="modal_edit_materClass">
		                       <option>请选择</option>
		                                <option>水泥熟料</option>
		                                <option>煤炭及产品</option>
		                                <option>普通硅酸盐水泥</option>
		                                <option>矿渣硅酸盐水泥</option>
		                                <option>粉煤灰硅酸盐水泥</option>
		                                <option>火山灰质硅酸盐水泥</option>
		                                <option>特种水泥</option>
		                                <option>复合硅酸盐水泥</option>
										<option>金属矿产及冶炼制成品</option>
										<option>黑色金属冶炼产品</option>
										<option>非金属矿产类</option>
										<option>石膏、石膏粉</option>
										<option>水泥助磨剂</option>
		                    </select>
		                </div>
		                <div class="model_width">
		                    <label><i style="color: #ff2f00;">*</i>计量单位：</label>
		                    <select class="form-control" disabled id="modal_edit_mainMeasUnit">
		                        <option>请选择</option>
		                        <c:forEach items="${measureBase }" var="aa">
				                      <option value="${aa.measureName }">${aa.measureName }</option>
			                      </c:forEach>
		                    </select>
		                </div>
		                <div class="model_width">
		                    <label>货物规格：</label><input type="text" readonly="readonly" maxlength="16" id="modal_edit_spec">
		                </div>
		                <div class="model_width">
		                    <label>货物型号：</label><input type="text" readonly="readonly" maxlength="16" id="modal_edit_model">
		                </div>
		                 <div class="model_width">
		                    <label>物料助记码：</label><input type="text" readonly="readonly" maxlength="5" id="modal_edit_materMNCode">
		                </div>
		               	<!-- 
		                <div class="model_width">
		                    <label>支付类型：</label>
							<select class="form-control" id="modal_edit_payType">
								<option value="0">在线支付</option>
								<option value="1">发票单支付</option>
							</select>
		                </div>
		               	 -->
		                 <div class="model_width">
		                    <label>发票类型：</label>
							<select class="form-control" id="modal_edit_desc1">
								<option value="540100000003">熟料运输费用</option>
								<option value="540100000016">原煤运输费用</option>
								<option value="540100000018">水泥运输费用</option>
							</select>
		                </div>
		                <div class="model_width">
		                    <label>货物图片：</label>
		                    <span><button class="btn byellow tx_contr" id="modal_edit_imgButton">更换货物图片</button></span>
		                </div>
		            </div>
	                <div class="clear"></div>
	                <div class="file_note">提示：图片大小不超过3M，限上传1张，只支持JPG、JPEG、PNG格式</div>
	                <div class=" ">
	                    <div class="user_oldtx" id="modal_edit_img">
	                        <img id="edit_cargoImg" src="${imagesRoot}/mei.jpg" class="img-rounded">
	                    </div>
	                    <!-- 以下隐藏项为图片回退用 -->
	                    <input type="hidden" id="edit_cargoImgBack_1">
	                    <input type="hidden" id="edit_cargoImgBack_2">
	                    <input type="hidden" id="edit_cargoImgBack_3">
	                    <!-- 以下隐藏项为判断图片是否更换用  -->
	                    <input type="hidden" id="edit_imgCompare_back">
	                    
	                    <!--物料档案图片上传begin-->
	                    <div class="acc_touxiang" style="display:block;" id="modal_edit_imgBox">
	                        <!--图片裁剪框-->
	                        <div class="imageBox imgBox_file" id="modal_edit_imgBoxFile">
	                            <div class="thumbBox" id="modal_edit_thumbBox"></div>
	                            <div class="spinner" style="display: none" id="modal_edit_spinner">Loading...</div>
	                        </div>
	                        <!--图片裁剪框end-->
	                        <!--操作按钮begin-->
	                        <div class="action wfile">
	                            <!-- <input type="file" id="file" style=" width: 200px">-->
	                            <div class="new-contentarea tc">
	                                <a href="javascript:void(0)" class="upload-img">
	                                    <label for="modal_edit_uploadFile" style="text-align: center;">选择图片</label>
	                                </a>
	                                <input type="file" class="" name="upload-file" id="modal_edit_uploadFile"/>
	                            </div>
	                            <button id="modal_edit_btncancel" class="Btnsty_peyton peytonbg1 tx_cancel">
	                                <i class="iconfont icon-huitui"></i></button>
	                            <button id="modal_edit_btnCrop" class="Btnsty_peyton peytonbg1"> 裁切</button>
	                            <button id="modal_edit_btnZoomIn" class="Btnsty_peyton peytonbg1"><i class="iconfont icon-bf-add"></i>
	                            </button>
	                            <button id="modal_edit_btnZoomOut" class="Btnsty_peyton peytonbg1">
	                                <i class="iconfont icon-jianhao"></i></button>
	                        </div>
	                        <!--操作按钮end-->
	                        <div class="tx_shouqi" id="modal_edit_packUp">
	                            <img src="${imagesRoot}/jtup.png">
	                            <h4>收起</h4>
	                        </div>
	                    </div>
	                    <!--物料档案图片上传end-->
	                </div>
	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="modal_edit_save">保存</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--修改货物end-->
	
	<!--查看详情begin-->
	<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id=" ">货物档案详情</h4>
	            </div>
	            <div class="modal-body" style="height: 350px;">
	                <!-- <div class="file_detail">
	                    <label>组织名称：</label><span id="modal_detail_orgName">山水公司水泥厂</span>
	                </div>
	                <div class="file_detail">
	                    <label>组织类型：</label><span id="modal_detail_orgType">组织的类型</span>
	                </div>
	                <div class="file_detail">
	                    <label>组织编码：</label><span id="modal_detail_orgCode">shhnkdjl</span>
	                </div> -->
	                <div class="file_detail">
	                    <label>物料编码：</label><span id="modal_detail_materCode">4564212315641212</span>
	                </div>
	                <div class="file_detail">
	                    <label>物料名称：</label><span id="modal_detail_materName">钢铁呵呵呵呵</span>
	                </div>
	                <div class="file_detail">
	                    <label>物料类别：</label><span id="modal_detail_materClass">钢铁</span>
	                </div>
	                <div class="file_detail">
	                    <label>状态：</label><span id="modal_detail_state">可用</span>
	                </div>
	                <div class="file_detail">
	                    <label>货物规格：</label><span id="modal_detail_spec">shhnkdjl</span>
	                </div>
	                <div class="file_detail">
	                    <label>货物型号：</label><span id="modal_detail_model">452121</span>
	                </div>
	                <div class="file_detail">
	                    <label>物料助记码：</label><span id="modal_detail_materMNCode">shhnkdjl</span>
	                </div>
	                <div class="file_detail">
	                    <label>计量单位：</label><span id="modal_detail_mainMeasUnit">吨</span>
	                </div>
	                <br/>
	                <div class="file_detail">
	                    <label>货物图片：</label><span><img id="modal_detail_img"  ></span>
	                </div>
	                <!-- 
	                <div class="file_detail">
	                    <label>支付类型：</label><span id="modal_detail_payType"></span>
	                </div>
	                 -->
	                <div class="file_detail">
	                    <label>发票类型：</label><span id="modal_detail_desc2"></span>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--查看详情end-->
	
	<!--停用begin-->
	<div class="modal fade" id="enDisableModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">提示</h4>
	                <input type="hidden" id="modal_endisable_id">
	                <input type="hidden" id="modal_endisable_state">
	                <input type="hidden" id="modal_endisable_rowIndex">
	            </div>
	            <div class="modal-body">
	                <h4 id="modal_endisable_content">确定要停用吗</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="modal_endisable_button">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--停用end-->
	<!--启用begin-->
	<div class="modal fade" id="qiyong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">提示</h4>
	            </div>
	            <div class="modal-body">
	                <h4>确定要启用吗</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--启用end-->
	<!--删除begin-->
	<div class="modal fade" id="dele_mate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">货物档案信息删除</h4>
	                <input type="hidden" id="modal_del_id">
	                <input type="hidden" id="modal_del_rowIndex">
	            </div>
	            <div class="modal-body">
	                <h4>确定要删除此条信息吗?</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" id="modal_del_button">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!--删除end-->
	<%@include file="../../common/footer.jsp" %>
	<script type="text/javascript">
	    var CONTEXTPATH="${contextPath}";
	    var imagesRoot = "${imagesRoot}";
	    <%-- 用户名称 --%>
	    var userName = "<%= user.getUsertype() %>";
	    <%-- 组织ID --%>
	    var orgId = "<%= user.getDesc1() %>";
	    <%-- 组织名称 --%>
	    var orgName = "<%= user.getDesc2() %>";
	    var imagesRoot="${imagesRoot }";
	</script>
	<script type="text/javascript" src="/resources/js/fileCargo/cargoArchPage.js?07.23"></script>
	<script type="text/javascript" src="${scriptsRoot }/jquery.pagination.js"></script>
	<script type="text/javascript" src="${scriptsRoot }/pagination.js"></script>
</body>
</html>
