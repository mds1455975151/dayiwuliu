;(function($,win){
	init();
	function init(){
		initOrgSelect();
		initEventBind();
	}
	//初始化组织自动完成
	function initOrgSelect(){
		$.ajax({
			url : CONTEXTPATH + '/Organization/query',
			data : {},
			type : 'post',
			success : function(result) {
				var data = result.data;
				var orgArray = [];
				for (var i=0; i<data.length; i++) {
					var orgObj =  new Object();
					orgObj.value = data[i].id;
					orgObj.label = data[i].organizationname;
					orgArray[i] = orgObj;
				}
				$('#orgid').autocomplete({
					minLength: 0,
					max:3,
			        source: orgArray,
			        select: function(event, ui){
			            $('#orgid').val(ui.item.label);
			            $('#orgid').attr('orgid',ui.item.value);
			            return false;
			        }
			    });
			}
		});
	}
	//初始化按钮事件
	function initEventBind(){
		$('.search').off('click').on('click',function(){
			var pageNo = $('#pageNo').val() || 1;pageNo = $.trim(pageNo);
			getPlanStatReport(pageNo);
			$('#jumpPageNo').val(1);
		});
		$('.reset').off('click').on('click',function(){
			$('#cargoname').val('');
			$('#orgid').val('');
			$('#orgid').removeAttr('orgid');
			$('#plancode').val('');
			$('#starttime').val('');
			$('#endtime').val('');
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var jumpPageNo = $('#jumpPageNo').val();jumpPageNo = $.trim(jumpPageNo);
			var maxPageNo = $('#jumpPageNo').attr('maxPageNo');
			if(jumpPageNo && $.isNumeric(jumpPageNo) && parseInt(jumpPageNo) >0 && parseInt(jumpPageNo) <= parseInt(maxPageNo)){
				getPlanStatReport(jumpPageNo);
			}else{
				alert('此处必须为1-'+maxPageNo+'的数字并且不能为空！');
			}
		});
		$('#pageSize').change(function(){
			getPlanStatReport(1);
			$('#jumpPageNo').val(1);
		});
		$('.exportReport').off('click').on('click',function(){
			var path = CONTEXTPATH + '/statReport/billStatExport?'+$.param(getParams(1));
			$('<form method="post" action="' + path + '"></form>').appendTo('body').submit().remove();
		});
		$('.printReport').off('click').on('click',function(){
			$('#planReport').jqprint();
		});
	}
	//获取查询参数
	function getParams(pageNo){
		var cargoname = $('#cargoname').val() || '';cargoname = $.trim(cargoname);
		var orgName = $('#orgid').val() || '';orgName = $.trim(orgName);
		var orgid = '';
		if(orgName){
			orgid = $('#orgid').attr('orgid') || '';orgid = $.trim(orgid);
		}
		var plancode = $('#plancode').val() || '';plancode = $.trim(plancode);
		var starttime = $('#starttime').val() || '';starttime = $.trim(starttime);
		var endtime = $('#endtime').val() || '';endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || 10;pageSize = $.trim(pageSize);
		return {
			cargoname:cargoname,
			orgid:orgid,
			plancode:plancode,
			starttimeStr:starttime,
			endtimeStr:endtime,
			pageNo:pageNo,
			pageSize:pageSize
		} || {};
	}
	//查询计划报表
	function getPlanStatReport(pageNo){
		var params = getParams(pageNo);
		$.ajax({
			url : CONTEXTPATH + '/statReport/getBillStatReport',
			data : params,
			type : 'post',
			success : function(result) {
				if(result && result.code == '000000'){
					var data = result.data.list;
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					readerHtml(data);
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: pageCallback,
					    prev_text: '上一页',
					    next_text: '下一页',
					    items_per_page:pageSize,
					    num_display_entries:4,
					    current_page:pageNo-1,
					    num_edge_entries:1,
					    maxentries:total,
					    link_to:"javascript:void(0)"
					});
				}else{
					//查询异常
				}
			}
		});
	}
	//渲染报表
	function readerHtml(data){
		$('#planReport tbody').empty();
		if(data && data.length > 0){
			var totalSum = 0;
			var completedSum = 0;
			var completePriceSum = 0;
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				var planCode = obj.planCode || '';
				var billCode = obj.billCode || '';
				var ownerName = obj.ownerName || '';
				var venderName = obj.venderName || '';
				var vehicleno = obj.vehicleno || '';
				var driverName = obj.driverName || '';
				var cargoName = obj.cargoName || '';
				var starttimeStr = obj.starttimeStr || '';
				var endtimeStr = obj.endtimeStr || '';
				var routeName = obj.routeName || '';
				var weight = obj.weight || '';
				if(weight){
					weight = parseFloat(weight).toFixed(2);
					totalSum += parseFloat(weight);
				}
				var trueweight = obj.trueweight || '';
				if(trueweight){
					trueweight = parseFloat(trueweight).toFixed(2);
					completedSum += parseFloat(trueweight);
				}
				var price = obj.price || '';
				if(price){
					price = parseFloat(price).toFixed(2);
				}
				var completePrice = (price*trueweight).toFixed(2) || '';
				if(completePrice){
					completePriceSum += parseFloat(completePrice);
				}
				var status = obj.status || '';
				switch (status) {
				case '0':
					status = '新建';
					break;
				case '2':
					status = '已提货';
					break;
				case '5':
					status = '已卸货';
					break;
				case '6':
					status = '已完成';
					break;
				default:
					status = '';
					break;
				}
				$tr = $('<tr>').append('<td>'+(i+1)+'</td>')
							   .append('<td>'+planCode+'</td>')
							   .append('<td>'+billCode+'</td>')
							   .append('<td>'+ownerName+'</td>')
							   .append('<td>'+venderName+'</td>')
							   .append('<td>'+vehicleno+'</td>')
							   .append('<td>'+driverName+'</td>')
							   .append('<td>'+cargoName+'</td>')
							   .append('<td>'+starttimeStr+'</td>')
							   .append('<td>'+endtimeStr+'</td>')
							   .append('<td>'+routeName+'</td>')
							   .append('<td>'+weight+'</td>')
							   .append('<td>'+trueweight+'</td>')
							   .append('<td>'+price+'</td>')
							   .append('<td>'+completePrice+'</td>')
							   .append('<td>'+status+'</td>');
				$tr.data(obj);
				$('#planReport tbody').append($tr);
			}
			$('#totalSum').html(totalSum.toFixed(2));
			$('#completedSum').html(completedSum.toFixed(2));
			$('#completePriceSum').html(completePriceSum.toFixed(2));
		}else{
			//暂无数据
			$('#totalSum').html('0.00');
			$('#completedSum').html('0.00');
			$('#completePriceSum').html('0.00');
		}
	}
	
	function pageCallback(pageNo){
		getPlanStatReport(pageNo+1);
		$('#jumpPageNo').val(1);
	}
	
	
})(jQuery,window);