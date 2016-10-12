;(function($,win){
	
	function init(target){
		var opts = $.data(target, 'report').options;
		var data = opts.data;
		var columns = $.extend(true, [], opts.columns);;
		if(!columns || columns.length == 0){
			return;
		}
		var subColumns = [];
		opts.groups.forEach(function(x, i, a){
			var col = columns.filter(function(x1){
				return x1.field == this.field;
			},x);
			var subcol = columns.splice(columns.indexOf(col[0]),1);
			subColumns.push(subcol[0]);
		});
		columns = subColumns.concat(columns);
		var $thead = $('<thead>')
		var $thead_tr = $('<tr>');
		for(var i=0;i<columns.length;i++){
			var col = columns[i];
			var $th = $('<th>').css('width',col.width?col.width:'auto').html(col.title);
			for(var style in col.theadStyle){
				$th.css(
					style,col.theadStyle[style]
				);
			}
			$thead_tr.append($th);
		}
		$thead.append($thead_tr);
		$table = $('<table>').append($thead).css({
			'border-collapse':'collapse'
		});
		
		if(!data || data.length == 0){
			return;
		}
		var $tbody = $('<tbody>');
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var $tbody_tr = $('<tr>');
			for(var j=0;j<columns.length;j++){
				var col = columns[j];
				var $td = $('<td>');
				var group = opts.groups.filter(function(x){
						return this == x.field;
					},col.field);
				if(group && group.length>0){
					if(obj[col.field] == $tbody.find('tr').not('tr.statistical').last().find('td').eq(j).data(col.field)){
						if(!$tbody.find('tr:last').hasClass('statistical')){
							var _tr = $tbody.find('tr').not('tr.statistical')[$thead_tr.find('th').eq(j).data('index')];
							var rowspan = $(_tr).find('td').eq(j).attr('rowspan');
							if(!rowspan) rowspan = 1;
							$(_tr).find('td').eq(j).attr('rowspan',++rowspan);
							$td.hide();
							$td.data(col.field,obj[col.field]);
							$tbody_tr.append($td);
							continue;
						}
					}else{
						$thead_tr.find('th').eq(j).data('index',i);
						//插入小计tr
						if(opts.subtotal && group[0].subtotal){
							if(i != 0){
								var $xj_tr = $tbody_tr.clone(false);
								$xj_tr.find('td').empty().hide();
								$xj_tr.insertAfter($tbody.find('tr').not('tr.statistical').last());
								$xj_tr.addClass('statistical');
								var $xj_td = $('<td>');
								$xj_td.html('小计');
								$xj_tr.append($xj_td);
								for(var k=j+1;k<columns.length;k++){
									var math = opts.statistical.filter(function(x){
										return this.field == x.field;
									},columns[k]);
									if(math && math.length>0){
										var sum = mathXJContent($tbody,j,k,math[0]);
										$xj_tr.append("<td>"+sum+"</td>");
									}else{
										$xj_tr.append("<td></td>");
									}
								}
								if(opts.subtotalColor){
									$xj_tr.find('td').css({
										background:opts.subtotalColor
									});
								}
								//调整前面td的rowspan
								if(j != 0){
									for(var k=j-1;k>=0;k--){
										var $trs = $tbody.find('tr').not('tr.statistical');
										for(var l=$trs.length-1;l>=0;l--){
											var $tr = $trs.eq(l);
											var rowspan = $tr.find('td').eq(k).attr('rowspan');
											if(rowspan){
												$tr.find('td').eq(k).attr('rowspan',++rowspan);
												break;
											}
										}
									}
								}
							}
						}
					}
					
				}
				$td.attr('rowspan','1')
				if(col.formatter){
					$td.html(col.formatter(obj[col.field],$td).toString());
				}else{
					$td.html(obj[col.field].toString());
				}
				for(var style in col.tbodyStyle){
					$td.css(
						style,col.tbodyStyle[style]
					);
				}
				$td.data(col.field,obj[col.field]);
				if($.isNumeric(obj[col.field])){
					$td.attr('style',$td.attr('style')+"mso-number-format:'\@';")
				}
				$tbody_tr.append($td);
			}
			$tbody.append($tbody_tr);
			if(i == data.length-1){
				//结尾小计
				if(opts.subtotal){
					var group = opts.groups.filter(function(x){
						return x.subtotal;
					});
					if(group && group.length>0){
						for(var j=group.length-1;j>=0;j--){
							var g = group[j];
							var $xj_tr = $('<tr>').addClass('statistical');
							var index = 0;
							for(var k=0;k<columns.length;k++){
								var col = columns[k];
								if(!$tbody.find('tr:last').find('td').eq(k).attr('rowspan') && col.field != g.field && index >= k){
									var index = $thead_tr.find('th').eq(k).data('index');
									var rowspan = $tbody.find('tr').not('tr.statistical').eq(index).find('td').eq(k).attr('rowspan');
									$tbody.find('tr').not('tr.statistical').eq(index).find('td').eq(k).attr('rowspan',++rowspan);
								}else{
									var $td = $('<td>');
									if(col.field == g.field){
										index = k;
										$td.html('小计');
									}
									var math = opts.statistical.filter(function(x){
										return this.field == x.field;
									},col);
									if(math && math.length>0){
										var sum = mathXJContent($tbody,index,k,math[0]);
										$td.html(sum);
									}
									$xj_tr.append($td);
									if(opts.subtotalColor){
										$xj_tr.find('td').css({
											background:opts.subtotalColor
										});
									}
								}
							}
							$tbody.append($xj_tr);
						}
					}
				}
				//结尾总计
				if(opts.summation){
					var $zj_tr = $('<tr>').addClass('total').append('<td>总计</td>');
					for(var j=1;j<columns.length;j++){
						var math = opts.statistical.filter(function(x){
							return this.field == x.field;
						},columns[j]);
						if(math && math.length>0){
							var sum = mathXJContent($tbody,-1,j,math[0]);
							$zj_tr.append("<td>"+sum+"</td>");
						}else{
							$zj_tr.append("<td></td>");
						}
					}
					if(opts.summationColor){
						$zj_tr.find('td').css({
							background:opts.summationColor
						});
					}
					$tbody.append($zj_tr);
				}
			}
		}
		$table.append($tbody).addClass('table table-bordered tablenone');
		if(opts.rowHeight){
			$table.find('tr').css({
				height:opts.rowHeight
			});
		}
		if(opts.fontSize){
			$table.find('td,th').css({
				fontSize:opts.fontSize
			});
		}
		if(opts.textAlign){
			$table.find('td,th').css({
				textAlign:opts.textAlign
			});
		}
		$(target).append($table);
		$table.find('td:hidden').remove();
	}
	
	function mathXJContent ($tbody,j,k,math) {
		var $trs = $tbody.find('tr').not('tr.statistical');
		var sum = 0;
		var index = 0;
		for(var i=$trs.length-1;i>=0;i--){
			var $td = $trs.eq(i).find('td').eq(j);
			var rowspan = $td.attr('rowspan');
			var num = $trs.eq(i).find('td').eq(k).data(math.field);
			sum += num;
			//默认求和
			index++;
			if(j < 0){
				continue;
			}
			if(rowspan){
				if(rowspan == 1) {
					break;
				}
				if(rowspan > 1){
					break;
				}
			}
		}
		if(math.summation == '平均'){
			sum = sum/index;
		}
		return sum.toFixed(2);
	}
	
	
	$.fn.report = function(options, param){
		if (typeof options == 'string'){
			return $.fn.report.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $(this).data('report');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $(this).data('report', {
					options: $.extend({}, $.fn.report.defaults, options)
				});
				init(this);
			}
		});
	};

	
	$.fn.report.methods = {
		options: function (jq) {
			return jq.data('report').options;
		}
	}
	
	$.fn.report.defaults = {
		data:null,
		columns:[/*{
				field:'createtime',
				title:'时间',
				width:'200',
				theadStyle:{
					background:'#ccc',
					color:'red',
					textAlign:'left'
				},
				tbodyStyle:{
					background:'yellow',
					color:'green',
					textAlign:'left'
				},
				formatter:function (data) {
					return dateformat(data);
				}
			}*/],
		groups:[/*{
			field:'',
			subtotal:false
		}*/],
		sort:[],
		statistical:[/*{
				field:'carNum',
				summation:'+'
			}*/],
		rowHeight:null,
		subtotalColor:null,
		summationColor:null,
		subtotal:false,//小计
		summation:false,//合计
		fontSize:'14px',
		textAlign:'left'
	};
	
})(jQuery,window);
