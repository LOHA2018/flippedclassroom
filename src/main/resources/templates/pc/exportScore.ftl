<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="assets/images/favicon_1.ico">

    <title>个人信息综合管理平台</title>

    <!-- Plugin Css-->
    <link rel="stylesheet" href="assets/plugins/magnific-popup/dist/magnific-popup.css">
    <link rel="stylesheet" href="assets/plugins/jquery-datatables-editable/datatables.css">

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
                    <h4 class="pull-left page-title">${courseName}-查看成绩</h4>
                </div>
            </div>


            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default ">
                        <div class="panel-heading"><h3 class="panel-title">查看成绩</h3></div>

                        <div class="panel-body">
                            <div class="form">
                                <div class="form-group  form-horizontal ">
                                    <label class="control-label col-lg-2 ">轮数</label>
                                    <div class="col-lg-10">
                                        <select class="select2 form-control" onclick="findGrade()">
                                            <#list roundList as r>
                                                <option>${r}</option>
                                            </#list>
                                        </select>

                                    </div>
                                </div>

                                <br/>
                                <br/>
                                <br/>

                                <div class="form-group">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th>小组总分</th>
                                                    <th>参与讨论课</th>
                                                    <th>发言成绩</th>
                                                    <th>提问成绩</th>
                                                    <th>报告成绩</th>
                                                    <th>该次总成绩</th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <#list roundList as list>
                                                <tr>
                                                    <td rowspan="2">1</td>
                                                    <td>fsd</td>
                                                    <td>Alex</td>
                                                    <td>Alex</td>
                                                    <td>Alex</td>
                                                    <td>Alex</td>
                                                </tr>

                                                <tr>
                                                    <td>${list}</td>
                                                    <td>fsd</td>
                                                    <td>Alex</td>
                                                    <td>Alex</td>
                                                    <td>Alex</td>
                                                </tr>
                                                </#list>



                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <button class="btn btn-success waves-effect waves-light pull-right" type="submit">
                                        导出为excel
                                    </button>
                                </div>

                            </div>


                        </div> <!-- panel-body -->
                    </div> <!-- panel -->
                </div> <!-- col -->
            </div>


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