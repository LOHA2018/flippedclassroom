<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">

    <link rel="shortcut icon" href="/img/favicon_1.ico">

    <title>讨论课</title>

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
                        <button class="button-menu-mobile">
                            <div class="glyphicon glyphicon-menu-left"></div>
                        </button>

                    </div>
                    <div class="pull-left">
                        <div class="button-menu-mobile">
                        ${klass.course.courseName}-讨论课
                        </div>
                    </div>
                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img
                                    src="/img/avatar-1.jpg" alt="user-img" class="img-circle">
                            </a>
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



    <div class="content">
        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default ">
                        <div class="panel-heading"><h3 class="panel-title">讨论课</h3></div>
                        <div class="panel-body">

                            <table class="table">

                                <tbody>


                                <tr>
                                    <td><p>小组</p></td>
                                    <td><p>展示</p></td>
                                    <td><p>提问</p></td>
                                    <td><p>报告</p></td>
                                    <td><p>修改</p></td>
                                </tr>


                                <#list scoreList as teamScore>
                                <tr>
                                    <td><p>
                                        ${teamScore.team.klass.klassSerial}—${teamScore.team.teamSerial}
                                    </p></td>

                                    <td id="teamPre${teamScore.team.id}"
                                        name="preScore">
                                        ${teamScore.presentationScore!""}
                                    </td>

                                    <td id="teamQue${teamScore.team.id}"
                                        name="queScore">
                                        ${teamScore.questionScore!""}
                                    </td>
                                    <td id="teamReport${teamScore.team.id}"
                                        name="reportScore">
                                        ${teamScore.reportScore!""}
                                    </td>
                                    <#--<td id="teamTotal${teamScore.team.id}">-->
                                        <#--<p>-->
                                            <#--${teamScore.totalScore}-->
                                        <#--</p>-->
                                    <#--</td>-->
                                    <td id="teamButton${teamScore.team.id}">
                                        <span class="glyphicon glyphicon-pencil"
                                              aria-hidden="true"
                                              onclick="modifyAllScore(${teamScore.team.id})">
                                        </span>
                                    </td>
                                </tr>
                                <br/>
                                </#list>


                                </tbody>

                            </table>
                        </div>


                    </div> <!-- panel-body -->
                </div> <!-- panel -->


            </div>

        </div>
    </div>

</div>
<!-- END wrapper -->

<!-- jQuery  -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script>
    function modifyAllScore(teamId) {

        document.getElementById("teamButton" + teamId).innerHTML =
                "<span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"" +
                " onclick=\"submitTeamScore(" +teamId +
                ")\"></span>";

        var preScore = document.getElementById("teamPre" + teamId).innerText;
        var queScore = document.getElementById("teamQue" + teamId).innerText;
        var reportScore = document.getElementById("teamReport" + teamId).innerText;
        document.getElementById("teamPre" + teamId).innerHTML =
                "<input type='text' value='" + preScore + "'>";


        document.getElementById("teamQue" + teamId).innerHTML =
                "<input type='text' value='" + queScore + "'>";

        document.getElementById("teamReport" + teamId).innerHTML =
                "<input type='text' value='" + reportScore + "'>";

        // document.getElementById("teamTotal" + teamId).innerText = "";


    }

    function submitTeamScore(teamId) {

        var preScore = document.getElementById("teamPre" + teamId).value;
        var queScore = document.getElementById("teamQue" + teamId).value;
        var reportScore = document.getElementById("teamReport" + teamId).value;

        $.ajax({
            url: "teacher/course/seminar/score",
            method: "post",
            data: {
                "klassSeminarId":${scoreList[0].klassSeminarId},
                "teamId":teamId,
                "preScore": preScore,
                "queScore": queScore,
                "reportScore": reportScore
            },
            success: function (totalScore) {
                alert("修改成功!");

                document.getElementById("teamButton" + teamId).innerHTML =
                        "<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"" +
                        " onclick=\"submitTeamScore("+
                        teamId+
                        ")\"></span>";

                document.getElementById("teamPre" + teamId).innerText = preScore;
                document.getElementById("teamQue" + teamId).innerText = queScore;
                document.getElementById("teamReport" + teamId).innerText = reportScore;
                // document.getElementById("teamTotal" + teamId).innerText = totalScore;

            }

        });
    }


</script>
</body>
</html>