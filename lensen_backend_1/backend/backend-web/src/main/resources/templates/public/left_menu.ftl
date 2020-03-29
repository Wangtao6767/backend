<!--nav start-->
<div class="layout-nav">
    <div>
        <a href="welcome.html" target="control" class="nav-control active">欢迎登录</a>
    </div>
    <#if canShowRoles?? && (canShowRoles?size > 0)>
        <#list canShowRoles as pagePO>
            <div>
                <#if pagePO.id == 1>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 2>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/goods.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 3>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 4>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 5>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 6>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 7>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 8>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 9>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 10>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 11>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                <#elseif pagePO.id == 12>
                    <a class="nav-control"><img src="${request.contextPath}/dest/images/f.png">${pagePO.designation!''}
                    </a>
                </#if>
                <ul class="nav-list">
                    <#list canShowRoles as subpagePO>
                        <#if pagePO.id == subpagePO.parentid>
                            <a href="${request.contextPath}/${subpagePO.url}" target="control"><img src="${request.contextPath}/dest/images/jiantou.png">
                                <li>${subpagePO.designation}</li>
                            </a>
                        </#if>
                    </#list>
                </ul>
            </div>
        </#list>
    </#if>
</div>
<!--nav end-->