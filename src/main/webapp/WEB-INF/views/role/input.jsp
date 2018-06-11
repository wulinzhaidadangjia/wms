<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/validate/jquery.validate.js"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/plugins/validate/messages_cn.js"></script>
    <script src="/js/commonAll.js"></script>
    <script>
        $(function () {
            $("#selectAll").click(function () {
                $(".all_permissions option").appendTo(".selected_permissions");
            });
            $("#deselectAll").click(function () {
                $(".selected_permissions option").appendTo(".all_permissions");
            });
            $("#select").click(function () {
                $(".all_permissions option:selected").appendTo(".selected_permissions");
            });
            $("#deselect").click(function () {
                $(".selected_permissions option:selected").appendTo(".all_permissions");
            });
        });
    </script>
    <script>
        $(function () {
            //1.获取左边所有的option的value值
            var val = $.map($(".all_permissions option"), function (ele) {
                return ele.value;
            });
            //2.迭代与右边比较,左边删除与右边相同的
            $.each(val, function (i, item) {
                var valR = $.map($(".selected_permissions option"), function (ele) {
                    return ele.value;
                });
                $.each(valR, function (j, itemR) {
                    if (item == itemR) {
                        $(".all_permissions option[value='" + item + "']").remove();
                    }
                });
            });
            $("#save").click(function () {
                $(".selected_permissions option").prop("selected", "selected");
                $(".selected_Menus option").prop("selected", "selected");
                $("#editForm").submit();
            });
        });
    </script>
</head>
<body>
<form id="editForm" action="/role/saveOrUpdate.do" method="post" data-obj="role">
    <input type="hidden" name="id" value="${entity.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">角色编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">角色名称</td>
                    <td class="ui_text_lt">
                        <input name="name" value="${entity.name}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">角色编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">分配权限</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <select multiple="true" class="ui_multiselect01 all_permissions">
                                        <c:forEach var="ele" items="${permissions}">
                                            <option value="${ele.id}">${ele.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <select name="ids" multiple="true" class="ui_multiselect01 selected_permissions">
                                        <c:forEach var="ele" items="${entity.permissions}">
                                            <option value="${ele.id}">${ele.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">分配菜单</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <select multiple="true" class="ui_multiselect01 all_Menus">
                                        <c:forEach var="ele" items="${menus}">
                                            <option value="${ele.id}">${ele.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td align="center">
                                    <input type="button" id="selectMenu" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAllMenu" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselectMenu" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAllMenu" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <select name="mids" multiple="true" class="ui_multiselect01 selected_Menus">
                                        <c:forEach var="ele" items="${entity.menus}">
                                            <option value="${ele.id}">${ele.name}</option>
                                        </c:forEach>
                                    </select>
                                    <script>
                                        //左右移动
                                        $(function () {
                                            $("#selectAllMenu").click(function () {
                                                $(".all_Menus option").appendTo(".selected_Menus");
                                            });
                                        });
                                        $(function () {
                                            $("#deselectAllMenu").click(function () {
                                                $(".selected_Menus option").appendTo(".all_Menus ");
                                            });
                                        });
                                        $(function () {
                                            $("#selectMenu").click(function () {
                                                $(".all_Menus option:selected").appendTo(".selected_Menus ");
                                            });
                                        });
                                        $(function () {
                                            $("#deselectMenu").click(function () {
                                                $(".selected_Menus option:selected").appendTo(".all_Menus ");
                                            });
                                            //消除重复
                                            var leftMenus = $.map($(".all_Menus option"), function (ele) {
                                                return ele.value;
                                            });
                                            $.each(leftMenus, function (i, item) {
                                                var rightMenus = $.map($(".selected_Menus option"), function (ele1) {
                                                    return ele1.value;
                                                });
                                                $.each(rightMenus, function (j, item1) {
                                                    if (item == item1) {
                                                        $(".all_Menus option[value='" + item + "']").remove();
                                                    }
                                                });
                                            });
                                        });
                                    </script>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input id="save" type="button" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>
