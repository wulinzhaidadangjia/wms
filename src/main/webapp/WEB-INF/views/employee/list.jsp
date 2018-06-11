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
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script>
        $(function () {
            $(".btachdelete").click(function () {
                url = $(this).data("url");
                showDialog("确定要批量删除吗?", function () {
                    if ($(".acb:checked").size() < 0) {
                        showDialog("至少选择一条数据");
                    } else {
                        var ids = $.map($(".acb:checked"), function (i) {
                            return $(i).data("eid");
                        });
                        //获取所有选择批量删除操作的id封装成数组.
                        var sendData = {ids: ids};
                        //ajax的post请求方式
                        $.post(url, sendData, function (data) {
                            if (data.success) {
                                showDialog("删除成功", function () {
                                    location.href = "/employee/list.do";
                                }, true);
                            } else {
                                showDialog("删除失败");
                            }
                        }, "json")
                    }
                }, true);
            });
            // 选择全部选择chedkbox
            $("#all").click(function () {
                $(".acb").prop("checked", this.checked);
            });
        });
    </script>
    <title>WMS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/employee/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        姓名/邮箱
                        <input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}"/>
                        所属部门
                        <select id="dept" class="ui_select01" name="dept_id">
                            <option value="">全部</option>
                            <c:forEach var="dept" items="${depts}">
                                <option value="${dept.id}">${dept.name}</option>
                            </c:forEach>
                            <script>
                                $.each($("#dept option"), function (i, item) {
                                    if (item.value ==${qo.dept_id}) {
                                        $(item).prop("selected", true);
                                    }
                                })
                            </script>
                        </select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/employee/input.do"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btachdelete"
                               data-url="/employee/batchDelete.do"/>
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
                        <th>用户名</th>
                        <th>EMAIL</th>
                        <th>年龄</th>
                        <th>所属部门</th>
                        <th></th>
                    </tr>
                    <c:forEach var="entity" items="${result.list}" varStatus="v">
                        <tr>
                            <td><input type="checkbox" class="acb" data-eid="${entity.id}"/></td>
                            <td>${v.count}</td>
                            <td>${entity.name}</td>
                            <td>${entity.email}</td>
                            <td>${entity.age}</td>
                            <td>${entity.dept.name}</td>
                            <td>
                                <a href="/employee/input.do?id=${entity.id}">编辑</a>
                                <a href="javascript:" class="btn_delete" data-obj="/employee/list.do"
                                   data-url="/employee/delete.do?id=${entity.id}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <%@ include file="/WEB-INF/views/common/common_page.jsp" %>
        </div>
    </div>
</form>
</body>
</html>


