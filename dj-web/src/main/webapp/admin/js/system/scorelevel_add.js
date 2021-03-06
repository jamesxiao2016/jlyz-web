var id = "";
$(function() {
	id = $.getUrlParam("id");
	getProdInfo(id);
	initEvent();
	initValidate();
});

function initEvent() {
	$("#btnSubmit").click(submitForm);
	$("#btnBack").click(function() {
		history.go(-1);
	});
}

var validatorForm;

function initValidate() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	validatorForm = $("#productForm").validate({
		rules : {
			prodName : {
				required : true,
				minlength : 2
			}
		},
		messages : {
			prodName : icon + "请输入平台名称"
		}
	});
}

function getProdInfo(id) {
	if (id != undefined && id != "") {
		$("#id").val(id);
		$("#nametip").hide();
		$("#title").text("平台角色");

		$.ajaxPost("../../v1/system/getPlatformInfoById", {
			id : id
		}, function(data) {
			if (data.result == 200) {
				$.setDataToForm(data.data);
			} else {
				layer.msg("获取平台信息失败");
			}
		})
	}
}

function submitForm() {
	// 验证参数
	if (!validatorForm.form()) {
		return;
	}
	var formData = $("#productForm").formSerialize();
	console.log(formData);
	$.ajaxPost("../../v1/system/savePlatform", formData, function(data) {
		if (data.result == 200) {
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