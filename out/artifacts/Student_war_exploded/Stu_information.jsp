<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int length;
    String [] stuinfo;
    stuinfo = (String[]) session.getAttribute("stu_info");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" type="text/css" href="dist/css/bootstrap.css"/>
  <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <meta charset="UTF-8">
  <div class="page-header">
      <h1>个人信息</h1>
  </div>
</head>
<body>
  <div class="panel panel-success">
      <div class="panel-heading">
          <h3 class="panel-title"></h3>
      </div>
      <div class="panel-body" id="stu_information">
        <div class="page-header">
            <h3>学生姓名:  <%=stuinfo[1]%></h3>
        </div>
        <div class="page-header">
            <h3>学生年龄:  <%=stuinfo[2]%></h3>
        </div>
        <div class="page-header">
            <h3>家庭住址:   <%=stuinfo[3]%></h3>
        </div>
        <div class="page-header">
            <h3>准考证号:  <%=stuinfo[0]%></h3>
        </div>
      </div>
  </div>
</body>
</html>
