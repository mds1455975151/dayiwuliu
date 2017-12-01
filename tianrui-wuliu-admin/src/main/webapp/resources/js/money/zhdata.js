	
function bagHml(data){
	var type = $(".bag_tab").find(".select").attr("type");
		if(type == 1){
//			for (var a = 0; a < data.length; a++) {
			$(".bag_tj").empty();
			var dongjie1 = "<div class=" + "bag_tjline" + ">" + "<label>今日支出金额：</label>" 
			+ "<span>"+ "44"+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>今日收入金额：</label>" 
			+ "<span>"+ 845712+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>昨日可用余额：</label>" 
			+ "<span>"+ 845712+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>今日可用余额：</label>" 
			+ "<span>"+ 845712+"元</span></div>"
			$(".bag_tj").append(dongjie1);
//			}
		}
		else if(type == 2){
//			for (var a = 0; a < data.length; a++) {
			$(".bag_tj").empty();
			var dongjie2 = "<div class=" + "bag_tjline" + ">" + "<label>今日解冻金额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>昨日冻结金额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>今日冻结金额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"
			$(".bag_tj").append(dongjie2);
//			}
			
		 }else if(type == 3){
//			for (var a = 0; a < data.length; a++) {
			$(".bag_tj").empty();
			var dongjie3 = "<div class=" + "bag_tjline" + ">" + "<label>提现冻结：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>用户可用余额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>账户金额总额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"
			$(".bag_tj").append(dongjie3);
//				}
		 }
}
function getParams(){
	var type = $(".bag_tab").find(".select").attr("type");
	var params = {
			transactiontype:type,
			}
	return params;
}
function tjtoal(){
	$.ajax({
		url:"/admin/money/capitalRecordSelect",
		type:"POST",
		data:getParams(),
		success:function(ret){
			if(ret.code == '000000'){
				var data = ret.data.list;
				bagHml(data);
			}
		}
	});
}






	
