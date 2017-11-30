

	$(".bag_tab li").on("click",function(){
		var type = $(".bag_tab").find(".select").attr("type");
		if(type == 1){
			$(".bag_tj").empty();
			var dongjie1 = "<div class=" + "bag_tjline" + ">" + "<label>今日支出金额：</label>" 
			+ "<span>"+ 845712+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>今日收入金额：</label>" 
			+ "<span>"+ 845712+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>昨日可用余额：</label>" 
			+ "<span>"+ 845712+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>今日可用余额：</label>" 
			+ "<span>"+ 845712+"元</span></div>"
			$(".bag_tj").append(dongjie1);
		}
		else if(type == 2){
			$(".bag_tj").empty();
			var dongjie2 = "<div class=" + "bag_tjline" + ">" + "<label>今日解冻金额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>昨日冻结金额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>今日冻结金额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"
			$(".bag_tj").append(dongjie2);
			
		 }else if(type == 3){
				$(".bag_tj").empty();
			var dongjie3 = "<div class=" + "bag_tjline" + ">" + "<label>提现冻结：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>用户可用余额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"+
			"<div class=" + "bag_tjline" + ">" + "<label>账户金额总额：</label>" 
			+ "<span>"+ 845712.01+"元</span></div>"
			$(".bag_tj").append(dongjie3);
		 }


	})
	
