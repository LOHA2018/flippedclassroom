<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="assets/images/favicon_1.ico">

    <title>学生成绩</title>

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

                        <form action="/teacher/course" method="get">
                            <div class="button-menu-mobile">
                                学生成绩
                            </div>
                        </form>


                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
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

                <div class="col-lg-12 col-md-12 col-xs-12">
                    <div class="panel-group panel-group-joined" id="accordion-test">
                        <div class="panel panel-default panel-fill">
                            <#assign x=0>

                            <#list roundTeamSeminarList as round>
                            <#assign x++>
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion-test" href="#round${x}"
                                       class="collapsed">
                                        第${x}轮
                                    </a>
                                </h4>
                            </div>
                            <#list round as teams>

                            <div id="round${x}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="panel-group panel-group-joined" id="accordion-test1">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion-test1"
                                                       href="#team${teams.team.id}" class="collapsed">
                                                        ${teams.team.klass.klassSerial}—${teams.team.teamSerial}:${teams.roundScore.totalScore}
                                                    </a>
                                                </h4>
                                            </div>

                                            <#list teams.seminarScores as seminars>
                                            <div id="team${teams.team.id}" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <label>${seminars.seminar.seminarName}</label>
                                                    <table class="table">

                                                        <tbody>
                                                        <tr>
                                                            <td>展示：${seminars.presentationScore}</td>
                                                            <td>提问：${seminars.questionScore}</td>
                                                            <td>报告：${seminars.reportScore}</td>
                                                        </tr>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </#list>

                                    </div>


                                </div>
                            </div>

                        </#list>
                    </#list>
                </div>

            </div>
        </div>
    </div> <!-- end row -->
</div> <!-- End row -->
</div>

</div>
<!-- END wrapper -->
</div>
<!-- jQuery  -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

</body>
</html>