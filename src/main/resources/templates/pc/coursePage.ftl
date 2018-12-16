<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>个人信息综合管理平台</title>

    <!-- Plugin Css-->

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
        <div class="topbar-left">
            <div class="text-center">
                <!--这个想做成可以点击刷新-->
                <div class="logo"><i class="md md-terrain"></i> <span>个人信息综合管理平台</span></div>
            </div>
        </div>
        <!-- Button mobile view to collapse sidebar menu -->
        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="">

                    <ul class="nav navbar-nav navbar-right pull-right">

                        <li>
                            <a href="login.html" id="btn-fullscreen" class="waves-effect"><i
                                    class="md md-settings-power"></i></a>
                        </li>

                    </ul>
                    <ul class="nav navbar-nav navbar-right pull-right">

                        <li>
                            <a href="login.html"  class="waves-effect"><i class="md md-home"></i></a>
                        </li>

                    </ul>




                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
    <!-- Top Bar End -->


    <!-- ========== Left Sidebar Start ========== -->

    <div class="left side-menu">
        <div class="sidebar-inner slimscrollleft">
            <div class="user-details">
                <div class="pull-left">
                    <img src="/img/avatar-1.jpg" alt="" class="thumb-md img-circle">
                </div>
                <div class="user-info">
                    <div class="dropdown">
                        <div class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">全场最帅的周健</div>
                        <ul class="dropdown-menu">

                        </ul>
                    </div>

                    <p class="text-muted m-0">Teacher</p>
                </div>
            </div>
            <!--- Divider -->
            <div id="sidebar-menu">
                <ul>
                    <li class="has_sub">
                        <button style="border:transparent;font-size: 15px" class="btn btn-default btn-block btn-lg " onclick="importStudent()">
                            <span> 导入学生名单</span></button>
                        <button style="border:transparent;font-size: 15px" class="btn btn-default btn-block btn-lg " onclick="seminarPage()">
                            <span> 讨论课</span></button>
                        <button style="border:transparent;font-size: 15px"
                                class="btn btn-default btn-block btn-lg " onclick="exportGrade()"></i><span> 导出成绩 </span></button>

                    </li>

                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- Left Sidebar End -->


    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <!-- ============================================================== -->

    <iframe id="teacherFrame" fit="true" src="/teacherpc/course/seminar?courseId=${courseId}" scrolling="no" frameborder="no" height="1100px"
            width="100%"></iframe>


    <!-- ============================================================== -->
    <!-- End Right content here -->
    <!-- ============================================================== -->



</div>
<!-- END wrapper -->

<script>
    function importStudent() {
        var url="/teacherpc/course/importStudent?courseId=${courseId}";
        document.getElementById("teacherFrame").src=url;
    }
    function seminarPage() {
        var url="/teacherpc/course/seminar?courseId=${courseId}";
        document.getElementById("teacherFrame").src=url;
    }
    function exportGrade() {
        var url="/teacherpc/course/exportScore?courseId=${courseId}";
        document.getElementById("teacherFrame").src=url;

    }


</script>


</body>
</html>