<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>讨论课进行</title>

    <link href="/plugins/sweetalert/dist/sweetalert.css" rel="stylesheet" type="text/css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/core.css" rel="stylesheet" type="text/css">
    <link href="/css/icons.css" rel="stylesheet" type="text/css">
    <link href="/css/components.css" rel="stylesheet" type="text/css">
    <link href="/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/css/menu.css" rel="stylesheet" type="text/css">
    <link href="/css/responsive.css" rel="stylesheet" type="text/css">

    <script src="/js/modernizr.min.js"></script>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>


<body class="fixed-left">
<button onclick="myclick1()">
    button1
</button>
<button onclick="myclick2()">
    button2
</button>
<button onclick="myclick3()">
    button3
</button>
<button onclick="myclick4()">
    button4
</button>
</body>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<script>
    var sock = new SockJS("/endpointSeminar");
    var stomp = Stomp.over(sock);
    stomp.connect(
            {},
            function connectCallback(frame) {
                // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
                alert("连接成功!");
            },
            function errorCallBack(error) {
                // 连接失败时（服务器响应 ERROR 帧）的回调方法
                alert("连接失败");
            }
    );


    // stomp.subscribe('/topic/getResponse', function (response) {
    //     //订阅/topic/getResponse
    //     // 目标发送的消息。这个是在控制器的@SendTo中定义的。
    //     alert(response);
    // });
    // alert(454);


    function question() {
        stomp.send("/app/question", {},
                JSON.stringify({'name': "123456", 'id': 4544}));
        // 当客户端与服务端连接成功后，可以调用send()来发送STOMP消息。这个方法
        // 必须有一个参数，用来描述对应的STOMP的目的地。另外可以有两个可选的参数：
        // headers，object类型包含额外的信息头部；body，一个String类型的参数。
        // client.send("/queue/test", {priority: 9}, "Hello, STOMP");
    }


    function sendNotice() {
        stomp.send("/app/choose", {}, "123");
    }

</script>

<script>
    function myclick1() {
        var curTeamOrder=1;
        var nextTeamOrder=1;
        var x=1;
        var attendanceList=new Array(${enrollList?size}+1);
        var length=${enrollList?size};
    <#list enrollList as attendance>
        <#if attendance.id??>
            attendanceList[x]=${attendance.id};
            x++;
        <#else >
            attendanceList[x]=-1;
            x++;
        </#if>
    </#list>

        for(var i=1;i<=length;i++)
        {
            if(attendanceList[i]!=-1) {
                curTeamOrder = i;
                break;
            }
        }

        for(var i=curTeamOrder+1;i<=length;i++)
        {
            if(attendanceList[i]!=-1)
            {  nextTeamOrder=i;
                break;
            }
        }

        var obj={
          "klassSeminarId":${klassSeminarId},
            "curAttendanceId":attendanceList[curTeamOrder],
            "nextAttendanceId":attendanceList[nextTeamOrder]
        };

        stomp.send("/app/nextPre", {},
                JSON.stringify({'klassSeminarId': "${klassSeminarId}"}));

    }

    function myclick2() {
        stomp.send("/app/question", {},
                JSON.stringify({'id': "2"}));
    }

    function myclick3() {
        stomp.send("/app/question", {},
                JSON.stringify({'id': "3"}));
    }

    function myclick4() {
        stomp.send("/app/question", {},
                JSON.stringify({'id': "4"}));
    }
</script>


</html>