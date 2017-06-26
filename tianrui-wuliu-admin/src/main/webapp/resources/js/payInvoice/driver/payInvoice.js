;(function($){
	var URL = {
			page: '/pay/invoice/driver/page',
			auditData: '/pay/invoice/driver/auditData',
			audit: '/pay/invoice/driver/audit',
			updateData: '/pay/invoice/driver/updateData',
			update: '/pay/invoice/driver/update',
			push: '/pay/invoice/driver/push'
	};
	//初始化
	init();
	//初始化方法
	function init(){
		getDataForAjax(1);
		bindEvent();
	}
	//事件绑定函数
	function bindEvent(){
		$('.search').off('click').on('click', function(){
			getDataForAjax(1);
		});
		$('.reset').off('click').on('click', function(){
			resetSearchParams();
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				getDataForAjax(pageNo);
			}
		});
		$('#pageSize').change(function(){
			getDataForAjax(1);
		});
		onpropertychange();
	}
	//重置搜索条件
	function resetSearchParams(){
		$('#billCode').val('');
		$('#cargoName').val('');
	}
	//获取搜索条件
	function getSearchParams(){
		var billCode = $('#billCode').val(); billCode = $.trim(billCode);
		var cargoName = $('#cargoName').val(); cargoName = $.trim(cargoName);
		var pageSize = $('#pageSize').val() || ''; pageSize = $.trim(pageSize);
		return {
			likeBillCode: billCode,
			likeCargoName: cargoName,
			pageSize: pageSize
		}
	}
	//ajax请求搜索结果
	function getDataForAjax(pageNo){
		var params = getSearchParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.page,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo){
					    	getDataForAjax(pageNo+1);
					    },
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
					alert('系统异常，请稍候再试！');
				}
			}
		});
	}
	//解析请求结果并渲染页面
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						 .append('<td>'+(obj.code || '')+'</td>')
						 .append('<td>'+(obj.invoiceName || '')+'</td>')
						 .append('<td>应付款'+(obj.amountPayable || '')+'元<br/>已付款'+(obj.paidAmount || '')+'元</td>')
						 .append('<td>'+(obj.payeeName || '')+'</td>')
						 .append('<td>'+(obj.payInvoiceStatus || '')+'</td>')
						 .append('<td>'+operationBtn(obj.btnOpts)+'</td>')
						 .data('option-data-id', obj.id)
						 .appendTo('#dataBody');
			}
			$('#dataBody').find('a.audit').off('click').on('click', function(e){
				e.stopPropagation();
				var id = $(this).closest('tr').data('option-data-id');
				loadAuditData(id);
			});
			$('#dataBody').find('a.update').off('click').on('click', function(e){
				e.stopPropagation();
				var id = $(this).closest('tr').data('option-data-id');
				loadUpdateData(id);
			});
			$('#dataBody').find('a.push').off('click').on('click', function(e){
				e.stopPropagation();
				var id = $(this).closest('tr').data('option-data-id');
				pushPayInvoice(id);
			});
		}
	}	
	//初始化操作按钮
	function operationBtn(btnOpts){
		var _btn = '';
		switch (btnOpts) {
		case 1:
			_btn = '<a class="btn btnblue btnprimary audit">审核</a>';
			break;
		case 2:
			_btn = '<a class="btn btnblue btnprimary update">修改</a><a class="btn btnblue btnprimary push">提交</a>';
			break;
		default:
			break;
		}
		return _btn;
	}
	//加载审核数据
	function loadAuditData(id){
		removeAuditData('审核信息');
		$.ajax({
			url:URL.auditData,
			data:{id: id},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					setAuditData(result.data);
					$('#audit').modal();
				}else{
					alert('系统异常，请稍候再试！');
				}
			}
		});
	}
	//加载修改数据
	function loadUpdateData(id){
		removeAuditData('修改信息');
		$.ajax({
			url:URL.updateData,
			data:{id: id},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					setAuditData(result.data);
					$('#audit').modal();
				}else{
					alert('系统异常，请稍候再试！');
				}
			}
		});
	}
	//SET审核dialog默认数据
	function setAuditData(data){
		if(data){
			for(var key in data){
				var _input = $('.payMoney input[data-name="'+key+'"]');
				var _span = $('.juesemodal span[data-name="'+key+'"]');
				if(_input){
					_input.val(data[key]);
				}
				if(_span){
					_span.html(data[key]);
				}
			}
			$('#auditCommit').off('click').on('click', function(){
				this.disabled = true;
				auditForAjax(data.id, this);
			});
		}
	}
	//remove审核dialog数据
	function removeAuditData(title){
		$('.modal-title').html(title)
		$('.payMoney input[data-name]').val('');
		$('.juesemodal span[data-name]').html('');
	}
	//获取审核修改的参数
	function getAuditParams(){
		var params = {}
		$('.payMoney input').each(function(){
			var name = $(this).attr('data-name');
			params[name] = $(this).val();
		});
		return params
	}
	//校验审核修改的参数
	function validate(params){
		if(!params.id){
			alert("审核ID不能为空！");return;
		}
		if(!params.billTotalPrice){
			alert("账单总额不能为空！");return;
		}
		if(!params.deductOilCard){
			alert("油卡金额不能为空！");return;
		}
		if(!params.deductWeightMisc){
			alert("扣重扣杂不能为空！");return;
		}
		if(!params.deductMoney){
			alert("扣款金额不能为空！");return;
		}
		if(!params.deductOther){
			alert("其他款项不能为空！");return;
		}
		return params;
	}
	//审核提交
	function auditForAjax(id, _commitBtn){
		var params = getAuditParams();
		params.id = id;
		if(validate(params)){
			$.ajax({
				url:URL.audit,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						$('#audit').modal('hide');
						$('.search').click();
					}else{
						alert('系统异常，请稍候再试！');
					}
					_commitBtn.disabled = false;
				}
			});
		}
	}
	//提交
	function pushPayInvoice(id){
		var flag = window.confirm('是否确认提交？');
		if(flag){
			$.ajax({
				url:URL.push,
				data:{id: id},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						$('.search').click();
					}else{
						alert(result.error);
					}
				}
			});
		}
	}
	//给审核和修改弹出窗绑定onpropertychange
	function onpropertychange(){
		$('.payMoney input').off('keyup').on('keyup', function(e){
			e.stopPropagation();
			var params = getAuditParams();
			var amountPayable = params.billTotalPrice - params.deductOilCard - params.deductWeightMisc - params.deductMoney - params.deductOther;
			$('.payMoney input[data-name="amountPayable"]').val(amountPayable);
		});
	}
	
})(jQuery);