        function init() {
            let now = new Date();
            let year = document.getElementById('year');
            let month = document.getElementById('month');
            //預設為當天年份和月份
            showDate(now.getFullYear(), now.getMonth());
            year.value = now.getFullYear();
            month.value = now.getMonth();


            //年份或月份有改變時，ySelect.value是字串，要轉成整數
            year.addEventListener('change', function () {
                showDate(year.value, month.value);
                let groupClassNo = sessionStorage.getItem('groupClassNo')
                let className = sessionStorage.getItem('className')
                let groupMax = sessionStorage.getItem('groupMax')
                let groupMin = sessionStorage.getItem('groupMin')

                $.ajax({
                    url: location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                    dataType: "json", //返回格式為json
                    async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                    type: "post", //請求方式
                    data: { "action": "listGroupHours_ByGroupClassNo_A", "groupClassNo": groupClassNo },
                    success: function (groupHour) {
                        // 		  		    	console.log(groupHour)
                        let space = document.getElementsByTagName('td');
                        let arraDaysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];// td陣列
                        let year = document.getElementById('year');
                        let month = document.getElementById('month');
                        let date = new Date(year.value, month.value, 1);
                        let day = date.getDay();
                        // 		 	            console.log(month.value)
                        for (let i = 0; i < groupHour.length; i++) {
                            //		  		    		 console.log(new Date(groupHour[i].groupDate).getFullYear())
                            for (let j = 0; j < arraDaysOfMonth[month.value]; j++) {
                                if ((year.value === new Date(groupHour[i].groupDate).getFullYear().toString()) && (month.value === new Date(groupHour[i].groupDate).getMonth().toString()) && ((j + 1) === new Date(groupHour[i].groupDate).getDate())) {
                                    space[day + j].innerHTML += `<br/>` + className + `<br/>`
                                    if (groupHour[i].courseStatus === -1) {
                                        space[day + j].innerText += '已取消'
                                    } else if (groupHour[i].courseStatus === 0) {
                                        let now = new Date().getTime()
                                        let registStartTime = groupHour[i].registStartTime + ' 00:00:00'
                                        let registEndTime = groupHour[i].registEndTime + ' 00:00:00'
                                        let start = new Date(registStartTime).getTime()
                                        let end = new Date(registEndTime).getTime()
                                        if ((now > start) && (now < end)) {
                                            $.ajax({
                                                url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
                                                dataType: "json", //返回格式為json
                                                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                                                type: "post", //請求方式
                                                data: { "action": "listGroupOrderCount_ByGroupTimeNo_A", "groupTimeNo": groupHour[i].groupTimeNo, "groupOrderStatus": 0 },
                                                success: function (groupOrder) {
                                                    let count = groupOrder.length
                                                    if (count < groupMax) {
                                                        space[day + j].innerHTML += groupHour[i].groupStartingTime + `<br/>`
                                                        space[day + j].innerHTML += '報名中' + `<br/>`
                                                        space[day + j].innerHTML += `<button>` + '預約' + `</button>`
                                                        let button = document.getElementsByTagName('button')
                                                        button[button.length - 1].value = groupHour[i].groupTimeNo
//                                                        sessionStorage.setItem('groupDate', groupHour[i].groupDate)
//                                                        sessionStorage.setItem('groupStartingTime', groupHour[i].groupStartingTime)
                                                        button[button.length - 1].onclick = reserve
                                                    } else {
                                                        space[day + j].innerHTML += groupHour[i].groupStartingTime + `<br/>`
                                                        space[day + j].innerHTML += '已額滿' + `<br/>`
                                                    }
                                                },
                                                error: function () {
                                                    console.log("失敗")
                                                }
                                            });

                                        } else if (now < start) {
                                            space[day + j].innerHTML += '敬請期待'
                                        } else {
                                            space[day + j].innerHTML += '已結束'
                                        }

                                    } else {
                                        space[day + j].innerText += '已結束'
                                    }
                                }
                            }

                        }
                    },
                    error: function () {
                        console.log("失敗")
                    }
                });
            });
            month.addEventListener('change', function () {
                showDate(year.value, month.value);
                let groupClassNo = sessionStorage.getItem('groupClassNo')
                let className = sessionStorage.getItem('className')
                let groupMax = sessionStorage.getItem('groupMax')
                let groupMin = sessionStorage.getItem('groupMin')

                $.ajax({
                    url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                    dataType: "json", //返回格式為json
                    async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                    type: "post", //請求方式
                    data: { "action": "listGroupHours_ByGroupClassNo_A", "groupClassNo": groupClassNo },
                    success: function (groupHour) {
                        // 		  		    	console.log(groupHour)
                        let space = document.getElementsByTagName('td');
                        let arraDaysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];// td陣列
                        let year = document.getElementById('year');
                        let month = document.getElementById('month');
                        let date = new Date(year.value, month.value, 1);
                        let day = date.getDay();
                        // 		 	            console.log(month.value)
                        for (let i = 0; i < groupHour.length; i++) {
                            //		  		    		 console.log(new Date(groupHour[i].groupDate).getFullYear())
                            for (let j = 0; j < arraDaysOfMonth[month.value]; j++) {
                                if ((year.value === new Date(groupHour[i].groupDate).getFullYear().toString()) && (month.value === new Date(groupHour[i].groupDate).getMonth().toString()) && ((j + 1) === new Date(groupHour[i].groupDate).getDate())) {
                                    space[day + j].innerHTML += `<br/>` + className + `<br/>`
                                    if (groupHour[i].courseStatus === -1) {
                                        space[day + j].innerText += '已取消'
                                    } else if (groupHour[i].courseStatus === 0) {
                                        let now = new Date().getTime()
                                        let registStartTime = groupHour[i].registStartTime + ' 00:00:00'
                                        let registEndTime = groupHour[i].registEndTime + ' 00:00:00'
                                        let start = new Date(registStartTime).getTime()
                                        let end = new Date(registEndTime).getTime()
                                        if ((now > start) && (now < end)) {
                                            $.ajax({
                                                url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
                                                dataType: "json", //返回格式為json
                                                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                                                type: "post", //請求方式
                                                data: {"action": "listGroupOrderCount_ByGroupTimeNo_A", "groupTimeNo": groupHour[i].groupTimeNo, "groupOrderStatus": 0 },
                                                success: function (groupOrder) {
                                                    let count = groupOrder.length
                                                    if (count < groupMax) {
                                                        space[day + j].innerHTML += groupHour[i].groupStartingTime + `<br/>`
                                                        space[day + j].innerHTML += '報名中' + `<br/>`
                                                        space[day + j].innerHTML += `<button class="btn_select">` + '預約' + `</button>`
                                                        let button = document.getElementsByTagName('button')
                                                        button[button.length - 1].value = groupHour[i].groupTimeNo
//                                                        sessionStorage.setItem('groupDate', groupHour[i].groupDate)
//                                                        sessionStorage.setItem('groupStartingTime', groupHour[i].groupStartingTime)
                                                        button[button.length - 1].onclick = reserve
                                                    } else {
                                                        space[day + j].innerHTML += groupHour[i].groupStartingTime + `<br/>`
                                                        space[day + j].innerHTML += '已額滿' + `<br/>`
                                                    }
                                                },
                                                error: function () {
                                                    console.log("失敗")
                                                }
                                            });

                                        } else if (now < start) {
                                            space[day + j].innerHTML += '敬請期待'
                                        } else {
                                            space[day + j].innerHTML += '已結束'
                                        }

                                    } else {
                                        space[day + j].innerText += '已結束'
                                    }
                                }
                            }

                        }
                    },
                    error: function () {
                        console.log("失敗")
                    }
                });
            });

        }

        window.addEventListener('load', function () {
            init()
            showDate(year.value, month.value);
            let groupClassNo = sessionStorage.getItem('groupClassNo')
            let className = sessionStorage.getItem('className')
            let groupMax = sessionStorage.getItem('groupMax')
            let groupMin = sessionStorage.getItem('groupMin')
            let name = document.getElementById('name')
            name.innerText = className
            $.ajax({
                url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/groupclass/groupClass.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { "action": "listGroupHours_ByGroupClassNo_A", "groupClassNo": groupClassNo },
                success: function (groupHour) {
                    //		  		    	console.log(groupHour)
                    let space = document.getElementsByTagName('td');
                    let arraDaysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];// td陣列
                    let year = document.getElementById('year');
                    let month = document.getElementById('month');
                    let date = new Date(year.value, month.value, 1);
                    let day = date.getDay();
                    //		 	            console.log(month.value)
                    for (let i = 0; i < groupHour.length; i++) {
                        //	  		    		 console.log(new Date(groupHour[i].groupDate).getFullYear())
                        for (let j = 0; j < arraDaysOfMonth[month.value]; j++) {
                            if ((year.value === new Date(groupHour[i].groupDate).getFullYear().toString()) && (month.value === new Date(groupHour[i].groupDate).getMonth().toString()) && ((j + 1) === new Date(groupHour[i].groupDate).getDate())) {
                                space[day + j].innerHTML += `<br/>` + className + `<br/>`
                                if (groupHour[i].courseStatus === -1) {
                                    space[day + j].innerText += '已取消'
                                } else if (groupHour[i].courseStatus === 0) {
                                    let now = new Date().getTime()
                                    let registStartTime = groupHour[i].registStartTime + ' 00:00:00'
                                    let registEndTime = groupHour[i].registEndTime + ' 00:00:00'
                                    let start = new Date(registStartTime).getTime()
                                    let end = new Date(registEndTime).getTime()
                                    if ((now > start) && (now < end)) {
                                        $.ajax({
                                            url:  location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/groupOrder.do", //請求的url地址
                                            dataType: "json", //返回格式為json
                                            async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                                            type: "post", //請求方式
                                            data: { "action": "listGroupOrderCount_ByGroupTimeNo_A", "groupTimeNo": groupHour[i].groupTimeNo, "groupOrderStatus": 0  },
                                            success: function (groupOrder) {
                                                let count = groupOrder.length
                                                if (count < groupMax) {
                                                    space[day + j].innerHTML += groupHour[i].groupStartingTime + `<br/>`
                                                    space[day + j].innerHTML += '報名中' + `<br/>`
                                                    space[day + j].innerHTML += `<button>` + '預約' + `</button>`
                                                    let button = document.getElementsByTagName('button')
                                                    button[button.length - 1].value = groupHour[i].groupTimeNo
//                                                    sessionStorage.setItem('groupDate', groupHour[i].groupDate)
//                                                    sessionStorage.setItem('groupStartingTime', groupHour[i].groupStartingTime)
                                                    button[button.length - 1].onclick = reserve
                                                } else {
                                                    space[day + j].innerHTML += groupHour[i].groupStartingTime + `<br/>`
                                                    space[day + j].innerHTML += '已額滿' + `<br/>`
                                                }
                                            },
                                            error: function () {
                                                console.log("失敗")
                                            }
                                        });

                                    } else if (now < start) {
                                        space[day + j].innerHTML += '敬請期待'
                                    } else {
                                        space[day + j].innerHTML += '已結束'
                                    }

                                } else {
                                    space[day + j].innerText += '已結束'
                                }
                            }
                        }

                    }
                },
                error: function () {
                    console.log("失敗")
                }
            });
        });

        function showDate(year, month) {
            let space = document.getElementsByTagName('td');
            let arraDaysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];// td陣列
            let date = new Date(year, month, 1);
            let day = date.getDay();
            if (isLeapYear(year))
                arraDaysOfMonth[1] = 29;
            for (let j = 0; j < space.length - 1; j++) {
                space[j].innerText = " ";
            }
            for (let j = 0; j < arraDaysOfMonth[month]; j++) {
                space[day + j].innerText = (j + 1);
            }
        }


        // 函式: 判斷是否為閏年
        function isLeapYear(year) {
            if (year % 4 === 0 && year % 100 !== 0) {
                return true;
            } else if (year % 400 === 0) {
                return true;
            } else {
                return false;
            }
        }

        function reserve() {
            $.ajax({
                url: location.pathname.substring(0,location.pathname.indexOf('/',4))+ "/front-end/grouphour/groupHour.do", //請求的url地址
                dataType: "json", //返回格式為json
                async: true, //請求是否非同步，預設為非同步，這也是ajax重要特性
                type: "post", //請求方式
                data: { 'action': 'getOne_For_Display','groupTimeNo': $(this).val() },
                success: function (groupHour) {
                    console.log("成功")
					sessionStorage.setItem('groupDate', groupHour.groupDate)
                    sessionStorage.setItem('groupStartingTime', groupHour.groupStartingTime)
//                    console.log(groupHour)
//                    sessionStorage.setItem('memId', groupOrder.memId)
                    sessionStorage.setItem('groupTimeNo', groupHour.groupTimeNo)
//                    sessionStorage.setItem('groupOrderTime', groupOrder.groupOrderTime)
                    location.href = location.pathname.substring(0,location.pathname.indexOf('/',4))+"/front-end/grouporder/listGroupOrderInfo2.html"
                },
                error: function () {
                    console.log("失敗")
                }
            });
        }