<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/artdialog/plugins/iframeTools.js"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/commonAll.js"></script>
    <script>
        $(function () {
            $(".left2right").click(function () {
                //点击选中按钮回显当前商品的数据给父窗口
                var productinfo = $(this).data("productinfo");
                $.artDialog.data("productinfo", productinfo);
                //关闭该窗口
                $.artDialog.close();
            });
        });
    </script>
    <title>PSS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/product/selectProductList.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        商品名称/编码
                        <input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}"/>
                        商品品牌
                        <select id="brandId" class="ui_select01" name="brand_id">
                            <option value="-1">全部品牌</option>
                            <c:forEach var="b" items="${brands}">
                                <option value="${b.id}" ${b.id==qo.brand_id?'selected':''}>${b.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
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
                        <th>商品图片</th>
                        <th>商品名称</th>
                        <th>商品编码</th>
                        <th>商品品牌</th>
                        <th>成本价</th>
                        <th>零售价</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${result.list}" var="entity" varStatus="v">
                        <tr>
                            <td><input type="checkbox" class="acb"/></td>
                            <td>${v.count}</td>
                            <td><img class="list_img" src="${entity.smallImagePath}"/>
                            </td>
                            <td>${entity.name}</td>
                            <td>${entity.sn}</td>
                            <td>${entity.brandName}</td>
                            <td>${entity.costPrice}</td>
                            <td>${entity.salePrice}</td>
                            <td>
                                <input class="left2right" type="button" value="选中"
                                       data-productinfo='${entity.productInfo}'/>
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

