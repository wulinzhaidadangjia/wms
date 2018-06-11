<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/commonAll.js"></script>
    <title>WMS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/client/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/client/input.do"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui_content">
        <div class="ui_tb">
            <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                <tr>
                    <th width="30"><input type="checkbox" id="all"/></th>
                    <th>编号</th>
                    <th>客户名称</th>
                    <th>客户编号</th>
                    <th>联系方式</th>
                    <th></th>
                </tr>
                <c:forEach var="entity" items="${result.list}" varStatus="v">
                    <tr>
                        <td><input type="checkbox" class="acb"/></td>
                        <td>${v.count}</td>
                        <td>${entity.name}</td>
                        <td>${entity.sn}</td>
                        <td>${entity.phone}</td>
                        <td>
                            <a href="/client/input.do?id=${entity.id}">编辑</a>
                            <a href="javascript:" class="btn_delete"
                               data-obj="/client/list.do"
                               data-url="/client/delete.do?id=${entity.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/common/common_page.jsp" %>
    </div>
</form>
</body>
</html>

