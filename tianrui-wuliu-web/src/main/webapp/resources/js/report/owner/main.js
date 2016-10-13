$(function(){
	$('.dayReport').off('click').on('click',function(){
		$(this).addClass('select').siblings('li').removeClass('select');
		$('#starttime').val('').attr('onfocus','WdatePicker({dateFmt:"yyyy-MM-dd"})')
		$('#endtime').val('').attr('onfocus','WdatePicker({dateFmt:"yyyy-MM-dd"})')
	});
	$('.monthReport').off('click').on('click',function(){
		$(this).addClass('select').siblings('li').removeClass('select');
		$('#starttime').val('').attr('onfocus','WdatePicker({dateFmt:"yyyy-MM"})')
		$('#endtime').val('').attr('onfocus','WdatePicker({dateFmt:"yyyy-MM"})')
	});
	
	function dateStr2time(dateStr){
		if(!dateStr) return '';
		var dateArr = dateStr.split('-');
		if(dateArr.length == 2){
			var date = new Date(dateArr[0], dateArr[1], 0, 0, 0, 0);
			return date.getTime();
		}
		if(dateArr.length == 3){
			var date = new Date(dateArr[0], dateArr[1], dateArr[2], 0, 0, 0);
			return date.getTime();
		}
	}
	
	$('#report').off('click').on('click',function(){
		var item = $('.report_menu li.select').attr('item');
		var starttime = $('#starttime').val();
		var endtime = $('#endtime').val();
		var carriers = $('#carriers').val();
		var material = $('#material').val();
		var startplace = $('#startplace').val();
		var endplace = $('#endplace').val();
		var groups = [];
		var order = '';
		var subtotal = false;
		$('#groupColumns tr').each(function(){
			var col = $(this).find('td').eq(0).attr('col');
			var isXJ = $(this).find('td').eq(1).find('input')[0].checked;
			groups.push({
				field:col,
				subtotal:isXJ
			});
			if(isXJ){
				subtotal = true;
			}
			if(col && col == 'venderName'){
				col = 'venderid';
			}
			order += col+',';
		});
		order = order.substring(0,order.length-1);
		var statistical = [];
		$('#mathColumns tr').each(function(){
			var col = $(this).find('td').eq(0).attr('col');
			var type = $(this).find('td').eq(0).attr('type');
			var mathType = $(this).find('td').eq(1).find('select').val();
			statistical.push({
				field:col,
				summation:mathType,
				type:type
			});
		});
		var summation = $('#summation')[0].checked;
		window.location.href='/trwuliu/billreport/report?item='+item
														+'&starttime='+dateStr2time(starttime)
														+'&endtime='+(dateStr2time(endtime) + 86400)
														+'&vendername='+encodeURI(encodeURI(carriers))
														+'&cargoname='+encodeURI(encodeURI(material))
														+'&startcity='+encodeURI(encodeURI(startplace))
														+'&endcity='+encodeURI(encodeURI(endplace))
														+'&order='+order
														+'&groups='+JSON.stringify(groups)
														+'&statistical='+encodeURI(encodeURI(JSON.stringify(statistical)))
														+'&summation='+summation
														+'&subtotal='+subtotal
		/*$.ajax({
			url:'/trwuliu/billreport/report',
			data:{
				starttime:dateStr2time(starttime),
				endtime:dateStr2time(endtime),
				vendername:carriers,
				cargoname:material,
				startcity:startplace,
				endcity:endplace,
				order:order,
				groups:JSON.stringify(groups),
				statistical:JSON.stringify(statistical),
				summation:summation,
				subtotal:subtotal
			},
			type: 'post',
			async: false,
			success:function(){
				
			}
		});*/
	});
});