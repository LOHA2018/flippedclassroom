<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>新建讨论课</title>

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
                            新建讨论课
                        </div>
                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
                                <li><a><h4><i class="md md-home"></i>&nbsp;待办</h4></a></li>
                                <li><a href="/teacher/index"><h4><i class="md md-home"></i>&nbsp;个人页</h4></a></li>
                                <li><a href="/teacher/seminar"><h4><i class="md md-layers"></i>&nbsp;讨论课</h4></a></li>
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
            <!-- col -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">

                        <div class="panel-heading"><h3 class="panel-title">新建讨论课</h3></div>
                    <div class="panel-body">
                        <label>讨论课名称</label>
                        <input id="seminarName" type="text" class="form-control"/>
                        <label>讨论课要求</label>
                        <input id="seminarIntroduction" type="text" class="form-control"/>


                        <label class="control-label ">讨论课次序号</label>
                        <input id="seminarOrder" type="text" class="form-control" name="seminarOrder"
                               value="">


                        <label class="control-label ">讨论课可见</label>

                        <select id="isVisible" class="select2 form-control" data-placeholder="Choose a Country...">
                            <option value=1>可见</option>
                            <option value=0>不可见</option>
                        </select>

                        <hr/>


                        <label class="control-label">展示报名开始时间</label>

                        <input id="registerStartTime" type="text" class="form-control">


                        <label class="control-label ">展示报名截止时间</label>
                        <input id="registerEndTime" type="text" class="form-control">


                        <label class="control-label">报名小组数限制</label>
                        <input id="registerNum" type="text" class="form-control">




                        <label class="control-label">所属Round</label>

                        <select id="roundID" class="select2 form-control"
                                data-placeholder="Choose a Country...">
                            <option value=-1>无（默认新建round）</option>
                                <#list roundList as round>
                                <option value=${round.id}>第${round.roundSerial}轮</option>
                                </#list>
                        </select>
                        <hr/>


                        <br/>


                        <button class="btn btn-lg btn-default btn-block waves-effect waves-light"
                                onclick="restoreSeminar()">发布
                        </button>
                    </div> <!-- panel-body -->
                </div> <!-- panel -->
            </div> <!-- col -->

        </div> <!-- End row -->
    </div>

</div>

</div>
<!-- END wrapper -->

<!-- jQuery  -->
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>


<script>
    $(function () {
        $("#registerStartTime").datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });
    $(function () {
        $("#registerEndTime").datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });
</script>


<script>
    function restoreSeminar() {

        var seminarName = document.getElementById("seminarName").value;
        var seminarIntroduction = document.getElementById("seminarIntroduction").value;
        var seminarOrder = document.getElementById("seminarOrder").value;
        var isVisible = document.getElementById("isVisible").value;//1可见  0不可见
        var registerStartTime = document.getElementById("registerStartTime").value;
        var registerEndTime = document.getElementById("registerEndTime").value;
        var maxTeam = document.getElementById("registerNum").value;
        var roundId = document.getElementById("roundID").value;

        if (!seminarName || !isVisible || !registerStartTime || !registerEndTime) {
            alert("字段不能为空!");
        }else {
            var obj = {
                "courseId":${courseId},
                "roundId": roundId,
                "seminarName": seminarName,
                "introduction": seminarIntroduction,
                "teamLimit": maxTeam,
                "seminarSerial": seminarOrder,
                "isVisible": isVisible,
                "enrollStartTime": registerStartTime,
                "enrollEndTime": registerEndTime
            };

            $.ajax({
                url: "/teacher/seminar/create",
                method: "put",
                data: JSON.stringify(obj),
                contentType: "application/json;charset=utf-8",
                success: function () {
                    alert("创建成功!");
                },
                error: function () {
                    alert("创建失败!");
                }
            });

        }
    }
</script>

</body>
</html>