$(function() {
    //-全选
    $("input[name='b']").click(function() {
        //判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
        if ($(this).is(":checked")) {
            $("input[name='a']").prop("checked", true);
        } else {
            $("input[name='a']").prop("checked", false);
        }
    });

    $("input[name='a']").click(function() {
        if (!$(this).is(":checked")) {
            $("input[name='b']").prop("checked", false);
        }
    });

    //    按钮切换功能
    $('.common-bbtn').click(function() {
        if ($(this).css('left') == "32px") {
            $(this).animate({
                left: '-3px'
            }, 400, function() {
                $(this).parents('.common-box').addClass('common-box-active');
                $(this).siblings('span').text("OFF");
                $(this).attr('data-value', 0);
            });
            return false;
        }
        $(this).animate({
            left: '32px'
        }, 400, function() {
            $(this).attr('data-value', 1);
            $(this).parents('.common-box').removeClass('common-box-active');
            $(this).siblings('span').text("ON");
        });

    });
    /*图片上传弹窗*/
    $(".PictureList li").attr("flag", 0);
    $(".PictureList li").on('click', function() {
        if ($(this).attr("flag") == 1) {
            $(this).attr("flag", 0);
            $(this).find("span").css("display", "none");
        } else if ($(this).attr("flag") == 0) {
            $(this).attr("flag", 1);
            $(this).find("span").css("display", "block");
        }
    });

    //时间开始结束
    laydate.render({
        elem: '#startTime',
        type: 'datetime'
    });

    laydate.render({
        elem: '#endTime',
        type: 'datetime'
    });

    lay('.laydateTime').each(function() {
        laydate.render({
            elem: this,
            format: 'HH:mm:ss',
            type: 'time',
            trigger: 'click'
        });
    });
});

/*电话号码验证*/
$("#PhoneNumber").on('blur', function() {
    var PHONE = $(this).val();
    var isMobile = /^(?:13\d|15\d)\d{5}(\d{3}|\*{3})$/;
    var isPhone = /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
    if (PHONE == "") {
        $(this).next().text("");
        $(this).next().text("不能为空").css('display', 'block');
        return false;
    } else if (!isMobile.test(PHONE) && !isPhone.test(PHONE)) {
        $(this).next().text("");
        $(this).next().text("请正确填写电话号码，例如:13415764179或0321-4816048").css('display', 'block');
        return false;
    } else {
        $(this).next().css('display', 'none');

    }

});

/*电子信箱验证*/
$("#ElectronicMailbox").on('blur', function() {
    var EMAIL = $(this).val();
    if (EMAIL == "") {
        $(this).next().text("");
        $(this).next().text("不能为空").css('display', 'block');
        return false;
    } else if (!EMAIL.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
        $(this).next().text("");
        $(this).next().text("邮箱格式不正确！请重新输入").css('display', 'block');
        return false;
    } else {
        $(this).next().css('display', 'none');

    }

});

function checkPassWordIsValid(value) {
    var reg1 = new RegExp(/^[0-9A-Za-z]+$/);
    return reg1.test(value);
}

//判断图片是否存在
function isImageExist(url) {
    if (url.length == 0) {
        return false;
    }
    var isExist = true;
    $.ajax({
        url: url,
        type: 'GET',
        async: false,
        timeout: 1000,
        success: function() {
        },
        error: function() {
            isExist = false;
        }
    });
    return isExist;
}

function compareTime(startTime, endTime) {
    if (startTime == null || startTime === '' || endTime == null || endTime === "") {
        return false;
    }
    var d1 = new Date(startTime.replace(/-/g, "\/"));
    var d2 = new Date(endTime.replace(/-/g, "\/"));
    return d1 <= d2;
}

function laydateBind() {
    lay('.laydateTime').each(function() {
        laydate.render({
            elem: this,
            format: 'HH:mm:ss',
            type: 'time',
            trigger: 'click'
        });
    });
}

