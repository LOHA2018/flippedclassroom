<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>班级信息</title>

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
                        <form action="/teacher/course" method="get">
                            <button class="button-menu-mobile">
                                <div class="glyphicon glyphicon-menu-left"></div>
                            </button>
                        </form>
                    </div>
                    <div class="pull-left">
                        <div class="button-menu-mobile">
                            班级信息
                        </div>
                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                            <ul class="dropdown-menu dropdown-menu-lg">
                                <li><a><h4><i class="md md-info"></i>&nbsp;待办</h4></a></li>
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

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default ">
                        <div id="tableParent" class="panel-body">


                            <#list klassList as klass>
                            <table id="klass${klass.id}" class="table">
                                <label id="klassLabel${klass.id}">${klass.grade}——${klass.klassSerial}</label>
                                <tbody>
                                <tr>
                                    <td>讨论课时间：
                                        <div class="pull-right">${klass.time}</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>讨论课地点：
                                        <div class="pull-right">${klass.location}</div>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12">
                                                <input id="klass${klass.id}" type="file"/>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12">
                                                <button class="md-trigger btn btn-purple waves-effect waves-light pull-right"
                                                        onclick="deleteClass(${klass.id})">
                                                    删除班级
                                                </button>
                                                <button class="md-trigger btn btn-primary waves-effect waves-light pull-right"
                                                        onclick="resetStudentList(${klass.id})">
                                                    提交
                                                </button>
                                            </div>
                                        </div>


                                    </td>
                                </tr>

                                </tbody>

                            </table>

                            </#list>

                            <form action="/teacher/course/klass/create" method="post">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button class="btn btn-default btn-block btn-lg waves-effect waves-light pull-right"
                                        type="submit">新增班级
                                </button>
                            </form>


                        </div>

                    </div> <!-- panel-body -->
                </div> <!-- panel -->
            </div>

        </div> <!-- End row -->

    </
</div>

</div>
<!-- END wrapper -->

<!-- jQuery  -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script>
    function resetStudentList(klassId) {
        var klassFile = "klass" + klassId;

        var fileObj = document.getElementById(klassFile).files[0];


        if (!fileObj) {
            alert("文件不能为空!");
            return;
        }
        else {

            var fileName = fileObj.name;
            if (!fileName.indexOf("xls") || !fileName.indexOf("xlsx")) {
                alert("文件格式不正确!");
                return
            }


            var formData = new FormData();
            formData.append('file', fileObj);
            formData.append('klassId', klassId);


            $.ajax({
                url: "/teacher/course/klassList/save",
                type: "POST",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (data, status) {
                    alert("创建成功!");
                },
                error: function (data, status) {
                    alert("创建失败!");
                }
            });
        }
    }
</script>

<script>
    function deleteClass(klassId) {
        var msg = confirm("是否删除该班级?");
        if (msg == true) {
            $.ajax({
                url: "/teacher/course/klass/delete",
                method: "post",
                data: {
                    "klassId": klassId
                },
                success: function () {
                    alert("删除成功!");
                    var tmp = "klass" + klassId;
                    var tmp2 = "klassLabel" + klassId;
                    document.getElementById(tmp).innerHTML = "";
                    document.getElementById(tmp2).innerHTML = "";
                    var parent=document.getElementById("tableParent");
                    var child=document.getElementById(tmp);
                    parent.removeChild(child);
                },
                error: function () {
                    alert("删除失败!");
                }
            });
        }

    }
</script>
</body>
</html>