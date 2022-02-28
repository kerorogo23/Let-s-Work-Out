        window.addEventListener('load', function () {
            let content = document.getElementById('content')
            $.ajax({
                url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: false, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { "action": "listGroupOrders_ByMemId_A"},
                success: function (groupOrder) {
                    for (let i = 0; i < groupOrder.length; i++) {
                        $.ajax({
                            url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouphour/groupHour.do", //請求的url地址
                            dataType: "json", //返回格式為json
                            async: false, //請求是否非同步，預設為非同步，這也是ajax重要特性
                            type: "post", //請求方式
                            data: { "action": "getOne_For_Display", "groupTimeNo": groupOrder[i].groupTimeNo },
                            success: function (groupHour) {
                                $.ajax({
                                    url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                                    dataType: "json", //返回格式為json
                                    async: false, //請求是否非同步，預設為非同步，這也是ajax重要特性
                                    type: "post", //請求方式
                                    data: { "action": "getOne_For_Display", "groupClassNo": groupHour.groupClassNo },
                                    success: function (groupClass) {
                                    	 let tr = document.createElement('tr')
                                    	 content.appendChild(tr)
                                         let t = document.getElementsByTagName('tr')
//                                         console.log(t)
                                         t[i+2].innerHTML += `<td>` + groupClass.className + `</td>`
                                         t[i+2].innerHTML += `<td>` + groupHour.groupDate + `</td>`
                                         t[i+2].innerHTML += `<td>` + groupHour.groupStartingTime + ':00~' + (parseInt(groupHour.groupStartingTime)+1) +':00'+ `</td>`
                                         t[i+2].innerHTML += `<td>` + groupOrder[i].groupOrderTime + `</td>`
                                         if(groupHour.courseStatus===-1){
                                        	 t[i+2].innerHTML += `<td>` + '已取消' + `</td>`
                                         }else if(groupHour.courseStatus===0){
                                        	 t[i+2].innerHTML += `<td>` + '尚未開始' + `</td>`
                                        	 
                                         }else{
                                        	 t[i+2].innerHTML += `<td>` + '已完成' + `</td>`
                                         }
										 if(groupOrder[i].groupOrderStatus===-1){
											 t[i+2].innerHTML += `<td>` + '已取消' + `</td>`
											 t[i+2].innerHTML += `<td>`+`<button>` + '取消訂單' + `</button>`+ `</td>`
											 let button = document.getElementsByTagName('button')
											 button[button.length-4].disabled = "disabled"
                                         }else if(groupOrder[i].groupOrderStatus===0){
                                        	 t[i+2].innerHTML += `<td>` + '進行中' + `</td>` 
                                        	 t[i+2].innerHTML += `<td>`+`<button>` + '取消訂單' + `</button>`+ `</td>`
                                             let button = document.getElementsByTagName('button')
    		 	      	     				 button[button.length-4].value = groupOrder[i].groupOrderNo
    		 	      	     				 button[button.length-4].onclick = function(){
                                        		 $.ajax({
                                                     url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
                                                     dataType: "json", //返回格式為json
                                                     async: false, //請求是否非同步，預設為非同步，這也是ajax重要特性
                                                     type: "post", //請求方式
                                                     data: { "action": "update", "groupOrderNo": $(this).val(),"groupTimeNo": groupHour.groupTimeNo, "groupOrderTime": groupOrder[i].groupOrderTime,"groupOrderStatus": -1},
                                                     success: function (groupOrder) {
                                                     	location.href= location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/listAllGroupOrderByMem.jsp"
                                                     },
                                                     error: function () {
                                                         console.log("失敗")
                                                     }
                                               
                                            	 });
                                        	 }
                                         }else if(groupOrder[i].groupOrderStatus===1){
                                        	 t[i+2].innerHTML += `<td>` + '已完成' + `</td>`
                                        	 t[i+2].innerHTML += `<td>`+`<button>` + '申請取消' + `</button>`+ `</td>`
                                        	 let button = document.getElementsByTagName('button')
                                        	 button[button.length-4].disabled = "disabled"
//                                         }else{
//                                        	 t[i+2].innerHTML += `<td>` + '取消審核中' + `</td>`
//                                        	 t[i+2].innerHTML += `<td>`+`<button>` + '取消審核' + `</button>`+ `</td>`
//                                             let button = document.getElementsByTagName('button')
//    		 	      	     				 button[button.length-4].value = groupOrder[i].groupOrderNo
//    		 	      	     				 button[button.length-4].onclick = function(){
//                                        		 $.ajax({
//                                                     url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
//                                                     dataType: "json", //返回格式為json
//                                                     async: false, //請求是否非同步，預設為非同步，這也是ajax重要特性
//                                                     type: "post", //請求方式
//                                                     data: { "action": "update", "groupOrderNo": $(this).val(), "groupTimeNo": groupHour.groupTimeNo, "groupOrderTime": groupOrder[i].groupOrderTime,"groupOrderStatus": 0},
//                                                     success: function (groupOrder) {
//                                                     	location.href= location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/listAllGroupOrderByMem.jsp"
//                                                     },
//                                                     error: function () {
//                                                         console.log("失敗")
//                                                     }
                                               
//                                            	 });
//                                        	 }
                                         }
//                                         
//                                         
                                    },
                                    error: function () {
                                        console.log("失敗")
                                    }
                                });
                            },
                            error: function () {
                                console.log("失敗")
                            }
                        });



                    }

                },
                error: function () {
                    console.log("失敗")
                }
            });

        });