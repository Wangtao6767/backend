<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>违法鸣笛后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--引入bootstrap-->
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/public/css/plus/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/style/admin/LoginPage.css"/>
    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>

    <![endif]-->
</head>

<body class="login-back">
<!--login start-->
<div class="login">
    <input type="text" class="form-control login-user same" name="user_name" id="user_name" placeholder="请输入账户">
    <input type="password" class="form-control login-password same" name="password" id="password" pattern="[\S]{6}[\S]*" title="密码不少于6个字符" placeholder="请输入密码">
    <div class="login-validation">
        <input type="text" class="form-control validation same" placeholder="请输入验证码" required pattern="[A-z0-9]{4}" title="验证码为4个字符" autocomplete="off" id="code">
        <img src="verificationCode" class="validation-chang" id="codeImage">
    </div>
    <a href="javascript:;" class="login-submit same">登录</a>
</div>
<!--弹出确认框-->
<div class="modal fade" id="loginSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div align="center">
                    登录成功!
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="loginSuccessBtn">确定</button>
            </div>
        </div>
    </div>
</div>
<!--弹出确认框-->

<!--login end-->
<script src="${request.contextPath}/public/js/script/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/js/script/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/js/script/layer/layer.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/js/script/msg.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/scripts/admin/login.js" type="text/javascript" charset="utf-8"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script type="text/javascript">
    if (self.frameElement && self.frameElement.tagName == "IFRAME") {
        window.parent.location.href = "/login";
    }
</script>
</body>
</html>