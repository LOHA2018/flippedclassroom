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
                        <form action="/student/course/team" method="post">
                            <input type="hidden" name="courseId" value="${courseId}">
                            <button class="button-menu-mobile">
                                <div class="glyphicon glyphicon-menu-left"></div>
                            </button>
                        </form>
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
            <!-- col -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form">
                                <form class="cmxform form-horizontal tasi-form" id="commentForm" method="get" action="#"
                                      novalidate="novalidate">
                                    <div class="form-group">
                                        <label class="control-label col-md-2">小组名：</label>
                                        <div class="col-md-10">
                                            <input id="teamName" type="text" class="form-control" placeholder="填写组名">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-2">选择班级</label>
                                        <div class="col-md-10">
                                            <select id="chooseClass" class="select2 form-control" data-placeholder="Choose a Country...">
                                                <#list klassList as list>
                                                    <option value=${list.id}>
                                                        ${list.grade}— (${list.klassSerial})班
                                                    </option>
                                                </#list>
                                            </select>

                                        </div>
                                    </div>


                                </form>
                            </div> <!-- .form -->
                        </div> <!-- panel-body -->
                    </div> <!-- panel -->
                </div> <!-- col -->

            </div> <!-- End row -->

            <div class="row">

                <div class="col-lg-12 col-md-12 col-xs-12">
                    <div class="panel-group panel-group-joined" id="accordion-test">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion-test" href="#collapseOne"
                                       class="collapsed">
                                        添加成员
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <table class="table">

                                        <tbody>
                                        <#list studentList as students>
                                        <tr>
                                            <td>
                                                <div class="checkbox checkbox-primary checkbox-inline">
                                                    <input id="addStudent${students.id}" type="checkbox" name="studentId"
                                                           value=${students.account}>
                                                    <label>
                                                        ${students.account}
                                                    </label>
                                                </div>
                                            </td>

                                            <td>
                                                <p class="pull-right"> ${students.studentName}</p>
                                            </td>
                                        </tr>

                                        </#list>


                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div> <!-- end row -->

            <div class="row">
                <div class="col-lg-12 col-md-12 col-xs-12">
                    <button class="btn btn-lg btn-default btn-block waves-effect waves-light " onclick="submitGroup()">
                        确认提交
                    </button>

                </div>

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
<script>
    function submitGroup() {
        var teamName = document.getElementById("teamName").value;
        var classId = document.getElementById("chooseClass").value;
        console.log(classId);
        var memberList = new Array();
    <#list studentList as students >
        if (document.getElementById("addStudent${students.id}").checked == true) {
            var student = document.getElementById("addStudent${students.id}").value;
            memberList.push(student);
        }
    </#list>
        if (!teamName) {
            alert("字段不能为空!");
            return;
        } else {
            $.ajax({
                        url: "/student/course/team/create",
                        method: "put",
                        data: {
                            "courseId":${courseId},
                            "teamName": teamName,
                            "klassId": classId,
                            "memberList": memberList
                        },
                        success: function () {
                            alert("创建成功!");
                             window.location.href = "/student/course/team?courseId=" + ${courseId}+"&klassId="+${klassId};
                        },
                        error: function () {
                            alert("创建失败");
                        }
                    }
            );
        }

    }


</script>

</body>
</html>