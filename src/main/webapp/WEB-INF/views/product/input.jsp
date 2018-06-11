<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/validate/jquery.validate.js"></script>
    <script src="/js/plugins/validate/messages_cn.js"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/commonAll.js"></script>
</head>
<body>
<form id="editForm" action="/product/saveOrUpdate.do" method="post" enctype="multipart/form-data" data-obj=" product
">
    <input type="hidden" name="id" value="${entity.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">商品编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">商品名称</td>
                    <td class="ui_text_lt">
                        <input name="name" value="${entity.name}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">成本价</td>
                    <td class="ui_text_lt">
                        <input name="costPrice" value="${entity.costPrice}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">零售价</td>
                    <td class="ui_text_lt">
                        <input name="salePrice" value="${entity.salePrice}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品品牌</td>
                    <td class="ui_text_lt">
                        <select id="brandId" name="brand_id" class="ui_select03">
                            <option value="-1">全部品牌</option>
                            <c:forEach var="brand" items="${brands}">
                                <option value="${brand.id}" ${entity.brand_id==brand.id?'selected':''}>${brand.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品图片</td>
                    <td class="ui_text_lt">
                        <input type="file" name="pic" class="ui_file"/>
                        <img src="${entity.imagePath}" width="80px"/>
                        <input type="hidden" name="imagePath" value="${entity.imagePath}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品简介</td>
                    <td class="ui_text_lt">
                        <textarea name="intro" class="ui_input_txtarea"></textarea>
                    </td>
                </tr>
                <td>&nbsp;</td>
                <td class="ui_text_lt">
                    &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                    &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>