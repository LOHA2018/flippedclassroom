<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>修改讨论课</title>

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
                            修改讨论课
                        </div>
                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
                                <li><a><h4><i class="md md-home"></i>&nbsp;待办</h4></a></li>
                                <li><a href="/teacher/index"><h4><i class="md md-home"></i>&nbsp;个人页</h4></a></li>
                                <li><a href="/teacher/chooseCourse"><h4><i class="md md-layers"></i>&nbsp;讨论课</h4></a></li>
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
<div class="panel-body">

    <label class="control-label col-md-2">讨论课名称</label>
    <div class="col-md-10">
        <input id="seminarName" type="text" class="form-control" value="${seminar.seminarName}">
    </div>
    <br/>


    <label class="control-label col-md-2">讨论课介绍</label>
    <div class="col-md-10">
        <input id="seminarIntroduction" type="text" class="form-control" value="${seminar.introduction}">

    </div>
    <br/>

    <label class="control-label col-md-2">讨论课可见</label>
    <div class="col-md-10">
        <select id="isVisble" class="select2 form-control"
                data-placeholder="Choose a Country...">
            <option value=1>可见</option>
            <option value=0>不可见</option>
        </select>

    </div>
    <br/>

    <label class="control-label col-md-2">展示报名开始时间</label>
    <div class="col-md-10">
        <input id="enrollStartTime" type="text" class="form-control" value="${seminar.enrollStartTime}">
    </div>
    <br/>


    <label class="control-label col-md-2">展示报名截止时间</label>
    <div class="col-md-10">
        <input id="enrollEndTime" type="text" class="form-control" value="${seminar.enrollEndTime}">
    </div>
    <br/>


    <label class="control-label col-md-2">报名小组数限制</label>
    <div class="col-md-10">
        <input id="registerNum" type="text" class="form-control col-md-10" value="${seminar.teamLimit}">
    </div>
    <br/>


    <label class="control-label col-md-2">所属Round</label>
    <div class="col-md-10">
        <select id="roundID" class="select2 form-control" data-placeholder="Choose a Country...">
            <option value=-1>无（默认新建round）</option>
                                    <#list roundList as round>
                                    <option id="round${round.serial}" value=${round.id}>
                                        第${round.roundSerial}轮
                                    </option>
                                    </#list>
        </select>
    </div>
    <br/>


    <label class="control-label col-md-2">书面报告提交截止时间</label>
<div class="col-md-10">

                                <#list klassList as klass>
                                    <label class="control-label col-md-2">${klass.grade}—${klass.klassSerial}</label>
                                    <input id="reportDDL${klass.id}" type="text" class="form-control"
                                           name="klassId" value=${klass.id}>
                            </#list>

                        </div>
                        <br/>

                        <button class="btn btn-lg btn-primary btn-block waves-effect waves-light pull-right"
                                onclick="deleteSeminar()">删除该讨论课
                        </button>
                        <button class="btn btn-lg btn-default btn-block waves-effect waves-light "
                                onclick="modifySeminar()">修改
                        </button>
                        <hr/>


                    </div> <!-- panel-body -->
                </div> <!-- panel -->
            </div> <!-- col -->

        </div> <!-- End row -->
    </div>

</div>

</div>
<!-- END wrapper -->
<!-- jQuery  -->

<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>


<script>
    $(function () {
        $("#preStartTime").datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });
    $(function () {
        $("#preEndTime").datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });
</script>


<script>

    function modifySeminar() {
        var seminarName = document.getElementById("seminarName").value;
        var seminarIntroduction = document.getElementById("seminarIntroduction").value;
        var isVisible = document.getElementById("isVisible").value;
        var enrollStartTime = document.getElementById("enrollStartTime").value;
        var enrollEndTime = document.getElementById("enrollEndTime").value;
        var reportDDLMap = new Map();
    <#list klassList as klass >
        var tmp = "reportDDL" +${klass.id};
        var time = document.getElementById(tmp).value;
        map.set(${klass.id}, time);
    </#list>

        if (!seminarName || !isVisible || !enrollEndTime || !enrollStartTime) {
            alert("字段不能为空!");
        }else {
            $.ajax({
                url:"/teacher/course/seminar/modify",
                method:"post",
                data:{
                    "courseId":${courseId},
                    "seminarName":seminarName,
                    "seminarIntroduction":seminarIntroduction,
                    "isVisible":isVisible,
                    "enrollStartTime":enrollStartTime,
                    "enrollEndTime":enrollEndTime,
                    "reportDDLMap":reportDDLMap
                },
                success:function () {
                    alert("创建成功!");
                },
                error:function () {
                    alert("创建失败!");
                }
            });
        }


    }


</script>


<script>
        <#
        list
        klassList as klass >
    $(function () {
        $("#reportDDL${klass.id}").datetimepicker({
            format: 'YYYY-MM-DD hh:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });
    <
    /#list>

</script>
<script>
    function deleteSeminar() {
        var msg = confirm("是否删除该门讨论课?");
        if (msg == true) {
            $.ajax(
                    {
                        url: "/teacher/course/seminar/delete",
                        method: "post",
                        data: {
                            "klassSeminarId":${klassSeminarId}
                        },
                        success: function () {
                            alert("删除成功");
                            window.location.href = "/teacher/course/seminar";//这个有点问题哈
                        },
                        error: function () {
                            alert("删除失败");
                        }
                    }
            );
        }
    }
</script>


</body>
</html>