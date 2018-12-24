<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>组队</title>

    <link href="/plugins/select2/dist/css/select2.css" rel="stylesheet" type="text/css">
    <link href="/plugins/select2/dist/css/select2-bootstrap.css" rel="stylesheet" type="text/css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/core.css" rel="stylesheet" type="text/css">
    <link href="/css/icons.css" rel="stylesheet" type="text/css">
    <link href="/css/components.css" rel="stylesheet" type="text/css">
    <link href="/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/css/menu.css" rel="stylesheet" type="text/css">
    <link href="/css/responsive.css" rel="stylesheet" type="text/css">

    <script src="/js/modernizr.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>


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
                        <button class="button-menu-mobile">
                            <div class="glyphicon glyphicon-menu-left"></div>
                        </button>

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
                                <li><a href="/student/chooseCourse"><h4><i class="md md-layers"></i>&nbsp;讨论课</h4></a>
                                </li>
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


            <div class="col-lg-6 col-md-6 col-xs-12">
                <div class="panel panel-default ">
                    <div class="panel-heading"><h3 class="panel-title">${team.klass.klassSerial}—${team.teamSerial}</h3></div>
                    <div class="panel-body">

                        <table class="table">

                            <tbody id="memberList">
                            <tr>
                                <td><p>组长</p></td>
                                <td><p>${team.leader.account}</p></td>
                                <td><p class="pull-right">${team.leader.studentName}</p></td>
                                <td></td>

                            </tr>
                            <#list team.member as member>
                            <tr id="member${member.id}">
                                <td><p>组员</p></td>
                                <td><p>${member.account}</p></td>
                                <td><p class="pull-right">${member.studentName}</p></td>
                                <td>
                                    <button class="md-trigger btn btn-primary waves-effect waves-light pull-right"
                                            onclick="deleteMember(${member.id},'${member.account}','${member.studentName}')">
                                        删除
                                    </button>
                                </td>

                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>

                </div> <!-- panel-body -->
            </div> <!-- panel -->


            <div class="col-lg-6 col-md-6 col-xs-12">


                <div class="panel panel-default ">
                    <div class="panel-body">
                        <div class="row">
                            <label>搜索学生</label>
                            <div>

                                <select id="searchStudent" class="select2 form-control" data-placeholder="选择学生">
                                    <#list studentList as list>
                                        <option id="notTeamStudent${list.id}" value={"id":${list.id},"account":"${list.account}","name":"${list.studentName}"}>
                                            ${list.account}
                                            ${list.studentName}
                                        </option>
                                    </#list>
                                </select>

                            </div>
                        </div>
                        <br/>
                        <div class="row">

                            <button class="btn btn-purple waves-effect waves-light" onclick="dissolveTeam()">解散小组</button>
                            <button class="btn btn-success waves-effect waves-light pull-right" onclick="addMember()">添加
                            </button>

                        </div>


                    </div>

                </div>
            </div>


        </div> <!-- End row -->

    </div>

</div>
<!-- END wrapper -->

<!-- jQuery  -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script>
    var resizefunc = [];
</script>

<!-- Main  -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/plugins/select2/dist/js/select2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/plugins/jquery-multi-select/jquery.multi-select.js"></script>
<script type="text/javascript" src="/plugins/jquery-multi-select/jquery.quicksearch.js"></script>


<script>
    jQuery(document).ready(function () {
        // Select2
        jQuery(".select2").select2({
            width: '100%'
        });
    });
</script>


<script>
    function deleteMember(memberId, account, name) {
        name = name.toString();
        var msg = confirm("是否删除该同学?");
        if (msg == true) {
            $.ajax({
                url: "/student/course/team/myteam/" + memberId,
                method: "delete",
                data: {
                    "teamId": ${team.id}
                },
                success: function () {
                    alert("删除成功");
                    var tmp = "member" + memberId;
                    var tmp2="notTeamStudent"+memberId;
                    document.getElementById(tmp).innerHTML="";
                    var str="<option id=\"notTeamStudent";
                    str+=memberId;
                    str+="\" value={\"id\":";
                    str+=memberId;
                    str+=",\"account\":\"";
                    str+=account;
                    str+="\",\"name\":\"";
                    str+=name;
                    str+="\"}>";
                    str+=account;
                    str+=" ";
                    str+=name;
                    str+="</option>";
                    document.getElementById("searchStudent").innerHTML += str;

                }
            })

        }
    }
</script>
<script>
    function addMember() {
        var student = document.getElementById("searchStudent").value;
        var studentJson=JSON.parse(student);

        var studentId=studentJson.id;
        var studentAccount=studentJson.account;
        var studentName=studentJson.name;

        $.ajax({
            url: "/student/course/team/myteam/"+studentId,
            method: "put",
            data: {
                "teamId":${team.id}
            },
            success: function () {
                alert("添加成功");
                var tmp2="notTeamStudent"+studentId;
                var obj=document.getElementById(tmp2);
                document.getElementById("searchStudent").removeChild(obj);


                var tmp="";
                tmp+="<tr id=\"member";
                tmp+=studentId;
                tmp+="\"> <td><p>组员</p></td><td><p>";
                tmp+=studentAccount;
                tmp+="</p></td><td><p class=\"pull-right\">";
                tmp+=studentName;
                tmp+="</p></td><td> <button class=\"md-trigger btn btn-primary waves-effect waves-light pull-right\" onclick=\"deleteMember(";
                tmp+=studentId;
                tmp+=",'";
                tmp+=studentAccount;
                tmp+="','";
                tmp+=studentName;
                tmp+="')\">删除</button></td></tr>";
                document.getElementById("memberList").innerHTML+=tmp;


            },
            error:function () {

            }
        });

    }
    </script>
<script>

    function dissolveTeam() {
        var msg=confirm("是否解散小组?");
        if(msg==true)
        {
            $.ajax({
                url:"/student/course/team/myteam/disband" ,
                method:"post",
                data:{
                    "teamId":${team.id}
                },
                success:function () {
                    alert("解散成功!");
                    window.location.href="/student/course/team?courseId=" + ${team.courseId} +"&klassId="+ ${team.klassId};
                },
                error:function () {
                    alert("解散失败!");
                }
            });
        }

    }


</script>

</body>
</html>