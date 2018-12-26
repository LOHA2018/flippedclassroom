<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>轮次设置</title>


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
                    <form action="/teacher/course" method="get">
                        <div class="pull-left">
                            <button class="button-menu-mobile">
                                <div class="glyphicon glyphicon-menu-left"></div>
                            </button>
                        </div>
                    </form>
                    <div class="pull-left">
                        <div class="button-menu-mobile">
                            第${round.roundSerial}轮
                        </div>
                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
                                <li><a><h4><i class="md md-info"></i>&nbsp;待办</h4></a></li>
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
<div class="col-md-12 col-sm-12">
<div class="panel panel-default">
<div class="panel-body">

    <label class="control-label">本轮讨论课:</label>


    <p>
                                <#list round.seminars as seminar>
                                    <br/>
                                    ${seminar.seminarName}
                                </#list>
    </p>


    <hr/>

    <label>成绩设置:</label>
    <br/>

    <label class="control-label">展示:</label>

    <select id="presentationMethod" class="select2 form-control">
        <option value="1">最高分</option>
        <option value="0">平均分</option>
    </select>

    <label class="control-label ">提问:</label>

    <select id="questionMethod" class="select2 form-control"
            data-placeholder="Choose a Country...">
        <option value="1">最高分</option>
        <option value="0">平均分</option>
    </select>


    <label class="control-label">报告:</label>

    <select id="reportMethod" class="select2 form-control">
        <option value="1">最高分</option>
        <option value="0">平均分</option>
    </select>


    <hr/>


    <label>本轮讨论课报名次数:</label>
    <br/>
                            <#assign size=round.seminars?size>
                            <#list klassList as klass>
                            <div class="row">
                                <div class="col-xs-4 col-sm-4">
                                    <label class="control-label ">${klass.grade}—${klass.klassSerial}:</label>
                                </div>
                                <div class="col-xs-8 col-sm-8">
                                    <select id="klass${klass.id}" class="select2 form-control">
                                        <#list 1..size as t>
                                            <option value=${t}>${t}</option>
                                        </#list>
                                    </select>
                                </div>

                            </div>
                            <!--</-->
                                </#list>
                            <br/>

                            <button class="btn btn-lg btn-default btn-block waves-effect waves-light "
                                    onclick="modifyRound()">修改
                            </button>


                        </div> <!-- panel-body -->
                    </div> <!-- panel -->
                </div> <!-- col -->

            </div> <!-- End row -->

        </div>
    </div>

</div>
<!-- END wrapper -->

<!--</button>-->
<!-- jQuery  -->

<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

<script>
    function modifyRound() {
        var presentationMethod = document.getElementById("presentationMethod").value;
        var questionMethod = document.getElementById("questionMethod").value;
        var reportMethod = document.getElementById("reportMethod").value;

        var klassList={};
        var temp;
        <#list klassList as list>
            temp=document.getElementById("klass${list.id}").value;
            klassList["${list.id}"]=temp;
        </#list>

        var roundSettingDTO={
            "roundId":${round.id},
            "presentationMethod":presentationMethod,
            "questionMethod":questionMethod,
            "reportMethod":reportMethod,
            "map":klassList
        };

        $.ajax({
            url: "/teacher/seminar/courseSeminar/roundSetting/modify",
            method: "post",
            data: JSON.stringify(roundSettingDTO),
            contentType: "application/json;charset=utf-8",
            success: function () {
                alert("修改成功!");
            },
            error: function () {
                alert("修改失败!");
            }
        });

    }
</script>


</body>
</html>