<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/commonAll.js"></script>
    <%--<script>--%>
    <%--$(function () {--%>
    <%--$(".btn_child").click(function () {--%>
    <%--var url = $(this).data("url");--%>
    <%--alert(url);--%>
    <%--location.href = url;--%>
    <%--$("#searchForm").submit();--%>

    <%--})--%>
    <%--})--%>
    <%--</script>--%>
    <title>WMS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/systemMenu/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/systemMenu/input.do?parentId=${qo.parentId}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui_content">
        <div>
            当前菜单:
            <a href="/systemMenu/list.do">根菜单</a>->
            <c:forEach var="ele" items="${parents}">
                <a href="/systemMenu/list.do?parentId=${ele.id}">${ele.name}</a>
            </c:forEach>
        </div>

        <div class="ui_tb">
            <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                <tr>
                    <th width="30"><input type="checkbox" id="all"/></th>
                    <th>编号</th>
                    <th>菜单名称</th>
                    <th>菜单编号</th>
                    <th>父菜单</th>
                    <th>URL</th>
                    <th></th>
                </tr>
                <c:forEach var="entity" items="${list}" varStatus="v">
                    <tr>
                        <td><input type="checkbox" class="acb"/></td>
                        <td>${v.count}</td>
                        <td>${entity.name}</td>
                        <td>${entity.sn}</td>
                        <td>${entity.parent.name}</td>
                        <td>${entity.url}</td>
                        <td>
                            <a href="/systemMenu/input.do?id=${entity.id}&parentId=${qo.parentId}">编辑</a>
                            <a href="javascript:" class="btn_delete"
                               data-obj="/systemMenu/list.do"
                               data-url="/systemMenu/delete.do?id=${entity.id}">删除</a>
                            <a href="/systemMenu/list.do?parentId=${entity.id}">子菜单</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <%--    <%@ include file="/WEB-INF/views/common/common_page.jsp" %>--%>
    </div>
</form>
</body>
</html>

