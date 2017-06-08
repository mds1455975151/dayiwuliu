<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form id="file" action="add" method="post" enctype="multipart/form-data">
文件：
<input id="file_djz" type="file" name="file" value="选择文件">
<input type="submit" value="提交">
</form>

<input type="button" onclick="djzfile()" value="异步提交">
</body>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>

<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
<script type="text/javascript">

function djzfile(){
	//机动车登记证图片
	var file_djz = $('#file_djz')[0].files[0];
	
	var formData = new FormData();
	formData.append("file",file_djz);
	$.ajax({
		url : '/upload/add',// 跳转到 action
		data : formData,
		type : "post",
		processData : false,//告诉jQuery不要去处理发送的数据
		contentType : false,//告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
	        //请求前的处理
		},
		success : function(result) {
		
		}
	});
}
</script>
</html>