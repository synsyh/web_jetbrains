<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: sunning
  Date: 2016/12/3
  Time: 下午10:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Untitled Document</title>
    <!-- Bootstrap -->
    <link href="dist/css/bootstrap.css" rel="stylesheet">
    <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li>
                <a href="getclass.jsp">开课</a>
            </li>
            <li>
                <a href="setexam.jsp">出题</a>
            </li>
            <li>
                <a href="exammaintain.html">题库维护</a>
            </li>
            <li><a href="setpaper.jsp">组卷</a></li>
            <li>
                <a href="#">批卷</a>
            </li>
            <li>
                <a href="#">设置</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->
    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h2>组卷界面</h2>
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown">
                            选择您要组卷的科目
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a>
                            </li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
                        </ul>
                    </div>
                    <div class="form-group">
                        <form action="/setpaper" method="post" role="form">
                            <label>请输入考试科目</label>
                            <input type="text" class="form-control" name="classname">
                            <label>选择题总分数</label>
                            <input type="number" class="form-control" name="selallmark">
                            <label>选择题每题分数</label>
                            <input type="number" class="form-control" name="selmark">
                            <label>填空题分数</label>
                            <input type="number" class="form-control" name="filallmark">
                            <label>选择题每题分数</label>
                            <input type="number" class="form-control" name="filmark">
                            <label>判断题分数</label>
                            <input type="number" class="form-control" name="judallmark">
                            <label>判断题每题分数</label>
                            <input type="number" class="form-control" name="judmark">
                            <label>简答题分数</label>
                            <input type="number" class="form-control" name="desallmark">
                            <label>简答题每题分数</label>
                            <input type="number" class="form-control" name="desmark">
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
