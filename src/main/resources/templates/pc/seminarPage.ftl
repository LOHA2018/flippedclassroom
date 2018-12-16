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

<div class="content-page">
    <!-- Start content -->
    <div class="content">
        <div class="container">
            <!-- Page-Title -->
            <div class="row">
                <div class="col-sm-12">
                    <h4 class="pull-left page-title">${courseName}-讨论课</h4>
                </div>
            </div>

        <#list roundAndSeminarList as list>
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">第${list.roundSerial}轮讨论课</h3></div>
                        <div class="panel-body">
                            <table class="table">

                                <tbody>
                                <#list list.seminars as list2>
                                <tr>
                                    <td>第${list2.seminarSerial}次讨论课——${list2.seminarName}</td>
                                    <td>
                                        <form action="/teacherpc/course/seminar/info" method="post">
                                            <input type="hidden" name="seminarStatus" value="inSeminar">
                                            <button class="md-trigger btn btn-primary waves-effect waves-light pull-right"
                                                    type="submit">
                                                进入
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div> <!-- panel-body -->
                    </div> <!-- panel -->
                </div> <!-- col -->

            </div> <!-- End row -->
        </#list>
        </div>


    </div> <!-- content -->

    <footer class="footer text-right">
        2018 © LOHA
    </footer>

</div>

</div>
<!-- END wrapper -->

<script>
</script>
</body>
</html>