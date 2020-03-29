<!--header start-->
<div class="index-header">
    <img src="${request.contextPath}/dest/images/logo_03.jpg" class="header-tale">
    <!--welcome start-->
    <div class="main-welcome">
        <div class="main-login">
            <a href="javascript:;">欢迎<span>strome</span></a>
            <a href="${request.contextPath}/dest/templates/resetpassword.html" target="control">修改密码</a>
            <a href="javascript:;">退出</a>
            <a href="javascript:;">访问店铺</a>
            <@shiro.hasPermission name="goods">
                <a href="createUser.jsp">Create a new site</a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="oodsetting">
                <a href="createUser.jsp">Create a new goods</a>
            </@shiro.hasPermission>

        </div>
    </div>
    <!--welcome end-->
</div>
<!--header end-->