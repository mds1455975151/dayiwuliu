<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>驾驶员添加</title>
	    <meta name="keywords" content=" 天瑞"/>
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link href="${trRoot}/tianrui/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${trRoot}/tianrui/js/My97DatePicker/WdatePicker.js"></script>
	    
	<!-- 引用公共header部分 -->
	<jsp:include page="../../common/member/header_busi.jsp"></jsp:include>
		 <style>
	    	.rz_box2 .reg_tel label {
			    text-align: left;
			    width: 120px;
			    font-size: 14px;
			}
	    </style>
		<!--内容部分begin-->
		<div class="bghui">
			<div class="container">
			    <!--网站位置-->
			    <div class="row">
			            <div class="rz_line">
			                <label>首页</label><span>></span> <label>账号</label><span>></span><label>个人中心</label>
			            </div>
			    </div>
			    <div class="row">
			        <!--个人中心左侧begin-->
			        <jsp:include page="../../common/member/left_busi.jsp"></jsp:include>
			        <!--个人中心左侧end-->
		            <!--个人中心右侧begin-->
		           	<form id="driver_save">
		              <div class="rz_right">
			            <div class=" bgblue">
			                <h2>添加驾驶员</h2>
			            </div>
			            <div class="rz_box2">
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>姓名</label>
			                    <input type="text" name="driverName" id="driverName_req" >
			                    <input type="hidden" name="currVId" value="${vehicleId }">
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>性别</label>
			                    <div class="geren_sex">
			                        <label class="radio-inline">
			                            <input type="radio" name="driverSex" checked="checked" value="xy" > 男
			                        </label>
			                        <label class="radio-inline">
			                            <input type="radio" name="driverSex" value="xx" > 女
			                        </label>
			                    </div>
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>出生日期</label>
			                    <input type="text" name="driverBirthDate" id="driverBirthDate_req" 
			                   		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" />
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>联系电话</label>
			                    <input type="text" name="driverLinkTel" id="driverLinkTel_req" placeholder="请输入手机号" >
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>证件号</label>
			                    <input type="text" name="driverIdCard" id="driverIdCard_req" placeholder="请输入证件号码">
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>身份证地址</label>
			                    <input type="text" name="driverIdCardAddr" id="driverIdCardAddr_req" placeholder="请输入身份证地址">
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>初次领证日期</label>
			                    <input type="text" name="driverCardFirstlicens" id="driverCardFirstlicens_req" 
			                    	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期" />
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>发证机关</label>
			                    <input type="text" name="driverCardLicenceorg" id="driverCardLicenceorg_req" placeholder="请输入驾驶证发证机关">
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>有效起始日期</label>
			                    <input type="text" name="driverCardRegDate" id="driverCardRegDate_req"
			                    	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" style="width: 160px" placeholder="请选择日期"/>
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>有效年限</label>
			                    <input type="text" name="driverCardUsefullife" id="driverCardUsefullife_req" placeholder="请输入有效年限,例如:6">
			                </div>
			                <div class="reg_tel">
			                    <label><i style="color: red">*</i>准驾车型</label>
			                    <button class="btn btn-default" data-toggle="modal"
									data-target="#car_zhunjia">请选择</button>
									<span id="drivinglicensetype"></span>
									<input type="hidden" name="driverCardType" id="driverCardType_req">
			                </div>
			               <div class="reg_tel">
								<label><i style="color: red">*</i>驾驶证:</label>
								<div class="rz_persontab">
									<div class="samples">
										<img class="jsz" src="${trRoot}/tianrui/images/jz.png">
									</div>
									<div class="img_upload">
										<input id="driverCardImg_req" onchange="fileupload('driverCardImg_req','jsz')" class="file" type="file"> <span
											class="annotation">* 图片大小不超过5M，限上传1张，只支持JPG、JPEG、PNG格式</span>
										<input type="hidden" id="driverCardImg_req_str" name="driverCardImg" value="" >
									</div>
								</div>
							</div>
			            </div>
			            <div class="tx_edit">
			                <button type="button" class="btn" id="personal_button">保存</button>
			            </div>
			
			        </div>
		            </form>
		            <!--个人中心右侧end-->
		            <!-- 准驾车型 -->
	<div class="modal fade" id="car_zhunjia" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document" style="width: 400px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                        aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">选择车型</h4>
	            </div>
	            <div class="modal-body">
	                <div class="car_altzhunjia">
	                        <table width="100%" border="0" id="licenseType">
	                            <tr>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>A1</label>
	                                </td>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>A2</label>
	                                </td>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>A3</label>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>B1</label>
	                                </td>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>B2</label>
	                                </td>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>B3</label>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>C1</label>
	                                </td>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>C2</label>
	                                </td>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>C3</label>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>C4</label>
	                                </td>
	                                <td >
	                                    <input type="radio" name="zjcx"><label>C5</label>
	                                </td>
	                                <td>
	                                    <input type="radio" name="zjcx"><label>D</label>
	                                </td>
	                            </tr>
	                        </table>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary insertType">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- 准驾车型 -->
		        </div>
		    </div>
		</div>
		<!--内容部分end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/member/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput.js"></script>
		<script type="text/javascript" src="${trRoot}/tianrui/js/fileinput_locale_zh.js"></script>
		<script type="text/javascript" src="/resources/js/common/member/header_busi.js" ></script>
		<script type="text/javascript" src="/resources/js/new/vehicle/driverAddView.js?0522" ></script>
		<script type="text/javascript">
		$('.insertType').off('click').on('click',function(){
			var text = '';
			$('#licenseType').find('input:checked').next('label').each(function(){
				text += ','+$(this).text();
			});
			$('#drivinglicensetype').html(text.substring(1,text.length));
			$("#driverCardType_req").val(text.substring(1,text.length));
			$('#car_zhunjia').modal('hide');
		});
		</script>
	</body>
</html>
