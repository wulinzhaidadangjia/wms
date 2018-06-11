<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="/js/commonAll.js"></script>
    <script>
        $(function () {
            $(".btn_audit").click(function () {
                var url = $(this).data("url");
                var obj = $(this).data("obj");
                showDialog("确定要审核吗?", function () {

                    $.get(url, function (data) {
                        if (data.success) {
                            showDialog("审核成功");
                            location.href = obj;
                        } else {
                            showDialog("审核失败" + data.msg);
                        }
                    }, "json")
                }, true)
            })
        })
    </script>

    <title>WMS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/stockOutcomeBill/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="beginDate"/>
                        <input type="text" class="ui_input_txt02 Wdate" name="beginDate" value="${beginDate}"
                               onclick="WdatePicker();"/> ~
                        <fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        <input type=" text" class="ui_input_txt02 Wdate" name="endDate" value="${endDate}"
                               onclick="WdatePicker();"/>
                        供应商
                        <select id="depotId" class="ui_select01" name="depotId">
                            <option value="-1">全部仓库</option>
                            <c:forEach var="depot" items="${depots}">
                                <option value="${depot.id}" ${depot.id==qo.depotId?'selected':''}>${depot.name}</option>
                            </c:forEach>
                        </select>
                        状态
                        <select id="status" class="ui_select01" name="status">
                            <option value="-1">全部</option>
                            <option value="0" ${qo.status==0?'selected':''} >待审核</option>
                            <option value="1" ${qo.status==1?'selected':''} >已审核</option>
                        </select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/stockOutcomeBill/input.do"/>
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
                    <th>采购单号</th>
                    <th>业务时间</th>
                    <th>供应商</th>
                    <th>总数量</th>
                    <th>总金额</th>
                    <th>录入人</th>
                    <th>审核人</th>
                    <th>状态</th>
                    <th></th>
                </tr>
                <c:forEach var="ob" items="${result.list}" varStatus="v">
                    <tr>
                        <td><input type="checkbox" class="acb"/></td>
                        <td>${v.count}</td>
                        <fmt:formatDate value="${ob.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
                        <td>${vdate}</td>
                        <td>${ob.depot.name}</td>
                        <td>${ob.totalNumber}</td>
                        <td>${ob.totalAmount}</td>
                        <td>${ob.inputUser.name}</td>
                        <td>${ob.auditor.name}</td>
                        <td>
                            <c:if test="${ob.status==0}">
                                <span style="color: red;">未审核</span>
                            </c:if>
                            <c:if test="${ob.status==1}">
                                <span style="color: green;">已审核</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${ob.status==0}">
                                <a href="/stockOutcomeBill/input.do?id=${ob.id}">编辑</a>
                                <a href="javascript:" class="btn_delete"
                                   data-url="/stockOutcomeBill/delete.do?id=${ob.id}"
                                   data-obj="/stockOutcomeBill/list.do">删除</a>
                                <a href="javascript:" class="btn_audit"
                                   data-url="/stockOutcomeBill/audit.do?id=${ob.id}"
                                   data-obj="/stockOutcomeBill/list.do">审核</a>
                            </c:if>
                            <c:if test="${ob.status==1}">
                                <a href="/stockOutcomeBill/view.do?id=${ob.id}">查看</a>
                            </c:if>
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

