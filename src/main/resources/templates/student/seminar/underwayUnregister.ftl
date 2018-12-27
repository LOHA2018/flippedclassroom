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
                        <form action="/student/seminar" method="post">
                            <input type="hidden" name="klassId" value="${klass.id}">
                            <input type="hidden" name="courseId" value="${seminar.courseId}">
                            <button class="button-menu-mobile">
                                <div class="glyphicon glyphicon-menu-left"></div>
                            </button>
                        </form>

                    </div>
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
                                <li><a href="/student/chooseCourse"><h4><i class="md md-layers"></i>&nbsp;讨论课</h4></a></li>
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
                                <tr>
                                    <td><p>轮次：第${round.roundSerial}轮</p></td>

                                </tr>
                                <tr>
                                    <td><p>主题：${seminar.seminarName}</p></td>
                                </tr>
                                <tr>
                                    <td><p>课次序号：第${seminar.seminarSerial}次</p></td>
                                </tr>
                                <tr>
                                    <td><p>要求：${seminar.introduction}</p></td>
                                </tr>
                                <tr>
                                    <td>
                                        <p>课程情况：正在进行
                                        <form action="/student/seminar/info/registerInfo" method="post">
                                            <input type="hidden" name="klassId" value=${klass.id}>
                                            <input type="hidden" name="seminarId" value=${seminar.id}>
                                            <button class="md-trigger btn btn-primary waves-effect waves-light pull-right"
                                                    type="submit">
                                                报名情况
                                            </button>
                                        </form>
                                        </p>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <form action="/student/seminar/progressing" method="post">
                                            <input type="hidden" name="seminarId" value=${seminar.id}>
                                            <input type="hidden" name="klassId" value=${klass.id}>
                                            <input type="hidden" name="courseId" value=${klass.courseId}>
                                            <button class="btn btn-lg btn-default btn-block waves-effect waves-light ">
                                                进入讨论课
                                            </button>
                                        </form>
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

</body>
</html>