<#if pageObject?? && pageObject.pageCount?? && pageObject.currentIndex?? >
    <!--分页器-->
    <div class="fixed">
        <div class="list-fenye">
            <span>共${pageObject.pageCount}</span>
            <span><em>${pageObject.currentIndex} /${pageObject.pageCount}</em>页</span>
            <ul class="pagination">
                <li>
                    <a id="pagination_prev" href="javascript:void(0)">&laquo;</a>
                </li>
                <#assign count = pageObject.pageCount>
                <#list 1..count as t>
                    <#if t == pageObject.currentIndex && t lt 10>
                        <li>
                            <a class="active">${t}</a>
                        </li>
                    <#elseif t lt 10>
                        <li>
                            <a id="to_page" href="javascript:void(0)">${t}</a>
                        </li>
                    </#if>
                </#list>
                <li>
                    <a id="pagination_next" href="javascript:void(0)">&raquo;</a>
                </li>
            </ul>
            <span>跳转到<input type="text" id="to_page_num"></span>
            <a href="javascript:;" id="jump_page" class="btn btn-primary">确定</a>
        </div>
        <input type="hidden" id="cur_page" value="0"/>
    </div>
</#if>
<!--/分页器-->
<script>
    $("#to_page").click(function() {
        var pageNum = $('#to_page').html();
        $('#cur_page').val(pageNum);
        $('#search_page').click();
    });
    $("#pagination_prev").click(function() {
        $('#cur_page').val("${pageObject.previousIndex}");
        $('#search_page').click();
    });
    $("#pagination_next").click(function() {
        $('#cur_page').val("${pageObject.nextIndex}");
        $('#search_page').click();
    });
    $("#jump_page").click(function() {
        var curPage = $('#to_page_num').val();
        $('#cur_page').val(curPage);
        $('#search_page').click();
    });
</script>