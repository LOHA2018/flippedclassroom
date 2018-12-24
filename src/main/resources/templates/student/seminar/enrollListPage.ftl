<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

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


<body class="fixed-left" onload="init()">
<!-- Begin page -->
<div id="wrapper">
    <!-- Top Bar Start -->
    <div class="topbar">
        <!-- LOGO -->
        <!-- Button mobile view to collapse sidebar menu -->
        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="">
                    <form action="/student/seminar/info" method="post">
                        <input type="hidden" name="klassId" value="${klass.id}">
                        <input type="hidden" name="seminarId" value="${seminarId}">
                        <div class="pull-left">
                            <button class="button-menu-mobile">
                                <div class="glyphicon glyphicon-menu-left"></div>
                            </button>
                        </div>
                    </form>
                    <div class="pull-left">
                        <div class="button-menu-mobile">
                        ${klass.course.courseName}-讨论课
                        </div>
                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
                                <li><a href="/student/index"><h4><i class="md md-home"></i>&nbsp;个人页</h4></a></li>
                                <li><a href="/student/chooseCourse"><h4><i class="md md-layers"></i>&nbsp;讨论课</h4></a>
                                </li>
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
                        <div class="panel-heading"><h3 class="panel-title">讨论课</h3></div>
                        <div class="panel-body">

                            <table class="table">

                                <tbody>

                                <#list enrollList as list>

                                    <#if list.teamId??>


                                <tr>
                                    <td><p>第${list.teamOrder}组</p></td>

                                    <td><p class="pull-right">
                                        ${list.team.klass.klassSerial}—${list.team.teamSerial}小组</p></td>
                                </tr>

                                    <#else>

                                <tr>
                                    <td><p>第${list.teamOrder}组</p></td>

                                    <td>
                                        <p>
                                            <button class="md-trigger btn btn-primary waves-effect waves-light pull-right"
                                                    onclick="register(${list.teamOrder})">可报名
                                            </button>
                                        </p>
                                    </td>
                                </tr>

                                    </#if>
                                </#list>

                                <tr>
                                    <td id="cancelEnroll">
                                        <button class="md-trigger btn btn-primary waves-effect waves-light pull-left"
                                                onclick="cancelEnroll()">
                                            取消报名
                                        </button>
                                    </td>
                                    <td>
                                    </td>
                                </tr>


                                </tbody>
                            </table>
                        </div>

                    </div> <!-- panel-body -->
                </div> <!-- panel -->
            </div>

        </div> <!-- End row -->

    </div>

</div>
<!-- END wrapper -->

<!-- jQuery  -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script>


    function init() {
        var isCancel = false;
    <#if status??>
        isCancel = true;
    </#if>
        if (isCancel == false) {
            document.getElementById("cancelEnroll").innerHTML = "";
        }
    }

    function cancelEnroll() {
        var msg = confirm("是否取消报名?");
        if (msg == true) {
            $.ajax({
                url: "/student/seminar/enrollList/cancel",
                method: "post",
                data: {
                    "klassId": ${klass.id},
                    "seminarId": ${seminarId},
                    "teamId":${myTeamId}
                },
                success: function () {
                    alert("取消报名成功!");
                    window.location.href = "/student/seminar/info?klassId=${klass.id}&seminarId=${seminarId}";
                },
                error: function () {
                    alert("取消报名失败!");
                }
            });
        }

    }


    function register(order) {
        var msg = confirm("确认报名第" + order + "组展示?");
        if (msg == true) {
            $.ajax({
                url: "/student/seminar/enrollList/enroll",
                method: "post",
                data: {
                    "klassId": ${klass.id},
                    "seminarId": ${seminarId},
                    "teamId":${myTeamId},
                    "teamOrder": order
                },
                success: function (data, status) {
                    alert("报名成功!");
                    window.location.href = "/student/seminar/info?klassId=${klass.id}&seminarId=${seminarId}";
                },
                error: function () {
                    alert("报名失败!");

                }

            });
        }
    }

</script>
</body>
</html>