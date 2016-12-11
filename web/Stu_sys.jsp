<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="new-backstage/css/style_index.css" />
<script type="text/javascript" src="<c:url value="/new-backstage/js/jquery-1.6.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/new-backstage/js/index.js"/>"></script>
</head>

<body>
<div class="nav-top">
	<span>学生在线考试系统</span>
    <div class="nav-topright">

        <span>您好：<%=session.getAttribute("username") %></span><span><a href="login.jsp">注销</a><%session.invalidate();%></span>
    </div>
</div>
<div class="nav-down">
	<div class="leftmenu1">
        <div class="menu-oc"><img src="<c:url value="/new-backstage/images/menu-all.png"/>" /></div>
    	<ul>
        	<li>
            	<a class="a_list a_list1">在线考试</a>
                <div class="menu_list menu_list_first">
                	<a class="lista_first" href="/stu_sys?name=examlist&t=<%=Math.random()%>">考试列表</a>
                    <a href="/stu_sys?name=score&t=<%=Math.random()%>">考试成绩</a>
                </div>
            </li>
            <li>
            	<a class="a_list a_list2">个人信息</a>
                <div class="menu_list">
                	<a href="/stu_sys?name=password">修改密码</a>
                    <a href="/stu_sys?name=stuinfo&t=<%=Math.random()%>">查看信息</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="leftmenu2">
    	<div class="menu-oc1"><img src="<c:url value="/new-backstage/images/menu-all.png"/>" /></div>
        <ul>
        	<li>
            	<a class="j_a_list j_a_list1"></a>
                <div class="j_menu_list j_menu_list_first">
                	<span class="sp1"><i></i>在线考试</span>
                	<a href="/stu_sys?name=examlist&t=<%=Math.random()%>">考试列表</a>
                    <a href="/stu_sys?name=score&t=<%=Math.random()%>">考试成绩</a>

                </div>
            </li>
            <li>
            	<a class="j_a_list j_a_list2"></a>
                <div class="j_menu_list">
                	<span class="sp2"><i></i>个人信息</span>
                	<a href="change_pwd.html">修改密码</a>
                    <a href="/stu_sys?name=stuinfo&t=<%=Math.random()%>">查看信息</a>
                </div>
            </li>
        </ul>

    </div>
    <div class="rightcon">
    	<div class="right_con">
        	<p style="text-align:left; margin-top:50px">学生考试规则</p>
            <p style="text-align:left;margin-left: 80px">1、考试期间教师不得以任何理由请假。<br/>
                考前各任课教师要求学生准备好草稿纸及考试用具，考完试不能让学生私自将草稿纸带走。  <br/>
                ２、 学生进入考场，不得携带与本课有关的书籍、可带必须的文具，但不得带计算尺、计算器。  <br/>
                3、  学生必须在考试前三十分到操场集合后清点人数，有缺考学生及时  上报教导处，排队进入考场， <br/>
                4、 考试三十分钟内，不准交卷出场。 学生对试题有疑难时，不得向监考人员询问。<br/>
                5、 考生答题时，一、二年级一律用铅笔，三年级以上用钢笔或圆珠笔书写，字迹要工整、清楚。学生在试卷上认真填写自己的姓名、班级。  <br/>
                6、  严把考试质量关，严肃考纪考风，杜绝人为操作如发现学生作弊或抄袭，考分为零并检讨。教师漏题或有给学生说答案现象，涉及到谁将严肃处理。 <br/>
                7、  如在一个班内出现多名学生抄袭，取消优秀班级评比资格并对任课教师做相应的处罚。<br/></p>
        </div>
    </div>
</div>
<div style="text-align:center;">

</div>

</body>
</html>

<script type="text/javascript">

</script>
