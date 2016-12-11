<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int length;

    ArrayList<String[]> examlist = new ArrayList<>();
    examlist = (ArrayList<String[]>) session.getAttribute("examlist");
    length = examlist.size();
    String [] class_id = new String[length];
    String [] name = new String[length];
    String [] date= new String[length];
    for (int i=0; i<examlist.size(); i++){

        class_id[i] = examlist.get(i)[0];
        name[i] = examlist.get(i)[1];
        if (!examlist.get(i)[2].equals("-2")){
        date[i] = "考试已完成";}
        else date[i] = examlist.get(i)[3];
    }
    session.setAttribute("username",session.getAttribute("username"));
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <head>
        <title>考试列表</title>

        <link rel="stylesheet" type="text/css" href="dist/css/bootstrap.css"/>
        <script type="text/javascript" src="Exam/js/examlist.js"></script>
        <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>
//            $(function(){
//                var xhttp = new XMLHttpRequest();
//                xhttp.onreadystatechange = function() {
//                    if (this.readyState == 4 && this.status == 200) {
//                        get_XML(this);
//                    }
//                };
//                xhttp.open("GET", "exam.xml?t="+Math.random(), true);
//                xhttp.send();
//            });
//            function get_XML(xml) {
//                var content = '';
//                var xmldoc = xml.responseXML;
//                var exam = xmldoc.getElementsByTagName("exam");
//                for (var i=0; i<exam.length; i++){
//                   content = content+ '<tr>'+
//                            '<td class="active">'+'<a href="paper.jsp">'+exam[i].getElementsByTagName("name")[0].childNodes[0].nodeValue+'</a>'+'</td>'+
//                            '<td class="active">'+exam[i].getElementsByTagName("teacher")[0].childNodes[0].nodeValue+'</td>'+
//                            '<td class="active">'+exam[i].getElementsByTagName("time")[0].childNodes[0].nodeValue+'</td>'+
//                            '<td class="active">'+exam[i].getElementsByTagName("course")[0].childNodes[0].nodeValue+'</td>'+
//                           '</tr>';
//                }
//                document.getElementById("t_body").innerHTML = content;
//            }
        </script>
        <script>
            $(function () {
                var id = [];
                var name = [];
                var date = [];
                var content = '';
                <%
                    for (int j=0; j<length; j++){ %>
                        id[<%=j%>] = '<%=class_id[j]%>';
                        name[<%=j%>] = '<%=name[j]%>';
                        date[<%=j%>] = '<%=date[j]%>';
                <%}%>
                for (var i=0; i<id.length; i++){
                    if (date[i]=="考试已完成"){
                        content = content+ '<tr>'+
                            '<td class="active">'+id[i]+'</td>'+
                            '<td class="active">'+name[i]+'</td>'+
                            '<td class="active">'+date[i]+'</td>'+
                            '<td class="active">'+date[i]+'</td>'+
                            '</tr>';
                    }
                    else
                   content = content+ '<tr>'+
                            '<td class="active">'+'<a href="/paper?t=<%=Math.random()%>&name='+id[i]+'">'+id[i]+'</a>'+'</td>'+
                            '<td class="active">'+name[i]+'</td>'+
                            '<td class="active">'+date[i]+'</td>'+
                            '<td class="active">'+date[i]+'</td>'+
                           '</tr>';

                }
                document.getElementById("t_body").innerHTML = content;
            })
        </script>
    </head>
<body>
<div class="container">
    <div class="page-header"><h1>考试列表</h1></div>
    <table class="table table-bordered table-hover">
        <thead class="active">
            <th class="bg-success">考试编号</th>
            <th class="bg-success">考试名称</th>
            <th class="bg-danger">考试时间</th>
            <th class="bg-danger">具体时间</th>
        </thead>
        <tbody id="t_body">
        <!-- On cells (`td` or `th`) -->
        </tbody>
    </table>
    <div class="progress">

        <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 100%">
            <span class="sr-only">100% Complete (warning)</span>
        </div>

    </div>

</div>
</body>
</html>
