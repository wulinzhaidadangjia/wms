<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/js/plugins/fancyBox/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/fancyBox/jquery.fancybox.js"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/commonAll.js"></script>
    <script>
        $(function () {
            $(".fancybox").fancybox();
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
<form id="searchForm" action="/product/list.do" method="post">
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
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="/product/input.do"/>
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
                    <th>商品图片</th>
                    <th>商品名称</th>
                    <th>商品编码</th>
                    <th>商品品牌</th>
                    <th>成本价</th>
                    <th>零售价</th>
                    <th></th>
                </tr>
                <c:forEach var="entity" items="${result.list}" varStatus="v">
                    <tr>
                        <td><input type="checkbox" class="acb"/></td>
                        <td>${v.count}</td>
                        <td>
                                <%--<a class="fancybox" data-fancybox-group="img" href="${entity.imagePath}"--%>
                                <%--title="${entity.name}">--%>
                                <%--<img src="${entity.smallImagePath}" width="80px"/></a>--%>
                            <a class="fancybox" data-fancybox-group="img"
                               href="${entity.imagePath}" title="${entity.name}">
                                <img class="list_img" src="${entity.smallImagePath}"/>
                            </a>
                        </td>
                            <%--<td><img src="${entity.smallImagePath}" width="80px"/></td>--%>
                        <td>${entity.name}</td>
                        <td>${entity.sn}</td>
                        <td>${entity.brandName}</td>
                        <td>${entity.costPrice}</td>
                        <td>${entity.salePrice}</td>
                        <td>
                            <a href="/product/input.do?id=${entity.id}">编辑</a>
                            <a href="javascript:" class="btn_delete"
                               data-obj="/product/list.do"
                               data-url="/product/delete.do?id=${entity.id}&imagePath=${entity.imagePath}">删除</a>
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

