var id = "";
$(function() {
	id = $.getUrlParam("id");
	//getProdInfo(id);
	initEvent();
	initValidate();
    initData();
});

function initEvent() {
	$("#btnSubmit").click(submitForm);
	$("#btnBack").click(function() {
		history.go(-1);
	});
}

function initData() {

    $.post("../../admin/getDeptAndPtMemberTree/"+sectionId, function(data) {
        $("#principalId").select2({
            data : data,
            language : "zh-CN"
        });
        if (secprincipalId) {
            $("#principalId").val(secprincipalId).trigger("change");
        }
    });
}

var validatorForm;

function initValidate() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	validatorForm = $("#productForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2
			}
		},
		messages : {
			roleName : icon + "请输入名称"
		}
	});
}


function submitForm() {
	// 验证参数
	if (!validatorForm.form()) {
		return;
	}
	var id = $("#id").val();
	var formData = $("#productForm").formSerialize();
	console.log(formData);
	$.ajaxJson("../section/updateSection/" + id, formData, function(data) {
		if (data.code == 1000) {
			history.go(-1);
		} else {
			if (data.reason) {
				layer.msg(data.reason);
			} else {
				layer.msg("保存失败");
			}
		}
	});
	// $.submitForm();
	// $("#productForm").submit();
}