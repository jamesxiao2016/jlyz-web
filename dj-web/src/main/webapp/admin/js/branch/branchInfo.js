var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var sId = "cn.dlbdata.dj.db.mapper.DjQueryMapper.queryPartyMemberList";
$(function() {
    initTree();
    initEvent();
    init();
});

function initEvent() {
    $("#btnQuery").click(query);

    $("#btnAdd").click(function() {
        location.href = "add.html";
    });
}

function query(deptId) {
    var qryParam = {
    	"deptId" : deptId
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
    };
    jQuery(grid_selector).jqGrid({
        sortable: true,
        url : '../../api/v1/component/query',
        datatype : "json",
        rownumbers : true,
        colModel : [ {
            label : 'ID',
            name : 'id',
            index : 'id',
            hidden : true
        }, {
            label : '姓名',
            name : 'name',
            index : 'name',
            width :30
        }, {
            label : '性别',
            name : 'sex',
            index : 'sex',
            width :30
        }, {
            label : '总积分',
            name : 'totalScore',
            index : 'totalScore',
            align : "center",
            width :30
        }, {
            label : '职级',
            name : 'roleId',
            index : 'roleId',
            align : "center",
            width :30
        }, {
            label : '操作',
            name : '',
            formatter : actionFormatter,
            fixed : true,
            sortable : false,
            resize : false,
            align : "center",
            width : 150
        } ],
        postData : {
            "sId" : sId,
            params : JSON.stringify(qryParam)
        },
        rowNum : 10,
        rowList : [ 10, 20, 30 ],
        pager : pager_selector,
        viewrecords : true,
        height : '100%',

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
    var btnEdit = "<a href='../section/add.html?id=" + rowObject.id + "'>编辑</a>";
    var btnDel = "<a href='javascript:delRecord(" + rowObject.id + ")'>删除</a>";

    return btnEdit + "&nbsp;" + btnDel;
}

var setting = {
	callback: {
        onClick: zTreeOnClick
    }	
};

function zTreeOnClick(event,treeId,treeNode,clickFlag) {
	var deptId = treeNode["deptId"];
    query(deptId);
}

var zTreeObj;
function initTree()
{
    $.get("../../api/v1/dept/deptTree?sectionId=6",function (data) {
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
    });
}


//-->

// function delRecord(id) {
// 	layer.confirm('确定要删除吗？', {
// 		btn : [ '确定', '取消' ]
// 	// 按钮
// 	}, function() {
// 		$.ajaxPost("../../admin/section/deleteById", {
// 			id : id
// 		}, function(data) {
// 			if (data.code == 1000) {
// 				layer.msg("删除成功");
// 				query();
// 			} else {
// 				layer.msg("删除失败");
// 			}
// 		})
// 	});
// }
