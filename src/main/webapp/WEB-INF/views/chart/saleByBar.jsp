<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/plugins/echarts/echarts.min.js">
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '销售报表',
            x: 'center'
        },
        tooltip: {},
        legend: {
            data: ['销售总额'],
            x: 'left'
        },
        xAxis: {
            data: ${groupByType}
        },
        yAxis: {},
        series: [{
            name: '销售总额',
            type: 'bar',
            data: ${totalAmount}
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
