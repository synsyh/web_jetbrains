<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <link rel="stylesheet" type="text/css" href="dist/css/bootstrap.css"/>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <meta charset="UTF-8">
    <div class="page-header">
        <h1>请认真完成试卷</h1>
    </div>
</head>
<body  oncontextmenu=self.event.returnValue=false onselectstart="return false">
<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">考试剩余时间</h3>
        </div>
        <div class="panel-body" id="timer">
        </div>
    </div>
    <form name="form" action="/stu_sys" method="post" onsubmit="return check()">
        <div id="select"></div>
        <div id="fill"></div>
        <div id="judge"></div>
        <div id="descrip"></div>
        <button type="submit" class="btn-primary submit_button">提交</button>
    </form>

</div>
</body>

<script LANGUAGE="JavaScript">
    <!--
    var maxtime = <%=session.getAttribute("time")%>;
    function CountDown() {
        if (maxtime >= 0) {
            minutes = Math.floor(maxtime / 60);
            seconds = Math.floor(maxtime % 60);
            msg = "距离结束还有" + minutes + "分" + seconds + "秒";
            document.all["timer"].innerHTML = msg;
            if (maxtime == 5 * 60) alert('注意，还有5分钟!');
            --maxtime;
        }
        else {
            clearInterval(timer);
            alert("时间到，考试结束!");
            $("form input").removeAttr("required");
            document.form.submit();
            check();
        }
    }
    $(function () {
        timer = setInterval("CountDown()", 1000);
    })
    //-->
</script>
<script>
    var store=[];
    $(function () {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                get_XML(this);
            }
        };
        xhttp.open("GET", "<%=session.getAttribute("link")%>?t=" + Math.random(), true);
        xhttp.send();
    });
    function get_XML(xml) {
        var content = '';
        var fill = '';
        var descrip = '';
        var judge = '';
        var xmldoc = xml.responseXML;
        var question = xmldoc.getElementsByTagName("question");
        var num = 0;
        for (var i = 0; i < question.length; i++) {
            if (question[i].getAttribute("type") == "select") {
                var name_id =question[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                store.push(name_id);
                num++;
                content +=
                        '<div class="panel panel-primary">' +
                        '<div class="panel panel-heading">' +
                        '<div class="panel-title">' +
                        question[i].getElementsByTagName("content")[0].childNodes[0].nodeValue +
                        '</div>' +
                        '</div>' +
                        '<div class="panel-body">' +
                        '<div class="radio">' +
                        '<label>' +
                        '<input  type="radio" required="required" value="a" name='+name_id+'>' +
                        question[i].getElementsByTagName("option")[0].childNodes[0].nodeValue +
                        '</label>' +
                        '</div>' +
                        '<div class="radio">' +
                        '<label>' +
                        '<input type="radio" value="b" name='+name_id+'>' +
                        question[i].getElementsByTagName("option")[1].childNodes[0].nodeValue +
                        '</label>' +
                        '</div>' +
                        '<div class="radio">' +
                        '<label>' +
                        '<input type="radio" value="c" name='+name_id+'>' +
                        question[i].getElementsByTagName("option")[2].childNodes[0].nodeValue +
                        '</label>' +
                        '</div>' +
                        '<div class="radio">' +
                        '<label>' +
                        '<input type="radio" value="d" name='+name_id+'>' +
                        question[i].getElementsByTagName("option")[3].childNodes[0].nodeValue +
                        '</label>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                document.getElementById("select").innerHTML = content;
            }
            if (question[i].getAttribute("type") == "fill") {
              num++;
                var name_id =question[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                store.push(name_id);
                var num_t = question[i].childElementCount;
                var first = question[i].firstElementChild;
                var next = first.nextElementSibling;
                fill = fill +'<div class="panel panel-warning">'+
                '<div class="panel-heading tab-pane">'+"填空题"+
                        '</div>'+'<div class="panel-body">';

                for (var j = 0; j <num_t-1; j++) {
                    if (next.nodeName!="blank"){
                    fill+=
                            '<div class="input-group">'+
                            '<span class="input-group-addon">'+
                            next.childNodes[0].nodeValue+
                            '</span>';}
                    if (next.nodeName=="blank")
                        fill+='<input type="text" required="required" class="form-control" name='+name_id+'>';
                    if (next.nodeName!="blank")
                        fill+='</div>';
                    if (j!=num_t-2)
                        next =next.nextElementSibling;
                }
                fill = fill+'</div>'+'</div>';
                document.getElementById("fill").innerHTML=fill;
            }
            if (question[i].getAttribute("type") == "descrip") {
              num++;
                var name_id =question[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                store.push(name_id);
                descrip +=
                        '<div class="panel panel-success">' +
                        '<div class="panel-heading">' +
                        "简答题" +
                        '</div>' +
                        '<div class="panel-body">' +
                        '<div class="text-left">' +
                        question[i].getElementsByTagName("content")[0].childNodes[0].nodeValue +
                        '</div>' +
                        '<textarea class="form-control" rows="3" id="1" name='+name_id+'>'+'</textarea>' +
                        '</div>' +
                        '</div>';
                document.getElementById("descrip").innerHTML = descrip;
            }
            if (question[i].getAttribute("type") == "judge") {
                var name_id =question[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                store.push(name_id);
              num++;
                judge +=
                        '<div class="panel panel-danger">' +
                        '<div class="panel-heading">' +
                        "判断题" +
                        '</div>' +
                        '<div class="panel-body">' +
                        '<div class="text-left">' +
                        question[i].getElementsByTagName("content")[0].childNodes[0].nodeValue+
                        '</div>'+
                        '<div class="radio">' +
                        '<label>' +
                        '<input type="radio" value="Y" name=' + name_id + '>' +
                        "正确"+
                        '</label>' +
                        '</div>' +
                        '<div class="radio">' +
                        '<label>' +
                        '<input type="radio" value="N" name=' + name_id + '>' +
                        "错误"+
                        '</label>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                document.getElementById("judge").innerHTML = judge;
            }
        }

    }
</script>
<script language="JavaScript">
//    $(document).ready(function(){
//        document.getElementsByName(store[1]);
//        $("button").click(function(){
//            document.getElementsByName(store[1]).innerHTML= $("input[name='7']:checked").val();
//        });
//    });
    function check() {
            alert("考试完成");
            return true;
    }
</script>

</html>
