/**
 * 新增缴费功能
 * @author ms
 * @date  2018年9月10日12:51:15
 * 
 * */
	function add() {
		var yhm = $("#add input[name=yhm]");
		var xqm = $("#add select[name=xqm]");
		var ldh = $("#add select[name=ldh]");
		var dyh = $("#add select[name=dyh]");
		var hh = $("#add input[name=hh]");
		if (yhm.val() == null || yhm.val() == "" || xqm.val() == null
				|| xqm.val() == "" || ldh.val() == null || ldh.val() == ""
				|| dyh.val() == null || dyh.val() == "" || hh.val() == null
				|| hh.val() == "") {
			sAlert('信息不能为空，请填写完整!');
			return false;
		}
		var hh = document.getElementById("hh").value;
		var yzbh = document.getElementById("yzbh").value;
		var jfje = document.getElementById("jfje").value;
		var hjje = document.getElementById("hjje").value;
		if (isNaN(hh)) {

			sAlert('户号必须是数字！');
			document.getElementById("hh").value = "";
			return;
		}
		if (isNaN(jfje)) {

			sAlert('缴费金额必须是数字！');
			document.getElementById("hh").value = "";
			return;
		}
		if (isNaN(hjje)) {

			sAlert('合计金额必须是数字！');
			document.getElementById("jfje").value = "";
			return;
		}
		if (isNaN(yzbh)) {

			sAlert('业主编号必须是数字！并且为八位数');
			document.getElementById("hjje").value = "";
			return;
		}
		if (yhbh.length != 8) {
			sAlert('用户编号必须是数字！并且为八位数');
			document.getElementById("hjje").value = "";
			return;
		}
		$("#add form").submit();
	}