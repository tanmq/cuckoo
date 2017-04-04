<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cuckoo monitor</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/monitor/monitor.css" rel="stylesheet">
    <link href="/css/monitor/flipclock.css" rel="stylesheet">
</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <div class="inner cover">
                <div class="clock"></div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/js/flipclock.js"></script>

<script>
    var clock;

    $(document).ready(function(){
        clock = new FlipClock($('.clock'), 100000000, {
            clockFace: 'Counter'
        });
        count();
    });

    function count() {
        $.post("/monitor/userCount?code=1234567890987654321", function( data ) {
            clock.setCounter(data["count"]);
        });
        setTimeout(count, 5000)
    }
</script>
</body>
</html>
