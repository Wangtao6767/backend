<#if data?? && data?size gt 0>
    <div class="fixed">
        <div class="list-fenye">
            <#assign pageCount=(pages!0)>
            <#assign pageNo=(pageNum!0)>
            <#assign totalCount=(total!0)>
            <input id="page_curPage" value="#{pageNo}" style="display: none"/>
            <span>共#{totalCount}条记录</span>
            <span><em>#{pageNo}/#{pageCount}</em>页</span>
            <ul class="pagination">
                <!-- 上一页处理 -->
                <#if 1==pageNo>
                    <li class="disabled"><a href="javascript:;">&laquo;</a></li>
                <#else>
                    <li><a onclick="turnOverPage(#{pageNo - 1})">&laquo;</a></li>
                </#if>

                <!-- 如果前面页数过多,显示... -->
                <#assign start=1>
                <#if (pageNo > 4)>
                    <#assign start=(pageNo - 1)>
                    <li><a onclick="turnOverPage(1)">1</a></li>
                    <li><a onclick="turnOverPage(2)">2</a></li>
                    <li class="disabled"><a href="javascript:;">&hellip;</a></li>
                </#if>

                <!-- 显示当前页号和它附近的页号 -->
                <#assign end=(pageNo + 1)>
                <#if (end > pageCount)>
                    <#assign end=pageCount>
                </#if>
                <#list start..end as i>
                    <#if (pageNo==i)>
                        <li class="active"><a onclick="turnOverPage(#{i})">#{i}</a></li>
                    <#elseif i gt 0>
                        <li><a onclick="turnOverPage(#{i})">#{i}</a></li>
                    </#if>
                </#list>

                <!-- 如果后面页数过多,显示... -->
                <#if (end < pageCount - 2)>
                    <li class="disabled"><a href="javascript:;">&hellip;</a></li>
                </#if>
                <#if (end < pageCount - 1)>
                    <li><a onclick="turnOverPage(#{pageCount - 1})">#{pageCount - 1}</a></li>
                </#if>
                <#if (end < pageCount)>
                    <li><a onclick="turnOverPage(#{pageCount})">#{pageCount}</a></li>
                </#if>

                <!-- 下一页处理 -->
                <#if (pageNo == pageCount)>
                    <li class="disabled"><a href="javascript:;">&raquo;</a></li>
                <#else>
                    <li><a onclick="turnOverPage(#{pageNo + 1})">&raquo;</a></li>
                </#if>
            </ul>
            <span>跳转到<input type="text" id="pageNo"></span>
            <a onclick="jumpTo(#{pageCount})" class="btn btn-primary" id="pageJump">确定</a>
        </div>
    </div>
</#if>