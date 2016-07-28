// 存放主要交互逻辑的js代码
// JavaScript模块化
var miaosha = {
    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return "/miaosha/time/now";
        },
        exposer: function (miaoshaId) {
            return "/miaosha/" + miaoshaId + "/expose";
        },
        execution: function (miaoshaId, md5) {
            return "/miaosha/" + miaoshaId + "/" + md5 + "/execute";
        }
    },


    // 判断手机号码是否合法
    validPhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    handleMiaosha: function (miaoshaId, node) {
        // console.log('handleMiaosha function...');
        //在node节点中隐藏一个执行秒杀的按钮
        node.hide().html('<button class="btn btn-primary btn-lg" id="executeBtn">开始秒杀</button>');
        $.post(miaosha.URL.exposer(miaoshaId), {}, function (result) {
            //function是一个回调函数，在controller执行完exposeurl之后调用
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['isExposed'] == true) {
                    //若开启秒杀，则将执行秒杀的url post给controller
                    var md5 = exposer['md5'];
                    var miaoshaUrl = miaosha.execution(miaoshaId, md5);
                    console.log("miaoshaUrl:" + miaoshaUrl);
                    //给按钮绑定一个一次点击事件
                    $('#executeBtn').one('click', function () {
                        //开始执行秒杀请求
                        //1.先禁用按钮，避免重复点击按钮发送多个请求
                        $(this).addClass('disabled');
                        //2.用post方法提交秒杀请求，被MiaoshaController接收，执行秒杀
                        $.post(miaoshaUrl, {}, function (result) {
                            if (result && result['success']) {
                                var miaoshaExecution = result['data'];
                                var state = miaoshaExecution['state'];
                                var stateInfo = miaoshaExecution['stateInfo'];
                                node.html('<span class="label lable-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    //这里调用show()方法是因为前面line: 18把node节点隐藏了，所以调用show才能将节点显示出来。
                    //绑定的点击事件完成后，调用show方法显示。
                    node.show();
                } else {
                    //若未开启秒杀，可能是因为系统时间与用户时间不一致，则重新倒计时
                    var currentTime = exposer['currentTime'];
                    var startTime = exposer['startTime'];
                    var endTime = exposer['endTime'];
                    miaosha.countDown(miaoshaId, startTime, endTime, currentTime);
                }
            } else {
                console.log('result: ' + result);
            }
        });
    },

    countDown: function (miaoshaId, startTime, endTime, currentTime) {
        //console.log("miaoshaId:" + miaoshaId + ",startTime:" + startTime + ",endTime:" + endTime + ",currentTime:" + currentTime);
        //获得计时面板
        var miaoshaBox = $('#miaosha-box');
        if (currentTime > endTime) {
            miaoshaBox.html('miaosha end...');
        } else if (currentTime < startTime) {
            console.log("currentTime < startTime...");
            var time = new Date(startTime + 1000);
            miaoshaBox.countdown(time, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 : %H时 : %M分 : %S秒');
                miaoshaBox.html(format);
            }).on('finishCountDown', function () {
                // 计时完成，获取秒杀地址，执行秒杀
                miaosha.handleMiaosha(miaoshaId, miaoshaBox);
            });
        } else {
            //秒杀开始
            miaosha.handleMiaosha(miaoshaId, miaoshaBox);
        }
    },

    //详情页秒杀逻辑
    detail: {
        //判断phone number是否合法
        init: function (params) {
            //从cookie中获取手机号
            var phone = $.cookie('userPhone');
            //验证手机号码是否有效
            if (!miaosha.validPhone(phone)) {
                //控制phoneModal节点（detail中的弹出层）的输出样式
                var phoneModal = $('#phoneModal');
                phoneModal.modal({
                    show: true,             //显示弹出层
                    backdrop: 'static',     //禁止其他位置关闭
                    keyboard: false         //禁止键盘关闭
                });
                $('#phoneButton').click(function () {
                    var inputPhone = $('#phoneKey').val();
                    console.log("inputPhone:" + inputPhone);
                    if (miaosha.validPhone(inputPhone)) {
                        $.cookie('userPhone', inputPhone, {expires: 7, path: '/miaosha'});
                        window.location.reload();
                    } else {
                        $('#phoneMessage').hide().html('<label class="label label-danger">phone namber error...</label>').show(300);
                    }
                });
            }
            //cookie 中phone number有效，则计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var miaoshaId = params['miaoshaId'];
            $.get(miaosha.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var currentTime = result['data'];
                    console.log("currentTime=" + currentTime);
                    miaosha.countDown(miaoshaId, startTime, endTime, currentTime);
                } else {
                    console.log("result=" + result);
                    alert("result:" + result);
                }
            });

        }
    }

};