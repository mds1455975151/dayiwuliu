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
<input type="file" name="file" value="选择文件">
<input type="submit" value="提交">
</form>
</body>
<script type="text/javascript" src="${trRoot}/tianrui/js/jquery-1.11.1.js"></script>

<script type="text/javascript" src="${scriptsRoot}/jquery-ui.min.js"></script>
<script type="text/javascript">
function index(){
	$.ajax({
		type:"post",
		url:"add",
		data:$('#file').serialize(),// 你的formid
		success: function(rs) {
			$(this).attr("disabled",false);
			if(rs.code=="000000"){
				
			}else{
				alert(rs.error);
			}
		}
	});
}
</script>
</html>