var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var sId = "com.meig.link.db.mapper.MgQueryMapper.queryPlatform";
$(function() {
	init();
	initEvent();
});

function initEvent() {
	$("#btnQuery").click(query);

	$("#btnAdd").click(function() {
		location.href = "platform_add.html";
	});
}

function query() {
	var qryParam = {
		"name": getLikeVal($("#name").val()),
		"start": $("#start").val(),
		"end": $("#end").val(),
		"orderBy": 't.create_time desc'
	};
	jQuery(grid_selector).setGridParam({
		postData: {
			"sId": sId,
			params: JSON.stringify(qryParam)
		}
	}).trigger("reloadGrid");
}

function init() {
	var qryParam = {
		"deviceType": '1',
		"orderBy": 't.create_time desc'
	};
	jQuery(grid_selector).jqGrid({
		url: '../../compCtl/queryJqData',
		datatype: "json",
		rownumbers: true,
		colModel: [{
			label: 'ID',
			name: 'id',
			index: 'id',
			hidden: true
		}, {
			label: '平台名称',
			name: 'prodName',
			index: 'prod_name',
		}, {
			label: '说明',
			name: 'prodDesc',
			index: 'prod_desc',
			align: "center",
		}, {
			label: '创建时间',
			name: 'createTime',
			index: 'create_time',
			formatter: datetimeFormatter,
			align: "center",
			width: 100
		}, {
			label: '操作',
			name: '',
			formatter: actionFormatter,
			fixed: true,
			sortable: false,
			resize: false,
			align: "center",
			width: 150,
		}],
		postData: {
			"sId": sId,
			params: JSON.stringify(qryParam)
		},
		rowNum: 10,
		rowList: [10, 30, 50],
		pager: pager_selector,
		viewrecords: true,
		height: '100%',
		sortname: 't.create_time',
		sortorder: "desc",
		loadComplete: function() {
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
		edit: false,
		add: false,
		del: false,
		search: false
	});

	var parent_column = $(grid_selector).closest('[class*="col-"]');
	// resize to fit page size
	$(window).on('resize.jqGrid', function() {
		$(grid_selector).jqGrid('setGridWidth', parent_column.width());
	})
	$(window).triggerHandler('resize.jqGrid');
}

function actionFormatter(cellvalue, options, rowObject) {
	var btnTrack = "<a href='platform_add.html?id=" + rowObject.id + "'>编辑</a>";
	var btnEvent = "<a href='javascript:delRecord(" + rowObject.id + ")'>删除</a>";
	return btnTrack + "&nbsp;" + btnEvent;
}

function delRecord(id) {
	layer.confirm('确定要删除吗？', {
		btn: ['确定', '取消'] //按钮
	}, function() {
		$.ajaxPost("../../v1/system/deletePlatformById", {
			id: id
		}, function(data) {
			if(data.result == 200) {
				layer.msg("删除成功");
				query();
			} else {
				layer.msg("删除失败");
			}
		})
	});
}
