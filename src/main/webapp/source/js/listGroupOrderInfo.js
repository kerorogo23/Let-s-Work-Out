window.addEventListener('load', function(){
        let className = sessionStorage.getItem('className')
        let groupDate = sessionStorage.getItem('groupDate')
        let groupStartingTime = sessionStorage.getItem('groupStartingTime')
		let groupClassPrice = sessionStorage.getItem('groupClassPrice')
		
		// -----------------------

		let classNa = document.getElementById('classNa')
		let date = document.getElementById('date')
		let time = document.getElementById('time')
		let name = document.getElementById('name')
		let total = document.getElementById('total')
		// -----------------------
		name.innerText = className
		classNa.innerText = className
		date.innerText = '課程日期：' + groupDate
		time.innerText = '課程時間：' +groupStartingTime +':00~' + (parseInt(groupStartingTime)+1) +":00"
		total.innerText = '總計：'+groupClassPrice+'元'
//		$.ajax({
//            url: "/CFA103G1_12.15/front-end/grouporder/groupOrder.do", //請求的url地址
//            dataType: "json", //返回格式為json
//            async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
//            type: "post", //請求方式
//            data: { "action": "getOneMem_For_Display", "memId": memId },
//            success: function (members) {
//            	console.log("成功")
//                console.log(members)
//        		mem.innerText = members.memName
//            },
//            error: function () {
//                console.log("失敗")
//            }
//        });
 		   
 	});
	
	$("#dbt").click(function () {
    	location.href =  location.pathname.substring(0,location.pathname.indexOf('/',4))+'/front-end/index/index.jsp'
    });
	$("#pay").click(function(){
		let groupTimeNo = sessionStorage.getItem('groupTimeNo')
	    	   $.ajax({
	               url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
	               dataType: "json", //返回格式為json
	               async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
	               type: "post", //請求方式
	               data: { 'action': 'insert','groupTimeNo': groupTimeNo},
	               success: showCon,
	               error: function () {
	                   console.log("失敗")
	               }
	           });
	})
	function reserve(){
//			if((cc_no1!=null)&&(cc_no2!=null)&&(cc_no3!=null)&&(cc_no4!=null)){
				let groupTimeNo = sessionStorage.getItem('groupTimeNo')
	    	   $.ajax({
	               url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
	               dataType: "json", //返回格式為json
	               async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
	               type: "post", //請求方式
	               data: { 'action': 'insert', 'groupTimeNo': groupTimeNo},
	               success: showCon,
//						function (groupOrder) {
//	                   console.log("成功")
//	                   console.log(groupOrder)
//	                   sessionStorage.setItem('memId', groupOrder.memId)
//	                   sessionStorage.setItem('groupTimeNo',groupOrder.groupTimeNo)
//					   sessionStorage.setItem('groupOrderTime', groupOrder.groupOrderTime)
//					   location.href="/CFA103G1_12.15/front-end/grouporder/listGroupOrderInfo2.html"
	               	   
//					},
	               error: function () {
	                   console.log("失敗")
	               }
	           });
//			}else{
//				alert('請輸入信用卡號')
//			}
			
       }

		function showCon(){
			$(".cov").show();
//			let memId = sessionStorage.getItem('memId')
//        	let className = sessionStorage.getItem('className')
//        	let groupDate = sessionStorage.getItem('groupDate')
//        	let groupStartingTime = sessionStorage.getItem('groupStartingTime')
//			let groupClassPrice = sessionStorage.getItem('groupClassPrice')
//			// -----------------------
//			let mem = document.getElementById('mem')
//			let classNa = document.getElementById('classNa')
//			let date = document.getElementById('date')
//			let time = document.getElementById('time')
//			let name = document.getElementById('name')
//			let total = document.getElementById('total')
//			// -----------------------
//			name.innerText = className
//			classNa.innerText = '課程名稱：' + className
//			date.innerText = '課程日期：' + groupDate
//			time.innerText = '課程時間：' +groupStartingTime
//			total.innerText = '總計'+ groupClassPrice +'元！'
			
//			$.ajax({
//            	url: "/CFA103G1_12.15/front-end/grouporder/groupOrder.do", //請求的url地址
//            	dataType: "json", //返回格式為json
//            	async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
//            	type: "post", //請求方式
//            	data: { "action": "getOneMem_For_Display", "memId": 1002 },
//            	success: function (members) {
//            	console.log("成功")
//                console.log(members)
//        		mem.innerText = members.memName
//            	},
//            	error: function () {
//                console.log("失敗")
//            	}
//        	});	
		}