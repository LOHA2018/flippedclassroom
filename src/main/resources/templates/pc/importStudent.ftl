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
                    <h4 class="pull-left page-title">${courseName}-导入学生名单</h4>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default ">
                        <div class="panel-heading"><h3 class="panel-title">班级列表</h3></div>
                        <div class="panel-body">
                            <table class="table">
                                <tbody>

                                <#list klassList as klass>
                                <tr>
                                    <td>${klass.klassSerial}班</td>
                                    <td>
                                        <input id=${klass.id} type="file" name="file">
                                    </td>

                                    <td>

                                        <button id="reset${klass.id}"class="md-trigger btn btn-default waves-effect waves-light pull-right"
                                                onclick="resetStudentList(${klass.id})" disabled="disabled">
                                            重置
                                        </button>
                                        <button id="submit${klass.id}" class="md-trigger btn btn-default waves-effect waves-light pull-right"
                                                onclick="submitFile(${klass.id})" >
                                            提交
                                        </button>
                                    </td>


                                </tr>
                                </#list>

                                </tbody>

                            </table>
                        </div> <!-- panel-body -->
                    </div> <!-- panel -->
                </div> <!-- col -->

            </div> <!-- End row -->


        </div>


    </div> <!-- content -->

    <footer class="footer text-right">
        2018 © LOHA
    </footer>

</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>

<script>

    function submitFile(classId) {
        alert(classId);
        var fileObj = document.getElementById(classId).files[0];

        if (!fileObj) {
            alert("请选择文件!");
            return false;
        }
        else {


            var formData = new FormData();

            formData.append('file', fileObj);
            formData.append('classId',classId);

            alert(fileObj.name);

            $.ajax({
                url: "/teacherpc/course/importStudent",
                type: "POST",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (data, status) {
                    alert(data);
                },
                error: function (data, status) {
                    alert(data);
                }
            });
        }
    }
    function resetStudentList(classId) {
        $.ajax({
            url:"/teacherpc/course/importStudent/reset",
            type:"POST",
            data:{
                "classId":classId
            },
            success:function (data,status) {
                alert(data);
                if(data.code=="重置成功")
                {
                    var s1="submit"+classId;
                    var s2="reset"+classId;
                    document.getElementById(s1).disabled="true";
                    document.getElementById(s2).disabled="false";
                }

            }
        })

    }
</script>


</body>
</html>