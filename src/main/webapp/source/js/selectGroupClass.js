
        $("#btn1").click(function () {
            $.ajax({
                url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { "action": "getOne_For_Display", "groupClassNo": "5001" },
                success: function (groupClass) {
                	console.log("成功")
                    console.log(groupClass)
//                     document.cookie = 'groupClassNo=' + groupClass.groupClassNo
//                     document.cookie = 'proId=' + groupClass.proId
//                     document.cookie = 'classTypeNo=' + groupClass.classTypeNo
//                     document.cookie = 'className=' + groupClass.className
//                     document.cookie = 'loc=' + groupClass.loc
//                     document.cookie = 'groupMax=' + groupClass.groupMax
//                     document.cookie = 'groupMin=' + groupClass.groupMin
//                     document.cookie = 'groupClassPrice=' + groupClass.groupClassPrice
//                     document.cookie = 'groupClassDetail=' + groupClass.groupClassDetail
//                     document.cookie = 'groupClassPic=' + groupClass.groupClassPic
//                     let cookie =  document.cookie
//                     console.log(cookie)
					sessionStorage.setItem('groupClassNo',groupClass.groupClassNo)
					sessionStorage.setItem('proId', groupClass.proId)
					sessionStorage.setItem('classTypeNo', groupClass.classTypeNo)
					sessionStorage.setItem('className', groupClass.className)
					sessionStorage.setItem('loc', groupClass.loc)
					sessionStorage.setItem('groupMax', groupClass.groupMax)
					sessionStorage.setItem('groupMin', groupClass.groupMin)
					sessionStorage.setItem('groupClassPrice', groupClass.groupClassPrice)
					sessionStorage.setItem('groupClassDetail', groupClass.groupClassDetail)
					sessionStorage.setItem('groupClassPic', groupClass.groupClassPic)
                	location.href=location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/listGroupClassInfo2.html"
                },
                error: function () {
                    console.log("失敗")
                }
            })
        })
        $("#btn2").click(function () {
        	$.ajax({
                url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { "action": "getOne_For_Display", "groupClassNo": "5002" },
                success: function (groupClass) {
                	console.log("成功")
                    console.log(groupClass)
//                     document.cookie = 'groupClassNo=' + groupClass.groupClassNo
//                     document.cookie = 'proId=' + groupClass.proId
//                     document.cookie = 'classTypeNo=' + groupClass.classTypeNo
//                     document.cookie = 'className=' + groupClass.className
//                     document.cookie = 'loc=' + groupClass.loc
//                     document.cookie = 'groupMax=' + groupClass.groupMax
//                     document.cookie = 'groupMin=' + groupClass.groupMin
//                     document.cookie = 'groupClassPrice=' + groupClass.groupClassPrice
//                     document.cookie = 'groupClassDetail=' + groupClass.groupClassDetail
//                     document.cookie = 'groupClassPic=' + groupClass.groupClassPic
//                     let cookie =  document.cookie
//                     console.log(cookie)
					sessionStorage.setItem('groupClassNo',groupClass.groupClassNo)
					sessionStorage.setItem('proId', groupClass.proId)
					sessionStorage.setItem('classTypeNo', groupClass.classTypeNo)
					sessionStorage.setItem('className', groupClass.className)
					sessionStorage.setItem('loc', groupClass.loc)
					sessionStorage.setItem('groupMax', groupClass.groupMax)
					sessionStorage.setItem('groupMin', groupClass.groupMin)
					sessionStorage.setItem('groupClassPrice', groupClass.groupClassPrice)
					sessionStorage.setItem('groupClassDetail', groupClass.groupClassDetail)
					sessionStorage.setItem('groupClassPic', groupClass.groupClassPic)
                	location.href=location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/listGroupClassInfo2.html"
                },
                error: function () {
                    console.log("失敗")
                }
            })
        })
        $("#btn3").click(function () {
        	$.ajax({
                url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { "action": "getOne_For_Display", "groupClassNo": "5003" },
                success: function (groupClass) {
                	console.log("成功")
                    console.log(groupClass)
//                     document.cookie = 'groupClassNo=' + groupClass.groupClassNo
//                     document.cookie = 'proId=' + groupClass.proId
//                     document.cookie = 'classTypeNo=' + groupClass.classTypeNo
//                     document.cookie = 'className=' + groupClass.className
//                     document.cookie = 'loc=' + groupClass.loc
//                     document.cookie = 'groupMax=' + groupClass.groupMax
//                     document.cookie = 'groupMin=' + groupClass.groupMin
//                     document.cookie = 'groupClassPrice=' + groupClass.groupClassPrice
//                     document.cookie = 'groupClassDetail=' + groupClass.groupClassDetail
//                     document.cookie = 'groupClassPic=' + groupClass.groupClassPic
//                     let cookie =  document.cookie
//                     console.log(cookie)
					sessionStorage.setItem('groupClassNo',groupClass.groupClassNo)
					sessionStorage.setItem('proId', groupClass.proId)
					sessionStorage.setItem('classTypeNo', groupClass.classTypeNo)
					sessionStorage.setItem('className', groupClass.className)
					sessionStorage.setItem('loc', groupClass.loc)
					sessionStorage.setItem('groupMax', groupClass.groupMax)
					sessionStorage.setItem('groupMin', groupClass.groupMin)
					sessionStorage.setItem('groupClassPrice', groupClass.groupClassPrice)
					sessionStorage.setItem('groupClassDetail', groupClass.groupClassDetail)
					sessionStorage.setItem('groupClassPic', groupClass.groupClassPic)
                	location.href=location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/listGroupClassInfo2.html"
                },
                error: function () {
                    console.log("失敗")
                }
            })
        })
        $("#btn4").click(function () {
        	$.ajax({
                url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { "action": "getOne_For_Display", "groupClassNo": "5004" },
                success: function (groupClass) {
                	console.log("成功")
                    console.log(groupClass)
//                     document.cookie = 'groupClassNo=' + groupClass.groupClassNo
//                     document.cookie = 'proId=' + groupClass.proId
//                     document.cookie = 'classTypeNo=' + groupClass.classTypeNo
//                     document.cookie = 'className=' + groupClass.className
//                     document.cookie = 'loc=' + groupClass.loc
//                     document.cookie = 'groupMax=' + groupClass.groupMax
//                     document.cookie = 'groupMin=' + groupClass.groupMin
//                     document.cookie = 'groupClassPrice=' + groupClass.groupClassPrice
//                     document.cookie = 'groupClassDetail=' + groupClass.groupClassDetail
//                     document.cookie = 'groupClassPic=' + groupClass.groupClassPic
//                     let cookie =  document.cookie
//                     console.log(cookie)
					sessionStorage.setItem('groupClassNo',groupClass.groupClassNo)
					sessionStorage.setItem('proId', groupClass.proId)
					sessionStorage.setItem('classTypeNo', groupClass.classTypeNo)
					sessionStorage.setItem('className', groupClass.className)
					sessionStorage.setItem('loc', groupClass.loc)
					sessionStorage.setItem('groupMax', groupClass.groupMax)
					sessionStorage.setItem('groupMin', groupClass.groupMin)
					sessionStorage.setItem('groupClassPrice', groupClass.groupClassPrice)
					sessionStorage.setItem('groupClassDetail', groupClass.groupClassDetail)
					sessionStorage.setItem('groupClassPic', groupClass.groupClassPic)
                	location.href=location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/listGroupClassInfo2.html"
                },
                error: function () {
                    console.log("失敗")
                }
            })
        })
        $("#btn5").click(function () {
        	$.ajax({
                url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { "action": "getOne_For_Display", "groupClassNo": "5005" },
                success: function (groupClass) {
                	console.log("成功")
                    console.log(groupClass)
//                     document.cookie = 'groupClassNo=' + groupClass.groupClassNo
//                     document.cookie = 'proId=' + groupClass.proId
//                     document.cookie = 'classTypeNo=' + groupClass.classTypeNo
//                     document.cookie = 'className=' + groupClass.className
//                     document.cookie = 'loc=' + groupClass.loc
//                     document.cookie = 'groupMax=' + groupClass.groupMax
//                     document.cookie = 'groupMin=' + groupClass.groupMin
//                     document.cookie = 'groupClassPrice=' + groupClass.groupClassPrice
//                     document.cookie = 'groupClassDetail=' + groupClass.groupClassDetail
//                     document.cookie = 'groupClassPic=' + groupClass.groupClassPic
//                     let cookie =  document.cookie
//                     console.log(cookie)
					sessionStorage.setItem('groupClassNo',groupClass.groupClassNo)
					sessionStorage.setItem('proId', groupClass.proId)
					sessionStorage.setItem('classTypeNo', groupClass.classTypeNo)
					sessionStorage.setItem('className', groupClass.className)
					sessionStorage.setItem('loc', groupClass.loc)
					sessionStorage.setItem('groupMax', groupClass.groupMax)
					sessionStorage.setItem('groupMin', groupClass.groupMin)
					sessionStorage.setItem('groupClassPrice', groupClass.groupClassPrice)
					sessionStorage.setItem('groupClassDetail', groupClass.groupClassDetail)
					sessionStorage.setItem('groupClassPic', groupClass.groupClassPic)
                	location.href=location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/listGroupClassInfo2.html"
                },
                error: function () {
                    console.log("失敗")
                }
            })
        })
