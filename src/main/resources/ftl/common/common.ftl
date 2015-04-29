<#macro pagination currentPage=1 totalPage=1 url="" params="">
    <#if (totalPage <= 0) || (currentPage > totalPage)><#return></#if>
    <#local startPage = currentPage - 2>
    <#if (startPage < 1)><#local startPage = 1></#if>

    <#local endPage = currentPage + 2>
    <#if (endPage > totalPage)><#local endPage = totalPage></#if>
<div id="page">
    <ul class="pagination">
        <#if (currentPage <= 3)>
            <#local startPage = 1>
        </#if>
        <#if ((totalPage - currentPage) < 2)>
            <#local endPage = totalPage>
        </#if>

        <#if (currentPage <= 1)>
            <li class="disabled"><a href="javascript:;">首页</a></li>
            <li class="disabled"><a href="javascript:;">上页</a></li>
        <#else>
            <li><a href="${url}1${params!}">首页</a></li>
            <li><a href="${url}#{currentPage - 1}${params!}">上页</a></li>
        </#if>

            <!--<#if (currentPage > 8)>-->
        <!--<li><a href="${url}#{1}${params!}">#{1}</a></li>-->
        <!--<li><a href="${url}#{2}${params!}">#{2}</a></li>-->
        <!--&lt;!&ndash;<li class="disabled">…</li>&ndash;&gt;-->
            <!--</#if>-->

        <#list startPage..endPage as i>
            <#if currentPage == i>
                <li class="active"><a class="disabled">#{i}</a></li>
            <#else>
                <li><a href="${url}#{i}${params!}">#{i}</a></li>
            </#if>
        </#list>

            <!--<#if ((totalPage - currentPage) >= 8)>-->
        <!--&lt;!&ndash;<li class="disabled">…</li>&ndash;&gt;-->
        <!--<li><a href="${url}#{totalPage - 1}${params!}">#{totalPage - 1}</a></li>-->
        <!--<li><a href="${url}#{totalPage}${params!}">#{totalPage}</a></li>-->
            <!--</#if>-->

        <#if (currentPage <= 1 || currentPage == totalPage)>
            <li class="disabled"><a href="javascript:;">下页</a></li>
            <li class="disabled"><a href="javascript:;">尾页</a></li>
        <#else>
            <li><a href="${url}#{currentPage + 1}${params!}">下页</a></li>
            <li><a href="${url}#{totalPage}${params!}">尾页</a></li>
        </#if>
    </ul>
</div>
</#macro>