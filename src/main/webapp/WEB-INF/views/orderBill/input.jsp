<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script src="/js/plugins/artdialog/jquery.artDialog.js"></script>
    <script src="/js/plugins/artdialog/plugins/iframeTools.js"></script>
    <script src="/js/plugins/validate/jquery.validate.js"></script>
    <script src="/js/plugins/validate/messages_cn.js"></script>
    <script src="/js/plugins/jQueryForm/jQueryForm.js"></script>
    <script src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="/js/commonAll.js"></script>
    <%--<script>--%>
    <%--$(function () {--%>
    <%--$(".btn_submit").click(function () {--%>
    <%--var newVal = $.map($("#edit_table_body tr"), function (ele) {--%>
    <%--return $(ele).find("[tag=pid]").val();--%>
    <%--});--%>
    <%--console.log(newVal);--%>
    <%--$.each(newVal, function (i, ele) {--%>
    <%--if (!ele >= 0) {--%>
    <%--showDialog("明细不能有空");--%>
    <%--return;--%>
    <%--}--%>
    <%--});--%>

    <%--});--%>
    <%--$(".searchproduct").click(function () {--%>
    <%--var tr = $(this).closest("tr");--%>
    <%--$.artDialog.open("/product/selectProductList.do", {--%>
    <%--id: 'selectProduct',--%>
    <%--title: '选择商品',--%>
    <%--width: 800,--%>
    <%--height: 500,--%>
    <%--opacity: 0.2,--%>
    <%--lock: true,--%>
    <%--resize: false,--%>
    <%--close: function () {--%>
    <%--//获取子窗口回传的数据--%>
    <%--var productinfo = $.artDialog.data("productinfo");--%>
    <%--console.log("父窗口" + productinfo);--%>
    <%--//删除之前共享的数据--%>
    <%--$.artDialog.removeData("productinfo");--%>
    <%--if (productinfo) {--%>
    <%--//赋值到当前的tr中--%>
    <%--tr.find("[tag=pid]").val(productinfo.id);--%>
    <%--tr.find("[tag=name]").val(productinfo.name);--%>
    <%--tr.find("[tag=costPrice]").val(productinfo.costPrice);--%>
    <%--tr.find("[tag=number]").val(1);--%>
    <%--tr.find("[tag=brand]").html(productinfo.brand);--%>
    <%--tr.find("[tag=amount]").html(productinfo.costPrice.toFixed(2));--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--});--%>
    <%--$("[tag=costPrice],[tag=number]").blur(function () {--%>
    <%--var currentIr = $(this).closest("tr");--%>
    <%--var costPrice = parseFloat(currentIr.find("[tag=costPrice]").val()) || 0;--%>
    <%--var number = parseFloat(currentIr.find("[tag=number]").val()) || 0;--%>
    <%--var amount = (costPrice * number).toFixed(2);--%>
    <%--currentIr.find("[tag=amount]").text(amount);--%>
    <%--});--%>
    <%--//添加明细--%>
    <%--$(".appendRow").click(function () {--%>
    <%--var tr = $("#edit_table_body tr:first");--%>
    <%--var newTr = tr.clone(true);--%>
    <%--newTr.find("[tag=pid]").val('');--%>
    <%--newTr.find("[tag=name]").val('');--%>
    <%--newTr.find("[tag=costPrice]").val('');--%>
    <%--newTr.find("[tag=number]").val('');--%>
    <%--newTr.find("[tag=brand]").text('');--%>
    <%--newTr.find("[tag=amount]").text('');--%>
    <%--console.log(newTr);--%>
    <%--$("#edit_table_body").append(newTr);--%>
    <%--}--%>
    <%--);--%>
    <%--//删除明细--%>
    <%--$(".removeItem").click(function () {--%>
    <%--var currentTr = $(this).closest("tr");--%>
    <%--if ($("#edit_table_body tr").size() == 1) {--%>
    <%--currentTr.find("[tag=pid]").val('');--%>
    <%--currentTr.find("[tag=name]").val('');--%>
    <%--currentTr.find("[tag=costPrice]").val('');--%>
    <%--currentTr.find("[tag=number]").val('');--%>
    <%--currentTr.find("[tag=brand]").text('');--%>
    <%--currentTr.find("[tag=amount]").text('');--%>
    <%--}--%>
    <%--else {--%>
    <%--currentTr.remove();--%>
    <%--}--%>
    <%--});--%>
    <%--//表单提交之前,修改元素name属性的值.--%>
    <%--$("#editForm").submit(function () {--%>
    <%--$.each($("#edit_table_body tr"), function (i, ele) {--%>
    <%--$(ele).find("[tag=pid]").prop("name", "items[" + i + "].product.id");--%>
    <%--$(ele).find("[tag=costPrice]").prop("name", "items[" + i + "].costPrice");--%>
    <%--$(ele).find("[tag=number]").prop("name", "items[" + i + "].number");--%>
    <%--$(ele).find("[tag=remark]").prop("name", "items[" + i + "].remark");--%>
    <%--});--%>
    <%--});--%>
    <%--$("#editForm").ajaxForm(function (data) {--%>
    <%--if (data.success) {--%>
    <%--showDialog("保存成功", function () {--%>
    <%--location.href = "/orderBill/list.do"--%>
    <%--});--%>
    <%--} else {--%>
    <%--showDialog("保存失败" + data.msg);--%>
    <%--}--%>
    <%--})--%>
    <%--})--%>
    <%--</script>--%>
    <script>
        $(function () {
            $(".searchproduct").on("click", function () {
                var tr = $(this).closest("tr");
                $.artDialog.open("/product/selectProductList.do", {
                    id: 'selectProduct',
                    title: '选择商品',
                    width: 800,
                    height: 500,
                    opacity: 0.2,
                    lock: true,
                    resize: false,
                    close: function () {
                        //获取子窗口回传的数据
                        var productinfo = $.artDialog.data("productinfo");
                        console.log("父窗口" + productinfo);
                        //删除之前共享的数据
                        $.artDialog.removeData("productinfo");
                        if (productinfo) {
                            //赋值到当前的tr中
                            tr.find("[tag=pid]").val(productinfo.id);
                            tr.find("[tag=name]").val(productinfo.name);
                            tr.find("[tag=costPrice]").val(productinfo.costPrice);
                            tr.find("[tag=number]").val(1);
                            tr.find("[tag=brand]").html(productinfo.brand);
                            tr.find("[tag=amount]").html(productinfo.costPrice.toFixed(2));
                        }
                    }
                });
                //小结
            }).on("blur", "[tag='costPrice'],[tag='number']", function () {
                var currentIr = $(this).closest("tr");
                var costPrice = parseFloat(currentIr.find("[tag=costPrice]").val()) || 0;
                var number = parseFloat(currentIr.find("[tag=number]").val()) || 0;
                var amount = (costPrice * number).toFixed(2);
                currentIr.find("[tag=amount]").text(amount);
            });
            $(".appendRow").click(function () {
                    var tr = $("#edit_table_body tr:first");
                    var newTr = tr.clone(true);
                    newTr.find("[tag=pid]").val('');
                    newTr.find("[tag=name]").val('');
                    newTr.find("[tag=costPrice]").val('');
                    newTr.find("[tag=number]").val('');
                    newTr.find("[tag=brand]").text('');
                    newTr.find("[tag=amount]").text('');
                    $("#edit_table_body").append(newTr);
                }
            );
            $(".removeItem").click(function () {
                var currentTr = $(this).closest("tr");
                if ($("#edit_table_body tr").size() == 1) {
                    currentTr.find("[tag=pid]").val('');
                    currentTr.find("[tag=name]").val('');
                    currentTr.find("[tag=costPrice]").val('');
                    currentTr.find("[tag=number]").val('');
                    currentTr.find("[tag=brand]").text('');
                    currentTr.find("[tag=amount]").text('');
                }
                else {
                    currentTr.remove();
                }
            });
            $("#editForm").submit(function () {
                $.each($("#edit_table_body tr"), function (i, ele) {
                    $(ele).find("[tag=pid]").prop("name", "items[" + i + "].product.id");
                    $(ele).find("[tag=costPrice]").prop("name", "items[" + i + "].costPrice");
                    $(ele).find("[tag=number]").prop("name", "items[" + i + "].number");
                    $(ele).find("[tag=remark]").prop("name", "items[" + i + "].remark");
                });
            });
            $("#editForm").ajaxForm(function (data) {
                if (data.success) {
                    showDialog("保存成功", function () {
                        location.href = "/orderBill/list.do"
                    });
                } else {
                    showDialog("保存失败" + data.msg);
                }
            });
        })
    </script>
</head>
<body>
<form id="editForm" action="/orderBill/saveOrUpdate.do" method="post"
      data-obj="orderBill">
    <input type="hidden" name="id" value="${entity.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">采购单编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <select id="supplierId" class="ui_select03" name="supplier.id">
                            <c:forEach var="supplier" items="${suppliers}">
                                <option value="${supplier.id}" ${supplier.id==entity.supplier.id?'selected':''}>${supplier.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${entity.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
                        <input name="vdate" value="${vdate}" class="ui_input_txt02 Wdate"
                               onclick="WdatePicker();"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0">
                            <thead>
                            <tr>
                                <th width="170">货品</th>
                                <th width="100">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="100">金额小计</th>
                                <th width="180">备注</th>
                                <th width="120"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <c:if test="${entity.id==null}">
                                <tr>
                                    <td>
                                        <input readonly class="ui_input_txt01" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><input type="number" tag="costPrice"
                                               class="ui_input_txt01"/></td>
                                    <td><input type="number" tag="number" class="ui_input_txt01"/>
                                    </td>
                                    <td><span tag="amount"></span></td>
                                    <td><input tag="remark" class="ui_input_txt01"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </c:if>
                            <c:forEach var="item" items="${entity.items}" varStatus="i">
                                <tr>
                                    <td>
                                        <input readonly class="ui_input_txt01" tag="name"
                                               value="${item.product.name}"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" tag="pid" value="${item.product.id}"/>
                                    </td>
                                    <td><span tag="brand">${item.product.brandName}</span></td>
                                    <td><input type="number" tag="costPrice"
                                               value="${item.costPrice}"
                                               class="ui_input_txt01"/></td>
                                    <td><input type="number" tag="number" value="${item.number}"
                                               class="ui_input_txt01"/>
                                    </td>
                                    <td><span tag="amount">${item.amount}</span></td>
                                    <td><input tag="remark" class="ui_input_txt01"
                                               value="${item.remark}"/></td>
                                    <td>
                                        <a href="javascript:" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>