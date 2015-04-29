<#macro managerPage title=""  css=[] scripts=[] showMenu="" showNav="" currentPage="" menuBigItem="">
<!DOCTYPE html>
<html>
<head>
    <title>
        <#if title?? && title?length gt 0>${title}<#else>:::管理:::</#if>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap-overrides.css"/>
    <link rel="stylesheet" type="text/css" href="/css/lib/jquery-ui-1.10.2.custom.css"/>
    <link rel="stylesheet" type="text/css" href="/css/lib/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="/css/compiled/layout.css"/>
    <link rel="stylesheet" type="text/css" href="/css/compiled/elements.css"/>
    <link rel="stylesheet" type="text/css" href="/css/compiled/icons.css"/><#if css?? && css?size gt 0>
    <#list css as item>
        <link rel="stylesheet" type="text/css" href="${item!}" media="screen"/></#list>
</#if><!-- scripts -->
    <script src="/js/jquery-2.1.3.min.js"></script>
    <script src="/js/jquery-ui-1.10.2.custom.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <#if scripts?? && scripts?size gt 0>
        <#list scripts as item>
            <script src="${item!}"></script></#list>
    </#if>
    <!--[if lt IE 9]>
    <script src="/js/html5.js"></script>
    <![endif]-->
</head>

<body>
<div class="container">
    <#if showMenu != "FALSE">
        <!-- navbar -->
        <header class="navbar navbar-inverse" role="banner">
            <div class="navbar-header">
                <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">
                    <img src="/img/logo.png" alt="logo"/>
                </a>
            </div>
            <ul class="nav navbar-nav pull-right hidden-xs">
                <li class="settings hidden-xs hidden-sm">
                    <a href="/manager/logout" role="button" title="退出登录">
                        <i class="icon-share-alt"></i>
                    </a>
                </li>
            </ul>
        </header>
        <!-- end navbar -->
    </#if>

    <#if showNav != "FALSE">
        <!-- sidebar -->
        <div id="sidebar-nav">
            <ul id="dashboard-menu">
                <li<#if menuBigItem?? && (menuBigItem=="USER_LIST" || menuBigItem=="")> class="active"</#if>>
                    <a href="/manager/user">
                        <i class="icon-user"></i>
                        <span>用户列表</span>
                    </a>
                </li>
                <li<#if menuBigItem?? && (menuBigItem=="USER_ANALYSIS")> class="active"</#if>>
                    <a href="/manager/analysis">
                        <i class="icon-bar-chart"></i>
                        <span>用户统计</span>
                    </a>
                </li>
                <li<#if menuBigItem?? && (menuBigItem=="REWARD")> class="active"</#if>>
                    <a href="/manager/reward">
                        <i class="icon-money"></i>
                        <span>奖励管理</span>
                    </a>
                </li>
                <li<#if menuBigItem?? && (menuBigItem=="ADMIN_USER_EDIT")> class="active"</#if>>
                    <a href="/manager/admin/edit">
                        <i class="icon-wrench"></i>
                        <span>修改登录信息</span>
                    </a>
                </li>
                <li>
                    <a href="/manager/logout">
                        <i class="icon-share-alt"></i>
                        <span>退出登录</span>
                    </a>
                </li>
            </ul>
        </div>
        <!-- end sidebar -->
    </#if>

    <!-- main container -->
    <#if showMenu =="FALSE" && showNav == "FALSE">
        <#nested />
    <#else>
        <div class="content">
            <div id="pad-wrapper">
                <#nested />
            </div>
        </div>
    </#if>
</div>
</body>
</html>
</#macro>