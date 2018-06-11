$(function () {
    // 加载日期
    loadDate();
    // ======================================
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
    // ======================================
    //面板切换
    $("#TabPage2 li").click(function () {
        //把所有li都复原
        $.each($("#TabPage2 li"), function (i, item) {
            $(item).removeClass("selected");
            $(item).children("img").prop("src", "/images/common/" + (i + 1) + ".jpg")
        });
        //当面板被选中
        $(this).addClass("selected");
        $(this).children("img").prop("src", "/images/common/" + ($(this).index() + 1) + "_hover.jpg");
        $("#nav_module img").prop("src", "/images/common/module_" + ($(this).index() + 1) + ".png");
        //切换菜单
        loadMenus($(this).data("rootmenu"));
    });
    loadMenus("business");
});
//加载菜单
function loadMenus(menu) {
    $.fn.zTree.init($("#dleft_tab1"), setting, treeNodes[menu])
}

//zThree
//zTree配置
var setting = {
    data: {
        simpleData: {
            enable: true,
        }
    },
    //设置节点的回调函数,
    callback: {
        //点击子菜单显示对应页面
        onClick: function (event, treeId, node) {
            //点击事件触发后.
            if (node.action) {
                $("#rightMain").prop("src", node.action + ".do");
                //修改菜单对应的标题
                $("#here_area").html("当前位置:" + "&nbsp;->&nbsp;" + node.name);
            }
        }
    },
    //异步加载
    async: {
        enable: true,
        //提交路径
        url: "/systemMenu/loadMenus.do",
        //请求参数
        autoParam: ["sn=parentSn"],
    }
};
//zTree树节点
var treeNodes = {
    business: [
        {id: 1, pId: 0, name: "业务模块", sn: "business", isParent: true},
        // {id: 2, pId: 1, name: "品牌管理"},
        // {id: 3, pId: 1, name: "供应商管理"}
    ],
    systemManage: [
        {id: 1, pId: 0, name: "系统模块", sn: "system", isParent: true},
        // {id: 2, pId: 1, name: "部门管理", action: "/department/list.do"},
        // {id: 3, pId: 1, name: "员工管理", action: "/employee/list.do"},
        // {id: 4, pId: 1, name: "权限管理", action: "/permission/list.do"},
        // {id: 5, pId: 1, name: "角色管理", action: "/role/list.do"},
        // {id: 5, pId: 1, name: "系统菜单管理", action: "/systemMenu/list.do"},
    ],
    charts: [
        {id: 1, pId: 0, name: "报表模块", sn: "charts", isParent: true},
        // {id: 2, pId: 1, name: "即时库存报表"},
        // {id: 3, pId: 1, name: "销售报表"}
    ]
};
//初始化菜单树
// $.fn.zTree.init($("#dleft_tab1"), setting, business)
// )


//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
        + myDay;
}


/**
 * 隐藏或者显示侧边栏
 *
 */
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) { // flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({
            width: '280px'
        });
        $('#top_nav').css({
            width: '77%',
            left: '304px'
        });
        $('#main').css({
            left: '280px'
        });
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({
                width: '60px'
            });
            $('#top_nav').css({
                width: '100%',
                left: '60px',
                'padding-left': '28px'
            });
            $('#main').css({
                left: '60px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({
                width: '280px'
            });
            $('#top_nav').css({
                width: '77%',
                left: '304px',
                'padding-left': '0px'
            });
            $('#main').css({
                left: '280px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_hide.png');
        }
    }
}
