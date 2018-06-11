<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>叩丁狼wms(演示版)</title>
    <link href="/style/login_css.css" rel="stylesheet" type="text/css"/>
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/validate/jquery.validate.js"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/plugins/validate/messages_cn.js"></script>
    <script src="/js/commonAll.js"></script>
    <script>
        $(function () {
            // $("#login_sub").click(function () {
            //     $("#submitForm").submit();
            // });
            $("#submitForm").ajaxForm(function (data) {
                if (data.success) {
                    location.href = "/main.do";
                    console.log(data);
                } else {
                    $("#login_err").html(data.msg);
                }
            });
        });
    </script>
</head>
<body>
<div id="login_center">
    <div id="login_area">
        <div id="login_box">
            <div id="login_form">
                <form id="submitForm" action="/login.do" method="post">
                    <div id="login_tip">
                        <span id="login_err" class="sty_txt2"></span>
                    </div>
                    <div>
                        账号:<input type="text" name="username" class="username" id="name" value="admin"/>
                    </div>
                    <div>
                        密码:
                        <input type="password" name="password" class="pwd" id="pwd" value="1"/>
                    </div>
                    <div id="btn_area">
                        <input type="submit" class="login_btn" id="login_sub" value="登  录"/>
                        <input type="reset" class="login_btn" id="login_ret" value="重 置"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>