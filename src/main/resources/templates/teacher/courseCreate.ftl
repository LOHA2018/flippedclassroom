<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>新建课程</title>
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

    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet">
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
                                <div class="glyphicon glyphicon-menu-left">
                                </div>
                            </button>
                        </div>
                    </form>

                    <div class="pull-left">
                        <div class="button-menu-mobile">
                            新建课程
                        </div>
                    </div>

                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
                                <li><a><h4><i class="md md-home"></i>&nbsp;待办</h4></a></li>
                                <li><a><h4><i class="md md-home"></i>&nbsp;个人页</h4></a></li>
                                <li><a><h4><i class="md md-layers"></i>&nbsp;讨论课</h4></a></li>
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


                            <label>课程名称</label>
                            <input id="courseName" type="text" class="form-control" name="courseName" value=""/>
                            <label>课程要求</label>
                            <input id="introduction" type="text" class="form-control" name="introduction" value=""/>
                            <hr/>


                            <label>成绩计算规则</label>


                            <div class="row">
                                <div class="col-xs-3 col-sm-3">课堂展示
                                </div>
                                <div class="col-xs-9 col-sm-9">
                                    <input id="presentationPercentage" type="text"
                                           class="form-control"
                                           name="presentationPercentage" value="40"/>
                                </div>
                            </div>

                            <br/>

                            <div class="row">
                                <div class="col-xs-3 col-sm-3">课堂提问
                                </div>
                                <div class="col-xs-9 col-sm-9">
                                    <input id="questionPercentage" type="text" class="form-control"
                                           name="questionPercentage" value="30"/>
                                </div>
                            </div>

                            <br/>

                            <div class="row">
                                <div class="col-xs-3 col-sm-3">书面报告
                                </div>
                                <div class="col-xs-9 col-sm-9">
                                    <input id="reportPercentage" type="text" class="form-control"
                                           name="reportPercentage" value="30"/>
                                </div>
                            </div>


                            <hr/>


                            <label>组员基本要求</label>
                            <br/>

                            <label>小组总人数</label>
                            <div class="row">
                                <div class="col-xs-6 col-sm-6">
                                    <input id="minMember" type="text" class="form-control" placeholder="下限"/>
                                </div>
                                <div class="col-xs-6 col-sm-6">
                                    <input id="maxMember" type="text" class="form-control" placeholder="上限"/>
                                </div>

                            </div>

                            <label>组内选修课程人数</label>
                            <div class="row">
                                <div class="col-xs-6 col-sm-6">
                                    <input type="text" class="form-control" placeholder="下限"/>
                                </div>
                                <div class="col-xs-6 col-sm-6">
                                    <input type="text" class="form-control" placeholder="上限"/>
                                </div>

                            </div>



                            <button class="btn  btn-primary  waves-effect waves-light pull-right">新增
                            </button>


                            <br/>
                            <br/>
                            <hr/>
                            <label>冲突课程</label>
                            <div class="form-group">
                                <div>
                                    <select class="select2 form-control">
                                        <option value="1">张老师OOAD</option>
                                    </select>
                                </div>
                            </div>
                            <button class="btn  btn-primary  waves-effect waves-light pull-right">新增
                            </button>
                            <br/>
                            <br/>
                            <hr/>





                            <label>组队开始时间</label>
                            <input id="teamStartTime" type='text' class="form-control"/>


                            <label>组队截止时间</label>
                            <input id="teamEndTime" type="text" class="form-control"/>


                            <hr/>
                            <button class="btn btn-lg btn-default btn-block waves-effect waves-light "
                                    onclick="publishCourse()">发布
                            </button>

                        </div>


                    </div>
                </div>
            </div>
        </div> <!-- col -->


    </div> <!-- End row -->

</div>

</div>
<!-- END wrapper -->

<!-- jQuery  -->
<script src="/js/modernizr.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>

<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>


<script>
    function publishCourse() {
        var startDate = document.getElementById("teamStartTime").value;
        var endDate = document.getElementById("teamEndTime").value;
        var courseName = document.getElementById("courseName").value;
        var introduction = document.getElementById("introduction").value;
        var maxNum = document.getElementById("maxMember").value;
        var minNum = document.getElementById("minMember").value;
        var presentationPercentage = document.getElementById("presentationPercentage").value;
        var questionPercentage = document.getElementById("questionPercentage").value;
        var reportPercentage = document.getElementById("reportPercentage").value;
        var sum = parseInt(presentationPercentage) + parseInt(questionPercentage) + parseInt(reportPercentage);
        var numReg = /^(0|[1-9]\d*)\b/;

        if (!startDate || !endDate || !courseName || !presentationPercentage ||
                !questionPercentage || !reportPercentage) {
            alert("字段不能为空!");
        } else if (sum != 100) {
            alert("成绩计算和不为100!");
        } else if (!numReg.test(maxNum) || !numReg.test(minNum) || maxNum < minNum) {
            alert("人数限制不正确!")
        } else {
            $.ajax({
                url: "/teacher/course/create/save",
                method: "post",
                data: {
                    "startDate": startDate,
                    "endDate": endDate,
                    "courseName": courseName,
                    "introduction": introduction,
                    //"maxNum":maxNum,
                    //"minNum":minNum,
                    "presentationPercentage": presentationPercentage,
                    "questionPercentage": questionPercentage,
                    "reportPercentage": reportPercentage,

                    //"sum": sum
                },
                success: function () {
                    alert("创建课程成功!");
                    window.location.href = "/teacher/course";
                },
                error: function () {
                    alert("创建课程失败!");

                }
            })
        }

    }


    $(function () {
        $("#teamStartTime").datetimepicker({
            format: 'YYYY-MM-DD hh:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });
    $(function () {
        $("#teamEndTime").datetimepicker({
            format: 'YYYY-MM-DD hh:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });

</script>


</body>
</html>