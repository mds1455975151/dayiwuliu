var PAGE;
if(PAGE != null){
	PAGE = null;
}
PAGE = {
		mod:{
			main:null
		},
		common:null
}

PAGE.mod.main = {
		init:function(){
			var _this = this;
			_this.bindEvent();
			$('.searchBtn').trigger('click');
		},
		bindEvent:function(){
			//搜索
			var _this = this;
			$('.searchBtn').off('click').on('click',function(){
				$('.pageMore').attr('pageno',1);
				$('#report>tbody').empty();
				_this.queryDate(1);
			});
			
			//重置搜索条件
			$('.resetBtn').off('click').on('click',function(){
				$('#starttime').val('');
				$('#endtime').val('');
				$('#waybillno').val('');
				$('#routename').val('');
				$('#cargoname').val('');
				$('#billStatus').val('');
				$('#drivername').val('');
				$('#planCode').val('');
				$('#vehicleno').val('');
				$('#isAppoint').val('');
			});
			
			//分页更多
			$('.pageMore').off('click').on('click',function(){
				var pageNo = $('.pageMore').attr('pageno');
				pageNo = $.trim(pageNo);
				$('.pageMore').attr('pageno',++pageNo);
				_this.queryDate();
			});
		},
		getParams:function(){
			var starttime = $('#starttime').val();
			starttime = $.trim(starttime);
			var endtime = $('#endtime').val();
			endtime = $.trim(endtime);
			var waybillno = $('#waybillno').val();
			waybillno = $.trim(waybillno);
			var routename = $('#routename').val();
			routename = $.trim(routename);
			var cargoname = $('#cargoname').val();
			cargoname = $.trim(cargoname);
			var billStatus = $('#billStatus').val();
			billStatus = $.trim(billStatus);
			var drivername = $('#drivername').val();
			drivername = $.trim(drivername);
			var planCode = $('#planCode').val();
			planCode = $.trim(planCode);
			var vehicleno = $('#vehicleno').val();
			vehicleno = $.trim(vehicleno);
			var isAppoint = $('#isAppoint').val();
			isAppoint = $.trim(isAppoint);
			var pageNo = $('.pageMore').attr('pageno');
			pageNo = $.trim(pageNo);
			return {
				starttimeStr: starttime,
				endtimeStr: endtime,
				waybillno: waybillno,
				routename: routename,
				cargoname: cargoname,
				billStatus: billStatus,
				drivername: drivername,
				planCode: planCode,
				vehicleno: vehicleno,
				isAppoint: isAppoint,
				pageNo: pageNo,
				pageSize: 10
			}
		},
		queryDate:function(){
			var _this = this;
			var params = _this.getParams();
			console.log(params);
			$.ajax({
				url:'/trwuliu/venderreport/queryReport',
				data:params,
				type:'POST',
				dateType:'json',
				async:true,
				cache:false,
				success:function(result){
					if(result && result.code == '000000'){
						if(result.data && result.data.list){
							$('.nodata').hide();
							$('#report').show();
							_this.renderHtml(result.data.list);
						}else{
							$('.nodata').show();
							$('#report').hide();
							$('.pageMore').hide();
						}
					}else{
						alert(result.error);
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("请求失败，请稍后重试！");
					console.log(errorThrown);
				}
			});
		},
		renderHtml:function(list){
			if(list.length < 10){
				$('.pageMore').hide();
			}else{
				$('.pageMore').show();
			}
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var id = "";
				if(obj.id){
					id = obj.id;
				}
				var billcreatetimeStr = "";
				if(obj.billcreatetimeStr){
					billcreatetimeStr = obj.billcreatetimeStr;
				}
				var waybillno = "";
				if(obj.waybillno){
					waybillno = obj.waybillno;
				}
				var cargoname = "";
				if(obj.cargoname){
					cargoname = obj.cargoname;
				}
				var routename = "";
				if(obj.routename){
					routename = obj.routename;
				}
				var weight = "";
				if(obj.weight){
					weight = obj.weight.toFixed(2);
				}
				var trueweight = "";
				if(obj.trueweight){
					trueweight = obj.trueweight.toFixed(2);
				}
				var billStatus = '';
				if(obj.billStatus == 6){
					switch (obj.payStatus) {
					case '2':
						billStatus = '待付款';
						break;
					case '3':
						billStatus = '支付完成';
						break;
					default:
						billStatus = '待运费确认';
						break;
					}
				}else{
					switch (obj.billStatus) {
					case '1':
						billStatus = '待提货';
						break;
					case '2':
						billStatus = '运输中';
						break;
					case '5':
						billStatus = '待签收';
						break;
					default:
						break;
					}
				}
				var $tr = $('<tr billid="'+id+'"><td>'+billcreatetimeStr+'</td><td>'+waybillno+'</td><td>'+cargoname+'</td><td>'+routename+'</td><td>'+weight+'</td><td>'+trueweight+'</td><td>'+billStatus+'</td></tr>');
				$('#report>tbody').append($tr);
			}
			$('#report>tbody>tr').off('dblclick').on('dblclick',function(){
				var id = $(this).attr('billid');
				window.location.href = '/trwuliu/venderreport/detail?id='+id;
			});
		}
}