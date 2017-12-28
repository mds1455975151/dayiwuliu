var iBytesUploaded = 0;
var iBytesTotal = 0;
var iPreviousBytesLoaded = 0;
var iMaxFilesize = 1048576; // 1MB
var oTimer = 0;
var sResultFileSize = '';

function uploadBase64(base64Str){
	$.ajax({
		url:"/upload/bannerBaes64Add",
		type:"POST",
		data:{imgStr:base64Str},
		success:function(ret){
			if(ret.code==000000){
//				alert(ret.data);
				$("#lianjie").html(ret.data)
			}else{
				alert("上传失败");
			}
			
		}
	});
}

function fileSelected(a, b) {
    var oFile = document.getElementById(b).files[0];
    // filter for image files
    var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
    if (!rFilter.test(oFile.type)) {
        document.getElementById('error').style.display = 'block';
        return;
    }
    // get preview element
    var oImage = document.getElementById(a);
    console.log(a);
    // prepare HTML5 FileReader
    var oReader = new FileReader();
    oReader.onload = function(e) {
        oImage.src = e.target.result; 
        uploadBase64(oImage.src);
    };

    // read selected file as DataURL
    oReader.readAsDataURL(oFile);
}
    $("#bottonUp").off('click').on('click',function(){
    	var picName=$("#nameUp").val();
    	var httpUrl=$("#lianjie").text();
    	$.ajax({
    		url:"/admin/banner/add",
    		type:"POST",
    		data:{picName:picName,httpUrl:httpUrl},
    		success:function(ret){
    			if(ret.code==000000){
    				alert("新增成功");
    			}
    		}
    	});
    })
   