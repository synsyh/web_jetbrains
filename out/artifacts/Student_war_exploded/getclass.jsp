<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Untitled Document</title>
    <!-- Bootstrap -->
    <link href="dist/css/bootstrap.css" rel="stylesheet">
    <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li><a href="getclass.jsp">开课</a></li>
            <li><a href="setexam.jsp">出题</a></li>
            <li><a href="exammaintain.html">题库维护</a></li>
            <li>
                <a href="setpaper.jsp">生成试卷</a>
            </li>
            <li><a href="#">批卷</a></li>
            <li><a href="#">设置</a></li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1>开课</h1>
                    <br>
                    <form action="/getclass" role="form" method="post">
                        <div class="form-group">
                            <label>开课课程号</label>
                            <input type="text" class="form-control" name="className" placeholder="请输入课程名">
                            <label>请输入班级人数</label>
                            <input type="number" class="form-control" name="studentNum" placeholder="请输入班级人数">
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="dist/js/jquery-1.11.2.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="dist/js/bootstrap.js"></script>
</body>
</html>
