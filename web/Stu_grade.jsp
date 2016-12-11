<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int length;
    ArrayList<String[]> examlist = new ArrayList<>();
    examlist = (ArrayList<String[]>) session.getAttribute("examlist");
    length = examlist.size();
    String[] class_id = new String[length];
    String[] name = new String[length];
    String[] date = new String[length];
    String[] score = new String[length];
    for (int i = 0; i < examlist.size(); i++) {
        if (examlist.get(i)[2] .equals("-1")) {
            class_id[i] = examlist.get(i)[0];
            name[i] = examlist.get(i)[1];
            score[i] = "待任课教师批卷";
            date[i] = examlist.get(i)[3];
        }
        else if (examlist.get(i)[2] .equals("-2")) {
            class_id[i] = examlist.get(i)[0];
            name[i] = examlist.get(i)[1];
            score[i] = "待进行考试";
            date[i] = examlist.get(i)[3];
        }
        else {
            class_id[i] = examlist.get(i)[0];
            name[i] = examlist.get(i)[1];
            score[i] = examlist.get(i)[2];
            date[i] = examlist.get(i)[3];
        }
    }
    session.setAttribute("username", session.getAttribute("username"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="dist/css/bootstrap.css"/>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <meta charset="UTF-8">
    <div class="page-header">
        <h1>考试成绩</h1>
        <h2 id="stu_name"></h2>
    </div>
</head>
<body>
<div class="container">
    <table class="table table-bordered table-hover">
        <thead class="active">
        <th class="bg-success">考试科目</th>
        <th class="bg-success">考试时间</th>
        <th class="bg-danger">考试代码</th>
        <th class="bg-primary">考试成绩</th>
        </thead>
        <tbody id="grade">
        <!-- On cells (`td` or `th`) -->
        </tbody>
    </table>
</div>
</body>
<script>
    //    $(function () {
    //        var xhttp = new XMLHttpRequest();
    //        xhttp.onreadystatechange = function () {
    //            if (this.readyState == 4 && this.status == 200) {
    //                get_XML(this);
    //            }
    //        };
    //        xhttp.open("GET", "stu_grade.xml?t=" + Math.random(), true);
    //        xhttp.send();
    //    });
    //    function get_XML(xml) {
    //        var content = '';
    //        var xmldoc = xml.responseXML;
    //        var student = xmldoc.getElementsByTagName("stu");
    //        for (var i = 0; i < student.length; i++) {
    //
    //            if (student[i].getElementsByTagName("id")[0].childNodes[0].nodeValue == "20141120165") //此处待替换为具体判断
    //            {
    //                document.getElementById("stu_name").innerHTML = student[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
    //                var exam = student[i].getElementsByTagName("exam");
    //                document.getElementById("grade").innerHTML = '<p>测试</p>';
    //                for (var j = 0; j < exam.length; j++)
    //                    content = content + '<tr>' +
    //                        '<td class="active">' + exam[j].getElementsByTagName("name")[0].childNodes[0].nodeValue + '</td>' +
    //                        '<td class="active">' + exam[j].getElementsByTagName("teacher")[0].childNodes[0].nodeValue + '</td>' +
    //                        '<td class="active">' + exam[j].getElementsByTagName("descrip")[0].childNodes[0].nodeValue + '</td>' +
    //                        '<td class="active">' + exam[j].getElementsByTagName("select")[0].childNodes[0].nodeValue + '</td>' +
    //                        '<td class="active">' + exam[j].getElementsByTagName("fill")[0].childNodes[0].nodeValue + '</td>' +
    //                        '<td class="active">' + exam[j].getElementsByTagName("judge")[0].childNodes[0].nodeValue + '</td>' +
    //                        '<td class="active">' + exam[j].getElementsByTagName("grade")[0].childNodes[0].nodeValue + '</td>';
    //            }
    //  content = content+ '<tr>'+
    //           '<td class="active">'+exam[i].getElementsByTagName("name")[0].childNodes[0].nodeValue+'</td>'+
    //           '<td class="active">'+exam[i].getElementsByTagName("teacher")[0].childNodes[0].nodeValue+'</td>'+
    //           '<td class="active">'+exam[i].getElementsByTagName("time")[0].childNodes[0].nodeValue+'</td>'+
    //           '<td class="active">'+exam[i].getElementsByTagName("course")[0].childNodes[0].nodeValue+'</td>'+
    //          '</tr>';
    //        }
    //        document.getElementById("grade").innerHTML = content;
    //    }
</script>
<script>
    $(function () {
        var id = [];
        var name = [];
        var date = [];
        var score = [];
        var content = '';
        <%
            for (int j=0; j<length; j++){ %>
        id[<%=j%>] = '<%=class_id[j]%>';
        name[<%=j%>] = '<%=name[j]%>';
        score[<%=j%>] = '<%=score[j]%>';
        date[<%=j%>] = '<%=date[j]%>';
        <%}%>
        for (var i = 0; i < id.length; i++) {

            content = content + '<tr>' +
                '<td class="active">' + name[i] + '</td>' +
                '<td class="active">' + date[i] + '</td>' +
                '<td class="active">' + id[i] + '</td>' +
                '<td class="active">' + score[i] + '</td>' +
                '</tr>';

        }
        document.getElementById("grade").innerHTML = content;
    })
</script>
</html>
