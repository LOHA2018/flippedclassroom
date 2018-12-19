<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>邮箱设置</title>

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
                        <form action="/teacher/setting" method="get">
                            <button class="button-menu-mobile">
                                <div class="glyphicon glyphicon-menu-left"></div>
                            </button>
                        </form>
                    </div>
                    <div class="pull-left">
                        <div class="button-menu-mobile">
                            邮箱修改
                        </div>
                    </div>

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
                        <div class="panel-heading"><h3 class="panel-title">邮箱修改</h3></div>
                        <div class="panel-body">
                            <div class="form">
                                <div class="cmxform form-horizontal tasi-form" id="commentForm" novalidate="novalidate">

                                    <div class="form-group">
                                        <label class="control-label col-md-2">新邮箱</label>
                                        <div class="col-md-10">
                                            <input class="form-control" id="newEmail" type="email" name="email" value="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-2"></label>
                                        <div class="col-md-10">
                                            <div id="myAlert2">
                                            </div>
                                        </div>
                                    </div>






                                    <div class="form-group">
                                        <div class="col-md-offset-2 col-md-10">
                                            <button class="btn btn-success waves-effect waves-light"
                                                    onclick="submitEmail()">确认提交
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div> <!-- .form -->
                        </div> <!-- panel-body -->
                    </div> <!-- panel -->
                </div> <!-- col -->

            </div> <!-- End row -->


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
    function submitEmail() {
        var newEmail=document.getElementById("newEmail").value;
        var email_reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if(!email_reg.test(newEmail))
        {
            document.getElementById("myAlert2").innerHTML="<strong>邮箱格式错误!</strong>";
            return ;
        }else
        {
            $.ajax({
                url:"/teacher/setting/modifyEmail",
                type:"post",
                data:{
                    "email":newEmail
                },
                success:function (data,status) {
                    alert("修改成功");
                },
                error:function (data,status) {
                    alert("修改失败");
                }
            });
        }

    }
</script>


</body>
</html>

