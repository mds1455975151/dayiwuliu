<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="UTF-8"%>
<div class="rz_persontab">
    <form id="NewQyq">
        <div class="SeeCont">
            <div class="SeeImg image_container">
                <img class="myimg" src='' />
                <input type="hidden" id="imgUrl" value="">
                <input type="hidden" name="id" id="uptmemberid">
				<input type="hidden" name="type" id="uptmembertype">
            </div><br>
            <button class="TxText xzBtn" id="imgReplaceBtn" type="button">点击更换图片</button>
        </div>
    </form>
</div>
<script type="text/javascript" src="${trRoot}/js/upImg.js" ></script>
<script type="text/javascript">
    //upImg();
 /**    $(function(){
      $(document).ready(function () {
          // 表格列宽度手动调整
      //    $("table").resizableColumns({});

          $(".rz_persontab").off("click").on("click",function(){
//            $('#detail').modal('hide');//第一层先隐藏
              $('#againPice').css("z-index",1);
              $('.modal-backdrop').css("z-index",0);
              $('.cropperBox').css("z-index",999);
          });
          upImg();
      });
 })
 */ 
</script>

<script type="text/javascript">
	function imgUrl(){
		 var imgStr = $(".myimg").attr("src");
			$.ajax({
				type:"post",
				url:"/upload/baes64Add",
				data:{"imgStr":imgStr },
				 async:false, 
				success: function(rs) {
					if(rs.code=="000000"){
						$("#imgUrl").val(rs.data);
					}else{
						alert(rs.error);
					}
				}
			});
			
	}
</script>
