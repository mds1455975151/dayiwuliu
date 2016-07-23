/**
 * ==================================================================
 * 实名认证功能中的认证明细与前台交互 v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.05.03
 */

// 初始化处理
$(function() { 
    // 左侧导航选中效果
	$('#authenPage').addClass('selected');

	// 界面根据认证状态变动
	setPageByStatus();
});

/**
 * 界面根据认证状态变动
 * @author kowuka
 */
function setPageByStatus() {
	// 审核中
	if (2 == perCheckStatus) {
		// 表头
		var vTable = $("<table></table>").addClass("table table-striped");
		var vThead = $("<thead></thead>");
		var vThead_tr = $("<tr></tr>");
		var vThead_tr_th1 = $("<th></th>").append("创建时间");
		var vThead_tr_th2 = $("<th></th>").append("订单号");
		var vThead_tr_th3 = $("<th></th>").append("状态");
		var vThead_tr_th4 = $("<th></th>").append("详情");
		vThead_tr.append(vThead_tr_th1)
				  .append(vThead_tr_th2)
				   .append(vThead_tr_th3)
				    .append(vThead_tr_th4);
		vThead.append(vThead_tr);
		vTable.append(vThead);
		// 表体
		$.ajax({
			url : PATH + '/trwuliu/Member/InfoListByMemberId',// 跳转到 action
			data : {
						memberId:memberId
				   },
			type : "post",
			success : function(result) {
				var data = result;
				var memberInfoList = data.data;
				var ret = data.code;
				// success
				if (ret == "000000") {
					var vTbody = $("<tbody></tbody>");
					for (var i = 0; i < memberInfoList.length; i++) {
						var vTbody_tr = $("<tr></tr>");
						var vTbody_tr_td1 = $("<td></td>").append(memberInfoList[i].submitDate);
						var vTbody_tr_td2 = $("<td></td>").append(memberInfoList[i].infoCode);
						var vTbody_tr_td3 = $("<td></td>").append("审核中");
						
						var vTbody_tr_td4 = $("<td></td>");
						var vTbody_tr_td4_a = $("<a></a>")
												.attr("href", "javascript:showDetail('" +memberInfoList[i].id + "')")
												 .html("详情");
						vTbody_tr_td4.append(vTbody_tr_td4_a);
						vTbody_tr.append(vTbody_tr_td1)
								  .append(vTbody_tr_td2)
								   .append(vTbody_tr_td3)
								    .append(vTbody_tr_td4);
						vTbody.append(vTbody_tr);
					}
					vTable.append(vTbody);
					$("#authenDetail_div").append(vTable);
				} else {
					$("#modal_common_content").html("出错啦！");
					$("#commonModal").modal();
				}
			}
		});
	}
}

/**
 * 列表行中【详情】的点击事件
 * @param id memberInfo的主键
 * @author kowuka
 */
function showDetail(id) {
	window.location.href = PATH + "/trwuliu/Member/authenDetailPage?memberInfoId=" + id;
}
