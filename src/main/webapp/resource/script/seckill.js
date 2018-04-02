let seckill = {
    URL: {
        now: function () {
            return "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return "/seckill/" + seckillId + "/exposer";
        },
        execution: function (seckillId, md5) {
            return "/seckill/" + seckillId + "/" + md5 + "/execution";
        }
    },
    validPhone: function (phone) {
        return !!(phone && phone.length === 11 && !isNaN(phone));

    },
    //详情页秒杀逻辑
    detail: {
        init: function (params) {
            let phone = $.cookie('userPhone');
            if (!seckill.validPhone(phone)) {
                let killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    show: true,
                    backdrop: 'static',
                    keyboard: false
                });
                $("#killPhoneBtn").click(function () {
                    let inputPhone = $("#killPhoneKey").val();
                    console.log("inputPhone: " + inputPhone);
                    if (seckill.validPhone(inputPhone)) {
                        $.cookie("userPhone", inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        $("#killPhoneMessage").hide().html(
                            '<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
            let startTime = params['startTime'];
            let endTime = params['endTime'];
            let seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    let nowTime = result['data'];
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result', result);
                    alert('result' + result);
                }
            });
        }
    },
    countDown: function (seckillId, nowTime, startTime, endTime) {
 //       console.log(seckillId + '_das' + nowTime + 'dsa_' + startTime + '_dsa' + endTime);
        let timeBox = $('#seckill-box');
        if (nowTime > endTime) {
            timeBox.html("秒杀结束");
        } else if (nowTime < startTime) {
            let killTime = new Date(startTime);
            timeBox.countdown(killTime, function (event) {
                let format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒 ');
                timeBox.html(format);
            }).on('finish.countdown', function () {
                seckill.handlerSeckill(seckillId, timeBox); //秒杀开始
            });
        } else {
            seckill.handlerSeckill(seckillId, timeBox);
        }
    },

    handlerSeckill: function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        //暴露秒杀地址,控制显示，直行秒杀
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            if (result && result['success']) {
                let exposer = result['data'];
                if (exposer['exposed']) {
                    let md5 = exposer['md5'];
                    let killUrl = seckill.URL.execution(seckillId, md5);
                    console.log("killUrl: " + killUrl);
                    //执行秒杀请求，绑定一个点击事件，避免多次点击服务器收到相同的秒杀请求
                    $('#killPhoneBtn').one('click', function () {
                        $(this).addClass('disabled');
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                let killResult = result['data'];
                                let stateInfo = killResult['stateInfo'];
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    let now = exposer['now'];
                    let start = exposer['start'];
                    let end = exposer['end'];
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result: ' + result);
            }
        });
    }
};