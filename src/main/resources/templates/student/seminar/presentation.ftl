<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/images/favicon_1.ico">

    <title>讨论课</title>

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
<!-- Begin page -->
<div id="wrapper">
    <!-- Top Bar Start -->
    <div class="topbar">
        <!-- LOGO -->
        <!-- Button mobile view to collapse sidebar menu -->
        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="">
                    <div class="pull-left">

                        <button class="button-menu-mobile">
                            <div class="glyphicon glyphicon-menu-left"></div>
                        </button>

                    </div>
                    <div class="pull-left">
                        <div class="button-menu-mobile">
                            OOAD-讨论课
                        </div>
                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
                                <li><a><h4><i class="md md-home"></i>&nbsp;个人页</h4></a></li>
                                <li><a><h4><i class="md md-layers"></i>&nbsp;讨论课</h4></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
    <!-- Top Bar End -->


    <br/>
    <br/>
    <br/>
    <br/>


    <div class="content">
        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default ">
                        <div class="panel-heading">
                            <h3 class="panel-title">

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6">展示小组：</div>
                                    <div id="curPreTeam" class="col-xs-6 col-sm-6"></div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12">${seminar.seminarName}</div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-6 col-sm-6">提问同学数：</div>
                                    <div id="curQueNum" class="col-xs-6 col-sm-6"></div>
                                </div>


                            </h3>
                        </div>

                    </div> <!-- panel-body -->
                </div> <!-- panel -->
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default ">
                        <div class="panel-heading"><h3 class="panel-title">展示顺序</h3></div>
                        <div class="panel-body">

                            <table class="table">

                                <tbody>
                                <#assign x=1>
                                <#list enrollList as attendance>
                                <tr id="attendance${x}" name="attenadnaceId" value=${attendance.teamOrder}>
                                    <td><p>第${attendance.teamOrder}组</p></td>
                                    <#if attendance.id??>
                                    <td><p class="pull-right">${attendance.team.klass.klassSerial}—${attendance.team.teamSerial}</p></td>
                                    <#else >
                                    <td><p class="pull-right">11111</p></td>
                                    </#if>
                                </tr>
                                    <#assign x++>
                                </#list>

                                </tbody>

                            </table>

                            <div>
                                <button class="btn btn-lg btn-default btn-block waves-effect waves-light "
                                        onclick="Question(${myTeamId},${studentId})">Q&A
                                </button>
                            </div>


                            </tr>
                        </div>

                    </div> <!-- panel-body -->
                </div> <!-- panel -->
            </div>

        </div> <!-- End row -->

    </div>

</div>
<!-- END wrapper -->
</body>
<!-- jQuery  -->


<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<script>
    var x=1;
    <#list enrollList as list>
    <#if list.id??>
        <#if list.isPresent==1>
            x=${list.teamOrder}
        </#if>
    </#if>
    </#list>
    var curQueNum = 0; //当前提问数量
    document.getElementById("curQueNum").innerText = curQueNum;
    document.getElementById("curPreTeam").innerText = x;
    var curPre = "attendance" + x;
    document.getElementById(curPre).style = "color:red"; //设置当前小组的颜色为红
    //var canQuestion = 1;  //该名学生能够提问
    var sock = new SockJS("/endpointSeminar");  //连接
    var stomp = Stomp.over(sock); //初始化
    stomp.connect(
            {},
            function connectCallback(frame) {
                // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
                 alert("连接成功!");
                stomp.subscribe('/topic/getResponse' + ${klassSeminarId}, function (response) {
                    //response 处理4种类型的数据
                    //1.增加当前当前提问学生数，在提问小组数+1即可
                    //2.下一组展示，红字颜色改变，提示提问
                    //3.老师按提问，弹出提示框提示某位学生提问
                    //4.老师按结束讨论，提示学生讨论课结束
                    var tmp=JSON.parse(response.body).message;
                    console.log(tmp);
                    if (tmp == 1) {
                        curQueNum++;
                        document.getElementById("curQueNum").innerText = curQueNum;
                    } else if (tmp == 2) {
                        document.getElementById(curPre).style = "color:black";
                        x++;
                        curPre = "attendance" + x;
                        document.getElementById("curPreTeam").innerText="第"+x+"组";
                        document.getElementById(curPre).style = "color:red";
                        curQueNum = 0;
                        document.getElementById("curQueNum").innerText = curQueNum;
                        canQuestion = 1;
                    } else if (tmp== 3) {
                        var questionStu = "请" + response.classSerial + "-" + response.teamSerial + response.studentName + "同学提问";
                        alert(questionStu);
                    } else {
                        alert("该门讨论课结束!");
                        stomp.disconnect();

                    }


                });
            },
            function errorCallBack(error) {
                // 连接失败时（服务器响应 ERROR 帧）的回调方法
                // alert("连接失败");
            }
    );


    function Question(teamId, studentId) {
        if (canQuestion == 1) {
            $.ajax({
                url: "/student/course/seminar/question",
                method: "post",
                data: {
                    "teamId": teamId,
                    "studentId": studentId,
                    <#--"klassSeminarId":${enrollList[0].klassSeminarId}-->
                },
                success: function (data, status) {
                    alert("提问成功!");
                    canQuestion = 0;
                },
                error: function () {
                    alert("提问失败!");
                }

            });
        } else {
            alert("本次展示你已经提问!");
            return false;
        }
    }

</script>


</html>