var PAGE;
if(PAGE){
	PAGE = null;
}
PAGE = {
	main:{}
}
PAGE.main = {
	init:function(){
		var _this = this;
		_this.loadReport();
		_this.bindEvent();
	},
	bindEvent:function(){
		//导出
		$('#exportReport').off('click').on('click',function(){
			var options = {
				type:'excel',
			}
			if(item == 'D'){
				options.tableName="日报表";
			}
			if(item == 'M'){
				options.tableName="月报表";
			}
			$('#reportContains').find('table').tableExport(options);
		});
		//返回
		$('#backSearch').off('click').on('click',function(){
			
		});
	},
	loadReport:function(){
		if(data){
			data.forEach(function(x,i,a){
				var date = new Date(parseInt(x.unloadtime));
				var formatStr = ''
				formatStr += date.getFullYear()+"-";
				formatStr += date.getMonth()+1<10?"0"+(date.getMonth()+1):date.getMonth()+1;
				if(item == 'D'){
					formatStr += "-" + (date.getDate()<10?"0"+date.getDate():date.getDate());
				}
//				if(item == 'M'){
//				}
				x.unloadtime = formatStr;
			});
		}
		$('#reportContains').report({
			data:data,
			columns:[{
				field:'unloadtime',
				title:'时间',
				width:''
			},{
				field:'vehicleno',
				title:'车牌号',
				width:''
			},{
				field:'cargoname',
				title:'物料',
				width:''
			},{
				field:'startcity',
				title:'起运地',
				width:''
			},{
				field:'endcity',
				title:'目的地',
				width:''
			},{
				field:'price',
				title:'单价',
				width:''
			},{
				field:'venderName',
				title:'承运商',
				width:''
			},{
				field:'totalnumber',
				title:'车数',
				width:''
			},{
				field:'trueweight',
				title:'结算数量',
				width:''
			},{
				field:'waybillno',
				title:'运单编号',
				width:''
			}],
			groups:groups,
			statistical:statistical,
			subtotal:subtotal,
			summation:summation,
			subtotalColor:'#cdeeff',
			summationColor:'#cdeeff',
			fontSize:'12px',
			textAlign:'center',
			rowHeight:'30',
			borderStyle:'',
			borderWidth:'',
			borderColor:'#ccc'
		});
	}
}
