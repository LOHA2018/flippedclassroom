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
                            讨论课
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
            <div class="row">
                <div class="col-md-6 col-sm-12">
                    <div class="panel panel-default panel-fill">
                        <div class="panel-body">
                            <div>
                                <label>${course.courseName}
                                </label>
                            </div>

                            <p>当前小组已有<label id="curQuestionNum">0</label>位同学提问</p>

                        </div> <!-- panel-body -->
                    </div> <!-- panel -->
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 col-sm-12">
                    <div class="tabs-vertical-env">
                        <ul class="nav tabs-vertical">


                            <#list enrollList as enroll>
                                <li class="">
                                    <#if enroll.id??>
                                        <a id="list${enroll.teamOrder}" href="#presentation${enroll.teamOrder}"
                                           data-toggle="tab" aria-expanded="false">
                                            <label id="preTeamFont${enroll.teamOrder}"> 第${enroll.teamOrder}
                                                组:${enroll.team.klass.klassSerial}-${enroll.team.teamSerial}
                                            </label>
                                        </a>
                                    <#else >
                                        <a id="list${enroll.teamOrder}" href="#presentation${enroll.teamOrder}"
                                           data-toggle="tab" aria-expanded="false">
                                            <label>第${enroll.teamOrder}组:-</label>
                                        </a>
                                    </#if>
                                </li>
                            </#list>
                            <!--注：认为进入讨论课时进入时、表左侧为展示列表，右侧为展示分数、展示分数输入框、提交展示分数按钮-->
                            <!--下组展示按钮、抽取提问按钮，同时，认为当前组及历史组可点击、将来组不可点击-->

                        </ul>

                        <div class="tab-content">


                            <!--注:此界面含展示列表、展示分数输入框、提交展示分数按钮、下组展示按钮、-->
                            <!--抽取展示提问，此为最初始的界面-->
                                <#list enrollList as enroll>
                                    <#if enroll.id??>
                            <div class="tab-pane" id="presentation${enroll.teamOrder}">
                                <label>展示分数</label>
                                <input id="presentationScore${enroll.teamOrder}"
                                       style="width:100%; height: 80px;font-size: 28px" type="text" class="form-control"
                                       placeholder="" value="">
                                <br/>
                                <button class=" btn  btn-default btn-block  waves-effect waves-light "
                                        onclick="submitPresentationScore(${enroll.teamOrder},${enroll.team.id})">
                                    提交展示分数
                                </button>
                            <#--<button class=" btn  btn-default btn-block  waves-effect waves-light "-->
                            <#--onclick="nextTeamPresentation(${enroll.teamOrder})">-->
                            <#--下组展示-->
                            <#--</button>-->
                                <button class=" btn  btn-default btn-block  waves-effect waves-light "
                                        onclick="beginQuestion(${enroll.teamOrder})">
                                    开始提问环节
                                </button>
                            </div>
                                    <#else >
                                <div class="tab-pane" id="presentation${enroll.teamOrder}">
                                    <label>没有小组报名该讨论课展示次序</label>
                                    <br/>
                                </div>
                                    </#if>
                                </#list>

                            <!--注：此则老师点击抽取提问或者点击下组展示的界面，该界面有-->
                            <!--提交提问分数按钮、抽取下个提问按钮、下组展示按钮-->
                                    <#list enrollList as enroll>
                                        <#if enroll.id??>
                        <div class="tab-pane" id="question${enroll.teamOrder}">
                            <label>提问分数</label>
                            <input id="curQuestionScore" style="width:100%; height: 80px;font-size: 28px" type="text"
                                   class="form-control"
                                   placeholder="" value="">
                            <br/>
                            <button class=" btn  btn-default btn-block  waves-effect waves-light "
                                    onclick="submitQuestionScore(${enroll.teamOrder})">
                                提交提问分数
                            </button>
                            <button class=" btn  btn-default btn-block  waves-effect waves-light "
                                    onclick="extractQuestion1(${enroll.teamOrder},${enroll.id})">
                                抽取提问
                            </button>
                        <#--<button class="btn  btn-default btn-block  waves-effect waves-light"-->
                        <#--onclick="nextTeamPresentation(${enroll.teamOrder})">-->
                        <#--下组展示-->
                        <#--</button>-->
                        </div>
                                        </#if>
                                    </#list>

                            <!--注:此则教师点击历史的某组展示，可以修改历史展示小组的展示分数-->
                                        <#list enrollList as enroll>
                                            <#if enroll.id??>
                    <div class="tab-pane" id="modify${enroll.teamOrder}">
                        <label>修改展示分数</label>
                        <input id="historyPresentationScore${enroll.teamOrder}"
                               style="width:100%; height: 80px;font-size: 28px" type="text" class="form-control"
                               placeholder="" value="">
                        <br/>
                        <button class=" btn  btn-default btn-block  waves-effect waves-light "
                                onclick="modifyPresentation${enroll.teamOrder}">
                            修改展示分数
                        </button>
                        <br/>
                        <hr/>
                        <p>
                            该小组有<label
                                id="historyQuestionNum${enroll.teamOrder}">0</label>位同学提问成功
                        </p>
                        <select id="historyQuestionList${enroll.teamOrder}" class="form-control">

                        </select>
                        <br/>
                        <label>
                            提问分数
                        </label>

                        <input id="questionScore${enroll.teamOrder}"
                               style="width:100%; height: 80px;font-size: 28px"
                               type="text" class="form-control"
                               value="">
                        <br/>

                        <button class=" btn  btn-default btn-block  waves-effect waves-light "
                                onclick="modifyQuestionScore(${enroll.klassSeminarId})"
                                value="${enroll.teamOrder}">修改
                        </button>

                    </div>

                                            </#if>
                                        </#list>


                        </div>
                    </div>
                    <button class=" btn  btn-default btn-block  waves-effect waves-light "
                            onclick="nextTeamPresentation()">
                        下组展示
                    </button>

                </div>

            </div>

        </div>
    </div>
    <!-- jQuery  -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.min.js"></script>
    <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        var curQuestionStudentName;
        var curQuestionTeamSerial;
        var curQuestionSum=0;
        var curPresentationTeamOrder = -1;
        var nextPresentationTeamOrder = -1;
        var sock = new SockJS("/endpointSeminar");
        var stomp = Stomp.over(sock); //初始化
        stomp.connect(
                {},
                function connectCallback(frame) {
                    // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
                    alert("连接成功!");
                    stomp.subscribe('/topic/getResponse' + ${klassSeminarId}, function (response) {//订阅某信息
                        var obj = JSON.parse(response.body);
                        if (obj.status == 3)   //如果是某同学提问状态
                        {
                            alert("请" + obj.teamSerial +"小组" +obj.name+"同学提问");
                            var curQuestionId = obj.questionId
                            curQuestionStudentName = obj.name;
                            curQuestionTeamSerial = obj.teamSerial;
                            var tmp = "";
                            tmp += "<option id=\"question";
                            tmp += curQuestionId;
                            tmp += "\"name=\"questionScore\"value=>";
                            tmp += curQuestionTeamSerial;
                            tmp +="  ";
                            tmp +=curQuestionStudentName;
                            tmp+="</option>";

                            document.getElementById("historyQuestionList"+curPresentationTeamOrder).innerHTML += tmp;


                        } else if (obj.status == 1) {  //有人举手
                            curQuestionSum++;
                            document.getElementById("curQuestionNum").innerText = curQuestionSum;
                        }
                    });
                },
                function errorCallBack(error) {
                    // 连接失败时（服务器响应 ERROR 帧）的回调方法
                    alert("连接失败");
                }
        );


        var attendanceList = new Array(${enrollList?size}+1);   //初始化参加列表
        var attendanceSize =${enrollList?size};   //得到参与讨论课的列表的长度

        var x = 1;
        <#list enrollList as attendance>
            <#if attendance.id??>
            attendanceList[x] =${attendance.id};
            x++;
            <#else >
            attendanceList[x] = -1;
            x++;
            </#if>
        </#list>

        <#list enrollList as list>
            <#if list.id??>
                <#if list.isPresent==1>
            curPresentationTeamOrder =${list.teamOrder}
                    <#break>
                <#else >
                document.getElementById("list${list.teamOrder}").href = "#modify${list.teamOrder}";
                </#if>
            </#if>
        </#list>

        document.getElementById("preTeamFont" + curPresentationTeamOrder).style = "color:red";

        document.getElementById("list" + curPresentationTeamOrder).setAttribute('class', 'active');
        document.getElementById("presentation" + curPresentationTeamOrder).setAttribute('class', 'tab-pane active');
        for (var i = curPresentationTeamOrder + 1; i <= attendanceSize; i++) {
            if (attendanceList[i] >= 0) {
                nextPresentationTeamOrder = i;
                break;
            }
        }
        if (nextPresentationTeamOrder == -1) {
            nextPresentationTeamOrder = curPresentationTeamOrder;
        }


    </script>


    <script>

        function submitPresentationScore(teamOrder) {

            if (attendanceId != attendanceList[curPresentationTeamOrder]) {
                alert("提交的成绩不为当前组!");
                return false;
            }


            var presentationScore = document.getElementById("presentationScore" + attendanceId).value;
            if (presentationScore == "") {
                alert("成绩不能为空!");
                return false;
            } else {
                $.ajax({
                    url: "teacher/presentation/grade/submit",
                    method: "post",
                    data: {
                        "attendanceId": attendanceId,
                        "presentationScore": presentationScore
                    },
                    success: function () {
                        alert("提交成功!");
                    },
                    error: function () {
                        alert("提交失败!");
                    }

                });
            }


        }


    </script>
    <script>

        function nextTeamPresentation(teamOrder) {
            //下一组展示
            // 到下一组展示

            var msg = confirm("确认结束当前小组展示？");
            if (msg == false) {
                return;
            }

            if (nextPresentationTeamOrder == curPresentationTeamOrder) {
                alert("该讨论课已结束");
                stomp.disconnect();
                return;
            }

            var obj = {
                "klassSeminarId":${klassSeminarId},
                "curAttendanceId": attendanceList[curPresentationTeamOrder],
                "nextAttendanceId": attendanceList[nextPresentationTeamOrder],
                "nextTeamOrder": nextPresentationTeamOrder
            };

            stomp.send("/app/nextPre", {},
                    JSON.stringify(obj));
            document.getElementById("preTeamFont" + curPresentationTeamOrder).style = "color:black";
            document.getElementById("preTeamFont" + nextPresentationTeamOrder).style = "color:red";

            document.getElementById("list" + curPresentationTeamOrder).setAttribute('class', '');
            document.getElementById("presentation" + curPresentationTeamOrder).setAttribute('class', 'tab-pane');
            document.getElementById("list" + nextPresentationTeamOrder).setAttribute('class', 'active');
            document.getElementById("presentation" + nextPresentationTeamOrder).setAttribute('class', 'tab-pane active');
            //再使本组的列表链接改变
            document.getElementById("list" + curPresentationTeamOrder).href = "#modify" + curPresentationTeamOrder;
            //设为初值
            document.getElementById("curQuestionNum").innerText = 0;

            curPresentationTeamOrder = nextPresentationTeamOrder;
            for (var i = curPresentationTeamOrder + 1; i <= attendanceSize; i++) {
                if (attendanceList[i] >= 0) {
                    nextPresentationTeamOrder = i;
                    break;
                }
            }


        }


    </script>

    <script>
        function beginQuestion(teamOrder) {
            document.getElementById("list"+teamOrder).href="#question"+teamOrder;
            document.getElementById("presentation"+teamOrder).innerHTML=document.getElementById("question"+teamOrder).innerHTML;
        }

        function extractQuestion1(teamOrder,attendanceId) {
            console.log("当前提问数0:"+curQuestionSum);
            if (curQuestionSum==0) {
                alert("当前没有同学提问");
                return;
            }
            var obj={
                "klassSeminarId":${klassSeminarId},
                "attendanceId":attendanceId
            };

            stomp.send("/app/teacher/question", {}, JSON.stringify(obj));
        }

    </script>

    <script>
        function submitQuestionScore(attendanceId) {
            if (attendanceId != attendanceList[curPresentationTeamOrder]) {
                alert("提交的成绩不为当前组!");
            }
            var questionScore = document.getElementById("curQuestionScore").value;
            $.ajax({
                url: "teacher/seminar/question/submit",
                method: "post",
                data: {
                    "questionScore": questionScore,
                    "attendanceId": attendanceId,
                    "studentId": curQuestionStudentId,
                    "teamId": curQuestionTeamId
                },
                success: function () {
                    alert("提交成功!");
                },
                error: function () {
                    alert("提交失败!");
                }
            });
        }
    </script>
    <script>
    </script>

    <script>
        function modifyPresentationScore(attendanceId) {

            var historyPresentationScore = document.getElementById("historyPresentationScore" + attendanceId).value;
            if (presentationScore == "") {
                alert("成绩不能为空!");
                return false;
            } else {
                $.ajax({
                    url: "teacher/presentation/grade/modify",
                    method: "post",
                    data: {
                        "attendanceId": attendanceId,
                        "historyPresentationScore": historyPresentationScore
                    },
                    success: function () {
                        alert("提交成功!");
                    },
                    error: function () {
                        alert("提交失败!");
                    }

                });
            }
        }
    </script>


    <script>
        function modifyQuestionScore() {
            var historyQuestionScore = document.getElementById("historyQuestionScore" + attendanceId).value;
            if (presentationScore == "") {
                alert("成绩不能为空!");
                return false;
            } else {
                $.ajax({
                    url: "teacher/presentation/grade/modify",
                    method: "post",
                    data: {
                        "attendanceId": attendanceId,
                        "historyPresentationScore": historyPresentationScore
                    },
                    success: function () {
                        alert("提交成功!");
                    },
                    error: function () {
                        alert("提交失败!");
                    }

                });
            }
        }
    </script>


</body>
</html>