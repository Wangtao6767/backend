$(function() {
    //登录框居中
    $(function() {
        var windowHeight = $(window).height();
        var heignt = (windowHeight - 320) / 2;
        $('.login').css("margin-top", heignt);
    });

    /*        $(function(){
                $.post("login/getBgImgUrl.do",{},function (data){
                    var json=jQuery.parseJSON(data);
                    if (json.isSuccess){
                        if (json.data!=null&&json.data.value!=null) {
                            $("body").css("background-image","url("+json.data.value+")");
                        }
                        $("body").css("background-repeat","no-repeat");
                        $("body").css("with","100%");
                        $("body").css("height","100%");
                    }
                });
            })*/

    $.ajaxSetup({
        async: false
    });

    $(".validation-chang").click(function() {
        $("#codeImage").attr("src", "verificationCode?d=" + new Date().getTime())
    });

    var login = false;
    var hasError = false;
    $(".login-submit").click(function() {
        var username = $("#user_name").val();
        if (username == "") {
            clearContent();
            showContent("请输入正确的用户名!");
            Disappger();
            hasError = true;
            return;
        }
        var password = $("#password").val();
        if (password.length == 0) {
            clearContent();
            showContent("请输入密码!");
            Disappger();
            hasError = true;
            return
        }
        var code = $("#code").val();
        if (code.length == 0) {
            clearContent();
            showContent("验证码不能为空!");
            Disappger();
            hasError = true;
            return
        }
        var param = {};
        param["username"] = username;
        param["password"] = password;
        param["code"] = code;

        if (returnCitySN != null && returnCitySN != '' && returnCitySN["cname"] != null) {
            param["ip_address"] = returnCitySN["cname"];
        }

        var loginResult = notAsyncLogin(param);

        if (null == loginResult.isSuccess || !loginResult.isSuccess) {
            clearContent();
            showContent(loginResult.msg);
            Disappger();
            hasError = true;
        } else {
            clearContent();
            showContent("登录成功");
            Disappger();
            locationNewHref();
            login = true;
        }
    });

    //键盘按下事件
    $(window).keydown(function(event) {
        //当键盘按下回车的时候
        if (event.keyCode == "13") {
            if (hasError) {
                $("#prompt").modal('hide');
                hasError = false;
            } else {
                if (login) {
                    location.href = "index";
                } else {
                    $(".login-submit").click();
                }
            }

        }
    });
});

function notAsyncLogin(param) {
    var loginResult;
    $.ajax({
        type: "POST",
        url: "manager/isLogin",
        data: param,
        dataType: "json",
        async: false,
        success: function(data) {
            loginResult = data;
        }
    });
    return loginResult;
}

//页面弹窗提示
var Until = function popUp(message) {
    var $el = $('<div class="modal fade" id="prompt" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> <div class="modal-dialog" style="width:300px"> <div class="modal-content" style=""><div class="modal-body" style="text-align: center;align:center" id="messContent">' + message + '</div><div class="modal-footer"  style="text-align: center;align:center"> <button type="button" class="btn btn-primary prompt-confirm">确认</button></div> </div> </div></div>');
    if ($('.dialog-cart').length > 0) {
        return false;
    }
    ;
    $(document.body).append($el);
    $('#prompt').modal({backdrop: 'static', keyboard: false});
    $("#prompt").modal('show');
};


var prompt = function(url) {
    $("#prompt").on("click", function() {
        window.location.href = url;
    })
};

//弹窗确认模态框消失
var Disappger = function() {
    $("#prompt").on("click", function() {
        $("#prompt").modal('hide');
    })
};

var locationNewHref = function() {
    $("#prompt").on("click", function() {
        location.href = "index";
    })
};

function clearContent() {
    var obj = $(document.body).find("#messContent");
    if (obj.length == 0) {
        return;
    } else {
        $(obj).html("");
    }
}

function showContent(mess) {
    var obj = $(document.body).find("#messContent");
    if (obj.length == 0) {
        Until(mess);
    } else {
        var thisModal = $(document.body).find("#prompt");
        $(thisModal).modal({backdrop: 'static', keyboard: false});
        $(thisModal).modal('show');
        $(obj).html(mess);
    }
}

