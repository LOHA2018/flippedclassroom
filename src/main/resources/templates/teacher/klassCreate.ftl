<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>新建班级</title>

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

                        <form action="/teacher/course/klassList" method="post">
                            <input type="hidden" name="courseId" value="${courseId}">
                            <button class="button-menu-mobile">
                                <div class="glyphicon glyphicon-menu-left"></div>
                            </button>
                        </form>
                    </div>
                    <div class="pull-left">
                        <div class="button-menu-mobile">
                            创建班级
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
                        <div class="panel-body">

                            <table class="table">
                                <tbody>
                                <tr>
                                    <td>

                                        <label>班级名：</label>

                                        <input id="grade" type="text" class="form-control" placeholder="年级" name="grade"
                                               value="">

                                        <input id="klassSerial" type="text" class="form-control" placeholder="班级"
                                               name="klassSerial" value="">

                                    </td>

                                </tr>
                                <tr>
                                    <td><label>上课时间：</label>
                                        <input id="klassTime" type="text" class="form-control" name="klassTime"
                                               value=""></td>
                                </tr>
                                <tr>
                                    <td><label>上课地点：</label>
                                        <input id="klassLocation" type="text" class="form-control"></td>

                                </tr>
                                <tr>
                                    <td><label>班级学生名单：</label>
                                        <input id="importStudent" type="file"/></td>
                                </tr>

                                </tbody>

                            </table>

                            <button class="btn btn-primary waves-effect waves-light pull-right"
                                    onclick="storeClass()">保存
                            </button>

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

<script>
    function storeClass() {
        var grade = document.getElementById("grade").value;
        var klassSerial = document.getElementById("klassSerial").value;
        var klassTime = document.getElementById("klassTime").value;
        var klassLocation = document.getElementById("klassLocation").value;
        var fileObj = document.getElementById("importStudent").files[0];
        if (grade == "" || klassLocation == "" || klassSerial == "" || klassTime == "") {
            alert("字段不能为空！");
            return false;
        } else {
            var formData = new FormData();
            var fileName = fileObj.name;
            if (fileName.indexOf("xls") < 0 || fileName.indexOf("xlsx") < 0) {
                alert("文件格式错误");
                return false;
            }
            formData.append('file', fileObj);
            formData.append('grade', grade);
            formData.append('klassSerial', klassSerial);
            formData.append('klassLocation', klassLocation);

            $.ajax({
                url: "/teacher/course/klass",
                type: "POST",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (data, status) {
                    alert("创建成功!");
                    window.location.href="/teacher/course/klass";
                },
                error: function (data, status) {
                    alert("创建失败!");
                }
            });
        }

    }
</script>


</body>
</html>