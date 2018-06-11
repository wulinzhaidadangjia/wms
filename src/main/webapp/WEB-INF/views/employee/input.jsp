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
            $("#editForm").validate({
                rules: {
                    name: {
                        required: true,
                        rangelength: [2, 6]
                    },
                    password: {
                        required: true,
                        rangelength: [2, 6]
                    },
                    re_password: {
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    age: {
                        digits: true,
                        range: [16, 60]
                    },
                }, message: {
                    name: {
                        required: "账号必填",
                        rangelength: "长度必须在2-6位"
                    },
                    password: {
                        required: "密码必填",
                        rangelength: "长度必须在2-6位"
                    },
                    repassword: {
                        equalTo: "必須與密碼相同",
                    },
                    email: {
                        required: "账号必填",
                        email: "必須輸入正確電子郵件格式"
                    },
                    age: {
                        digits: "必需輸入整數",
                        range: "年齡必须在16-60"
                    },
                }

            });
        });
    </script>
    <script>
        $(function () {
            $("#selectAll").click(function () {
                $(".all_roles option").appendTo(".selected_roles");
            });
            $("#deselectAll").click(function () {
                $(".selected_roles option").appendTo(".all_roles");
            });
            $("#select").click(function () {
                $(".all_roles option:selected").appendTo(".selected_roles");
            });
            $("#deselect").click(function () {
                $(".selected_roles option:selected").appendTo(".all_roles");
            });
        });
    </script>
    <script>
        $(function () {
            //1.获取左边所有的option的value值
            var val = $.map($(".all_roles option"), function (ele) {
                return ele.value;
            });
            //2.迭代与右边比较,左边删除与右边相同的
            $.each(val, function (i, item) {
                var valR = $.map($(".selected_roles option"), function (ele) {
                    return ele.value;
                });
                $.each(valR, function (j, itemR) {
                    if (item == itemR) {
                        $(".all_roles option[value='" + item + "']").remove();
                    }
                });
            });
            $("#save").click(function () {
                $(".selected_roles option").prop("selected", "selected");
                $("#editForm").submit();
            });
        });
    </script>
</head>
<body>
<form id="editForm" action="/employee/saveOrUpdate.do" method="post" data-obj="employee">
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
                    <td class="ui_text_rt" width="140">用户名</td>
                    <td class="ui_text_lt">
                        <input name="name" value="${entity.name}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <c:if test="${empty entity.id}">

                    <tr>
                        <td class="ui_text_rt" width="140">密码</td>
                        <td class="ui_text_lt">
                            <input type="password" id="password" name="password" class="ui_input_txt02"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="ui_text_rt" width="140">验证密码</td>
                        <td class="ui_text_lt">
                            <input name="repassword" type="password" class="ui_input_txt02"/>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td class="ui_text_rt" width="140">Email</td>
                    <td class="ui_text_lt">
                        <input name="email" value="${entity.email}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">年龄</td>
                    <td class="ui_text_lt">
                        <input name="age" value="${entity.age}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">所属部门</td>
                    <td class="ui_text_lt">
                        <select id="depts" name="dept.id" class="ui_select03">
                            <option>请选择</option>
                            <c:forEach var="dept" items="${depts}">
                                <option value="${dept.id}">${dept.name}</option>
                            </c:forEach>
                        </select>
                        <script>
                            $.each($("#depts option"), function (i, item) {
                                if (item.value ==${entity.dept.id}) {
                                    $(item).prop("selected", true);
                                }
                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">超级管理员</td>
                    <td class="ui_text_lt">
                        <input type="checkbox" name="admin" class="ui_checkbox01" id="admin"/>
                    </td>
                    <script>
                        <%--$("#admin").prop("checked", ${empty entity? false:entity.admin});--%>
                        $(function () {
                            var roleTr = null;
                            if ($("#admin").prop("checked")) {
                                roleTr = $("#roleTr").detach();
                            }
                            $("#admin").click(function () {
                                if ($(this).prop("checked")) {
                                    roleTr = $("#roleTr").detach();
                                } else {
                                    $(this).closest("tr").after(roleTr);
                                }
                            })
                        })
                    </script>
                </tr>
                <tr id="roleTr">
                    <td class="ui_text_rt" width="140">角色</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <select multiple="true" class="ui_multiselect01 all_roles">
                                        <c:forEach var="ele" items="${roles}">
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
                                    <select name="ids" multiple="true" class="ui_multiselect01 selected_roles">
                                        <c:forEach var="ele" items="${entity.roles}">
                                            <option value="${ele.id}">${ele.name}</option>
                                        </c:forEach>
                                    </select>
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
