<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>违法鸣笛后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/public/css/plus/common.css"/>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/style/admin/index.css"/>
    <script src="${request.contextPath}/public/js/script/jquery.min.js"></script>
    <style type="text/css">
        .font-initial {
            font: initial;
        }

        .text-align-right {
            text-align: right !important;
        }

        .hidden-element {
            display: none;
        }
    </style>
</head>

<body class="index-back">
<!--header start-->
<div class="index-header">
    <div class="common-image">
        <img src="images/total.png" class="header-tale">
    </div>
    <!--welcome start-->
    <div class="main-welcome">
        <div class="main-login">
            <label>欢迎<span>${userName!''}</span></label>
            <a href="manager/toChangePassword" target="control">修改密码</a>
            <a href="javascript:;" class="loginOutBtn">退出</a>
        </div>
    </div>
    <!--welcome end-->
</div>
<!--header end-->
<!--main strat-->
<div class="main">
    <!--layout start-->
    <div class="layout">
        <!--nav start-->
        <div class="layout-nav">
            <div>
                <a href="static/init" target="control" class="nav-control active">欢迎登录</a>
            </div>
            <#if canShowRoles?? && (canShowRoles?size > 0)>
                <#list canShowRoles as pagePO>
                    <#if pagePO.parentId == 0 >
                        <div>
                            <a class="nav-control"><img src="public/images/${pagePO.imgUrl!''}">${pagePO.designation!''}
                            </a>
                            <ul class="nav-list">
                                <#list canShowRoles as subpagePO>
                                    <#if pagePO.id == subpagePO.parentId>
                                        <a href="${request.contextPath}/${(subpagePO.url)!''}" target="control"><img src="public/images/jiantou.png">
                                            <li>${(subpagePO.designation)!''}</li>
                                        </a>
                                    </#if>
                                </#list>
                            </ul>
                        </div>
                    </#if>
                </#list>
            </#if>
        </div>
        <!--nav end-->
        <!--iframe 框架-->
        <iframe class="layou-template" src="static/init" name="control"></iframe>
        <!--/iframe 框架-->
    </div>
</div>
</body>
<script type="text/javascript">
    function getRootPath() {
        var pathName = window.location.pathname.substring(1);
        var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        return window.location.protocol + '//' + window.location.host + '/' + webName;
    }

    $(function() {
        $('.nav-control').click(function() {
            if ($(this).siblings('.nav-list').css('display') == "block") {
                $(this).siblings('.nav-list').slideUp(300);
                return false;
            }
            $('.nav-control').removeClass('active');
            $(this).addClass('active');
            $(this).siblings('.nav-list').slideDown(300);
        });
        $('.nav-list a').click(function() {
            $('.nav-list a').css({"background": "#29282a", "color": "#939393"});
            $('.nav-list a').find('img').hide();
            $(this).css({"background": "#3d85cc", "color": "#ffffff"});
            $(this).find('img').show();
        });

        // 登录退出
        var webBase = getRootPath();

        $('.loginOutBtn').click(function() {

            if (confirm("确定退出？")) {
                var params = {};
                $.post(webBase + "/manager/logOut", params, function() {
                    window.location.href = webBase + "/login";
                });
            }
        });
    });

    // $(function() {
    // $.post("login/getLogoUrl.do", {}, function(data) {
    //     var json = jQuery.parseJSON(data);
    //     if (json.isSuccess) {
    //         $(".index-header").css("background-image", "url(" + json.data.value + ")");
    //                  $("body").css("background-repeat","no-repeat");
    //                  $("body").css("with","100%");
    //                  $("body").css("height","100%");
    //             }
    // });
    // })
</script>

</html>