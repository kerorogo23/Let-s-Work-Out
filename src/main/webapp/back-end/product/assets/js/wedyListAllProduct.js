
function deleteAJAX(obj) {
	var yes = confirm('確定強制下架嗎?');
	if(yes){
		console.log('confirm');
	} else{
		console.log('cancel');
		return;
	}
	var productNo = obj.nextSibling.nextSibling.value;
	var pStatus = obj.parentNode.previousSibling.previousSibling;
	console.log(productNo);
	console.log(pStatus);
	$.ajax({
		type : "POST",
		url : "../../product/product.do",
		data : {
			"action" : "deleteBack",
			"productNo" : productNo
		},
		success : function(data) {
			alert('下架成功');
			pStatus.innerText = "";
			pStatus.innerText = "已下架"
		},
		error : function() {
			alert("AJAX-class發生錯誤囉!")
		}
	});
}