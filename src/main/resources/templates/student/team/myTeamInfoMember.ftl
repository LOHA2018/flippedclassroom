<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>组队</title>

    <link href="/plugins/sweetalert/dist/sweetalert.css" rel="stylesheet" type="text/css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/core.css" rel="stylesheet" type="text/css">
    <link href="/css/icons.css" rel="stylesheet" type="text/css">
    <link href="/css/components.css" rel="stylesheet" type="text/css">
    <link href="/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/css/menu.css" rel="stylesheet" type="text/css">
    <link href="/css/responsive.css" rel="stylesheet" type="text/css">


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
                            我的组队
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
            <!-- col -->


            <div class="col-lg-12 col-md-12 col-xs-12">
                <div class="panel panel-default ">
                    <div class="panel-heading"><h3 class="panel-title">${team.klass.klassSerial}-${team.teamSerial}小组</h3></div>
                    <div class="panel-body">

                        <table class="table">

                            <tbody>
                            <tr>
                                <td><p>组长</p></td>
                                <td><p>${team.leader.account}</p></td>
                                <td><p>${team.leader.studentName}</p></td>

                            </tr>

                            <#list team.member as member>

                            <tr>
                                <td><p>组员</p></td>
                                <td><p>${member.account}</p></td>
                                <td><p>${member.studentName}</p></td>


                            </tr>
                            </#list>


                            </tbody>
                        </table>

                        <button class="btn btn-success waves-effect waves-light pull-right"
                                onclick="quitGroup()">退组
                        </button>
                    </div>

                </div> <!-- panel-body -->
            </div> <!-- panel -->

        </div>
    </div>
</div>
<!-- END wrapper -->

<!-- jQuery  -->
<script src="/js/modernizr.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script>
    function quitGroup() {
        var msg = "是否要退出小组？";
        if (confirm(msg) == true) {
            $.ajax({
                url: "/student/course/team/myteam/"+${studentId},
                type: "delete",
                data: {
                    "teamId": ${team.id}
                },
                success: function (data, status) {

                    alert("退出成功");
                    window.location.href = "/student/course/team?klassId=" + ${team.klassId} + "&courseId="+${team.klass.courseId};


                },
                error: function () {
                    alert("退出失败!");

                }

            })
        }

    }


</script>

</body>
</html>