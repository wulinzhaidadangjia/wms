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
    <script>
        $(function () {
            //点击搜索放大镜进入子页面
            $(".searchproduct").click(function () {
                var tr = $(this).closest("tr");
                $.artDialog.open("/product/selectProductList.do", {
                    id: 'selectProduct',
                    title: '选择商品',
                    width: 800,
                    height: 500,
                    opacity: 0.1,
                    lock: true,
                    resize: false,
                    close: function () {
                        var productinfo = $.artDialog.data('productinfo'); // 读取子窗口返回的数据
                        console.log(productinfo);
                        $.artDialog.removeData("productinfo");
                        if (productinfo) {
                            //赋值到页面A的input以显示出来
                            //找到当前行最近的tr元素,
                            tr.find("[tag=pid]").val(productinfo.id);
                            tr.find("[tag=name]").val(productinfo.name);
                            tr.find("[tag=brand]").text(productinfo.brand);
                            tr.find("[tag=costPrice]").val(productinfo.costPrice);
                            tr.find("[tag=number]").val(1);
                            tr.find("[tag=amount]").text(productinfo.costPrice.toFixed(2));
                        }
                    }
                });
            });
            //添加新的明细
            $(".appendRow").click(function () {
                //1.找到输入框的第一行,克隆,清除input元素的value值.
                var newTr = $("#edit_table_body tr:first").clone(true);
                newTr.find("[tag=pid]").val('');
                newTr.find("[tag=name]").val('');
                newTr.find("[tag=brand]").text('');
                newTr.find("[tag=costPrice]").val('');
                newTr.find("[tag=number]").val('');
                newTr.find("[tag=amount]").text('');
                newTr.find("[tag=remark]").val('');
                //2.拼接到最后一行
                $("#edit_table_body").append(newTr);
            });
            //删除明细
            $(".removeItem").click(function () {
                //1.做判断.必须得留一个空行
                var currentTr = $(this).closest("tr");
                if ($("#edit_table_body tr").size() == 1) {
                    currentTr.find("[tag=pid]").val('');
                    currentTr.find("[tag=name]").val('');
                    currentTr.find("[tag=brand]").text('');
                    currentTr.find("[tag=costPrice]").val('');
                    currentTr.find("[tag=number]").val('');
                    currentTr.find("[tag=amount]").text('');
                    currentTr.find("[tag=remark]").val('');
                } else {
                    currentTr.remove();
                }
            });
            //提交数据
            $("#editForm").submit(function () {
                $.each($("#edit_table_body tr"), function (index, item) {
                    $(item).find("[tag=pid]").prop("name", "items[" + index + "].product.id");
                    $(item).find("[tag=name]").prop("name", "items[" + index + "].product.name");
                    $(item).find("[tag=costPrice]").prop("name", "items[" + index + "].costPrice");
                    $(item).find("[tag=number]").prop("name", "items[" + index + "].number");
                    $(item).find("[tag=remark]").prop("name", "items[" + index + "].remark");
                })
            });
            //计算总金额
            $("[tag=number],[tag=costPrice]").blur(function () {
                var currentTr = $(this).closest("tr");
                var totalNumer = currentTr.find("[tag=number]").val();
                var costPrice = currentTr.find("[tag=costPrice]").val();
                currentTr.find("[tag=amount]").text((totalNumer * costPrice).toFixed(2));
            });
            //保存弹出提示
            $("#editForm").ajaxForm(function (data) {
                if (data.success) {
                    showDialog("保存成功", function () {
                        location.href = "/stockIncomeBill/list.do"
                    });
                } else {
                    showDialog("保存成功" + data.msg);
                }
            })
        });
    </script>
</head>
<body>
<form id="editForm" action="/stockIncomeBill/saveOrUpdate.do" method="post"
      data-obj="stockIncomeBill">
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
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <select id="depotId" class="ui_select03" name="depot.id">
                            <c:forEach var="depot" items="${depots}">
                                <option value="${depot.id}" ${depot.id==entity.depot.id?'selected':''}>${depot.name}</option>
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