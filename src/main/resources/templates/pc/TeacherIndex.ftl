<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>个人信息综合管理平台</title>

    <!-- Plugin Css-->
    <link rel="stylesheet" href="/plugins/magnific-popup/dist/magnific-popup.css">
    <link rel="stylesheet" href="/plugins/jquery-datatables-editable/datatables.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/core.css" rel="stylesheet" type="text/css">
    <link href="/css/icons.css" rel="stylesheet" type="text/css">
    <link href="/css/components.css" rel="stylesheet" type="text/css">
    <link href="/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/css/menu.css" rel="stylesheet" type="text/css">
    <link href="/css/responsive.css" rel="stylesheet" type="text/css">


</head>


<body class="fixed-left">
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
                            <a href="login.html" id="btn-fullscreen" class="waves-effect"><i class="md md-settings-power"></i></a>
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
    <br/>


    <div class="content">
        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default ">
                        <div class="panel-heading"><h3 class="panel-title">选择课程</h3></div>
                        <div class="panel-body">

                            <table class="table">
                                <tbody>
                                <#list courseList as List>
                                <tr>
                                    <td>${List.courseName}</td>
                                    <td>
                                        <form action="/teacherpc/course" method="post">
                                            <input type="hidden" name="courseId" value="${List.id}"/>
                                            <button class="md-trigger btn btn-primary waves-effect waves-light pull-right">
                                                进入
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                </#list>

                                </tbody>
                            </table>
                        </div>


                    </div> <!-- panel-body -->
                </div> <!-- panel -->
            </div> <!-- col -->

        </div> <!-- End row -->


    </div>
</div>


<!-- END wrapper -->

<script>


</script>

</body>
</html>