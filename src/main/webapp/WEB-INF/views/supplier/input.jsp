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
<form id="editForm" action="/supplier/saveOrUpdate.do" method="post" data-obj="supplier">
    <input type="hidden" name="id" value="${entity.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">用户编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">供应商名称</td>
                    <td class="ui_text_lt">
                        <input type="text" name="name" value="${entity.name}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商电话</td>
                    <td class="ui_text_lt">
                        <input type="text" name="phone" value="${entity.phone}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商地址</td>
                    <td class="ui_text_lt">
                        <input type="text" name="address" value="${entity.address}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
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