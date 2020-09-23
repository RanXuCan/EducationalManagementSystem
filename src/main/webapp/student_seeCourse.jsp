<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.rxc.entity.Course" %>
<%@ page import="com.rxc.entity.Teacher" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    
    <link href="css/teacher_index.css" rel="stylesheet" />
    
    <title>学生教务管理系统</title>
</head>
<body>
    <div class="PublicHead clearfix">
        <div class="leftBox clearfix">
            <div class="storeLogo">
                <img src="images/storelogo.png" />
            </div>

            <div class="storeText">
                学生教务管理系统
            </div>
        </div>
        <div class="RightBox clearfix">
            <div class="ManagerPhotoBox">
                <div class="ManagerPic">
                </div>
                <div class="ManagerName">
                    欢迎你，学生<h5 style="display:inline;">${studentname}</h5>
                </div>
            </div>
            <a href="student_login.jsp">
                <div class="dropOutBox">
                    <i class="">
                    </i>
                    <span>退出</span>
                </div>
            </a>
        </div>
    </div>

    <div class="PublicDownWhole clearfix">
        <!--左侧-->
        <div class="leftBox">
            <ul>
                <a href="student_index.jsp"> <li class=""><i class=""></i><span>我要选课</span></li></a>
                <a href="studentSeeCourse">
                    <li class="Select"><i class=""></i><span>我的课程</span></li>
                </a>
                <a href="studentSeeScore"> <li class=""><i class=""></i><span>我的成绩</span></li></a>
                <a href="studentSeeInfor"> <li class=""><i class=""></i><span>个人信息</span></li></a>
            </ul>
        </div>
        <!--右侧-->
        <div class="RightBox">
            <div class="PublicContentBox">
                <!--公用指向页面名字-->
                <div class="PublicPointToAgeText">
                    <span class="span1">教务管理  </span> <span class="span2">学生教务管理列表</span>
                </div>
                
             
             
                <div class="InquireTableBox">
                    <div class="headbox">
                        <div class="headboxtext">
                            <span class="span1">已选课程如下</span>
                        </div>
                        
                        
                         <div class="PublicBtnIcon">
                            
                            <span>&nbsp;</span>
                        </div>
                    </div>

                    <!--查询到的表格-->
                    <div class="InquireSelectTable">

                        <table class="PublicTableCss">
                            <thead>
                                <tr>
                                    <td>课程名称</td>
                                    <td>任课教师</td>
                                </tr>

                            </thead>
                            <tbody>
<%
     List<Course> courselist = (List<Course>)request.getAttribute("courselist");
     List<Teacher> teacherlist = (List<Teacher>)request.getAttribute("teacherlist");
     if(courselist!=null && teacherlist!=null && !teacherlist.isEmpty()){
    	for(int j = 0;j<courselist.size();j++){
%>
                                <tr>
                                    <td><%= courselist.get(j).getCname() %></td>
                                    <td><%= teacherlist.get(j).getTname() %></td>
                                
                                </tr>

<%      }
    }else{
%>
                            <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
<%
         } 
%> 
                            </tbody>
                        </table>
                        <br><br>
                    </div>
                    
                </div>
            </div>

        </div>
    </div>
<% String message = (String)request.getAttribute("message");
if(message != null){
%>
<script type="text/javascript">
alert("<%= message%>");
</script>
<%} %>
</body>
</html>