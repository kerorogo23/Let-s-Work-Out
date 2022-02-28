
function deleteAJAX(obj) {
	var yes = confirm('確定強制取消交易嗎?');
	if(yes){
		console.log('confirm');
	} else{
		console.log('cancel');
		return;
	}
	var poNo = obj.nextSibling.nextSibling.value;
	var poStatus = obj.parentNode.previousSibling.previousSibling;
	console.log(poNo);
	console.log(poStatus);
	$.ajax({
		type : "POST",
		url : "../../purchase_order/purchaseOrder.do",
		data : {
			"action" : "getOneForUpdateStatusBack",
			"poNo" : poNo,
			"changePoStatus" : "70"
		},
		success : function(data) {
			alert('訂單已取消');
			poStatus.innerText = "";
			poStatus.innerText = "訂單已取消"
		},
		error : function() {
			alert("AJAX-class發生錯誤囉!")
		}
	});
}