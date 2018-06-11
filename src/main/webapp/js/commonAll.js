/** table鼠标悬停换色* */
$.ajaxSettings.traditional = true;
$(function () {
    $(".btn_input").click(function () {
        location.href = $(this).data("url");
    });
    $("#pageSize").change(function () {
        $(":text[name='currentPage']").val(1);
        $("#searchForm").submit();
    });
    $(".btn_page").click(function () {
        //获取该事件请求的参数
        var pageNo = $(this).data("page") || $(":input[name='currentPage']").val();
        //将参数设置为该当前参数
        $(":text[name='currentPage']").val(pageNo);
        //以post方式提交
        $("#searchForm").submit();
    });
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({
            background: "#CDDAEB"
        });
        $(this).children('td').each(function (index, ele) {
            $(ele).css({
                color: "#1D1E21"
            });
        });
    }).mouseout(function () {
        $(this).css({
            background: "#FFF"
        });
        $(this).children('td').each(function (index, ele) {
            $(ele).css({
                color: "#909090"
            });
        });
    });
});

//对话框
function showDialog(msg, ok, cancel) {
    $.dialog({
        title: "温馨提示",
        content: msg,
        icon: "face-smile",
        ok: ok || true,
        lock: true,
        cancel: cancel || true,
    })
    ;
};
//弹出框
$(function () {

    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        var obj = $(this).data("obj");
        //弹出提示框
        showDialog("确定要删除吗", function () {
            //点击确定按钮,发送ajax请求数据
            $.get(url, function (data) {
                if (data.success) {
                    //提示用户操作成功
                    showDialog("操作成功", function () {
                        location.href = obj;
                    });
                } else {
                    showDialog("操作失败" + data.msg);
                }
            }, "json");
        }, true);
    });

});

//保存操作弹出框
$("#editForm").ajaxForm(
    function (data) {
        var obj = $("#editForm").data("obj");
        if (data.success) {
            showDialog("保存成功", function () {
                location.href = "/" + obj + "/list.do"
            })
        } else {
            showDialog("保存失败" + data.msg);
        }
    }
);
