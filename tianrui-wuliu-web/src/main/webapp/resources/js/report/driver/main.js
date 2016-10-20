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

PAGE.common = {
		dateUtils:{
			dateStr2Time:function(dateStr){
				var date;
				if(dateStr){
					var dateArr = dateStr.split('-');
					date = new Date(dateArr[0], (dateArr[1]-1), dateArr[2], 0, 0, 0, 0);
					return date.getTime();
				}
				return null;
			},
			dateTime2Str:function(time){
				var date;
				if(time){
					date = new Date(time);
					var y = date.getFullYear();
					var mo = date.getMonth();
					mo += 1;
					mo = mo<10?'0'+mo:mo;
					var d = date.getDate();
					d = d<10?'0'+d:d;
					var h = date.getHours();
					h = h<10?'0'+h:h;
					var mi = date.getMinutes();
					mi = mi<10?'0'+mi:mi;
					var s = date.getSeconds();
					s = s<10?'0'+s:s;
					return y+'-'+mo+'-'+d+' '+h+':'+mi+':'+s;
				}
				return '';
			}
		}
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
			var dateutils =  PAGE.common.dateUtils;
			var starttime = $('#starttime').val();
			starttime = dateutils.dateStr2Time($.trim(starttime));
			var endtime = $('#endtime').val();
			endtime = dateutils.dateStr2Time($.trim(endtime));
			var waybillno = $('#waybillno').val();
			waybillno = $.trim(waybillno);
			var routename = $('#routename').val();
			routename = $.trim(routename);
			var cargoname = $('#cargoname').val();
			cargoname = $.trim(cargoname);
			var billStatus = $('#billStatus').val();
			billStatus = $.trim(billStatus);
			var pageNo = $('.pageMore').attr('pageno');
			pageNo = $.trim(pageNo);
			return {
				starttime: starttime,
				endtime: endtime,
				waybillno: waybillno,
				routename: routename,
				cargoname: cargoname,
				billStatus: billStatus,
				pageNo: pageNo,
				pageSize: 10
			}
		},
		queryDate:function(){
			var _this = this;
			var params = _this.getParams();
			console.log(params);
			$.ajax({
				url:'/trwuliu/driverreport/queryReport',
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
			var dateutils =  PAGE.common.dateUtils;
			for(var i=0;i<list.length;i++){
				var obj = list[i];
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
				case '6':
					billStatus = '已完成';
					break;
				default:
					break;
				}
				var $tr = $('<tr billid="'+obj.id+'"><td>'+dateutils.dateTime2Str(obj.billcreatetime)+'</td><td>'+waybillno+'</td><td>'+cargoname+'</td><td>'+routename+'</td><td>'+weight+'</td><td>'+trueweight+'</td><td>'+billStatus+'</td></tr>');
				$('#report>tbody').append($tr);
			}
			$('#report>tbody>tr').off('click').on('click',function(){
				var id = $(this).attr('billid');
				window.location.href = '/trwuliu/driverreport/detail?id='+id;
			});
		}
}