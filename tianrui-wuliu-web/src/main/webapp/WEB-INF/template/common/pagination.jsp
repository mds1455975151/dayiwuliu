<%@ page language="java" contentType="text/html;  charset=utf-8"
	pageEncoding="UTF-8"%>
<div class="page_date">
	<label>数据共：</label><i><span id="totalRecords"></span></i><label>条</label>
</div>
<input id="pageSize" type="hidden" value="${initParam.pageSize}" />
<input id="totalPages" type="hidden" />
<div class="page_date">
	<label>跳到第：</label> <input id="goPage" type="text"> <label>页</label>
	<button id="_toPageSize" class="btn btn-default">确定</button>
</div>
<div class="page_btn" id="pagination"></div>
