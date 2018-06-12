var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var sId = "cn.dlbdata.dj.db.mapper.DjQueryMapper.queryLoginLogList";
$(function() {
	init();
	initEvent();
});

function initEvent() {
	$("#btnQuery").click(query);

	$("#btnAdd").click(function() {
		location.href = "add.html";
	});
	
	//支部名称
	$.post("../../api/v1/component/getDeptNameList", function(
			data) {
		$('.seldept').select2({
			data : data
		});
	})
}

function query() {
	var qryParam = {
		"seldept" : $("#seldept").val(),
		"userAccount" : $("#userAccount").val(),
		"userName" : $("#userName").val(),
		"status": $("#status").val(),
		"orderBy" : 'create_time desc'
	};
	jQuery(grid_selector).setGridParam({
		postData : {
			"sId" : sId,
			params : JSON.stringify(qryParam)
		}
	}).trigger("reloadGrid");
}

function init() {
	var qryParam = {
		"orderBy" : 'create_time desc'
	};
	jQuery(grid_selector).jqGrid({
		url : '../../api/v1/component/query',
		datatype : "json",
		rownumbers : true,
		colModel : [ {
			label : 'ID',
			name : 'id',
			index : 'id',
			hidden : true
		}, {
			label : '账号',
			name : 'userAccount',
			index : 'user_account',
			width : 80,
		}, {
			label : '用户名',
			name : 'userName',
			index : 'user_name',
			width : 80,
		}, {
			label : '支部名称',
			name : 'deptName',
			index : 'dept_name',
			width : 170,
		}, 
		{
			label : '状态码',
			name : 'status',
			index : 'status',
			width : 80,
		}, 
		{
			label : '日志信息',
			name : 'errorMsg',
			index : 'error_msg',
		}, 
		 {
			label : '登录时间',
			name : 'createTime',
			index : 'create_time',
			formatter : datetimeFormatter,
			align : "center",
			width : 100
		}, 
		{
			label : '操作',
			name : '',
			formatter : actionFormatter,
			fixed : true,
			sortable : false,
			resize : false,
			align : "center",
			width : 150,
		} ],
		postData : {
			"sId" : sId,
			params : JSON.stringify(qryParam)
		},
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		pager : pager_selector,
		viewrecords : true,
		height : '100%',
		sortname : 'create_time',
		sortorder : "desc",
		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				styleCheckbox(table);
				updateActionIcons(table);
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},
	});

	jQuery(grid_selector).jqGrid('navGrid', pager_selector, {
		edit : false,
		add : false,
		del : false,
		search : false
	});

	var parent_column = $(grid_selector).closest('[class*="col-"]');
	// resize to fit page size
	$(window).on('resize.jqGrid', function() {
		$(grid_selector).jqGrid('setGridWidth', parent_column.width());
	})
	$(window).triggerHandler('resize.jqGrid');
}

function actionFormatter(cellvalue, options, rowObject) {
	var btnEdit = "<a href='../loginlog/detail.html?id=" + rowObject.id + "'>查看详情&nbsp;&nbsp;&nbsp;</a>";
	var btnDel = "<a href='javascript:delRecord(" + rowObject.id + ")'>删除</a>";

	return btnEdit + "&nbsp;" + btnDel;
}

function delRecord(id) {
	layer.confirm('确定要删除吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajaxPost("../../admin/loginlog/deleteById", {
			id : id
		}, function(data) {
			if (data.code == 1000) {
				layer.msg("删除成功");
				query();
			} else {
				layer.msg("删除失败");
			}
		})
	});
}