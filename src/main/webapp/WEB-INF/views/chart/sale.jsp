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
    <script src="/js/plugins/artdialog/plugins/iframeTools.js"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="/js/plugins/echarts/echarts.min.js"></script>
    <script src="/js/commonAll.js"></script>
    <script>
        $(function () {
            $(".chart").click(function () {
                //表单的序列化:效果表单中的所有参数 -> 参数名=参数值&参数名=参数值
                var params = $("#searchForm").serialize();
                 console.log(params);
                // params=beginDate=&endDate=&keyword=&clientId=-1&brandId=-1&groupType=p.name
                var url = $(this).data("url") + "?" + params;
                //弹出子窗口
                $.dialog.open(url, {
                    title: "选择商品", //标题
                    width: "85%", //宽度
                    height: "85%", //高度
                    lock: true, //锁定模式
                    resize: false //不允许重置
                });

            })
        })
    </script>
<body>
<form id="searchForm" action="/chart/sale.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_bsale">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="beginDate"/>
                        <input type="text" class="ui_input_txt01 Wdate" name="beginDate" value="${beginDate}"
                               onclick="WdatePicker();"/> ~
                        <fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        <input type=" text" class="ui_input_txt01 Wdate" name="endDate" value="${endDate}"
                               onclick="WdatePicker();"/>
                        货品名称/编码
                        <input type=" text" class="ui_input_txt01" name="keyword" value="${qo.keyword}"/>
                        客户
                        <select id="clientId" class="ui_select01" name="clientId">
                            <option value="-1">全部客户</option>
                            <c:forEach items="${clients}" var="client">
                                <option value="${client.id}" ${qo.clientId==client.id?'selected':''}>${client.name}</option>
                            </c:forEach>
                        </select>
                        品牌
                        <select id="brandId" class="ui_select01" name="brandId">
                            <option value="-1">全部品牌</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${qo.brandId==brand.id?'selected':''}>${brand.name}</option>
                            </c:forEach>
                        </select>
                        类型
                        <select id="groupType" class="ui_select01" name="groupType">
                            <c:forEach items="${groupByTypeMap}" var="groupByType">
                                <option value="${groupByType.key}" ${qo.groupType==groupByType.key?'selected':''}>${groupByType.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
                        <input type="button" value="柱状图" class="left2right chart"
                               data-url="/chart/saleByBar.do"/>
                        <input type="button" value="饼状图" class="left2right chart"
                               data-url="/chart/saleByPie.do"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" bsale="0">
                    <tr>
                        <th>分组类型</th>
                        <th>销售总数</th>
                        <th>销售总额</th>
                        <th>利润</th>
                    </tr>
                    <c:forEach items="${saleCharts}" var="sale">
                        <tr>
                            <td>${sale.groupType}</td>
                            <td>${sale.totalNumber}</td>
                            <td>${sale.totalAmount}</td>
                            <td>${sale.profit}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>

