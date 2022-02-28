window.addEventListener('load', function () {
            let groupClassNo = sessionStorage.getItem('groupClassNo')
            let proId = sessionStorage.getItem('proId')
            let className = sessionStorage.getItem('className')
            let loc = sessionStorage.getItem('loc')
            let groupMax = sessionStorage.getItem('groupMax')
            let groupMin = sessionStorage.getItem('groupMin')
            let groupClassPrice = sessionStorage.getItem('groupClassPrice')
            let groupClassDetail = sessionStorage.getItem('groupClassDetail')
            let groupClassPic = sessionStorage.getItem('groupClassPic')
            // ---------------------
            let name = document.getElementById('name')
            let loca = document.getElementById('loca')
            let pro = document.getElementById('pro')
            let price = document.getElementById('price')
            let detail = document.getElementById('detail')
            let pic = document.getElementById('pic')
            // ----------------------
            name.innerText = className
            loca.innerText = 'Loc：' + loc
            price.innerText = 'Price：新台幣' + groupClassPrice + '元'
            detail.innerText = groupClassDetail
            pic.src = 'images/'+ groupClassNo +'.jpg'
             $.ajax({
                 url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                 dataType: "json", //返回格式為json
                 async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                 type: "post", //請求方式
                 data: { "action": "getOnePro_For_Display", "proId": proId },
                 success: function (proVO) {
                 	console.log("成功")
             		$.ajax({
	                 url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
	                 dataType: "json", //返回格式為json
	                 async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
	                 type: "post", //請求方式
	                 data: { "action": "getOneProName_For_Display", "work_id": proVO.workId },
	                 success: function (workerVO) {
	                 	console.log("成功")
	             		pro.innerText = 'Personal Trainer：'+ workerVO.w_name 
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
            
        });
        $('#selectTime').click(function () {
            location.href = location.pathname.substring(0,location.pathname.indexOf('/',4))+ "/front-end/grouphour/selectGroupHour3.html"
            // let groupClassNo = sessionStorage.getItem('groupClassNo')
            // let className = sessionStorage.getItem('className')
            // $.ajax({
            //     url: "/CFA103G1_12.15/front-end/groupclass/selectGroupHour2.html", //請求的url地址
            //     dataType: "json", //返回格式為json
            //     async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
            //     type: "post", //請求方式
            //     data: { "groupClassNo": groupClassNo, "className": className },
            //     success: function (groupHour) {
            //         console.log("成功")
            //         for (let i = 0; i < groupHour.length; i++) {
            //             console.log(groupHour[i].groupDate)
            //         }

            //     },
            //     error: function () {
            //         console.log("失敗")
            //     }
            // });
        });